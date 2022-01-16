package com.homeloan.tests;

import java.util.logging.Level;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.homeloan.init.InitHomeLoan;

public class TestHomeLoan extends InitHomeLoan{

	private static String  sRet = null;
	
	@BeforeClass
	public static void setup() throws Exception {
		// driver.manage().timeouts().implicitlyWait(SMALL_SLEEP, TimeUnit.)
		try {
			System.out.println("Before driver.get...");
			driver.get(BASE_URL);
			System.out.println("current url: \t" + driver.getCurrentUrl());
			//driver.manage().window().maximize();			
		} catch (Exception e) {
			clogger.log(Level.SEVERE, e.getMessage(), e);
			clogger.info("Exiting execution!!! as base setup itself failed.");
			e.printStackTrace();
			driver.quit();
			System.exit(1);
		}
	}
	
	@Test
	public void test_HomeLoanRepay() throws Exception{
		sRet = homepagemethods.calculateHomeLoanReypayment("200000");
		System.out.println("Returned : "+sRet);
		logger.info("After executing test case returned string :"+sRet);
		Assert.assertEquals("done", sRet);
	}

	
	@AfterClass
	public static void teardown() throws Exception {
		try {
			driver.quit();
		} catch (Exception e) {
			clogger.info(e.getMessage());
		} 
	}
	
}
