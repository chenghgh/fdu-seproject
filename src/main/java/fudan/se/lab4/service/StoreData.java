package fudan.se.lab4.service;

import fudan.se.lab4.Util.LoadJDBCDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.ConnectException;
import java.sql.*;

public interface StoreData {
    static final String USER = "Se19";
    static final String PASS = "Se19_3CARRY1";
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://149.28.79.137:3306/SEProject";
    Logger logger = LoggerFactory.getLogger(StoreData.class);
    static String getEncryptedUname(){
        return USER;
    }
    static String getEncryptedPass(){
        return PASS;
    }
    static void storeDrinkItemBasicPrice() {
        Connection connection = null;
        Statement stmt = null;
        String sql;
        LoadJDBCDriver.LoadDriver();
        try {
            connection = DriverManager.getConnection(DB_URL, getEncryptedUname(), getEncryptedPass());
            stmt = connection.createStatement();
            sql = "create DATABASE SEProject";
            stmt.executeUpdate(sql);
            sql = "use SEProject";
            stmt.executeQuery(sql);
            sql = "create table DrinkPriceTable(Name VARCHAR(10), Price DOUBLE, primary key(Name))";
            int res = stmt.executeUpdate(sql);
            if(res != -1){
                logger.info("DrinkPriceTable created successfully!");
                sql = "insert into DrinkPriceTable(Name, price) values('Espresso', 20.0)";
                stmt.executeUpdate(sql);
                sql = "insert into DrinkPriceTable(Name, price) values('Cappuccino', 22.0)";
                stmt.executeUpdate(sql);
                sql = "insert into DrinkPriceTable(Name, price) values('GreenTea', 16.0)";
                stmt.executeUpdate(sql);
                sql = "insert into DrinkPriceTable(Name, price) values('BlackTea', 18.0)";
                stmt.executeUpdate(sql);
//                sql = "select * from DrinkPriceTable";
//                ResultSet rs = stmt.executeQuery(sql);
//                while(rs.next()){
//                    System.out
//                            .println(rs.getString("Name") + "\t" + rs.getDouble("Price"));
//                }
            }
        } catch (SQLException e) {
            logger.info("Connection failed!");

        } finally {
            try{
                if(stmt != null)
                    stmt.close();
            } catch (SQLException e){
                logger.info("close failed");
            } finally {
                try{
                    if(connection != null)
                        connection.close();
                }catch (SQLException e){
                    logger.info("close failed");
                }
            }


        }
    }

    static void storeIngrePrice() throws SQLException {
        Connection connection = null;
        Statement stmt = null;
        String sql;
        LoadJDBCDriver.LoadDriver();
        try {
            connection = DriverManager.getConnection(DB_URL, getEncryptedUname(), getEncryptedPass());
            stmt = connection.createStatement();
            sql = "create table IngredientPriceTable(Name VARCHAR(10), Price DOUBLE, primary key(Name))";
            int res = stmt.executeUpdate(sql);
            if(res != -1){
                logger.info("IngredientPriceTable created successfully!");
                sql = "insert into IngredientPriceTable(Name, Price) values('milk', 1.2)";
                stmt.executeUpdate(sql);
                sql = "insert into IngredientPriceTable(Name, Price) values('chocolate', 1.2)";
                stmt.executeUpdate(sql);
                sql = "insert into IngredientPriceTable(Name, Price) values('sugar', 1.0)";
                stmt.executeUpdate(sql);
                sql = "insert into IngredientPriceTable(Name, Price) values('cream', 1.0)";
                stmt.executeUpdate(sql);
                sql = "select * from IngredientPriceTable";
            }

        } catch (SQLException e) {
            logger.info("Connection failed!");

        }finally {
            try{
                if(stmt != null)
                    stmt.close();
            } catch (SQLException e){
                logger.info("close failed");
            } finally {
                try{
                    if(connection != null)
                        connection.close();
                }catch (SQLException e){
                    logger.info("close failed");
                }
            }
        }
    }

