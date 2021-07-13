package ExecutionRepo.StepDefinition;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
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

public class ClickSteps {
	static WebDriver driver;
	readOR objOR;
	static WaitStep wait;
	static CommonSteps commonObj;

	public ClickSteps() {
		driver = Hooks.driver;
		objOR = new readOR();
		wait = new WaitStep();
		commonObj = new CommonSteps();
		//commonObj.HandleBusinessProcessError();
	}

	
	@When("^I mousehover on \"([^\"]*)\" dropdown in \"([^\"]*)\" page$")
	public void mouseHover_on_Menu_Link(String sControlName, String sPageName) throws Throwable {
		Thread.sleep(5000);
		try {
			WebElement obj = objOR.getObject(sPageName, sControlName);
			Actions act = new Actions(driver);
			act.moveToElement(obj);
			act.build().perform();

		} catch (Exception e) {
			Reporter.report("INFO", e.getMessage());
			Reporter.report("FAIL", e.getMessage());
		}

	}
	
	@Then("^I click on option About$")
	public void click_on_about() {
		
		WebElement obj = driver.findElement(By.xpath("//li[@class='dropdown'][1]/ul"));
		
		
		List<WebElement> list = obj.findElements(By.tagName("li"));
		
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getText().equals("About")) {
				list.get(i).click();
				break;
			}
		}
			
		
		
	}
	
	
	
	
	@Then("^I click this \"([^\"]*)\" button on \"([^\"]*)\" page$")
	public void clickOnAnyQuestions(String sControlName, String sPageName) throws Throwable {
		try {
			Thread.sleep(10000);
			
			WebElement oControl = objOR.getObject(sPageName, sControlName);
			
			//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", oControl);
			
			ElementToClick(sPageName, sControlName);
			Thread.sleep(8000);
		} catch (Exception e) {
			Reporter.report("FAIL", e.getMessage());
		}
	}
	
	
	@Then("^I see a pop window with Title \"([^\"]*)\" on \"([^\"]*)\" page$")
	public void i_see_a_pop_window_with_Title(String sControlName, String sPageName) {
	   
		
		try {
			Thread.sleep(10000);
			
			WebElement oControl = objOR.getObject(sPageName, sControlName);
			
			if(oControl.isDisplayed()) {
				System.out.println("Title of Window match:= "+oControl.getText());
			}else {
				System.out.println("Title of Window doesn't match:= "+oControl.getText());
			}
			
			Thread.sleep(8000);
		} catch (Exception e) {
			Reporter.report("FAIL", e.getMessage());
		}
	
		
		
	}

	@Then("^I press close \"([^\"]*)\" on this pop window on \"([^\"]*)\" page$")
	public void i_press_close_x_on_this_pop_window(String sControlName, String sPageName) throws Throwable {

		try {
			Thread.sleep(10000);
			
			WebElement oControl = objOR.getObject(sPageName, sControlName);
			
			ElementToClick(sPageName, sControlName);
			Thread.sleep(8000);
		} catch (Exception e) {
			Reporter.report("FAIL", e.getMessage());
		}

		
	}

	@Then("^I click on modifysearch \"([^\"]*)\" button on \"([^\"]*)\" page$")
	public void Click_modifysearch_Take_Demo_button(String sControlName, String sPageName) throws Throwable {
	   
		try {
			Thread.sleep(10000);
			
			
			ElementToClick(sPageName, sControlName);
			Thread.sleep(8000);
		} catch (Exception e) {
			Reporter.report("FAIL", e.getMessage());
		}
	}

	
	
	@Then("^I see clickable radio buttons \"([^\"]*)\" under Flights option on \"([^\"]*)\" page$")
	public void clickable_radio_buttons(String sControlName, String sPageName) {
		
		try {
			Thread.sleep(10000);
			
			WebElement oControl = objOR.getObject(sPageName, sControlName);
			
			List<WebElement> list = oControl.findElements(By.tagName("td"));
			
			for(int i=0;i<list.size();i++) {
				System.out.println("Radio Button Name:= "+list.get(i).getText());
				System.out.println("Radio Button Selected:= "+list.get(i).isSelected());
			}
			
			Thread.sleep(8000);
		} catch (Exception e) {
			Reporter.report("FAIL", e.getMessage());
		}
		
		
	}
	
	
	
	
	@When("I select \"([^\"]*)\" radio button on \"([^\"]*)\" page")
	public void i_select_radio_button_on_page(String sControlName, String sPageName) {

		try {
			Thread.sleep(10000);
			
			WebElement oControl = objOR.getObject(sPageName, sControlName);
			
			List<WebElement> list = oControl.findElements(By.tagName("td"));
			
			
			
			for(int i=0;i<list.size();i++) {
				System.out.println("Radio Button Name:= "+list.get(i).getText());
				System.out.println("Radio Button Selected:= "+list.get(i).isSelected());
			}
			
			Thread.sleep(8000);
		} catch (Exception e) {
			Reporter.report("FAIL", e.getMessage());
		}
	
	}

	@When("I select \"([^\"]*)\" tab on \"([^\"]*)\" page")
	public void i_select_tab_on_page(String sControlName, String sPageName) throws Exception {
		try {
			Thread.sleep(5000);
			WebElement obj = objOR.getObject(sPageName, sControlName);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", obj);	
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@When("I select \"([^\"]*)\" on \"([^\"]*)\" page")
	public void i_select_on_page(String sControlName, String sPageName) throws Exception {
		WebElement obj = objOR.getObject(sPageName, sControlName);
		
		if(obj.getAttribute("class").equals("selected")) {
			System.out.println("Flights is selected...");
		}
	}
	
	@When("I select oneway radio \"([^\"]*)\" on \"([^\"]*)\" page")
	public void i_select_on_page_oneway(String sControlName, String sPageName) throws Exception {
		WebElement obj = objOR.getObject(sPageName, sControlName);
		
		if(obj.isSelected()) {
			System.out.println("Selected...");
		}else {
			System.out.println("NOt selected...");
		}
		
		
	}
	 

	@When("I select Agra_textbox departure city in the \"([^\"]*)\" field on \"([^\"]*)\" page")
	public void i_select_departure_city_in_the_field_on_page(String sControlName, String sPageName) throws Exception {
	
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='ctl00_mainContent_ddl_originStation1_CTXT']")).click();
		
		WebElement obj = objOR.getObject(sPageName, sControlName);
		Thread.sleep(5000);
		obj.click();
		
		
		
		
		
	}

	@When("I select Jaipur_textbox arrival city in the \"([^\"]*)\" field on \"([^\"]*)\" page")
	public void i_select_arrival_city_in_the_field_on_page(String sControlName, String sPageName) throws Exception {
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@id='ctl00_mainContent_ddl_destinationStation1_CTXT']")).click();
		Thread.sleep(10000);
		driver.findElement(By.xpath("//input[@id='ctl00_mainContent_ddl_destinationStation1_CTXT']")).sendKeys("Jaipur");
		Thread.sleep(5000);
		//driver.findElement(By.xpath("//li[@class='city_selected ']//a[@value='JAI'][normalize-space()='Jaipur (JAI)']")).click();
		//Thread.sleep(5000);
		
		
	}

	@When("I select any date in the \"([^\"]*)\" field on \"([^\"]*)\" page")
	public void i_select_any_date_in_the_field_on_page(String sControlName, String sPageName) throws Exception {
		WebElement obj = objOR.getObject(sPageName, sControlName);
		Thread.sleep(5000);
		obj.click();
		Thread.sleep(5000);
		driver.findElement(By.linkText("12")).click();
		//driver.findElement(By.xpath("//a[@class='ui-state-default ui-state-hover']")).click();
		
		
	}

	@When("I select \"([^\"]*)\" from 1Adults dropdown on \"([^\"]*)\" page")
	public void i_select_from_1Adults_dropdown_on_page(String sControlName, String sPageName) throws InterruptedException {
		Thread.sleep(5000);
		
		WebElement objs= driver.findElement(By.xpath("//div[@id='divpaxinfo']"));
		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", objs);
		Thread.sleep(10000);
		
		Select select1 = new Select(driver.findElement(By.xpath("//select[@id='ctl00_mainContent_ddl_Adult']")));
		
		select1.selectByIndex(0);
	}

	
	@When("I select \"([^\"]*)\" from 2Adults dropdown on \"([^\"]*)\" page")
	public void i_select_from_2Adults_dropdown_on_page(String sControlName, String sPageName) throws InterruptedException {
		Thread.sleep(5000);
		
		WebElement objs= driver.findElement(By.xpath("//div[@id='divpaxinfo']"));
		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", objs);
		Thread.sleep(10000);
		
		Select select1 = new Select(driver.findElement(By.xpath("//select[@id='ctl00_mainContent_ddl_Adult']")));
		
		select1.selectByIndex(1);
	}

	
	@When("I select \"([^\"]*)\" from Adults dropdown on \"([^\"]*)\" page")
	public void i_select_from_Adults_dropdown_on_page(String sControlName, String sPageName) throws InterruptedException {
		Thread.sleep(5000);
		
		WebElement objs= driver.findElement(By.xpath("//div[@id='divpaxinfo']"));
		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", objs);
		Thread.sleep(10000);
		
		Select select1 = new Select(driver.findElement(By.xpath("//select[@id='ctl00_mainContent_ddl_Adult']")));
		
		select1.selectByIndex(0);
	}

	
	@When("I select \"([^\"]*)\" from the Currency dropdown on \"([^\"]*)\" page")
	public void i_select_from_the_Currency_dropdown_on_page(String sControlName, String sPageName) throws Exception {
	
		Thread.sleep(5000);
		
		WebElement obj = objOR.getObject(sPageName, sControlName);
		Thread.sleep(5000);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", obj);
		Thread.sleep(10000);
		
		Select select = new Select(obj);
		
		select.selectByIndex(5);
		
		
	}

	@When("I click on \"([^\"]*)\" CTA button on \"([^\"]*)\" page")
	public void i_click_on_CTA_button_on_page(String sControlName, String sPageName) throws Exception {
	
		Thread.sleep(5000);
		
		WebElement obj = objOR.getObject(sPageName, sControlName);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", obj);
		//Thread.sleep(10000);
		
		
	}

	
	@When("^I checkbox \"([^\"]*)\" on \"([^\"]*)\" page$")
	public void checkbox_family_friends(String sControlName, String sPageName) throws Exception {
		Thread.sleep(5000);
		
		WebElement obj = objOR.getObject(sPageName, sControlName);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", obj);
		Thread.sleep(10000);
		
	}
	
	
	
	@When("^I click on \"([^\"]*)\" button on \"([^\"]*)\" page$")
	public void click_modify(String sControlName, String sPageName) throws Exception {
		Thread.sleep(5000);
		WebElement obj = objOR.getObject(sPageName, sControlName);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", obj);
		Thread.sleep(5000);
		
	}
	
	@When("^I click \"([^\"]*)\" CTA button on \"([^\"]*)\" page$")
	public void click_go_icon(String sControlName, String sPageName) throws Exception {
		Thread.sleep(5000);
		WebElement obj = objOR.getObject(sPageName, sControlName);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", obj);
		Thread.sleep(5000);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@Given("^user navigate to the sample url$")
public void urltest() {
	
		driver.get("https://the-internet.herokuapp.com/javascript_alerts");
	
}
@When("^I click on the jsalert button$")	
public void clickjs() {
	
	driver.findElement(By.xpath("//button[contains(text(),'Click for JS Alert')]")).click();
}

@Then("^I click on alert box$")
public void clickonalertbox() {
	
	Alert alert = driver.switchTo().alert();
	System.out.println(alert.getText());
	alert.accept();
}
 


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//--------------------------------------------------------------------------
	
	
	
	
	
	public void ElementMouseHover(String sPageName, String sControlName) throws InterruptedException {
		Thread.sleep(5000);
		try {
			WebElement obj = objOR.getObject(sPageName, sControlName);
			Actions act = new Actions(driver);
			act.moveToElement(obj);
			act.build().perform();

		} catch (Exception e) {
			Reporter.report("INFO", e.getMessage());
			Reporter.report("FAIL", e.getMessage());
		}

	}

	
	
	
	
	// Page element click
	public void ElementToClick(String sPageName, String sControlName) throws Throwable {
		try {
			Thread.sleep(6000);
			WebElement obj = objOR.getObject(sPageName, sControlName);
			Thread.sleep(8000);
			obj.click();

		} catch (Exception e) {
			Reporter.report("INFO", e.getMessage());
			Reporter.report("FAIL", "");
		}
		Thread.sleep(2000);
	}

	
	
	// Page element click
	public void ElementToClick_JS(String sPageName, String sControlName) throws Throwable {
		try {
			Thread.sleep(6000);
			WebElement obj = objOR.getObject(sPageName, sControlName);
			Thread.sleep(8000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", obj);
		} catch (Exception e) {
			Reporter.report("INFO", e.getMessage());
			Reporter.report("FAIL", "");
		}
		Thread.sleep(2000);
	}

	
	
	  @Given("^I want to launch hotel app$")
	  public void browsertest() {
		  
		  
	  }
	  
	  @When("^I enter correct userid and password$")
	  public void userid_test() {
		  
		  System.out.println("userid and password");
		  
	  }
	  
	  
	  @Then("^I validate logout link$")
	  public void logout_test() {
		  System.out.println("logout link verify");
	  }

	
	
}
