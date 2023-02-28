package com.qa.tests;

import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.cogmento.qa.pageObject.ContactsPage;
import com.cogmento.qa.pageObject.DashBoardPage;
import com.cogmento.qa.pageObject.LoginPage;
import com.qa.baseClass.BaseClass;
import com.qa.util.PropertiesOperations;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class ContactPageTest extends BaseClass
{
	static DataFormatter formatter = new DataFormatter();
	DashBoardPage contactmenuclick;
	ContactsPage ContactP;
	public static ExtentReports report;
	public static ExtentTest test;
	String uemail=PropertiesOperations.getPropertyValueByKey("email");
	String upwd=PropertiesOperations.getPropertyValueByKey("pwd");
	
	
	@BeforeTest
	public void setExtent()
	{
		initialization();
		report = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html", true);
		test=report.startTest("Contact Report");
		report.addSystemInfo("User Name", "Deesha Shetty");
		report.addSystemInfo("Environment", "QA");

	}

	
	@AfterTest
	public void TearDown(){
		driver.close();
		report.endTest(test);
		report.flush();
	}

	
	@DataProvider(name="test3data")
	public Object[][] getData1() throws IOException {
		String projectpath = System.getProperty("user.dir");
		String excelpath=projectpath+"\\src\\main\\java\\dataFiles\\TestData.xlsx";
		Object data[][]=testData(excelpath,"Contactinp");
		return data;
	}

	
	@Test(priority=1,dataProvider="test3data")
	public void contactPageTest(String FirstName,String LastName,String MiddleName,String Email,String StreetAddress,String StreetCity,String StreetState,String StreetZip,String MobileNumber) throws InterruptedException, IOException
	{
		test.log(LogStatus.INFO, "User is navigating to login page");
		LoginPage login = new LoginPage();
		login.doLoginToApplication(uemail,upwd);
		contactmenuclick = new DashBoardPage(driver);
		contactmenuclick.ContactsMenu();
		try
		{
			test.log(LogStatus.WARNING,"User is navigating to contact page");
			ContactP= new ContactsPage();
			ContactP.addContactDetails(FirstName,LastName,MiddleName,Email,StreetAddress,StreetCity,StreetState,StreetZip,MobileNumber);
			test.log(LogStatus.PASS, "User updated contact successfully");
		}catch (Exception e) 
		{
			test.log(LogStatus.FAIL,test.addScreenCapture(CaptureScreen(driver))+ "Contact test Failed");
		}
	}
}
