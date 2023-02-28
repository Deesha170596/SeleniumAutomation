package com.cogmento.qa.pageObject;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.baseClass.BaseClass;

public class DashBoardPage extends BaseClass
{

	public DashBoardPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	public void CalendarMenu() throws IOException
	{
		waitFor(1);
		doClick(addcalendar_btn);
		String CTitle=caltitle_txt.getText();
		System.out.println("The Menu Clicked is: "+CTitle);
		waitFor(2);
	}

	public void ContactsMenu() throws IOException
	{
		waitFor(1);
		doClick(contact_btn);
		String ContTitle=contcttitle_txt.getText();
		System.out.println("The Menu Clicked is: "+ContTitle);
		waitFor(2);
	}

	public void CompaniesMenu() throws IOException
	{
		waitFor(1);
		doClick(Companies_btn);
		String CompTitle=compnytitle_txt.getText();
		System.out.println("The Menu Clicked is: "+CompTitle);
		waitFor(2);
	}

	public void TasksMenu() throws IOException
	{
		waitFor(1);
		doClick(Tasks_btn);
		String taskTitle=tsktitle_txt.getText();
		System.out.println("The Menu Clicked is: "+taskTitle);
		waitFor(2);
	}

	public void DocumentsMenu() throws IOException
	{
		waitFor(1);
		doClick(document_btn);
		String DocumentTitle=doctitle_txt.getText();
		System.out.println("The Menu Clicked is: "+DocumentTitle);
		waitFor(2);
	}

	public void RubbishBin() throws IOException
	{
		waitFor(1);
		doClick(deletebutton);
		waitFor(2);
	}

	public void doLogout() throws InterruptedException, IOException 
	{
		waitFor(2);
		doClick(settings_btn);
		nonSelectDropdown(settingoptn,"Log Out");
		waitFor(5);
	}

	//page factory
	@FindBy(css="div.ui.header.item.mb5.light-black")
	private WebElement caltitle_txt;

	@FindBy(xpath="//a//span[contains(text(),'Calendar')]")
	private WebElement addcalendar_btn;

	@FindBy(css="div.ui.header.item.mb5.light-black")
	private WebElement contcttitle_txt;

	@FindBy(xpath="//span[contains(text(),'Contacts')]")
	private WebElement contact_btn;

	@FindBy(css="div.ui.header.item.mb5.light-black")
	private WebElement compnytitle_txt;

	@FindBy(xpath="//span[contains(text(),'Companies')]")
	private WebElement Companies_btn;

	@FindBy(css="div.ui.header.item.mb5.light-black")
	private WebElement tsktitle_txt;

	@FindBy(xpath="//span[contains(text(),'Tasks')]")
	private WebElement Tasks_btn;

	@FindBy(css="div.ui.header.item.mb5.light-black")
	private WebElement doctitle_txt;

	@FindBy(xpath="//span[contains(text(),'Documents')]")
	private WebElement document_btn;

	@FindBy(css="button.ui.basic.button.item")
	private WebElement deletebutton;

	@FindBy(xpath="//div[@role='listbox']")
	private WebElement settings_btn;

	@FindBy(xpath="//*[@id=\"top-header-menu\"]/div[2]/div[2]/div/div//a")
	private List<WebElement> settingoptn;
}
