package ExecutionRepo.StepDefinition;

import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeoutException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ExecutionRepo.Hooks;
import ObjectRepo.readOR;
import ReporterRepo.Reporter;
import cucumber.api.java.en.*;
import io.cucumber.datatable.DataTable;

public class MyApplicationSteps extends GlobalData {
	WebDriver driver;
	static ClickSteps clickObj;
	static WaitStep wait;
	static FillSteps fillObj;
	static VerifySteps verifyObj;
	static NavigationSteps navObj;
	readOR objOR;
	static CommonSteps commonObj;

	public MyApplicationSteps() {
		driver = Hooks.driver;
		clickObj = new ClickSteps();
		wait = new WaitStep();
		fillObj = new FillSteps();
		verifyObj = new VerifySteps();
		objOR = new readOR();
		navObj = new NavigationSteps();
		commonObj = new CommonSteps();
		//commonObj.HandleBusinessProcessError();
	}

	public static void main(String[] args) throws Exception {
		JsonObject objAccData = (JsonObject) getJSONData("FourModulesData", "FourModulesDataDetails").get("FourModules_Menu");
		Set<String> arrKeys = objAccData.keySet();
		Iterator itr = arrKeys.iterator();
		while (itr.hasNext()) {
			String sKey = itr.next().toString();
			String sValue = objAccData.get(sKey).getAsString();
		}
	}

			
	
	
	

	public static JsonObject getJSONData(String sType, String sKey) throws Exception {
		JsonObject accObject = null;
		String sPath = "";
		switch (sType.toUpperCase()) {
		case "FOURMODULES":
			sPath = "src/test/resources/ExecutionData/FourModulesData.json";
			break;
		case "SPICEJET":
			sPath = "src/test/resources/ExecutionData/SpicejetData.json";
			break;
		default:
			Reporter.report("FAIL", "Invalid type " + sType);
		}
		try {
			JsonParser parser = new JsonParser();
			Object obj = parser.parse(new FileReader(sPath));
			JsonObject jsonObject = (JsonObject) obj;
			accObject = (JsonObject) jsonObject.get(sKey);
		} catch (Exception e) {
			Reporter.report("INFO", e.getMessage());
			Reporter.report("FAIL", "");
		}
		if (accObject == null) {
			Reporter.report("INFO", "Failed to find the " + sType + " data with key " + sKey + " in the " + sPath);
			Reporter.report("FAIL", "");
		}
		return accObject;
	}




}
