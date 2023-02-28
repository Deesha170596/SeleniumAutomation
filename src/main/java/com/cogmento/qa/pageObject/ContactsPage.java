package com.cogmento.qa.pageObject;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.baseClass.BaseClass;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ContactsPage extends BaseClass
{
	public static ExtentTest test;
	public ContactsPage()
	{
		PageFactory.initElements(driver,this);
	}

	public void addContactDetails(String fname,String lname,String mname,String loginemail,String streetaddress,String streetcity,String streetstate,String streetzip,String mobilenum) throws InterruptedException, IOException
	{
		waitFor(1);
		doClick(addcontact_btn);
		String contactcreatepage1=contctcreatepage_txt.getText();
		System.out.println(contactcreatepage1+"page is successfully displayed");
		doSendKeys(fnamec_txt,fname);
		doSendKeys(lnamec_txt,lname);
		if(fname.isBlank() || lname.isBlank())
		{
			String msg = errmsg.getText();
			System.out.println(msg);
			test.log(LogStatus.FAIL,test.addScreenCapture(CaptureScreen(driver))+ "Mandatory field should not be blank");
		}
		doSendKeys(mnamec_txt,mname);
		doSendKeys(pemailc_txt,loginemail);
		doClick(statusdd);
		doClick(selectstatusdd);
		doClick(categorydd);
		doClick(selectcategorydd);
		doSendKeys(streetaddressc_txt,streetaddress);
		doSendKeys(streetcityc_txt,streetcity);
		doSendKeys(streetstatec_txt,streetstate);
		doSendKeys(streetzipc_txt,streetzip);
		doClick(countryc);
		nonSelectDropdown(flagc,"India");
		doClick(phonec);
		nonSelectDropdown(phonecountry,"India");
		doSendKeys(mobileno_txt,mobilenum);
		doClick(savecont_btn);
		waitFor(3);
		String contacttitle= contacttitle1_txt.getText();
		System.out.println("Contact main title is: "+contacttitle);
	}

	//page factory
	@FindBy(xpath="//a//button[@class='ui linkedin button']")
	private WebElement addcontact_btn;
	@FindBy(css="div.ui.header.item.mb5.light-black")
	private WebElement contctcreatepage_txt;
	@FindBy(xpath="//input[@name='first_name']")
	private WebElement fnamec_txt;
	@FindBy(xpath="//input[@name='last_name']")
	private WebElement lnamec_txt;
	@FindBy(xpath="//input[@name='middle_name']")
	private WebElement mnamec_txt;
	@FindBy(xpath="//input[@placeholder='Email address']")
	private WebElement pemailc_txt;
	@FindBy(xpath="//div[@name='status' and @role='listbox']")
	private WebElement statusdd;
	@FindBy(xpath="//div//span[contains(text(),'Active')]")
	private WebElement selectstatusdd;
	@FindBy(xpath="//div[@name='category' and @role='listbox']")
	private WebElement categorydd;
	@FindBy(xpath="//div//span[contains(text(),'Customer')]")
	private WebElement selectcategorydd;	
	@FindBy(xpath="//input[@placeholder='Street Address']")
	private WebElement streetaddressc_txt;
	@FindBy(xpath="//input[@name='city']")
	private WebElement streetcityc_txt;
	@FindBy(xpath="//input[@name='state']")
	private WebElement streetstatec_txt;
	@FindBy(xpath="//input[@name='zip']")
	private WebElement streetzipc_txt;
	@FindBy(xpath="//div[@name='country']")
	private WebElement countryc;
	@FindBy(xpath="//div[5]/div/div[2]//div")
	private List<WebElement> flagc;
	@FindBy(xpath="//div[7]/div[2]/div/div/div/div[1]")
	private WebElement phonec;
	@FindBy(xpath="//div[7]/div[2]/div/div/div/div[1]")
	private List<WebElement> phonecountry;
	@FindBy(xpath="//input[@placeholder='Number']")
	private WebElement mobileno_txt;
	@FindBy(xpath="//button[contains(text(),'Save')]")
	private WebElement savecont_btn;
	@FindBy(xpath="//div[2]/div/div[1]/div/div[7]/div[2]/div")
	private WebElement check;
	@FindBy(css="div.ui.header.item.mb5.light-black")
	private WebElement contacttitle1_txt;
	@FindBy(xpath="//span[@class='inline-error-msg']")
	private WebElement errmsg;

}
