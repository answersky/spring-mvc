package com.java.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class Properties {

    private java.util.Properties props = new java.util.Properties();
    private String filename;

    public Properties() {
    }

    public Properties(String filename) {
//		System.out.println(filename);
        this.filename = filename;
    }

    public void load() throws IOException {
        java.util.Properties defaultProps = buildDefaultProperty();
        java.util.Properties customProps = buildCustomProperty();
        props.putAll(defaultProps);
        props.putAll(customProps);
    }

    private java.util.Properties buildCustomProperty() throws IOException {
        java.util.Properties p = new java.util.Properties();
        if (filename == null) {
            filename = "system.properties";
        }
        File file = new File(filename);
        if (!file.exists()) {
//			logger.info("default value will be load because property file can't be found from "
//					+ file.getCanonicalPath());
        } else {
//			System.out.println(file.getAbsolutePath());
            InputStream is = new FileInputStream(file);
            p.load(is);
        }
        return p;

    }

    private java.util.Properties buildDefaultProperty() throws IOException {
        java.util.Properties p = new java.util.Properties();
        InputStream is = this.getClass().getResourceAsStream(
                "/config/" + "system.properties");

        p.load(is);
        return p;

    }

    public void setProperty(String key, String value) {
        props.setProperty(key, value);
    }


    public String getValue(String key) {
        return (String) props.get(key);
    }

    public String getValue(String key, String defaultValue) {
        return (String) props.getProperty(key, defaultValue);
    }

    public int getInt(String key) {

        String value = (String) props.getProperty(key);
        int result = 0;
        try {
            result = Integer.parseInt(value);
        } catch (NumberFormatException e) {
            //nothing
        }
        return result;
    }


}
