package com.qa.tests;

import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.cogmento.qa.pageObject.CalendarPage;
import com.cogmento.qa.pageObject.DashBoardPage;
import com.cogmento.qa.pageObject.LoginPage;
import com.qa.baseClass.BaseClass;
import com.qa.util.PropertiesOperations;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
@Listeners(com.qa.cogmento.listener.Listeners.class)
public class CalendarPageTest extends BaseClass
{
	static DataFormatter formatter = new DataFormatter();
	DashBoardPage CalendarMenuClick;
	CalendarPage calendar;
	public static ExtentReports report;
	public static ExtentTest test;
	String uemail=PropertiesOperations.getPropertyValueByKey("email");
	String upwd=PropertiesOperations.getPropertyValueByKey("pwd");


	@BeforeTest
	public void setExtent() throws IOException
	{
		initialization();
		report = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html", true);
		test=report.startTest("Calendar Report");
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


	@DataProvider(name="test2data")
	public Object[][] getData1() throws IOException
	{
		String projectpath = System.getProperty("user.dir");
		String excelpath=projectpath+"\\src\\main\\java\\dataFiles\\TestData.xlsx";
		Object data[][]=testData(excelpath,"Calendarinp");
		return data;
	}
	

	@Test(priority=1,dataProvider="test2data")
	public void calendarPageTest(String CalendarTitle,String CalendarDesc,String CalendarLoc,String Alerttime,String Alertvia) throws IOException
	{
		test.log(LogStatus.INFO, "User is navigating to login page");
		LoginPage login=new LoginPage();
		login.doLoginToApplication(uemail,upwd);
		test.log(LogStatus.INFO,"Calendar test case validation started");
		try
		{
			test.log(LogStatus.WARNING,"User is navigating to calendar page");
			CalendarMenuClick= new DashBoardPage(driver);
			CalendarMenuClick.CalendarMenu();		
			calendar = new CalendarPage();
			calendar.addDetailsToCalendar(CalendarTitle,CalendarDesc,CalendarLoc,Alerttime,Alertvia);
		}catch (Exception e) 
		{
			test.log(LogStatus.FAIL,test.addScreenCapture(CaptureScreen(driver))+ "Calendar test Failed");
		}
		test.log(LogStatus.INFO,"Calendar test case validation Ended");
	}	
}
