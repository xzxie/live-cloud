package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesUtil {
	
	private static final Logger logger = Logger.getLogger(Properties.class);
	
	public static Properties getPropertiesMap(String srcProperiesName) {
		InputStream is = null;
		Properties properties = new Properties();
		is = PropertiesUtil.class.getClassLoader().getResourceAsStream(srcProperiesName);
		try {
			properties.load(is);
		} catch (IOException e) {
			logger.error("load property file failed", e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return properties;
	}
	

	public static void main(String[] args) {
		Properties p = PropertiesUtil.getPropertiesMap("misc.properties");
		String value = p.getProperty("iflocal");
		System.out.println(value);
	}

}
