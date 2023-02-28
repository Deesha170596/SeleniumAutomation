package com.cogmento.qa.pageObject;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.baseClass.BaseClass;

public class DocumentsPage extends BaseClass
{
	public DocumentsPage()
	{
		PageFactory.initElements(driver,this);
	}
	
	public void adddocument(String doctitle,String docdescription) throws AWTException, IOException 
	{
		waitFor(1);
		doClick(document_btn);
		doClick(docfolder);
		doClick(createdoc_btn);
		String doccreatepage=docpage_txt.getText();
		System.out.println(doccreatepage+"page is successfully displayed");
		Assert.assertEquals(doccreatepage,"Create new document in IMP");
		doSendKeys(doctitile_txt,doctitle);
		System.out.println(doctitle);
		doClick(accessd);
		doSendKeys(descriptiond_txt,docdescription);
		System.out.println(docdescription);
		doClick(fileupload);
		waitFor(1);
		action("C:\\Users\\DeeshaDShetty\\Downloads\\RahulShettyAcademy_Q&A List (1).pdf");
		doClick(savedoc_btn);
		waitFor(6);
		
	}
	
	public void action(String xpath) throws AWTException
	{
		Robot robot=new Robot();
		robot.delay(2000);
		StringSelection ss= new StringSelection(xpath);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.delay(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		waitFor(1);
	}
	
	//page factory
	@FindBy(xpath="//span[contains(text(),'Documents')]")
	private WebElement document_btn;	
	@FindBy(xpath="//a[contains(text(),'IMP')]")
	private WebElement docfolder;	
	@FindBy(xpath="//button[contains(text(),'Create')]")
	private WebElement createdoc_btn;
	@FindBy(css="div.ui.header.item.mb5.light-black")
	private WebElement docpage_txt;
	@FindBy(xpath="//input[@name='title']")
	private WebElement doctitile_txt;	
	@FindBy(xpath="//form/div[2]/div[1]/div/div/div[1]/button")
	private WebElement accessd;	
	@FindBy(xpath="//textarea[@name='description']")
	private WebElement descriptiond_txt;	
	@FindBy(xpath="//input[@name='file']")
	private WebElement fileupload;	
	@FindBy(xpath="//button[contains(text(),'Save')]")
	private WebElement savedoc_btn;
	@FindBy(css="div.ui.header.item.mb5.light-black")
	private WebElement documenttitle1_txt;
}
