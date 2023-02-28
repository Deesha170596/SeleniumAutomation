package com.qa.tests;

import java.awt.AWTException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.cogmento.qa.pageObject.DashBoardPage;
import com.cogmento.qa.pageObject.DocumentsPage;
import com.cogmento.qa.pageObject.LoginPage;
import com.qa.baseClass.BaseClass;
import com.qa.util.PropertiesOperations;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DocumentPageTest extends BaseClass
{
	static DataFormatter formatter = new DataFormatter();
	DashBoardPage documentmenuclick;
	DocumentsPage Doc;
	public static ExtentReports report;
	public static ExtentTest test;
	String uemail=PropertiesOperations.getPropertyValueByKey("email");
	String upwd=PropertiesOperations.getPropertyValueByKey("pwd");


	@BeforeTest
	public void setExtent()
	{
		initialization();
		report = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html", true);
		test=report.startTest("Documents Report");
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


	@DataProvider(name="test6data")
	public Object[][] getData1() throws IOException
	{
		String projectpath = System.getProperty("user.dir");
		String excelpath=projectpath+"\\src\\main\\java\\dataFiles\\TestData.xlsx";
		Object data[][]=testData(excelpath,"Documentstestinp");
		return data;
	}


	@Test(priority= 1,dataProvider="test6data")
	public void documentPageTest(String DocumentTitle,String DocumentDescription) throws InterruptedException, IOException, AWTException
	{
		test.log(LogStatus.INFO, "User is navigating to login page");
		LoginPage login = new LoginPage();
		login.doLoginToApplication(uemail,upwd);
		documentmenuclick = new DashBoardPage(driver);
		documentmenuclick.DocumentsMenu();
		try
		{
			test.log(LogStatus.WARNING,"User is navigating to Document page");
			Doc= new DocumentsPage();
			Doc.adddocument(DocumentTitle,DocumentDescription);
			test.log(LogStatus.PASS, "User updated and upoaded Document successfully");
		}catch (Exception e)
		{
			test.log(LogStatus.FAIL,test.addScreenCapture(CaptureScreen(driver))+ "Document test Failed");
		}
	}	
}