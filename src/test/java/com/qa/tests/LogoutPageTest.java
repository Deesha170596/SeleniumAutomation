package com.qa.tests;

import java.io.IOException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.cogmento.qa.pageObject.DashBoardPage;
import com.cogmento.qa.pageObject.LoginPage;
import com.qa.baseClass.BaseClass;
import com.qa.util.PropertiesOperations;


public class LogoutPageTest extends BaseClass
{
	String uemail=PropertiesOperations.getPropertyValueByKey("email");
	String upwd=PropertiesOperations.getPropertyValueByKey("pwd");
	
	@BeforeMethod
	public void  setup()
	{
		initialization();
	}
	
	
	
	@Test(priority=1)
	public void logOutPageTest() throws InterruptedException, IOException {
		LoginPage login = new LoginPage();
		login.doLoginToApplication(uemail,upwd);
		DashBoardPage signout= new DashBoardPage(driver);
		signout.doLogout();	
	}
	
	
	@AfterMethod
	public void close() {
		teardown();
	}
}
