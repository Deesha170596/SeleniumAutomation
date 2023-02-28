package com.qa.tests;
import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.cogmento.qa.pageObject.LoginPage;
import com.qa.baseClass.BaseClass;
import com.qa.util.PropertiesOperations;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ForgotPasswordPageTest extends BaseClass
{
	public static ExtentReports report;
	public static ExtentTest test;
	String uemail=PropertiesOperations.getPropertyValueByKey("email");
	String upwd=PropertiesOperations.getPropertyValueByKey("pwd");


	@BeforeTest
	public void setExtent()
	{
		initialization();
		report = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html", true);
		test=report.startTest("Forgotpassword Report");
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

	@Test(priority=1)
	public void forgetPasswordTest() throws InterruptedException, IOException
	{
		try
		{
			test.log(LogStatus.INFO, "User is navigating to ForgotPassword page");
			LoginPage forgotpwd = new LoginPage();
			forgotpwd.frgtpwd(uemail);
			test.log(LogStatus.PASS, "User updated password successfully");
		}catch (Exception e) 
		{
			test.log(LogStatus.FAIL,test.addScreenCapture(CaptureScreen(driver))+ "ForgotPassword test Failed");
		}
	}			
}