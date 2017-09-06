package com.acme.edu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by vanbkin on 05.09.2017.
 */
public class LoggerConfig {
    public final String HOST;
    public final String PORT;

    public LoggerConfig(String host, String port) {

        this.HOST = getProperties().getProperty("host");
        this.PORT = getProperties().getProperty("host");
    }

    public Properties getProperties() {
        Properties properties = new Properties();
        String propFileName = "config.properties";

        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(propFileName))
        {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return properties;
    }
}
