package Application;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguagePack {

    private static ResourceBundle resourceBundle;

    public static void setLanguage(Locale locale){
        resourceBundle = ResourceBundle.getBundle("messages", locale);
    }

    public static String getTranslation(String key){
        if (resourceBundle != null && key != null){
            return resourceBundle.getString(key);
        }
        return null;
    }
}
