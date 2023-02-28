package com.qa.tests;

import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cogmento.qa.pageObject.SignupPage;
import com.qa.baseClass.BaseClass;

public class SignupTest extends BaseClass 
{

	static DataFormatter formatter = new DataFormatter();

	@BeforeMethod
	public void setExtent()
	{
		initialization();
	}


	@DataProvider(name="test8data")
	public Object[][] getData() throws IOException {
		String projectpath = System.getProperty("user.dir");
		String excelpath=projectpath+"\\src\\main\\java\\dataFiles\\TestData.xlsx";
		Object data[][]=testData(excelpath,"signupInput");
		return data;
	}

	@Test(dataProvider="test8data")
	public void dosignup(String Emailid,String Phonenum) throws InterruptedException, IOException
	{
		SignupPage sgnup=new SignupPage();
		sgnup.signingup(Emailid,Phonenum);
	}


	@AfterMethod
	public void TearDown(){
		teardown();
	}
}