    static void storeSizeExtraPrice() throws SQLException {
        Connection connection = null;
        Statement stmt = null;
        String sql;
        LoadJDBCDriver.LoadDriver();
        try {
            connection = DriverManager.getConnection(DB_URL, getEncryptedUname(), getEncryptedPass());
            stmt = connection.createStatement();
            sql = "create table SizeExtraPriceTable(Name VARCHAR(10), id INT, Price DOUBLE)DEFAULT CHARSET=utf8;";
            int res = stmt.executeUpdate(sql);
            if(res != -1){
                logger.info("DrinkPriceTable created successfully!");
                sql = "insert into SizeExtraPriceTable(Name, id, Price) values('Espresso', 1 , 2.0);";
                stmt.executeUpdate(sql);
                sql = "insert into SizeExtraPriceTable(Name, id, Price) values('Espresso', 2, 4.0)";
                stmt.executeUpdate(sql);
                sql = "insert into SizeExtraPriceTable(Name, id, Price) values('Espresso', 3, 6.0)";
                stmt.executeUpdate(sql);
                sql = "insert into SizeExtraPriceTable(Name, id, Price) values('Cappuccino', 1, 2.0)";
                stmt.executeUpdate(sql);
                sql = "insert into SizeExtraPriceTable(Name, id, Price) values('Cappuccino', 2, 4.0)";
                stmt.executeUpdate(sql);
                sql = "insert into SizeExtraPriceTable(Name, id, Price) values('Cappuccino', 3, 6.0)";
                stmt.executeUpdate(sql);
                sql = "insert into SizeExtraPriceTable(Name, id, Price) values('GreenTea', 1, 2.0)";
                stmt.executeUpdate(sql);
                sql = "insert into SizeExtraPriceTable(Name, id, Price) values('GreenTea', 2, 4.0)";
                stmt.executeUpdate(sql);
                sql = "insert into SizeExtraPriceTable(Name, id, Price) values('GreenTea', 3, 5.0)";
                stmt.executeUpdate(sql);
                sql = "insert into SizeExtraPriceTable(Name, id, Price) values('BlackTea', 1, 2.0)";
                stmt.executeUpdate(sql);
                sql = "insert into SizeExtraPriceTable(Name, id, Price) values('BlackTea', 2, 4.0)";
                stmt.executeUpdate(sql);
                sql = "insert into SizeExtraPriceTable(Name, id, Price) values('BlackTea', 3, 5.0)";
                stmt.executeUpdate(sql);
                sql = "select * from SizeExtraPriceTable";
            }

        } catch (SQLException e) {
            logger.info("Connection failed!");

        }finally {
            try{
                if(stmt != null)
                    stmt.close();
            } catch (SQLException e){
                logger.info("close failed");
            } finally {
                try{
                    if(connection != null)
                        connection.close();
                }catch (SQLException e){
                    logger.info("close failed");
                }
            }

        }
    }

    static void creatUserInfoTable() throws SQLException {
        LoadJDBCDriver.LoadDriver();
        Connection connection = null;
        Statement stmt = null;
        String sql = "create table userInfoTable(Name VARCHAR(100), Password VARCHAR(100), primary key(Name));";
        try {
            connection = DriverManager.getConnection(DB_URL, getEncryptedUname(), getEncryptedPass());
            stmt = connection.createStatement();
            int res = stmt.executeUpdate(sql);
            if(res != -1)
                logger.info("UserInfo table created successfully!");
            else
                logger.info("failed to create Userinfo table!");
        } catch (SQLException e) {
            logger.info("connection failed!");
        } finally {
            try{
                if(stmt != null)
                    stmt.close();
            } catch (SQLException e){
                logger.info("close failed");
            } finally {
                try{
                    if(connection != null)
                        connection.close();
                }catch (SQLException e){
                    logger.info("close failed");
                }
            }
        }

    }
}
