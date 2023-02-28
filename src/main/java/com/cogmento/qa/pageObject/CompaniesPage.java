package com.cogmento.qa.pageObject;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.baseClass.BaseClass;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CompaniesPage extends BaseClass
{
	public static ExtentTest test;
	public CompaniesPage()
	{
		PageFactory.initElements(driver,this);
	}
	
	public void addCompanyDetails(String compname,String compwebsite,String streetadd,String compcity,String compstate,String postcode,String countflag,String compindustry,String nofoemp,String cpriority) throws IOException 
	{
		waitFor(1);
		doClick(createCompanies_btn);
		String compcreatepage1=compcreatepage.getText();
		System.out.println(compcreatepage1+"page is successfully displayed");
		doSendKeys(companyname_txt,compname);
		if(compname.isBlank())
		{
			String msg = errmsg.getText();
			System.out.println(msg);
			test.log(LogStatus.FAIL,test.addScreenCapture(CaptureScreen(driver))+ "Mandatory field should not be blank");
		}
		doClick(access_btn);
		doSendKeys(website,compwebsite);
		doClick(websiteFetch);
		doSendKeys(streetaddress_txt,streetadd);
		doSendKeys(city_txt,compcity);
		doSendKeys(state_txt,compstate);
		doSendKeys(postalcode_txt,postcode);
		doClick(country);
		nonSelectDropdown(flag_dd,countflag);
		doSendKeys(industry_txt,compindustry);
		doSendKeys(noofemp_txt,nofoemp);
		doClick(priorityclick);
		nonSelectDropdown(priority_dd,cpriority);
		doClick(Save_btn);
		waitFor(3);
		String companytitle= compmaintitle1_txt.getText();
		System.out.println("Company main title is: "+companytitle);
	}
	
//page factory
	@FindBy(xpath="//button[contains(text(),'Create')]")
	private WebElement createCompanies_btn;
	@FindBy(css="div.ui.header.item.mb5.light-black")
	private WebElement compcreatepage;
	@FindBy(xpath="//form/div[1]/div[1]/div/div/div/input")
	private WebElement companyname_txt;
	@FindBy(xpath="//button[@class='ui small fluid positive toggle button']")
	private WebElement access_btn;
	@FindBy(xpath="//input[@name='url']")
	private WebElement website;
	@FindBy(xpath="//button[contains(text(),'Fetch')]")
	private WebElement websiteFetch;
	@FindBy(xpath="//input[@name='address']")
	private WebElement streetaddress_txt;
	@FindBy(xpath="//input[@name='city']")
	private WebElement city_txt;
	@FindBy(xpath="//input[@name='state']")
	private WebElement state_txt;
	@FindBy(xpath="//input[@name='zip']")
	private WebElement postalcode_txt;
	@FindBy(xpath="//div[@name='country']")
	private WebElement country;
	@FindBy(xpath="//div[5]/div/div[2]//div")
	private List<WebElement> flag_dd;
	@FindBy(xpath="//input[@name='industry']")
	private WebElement industry_txt;
	@FindBy(xpath="//input[@name='num_employees']")
	private WebElement noofemp_txt;
	@FindBy(xpath="//div[1][@name='priority']")
	private WebElement priorityclick;
	@FindBy(xpath="//div[@name='priority']")
	private List<WebElement> priority_dd;
	@FindBy(xpath="//button[contains(text(),'Save')]")
	private WebElement Save_btn;
	@FindBy(css="div.ui.header.item.mb5.light-black")
	private WebElement compmaintitle1_txt;
	@FindBy(xpath="//span[@class='inline-error-msg']")
	private WebElement errmsg;
}