package fudan.se.lab4;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import fudan.se.lab4.entity.Switch;
import fudan.se.lab4.entity.User;
import fudan.se.lab4.service.impl.AccountServiceImpl;


import java.io.IOException;
import java.util.ResourceBundle;

public class Lab4Application {
    public static Switch aSwitch = Switch.getInstance();
    public static ResourceBundle InfoLanguage = aSwitch.getInfoLanguage();;

    static User user;
    static AccountServiceImpl accountService = new AccountServiceImpl();

    public static void main(String[] args) throws IOException, GeoIp2Exception {
        aSwitch.manualSet();
    }
}

