package fudan.se.lab4.service.impl;

import fudan.se.lab4.Util.InitUtil;
import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.dto.Ingredient;
import fudan.se.lab4.entity.User;
import fudan.se.lab4.repository.impl.UserRepositoryImpl;
import fudan.se.lab4.service.AccountService;
import fudan.se.lab4.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static fudan.se.lab4.Util.InitUtil.InfoLanguage;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Map;

public class AccountServiceImpl implements AccountService {
    private UserRepositoryImpl usrRepo = new UserRepositoryImpl();
    private static Logger logger = InitUtil.sysInfoLogger;
    private boolean loginStatus = false;
    private boolean signupStatus = false;
    private static Logger logger2 = LoggerFactory.getLogger(AccountServiceImpl.class);
    @Override
    public boolean signup(User user) {
        /** examine whether the user name exists or not **/

        if(DataService.ifUserExists(user)) {
            signupStatus = false;
            logger.info(MessageFormat.format(InfoConstant.USER_EXIST, user.getName()));
            System.out.println(MessageFormat.format(InfoLanguage.getString("USER_EXIST"), user.getName()));
        }
        else{
            if(this.checkName(user.getName()) && this.checkPassword(user.getPassword())){
                DataService.creatUser(user);
                logger.info(MessageFormat.format(InfoConstant.SIGNUP_SUCCESSFUL, user.getName()));
                System.out.println(MessageFormat.format(InfoLanguage.getString("SIGNUP_SUCCESSFUL"), user.getName()));
                signupStatus = true;
            }
            else {
                signupStatus = false;
                logger.info(InfoConstant.WRONG_KEY_USER_NAME);
                System.out.println(InfoLanguage.getString("WRONG_KEY_USER_NAME"));
            }

        }
        return signupStatus;
    }

    @Override
    public boolean login(User user) {
        String name = user.getName();

        if(!DataService.checkInfo(user)){
            this.loginStatus = false;
            logger.info(MessageFormat.format(InfoConstant.LOGIN_UNSUCCESSFUL, name));
            System.out.println(MessageFormat.format(InfoLanguage.getString("LOGIN_UNSUCCESSFUL"), name));
        }
        else {
            this.loginStatus = true;
            logger.info(MessageFormat.format(InfoConstant.LOGIN_SUCCESSFUL, name));
            System.out.println(MessageFormat.format(InfoLanguage.getString("LOGIN_SUCCESSFUL"), name));
        }

        return loginStatus;

    }

    @Override
    public boolean checkStatus() {
        if(this.loginStatus) {
            logger.info(InfoConstant.LOGGED_IN);
            System.out.println(InfoLanguage.getString("LOGIN_TRUE"));
        }
        else {
            logger.info(InfoConstant.PLEASE_LOGIN);
            System.out.println(InfoLanguage.getString("LOGIN_FALSE"));
        }
        return this.loginStatus;
    }

    @Override
    public boolean checkName(String name) {
        int nameLength = name.length();
        //check the length
        if(nameLength < 8 || nameLength > 50)
            return false;
        if(!name.startsWith("starbb_"))
            return false;
        String pattern = "^\\w*$";
        return name.matches(pattern);
    }

    @Override
    public boolean checkPassword(String password) {
        int nameLength = password.length();
        //check the length
        if(nameLength < 8 || nameLength > 100)
            return false;
        String pattern = "^\\w+$";
        if(!password.matches(pattern))
            return false;
        else{
            if(password.matches("^[a-zA-Z]+$") || password.matches("^[0-9]+$") || password.matches("^[_]+$") ||
                password.matches("^[a-zA-Z0-9]+$") || password.matches("^[0-9_]+$") ||password.matches("^[a-zA-Z_]+$"))
                    return false;
        }
        return true;
    }

    @Override
    public String getDescription() {
        if(this.loginStatus == false){
            logger.info(InfoConstant.PERMISSION_DENIED);
            System.out.println(InfoLanguage.getString("PERMISSION_DENIED"));
        }

        Map<String, ArrayList<String>> mapIngre = DataService.getTableFields("IngredientPriceTable");
        Map<String, ArrayList<String>> mapDrink = DataService.getTableFields("DrinkPriceTable");
        Map<String, ArrayList<String>> mapSize = DataService.getTableFields("SizeExtraPriceTable");
        printMap(mapIngre);
        printMap(mapDrink);
        printMap(mapSize);
        return null;
    }

    private void printMap(Map<String,ArrayList<String>> map){
        for(String key:map.keySet()){
            ArrayList values = (ArrayList)map.get(key);
            String valueS = values.toString();
            System.out.println("name"+ key+"   value ="+valueS);
        }
    }

}
