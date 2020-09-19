package trello.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import trello.exception.CustomRuntimeException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class Config {

    private static final String BASE_URL = "baseUrl";
    private static final String KEY = "key";
    private static final String TOKEN = "token";
    private static final Logger LOGGER = LogManager.getLogger();

    private static Config instance = new Config();
    private Properties properties;

    private Config() {
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties")) {
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            String message = "There was an error";
            LOGGER.error(message);
            LOGGER.error(e);
            throw new CustomRuntimeException(message, e);
        }
    }

    public static Config getInstance() {
        return instance;
    }

    private String getConfig(String property) {
        String baseUrl = System.getProperty(property);
        if (baseUrl == null) {
            return properties.getProperty(property);
        }
        return baseUrl;
    }

    public String getBaseUrl() {
        return getConfig(BASE_URL);
    }

    public String getKey() {
        return getConfig(KEY);
    }

    public String getToken() {
        return getConfig(TOKEN);
    }
}
