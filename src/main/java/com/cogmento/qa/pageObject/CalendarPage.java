package com.cogmento.qa.pageObject;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.baseClass.BaseClass;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CalendarPage extends BaseClass
{
	public static ExtentTest test;
	public CalendarPage()
	{
		PageFactory.initElements(driver,this);
	}

	public void addDetailsToCalendar(String calendartitle,String calendardesc,String calendarloc,String Alerttime,String Alertvia) throws IOException
	{
		waitFor(1);
		doClick(create_btn); 
		String calcreatepage=calpage_txt.getText();
		System.out.println(calcreatepage+"page is successfully displayed");
		doSendKeys(title_txt,calendartitle);
		doClick(startdate);
		nonSelectDropdown(startday,"23");
		nonSelectDropdown(starttime,"04:00");
		doClick(enddate);
		doClick(nextmonth);
		nonSelectDropdown(endday,"23");
		nonSelectDropdown(endtime,"05:15");
		doClick(category);
		doClick(selcategory);	
		doSendKeys(description_txt,calendardesc);
		doSendKeys(location_txt,calendarloc);
		doClick(enableallday);	
		doClick(alertclk);
		nonSelectDropdown(setalert,Alerttime);
		doClick(clkalertvia);
		nonSelectDropdown(setalertvia,Alertvia);
		doClick(savecalendar_btn);
		if(!savecalendar_btn.isEnabled())
		{
			System.out.println("Please provide mandatory field, Cannot create Calendar event");
			test.log(LogStatus.FAIL,test.addScreenCapture(CaptureScreen(driver))+ "Mandatory field is Blank");
		}
		waitFor(2);
		String caleventtitle = calendartitle1_txt.getText();
		System.out.println("Title of Calendar event is: "+caleventtitle);
	}

	//Page Factory	
	@FindBy(xpath="//a//button[contains(text(),'Create')]")
	private WebElement create_btn;
	
	@FindBy(css="div.ui.header.item.mb5.light-black")
	private WebElement calpage_txt;
	
	@FindBy(xpath="//input[@name='title']")
	private WebElement title_txt;	
	
	@FindBy(xpath="//input[@class='calendarField']")
	private WebElement startdate;
	
	@FindBy(xpath="//button//span[contains(text(),'Next Month')]")
	private WebElement nextmonth;	
	
	@FindBy(xpath="//div[@class='react-datepicker__week']//div")
	private List<WebElement> startday;
	
	@FindBy(xpath="//ul[@class='react-datepicker__time-list']//li")
	private List<WebElement> starttime;
	
	@FindBy(xpath="//div/div[2]/form/div[2]/div[2]/div/div")
	private WebElement enddate;	
	
	@FindBy(xpath="//div[@class='react-datepicker__week']//div")
	private List<WebElement> endday;
	
	@FindBy(xpath="//ul[@class='react-datepicker__time-list']//li")
	private List<WebElement> endtime;
	
	@FindBy(xpath="//div[@name='category'  and @role='listbox']")
	private WebElement category;
	
	@FindBy(xpath="//div//span[contains(text(),'Meeting')]")
	private WebElement selcategory;
	
	@FindBy(xpath="//textarea[@name='description']")
	private WebElement description_txt;
	
	@FindBy(xpath="//textarea[@name='location']")
	private WebElement location_txt;	
	
	@FindBy(xpath="//div/div[2]/form/div[5]/div[1]/div/div/label")
	private WebElement enableallday;
	
	@FindBy(xpath="//div[@name='minutesBefore']")
	private WebElement alertclk;	
	
	@FindBy(xpath="//div[@name='minutesBefore']//div")
	private List<WebElement> setalert;
	
	@FindBy(xpath="//div[@name='channels']")
	private WebElement clkalertvia;	
	
	@FindBy(xpath="//div[@name='channels']//div")
	private List<WebElement> setalertvia;
	
	@FindBy(xpath="//button[contains(text(),'Save')]")
	private WebElement savecalendar_btn;
	
	@FindBy(xpath="//div[@class='ui header item mb5 light-black']")
	private WebElement calendartitle1_txt;
}