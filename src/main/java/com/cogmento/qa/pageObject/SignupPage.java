package com.cogmento.qa.pageObject;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.baseClass.BaseClass;

public class SignupPage extends BaseClass 
{
	//Initializing
	public SignupPage()
	{
		PageFactory.initElements(driver,this);
	} 

	public void signingup(String Emailid,String Phonenum) throws InterruptedException, IOException 
	{
		doClick(signup_btn);
		waitFor(1);
		doSendKeys(email_txt,Emailid);
		doSendKeys(mobileno_txt,Phonenum);
		doClick(terms);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(recaptcha));
		WebElement captchachk = wait.until(ExpectedConditions.elementToBeClickable(recaptchaclk));
		Actions action =new Actions(driver);
		action.moveToElement(captchachk).click().build().perform();
		waitFor(5);
		driver.switchTo().defaultContent();
		waitFor(2);
		doClick(signupbtn_btn);
	}


	//PageFactory
	@FindBy(xpath="//a[contains(text(),'Sign Up')]")
	private WebElement signup_btn;

	@FindBy(css="#email")
	private WebElement email_txt;

	@FindBy(css="#phone_number")
	private WebElement mobileno_txt;

	@FindBy(css="#terms")
	private WebElement terms;

	@FindBy(xpath="//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")
	private WebElement recaptcha;

	@FindBy(css="div.recaptcha-checkbox-checkmark")
	private WebElement recaptchaclk;

	@FindBy(css="button.ui.fluid.large.blue.submit.button")
	private WebElement signupbtn_btn;
}