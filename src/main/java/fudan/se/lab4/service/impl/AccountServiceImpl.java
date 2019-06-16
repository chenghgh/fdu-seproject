package fudan.se.lab4.service.impl;

import fudan.se.lab4.constant.InfoConstant;
import fudan.se.lab4.entity.User;
import fudan.se.lab4.repository.impl.UserRepositoryImpl;
import fudan.se.lab4.service.AccountService;
import fudan.se.lab4.service.DataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

public class AccountServiceImpl implements AccountService {
    private UserRepositoryImpl usrRepo = new UserRepositoryImpl();
    private static Logger logger = LoggerFactory.getLogger(AccountService.class);
    private boolean loginStatus = false;
    private boolean signupStatus = false;

    @Override
    public boolean signup(User user) {
        /** examine whether the user name exists or not **/

        if(DataService.ifUserExists(user)) {
            signupStatus = false;
            logger.info(MessageFormat.format(InfoConstant.USER_EXIST, user.getName()));
        }
        else{
            signupStatus = true;
            DataService.creatUser(user);
            logger.info(MessageFormat.format(InfoConstant.SIGNUP_SUCCESSFUL, user.getName()));

        }
        return signupStatus;
    }

    @Override
    public boolean login(User user) {
        String name = user.getName();

        if(!DataService.checkInfo(user)){
            this.loginStatus = false;
            logger.info(MessageFormat.format(InfoConstant.LOGIN_UNSUCCESSFUL, name));
        }
        else {
            this.loginStatus = true;
            logger.info(MessageFormat.format(InfoConstant.LOGIN_SUCCESSFUL, name));
        }

        return loginStatus;

    }

    @Override
    public boolean checkStatus() {
        if(this.loginStatus) {
            logger.info(InfoConstant.LOGGED_IN);
        }
        else {
            logger.info(InfoConstant.PLEASE_LOGIN);
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

}
