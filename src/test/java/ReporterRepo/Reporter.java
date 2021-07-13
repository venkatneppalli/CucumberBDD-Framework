package ReporterRepo;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import ExecutionRepo.Hooks;
import cucumber.api.Scenario;
import junit.framework.Assert;

public class Reporter {
	static WebDriver driver;
	public static Scenario scenario;

	public static void report(String status, String sMessage) {
		driver = Hooks.driver;
		if (status.contentEquals("FAIL")) {
			Assert.fail(sMessage);
		}
		scenario.write(sMessage);
	}

}
