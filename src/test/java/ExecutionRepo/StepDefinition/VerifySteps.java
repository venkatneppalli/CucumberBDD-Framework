package ExecutionRepo.StepDefinition;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import ExecutionRepo.Hooks;
import ObjectRepo.readOR;
import ReporterRepo.Reporter;
import cucumber.api.java.en.*;
import io.cucumber.datatable.DataTable;

public class VerifySteps extends GlobalData {
	static WebDriver driver;
	readOR objOR;
	static WaitStep wait;
	static CommonSteps commonObj;
	static ClickSteps clickObj;
	static FillSteps fillObj;

	public VerifySteps() {
		driver = Hooks.driver;
		objOR = new readOR();
		wait = new WaitStep();
		commonObj = new CommonSteps();
		clickObj = new ClickSteps();
		fillObj = new FillSteps();
		//commonObj.HandleBusinessProcessError();
	}

	
	@Then("^Print the dropdown options displayed$")
	public void VerifyDropDownItems() throws Exception {
		
		for(int j=1;j<=4;j++) {
		
		Thread.sleep(5000);
		WebElement obj = driver.findElement(By.xpath("//li[@class='dropdown']["+j+"]/ul"));
		
		
		List<WebElement> list = obj.findElements(By.tagName("li"));
		
		
		//System.out.println("size:= "+list.size());
		
		for(int i=0;i<list.size();i++) {
			
			//System.out.println("Print dropdown values:= "+list.get(i).getText());
			if(list.get(i).getText().equals("About")) {
				System.out.println("Print About");
			}else if(list.get(i).getText().equals("Pricing & Package")) {
				System.out.println("Print Pricing & Package");
			}
			
		}
		
		}
	}
	
	@Then("^I verify current url after clicking about$")
	public void verify_URL() throws InterruptedException {
		Thread.sleep(5000);
		System.out.println("Current URL after clicking on About:= "+driver.getCurrentUrl());
		
	}
	
	@Then("^I see current url as signup$")
	public void Verify_CurrentURL() {
	
		String currentURL=driver.getCurrentUrl();
		if(currentURL.equals("https://www.fourmodules.com/signup")) {
			System.out.println("Current URL:= "+currentURL);
		}else {
			System.out.println("Current URL:= "+currentURL);
		}
	}
	
	
	
	@Then("^I see a button \"([^\"]*)\" on \"([^\"]*)\" page$")
	public void verifyButton(String sPageName, String sControlName) throws Exception {
		
		
		Thread.sleep(10000);
		
		WebElement obj = driver.findElement(By.xpath("//span[contains(text(),'Any Questions?')]"));
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", obj);
		
		driver.findElement(By.xpath("//html")).sendKeys(Keys.PAGE_UP);
		
		if(obj.isEnabled()) {
			System.out.println("AnyQuestions Button is enabled.............");
		}else {
			System.out.println("AnyQuestions Button is disabled............");
		}
		
		
	}
	
	
	
