package libs;

import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFiles {
    private final String configFilePath = "configuration/config.properties";

    private Properties prop = null;

    PropertyFiles() {
        initPropertyFiles();
    }

    private void initPropertyFiles() {
        try (InputStream input = new FileInputStream(configFilePath)) {
            prop = new Properties();
            prop.load(input);
        } catch (IOException ex) {
            functionThrowAssertMessage("Property file loading failed. Please check configuration directory");
        }
    }


    String getPropertyValue(String key) {
        String value = null;
        try {
            value = prop.getProperty(key).trim();
        } catch (Exception e) {
            functionThrowAssertMessage("Property value : " + key + " not found in the file. Please check configuration/config.properties file");
        }
        return value;
    }

    public void functionThrowAssertMessage(String message) {
        Assert.fail(message);
    }


}
