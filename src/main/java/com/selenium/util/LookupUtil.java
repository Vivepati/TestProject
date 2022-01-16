package com.selenium.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class LookupUtil {
	
	public String getConfig(String sKey) {
		String sValue = "";
		String sConfingFile = "." +File.separator+"config"+File.separator+"config.properties";
		Properties prop = new Properties();
		
		try {
			File root = new File(System.getProperty("user.dir"));
			
			String sRootFolder = root.getAbsolutePath() ;
			sConfingFile = sRootFolder + File.separator + sConfingFile;			
			prop.load(new FileInputStream(sConfingFile));			
			sValue = prop.getProperty(sKey).trim();			
			return sValue;
			
		}catch(Exception e) {			
			System.out.println(e.getMessage());
			if(e.getMessage() == null) {
				throw new NoSuchFieldError( "No Key found in "+sConfingFile+": "  + sKey);
			}
		}		
		//System.out.println("ConfigFile: "+sConfingFile);
		return sValue;
	}

}
