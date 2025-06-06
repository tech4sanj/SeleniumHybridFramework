package com.mystore.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

	Properties properties;
	String path="C:\\d data\\SeleniumFramework\\MyStoreV1\\Configuration\\config.properties";

	public ReadConfig() throws IOException {
		properties =new Properties();

		FileInputStream fis=new FileInputStream(path);

		properties.load(fis);
	}

	public String getBaseUrl() 
	{

		String value=properties.getProperty("baseUrl");
		if(value!=null) 
			return value;
		else 
			throw new RuntimeException("url not found");
	}
	public String getBrowser() {

		String value=properties.getProperty("browser");
		if(value!=null) 
			return value;
		else 
			throw new RuntimeException("Browser not found");
	}

}
