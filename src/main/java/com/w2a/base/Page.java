package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.ExtentManager;

import com.w2a.utilities.Utilities;

public class Page
{
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static ExcelReader excel =  new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\com\\w2a\\excel\\testdata2.xlsx");
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public ExtentReports rep =  ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;
	public static TopMenu menu;


	
	public Page()
	{
		if(driver==null)			//made driver as static , so it will create object only if diver object is not holding any value (driver holds value when class is instanciated)  before
		{							//else it will create each object for each class during instanciating. and multiple window will open with main page
	
			try {
				fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\com\\w2a\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				config.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\com\\w2a\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				OR.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}


			//code for jenkins browser choice
			
			if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty())
			{
				browser= System.getenv("browser");}
			else
			{
				browser = config.getProperty("browser");
			}
			config.setProperty("browser",browser);   
			//till here




			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\com\\w2a\\executables\\chromedriver.exe");

			Map <String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);

			ChromeOptions options = new ChromeOptions();

			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-infobars");

			driver = new ChromeDriver(options);

			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to : "+ config.getProperty("testsiteurl"));
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			wait = new WebDriverWait(driver,5);
			menu = new TopMenu(driver);

		}
	}

	
	
	public static void quit()
	{
		driver.quit();
	}



	public static void verifyEquals(String expected,String actual) throws IOException
	{
		try {
			Assert.assertEquals(actual,expected);
		}catch(Throwable t)
		{
			Utilities.captureScreenshot();
			// For reportNG
			Reporter.log("<br>"+"Verification failure : "+t.getMessage()+"<br>");
			Reporter.log("<a target=\"_blank\" href="+Utilities.screenshotName+"><img src="+Utilities.screenshotName+" height=200 width=200></img></a>");

			// For ExtentReport
			test.log(LogStatus.FAIL, "Verification failed with exception : "+t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenshotName));
		}
	}

	
	
	//Common Keywords
	public static void click(String locator)
	{
		if(locator.endsWith("_CSS"))
		{driver.findElement(By.cssSelector(OR.getProperty(locator))).click();}

		else if(locator.endsWith("_XPATH"))
		{driver.findElement(By.xpath(OR.getProperty(locator))).click();}

		else if(locator.endsWith("_ID"))
		{driver.findElement(By.id(OR.getProperty(locator))).click();}

		
		log.debug("Clicking on an Element : "+locator );
		test.log(LogStatus.INFO,"Clicking on : " +locator);
	}



	public static void type(String locator, String value)
	{
		if(locator.endsWith("_CSS"))
		{driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);}

		else if(locator.endsWith("_XPATH"))
		{driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);}

		else if(locator.endsWith("_ID"))
		{driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);}

		log.debug("typing in an Element : "+locator+ " Entered value as : "+value);
		test.log(LogStatus.INFO, "Typing in : "+locator+" entered values : "+value);
	}



	static WebElement dropdown;
	
	public static void select(String locator, String value)
	{

		if(locator.endsWith("_CSS"))
		{dropdown=driver.findElement(By.cssSelector(OR.getProperty(locator)));}

		else if(locator.endsWith("_XPATH"))
		{dropdown=driver.findElement(By.xpath(OR.getProperty(locator)));}

		else if(locator.endsWith("_ID"))
		{dropdown=driver.findElement(By.id(OR.getProperty(locator)));}

		Select select =new Select(dropdown);
		select.selectByVisibleText(value);
		
		log.debug("Selecting from an Element : "+locator+" value as"+value);
		test.log(LogStatus.INFO,"Selecting from dropdown : "+ locator +"value as"+ value);

	}


	public boolean isElementPresent(By by)
	{
		try {
			driver.findElement(by);
			return true;}
		catch (NoSuchElementException e)
			{return false;}
	}



}
