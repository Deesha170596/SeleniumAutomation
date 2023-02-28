package com.qa.tests;

import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.cogmento.qa.pageObject.LoginPage;
import com.qa.baseClass.BaseClass;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

@Listeners(com.qa.cogmento.listener.Listeners.class)
public class LoginPageTest extends BaseClass
{
	static DataFormatter formatter = new DataFormatter();
	LoginPage login;
	public static ExtentReports report;
	public static ExtentTest test;


	@BeforeTest
	public void setExtent()
	{
		initialization();
		login = new LoginPage();
		report = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html", true);
		test=report.startTest("Login Report");
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


	@DataProvider(name="test1data")
	public Object[][] getData() throws IOException
	{
		String projectpath = System.getProperty("user.dir");
		String excelpath=projectpath+"\\src\\main\\java\\dataFiles\\TestData.xlsx";
		Object data[][]=testData(excelpath,"LoginInput");
		return data;
	}


	@Test(priority=1,dataProvider="test1data",description="verifying login page")
	public void loginPageTest(String Username,String Password) throws InterruptedException, IOException{
		test.log(LogStatus.INFO,"Login test case validation started");
		try
		{
			test.log(LogStatus.WARNING,"User is navigating to login page");
			login.doLoginToApplication(Username, Password);
			LoginPage.loginerror(Username, Password);
			test.log(LogStatus.PASS, "User logged in successfully");
		}catch (Exception e)
		{
			test.log(LogStatus.FAIL,test.addScreenCapture(CaptureScreen(driver))+ "Login test Failed");
		}
	}
}