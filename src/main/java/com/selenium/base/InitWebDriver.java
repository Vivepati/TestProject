package com.selenium.base;

import java.io.File;
import java.util.HashMap;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.selenium.util.LoggerUtils;
import com.selenium.util.LookupUtil;;

public class InitWebDriver {

	protected static LookupUtil lookuputil = new LookupUtil();
	protected static WebDriver driver = null;
	protected static WebDriver drivertemp = null;
	// protected static FirefoxProfile profile = null;
	protected static final String sBROWSER_TYPE = lookuputil.getConfig("BrowserType");	
	protected static String currDir = new File(System.getProperty("user.dir")).getAbsolutePath();
	
	protected static Logger logger = null;
	protected static Logger clogger = null;
	private String _methodName = null;
	private static String _className = null;
	
	@Rule
	public TestName testName = new TestName();
	
	protected static synchronized WebDriver initDriver() throws Exception
	{
	   if (driver == null)
	    {
	       if (sBROWSER_TYPE.toLowerCase().contains("firefox"))
	       {
	    	   System.setProperty("webdriver.gecko.driver", currDir + File.separator + "geckodriver"); 	
	    	   
	         FirefoxOptions options = setFirefoxOptions();
	         drivertemp = new FirefoxDriver(options);
	       }
	      else if (sBROWSER_TYPE.toLowerCase().contains("chrome"))
	       {
	    	  System.setProperty("webdriver.chrome.driver", currDir + File.separator + "chromedriver.exe");
	         drivertemp = new ChromeDriver(setChromeOptions());
	       }
	       else
	       {
	         throw new Exception("BROWSER TYPE : " + sBROWSER_TYPE + " NOT SUPPORTED CURRENTLY");
	      }
	      driver = drivertemp;
	     }
	     return driver;
	  }
	
	
	@BeforeClass
	public static void setUp() throws Exception {
		System.out.println(">>> In base WebDriver setup.....");
		_className = System.getProperty("epm.runningclass", "DefaultClassLog");
		System.out.println(_className);
		clogger = setUpClassLogger();
		initDriver();
	}
	
	protected static FirefoxOptions setFirefoxOptions() throws Exception {
		FirefoxOptions options = new FirefoxOptions();
		return options;
	}
	
	private static ChromeOptions setChromeOptions() throws Exception {
		ChromeOptions options = new ChromeOptions();
		
		HashMap<String, Object> chromePrefs = new HashMap();
		
		chromePrefs.put("profile.default_content_setting_values.notifications", 2);
		options.setExperimentalOption("prefs", chromePrefs);
		
		options.addArguments(new String[] { "start-maximized" });
		options.addArguments(new String[] { "test-type" });
		options.addArguments(new String[] { "disable-extensions" });
		options.addArguments(new String[] { "disable-infobars" });
		options.addArguments(new String[] { "safebrowsing-disable-download-protection" });
		options.addArguments(new String[] { "ignore-certificate-errors" });
		return options;
	}
	

	//----
	@Before
	public void setUpLogger() throws Exception {
		String basePathForLogs = null;
		
		basePathForLogs = currDir + File.separator + "logs" + File.separator;
		this._methodName = this.testName.getMethodName();
		System.out.println(">>> Method name : " + this._methodName);
		System.out.println(">>> Class name : " + getClass().getName());
		System.out.println();
		logger = LoggerUtils.getLogger(getClass().getName(), this._methodName);
		logger.info("Starting : " + getClass().getName() + "_"
				+ this._methodName);
	}

	public static Logger setUpClassLogger() throws Exception {
		if (clogger == null) {
			String basePathForLogs = null;
			
			basePathForLogs = currDir + File.separator + "logs"	+ File.separator;

			clogger = LoggerUtils.getLogger(_className);
			clogger.info("Starting : " + _className);
		}
		return clogger;
	}

	@AfterClass
	public static void tearDown() throws Exception {
		clogger.info("Ending : " + _className);
		LoggerUtils.releaselogger(clogger);
		clogger = null;
	}

	@After
	public void tearDownLogger() throws Exception {
		logger.info("Ending : " + getClass().getName() + "_" + this._methodName);
		LoggerUtils.releaselogger(logger);
		logger = null;
	}
	
	
}
