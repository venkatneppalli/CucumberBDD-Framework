package ReporterRepo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ReportManager {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		String sData = readFileAsString("target/result/cucumber.json");
		parseFromJSONResponse(sData);
		System.out.println("Successfully generated report");
	}

	static String finalResPath = "";

	public static void reportCreate() throws Throwable {
		String sData = readFileAsString("target/result/cucumber.json");
		parseFromJSONResponse(sData);
		System.out.println("Please find the execution result under: " + finalResPath);
	}

	/* Read the cucumber.json file as string */
	private static String readFileAsString(String fileName) throws Exception {
		String data = "";
		data = new String(Files.readAllBytes(Paths.get(fileName)));
		return data;
	}

	private static void parseFromJSONResponse(String respo) throws Throwable {
		JSONArray myjson;
		String ftData = "";
		try {
			myjson = new JSONArray(respo);
			for (int i = 0; i < myjson.length(); i++) {
				JSONObject jsonObjFeatureDtl = (JSONObject) myjson.get(i);
				String sFeature = jsonObjFeatureDtl.get("name").toString();
				if (i != 0) {
					ftData = ftData + "\n" + "reportData.push(" + getScenarioData(sFeature, jsonObjFeatureDtl) + ")";
				} else {
					ftData = "reportData.push(" + getScenarioData(sFeature, jsonObjFeatureDtl) + ")";
				}
			}
		} catch (JSONException e) {
			System.out.println(e.getMessage());
		}
		generateReport(ftData);
	}

	private static String getScenarioData(String sFeature, JSONObject scData) {
		JSONArray arrElements = scData.getJSONArray("elements");
		List<String> arrScenData = new ArrayList<String>();
		String featureData = "", stepData = "", scenarioData = "", scStatus = "SKIP", ftStatus = "SKIP";
		BigDecimal ftDuration = new BigDecimal(0);
		boolean bFtFail = false, bFtSkip = false, bFtPass = false;
		for (int i = 0; i < arrElements.length(); i++) {
			JSONObject jsonObjScenarioDtl = (JSONObject) arrElements.get(i);
			String sScenario = jsonObjScenarioDtl.get("name").toString();
			arrScenData.add(sScenario);
			stepData = getStepData(jsonObjScenarioDtl);
			JSONArray arrSteps = jsonObjScenarioDtl.getJSONArray("steps");
			BigDecimal scDuration = new BigDecimal(0);
			boolean bFail = false, bSkip = false, bPass = false;
			for (int iSt = 0; iSt < arrSteps.length(); iSt++) {
				JSONObject jsonObjStepsDtl = (JSONObject) arrSteps.get(iSt);
				JSONObject jsonObjStepRes = jsonObjStepsDtl.getJSONObject("result");
				String stDuration = jsonObjStepRes.getBigDecimal("duration").toString();
				String stStatus = jsonObjStepRes.getString("status");
				scDuration = scDuration.add(new BigDecimal(stDuration));
				switch (stStatus) {
				case "passed":
					stStatus = "PASS";
					bPass = true;
					break;
				case "failed":
					stStatus = "FAIL";
					bFail = true;
					break;
				case "skipped":
					stStatus = "SKIP";
					bSkip = true;
					break;
				}
			}
			if (bFail) {
				scStatus = "FAIL";
			} else if (bSkip) {
				scStatus = "SKIP";
			} else if (bPass) {
				scStatus = "PASS";
			}

			ftDuration = ftDuration.add(scDuration);
			switch (scStatus) {
			case "PASS":
				bFtPass = true;
				break;
			case "FAIL":
				bFtFail = true;
				break;
			case "SKIP":
				bFtSkip = true;
				break;
			}
			// Call the report generator here
			if (i != 0) {
				scenarioData = scenarioData + "," + "{ Name: '" + sScenario + "',Duration:'" + scDuration
						+ "', Status: '" + scStatus + "' , 'TestStep':[" + stepData + "]}";
			} else {
				scenarioData = "{ Name: '" + sScenario + "',Duration:'" + scDuration + "', Status: '" + scStatus
						+ "' , 'TestStep':[" + stepData + "]}";
			}
		}
		if (bFtFail) {
			ftStatus = "FAIL";
		} else if (bFtSkip) {
			ftStatus = "SKIP";
		} else if (bFtPass) {
			ftStatus = "PASS";
		}
		featureData = "{ Name: '" + sFeature + "',ExceDate: '"
				+ new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()) + "',Duration:'" + ftDuration
				+ "', Status: '" + ftStatus + "' , 'ScenarioDtl':[" + scenarioData + "]}";
		return featureData;
	}

	private static String getStepData(JSONObject stepData) {
		JSONArray arrSteps = stepData.getJSONArray("steps");
		String stepDataReturn = "";
		for (int i = 0; i < arrSteps.length(); i++) {
			JSONObject jsonObjStepsDtl = (JSONObject) arrSteps.get(i);
			String sStep = jsonObjStepsDtl.get("name").toString();
			String sOutput = "";
			try {
				JSONArray arrOutput = jsonObjStepsDtl.getJSONArray("output");
				for (int iOut = 0; iOut < arrOutput.length(); iOut++) {
					if (iOut != 0) {
						sOutput = sOutput + "#" + arrOutput.get(iOut).toString();
					} else {
						sOutput = arrOutput.get(iOut).toString();
					}
				}
			} catch (JSONException je) {

			}
			JSONObject jsonObjStepRes = jsonObjStepsDtl.getJSONObject("result");
			String sDuration = jsonObjStepRes.getBigDecimal("duration").toString();
			String sStatus = jsonObjStepRes.getString("status");
			switch (sStatus) {
			case "passed":
				sStatus = "PASS";
				break;
			case "failed":
				sStatus = "FAIL";
				break;
			case "skipped":
				sStatus = "SKIP";
				break;
			}
			if (i != 0) {
				stepDataReturn = stepDataReturn + "," + "{'Label':'when','Name':'" + sStep + "','Duration':'"
						+ sDuration + "','Report':'" + sOutput + "','Status':'" + sStatus + "'}";
			} else {
				stepDataReturn = "{'Label':'when','Name':'" + sStep + "','Duration':'" + sDuration + "','Report':'"
						+ sOutput + "','Status':'" + sStatus + "'}";
			}
		}
		return stepDataReturn;
	}

	private static void generateReport(String ftScriptData) throws Exception {
		String resultPath = "";
		try (InputStream input = new FileInputStream("src/test/resources/runConfig.properties")) {
			Properties prop = new Properties();
			prop.load(input);
			resultPath = prop.getProperty("ReportPath");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String sResultSheetName = "ResultSheet_" + now.getYear() + "_" + now.getMonthValue() + "_" + now.getDayOfMonth()
				+ "_" + now.getHour() + "_" + now.getMinute() + "_" + now.getSecond();

		File htmlTemplateFile = new File("src/test/java/ReporterRepo/ReportTemplate.html");
		String htmlString = FileUtils.readFileToString(htmlTemplateFile, "UTF8");
		String title = "New Page";
		htmlString = htmlString.replace("$ReportData", ftScriptData);
		finalResPath = resultPath + "/" + sResultSheetName + ".html";
		File newHtmlFile = new File(finalResPath);
		FileUtils.writeStringToFile(newHtmlFile, htmlString, "UTF8");
	}
}
