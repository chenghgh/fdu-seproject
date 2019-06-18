package fudan.se.lab4.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoadJDBCDriver {
    static Logger logger = null;
    public static void LoadDriver(){
        logger = InitUtil.sysInfoLogger;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
//            logger.info("Load jdbc driver successfully!");
        } catch (ClassNotFoundException e) {
            logger.info("Cannot load jdbc driver!");
        }
    }
}
