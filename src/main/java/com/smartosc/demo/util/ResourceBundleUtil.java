package com.smartosc.demo.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by smartosc on 5/5/2016.
 */
public class ResourceBundleUtil {
    private static ResourceBundleUtil instance;
    private final String BUNDLE_NAME = "app_configuration.properties";
    private Properties pros;
    private final static Logger logger = LogManager.getLogger(ResourceBundleUtil.class.getName());

    private ResourceBundleUtil() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(BUNDLE_NAME);
        Reader reader = new InputStreamReader(inputStream, "UTF-8");
        pros = new Properties();
        pros.load(reader);
    }

    public static ResourceBundleUtil getInstance() {
        if (instance == null) {
            synchronized (ResourceBundleUtil.class) {
                if (instance == null)
                    try {
                        instance = new ResourceBundleUtil();
                    } catch (IOException e) {
                        //logger.error("Have problem: " + e);
                    }
            }
        }
        return instance;
    }

    public String loadResource(String resourceName) {
        return pros.getProperty(resourceName);
    }
}
