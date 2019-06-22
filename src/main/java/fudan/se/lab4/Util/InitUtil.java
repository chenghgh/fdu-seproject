package fudan.se.lab4.Util;

import fudan.se.lab4.entity.Switch;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;

public class InitUtil {
    public static final Switch aSwitch = Switch.getInstance();
    public static final ResourceBundle InfoLanguage = aSwitch.getInfoLanguage();;
    public static Logger sysInfoLogger;
    public static Logger orderInfoLogger;
    public static void init(){
        PropertyConfigurator.configure("config/log4j.properties");
        sysInfoLogger = LoggerFactory.getLogger("sysInfoLogger");
        orderInfoLogger = LoggerFactory.getLogger("orderInfoLogger");
    }

    private void showItemInfo(){

    }
}