	//@Then("^I see current url as signup$")
	public void i_see_current_url_as(String sControlName, String sPageName) {
		System.out.println(driver.getCurrentUrl());
	}
	
	
	
	
	@When("^I see Navbar that includes the \"([^\"]*)\" on \"([^\"]*)\" page$")
	public void see_navbar_spicejet(String sControlName, String sPageName) throws Exception {
		
		
		WebElement obj = objOR.getObject(sPageName, sControlName);
		
		List<WebElement> list = obj.findElements(By.tagName("li"));
		
		for(int i=0;i<list.size();i++) {
			System.out.println("Print SpiceJet Menu Items:= "+list.get(i).getText());
		}
		
		
		
	}
	
	
	@Then("^I see \"([^\"]*)\" is selected by default on \"([^\"]*)\" page$")
	public void verify_flights_selected(String sControlName, String sPageName) throws Exception {
		
		WebElement obj = objOR.getObject(sPageName, sControlName);
		
		//List<WebElement> list = obj.findElements(By.tagName("li"));
		
		WebElement obj2 = obj.findElement(By.tagName("li"));
		
		
		List<WebElement> list2 = obj2.findElements(By.tagName("a"));
		
		for(int i=0;i<list2.size();i++) {
			
			if(list2.get(i).getText().equals("Flights") && list2.get(i).getAttribute("class").equals("selected")) {
				
				
					System.out.println("Print SpiceJet Menu Flights Items selected:= "+list2.get(i).getAttribute("class"));
					System.out.println("Print SpiceJet Menu Flights Items:= "+list2.get(i).getText());
					System.out.println("Flights in menu is selected by default........");
					break;
				
			}
		}
	}
	
	
	@When("^I select 1Adult_textbox and \"([^\"]*)\" on \"([^\"]*)\" page$")
	public void selectInfants(String sControlName, String sPageName) throws Exception {
		Thread.sleep(5000);
		
		WebElement objs= driver.findElement(By.xpath("//div[@id='divpaxinfo']"));
		Thread.sleep(10000);
		
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(objs));
		
		
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", objs);
		Thread.sleep(5000);
		
		
		Thread.sleep(5000);
		
