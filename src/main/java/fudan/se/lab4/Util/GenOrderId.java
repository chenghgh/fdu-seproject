package fudan.se.lab4.Util;

import java.util.UUID;

public class GenOrderId {
    public static String getId(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
