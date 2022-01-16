package com.homeloan.methods;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.homeloan.init.InitHomeLoan;

public class HomeLoanMehtods extends InitHomeLoan {

	public String calculateHomeLoanReypayment(String sLoanAmmount) throws Exception {
		
		String sHomeLoanRepayLink = "//a[text()='Repayments calculator']";
		String sInput_LoanAmount="//input[@id='amount']";
		String sInput_Term = "term";
		String sDropDown_RepayType = "interestOnly";		
		String sDropDown_InterestType = "productId";
		String sText_TotalRepayment = "//span[@data-tid='total-repayment']";
		String sText_TotalInterest = "//span[@data-tid='total-interest']";
		String sCalculateBtn = "//button[@id='submit']";
				
		//homeloan repay calculation link in home page
		webdriverUtil.getWebElement(driver, sHomeLoanRepayLink).click();
		//explicit wait after homeloan link 				
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sInput_LoanAmount)));
		
		webdriverUtil.getWebElement(driver, sInput_LoanAmount).clear();
		Thread.sleep(500);
		webdriverUtil.getWebElement(driver, sInput_LoanAmount).sendKeys(Keys.BACK_SPACE);		
		webdriverUtil.getWebElement(driver, sInput_LoanAmount).sendKeys(sLoanAmmount);
		
		Select select_RepayType = new Select (webdriverUtil.getWebElement(driver, sDropDown_RepayType));
		select_RepayType.selectByIndex(1);
		
		Select select_InterestType = new Select (webdriverUtil.getWebElement(driver, sDropDown_InterestType));
		select_InterestType.selectByIndex(0);

		//Thread.sleep(500);
		//change values of filed repayment type and capture
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(sCalculateBtn)));				
		webdriverUtil.getWebElement(driver, sCalculateBtn).click();
		
		String sRepayment = webdriverUtil.getWebElement(driver, sText_TotalRepayment).getText();
		String sInterest = webdriverUtil.getWebElement(driver, sText_TotalInterest).getText();
		
		logger.info("Total Repayemnt: "+sRepayment );
		logger.info("Total Interest: "+ sInterest );
		
		System.out.println("Total Repayemnt: "+sRepayment);
		System.out.println("Total Interest: "+ sInterest);
		
		Thread.sleep(500);
		
		List<WebElement> lRepayType = select_RepayType.getOptions();
		List<WebElement> lInterestType = select_InterestType.getOptions();
		
		for(int i=1; i<lRepayType.size(); i++) {
			select_RepayType.selectByIndex(i);
			System.out.println("----------------------------------------");
			System.out.println("Repayment Type : "+  select_RepayType.getFirstSelectedOption().getText());
			Thread.sleep(250);
			for(int j=1; j<lInterestType.size(); j++) {
				select_InterestType.selectByIndex(j);
				Thread.sleep(250);
				
				sRepayment = webdriverUtil.getWebElement(driver, sText_TotalRepayment).getText();
				sInterest = webdriverUtil.getWebElement(driver, sText_TotalInterest).getText();
				
				System.out.println("Total Repayemnt: "+sRepayment);
				System.out.println("Total Interest: "+ sInterest);
				
			}
		}
		logger.info("Done!");		
		return "done";
		
	}
}