		Select select1 = new Select(driver.findElement(By.xpath("//select[@id='ctl00_mainContent_ddl_Infant']")));
		Thread.sleep(5000);
		select1.selectByIndex(3);
	
	}
	
	
	
	@Then("^I see a pop-up window with a message$")
	public void popup_window_message() throws InterruptedException, AWTException {
		
		try {
			//Thread.sleep(5000);
			
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.alertIsPresent());
			
			Alert alert = driver.switchTo().alert();
			System.out.println("Alert message:= "+alert.getText());
			//alert.accept();
			//System.out.println("Clicked on alert message box");	
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	@Then("^Record the message and print it to the Console$")
	public void record_popup_window_message() throws InterruptedException {
		Thread.sleep(5000);
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.alertIsPresent());
		
		Alert alert = driver.switchTo().alert();
		System.out.println("Alert message:= "+alert.getText());
		alert.accept();
		System.out.println("Clicked on alert message box");
	}
	
	
	@Then("^Redirect me to book_url$")
	public void redirect_me_to() {
		String currentURL=driver.getCurrentUrl();
		if(currentURL.equals("https://book.spicejet.com/Select.aspx")) {
			System.out.println("Current URL:= "+currentURL);
		}else {
			System.out.println("Current URL:= "+currentURL);
		}
	}

	@Then("Verify the user has advanced to the next page by printing to Console the URL")
	public void verify_the_user_has_advanced_to_the_next_page_by_printing_to_Console_the_URL() {
		String currentURL=driver.getCurrentUrl();
		
	}

	@Then("^Clicking on \"([^\"]*)\" CTA button will redirect me to url on \"([^\"]*)\" page$")
	public void verify_redirect_url(String sControlName, String sPageName) {
	
		//if(driver.getCurrentUrl().equals("https://book.spicejet.com/Select.aspx")){
			System.out.println("current url redirected successfully:= "+driver.getCurrentUrl());
		//}
	}
	
	
	
	
	@Then("^I see the page interstitial$")
	public void interstital_page() {
		System.out.println("Page Interstitial:= "+driver.getCurrentUrl());
	}
	
	
	@Then("^If no fares are available, record the message and print to the Console$") 
	public void getMessage_no_fair() {
		
		try {
			WebElement element =driver.findElement(By.xpath("//div[contains(text(),'Sorry, no fares aSorry, no fares available for this date. Please select another date and try again.vailable for this date. Please se')]"));
			
			if(element.isDisplayed()) {
				System.out.println("Sorry, no fares available for this date. Please se");
			}	
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	 
	@When("^I see the \"([^\"]*)\" CTA button on \"([^\"]*)\" page$")
	public void verify_go_icon(String sControlName, String sPageName) throws Exception {
		Thread.sleep(5000);
		WebElement obj = objOR.getObject(sPageName, sControlName);
		
		if(obj.isEnabled()) {
			System.out.println("Go_CTA_button is enabled");
		}else {
			System.out.println("Go_CTA_button is disabled");
		}
		
		
	}
	
	
	
	
	/*
	
	
	
	// Verify element displayed in a page
	public void VerifyElementDisplayed(String sPageName, String sControlName) throws Throwable {
		String sXpath = objOR.getXpath(sPageName, sControlName);
		wait.waitForElementDisplayed(sXpath);
		WebElement obj = objOR.getObject(sPageName, sControlName);
	}

	// Verify page title
	public void VerifyPageTitle(String sPageName, String sControlName) throws Throwable {
		String sXpath = objOR.getXpath(sPageName, sControlName);
		wait.waitForElementDisplayed(sXpath);
		WebElement obj = objOR.getObject(sPageName, sControlName);
	}

	// Verify element not displayed in a page
	public void VerifyElementNotDisplayed(String sPageName, String sControlName) throws Throwable {
		String sXpath = objOR.getXpath(sPageName, sControlName);
		wait.waitForElementDisappear(sXpath);
	}

	// Verify element enabled in a page
	public void VerifyElementEnabled(String sPageName, String sControlName) throws Throwable {
		String sXpath = objOR.getXpath(sPageName, sControlName);
		wait.waitForElementDisplayed(sXpath, 5);
		WebElement obj = objOR.getObject(sPageName, sControlName);
		if (!obj.isEnabled()) {
			Reporter.report("FAIL", sControlName + " is not enabled in the " + sPageName + " page");
		}
	}

	// Verify element displayed inside a frame
	// Pass the frame id as 'sFrame'
	public void VerifyElementDisplayed(String sPageName, String sFrame, String sControlName) throws Throwable {
		String sXpath = objOR.getXpath(sPageName, sFrame, sControlName);
		wait.waitForElementDisplayed("//iframe[@id='" + sFrame + "' or @title='" + sFrame + "']");
		commonObj.switchToFrame(sFrame);
		wait.waitForElementDisplayed(sXpath);
		WebElement obj = objOR.getObjectFromFrame(sPageName, sFrame, sControlName);
		driver.switchTo().parentFrame();
	}

	// Verify element not displayed in a frame
	public void VerifyElementNotDisplayed(String sPageName, String sFrame, String sControlName) throws Throwable {
		String sXpath = objOR.getXpath(sPageName, sControlName);
		wait.waitForElementDisplayed("//iframe[@id='" + sFrame + "' or @title='" + sFrame + "']");
		commonObj.switchToFrame(sFrame);
		wait.waitForElementDisappear(sXpath);
		driver.switchTo().parentFrame();
	}

	@Then("^verify below controls in \"([^\"]*)\" frame of \"([^\"]*)\" page$")
	public void verify_below_controls_in_frame(String sFrame, String sPage, DataTable dt) throws Throwable {
		boolean bFail = false;
		List<List<String>> expValues = dt.asLists();
		for (int i = 0; i < expValues.size(); i++) {
			try {
				String sKey = expValues.get(i).get(0);
				String sVal = expValues.get(i).get(1);
				switch (sVal.toUpperCase()) {
				case "DISPLAYED":
					VerifyElementDisplayed(sPage, sFrame, sKey);
					break;
				case "NOT DISPLAYED":
					VerifyElementNotDisplayed(sPage, sFrame, sKey);
					break;
				default:
					bFail = true;
					Reporter.report("INFO", "The control status " + sVal + " is not valid for " + sKey);
				}
			} catch (Exception e) {
				bFail = true;
				Reporter.report("INFO", e.getMessage());
			}
		}
		if (bFail) {
			Reporter.report("FAIL", "");
		}
	}

	// Verify a control is displayed with a specific value
	@Then("^verify \"([^\"]*)\" displaying with \"([^\"]*)\" value in \"([^\"]*)\" page$")
	public void verify_displaying_with_value_in_page(String sControlName, String sValue, String sPage)
			throws Throwable {
		if (checkControlValue(sPage, sControlName, sValue)) {
			Reporter.report("INFO", "Failed to verify the control " + sControlName + " is displaying with " + sValue);
			Reporter.report("FAIL", "");
		} else {
			Reporter.report("PASS", "The control " + sControlName + " is displaying with " + sValue);
		}
	}

	public boolean checkControlValue(String sPage, String sControlName, String sValue) throws Throwable {
		boolean bFail = false;
		WebElement oControl = null;
		String sActVal = "";
		if (sValue.contains("%Account%")) {
			sValue = sValue.replace("%Account%", getAccountName());
		}
		if (sControlName.contains("_textbox")) {
			wait.waitForElementDisplayed(objOR.getXpath(sPage, sControlName));
			oControl = objOR.getObject(sPage, sControlName);
			sActVal = oControl.getAttribute("value");
		} else if (sControlName.contains("_dropdown")) {
			wait.waitForElementDisplayed(objOR.getXpath(sPage, sControlName));
			oControl = objOR.getObject(sPage, sControlName);
			sActVal = oControl.getAttribute("title");
		} else if (sControlName.contains("_inputsearch")) {
			// Need to add
			String sXpath = objOR.getXpath(sPage, sControlName);
			try {
				oControl = driver.findElement(By.xpath(sXpath));
				sActVal = oControl.getAttribute("value");
			} catch (NoSuchElementException ne) {
				String sLabel = sXpath.substring(sXpath.indexOf("input[@aria-label='") + 19).replace("']", "");
				wait.waitForElementDisplayed("//div[contains(text(),'" + sLabel
						+ "')]/following-sibling::ul/li/descendant::div/div[contains(@data-id,'LookupResultsDropdown')]");
				oControl = driver.findElement(By.xpath("//div[contains(text(),'" + sLabel
						+ "')]/following-sibling::ul/li/descendant::div/div[contains(@data-id,'LookupResultsDropdown')]"));
				sActVal = oControl.getAttribute("title");
			}
		} else if ((sControlName.contains("_errormessage")) | (sControlName.contains("_header"))
				| (sControlName.contains("_button")) | (sControlName.contains("_label"))
				| (sControlName.contains("_link"))) {
			wait.waitForElementDisplayed(objOR.getXpath(sPage, sControlName));
			oControl = objOR.getObject(sPage, sControlName);
			sActVal = oControl.getText();
		} else if (sControlName.contains("_containercheckbox") | sControlName.contains("_checkbox")) {
			String sXpath = objOR.getXpath(sPage, sControlName);
			wait.waitForElementDisplayed(sXpath);
			sActVal = driver.findElement(By.xpath(sXpath + "/div[@aria-checked='true']")).getAttribute("title");
		} else {
			bFail = true;
			Reporter.report("INFO", "Invalid control type defined for " + sControlName);
		}
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", oControl);
		if (!sActVal.contentEquals(sValue)) {
			bFail = true;
			Reporter.report("INFO", "The control " + sControlName + " is displaying with " + sActVal);
		}
		return bFail;
	}

	// To check the state of a control
	@Then("^verify \"([^\"]*)\" (displayed|enabled|not displayed) in \"([^\"]*)\" page$")
	public void verify_control_state_value(String sControlName, String sControlState, String sPage) throws Throwable {
		boolean bFail = false;
		switch (sControlState.toUpperCase()) {
		case "DISPLAYED":
			VerifyElementDisplayed(sPage, sControlName);
			break;
		case "ENABLED":
			VerifyElementEnabled(sPage, sControlName);
			break;
		case "NOT DISPLAYED":
			VerifyElementNotDisplayed(sPage, sControlName);
			break;
		default:
			Reporter.report("FAIL", "Control state " + sControlState + " is not valid for this method");
		}
	}

		private boolean checkDisableAttribute(WebElement elm) {
		boolean bFail = false;
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elm);
			if (elm.getAttribute("disabled") == null) {
				bFail = true;
				Reporter.report("INFO", elm.getAttribute("aria-label") + " is not disabled");
			}
		} catch (Exception e) {
			bFail = true;
			Reporter.report("INFO", elm.getAttribute("aria-label") + " is not disabled");
		}
		return bFail;
	}

	private boolean checkAriaReadonlyAttribute(WebElement elm) {
		boolean bFail = false;
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elm);
			if (!elm.getAttribute("aria-readonly").contentEquals("true")) {
				bFail = true;
				Reporter.report("INFO", elm.getAttribute("aria-label") + " is not disabled");
			}
		} catch (Exception e) {
			bFail = true;
			Reporter.report("INFO", e.getMessage());
		}
		return bFail;
	}

	private boolean checkTextContainReadonly(WebElement elm) {
		boolean bFail = false;
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elm);
			if (!elm.getText().contains("Readonly")) {
				bFail = true;
				Reporter.report("INFO", elm.getAttribute("aria-label") + " is not disabled");
			}
		} catch (Exception e) {
			bFail = true;
			Reporter.report("INFO", elm.getAttribute("aria-label") + " is not disabled");
		}
		return bFail;
	}

	@Then("^verify (mandatory|non mandatory) controls with \"([^\"]*)\"$")
	public void verify_mandatory_controls(String isMand, String sKey) throws Throwable {
		boolean bFail = false;
		List<String> arrExpControl = new ArrayList<String>();
		List<String> arrActControl = new ArrayList<String>();
		List<WebElement> arrActControlElm = new ArrayList<WebElement>();
		if (sKey.contentEquals("%MandatoryTextbox%")) {
			arrExpControl = getArrMandTextbox();
			arrActControlElm = driver.findElements(By.xpath("//input[@aria-required='true']"));
		}
		if (sKey.contentEquals("%MandatoryDropdown%")) {
			arrExpControl = getArrMandDropdown();
			arrActControlElm = driver.findElements(By.xpath("//select[@aria-required='true']"));
		}
		if (sKey.contentEquals("%NonMandatoryTextbox%")) {
			arrExpControl = getArrNonMandTextbox();
			arrActControlElm = driver.findElements(By.xpath("//input[not(@aria-required='true')]"));
		}
		if (sKey.contentEquals("%NonMandatoryDropdown%")) {
			arrExpControl = getArrNonMandDrodpwn();
			arrActControlElm = driver.findElements(By.xpath("//select[not(@aria-required='true')]"));
		}
		for (WebElement elm : arrActControlElm) {
			arrActControl.add(elm.getAttribute("aria-label"));
		}
		for (String elmName : arrExpControl) {
			if (!arrActControl.contains(elmName)) {
				bFail = true;
				Reporter.report("INFO", elmName + " is not found as mandatory in the page");
			}
		}
		if (bFail) {
			Reporter.report("FAIL", "");
		}
	}

	@Then("^verify \"([^\"]*)\" inputsearch contains below values in \"([^\"]*)\" page$")
	public void verify_inputsearch_contains_below_values_in_page(String sControl, String sPage, DataTable dataTable)
			throws Throwable {
		boolean bFail = false;
		wait.waitForElementDisplayed(objOR.getXpath(sPage, sControl));
		WebElement obj = driver.findElement(By.xpath(objOR.getXpath(sPage, sControl)));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", obj);
		clickObj.ElementClick(sPage, sControl);
		String sObjXpath = objOR.getXpath(sPage, sControl);
		wait.waitForElementDisplayed(sObjXpath + "/following-sibling::button[@aria-label='Search all records']");
		driver.findElement(By.xpath(sObjXpath + "/following-sibling::button[@aria-label='Search all records']"))
				.click();
		wait.waitForElementDisplayed("//ul[@aria-label='Lookup Search Results']");
		List<WebElement> arrOptions = driver.findElements(By.xpath("//ul[@aria-label='Lookup Search Results']/li"));
		List<List<String>> expValues = dataTable.asLists();
		for (int i = 0; i < expValues.size(); i++) {
			boolean bFound = false;
			String sExpVal = expValues.get(i).get(0);
			for (int j = 0; j < arrOptions.size(); j++) {
				String sActVal = arrOptions.get(j).getAttribute("aria-label");
				if (sExpVal.contentEquals(sActVal)) {
					bFound = true;
					break;
				}
			}
			if (!bFound) {
				bFail = true;
				Reporter.report("INFO", "Failed to find " + sExpVal + " in the " + sControl + " control");
			}
		}
		if (bFail) {
			Reporter.report("FAIL", "");
		}
	}

	@Then("^verify business process error popup is displayed with below error message$")
	public void verify_business_process_error(DataTable dataTable) throws Throwable {
		boolean bFail = false;
		String sError = dataTable.asLists().get(0).get(0);
		try {
			wait.waitForElementDisplayed(
					"//section[contains(@id,'DialogContainer')]/descendant::button[@data-id='ignore_save']", 10);
			driver.findElement(
					By.xpath("//section[contains(@id,'DialogContainer')]/descendant::button[@data-id='ignore_save']"))
					.click();
			wait.waitForElementDisappear("//span[text()='Saving...']");
			Thread.sleep(1000);
		} catch (TimeoutException te) {

		}
		try {
			wait.waitForElementDisplayed("//h2[@id='subtitle']", 10);
		} catch (TimeoutException te) {
			bFail = true;
			Reporter.report("INFO", "Business process error popup is not displayed");
		}
		if (!bFail) {
			WebElement oErrorMessage = driver.findElement(By.xpath("//h2[@id='subtitle']"));
			if (!oErrorMessage.getText().contains(sError)) {
				Reporter.report("INFO", "Error message is not displaying as expected");
				Reporter.report("INFO", "Expected: " + sError);
				Reporter.report("INFO", "Acual: " + oErrorMessage.getText());
				Reporter.report("FAIL", "");
			}
		} else {
			Reporter.report("FAIL", "");
		}
	}

	@Then("^verify \"([^\"]*)\" listbox in \"([^\"]*)\" frame of \"([^\"]*)\" page contains below values$")
	public void verify_listbox_contains_below_values_in_frame(String sListbox, String sFrame, String sPage,
			DataTable dt) throws Throwable {
		List<List<String>> expValues = dt.asLists();
		wait.waitForElementDisplayed("//iframe[@id='" + sFrame + "' or @title='" + sFrame + "']");
		commonObj.switchToFrame(sFrame);
		wait.waitForElementDisplayed(objOR.getXpath(sPage, sFrame, sListbox));
		WebElement oControl = objOR.getObjectFromFrame(sPage, sFrame, sListbox);
		boolean bFail = false;
		List<WebElement> arrOptions = driver
				.findElements(By.xpath(objOR.getXpath(sPage, sFrame, sListbox) + "/descendant::tr/td"));
		List<String> actValues = new ArrayList<String>();
		for (WebElement option : arrOptions) {
			actValues.add(option.getAttribute("title"));
		}
		for (int i = 0; i < expValues.size(); i++) {
			String sExpVal = expValues.get(i).get(0);
			if (!actValues.contains(sExpVal)) {
				bFail = true;
				Reporter.report("INFO", sExpVal + " is not displayed in the listbox");
			}
		}
		driver.switchTo().parentFrame();
		if (bFail) {
			Reporter.report("INFO", "Expected: " + expValues + ", Actual values: " + actValues);
			Reporter.report("FAIL", "");
		} else {
			Reporter.report("PASS", "Successfully verified " + sListbox + " values as " + expValues);
		}
	}
	
	*/
}
