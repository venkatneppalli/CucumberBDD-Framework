package ExecutionRepo.StepDefinition;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.gson.JsonObject;

import ExecutionRepo.Hooks;
import ObjectRepo.readOR;
import ReporterRepo.Reporter;
import net.bytebuddy.utility.privilege.GetSystemPropertyAction;
import cucumber.api.java.en.*;
import io.cucumber.datatable.DataTable;

public class FillSteps extends GlobalData {
	static WebDriver driver;
	readOR objOR;
	static WaitStep wait;
	static CommonSteps commonObj;
	static MyApplicationSteps quicknavObj;
	static int iCount = 0;
	/*
	public FillSteps() {
		driver = Hooks.driver;
		objOR = new readOR();
		wait = new WaitStep();
		commonObj = new CommonSteps();
		commonObj.HandleBusinessProcessError();
	}

	

	// Fill element in a page
	public void FillCustomerTextbox(String sPageName, String sControlName, String sValue) throws Throwable {
		try {
			
			WebElement obj = objOR.getObject(sPageName, sControlName);
		
			System.out.println("Text Box is empty");
				Actions actText = new Actions(driver);
				Thread.sleep(5000);
				actText.sendKeys(obj, sValue);
				actText.build().perform();
		
		} catch (Exception e) {
			Reporter.report("INFO", e.getMessage());
			Reporter.report("FAIL", "");
		}
		Thread.sleep(2000);
	}

	public boolean isElementPresent(WebDriver driver,WebElement element) {
		
		try {
			//driver.findElement(by);
			element.isDisplayed();
			return true;
			
		}catch(Exception e) {
			return false;
		}
		
	}

	
	public void EditClientFillTextbox(String sValue) throws InterruptedException {

		try {
			Actions typeInCAPS = new Actions(driver);
			Thread.sleep(5000);
			// System.out.println(sControlName);
			typeInCAPS.sendKeys(sValue);
			typeInCAPS.build().perform();
		} catch (Exception e) {
			Reporter.report("INFO", e.getMessage());
			Reporter.report("FAIL", "");
		}
	}

	// Select dropdown in apage
	public void SelectValueFromDropdown(String sPageName, String sControlName, String sValue) throws Throwable {
		WebElement obj = objOR.getObject(sPageName, sControlName);
		try {
			Select dropdown = new Select(obj);
			dropdown.selectByVisibleText(sValue);
		} catch (Exception e) {
			Reporter.report("INFO", e.getMessage());
			Reporter.report("FAIL", "");
		}
		Thread.sleep(2000);
	}

	// Fill element in a page
	public void FillTextboxField(String sPageName, String sControlName, String sValue) throws Throwable {
		WebElement obj = objOR.getObject(sPageName, sControlName);
		try {
			obj.click();
			Thread.sleep(500);
			String selectAll = Keys.chord(Keys.CONTROL, "a");
			obj.sendKeys(selectAll);
			obj.sendKeys(Keys.BACK_SPACE);
			int iSize = obj.getAttribute("value").length();
			for (int i = 0; i < iSize; i++) {
				obj.sendKeys(Keys.BACK_SPACE);
			}
			Thread.sleep(500);
			obj.sendKeys(sValue);
			obj.sendKeys(Keys.TAB);
		} catch (Exception e) {
			Reporter.report("INFO", e.getMessage());
			Reporter.report("FAIL", "");
		}
		Thread.sleep(2000);
	}

	// Fill textbox inside a frame
	// Pass the frame id as 'sFrame'
	public void FillTextbox(String sPageName, String sFrame, String sControlName, String sValue) throws Throwable {
		String sXpath = objOR.getXpath(sPageName, sFrame, sControlName);
		wait.waitForElementDisplayed("//iframe[@id='" + sFrame + "' or @title='" + sFrame + "']");
		commonObj.switchToFrame(sFrame);
		wait.waitForElementDisplayed(sXpath);
		WebElement obj = objOR.getObjectFromFrame(sPageName, sFrame, sControlName);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", obj);
		try {
			obj.click();
			Thread.sleep(500);
			String selectAll = Keys.chord(Keys.CONTROL, "a");
			obj.sendKeys(selectAll);
			obj.sendKeys(Keys.BACK_SPACE);
			int iSize = obj.getAttribute("value").length();
			for (int i = 0; i < iSize; i++) {
				obj.sendKeys(Keys.BACK_SPACE);
			}
			Thread.sleep(500);
			obj.sendKeys(sValue);
			obj.sendKeys(Keys.TAB);
		} catch (Exception e) {
			Reporter.report("INFO", e.getMessage());
			Reporter.report("FAIL", "");
		}
		driver.switchTo().parentFrame();
		Thread.sleep(2000);
	}

	// Select dropdown in apage
	public void SelectDropdownValue(String sPageName, String sControlName, String sValue) throws Throwable {
		String sXpath = objOR.getXpath(sPageName, sControlName);
		wait.waitForElementDisplayed(sXpath);
		WebElement obj = objOR.getObject(sPageName, sControlName);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", obj);
		try {
			Select dropdown = new Select(obj);
			dropdown.selectByVisibleText(sValue);
		} catch (Exception e) {
			Reporter.report("INFO", e.getMessage());
			Reporter.report("FAIL", "");
		}
		Thread.sleep(2000);
	}

	// Select dropdown in apage
	public void SelectDropdownValue(String sPageName, String sFrame, String sControlName, String sValue)
			throws Throwable {
		String sXpath = objOR.getXpath(sPageName, sFrame, sControlName);
		wait.waitForElementDisplayed("//iframe[@id='" + sFrame + "' or @title='" + sFrame + "']");
		commonObj.switchToFrame(sFrame);
		wait.waitForElementDisplayed(sXpath);
		WebElement obj = driver.findElement(By.xpath(sXpath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", obj);
		try {
			Select dropdown = new Select(obj);
			dropdown.selectByVisibleText(sValue);
		} catch (Exception e) {
			Reporter.report("INFO", e.getMessage());
			Reporter.report("FAIL", "");
		}
		driver.switchTo().parentFrame();
		Thread.sleep(2000);
	}

	public void FillInputSearchOld(String sPageName, String sControlName, String sValue) throws Exception {
		String sXpath = objOR.getXpath(sPageName, sControlName);
		WebDriverWait wt = new WebDriverWait(driver, 10);
		Actions act = new Actions(driver);
		try {
			wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXpath)));
		} catch (TimeoutException te) {
			// String sLabel = sXpath.replace("//input[@aria-label='", "").replace(",
			// Lookup']", "");
			String sLabel = sXpath.substring(sXpath.indexOf("input[@aria-label='") + 19).replace("']", "");
			WebElement elm = driver.findElement(By.xpath("//label[contains(text(),'" + sLabel
					+ "')]/parent::span/parent::div/parent::div/following-sibling::div"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elm);
			int width = elm.getSize().getWidth();
			act.moveToElement(elm).moveByOffset((width / 2) - 2, 0).click().build().perform();
			Thread.sleep(1000);
			act.sendKeys(Keys.BACK_SPACE).build().perform();
			// act.sendKeys(Keys.TAB).build().perform();
		}

		wait.waitForElementDisplayed(sXpath);
		WebElement obj = objOR.getObject(sPageName, sControlName);
		// ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
		// obj);
		try {
			act.moveToElement(obj).click().sendKeys(Keys.BACK_SPACE).build().perform();
			obj.sendKeys(sValue);
			Thread.sleep(2000);
			driver.findElement(By.xpath(sXpath + "/following-sibling::button")).click();
			Thread.sleep(1000);
			wait.waitForElementDisplayed("(//ul[@aria-label='Lookup Search Results']/li/descendant::label)[1]");
			// act.moveToElement(obj).click().sendKeys(Keys.TAB).sendKeys(Keys.TAB).build().perform();
			Thread.sleep(3000);
			act.moveToElement(obj).click().sendKeys(Keys.ARROW_DOWN).build().perform();
			Thread.sleep(2000);
			act.sendKeys(Keys.ENTER).build().perform();
		} catch (Exception e) {
			Reporter.report("INFO", e.getMessage());
			Reporter.report("FAIL", "");
		}
	}

	public void FillInputSearch(String sPageName, String sControlName, String sValue) throws Exception {
		String sXpath = objOR.getXpath(sPageName, sControlName);
		WebDriverWait wt = new WebDriverWait(driver, 10);
		String sLabel = sXpath.substring(sXpath.indexOf("@aria-label") + 13).replace(", Lookup']", "")
				.replace(", Lookup')]", "").trim();
		if (sLabel.contains("@aria-label")) {
			sLabel = sLabel.substring(sLabel.indexOf("@aria-label") + 13).trim();
		}
		Actions act = new Actions(driver);
		try {
			wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXpath)));
		} catch (TimeoutException te) {
			WebElement elm = driver.findElement(By.xpath("//label[contains(text(),'" + sLabel
					+ "')]/parent::span/parent::div/parent::div/following-sibling::div"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elm);

			int width = elm.getSize().getWidth();
			act.moveToElement(elm).moveByOffset((width / 2) - 2, 0).click().build().perform();
			Thread.sleep(1000);
			act.sendKeys(Keys.BACK_SPACE).build().perform();
			act.moveToElement(driver.findElement(By.xpath("//label[contains(text(),'" + sLabel + "')]"))).click();
			Thread.sleep(1000);
			// act.sendKeys(Keys.TAB).build().perform();
		}
		Thread.sleep(1000);
		wait.waitForElementDisplayed(sXpath);
		WebElement obj = objOR.getObject(sPageName, sControlName);
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", obj);
			act.moveToElement(obj).click().build().perform();
			Thread.sleep(2000);
			act.sendKeys(sValue).build().perform();
			Thread.sleep(4000);
			wait.waitForElementDisplayed(
					"//ul[@aria-label='Lookup Search Results']/li/descendant::label/span[text()='" + sValue + "']", 20);
			driver.findElement(By.xpath(
					"//ul[@aria-label='Lookup Search Results']/li/descendant::label/span[text()='" + sValue + "']"))
					.click();
		} catch (Exception e) {
			Reporter.report("INFO", e.getMessage());
			Reporter.report("FAIL", "");
		}
	}

	public void FillMultiSelectLookup(String sPageName, String sControlName, String sValue) throws Exception {
		String sXpath = objOR.getXpath(sPageName, sControlName);
		WebDriverWait wt = new WebDriverWait(driver, 10);
		Actions act = new Actions(driver);
		wait.waitForElementDisplayed(sXpath);
		WebElement obj = objOR.getObject(sPageName, sControlName);
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", obj);
			act.moveToElement(obj).click().build().perform();
			Thread.sleep(2000);
			act.sendKeys(sValue).build().perform();
			Thread.sleep(4000);
			wait.waitForElementDisplayed(
					"(//ul[@role='tree']/descendant::div[contains(@id,'LookupResultsPopup')]/label)[1]", 20);
			driver.findElement(
					By.xpath("(//ul[@role='tree']/descendant::div[contains(@id,'LookupResultsPopup')]/label)[1]"))
					.click();
		} catch (Exception e) {
			Reporter.report("INFO", e.getMessage());
			Reporter.report("FAIL", "");
		}
	}

	@When("^user fill \"([^\"]*)\" (textbox|inputsearch|dropdown|containercheckbox|checkbox) with \"([^\"]*)\" in \"([^\"]*)\" page$")
	public void user_fill_with_in_page(String sControlName, String sControlType, String sValue, String sPage)
			throws Throwable {
		try {
			switch (sControlType.toUpperCase()) {
			case "TEXTBOX":
				FillTextbox(sPage, sControlName, sValue);
				break;
			case "INPUTSEARCH":
				FillInputSearch(sPage, sControlName, sValue);
				break;
			case "DROPDOWN":
				SelectDropdownValue(sPage, sControlName, sValue);
				break;
			case "CONTAINERCHECKBOX":
				FillContainerCheckbox(sPage, sControlName, sValue);
				break;
			case "CHECKBOX":
				FillContainerCheckbox(sPage, sControlName, sValue);
				break;
			default:
				Reporter.report("FAIL", "Invalid control type " + sControlType + " for this method");
			}

		} catch (Exception e) {
			Reporter.report("FAIL", e.getMessage());
		}
	}

	public void FillContainerCheckbox(String sPage, String sControlName, String sValue) throws Throwable {
		try {
			Actions act = new Actions(driver);
			wait.waitForElementDisplayed(objOR.getXpath(sPage, sControlName));
			WebElement oControl = objOR.getObject(sPage, sControlName);
			// WebElement elmOption = driver
			// .findElement(By.xpath(objOR.getXpath(sPage, sControlName) +
			// "/div[@aria-checked='true']"));
			if (!oControl.getAttribute("title").contentEquals(sValue)) {
				// oConstrol.click();
				act.moveToElement(oControl).click().build().perform();
				Thread.sleep(1000);
				Reporter.report("PASS", "Successfully filled the " + sControlName + " control with value " + sValue);
			}
		} catch (Exception e) {
			Reporter.report("FAIL", "Failed to fill the " + sControlName + " control with value " + sValue);
		}
	}

	public void FillCheckbox(String sPage, String sControlName, String sValue) throws Throwable {
		try {
			Actions act = new Actions(driver);
			String sXpath = objOR.getXpath(sPage, sControlName);
			wait.waitForElementDisplayed(sXpath);
			WebElement oControl = driver.findElement(By.xpath(sXpath + "/div[@aria-checked='true']"));
			if (!oControl.getAttribute("title").contentEquals(sValue)) {
				act.moveToElement(oControl).click().build().perform();
				Thread.sleep(1000);
				oControl = driver.findElement(By.xpath(sXpath + "/div[@aria-checked='true']"));
				if (oControl.getAttribute("title").contentEquals(sValue)) {
					Reporter.report("PASS",
							"Successfully filled the " + sControlName + " control with value " + sValue);
				} else {
					Reporter.report("FAIL", "Failed to fill the " + sControlName + " control with value " + sValue);
				}
			}
		} catch (Exception e) {
			Reporter.report("FAIL", "Failed to fill the " + sControlName + " control with value " + sValue);
		}
	}

	public void FillCheckbox(String sPage, String sFrame, String sControlName, String sValue) throws Throwable {
		try {
			Actions act = new Actions(driver);
			String sXpath = objOR.getXpath(sPage, sFrame, sControlName);
			wait.waitForElementDisplayed("//iframe[@id='" + sFrame + "' or @title='" + sFrame + "']");
			commonObj.switchToFrame(sFrame);
			wait.waitForElementDisplayed(sXpath);
			WebElement oControl = driver.findElement(By.xpath(sXpath + "/div[@aria-checked='true']"));
			if (!oControl.getAttribute("title").contentEquals(sValue)) {
				act.moveToElement(oControl).click().build().perform();
				Thread.sleep(1000);
				oControl = driver.findElement(By.xpath(sXpath + "/div[@aria-checked='true']"));
				if (oControl.getAttribute("title").contentEquals(sValue)) {
					Reporter.report("PASS",
							"Successfully filled the " + sControlName + " control with value " + sValue);
				} else {
					Reporter.report("FAIL", "Failed to fill the " + sControlName + " control with value " + sValue);
				}
			}
		} catch (Exception e) {
			Reporter.report("FAIL", "Failed to fill the " + sControlName + " control with value " + sValue);
		}
		driver.switchTo().parentFrame();
	}

	public void FillInputSearch(String sPageName, String sFrame, String sControlName, String sValue) throws Throwable {
		String sXpath = objOR.getXpath(sPageName, sFrame, sControlName);
		wait.waitForElementDisplayed("//iframe[@id='" + sFrame + "' or @title='" + sFrame + "']");
		commonObj.switchToFrame(sFrame);
		wait.waitForElementDisplayed(sXpath);
		Actions act = new Actions(driver);
		WebElement obj = objOR.getObjectFromFrame(sPageName, sFrame, sControlName);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", obj);
		try {
			obj.click();
			Thread.sleep(500);
			String selectAll = Keys.chord(Keys.CONTROL, "a");
			obj.sendKeys(selectAll);
			obj.sendKeys(Keys.BACK_SPACE);
			int iSize = obj.getAttribute("value").length();
			for (int i = 0; i < iSize; i++) {
				obj.sendKeys(Keys.BACK_SPACE);
			}
			Thread.sleep(500);
			act.moveToElement(obj).click().sendKeys(sValue).build().perform();
			try {
				wait.waitForElementDisplayed("//div[@id='as_containerSearch' and @style='visibility: visible;']");
			} catch (Exception e1) {
				obj.click();
				wait.waitForElementDisplayed("//div[@id='as_containerSearch' and @style='visibility: visible;']");
			}
			Thread.sleep(4000);
			obj.sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(500);
			obj.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			Reporter.report("INFO", e.getMessage());
			Reporter.report("FAIL", "");
		}
		driver.switchTo().parentFrame();
		Thread.sleep(2000);
	}

	@Then("^user fill below controls in \"([^\"]*)\" page$")
	public void fill_below_controls_in_page(String sPage, DataTable dt) throws Throwable {
		boolean bFail = false;
		List<List<String>> expValues = dt.asLists();
		for (int i = 0; i < expValues.size(); i++) {
			try {
				String sKey = expValues.get(i).get(0);
				String sVal = expValues.get(i).get(1);

				System.out.println("FillSteps Print keys:= " + sKey);
				System.out.println("FIllSteps PRINT SKEYS:= " + sVal);

				if (sVal.toLowerCase().contentEquals("%currentaccount%")) {
					sVal = getAccountName();
					System.out.println("FillSteps Get AccountName:= " + sVal);
				}
				if (sVal.toLowerCase().contentEquals("%accountnumber%")) {
					sVal = getAccountNumber();
					System.out.println("FillSteps Get AccountNumber:= " + sVal);
				}
				if (sVal.contains("%Dic:")) {
					String sDicKey = sVal.replace("%Dic:", "").replace("%", "");
					sVal = getDic().get(sDicKey);
					System.out.println("FillSteps  Get Dic:= " + sVal);
				}
				String sControlType = sKey.split("_")[1];

				System.out.println("FillSteps  Get sControlType:= " + sControlType);

				switch (sControlType.toUpperCase()) {
				case "TEXTBOX":
					FillTextbox(sPage, sKey, sVal);
					break;
				case "INPUTSEARCH":
					FillInputSearch(sPage, sKey, sVal);
					break;
				case "DROPDOWN":
					SelectDropdownValue(sPage, sKey, sVal);
					break;
				case "CHECKBOX":
					FillCheckbox(sPage, sKey, sVal);
					break;
				default:
					bFail = true;
					Reporter.report("INFO", "The control name " + sKey + " is not valid");
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

	@Then("^user fill CloseAsLost window with below values$")
	public void fill_closeaslost_field_values(DataTable dt) throws Throwable {
		boolean bFail = false;
		List<List<String>> expValues = dt.asLists();
		for (int i = 0; i < expValues.size(); i++) {
			try {
				String sKey = expValues.get(i).get(0);
				String sVal = expValues.get(i).get(1);
				String sControlType = sKey.split("_")[1];
				switch (sControlType.toUpperCase()) {
				case "TEXTBOX":
					FillTextbox("CloseAsLost", sKey, sVal);
					break;
				case "INPUTSEARCH":
					WebElement elm = objOR.getObject("CloseAsLost", sKey);
					elm.click();
					wait.waitForElementDisplayed(
							"(//div[@aria-label='competitorid Lookup results']/descendant::label[text()='" + sVal
									+ "'])[1]");
					driver.findElement(
							By.xpath("(//div[@aria-label='competitorid Lookup results']/descendant::label[text()='"
									+ sVal + "'])[1]"))
							.click();
					break;
				case "DROPDOWN":
					SelectDropdownValue("CloseAsLost", sKey, sVal);
					break;
				default:
					bFail = true;
					Reporter.report("INFO", "The control name " + sKey + " is not valid");
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

	@Then("user fill date \"([^\"]*)\" in \"([^\"]*)\" of \"([^\"]*)\" page")
	public void user_fill_date_in_of_page(String sDate, String sControl, String sPage) throws Throwable {
		Actions act = new Actions(driver);
		String sXpath = objOR.getXpath(sPage, sControl);
		wait.waitForElementDisplayed(sXpath);
		WebElement obj = objOR.getObject(sPage, sControl);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", obj);
		act.moveToElement(obj).doubleClick().build().perform();
		String selectAll = Keys.chord(Keys.CONTROL, "a");
		obj.sendKeys(selectAll);
		obj.sendKeys(Keys.BACK_SPACE);
		Thread.sleep(1000);
		obj.sendKeys(sDate);
		obj.sendKeys(Keys.TAB);
		Thread.sleep(1000);
	}

	@Then("^user clear \"([^\"]*)\" control in \"([^\"]*)\" page$")
	public void clearControl(String sControl, String sPage) throws Throwable {
		try {
			Actions act = new Actions(driver);
			WebDriverWait wt = new WebDriverWait(driver, 10);
			String sXpath = objOR.getXpath(sPage, sControl);
			if (sControl.contains("_textbox")) {
				wait.waitForElementDisplayed(sXpath);
				WebElement obj = objOR.getObject(sPage, sControl);
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", obj);
				String selectAll = Keys.chord(Keys.CONTROL, "a");
				obj.sendKeys(selectAll);
				obj.sendKeys(Keys.BACK_SPACE);
			}
			if (sControl.contains("_inputsearch")) {
				try {
					wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(sXpath)));
				} catch (TimeoutException te) {
					String sLabel = sXpath.substring(sXpath.indexOf("@aria-label") + 13).replace(", Lookup']", "")
							.replace(", Lookup')]", "").trim();
					if (sLabel.contains("@aria-label")) {
						sLabel = sLabel.substring(sLabel.indexOf("@aria-label") + 13).trim();
					}
					WebElement elm = driver.findElement(By.xpath("//label[contains(text(),'" + sLabel
							+ "')]/parent::span/parent::div/parent::div/following-sibling::div"));
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", elm);

					int width = elm.getSize().getWidth();
					act.moveToElement(elm).moveByOffset((width / 2) - 2, 0).click().build().perform();
					Thread.sleep(1000);
					act.sendKeys(Keys.BACK_SPACE).build().perform();
				}
			}
		} catch (Exception e) {
			Reporter.report("INFO", e.getMessage());
			Reporter.report("FAIL", "");
		}
	}

	@When("^user enter \"([^\"]*)\" value in \"([^\"]*)\" in \"([^\"]*)\" page$")
	public void enter_value_in_control(String sValue, String sControlName, String sPage) throws Throwable {
		Actions act = new Actions(driver);
		try {
			if (sValue.toLowerCase().contentEquals("%currentaccount%")) {
				sValue = getAccountName();
			}
			if (sValue.toLowerCase().contentEquals("%accountnumber%")) {
				sValue = getAccountNumber();
			}
			wait.waitForElementDisplayed(objOR.getXpath(sPage, sControlName));
			WebElement obj = objOR.getObject(sPage, sControlName);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", obj);
			if (sControlName.contains("_inputsearch")) {
				obj.sendKeys(sValue);
			}
			if (sControlName.contains("_checkbox")) {
				if (!obj.getAttribute("title").contentEquals(sValue)) {
					act.moveToElement(obj).click().build().perform();
					Thread.sleep(1000);
				}
			}
		} catch (Exception e) {
			Reporter.report("FAIL", e.getMessage());
		}
	}

	@Then("^user fill below controls and values in \"([^\"]*)\" frame of \"([^\"]*)\" page$")
	public void verify_below_controls_and_values_in_page(String sFrame, String sPage, DataTable dt) throws Throwable {
		boolean bFail = false;
		List<List<String>> expValues = dt.asLists();
		for (int i = 0; i < expValues.size(); i++) {
			try {
				String sKey = expValues.get(i).get(0);
				String sVal = expValues.get(i).get(1);
				String sControlType = sKey.split("_")[1];
				switch (sControlType.toUpperCase()) {
				case "TEXTBOX":
					FillTextbox(sPage, sFrame, sKey, sVal);
					break;
				case "INPUTSEARCH":
					FillInputSearch(sPage, sFrame, sKey, sVal);
					break;
				case "DROPDOWN":
					SelectDropdownValue(sPage, sFrame, sKey, sVal);
					break;
				case "CHECKBOX":
					FillCheckbox(sPage, sFrame, sKey, sVal);
					break;
				default:
					bFail = true;
					Reporter.report("INFO", "The control name " + sKey + " is not valid");
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

	// ***********************************My Project*******************************
	// Filling Summary Details Page in CP_identityPage
	@Then("^user fills SummaryDetails in \"([^\"]*)\" page$")
	public void sampleFillMethod(String sPage, DataTable dt) throws Throwable {
		boolean bFail = false;

		List<List<String>> expValues = dt.asLists();
		for (int i = 0; i < expValues.size(); i++) {
			try {
				String sKey = expValues.get(i).get(0);
				String sVal = expValues.get(i).get(1);
				String sControlType = sKey.split("_")[1];

				switch (sControlType.toUpperCase()) {
				case "TEXTBOX":
					FillTextbox_MyProject(sPage, sKey, sVal);
					System.out.println("Executed FillTextBox method.....");
					Thread.sleep(6000);
					break;
				case "ENTER":
					FillTextbox_Details(sPage, sKey, sVal);
					System.out.println("Executed FillTextBox details method.....");
					break;

				case "PRIMARY":
					FillTextbox_Primary(sPage, sKey, sVal);
					System.out.println("Executed Primary details method.....");
					break;

				case "SEARCH":
					ClickOn_Search(sPage, sKey);
					System.out.println("Executed Search method.....");
					break;
				case "MENU":
					SelectValue_menu(sPage, sKey);
					System.out.println("Executed Menu method.....");
					break;
				default:
					bFail = true;
					Reporter.report("INFO", "The control name " + sKey + " is not valid");
				}

			} catch (Exception e) {
				bFail = true;
				Reporter.report("INFO", e.getMessage());
			}

		}
		if (bFail) {
			Reporter.report("FAIL", "");
		}

		driver.switchTo().defaultContent(); // closed the iframe of summary page

	}

	// Fill ProjectName Text field
	public void FillTextbox_MyProject(String sPageName, String sControlName, String sVal) throws Throwable {
		System.out.println("swtiched to filltextboxmypojrect method......");
		Thread.sleep(5000);
		driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='contentIFrame1']")));

		WebElement obj = objOR.getObject(sPageName, sControlName);
		System.out.println("ControleName:= " + sControlName);

		Thread.sleep(5000);
		try {
			((JavascriptExecutor) driver).executeScript("document.getElementById('name_i').value='" + sVal + "'");

		} catch (Exception e) {
			Reporter.report("INFO", e.getMessage());
			Reporter.report("FAIL", "");
		}
		Thread.sleep(2000);
	}

	// Fill AccountName Text Field
	public void FillTextbox_Details(String sPageName, String sControlName, String sValue) throws Throwable {
		Thread.sleep(5000);
		System.out.println("sValue:= " + sValue);

		WebElement obj = objOR.getObject(sPageName, sControlName);
		Thread.sleep(8000);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", obj);

		// ((JavascriptExecutor) driver).executeScript("arguments[0].click();", obj);

		try {
			// obj.click();
			Thread.sleep(5000);
			obj.sendKeys(sValue);

			Thread.sleep(5000);
		} catch (Exception e) {
			Reporter.report("INFO", e.getMessage());
			Reporter.report("FAIL", "");
		}
		Thread.sleep(2000);
	}

	// Fill AccountName Text Field
	public void FillTextbox_Details_JS(String sPageName, String sControlName, String sVal) throws Throwable {
		Thread.sleep(8000);
		System.out.println("sValue:= " + sVal);
		// driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='contentIFrame1']")));
		// //--iframe switch not required
		try {

			WebElement obj = objOR.getObject(sPageName, sControlName);
			Thread.sleep(5000);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", obj);
			Thread.sleep(3000);
			((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value','" + sVal + "')", obj);
			Thread.sleep(3000);
			// ((JavascriptExecutor) driver).executeScript("arguments[0].click();", obj);

			Thread.sleep(5000);

		} catch (Exception e) {
			Reporter.report("INFO", e.getMessage());
			Reporter.report("FAIL", "");
		}
		Thread.sleep(2000);
	}

	// Fill Primary Text Field
	public void FillTextbox_Primary(String sPageName, String sControlName, String sVal) throws Throwable {

		Thread.sleep(5000);
		// driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='contentIFrame1']")));
		Thread.sleep(5000);
		try {

			driver.findElement(By.id("header_process_parentcontactid")).sendKeys("suzy");
			// WebElement obj = objOR.getObject(sPageName, sControlName);
			// ((JavascriptExecutor) driver).executeScript("arguments[0].click();", obj);
			Thread.sleep(5000);
			// obj.sendKeys(sVal);
			Thread.sleep(6000);

			Thread.sleep(5000);
		} catch (Exception e) {
			Reporter.report("INFO", e.getMessage());
			Reporter.report("FAIL", "");
		}
		Thread.sleep(2000);

	}

	// Search and CLick Account Name lookup search icon
	public void ClickOn_Search(String sPageName, String sControlName) throws Throwable {

		Thread.sleep(5000);
		// driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='contentIFrame1']")));
		WebElement obj = objOR.getObject(sPageName, sControlName);
		System.out.println("ClickonSearch Xpath:= " + obj);

		Thread.sleep(5000);
		try {

			((JavascriptExecutor) driver).executeScript("arguments[0].click();", obj);
			Thread.sleep(5000);

			Thread.sleep(5000);

		} catch (Exception e) {
			Reporter.report("INFO", e.getMessage());
			Reporter.report("FAIL", "");
		}
		Thread.sleep(2000);
	}

	// Fill element in a page
	public void FillTextbox(String sPageName, String sControlName, String sValue) throws Throwable {
		// String sXpath = objOR.getXpath(sPageName, sControlName);
		// wait.waitForElementDisplayed(sXpath);

		WebElement obj = objOR.getObject(sPageName, sControlName);
		// ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
		// obj);
		try {
			obj.click();
			Thread.sleep(500);
			String selectAll = Keys.chord(Keys.CONTROL, "a");
			obj.sendKeys(selectAll);
			obj.sendKeys(Keys.BACK_SPACE);
			int iSize = obj.getAttribute("value").length();
			for (int i = 0; i < iSize; i++) {
				obj.sendKeys(Keys.BACK_SPACE);
			}
			Thread.sleep(500);
			obj.sendKeys(sValue);
			obj.sendKeys(Keys.TAB);
		} catch (Exception e) {
			Reporter.report("INFO", e.getMessage());
			Reporter.report("FAIL", "");
		}
		Thread.sleep(2000);
	}

	// Select dropdown menu in apage
	public void SelectValue_menu(String sPageName, String sControlName) throws Throwable {
		// driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='contentIFrame1']")));
		try {
			WebElement obj = objOR.getObject(sPageName, sControlName);
			System.out.println("select value of menu xpath:= " + obj);

			List<WebElement> parentmenu_list = obj.findElements(By.tagName("li"));

			System.out.println("menu list:= " + parentmenu_list.size());

			for (int i = 0; i < parentmenu_list.size(); i++) {

				if (i == 0) {
					System.out.println("Clicked on 1st Item from ParentoountMenu");
					Thread.sleep(2000);
					parentmenu_list.get(i).click();
					System.out.println("selected first element from the menu...");
					// driver.switchTo().defaultContent(); /// Switched to contentIFrame1 from
					// FillTextbox_MyProject method
				}

			}

		} catch (Exception e) {
			Reporter.report("INFO", e.getMessage());
			Reporter.report("FAIL", "");
		}
		Thread.sleep(2000);
	}

	// Select dropdown menu in apage
	public void SelectByValuE_menu(String sPageName, String sControlName, String sVal) throws Throwable {

		System.out.println("sVal:= " + sVal);
		try {
			WebElement obj = objOR.getObject(sPageName, sControlName);
			System.out.println("select value of menu xpath:= " + obj);

			List<WebElement> parentmenu_list = obj.findElements(By.tagName("li"));

			System.out.println("menu list:= " + parentmenu_list.size());

			for (int i = 0; i < parentmenu_list.size(); i++) {

				if (parentmenu_list.get(i).getText().trim().contentEquals(sVal)) {
					System.out.println("Clicked on 1st Item from ParentoountMenu");
					Thread.sleep(2000);
					parentmenu_list.get(i).click();
					System.out.println("selected first element from the menu...");
				}

			}

		} catch (Exception e) {
			Reporter.report("INFO", e.getMessage());
			Reporter.report("FAIL", "");
		}
		Thread.sleep(2000);
	}

	@Then("^user fills Investigate mandatory details on \"([^\"]*)\" page$")
	public void user_fills_Investigate_mandatory_details_on_page(String sPage, DataTable dt) throws Throwable {
		boolean bFail = false;

		List<List<String>> expValues = dt.asLists();
		for (int i = 0; i < expValues.size(); i++) {
			try {
				String sKey = expValues.get(i).get(0);
				String sVal = expValues.get(i).get(1);
				String sControlType = sKey.split("_")[1];

				System.out.println("sKey:= " + sKey);
				System.out.println("sVal:= " + sVal);
				System.out.println("sControlType:= " + sControlType);

				switch (sControlType.toUpperCase()) {
				case "ENTER":
					driver.switchTo().frame(driver.findElement(By.name("contentIFrame1")));
					FillTextbox_Details_JS(sPage, sKey, sVal);
					break;
				case "SEARCH":
					ClickOn_Search(sPage, sKey);
					System.out.println("Executed Search method.....");
					break;
				case "MENU":
					SelectByValuE_menu(sPage, sKey, sVal);
					System.out.println("Executed Menu method.....");
					driver.switchTo().defaultContent();
					break;
				default:
					bFail = true;
					Reporter.report("INFO", "The control name " + sKey + " is not valid");
				}
			} catch (Exception e) {
				bFail = true;
				Reporter.report("INFO", e.getMessage());
			}
		}
		if (bFail) {
			Reporter.report("FAIL", "");
		}

		// driver.switchTo().defaultContent();

	}

	// Fill element in a page
	public void GenericMethod_Text(String sPageName, String sControlName, String sVal) throws Throwable {
		String sXpath = objOR.getXpath(sPageName, sControlName);
		wait.waitForElementDisplayed(sXpath);

		try {
			WebElement obj = objOR.getObject(sPageName, sControlName);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", obj);

			if (sVal.contentEquals("%ProjectName%")) {
				sVal = getProjectName();
				System.out.println("sVal:= " + sVal);
				sVal = "my project";
				setProjectName(sVal);
			}

		} catch (Exception e) {
			Reporter.report("INFO", e.getMessage());
			Reporter.report("FAIL", "");
		}
		Thread.sleep(2000);
	}
*/
}
