package ExecutionRepo.StepDefinition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;

public class GlobalData {
	private static String AccountNumber, ContactNumber, OpportunityNumber, OpportunityName, LeadNumber,ProjectName,AccountTxt;
	private static String AccountName;
	private static String DuplicateAccountMessage;
	private static String FirstName, LastName, CompanyName;
	private static List<String> arrMandTextbox, arrNonMandTextbox, arrMandDropdown, arrNonMandDrodpwn;
	private static String sCurrentStep,sCurrentFeature;
	private static Map<String, String> Dic = new HashMap<String, String>();
	private static boolean isLoggedOut;
	
	public static Map<String, String> getDic() {
		return Dic;
	}

	public static void setDic(Map<String, String> dic) {
		Dic = dic;
	}

	public static String getCurrentStep() {
		return sCurrentStep;
	}

	public static void setCurrentStep(String sCurrentStep) {
		GlobalData.sCurrentStep = sCurrentStep;
	}

	public String getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}

	public String getContactNumber() {
		return ContactNumber;
	}

	public void setContactNumber(String contactNumber) {
		ContactNumber = contactNumber;
	}

	public String getOpportunityNumber() {
		return OpportunityNumber;
	}

	public void setOpportunityNumber(String opportunityNumber) {
		OpportunityNumber = opportunityNumber;
	}

	public static String getDuplicateAccountMessage() {
		return DuplicateAccountMessage;
	}

	public static void setDuplicateAccountMessage(String duplicateAccountMessage) {
		DuplicateAccountMessage = duplicateAccountMessage;
	}

	public static String getAccountName() {
		return AccountName;
	}

	public static void setAccountName(String accountName) {
		AccountName = accountName;
	}

	public static String getLeadNumber() {
		return LeadNumber;
	}

	public static void setLeadNumber(String leadNumber) {
		LeadNumber = leadNumber;
	}

	public static String getFirstName() {
		return FirstName;
	}

	public static void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public static String getLastName() {
		return LastName;
	}

	public static void setLastName(String lastName) {
		LastName = lastName;
	}

	public static String getCompanyName() {
		return CompanyName;
	}

	public static void setCompanyName(String companyName) {
		CompanyName = companyName;
	}

	public static List<String> getArrMandTextbox() {
		return arrMandTextbox;
	}

	public static void setArrMandTextbox(List<String> arrMandTextbox) {
		GlobalData.arrMandTextbox = arrMandTextbox;
	}

	public static List<String> getArrNonMandTextbox() {
		return arrNonMandTextbox;
	}

	public static void setArrNonMandTextbox(List<String> arrNonMandTextbox) {
		GlobalData.arrNonMandTextbox = arrNonMandTextbox;
	}

	public static List<String> getArrMandDropdown() {
		return arrMandDropdown;
	}

	public static void setArrMandDropdown(List<String> arrMandDropdown) {
		GlobalData.arrMandDropdown = arrMandDropdown;
	}

	public static List<String> getArrNonMandDrodpwn() {
		return arrNonMandDrodpwn;
	}

	public static void setArrNonMandDrodpwn(List<String> arrNonMandDrodpwn) {
		GlobalData.arrNonMandDrodpwn = arrNonMandDrodpwn;
	}

	public static String getOpportunityName() {
		return OpportunityName;
	}

	public static void setOpportunityName(String opportunityName) {
		OpportunityName = opportunityName;
	}

	public static String getsCurrentFeature() {
		return sCurrentFeature;
	}

	public static void setsCurrentFeature(String sCurrentFeature) {
		GlobalData.sCurrentFeature = sCurrentFeature;
	}

	public static boolean isLoggedOut() {
		return isLoggedOut;
	}

	public static void setLoggedOut(boolean isLoggedOut) {
		GlobalData.isLoggedOut = isLoggedOut;
	}
	
	public static void setProjectName(String projectname) {
		ProjectName=projectname;
	}
	
	public String getProjectName() {
		return ProjectName;
	}
	
	public static void setAccountTxt(String accounttxt) {
		AccountTxt=accounttxt;
	}
}
