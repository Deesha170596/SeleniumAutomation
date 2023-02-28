package com.cogmento.qa.pageObject;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.baseClass.BaseClass;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TasksPage extends BaseClass
{
	public static ExtentTest test;
	public TasksPage()
	{
		PageFactory.initElements(driver,this);
	}

	public void addtask(String TaskTitle,String Taskdescription,String taskcompletion) throws IOException
	{
		waitFor(1);
		doClick(createTasks_btn);
		String Taskpage=Tskpage_txt.getText();
		System.out.println(Taskpage+"page is successfully displayed");
		doSendKeys(Title_txt,TaskTitle);
		doClick(accesst);
		test.log(LogStatus.FAIL,test.addScreenCapture(CaptureScreen(driver))+ "Mandatory field should not be blank");
		doClick(type);
		nonSelectDropdown(typesel_dd,"General Support");
		doClick(DueDate);
		nonSelectDropdown(Dueday_dd,"23");
		nonSelectDropdown(Duetime_dd,"20:00");
		doClick(closeDate);
		nonSelectDropdown(closeday_dd,"23");
		nonSelectDropdown(closetime_dd,"21:00");
		doSendKeys(descriptiont_txt,Taskdescription);
		doSendKeys(completion_txt,taskcompletion);
		doClick(priorityt_btn);
		nonSelectDropdown(priorityclickt_dd,"High");
		doClick(statust);
		nonSelectDropdown(statusclick_dd,"Enquiring");
		doClick(savetask_btn);
		waitFor(2);
		if(TaskTitle.isBlank())
		{
			String msg = errmsg.getText();
			System.out.println(msg);
		}
		String task1title = tasktitle1_txt.getText();
		System.out.println("Title of Task event is: "+task1title);
	}

	//page factory
	@FindBy(xpath="//button[contains(text(),'Create')]")
	private WebElement createTasks_btn;
	
	@FindBy(css="div.ui.header.item.mb5.light-black")
	private WebElement Tskpage_txt;
	
	@FindBy(xpath="//input[@name='title']")
	private WebElement Title_txt;
	
	@FindBy(xpath="//button[contains(text(),'Public')]")
	private WebElement accesst;
	
	@FindBy(xpath="//div[@name='type']")
	private WebElement type;
	
	@FindBy(xpath="//form/div[2]/div[2]/div/div/div[2]//div")
	private List<WebElement> typesel_dd;
	
	@FindBy(xpath="//div[3]/div[1]/div/div/div/input")
	private WebElement DueDate;	
	
	@FindBy(xpath="//div[@class='react-datepicker__week']//div")
	private List<WebElement> Dueday_dd;
	
	@FindBy(xpath="//li[@class='react-datepicker__time-list-item ']")
	private List<WebElement> Duetime_dd;
	
	@FindBy(xpath="//form/div[5]/div[1]/div/div/div")
	private WebElement closeDate;	
	
	@FindBy(xpath="//div[@class='react-datepicker__week']//div")
	private List<WebElement> closeday_dd;
	
	@FindBy(xpath="//li[@class='react-datepicker__time-list-item ']")
	private List<WebElement> closetime_dd;
	
	@FindBy(xpath="//textarea[@name='description']")
	private WebElement descriptiont_txt;
	
	@FindBy(xpath="//input[@name='completion']")
	private WebElement completion_txt;	
	
	@FindBy(xpath="//label[contains(text(),'Priority')]")
	private WebElement priorityt_btn;
	
	@FindBy(xpath="//form/div[7]/div[1]/div/div/div[2]//div")
	private List<WebElement> priorityclickt_dd;
	
	@FindBy(xpath="//div[@name='status' and @role='listbox']")
	private WebElement statust;
	
	@FindBy(xpath="//form/div[7]/div[2]/div/div/div[2]//div")
	private List<WebElement> statusclick_dd;
	
	@FindBy(xpath="//button[contains(text(),'Save')]")
	private WebElement savetask_btn;
	
	@FindBy(css="div.ui.header.item.mb5.light-black")
	private WebElement tasktitle1_txt;
	
	@FindBy(css="span.inline-error-msg")
	private WebElement errmsg;
}