package ExecutionRepo;

import java.io.Console;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import DriverRepo.CreateDriver;
import ReporterRepo.ReportManager;
import cucumber.api.cli.Main;

public class RunnerClass {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		String sFeaturePath = "";
		try (InputStream input = new FileInputStream("src/test/resources/runConfig.properties")) {
			Properties prop = new Properties();
			prop.load(input);
			sFeaturePath = prop.getProperty("FeaturePath");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		if (sFeaturePath.isEmpty()) {
			System.out.println("Feature file path is empty in the runConfig file");
		} else {
			String[] argv = new String[] { "-t", "~@manual", "-p", "pretty", "--plugin", "json:target/result/cucumber.json",
					"-g", "", sFeaturePath };
			ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
			byte exitstatus = Main.run(argv, contextClassLoader);
			ReportManager.reportCreate();
			CreateDriver.killInstance();
			Hooks.driver.quit();
			Hooks.driver = null;
			System.exit(exitstatus);
		}
	}

}
