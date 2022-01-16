package com.homeloan.init;

import java.time.Duration;

import org.junit.BeforeClass;

import com.homeloan.methods.HomeLoanMehtods;
import com.selenium.base.InitWebDriver;
import com.selenium.util.WebDriverUtil;

public class InitHomeLoan extends InitWebDriver {

	protected static final String BASE_URL = lookuputil.getConfig("BASE_URL").trim();
	protected static HomeLoanMehtods homepagemethods;
	protected static WebDriverUtil webdriverUtil = null;
	 @BeforeClass
	 public static void setUpInit() throws Exception
	   {
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));		 
		 homepagemethods = new HomeLoanMehtods();
		 webdriverUtil = new WebDriverUtil();
	   }
}
