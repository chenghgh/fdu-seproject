package fudan.se.lab4.entity;

import javax.sound.sampled.Line;
import java.util.Locale;
import java.util.ResourceBundle;

public class Switch {

    private String language;
    private String currency;

    private ResourceBundle InfoLanguage;
    private Locale locale;

    private Switch(){
        autoSet();
    }

    private void autoSet() {
        this.currency = "CNY";
        this.language = "en_US";
        locale = new Locale(this.getLanguage());
        InfoLanguage = ResourceBundle.getBundle("MultiLanguage.info",locale);
    }

    public void manualSet(String currency, String language) {
        this.currency = currency;
        this.language = language;
        locale = new Locale(this.getLanguage());
        InfoLanguage = ResourceBundle.getBundle("MultiLanguage.info",locale);
    }


    public String getCurrency() { return currency; }
    public String getLanguage() { return language; }

    public ResourceBundle getInfoLanguage() {
        return InfoLanguage;
    }

    private static Switch aSwitch = new Switch();

    public static Switch getInstance(){
        return aSwitch;
    }

}
