package com.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

public class PropertiesOperations {
	static Properties prop=new Properties();
public static  String getPropertyValueByKey(String key)
{
	String propFile = System.getProperty("user.dir")+"\\src\\main\\resources\\config.properties";
	try {
		FileInputStream fis= new FileInputStream(propFile);
		prop.load(fis);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	String keyValue= prop.get(key).toString();
	if(StringUtils.isEmpty(keyValue))
	{
		try {
			throw new Exception("Value is not specified for the key: "+key+ "in properties file.");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	return keyValue;
	}
}
