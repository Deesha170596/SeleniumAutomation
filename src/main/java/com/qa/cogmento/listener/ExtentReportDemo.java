package com.qa.cogmento.listener;

import org.testng.annotations.BeforeMethod;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportDemo {
	@BeforeMethod
	public static ExtentReports getReportObject()
	{
		String path = System.getProperty("user.dir")+"//reports//cogmentoresult.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Deesha Shetty");
		extent.createTest(path);
		return extent;
}
}