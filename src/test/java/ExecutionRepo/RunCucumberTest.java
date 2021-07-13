package ExecutionRepo;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import DriverRepo.CreateDriver;
import ReporterRepo.EmailAttachmentSendAll;
import ReporterRepo.ReportManager;
import ReporterRepo.SendEmilAttachment;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(tags = { "~@skip-test", "@verifySpiceJetBook2Adults" },
		// glue="src/test/java/ExecutionRepo/StepDefinition/TempSteps.java",
		monochrome = true,
		plugin = { "pretty", "json:target/result/cucumber.json",
				"html:target/result/cucumber-reports" },
		features = "src/test/resources/features/SmokeTests",dryRun=false
//		features = "classpath:features/Regression"
)

public class RunCucumberTest {
	@AfterClass
	public static void testMethod() throws Throwable {
		System.out.println("after class");
		ReportManager.reportCreate();
		//EmailAttachmentSendAll.senEmail();
		CreateDriver.killInstance();
		Hooks.driver.quit();
		Hooks.driver = null;
	}
}