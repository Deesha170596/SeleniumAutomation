package com.qa.tests;

import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.cogmento.qa.pageObject.CompaniesPage;
import com.cogmento.qa.pageObject.DashBoardPage;
import com.cogmento.qa.pageObject.LoginPage;
import com.qa.baseClass.BaseClass;
import com.qa.util.PropertiesOperations;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
@Listeners(com.qa.cogmento.listener.Listeners.class)
public class CompaniesPageTest extends BaseClass
{
	static DataFormatter formatter = new DataFormatter();
	DashBoardPage companiesmenuclick;
	CompaniesPage company;
	public static ExtentReports report;
	public static ExtentTest test;
	String uemail=PropertiesOperations.getPropertyValueByKey("email");
	String upwd=PropertiesOperations.getPropertyValueByKey("pwd");


	@BeforeTest
	public void setExtent()
	{
		initialization();
		report = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html", true);
		test=report.startTest("Companies Report");
		report.addSystemInfo("User Name", "Deesha Shetty");
		report.addSystemInfo("Environment", "QA");
	}


	@AfterTest
	public void TearDown()
	{
		driver.close();
		report.endTest(test);
		report.flush();
	}


	@DataProvider(name="test4data")
	public Object[][] getData1() throws IOException
	{
		String projectpath = System.getProperty("user.dir");
		String excelpath=projectpath+"\\src\\main\\java\\dataFiles\\TestData.xlsx";
		Object data[][]=testData(excelpath,"Companiestestinp");
		return data;
	}

	@Test(priority=1,dataProvider="test4data")
	public void companyPageTest(String companyname,String companywebsite,String streetaddress,String companycity,String companystate,String postalcode,String countflag,String companyindustry,String nofoemployees,String cpriority) throws InterruptedException, IOException
	{
		test.log(LogStatus.INFO, "User is navigating to login page");
		LoginPage login = new LoginPage();
		login.doLoginToApplication(uemail,upwd);
		companiesmenuclick = new DashBoardPage(driver);
		companiesmenuclick.CompaniesMenu();
		try
		{
			test.log(LogStatus.WARNING,"User is navigating to companies page");
			company= new CompaniesPage();
			company.addCompanyDetails(companyname,companywebsite,streetaddress,companycity,companystate,postalcode,countflag,companyindustry,nofoemployees,cpriority);
			test.log(LogStatus.PASS, "User updated comapny details successfully");
		}catch (Exception e)
		{
			test.log(LogStatus.FAIL,test.addScreenCapture(CaptureScreen(driver))+ "Company test Failed");
		}
	}
}