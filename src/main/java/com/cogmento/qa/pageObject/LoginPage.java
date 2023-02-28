package com.cogmento.qa.pageObject;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.baseClass.BaseClass;
import com.qa.util.PropertiesOperations;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginPage extends BaseClass 
{
	public static ExtentTest test;
	static String uemail=PropertiesOperations.getPropertyValueByKey("email");
	static String upwd=PropertiesOperations.getPropertyValueByKey("pwd");
	//Initializing
	public LoginPage()
	{
		PageFactory.initElements(driver,this);
	} 

	//Actions
	public DashBoardPage doLoginToApplication(String emailId, String pwd) throws IOException
	{
		waitFor(10);
		//isElementPresent(email);
		doSendKeys(email_txt,emailId);
		waitFor(2);
		doSendKeys(password_txt,pwd);
		doClick(loginbtn);
		waitFor(3);
		System.out.println("Title is "+driver.getTitle());
		waitFor(1);
		String uname = username_txt.getText();
		System.out.println("User who logged in: "+uname);	
		return new DashBoardPage(driver);
	}
	public static void loginerror(String emailId, String pwd) throws IOException
	{
		if(emailId.isBlank() || pwd.isBlank())
		{
			System.out.println("One of the field is empty");
			test.log(LogStatus.FAIL,test.addScreenCapture(CaptureScreen(driver))+ "one of the input field is empty");	
		}
		else if(emailId!=uemail || pwd!=upwd)
		{
			System.out.println("Invalid Credentials");
			test.log(LogStatus.FAIL,test.addScreenCapture(CaptureScreen(driver))+ "Input provided is invalid");	
		}
	}
	public void frgtpwd(String forgotemailId) throws InterruptedException, IOException 
	{
		doClick(fgtpwd_btn);
		doSendKeys(frgtemail_txt,forgotemailId);
		doClick(resetpwd_btn);
		String Forgotpwdtext=resetpwdtext.getText();
		System.out.println(Forgotpwdtext);
	}


	//PageFactory for login
	@FindBy(xpath="//input[@name='email']")
	private WebElement email_txt;

	@FindBy(xpath="//input[@placeholder='Password']")
	private WebElement password_txt;

	@FindBy(css="div.ui.fluid.large.blue.submit.button")
	private WebElement loginbtn;

	@FindBy(css="div.ui.negative.message")
	private WebElement Errormsg_txt;

	@FindBy(css="div.header.item")
	private WebElement cogmentologo;	

	//PageFactory for forgot password
	@FindBy(xpath="//span[@class='user-display']")
	private  WebElement username_txt;

	@FindBy(xpath="//a[contains(text(),'Forgot your password?')]")
	private WebElement fgtpwd_btn;

	@FindBy(css="#email")
	private WebElement frgtemail_txt;

	@FindBy(xpath="//button[@name='action']")
	private WebElement resetpwd_btn;

	@FindBy(xpath="/html/body/div/div/div/div/div/form/div")
	private WebElement resetpwdtext;

	@FindBy(css="div.ui.negative.message")
	private WebElement errorMessage;
}
