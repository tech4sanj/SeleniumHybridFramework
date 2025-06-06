package com.mystore.testcases;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

import com.mystore.utilities.ReadConfig;

public class BaseClass {

	ReadConfig readConfig;
	String url;
	String browser;

	// Declare SoftAssert instance
    protected SoftAssert softAssert;
	public static WebDriver driver;
	public static Logger logger;

	// Constructor to initialize ReadConfig and handle potential exceptions
	public BaseClass() {
		try {
			readConfig = new ReadConfig();
			url = readConfig.getBaseUrl();
			browser = readConfig.getBrowser();
		} catch (IOException e) {
			System.err.println("Error initializing configuration: " + e.getMessage());
			throw new RuntimeException("Failed to initialize configuration.", e);  // Rethrow if initialization fails
		}
	}

	@BeforeClass
	public void setup() {
		if (browser == null || browser.isEmpty()) {
			throw new RuntimeException("Browser is not specified in the config.");
		}

		switch (browser.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			break;

		case "msedge":
			driver = new EdgeDriver();
			break;

		case "firefox":
			driver = new FirefoxDriver();
			break;

		default:
			throw new RuntimeException("Browser not supported: " + browser);
		}

		// Implicit wait of 10 seconds
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Initialize logger
		logger = LogManager.getLogger("MyStoreV1");

		//Maximize the browser
		driver.manage().window().maximize();
		//Open url
		driver.get(url);
		logger.info("URL opened: " + url);
		
		  // Initialize SoftAssert instance
        softAssert = new SoftAssert();
	}

	 
	@AfterClass
	public void tearDown() {
		// Call softAssert.assertAll() to report all soft assertion failures at the end of the test
        softAssert.assertAll();
		if (driver != null) {
			//driver.close();
			driver.quit();
		}
	}
	
	

	// User method to capture screenshots
	public void captureScreenShot(WebDriver driver, String testName) throws IOException {
		if (driver == null) {
			throw new IllegalArgumentException("WebDriver is not initialized.");
		}

		// Convert WebDriver object to TakesScreenshot interface
		TakesScreenshot screenshot = (TakesScreenshot) driver;

		// Create the screenshot file
		File src = screenshot.getScreenshotAs(OutputType.FILE);

		// Define the destination file path
		File dest = new File(System.getProperty("user.dir") + "//Screenshots//" + testName + ".png");

		// Copy the file to the destination
		FileUtils.copyFile(src, dest);
		
		logger.info("Screenshot taken for test: " + testName);
	}
	 // Scroll to the bottom of the page
    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    // Scroll to the top of the page
    public void scrollUp() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
    }

    // Scroll to a specific WebElement on the page
    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
