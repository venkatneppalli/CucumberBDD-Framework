package ExecutionRepo.StepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import DriverRepo.CreateDriver;
import ExecutionRepo.Hooks;
import ObjectRepo.readOR;
import ReporterRepo.Reporter;
import cucumber.api.java.en.*;

public class NavigationSteps extends GlobalData {
	WebDriver driver;
	static ClickSteps clickObj;
	static WaitStep wait;
	static FillSteps fillObj;
	static VerifySteps verifyObj;
	readOR objOR;
	static CommonSteps commObj;
	static GlobalData global;
	static Hooks hookObj;

	public NavigationSteps() {
		driver = Hooks.driver;
		clickObj = new ClickSteps();
		wait = new WaitStep();
		fillObj = new FillSteps();
		verifyObj = new VerifySteps();
		objOR = new readOR();
		global = new GlobalData();
		commObj = new CommonSteps();
		hookObj = new Hooks();
		//commObj.HandleBusinessProcessError();
	}

/*	
	
	
	@Then("^open current \"([^\"]*)\"$")
	public void open_current(String sEntity) throws Throwable {
		try {
			boolean bFail = false;
			Actions act = new Actions(driver);
			String sLink = "", sNum = "";
			switch (sEntity.toUpperCase()) {
			case "ACCOUNT":
				sLink = "Accounts_link";
				sNum = getAccountNumber();
				break;
			case "CONTACT":
				sLink = "Contacts_link";
				sNum = getContactNumber();
				break;
			case "OPPORTUNITY":
				sLink = "Opportunity_link";
				sNum = getOpportunityNumber();
				break;
			case "LEAD":
				sLink = "Leads_link";
				sNum = getLeadNumber();
				break;
			default:
				Reporter.report("FAIL", "Entity type " + sEntity + " is not valid for this method");
			}
			// global.setOpportunityNumber("OPP-200527083109T0C");
			clickObj.ElementClick("NECRM_Dashboard", sLink);
			Thread.sleep(2000);
			try {
				driver.findElement(By.xpath("(//section/descendant::button[@id='cancelButton'])[1]")).click();
				Thread.sleep(2000);
			} catch (NoSuchElementException ne) {

			}
			fillObj.FillTextbox("CommandMenu_bar", "Search_textbox", sNum);
			clickObj.ElementClick("CommandMenu_bar", "Search_button");
			wait.dynamicWait();
			String linkXpath = "//div[@class='wj-cells']/div[@class='wj-row' and contains(@data-lp-id,'"
					+ sEntity.toLowerCase() + "')]/descendant::a";
			wait.waitForElementDisplayed(linkXpath);
			driver.findElement(By.xpath(linkXpath)).click();
			wait.dynamicWait();
			wait.waitForElementDisplayed(objOR.getXpath("CommandMenu_bar", "Save_button"));
			// clickObj.ElementClick("OpportunityTablist", "Summary_link");
			act.moveToElement(driver.findElement(By.xpath("(//input[contains(@aria-label,'Number')])[1]"))).click()
					.sendKeys(Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN,
							Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN)
					.build().perform();
			Thread.sleep(2000);
			act.sendKeys(Keys.PAGE_UP, Keys.PAGE_UP, Keys.PAGE_UP, Keys.PAGE_UP, Keys.PAGE_UP, Keys.PAGE_UP,
					Keys.PAGE_UP, Keys.PAGE_UP).build().perform();
			Thread.sleep(2000);
		} catch (Exception e) {
			Reporter.report("FAIL", e.getMessage());
		}
	}

	@Then("^open latest \"([^\"]*)\"$")
	public void open_latest(String sEntity) throws Throwable {
		setCurrentStep("open latest " + sEntity);

		try {
			boolean bFail = false;
			Actions act = new Actions(driver);
			String sLink = "";
			switch (sEntity.toUpperCase()) {
			case "ACCOUNT":
				sLink = "Accounts_link";
				break;
			case "CONTACT":
				sLink = "Contacts_link";
				break;
			case "OPPORTUNITY":
				sLink = "Opportunity_link";
				break;
			case "LEAD":
				sLink = "Leads_link";
				break;
			default:
				Reporter.report("FAIL", "Entity type " + sEntity + " is not valid for this method");
			}
			// global.setOpportunityNumber("OPP-200527083109T0C");
			clickObj.ElementClick("NECRM_Dashboard", sLink);
			wait.dynamicWait();
			try {
				driver.findElement(By.xpath("(//section/descendant::button[@id='cancelButton'])[1]")).click();
				Thread.sleep(2000);
			} catch (NoSuchElementException ne) {

			}
			String linkXpath = "//div[@class='wj-cells']/div[@class='wj-row' and contains(@data-lp-id,'"
					+ sEntity.toLowerCase() + "')]/descendant::a";
			wait.waitForElementDisplayed(linkXpath);
			// driver.findElement(By.xpath(linkXpath)).click();
			WebElement colElm = driver
					.findElement(By.xpath("(//div[@class='wj-cells']/div[@class='wj-row' and contains(@data-lp-id,'"
							+ sEntity.toLowerCase() + "')]/div)[2]"));
			act.doubleClick(colElm).perform();
			wait.dynamicWait();
			wait.waitForElementDisplayed(objOR.getXpath("CommandMenu_bar", "Save_button"));
			// clickObj.ElementClick("OpportunityTablist", "Summary_link");
			act.moveToElement(driver.findElement(By.xpath("(//input[contains(@aria-label,'Number')])[1]"))).click()
					.sendKeys(Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN,
							Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN)
					.build().perform();
			Thread.sleep(2000);
			act.sendKeys(Keys.PAGE_UP, Keys.PAGE_UP, Keys.PAGE_UP, Keys.PAGE_UP, Keys.PAGE_UP, Keys.PAGE_UP,
					Keys.PAGE_UP, Keys.PAGE_UP).build().perform();
			Thread.sleep(2000);
		} catch (Exception e) {
			Reporter.report("FAIL", e.getMessage());
		}
	}

	@Then("^open new \"([^\"]*)\"$")
	public void open_new_entity(String sEntity) throws Throwable {
		try {
			boolean bFail = false;
			Actions act = new Actions(driver);
			String sLink = "";
			switch (sEntity.toUpperCase()) {
			case "ACCOUNT":
				sLink = "Accounts_link";
				break;
			case "CONTACT":
				sLink = "Contacts_link";
				break;
			case "OPPORTUNITY":
				sLink = "Opportunity_link";
				break;
			case "LEAD":
				sLink = "Leads_link";
				break;
			default:
				Reporter.report("FAIL", "Entity type " + sEntity + " is not valid for this method");
			}
			// global.setOpportunityNumber("OPP-200527083109T0C");
			clickObj.ElementClick("NECRM_Dashboard", sLink);
			wait.dynamicWait();
			try {
				driver.findElement(By.xpath("(//section/descendant::button[@id='cancelButton'])[1]")).click();
				Thread.sleep(2000);
			} catch (NoSuchElementException ne) {

			}
			clickObj.ElementClick("NECRM_Dashboard", "New_button");
			wait.dynamicWait();
			wait.waitForElementDisplayed(objOR.getXpath("CommandMenu_bar", "Save_button"));
			// clickObj.ElementClick("OpportunityTablist", "Summary_link");
			act.moveToElement(driver.findElement(By.xpath("(//input[contains(@aria-label,'Number')])[1]"))).click()
					.sendKeys(Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN,
							Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN)
					.build().perform();
			Thread.sleep(2000);
			act.sendKeys(Keys.PAGE_UP, Keys.PAGE_UP, Keys.PAGE_UP, Keys.PAGE_UP, Keys.PAGE_UP, Keys.PAGE_UP,
					Keys.PAGE_UP, Keys.PAGE_UP).build().perform();
			Thread.sleep(2000);
		} catch (Exception e) {
			Reporter.report("FAIL", e.getMessage());
		}
	}

	@Then("^user open inactive contact$")
	public void open_inactive_contact() throws Throwable {
		boolean bFail = false;
		try {
			Actions act = new Actions(driver);
			clickObj.ElementClick("NECRM_Dashboard", "Contacts_link");
			wait.dynamicWait();
			try {
				driver.findElement(By.xpath("(//section/descendant::button[@id='cancelButton'])[1]")).click();
				Thread.sleep(2000);
			} catch (NoSuchElementException ne) {

			}
			clickObj.ElementClick("ContactTablist", "MyActiveContacts_header");
			wait.waitForElementDisplayed("//*[text()='Inactive Contacts']");
			driver.findElement(By.xpath("//*[text()='Inactive Contacts']")).click();
			Thread.sleep(1000);

			String linkXpath = "//div[@class='wj-cells']/div[@class='wj-row' and contains(@data-lp-id,'contact')]/descendant::a";
			wait.waitForElementDisplayed(linkXpath);
			Thread.sleep(2000);
			driver.findElement(By.xpath(linkXpath)).click();
			wait.dynamicWait();
			wait.waitForElementDisplayed(objOR.getXpath("CommandMenu_bar", "Activate_button"));
			// clickObj.ElementClick("OpportunityTablist", "Summary_link");
			act.moveToElement(driver.findElement(By.xpath("(//input[contains(@aria-label,'Number')])[1]"))).click()
					.sendKeys(Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN,
							Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN)
					.build().perform();
			Thread.sleep(2000);
			act.sendKeys(Keys.PAGE_UP, Keys.PAGE_UP, Keys.PAGE_UP, Keys.PAGE_UP, Keys.PAGE_UP, Keys.PAGE_UP,
					Keys.PAGE_UP, Keys.PAGE_UP).build().perform();
			Thread.sleep(2000);
		} catch (Exception e) {
			bFail = true;
			Reporter.report("INFO", e.getMessage());
		}
		if (bFail) {
			Reporter.report("FAIL", "");
		}
	}

	@Then("^user open the (account|contact|opportunity|lead) \"([^\"]*)\"$")
	public void open_entity_with_value(String sEntity, String sValue) throws Throwable {
		try {
			boolean bFail = false;
			Actions act = new Actions(driver);
			String sLink = "";
			switch (sEntity.toUpperCase()) {
			case "ACCOUNT":
				sLink = "Accounts_link";
				if (sValue.equalsIgnoreCase("%Current%")) {
					sValue = getAccountNumber();
				}
				break;
			case "CONTACT":
				sLink = "Contacts_link";
				if (sValue.equalsIgnoreCase("%Current%")) {
					sValue = getContactNumber();
				}
				break;
			case "OPPORTUNITY":
				sLink = "Opportunity_link";
				if (sValue.equalsIgnoreCase("%Current%")) {
					sValue = getOpportunityNumber();
				}
				break;
			case "LEAD":
				sLink = "Leads_link";
				if (sValue.equalsIgnoreCase("%Current%")) {
					sValue = getLeadNumber();
				}
				break;
			default:
				Reporter.report("FAIL", "Entity type " + sEntity + " is not valid for this method");
			}
			if (sValue.contains("%Dic:")) {
				String sKey = sValue.replace("%Dic:", "").replace("%", "");
				sValue = getDic().get(sKey);
			}

			// global.setOpportunityNumber("OPP-200527083109T0C");
			clickObj.ElementClick("NECRM_Dashboard", sLink);
			wait.dynamicWait();
			try {
				driver.findElement(By.xpath("//section/descendant::button[@data-id='cancelButton']")).click();
			} catch (NoSuchElementException ne) {

			}
			wait.waitForElementDisplayed(objOR.getXpath("CommandMenu_bar", "Search_textbox"));
			fillObj.FillTextbox("CommandMenu_bar", "Search_textbox", sValue);
			clickObj.ElementClick("CommandMenu_bar", "Search_button");
			wait.dynamicWait();
			String linkXpath = "//div[@class='wj-cells']/div[@class='wj-row' and contains(@data-lp-id,'"
					+ sEntity.toLowerCase() + "')]/descendant::a";
			wait.waitForElementDisplayed(linkXpath);
			driver.findElement(By.xpath(linkXpath)).click();
			wait.dynamicWait();
			wait.waitForElementDisplayed(objOR.getXpath("CommandMenu_bar", "Save_button"));
			// clickObj.ElementClick("OpportunityTablist", "Summary_link");
			act.moveToElement(driver.findElement(By.xpath("(//input[contains(@aria-label,'Number')])[1]"))).click()
					.sendKeys(Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN,
							Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN,
							Keys.PAGE_DOWN)
					.build().perform();
			Thread.sleep(3000);
			act.sendKeys(Keys.PAGE_UP, Keys.PAGE_UP, Keys.PAGE_UP, Keys.PAGE_UP, Keys.PAGE_UP, Keys.PAGE_UP,
					Keys.PAGE_UP, Keys.PAGE_UP).build().perform();
			Thread.sleep(2000);
		} catch (Exception e) {
			Reporter.report("FAIL", e.getMessage());
		}
	}

	@Given("^user open \"([^\"]*)\"$")
	public void user_open(String sUrl) throws Throwable {
		commObj.closeBrowser();
		hookObj.initDriver();
		driver = Hooks.driver;
		driver.get(sUrl);
	}

	@Then("^open current lead in closed leads$")
	public void open_closed_lead() throws Throwable {
		boolean bFail = false;
		try {
			Actions act = new Actions(driver);
			clickObj.ElementClick("NECRM_Dashboard", "Leads_link");
			wait.dynamicWait();
			try {
				driver.findElement(By.xpath("(//section/descendant::button[@id='cancelButton'])[1]")).click();
				Thread.sleep(2000);
			} catch (NoSuchElementException ne) {

			}
			clickObj.ElementClick("LeadTablist", "MyOpenLeads_header");
			wait.waitForElementDisplayed("//*[text()='Closed Leads']");
			driver.findElement(By.xpath("//*[text()='Closed Leads']")).click();
			Thread.sleep(1000);

			String linkXpath = "//div[@class='wj-cells']/div[@class='wj-row' and contains(@data-lp-id,'lead')]/descendant::a";
			wait.waitForElementDisplayed(linkXpath);
			fillObj.FillTextbox("CommandMenu_bar", "Search_textbox", getLeadNumber());
			clickObj.ElementClick("CommandMenu_bar", "Search_button");
			wait.dynamicWait();
			wait.waitForElementDisplayed(linkXpath);
			Thread.sleep(2000);
			driver.findElement(By.xpath(linkXpath)).click();
			wait.dynamicWait();
			wait.waitForElementDisplayed(objOR.getXpath("CommandMenu_bar", "ReActivateLead_button"));
			// clickObj.ElementClick("OpportunityTablist", "Summary_link");
			act.moveToElement(driver.findElement(By.xpath("(//input[contains(@aria-label,'Number')])[1]"))).click()
					.sendKeys(Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN,
							Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN)
					.build().perform();
			Thread.sleep(2000);
			act.sendKeys(Keys.PAGE_UP, Keys.PAGE_UP, Keys.PAGE_UP, Keys.PAGE_UP, Keys.PAGE_UP, Keys.PAGE_UP,
					Keys.PAGE_UP, Keys.PAGE_UP).build().perform();
			Thread.sleep(2000);
		} catch (Exception e) {
			bFail = true;
			Reporter.report("INFO", e.getMessage());
		}
		if (bFail) {
			Reporter.report("FAIL", "");
		}
	}

	@Then("^user verify (opportunity|account|contact|lead) \"([^\"]*)\" available in \"([^\"]*)\" view$")
	public void open_enity_in_view(String sEntity, String sEntityVal, String sView) throws Throwable {
		try {
			String sLink = "";
			switch (sEntity.toUpperCase()) {
			case "ACCOUNT":
				sLink = "Accounts_link";
				break;
			case "CONTACT":
				sLink = "Contacts_link";
				break;
			case "OPPORTUNITY":
				sLink = "Opportunity_link";
				break;
			case "LEAD":
				sLink = "Leads_link";
				break;
			default:
				Reporter.report("FAIL", "Entity type " + sEntity + " is not valid for this method");
			}
			clickObj.ElementClick("NECRM_Dashboard", sLink);
			wait.dynamicWait();
			try {
				driver.findElement(By.xpath("(//section/descendant::button[@id='cancelButton'])[1]")).click();
				Thread.sleep(2000);
			} catch (NoSuchElementException ne) {

			}
			clickObj.ElementClick("CommandMenu_bar", "SelectAView_link");
			wait.waitForElementDisplayed("//*[text()='" + sView + "']");
			driver.findElement(By.xpath("//*[text()='" + sView + "']")).click();
			wait.dynamicWait();
			if (sEntityVal.toLowerCase().contentEquals("%opportunity%")) {
				sEntityVal = getOpportunityNumber();
				if (sEntity == null) {
					Reporter.report("FAIL", "Failed to get the Opportunity number from the global dictionary");
				}
			}
			if (sEntityVal.toLowerCase().contentEquals("%account%")) {
				sEntityVal = getAccountNumber();
				if (sEntity == null) {
					Reporter.report("FAIL", "Failed to get the Account number from the global dictionary");
				}
			}
			if (sEntityVal.toLowerCase().contentEquals("%contact%")) {
				sEntityVal = getContactNumber();
				if (sEntity == null) {
					Reporter.report("FAIL", "Failed to get the Contact number from the global dictionary");
				}
			}
			fillObj.FillTextbox("CommandMenu_bar", "Search_textbox", sEntityVal);
			clickObj.ElementClick("CommandMenu_bar", "Search_button");
			wait.dynamicWait();
			try {
				wait.waitForElementDisplayed("//div[@class='wj-cells']/div[@class='wj-row' and contains(@data-lp-id,'"
						+ sEntity.toLowerCase() + "')]/descendant::a");
			} catch (Exception e) {
				Reporter.report("INFO", "Failed to find the " + sEntity + " with value " + sEntityVal);
				Reporter.report("FAIL", "");
			}
		} catch (Exception fe) {
			Reporter.report("FAIL", fe.getMessage());
		}
	}

	@Then("^open latest (opportunity|account|contact|lead) in the \"([^\"]*)\" view$")
	public void open_latest_enity_in_view(String sEntity, String sView) throws Throwable {
		Actions act = new Actions(driver);
		try {
			String sLink = "", sAriaLabel = "";
			switch (sEntity.toUpperCase()) {
			case "ACCOUNT":
				sLink = "Accounts_link";
				sAriaLabel = "Account Number";
				break;
			case "CONTACT":
				sLink = "Contacts_link";
				sAriaLabel = "Contact Number";
				break;
			case "OPPORTUNITY":
				sLink = "Opportunity_link";
				sAriaLabel = "Opportunity Number";
				break;
			case "LEAD":
				sLink = "Leads_link";
				sAriaLabel = "Lead Number";
				break;
			default:
				Reporter.report("FAIL", "Entity type " + sEntity + " is not valid for this method");
			}
			clickObj.ElementClick("NECRM_Dashboard", sLink);
			wait.dynamicWait();
			try {
				driver.findElement(By.xpath("(//section/descendant::button[@id='cancelButton'])[1]")).click();
				Thread.sleep(2000);
			} catch (NoSuchElementException ne) {

			}
			clickObj.ElementClick("CommandMenu_bar", "SelectAView_link");
			wait.waitForElementDisplayed("//*[text()='" + sView + "']");
			driver.findElement(By.xpath("//*[text()='" + sView + "']")).click();
			wait.dynamicWait();

			try {
				wait.waitForElementDisplayed("//div[@class='wj-cells']/div[@class='wj-row' and contains(@data-lp-id,'"
						+ sEntity.toLowerCase() + "')]/descendant::a");
				WebElement colElm = driver
						.findElement(By.xpath("(//div[@class='wj-cells']/div[@class='wj-row' and contains(@data-lp-id,'"
								+ sEntity.toLowerCase() + "')]/div)[2]"));
				act.doubleClick(colElm).perform();
				wait.dynamicWait();
				wait.waitForElementDisplayed(objOR.getXpath("CommandMenu_bar", "Save_button"));
				act.moveToElement(driver.findElement(By.xpath("(//input[contains(@aria-label,'Number')])[1]"))).click()
						.sendKeys(Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN,
								Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN)
						.build().perform();
				Thread.sleep(2000);
				act.sendKeys(Keys.PAGE_UP, Keys.PAGE_UP, Keys.PAGE_UP, Keys.PAGE_UP, Keys.PAGE_UP, Keys.PAGE_UP,
						Keys.PAGE_UP, Keys.PAGE_UP).build().perform();
				Thread.sleep(2000);
			} catch (Exception e) {
				Reporter.report("INFO", e.getMessage());
				Reporter.report("FAIL", "");
			}
		} catch (Exception fe) {
			Reporter.report("FAIL", fe.getMessage());
		}
	}

	@Then("^open (opportunity|account|contact|lead|competitor) in the \"([^\"]*)\" view$")
	public void open_enity_in_view(String sEntity, String sView) throws Throwable {
		Actions act = new Actions(driver);
		try {
			String sLink = "";
			switch (sEntity.toUpperCase()) {
			case "ACCOUNT":
				sLink = "Accounts_link";
				break;
			case "CONTACT":
				sLink = "Contacts_link";
				break;
			case "OPPORTUNITY":
				sLink = "Opportunity_link";
				break;
			case "LEAD":
				sLink = "Leads_link";
				break;
			case "COMPETITOR":
				sLink = "Competitors_link";
				break;
			default:
				Reporter.report("FAIL", "Entity type " + sEntity + " is not valid for this method");
			}
			clickObj.ElementClick("NECRM_Dashboard", sLink);
			wait.dynamicWait();
			try {
				driver.findElement(By.xpath("(//section/descendant::button[@id='cancelButton'])[1]")).click();
				Thread.sleep(2000);
			} catch (NoSuchElementException ne) {

			}
			clickObj.ElementClick("CommandMenu_bar", "SelectAView_link");
			wait.waitForElementDisplayed("//*[text()='" + sView + "']");
			driver.findElement(By.xpath("//*[text()='" + sView + "']")).click();
		} catch (Exception fe) {
			Reporter.report("FAIL", fe.getMessage());
		}
	}
	
	//**************************** My project NCRM ******* //
	
	
	
	
	*/
	
}
