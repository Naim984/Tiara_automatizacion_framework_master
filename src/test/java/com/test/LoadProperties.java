package com.test;

import com.test.exceptions.TestDataError;
import com.test.helper.PropertiesHelper;

import java.io.File;


public class LoadProperties {


    public static final int LARGE_WAIT = 40;
    public static final int MEDIUM_WAIT = 15;
    public static final int SHORT_WAIT = 5;
    public static final boolean CLOSE_BROWSER = true;
    public static String absPath = new File("").getAbsolutePath();


    /**
     * Gets the key from Config.properties related to chosen profile
     *
     * @param key
     **/

    public static String getProp(String key) {
        if ((key == null) || key.isEmpty()) {
            return "";
        } else {
            String value = PropertiesHelper.properties.getProperty(key);
            if (value == null) {
                throw new TestDataError("No value found in config.properties  for '" + key + "'.. ");
            }
            return value;
        }
    }

    public static void setProp(String key, String value) {
        PropertiesHelper.properties.setProperty(key, value);
        System.out.println("funciona");

    }

}