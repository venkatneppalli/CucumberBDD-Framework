package ExecutionRepo.StepDefinition;

import org.openqa.selenium.WebDriver;

import ExecutionRepo.Hooks;
import ObjectRepo.readOR;
import ReporterRepo.Reporter;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TempSteps extends GlobalData {

	static WebDriver driver;
	readOR objOR;

	public TempSteps() {
		driver = Hooks.driver;
		objOR = new readOR();
	}

	@When("^first step$")
	public void first_step() throws Throwable {
		Reporter.report("PASS", "First step");
		driver.get("https://www.google.com");
	}

	@Then("^second step$")
	public void second_step() throws Throwable {
		Reporter.report("PASS", "Second step");
	}

	@When("first step execute")
	public void first_step_execute() {
		Reporter.report("PASS", "First step");
		driver.get("https://www.google.com");
	}

	@Then("second step execute")
	public void second_step_execute() {
		Reporter.report("PASS", "Second step");
	}

	@When("^third step$")
	public void third_step() throws Throwable {
		Reporter.report("PASS", "Third step");
	}

	@Then("^fourth step$")
	public void forth_step() throws Throwable {
		Reporter.report("FAIL", "Fourth step");
	}

	@When("^i execute first step$")
	public void i_execute_first_step() throws Throwable {
		System.out.println("My first step");
	}

	@Then("^set account number$")
	public void i_execute_second_step() throws Throwable {
		setOpportunityNumber("OPP-200619070413V3F");
	}
}
