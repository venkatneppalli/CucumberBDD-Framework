package DriverRepo;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

public class CreateDriver {
	public static WebDriver driver;
	private static String browser;
	private static String os;
	private static Properties prop = new Properties();
	//
	// private static InputStream in = WebDriverFactory.class
	// .getResourceAsStream("src/test/resources/runConfig.properties");
	private static CreateDriver instance = null;

	/******** Initialize Driver Configuration when the class is instanced 
	 * @throws MalformedURLException ********/
	private CreateDriver() throws MalformedURLException {
		CreateDriver.initConfig();
	}

	/**
	 * Singleton pattern
	 * 
	 * @return a single instance
	 * @throws MalformedURLException 
	 */
	public static CreateDriver getInstance() throws MalformedURLException {
		if (instance == null) {
			instance = new CreateDriver();
		}
		return instance;
	}

	public static void killInstance() {
		instance = null;
	}

	/**
	 * Get the Browser from the POM
	 * @throws MalformedURLException 
	 */
	public static WebDriver initConfig() throws MalformedURLException {

		try {
			FileReader reader = new FileReader("src/test/resources/runConfig.properties");
			prop.load(reader);
			browser = prop.getProperty("browser");
			os = prop.getProperty("os");
			// logLevel = prop.getProperty("logLevel");

		} catch (IOException e) {

		}

		/****** Load the driver *******/
		// driver=WebDriverFactory.getInstance().driver;
		driver = WebDriverFactory.createNewWebDriver(browser, os);

		/******** Clean Cookies, maxinize and declare Timeout on the Driver *******/
		if (!browser.equalsIgnoreCase("edge")) {
			driver.manage().deleteAllCookies();
		}
		driver.manage().window().maximize();
		return driver;
	}

}
