package ExecutionRepo.StepDefinition;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import DriverRepo.CreateDriver;
import ExecutionRepo.Hooks;
import ObjectRepo.readOR;
import ReporterRepo.Reporter;
import cucumber.api.java.en.*;

public class CommonSteps extends GlobalData {
	static WebDriver driver;
	readOR objOR;
	static WaitStep wait;
	// static ClickSteps clickObj;
	/*
	public CommonSteps() {
		driver = Hooks.driver;
		objOR = new readOR();
		wait = new WaitStep();
		// clickObj = new ClickSteps();
	}

	
	
	@When("^user verify \"([^\"]*)\" displayed in MAHDashboard page$")
	public void getPageTitle(String sTitle) {
		
		try {
			if(driver.getTitle().trim().equals(sTitle)) {
				Reporter.report("INFO", "Dasboard Page Title "+sTitle);
			}	
		}catch(Exception ie) {
			ie.printStackTrace();
			Reporter.report("INFO", "Login unsuccessfull page title "+driver.getTitle());
		}
		
	}
	
	public void switchToFrame(String sFrame) {
		List<WebElement> arrFrames = driver.findElements(By.tagName("iframe"));
		WebElement iFrame = null;
		if (arrFrames.size() != 0) {
			for (WebElement frame : arrFrames) {
				iFrame = frame;
				if (frame.getAttribute("id").contentEquals(sFrame)
						| frame.getAttribute("title").contentEquals(sFrame)) {
					break;
				}
			}
			driver.switchTo().frame(iFrame);
		}
	}

	@Then("^user close the browser$")
	public void closeBrowser() throws Throwable {
//		CreateDriver.killInstance();
//		Hooks.driver.quit();
//		Hooks.driver = null;
		driver.close();
		System.out.println("browser closed successfully");
	}

	@Given("^user scroll down to the page")
	public void scrollDownToPage() throws Throwable {
		Actions act = new Actions(driver);
		wait.waitForElementDisplayed(objOR.getXpath("NECRM_Dashboard", "FormHeader_label"));
		act.moveToElement(driver.findElement(By.xpath("(//input[contains(@aria-label,'Number')])[1]"))).click()
				.sendKeys(Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN,
						Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN)
				.build().perform();
		Thread.sleep(4000);
		act.sendKeys(Keys.PAGE_UP).sendKeys(Keys.PAGE_UP).sendKeys(Keys.PAGE_UP).sendKeys(Keys.PAGE_UP)
				.sendKeys(Keys.PAGE_UP).build().perform();
	}

	@Given("^user scroll down till \"([^\"]*)\"")
	public void scrollDownTillControl(String sControl) throws Throwable {
		Actions act = new Actions(driver);
		boolean bFound = false;
		int iCount = 0;
		wait.waitForElementDisplayed(objOR.getXpath("CommandMenu_bar", "Refresh_button"));
		act.moveToElement(driver.findElement(By.xpath("(//input[contains(@aria-label,'Number')])[1]"))).click()
				.sendKeys(Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN,
						Keys.PAGE_DOWN, Keys.PAGE_DOWN, Keys.PAGE_DOWN)
				.build().perform();
		Thread.sleep(4000);
		while (!bFound) {
			iCount++;
			try {
				WebElement obj = driver.findElement(By.xpath("//*[text()='" + sControl + "']"));
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", obj);
				bFound = true;
			} catch (NoSuchElementException ne) {
				act.sendKeys(Keys.PAGE_DOWN);
				Thread.sleep(2000);
			}
			if (iCount == 10) {
				break;
			}
		}
	}

	@Given("^page scroll (down|up)")
	public void page_scroll(String scroll) throws Throwable {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		if (scroll.toUpperCase().contentEquals("DOWN")) {
			jse.executeScript("window.scrollBy(0,250)");
		} else if (scroll.toUpperCase().contentEquals("UP")) {
			jse.executeScript("window.scrollBy(0,-250)");
		}
	}

	@Given("^scroll down in connections page")
	public void page_scroll_connections_page() throws Throwable {
		Actions act = new Actions(driver);
		try {
			wait.waitForElementDisplayed("(//section/descendant::button[@aria-label='Refresh'])[1]");
		} catch (Exception e) {
			// clickObj.ElementClick("OpportunityTablist", "Connections_link");
			objOR.getObject("OpportunityTablist", "Connections_link").click();
			wait.waitForElementDisplayed("(//section/descendant::button[@aria-label='Refresh'])[1]");
		}
		WebElement elm = driver.findElement(By.xpath("(//section/descendant::button[@aria-label='Refresh'])[1]"));
		act.moveToElement(elm).click().build().perform();
		act.sendKeys(Keys.PAGE_DOWN).sendKeys(Keys.PAGE_DOWN).sendKeys(Keys.PAGE_DOWN).sendKeys(Keys.PAGE_DOWN)
				.sendKeys(Keys.PAGE_DOWN).build().perform();
		Thread.sleep(2000);
	}

	@Then("^get all the mandatory controls in the page")
	public void get_madatory_controls() throws Throwable {
		List<String> arrTextboxName = new ArrayList<String>();
		List<String> arrDropdownName = new ArrayList<String>();
		List<WebElement> arrTextbox = driver.findElements(By.xpath(
				"//section[@aria-label='Details' or @aria-label='Address' or @aria-label='Contact Information']/descendant::input[@aria-required='true']"));
		List<WebElement> arrDropdown = driver.findElements(By.xpath(
				"//section[@aria-label='Details' or @aria-label='Address' or @aria-label='Contact Information']/descendant::select[@aria-required='true']"));
		for (WebElement elm : arrTextbox) {
			arrTextboxName.add(elm.getAttribute("aria-label"));
		}
		for (WebElement elm : arrDropdown) {
			arrDropdownName.add(elm.getAttribute("aria-label"));
		}
		setArrMandTextbox(arrTextboxName);
		setArrMandDropdown(arrDropdownName);
	}

	@Then("^get all the non mandatory controls in the page")
	public void get_non_madatory_controls() throws Throwable {
		List<String> arrTextboxName = new ArrayList<String>();
		List<String> arrDropdownName = new ArrayList<String>();
		List<WebElement> arrTextbox = driver.findElements(By.xpath(
				"//section[@aria-label='Details' or @aria-label='Address' or @aria-label='Contact Information']/descendant::input[not(@aria-required='true')]"));
		List<WebElement> arrDropdown = driver.findElements(By.xpath(
				"//section[@aria-label='Details' or @aria-label='Address' or @aria-label='Contact Information']/descendant::select[not(@aria-required='true')]"));
		for (WebElement elm : arrTextbox) {
			arrTextboxName.add(elm.getAttribute("aria-label"));
		}
		for (WebElement elm : arrDropdown) {
			arrDropdownName.add(elm.getAttribute("aria-label"));
		}
		setArrNonMandTextbox(arrTextboxName);
		setArrNonMandDrodpwn(arrDropdownName);
	}

	public void HandleBusinessProcessError() {
		try {
			driver.findElement(By.xpath("//h1[@aria-label='Business Process Error']"));
			driver.findElement(By.id("cancelButton")).click();
			Thread.sleep(2000);
			Reporter.report("INFO", "Business Process Error handled");
		} catch (NoSuchElementException ne) {

		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}

	@Given("^user add \"([^\"]*)\" to global dictionary as \"([^\"]*)\"$")
	public void addToGlobalDic(String sDataType, String sKey) throws Throwable {
		String sData = "";
		switch (sDataType.toUpperCase()) {
		case "OPPORTUNITYNUMBER":
			sData = getOpportunityNumber();
			break;
		case "ACCOUNTNUMBER":
			sData = getAccountNumber();
			break;
		case "OPPORTUNITYNAME":
			sData = getOpportunityName();
			break;
		case "CONTACTNUMBER":
			sData = getContactNumber();
			break;
		case "TESTNOTE_DATELABEL":
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			sData = "Note_" + now.getYear() + "_" + now.getMonthValue() + "_" + now.getDayOfMonth() + "_"
					+ now.getHour() + "_" + now.getMinute() + "_" + now.getSecond();
			break;
		default:
			Reporter.report("INFO", "Invalid data type " + sDataType);
			Reporter.report("FAIL", "");
		}
		Map<String, String> temp = getDic();
		temp.put(sKey, sData);
		setDic(temp);
	}

	@When("^user switch to new window (\\d+)$")
	public void switchToNewWindow(int iWindow) throws Throwable {
		Set<String> windowHandles = driver.getWindowHandles();
		int iCount = 0;
		if (iWindow > windowHandles.size()) {
			Reporter.report("INFO", "Could not switch to window " + iWindow);
			Reporter.report("INFO", windowHandles.size() + " windows displayed");
			Reporter.report("FAIL", "");
		}
		for (String sWindow : windowHandles) {
			iCount = iCount + 1;
			if (iWindow == iCount) {
				driver.switchTo().window(sWindow);
				Reporter.report("INFO", "switched to window " + iWindow);
				break;
			}
		}
	}

	@When("^user switch to default content$")
	public void switchToDefaultContent() throws Throwable {
		driver.switchTo().defaultContent();
	}

	@When("^user close the current window$")
	public void close_current_window() throws Throwable {
		driver.close();
		Thread.sleep(2000);
	}
	
	
	*/
}
