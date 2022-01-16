package com.selenium.util;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebDriverUtil {
	
	
	public WebElement findWebElement(WebDriver drvr, String sLocator) throws Exception {
		WebElement webelement;
		if (sLocator.startsWith("css=")) {

			webelement = drvr.findElement(By.cssSelector(sLocator.substring(4, sLocator.length())));
			//System.out.println(" css locator used for : " + sLocator);
		} else if ((sLocator.startsWith("xpath=")) || (sLocator.startsWith("//"))) {
			// WebElement webelement;
			if (sLocator.startsWith("xpath=")) {
				webelement = drvr.findElement(By.xpath(sLocator.substring(6, sLocator.length())));
			} else {
				webelement = drvr.findElement(By.xpath(sLocator));
			}
			//System.out.println(" xpath locator used for :     " + sLocator);
		} else if (sLocator.startsWith("class=")) {
			// WebElement webelement = drvr.findElement(By.className(sLocator.substring(6,
			// sLocator.length())));
			webelement = drvr.findElement(By.className(sLocator.substring(6, sLocator.length())));
			//System.out.println(" class name locator used for :     " + sLocator);
		} else if (sLocator.startsWith("name=")) {
			// WebElement webelement = drvr.findElement(By.name(sLocator.substring(5,
			// sLocator.length())));
			webelement = drvr.findElement(By.name(sLocator.substring(5, sLocator.length())));
			//System.out.println(" name locator used for :     " + sLocator);
		} else if (sLocator.startsWith("tag=")) {
			// WebElement webelement = drvr.findElement(By.tagName(sLocator.substring(4,
			// sLocator.length())));
			webelement = drvr.findElement(By.tagName(sLocator.substring(4, sLocator.length())));
			//System.out.println(" tag locator used for :     " + sLocator);
		} else if (sLocator.startsWith("link=")) {
			// WebElement webelement = drvr.findElement(By.linkText(sLocator.substring(5,
			// sLocator.length())));
			webelement = drvr.findElement(By.linkText(sLocator.substring(5, sLocator.length())));
			//System.out.println(" link locator used for :     " + sLocator);
		} else if (sLocator.startsWith("plink=")) {
			// WebElement webelement =
			// drvr.findElement(By.partialLinkText(sLocator.substring(5,
			// sLocator.length())));
			webelement = drvr.findElement(By.partialLinkText(sLocator.substring(5, sLocator.length())));
			//System.out.println(" partial link locator used for :     " + sLocator);
		} else {
			webelement = drvr.findElement(By.id(sLocator));
			//System.out.println(" id locator used for :     " + sLocator);
		}		
		return webelement;
	}
	
	public WebElement getWebElement(WebDriver drvr, String sLocator) throws Exception {
		WebElement webelement = findWebElement(drvr, sLocator);
		
		((JavascriptExecutor) drvr).executeScript("arguments[0].scrollIntoView(true)", new Object[] { webelement });

		return webelement;
	}

	
	

	
	

}
