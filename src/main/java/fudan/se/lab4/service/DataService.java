package fudan.se.lab4.service;
import fudan.se.lab4.Util.InitUtil;
import fudan.se.lab4.Util.LoadJDBCDriver;
import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.entity.User;
import org.apache.hadoop.yarn.webapp.hamlet.HamletSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static fudan.se.lab4.Util.InitUtil.InfoLanguage;
import static fudan.se.lab4.Util.InitUtil.aSwitch;


public interface DataService {
    String USER = "root";
    String PASS = "";
    String DB_URL = "jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
    Logger logger = InitUtil.sysInfoLogger;
    Logger logger2 = LoggerFactory.getLogger(DataService.class);
    static void closeAll(ResultSet res, Statement stmt, Connection connection) {
        try {
            if (res != null)
                res.close();
        } catch (SQLException e) {
            logger.info("close failed");
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException e) {
                logger.info("close failed");
            } finally {
                try {
                    if (connection != null)
                        connection.close();
                } catch (SQLException e) {
                    logger.info("close failed");
                }
            }
        }
    }
    static String getDrinkBasicPriceSQLQuery(int id){
        return "select Price" + aSwitch.getCurrency() + " from DrinkPriceTable where Id="+id;
    }
    static String getSizeExtraPriceSQLQuery(int id, int size){
        return "select Price" + aSwitch.getCurrency() +" from SizeExtraPriceTable where Id=\'"+id+"\' and Size="+size;
    }
    static String getIngrePriceSQLQuery(int id){
        return "select Price" + aSwitch.getCurrency() +" from IngredientPriceTable where Id=\'"+id+"\'";
    }
    static String getEncryptedUname(){
        return USER;
    }
    static String getEncryptedPass(){
        return PASS;
    }
    static double getDrinkBasicPrice(int id)  {
        double price = 0;
        int count = 0;
        String sql = "";
        LoadJDBCDriver.LoadDriver();
        Connection connection = null;
        Statement stmt = null;
        ResultSet res = null;
        try {
            connection = DriverManager.getConnection(DB_URL, getEncryptedUname(), getEncryptedPass());
            stmt = connection.createStatement();

            sql = getDrinkBasicPriceSQLQuery(id);

            res =stmt.executeQuery(sql);
            while(res.next()){
                count++;
                price = res.getDouble("Price"+aSwitch.getCurrency());
//                System.out.println("price:"+price);
            }
            if(count == 0){
                logger.info(MessageFormat.format(InfoConstant.DRINK_NOT_EXIST, id));
                logger2.info(InfoLanguage.getString("DRINK_NOT_EXIST"));
//                throw new RuntimeException(MessageFormat.format(InfoConstant.DRINK_NOT_EXIST, id));
            }
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } finally {
            closeAll(res, stmt, connection);

        }

        return price;
    }
    static double getSizeExtraPrice(int id, int size) {
        double price = 0;
        int count = 0;
        String sql = "";
        LoadJDBCDriver.LoadDriver();
        Connection connection = null;
        Statement stmt = null;
        ResultSet res = null;
        try {
            connection = DriverManager.getConnection(DB_URL, getEncryptedUname(), getEncryptedPass());
            stmt = connection.createStatement();
//            sql = "select Price from SizeExtraPriceTable where Name=\'"+drinkName+"\' and id="+size;
            sql = getSizeExtraPriceSQLQuery(id, size);
//            System.out.println(sql);
            res =stmt.executeQuery(sql);
            while(res.next()){
                count++;
                price = res.getDouble("Price" + aSwitch.getCurrency());
            }
            if(count == 0){
                logger.info(MessageFormat.format(InfoConstant.SIZE_NOT_EXIST, size));
                logger2.info(InfoLanguage.getString("SIZE_NOT_EXIST"));
//                throw new RuntimeException(MessageFormat.format(InfoConstant.SIZE_NOT_EXIST, size));
            }
        } catch (SQLException e) {
            logger.info("connection failed");
        } finally {
            closeAll(res, stmt, connection);
        }
        return price;
    }

    static double getIngredientPrice(int id)  {
        double price = 0;
        int count = 0;
        String sql = "";
        LoadJDBCDriver.LoadDriver();
        Connection connection = null;
        Statement stmt = null;
        ResultSet res = null;
        try {
            connection = DriverManager.getConnection(DB_URL, getEncryptedUname(), getEncryptedPass());
            stmt = connection.createStatement();
//            sql = "select Price from IngredientPriceTable where Name=\'"+ingreName+"\'";
            sql = getIngrePriceSQLQuery(id);
//            System.out.println(sql);
            res =stmt.executeQuery(sql);
            while(res.next()){
                count++;
                price = res.getDouble("Price" + aSwitch.getCurrency());
            }
            if(count == 0){
                logger.info(MessageFormat.format(InfoConstant.INGREDIENT_NOT_EXIST, id));
                logger2.info(InfoLanguage.getString("INGREDIENT_NOT_EXIST"));
//                throw new RuntimeException(MessageFormat.format(InfoConstant.INGREDIENT_NOT_EXIST, id));
            }
        } catch (SQLException e) {
            logger.info("connection failed");
        } finally {

            closeAll(res, stmt, connection);
        }
        return price;
    }

    static void creatUser(User usr) {
        String sql = "";
        LoadJDBCDriver.LoadDriver();
        Connection connection = null;
        Statement stmt = null;
        try {
            connection = DriverManager.getConnection(DB_URL, getEncryptedUname(), getEncryptedPass());
            stmt = connection.createStatement();
            sql = "insert into userInfoTable(Name, Password) " +
                    "values('"+usr.getName()+"', '"+usr.getPassword()+"')";
//            System.out.println(sql);
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            logger.info("connection failed");
        }finally {
            try{
                if(stmt != null)
                    stmt.close();
            }catch (SQLException e){
                logger.info("close failed");
            } finally {
                try{
                    if(connection != null)
                        connection.close();
                }catch (SQLException e){
                    logger.info("connection failed");
                }

            }
        }
    }

    static boolean ifUserExists(User usr)  {
        boolean flag = false;
        String sql = "";
        int count = 0;
        LoadJDBCDriver.LoadDriver();
        Connection connection = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(DB_URL, getEncryptedUname(), getEncryptedPass());
            stmt = connection.createStatement();
            sql = "select * from userInfoTable where Name='"+usr.getName()+"'";
//            System.out.println(sql);
            resultSet = stmt.executeQuery(sql);
            while(resultSet.next()){
                count++;
            }
            if(count == 0)
                flag = false;
            else
                flag = true;
        } catch (SQLException e) {
          logger.info("connection failed");
        }finally {
            closeAll(resultSet, stmt, connection);
        }
        return flag;
    }

    static boolean checkInfo(User user) {
        boolean flag = false;
        String sql = "";
        int count = 0;
        LoadJDBCDriver.LoadDriver();
        Connection connection = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(DB_URL, getEncryptedUname(), getEncryptedPass());
            stmt = connection.createStatement();
            sql = "select Password from userInfoTable where Name='"+user.getName()+"'";
//            System.out.println(sql);

            resultSet = stmt.executeQuery(sql);
            while(resultSet.next()){
                count++;
                flag = resultSet.getString("Password").equals(user.getPassword());
            }
            if(count == 0){
//                logger.info(MessageFormat.format(InfoConstant.USER_NOT_EXIST, user.getName()));
                flag = false;
            }

        } catch (SQLException e) {
            logger.info("connection failed");
        }finally {
            closeAll(resultSet, stmt, connection);
        }
        return flag;
    }

    static Map<String, ArrayList<String>> getTableFields(String tableName){
        Map<String, ArrayList<String>> data = new HashMap<>();
        String sql = "";
        LoadJDBCDriver.LoadDriver();
        Connection connection = null;
        Statement stmt = null;
        ResultSet res = null;
        try {
            connection = DriverManager.getConnection(DB_URL, getEncryptedUname(), getEncryptedPass());

            stmt = connection.createStatement();
            sql = "select * from seproject."+tableName;
            res = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = res.getMetaData();

            int columns = rsmd.getColumnCount();
            String[] fieldname = new String[columns];
            for(int i = 1;i <= columns;i++){
                fieldname[i-1] = rsmd.getColumnName(i);
                ArrayList<String> field = new ArrayList<>();
                data.put(fieldname[i-1],field);
            }
            ArrayList<String> a;
            while(res.next()){
                for(int j = 0; j < columns;j++){
                    String str = res.getString(j+1);
                    a = data.get(fieldname[j]);
                    data.remove(fieldname[j]);
                    a.add(str);
                    data.put(fieldname[j],a);
                }
            }

        } catch (SQLException e) {
            logger.info("connection failed");
        } finally {
            closeAll(res, stmt, connection);
        }
        return data;

    }
}

