package ExecutionRepo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import DriverRepo.CreateDriver;
import ExecutionRepo.StepDefinition.CommonSteps;
import ExecutionRepo.StepDefinition.GlobalData;
import ExecutionRepo.StepDefinition.LoginSteps;
import ReporterRepo.Reporter;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;

public class Hooks {

	public static WebDriver driver;
	public static Scenario scenario;
	GlobalData global = new GlobalData();
	
	@BeforeStep
	public void beforeStep() {
		try {
			//WebElement elm1 = driver.findElement(By.xpath("//h1[@aria-label='Unsaved changes']"));
			//driver.findElement(By.xpath("//button[@id='cancelButton']")).click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//Reporter.report("INFO", elm1.getText() + " popup handled");
		} catch (Exception ne) {

		}
		try {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//WebElement elm2 = driver.findElement(By.xpath("//h1[contains(@aria-label,'Error')]"));
			//driver.findElement(By.xpath("//button[@id='cancelButton']")).click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//Reporter.report("INFO", elm2.getText() + " popup handled");
		} catch (Exception ne) {

		}
	}

	@Before
	public void before(Scenario scenario) {
		this.scenario = scenario;
		Reporter.scenario = scenario;
		String sFeature = global.getsCurrentFeature();
		if (sFeature != null) {
			String sActFeature = getFeatureFileName(scenario);
			if (!sFeature.contentEquals(sActFeature)) {
				global.setsCurrentFeature(getFeatureFileName(scenario));
				try {					
					LoginSteps obj=new LoginSteps();
					//obj.user_logout_from_MAH();
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		} else {
			global.setsCurrentFeature(getFeatureFileName(scenario));
		}
	}

	@Before
	public static void initDriver() throws MalformedURLException, Exception {
		driver = CreateDriver.getInstance().driver;
	}

	@Before
	public void handlePopup() throws Throwable {
		try {
			Thread.sleep(2000);
			driver.findElement(By.xpath("//button[@id='cancelButton']")).click();
			Thread.sleep(2000);
		} catch (Exception ne) {

		}
	}

	public void storeScreenshot(Scenario scenario) throws Exception {
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			LocalDateTime now = LocalDateTime.now();
			String sName = "";
			Collection<String> arr = scenario.getSourceTagNames();
			for (String s : arr) {
				sName = sName + "_" + s;
			}
			sName = sName.replace("@", "").substring(1);
			String sFeatureName = getFeatureFileName(scenario);
			String sFolderName = now.getYear() + "_" + now.getMonthValue() + "_" + now.getDayOfMonth() + "/"
					+ sFeatureName + "/" + sName;
			String sFileName = "Screen_" + now.getYear() + "_" + now.getMonthValue() + "_" + now.getDayOfMonth() + "_"
					+ now.getHour() + "_" + now.getMinute() + "_" + now.getSecond();
			FileUtils.copyFile(scrFile,
					new File("target/ScreenshotsSuccess/" + sFolderName + "/" + sFileName + ".png"));
			Reporter.report("INFO", "Screenshot: target/ScreenshotsSuccess/" + sFolderName + "/" + sFileName + ".png");
		} catch (WebDriverException somePlatformsDontSupportScreenshots) {
			System.err.println(somePlatformsDontSupportScreenshots.getMessage());
		}
	}

	@AfterStep
	public void embedScreenshot(Scenario scenario) throws Exception {
		if (scenario.isFailed()) {
			try {
				byte[] scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.embed(scrFile, "image/png");
			} catch (WebDriverException somePlatformsDontSupportScreenshots) {
				System.err.println(somePlatformsDontSupportScreenshots.getMessage());
			}
		}
	}

	@AfterStep
	public void passScreenshot(Scenario scenario) throws Throwable {
		Properties prop = new Properties();
		FileReader reader = new FileReader("src/test/resources/runConfig.properties");
		prop.load(reader);
		String sPassSc = prop.getProperty("Need_Pass_Screenshot");
		if (sPassSc.toUpperCase().contentEquals("TRUE")) {
			storeScreenshot(scenario);
		}
	}

	private String getFeatureFileName(Scenario scenario) {
		String featureName = "";
		String[] arr = scenario.getId().split(":")[1].split("/");
		featureName = scenario.getId().split(":")[1].split("/")[arr.length - 1].replace(".feature", "");
		return featureName;
	}
}
