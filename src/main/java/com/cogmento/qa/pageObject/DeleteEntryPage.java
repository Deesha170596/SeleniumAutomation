package com.cogmento.qa.pageObject;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.baseClass.BaseClass;

public class DeleteEntryPage extends BaseClass
{

	public DeleteEntryPage()
	{
		PageFactory.initElements(driver,this);
	}

	public void deleteEntry() throws IOException
	{
		waitFor(1);
		doClick(contacts_btn);
		doClick(deleteallcheckbox);
		doClick(purgeselected);
		doClick(clickOk_btn);
		waitFor(2);
	}	

	//page factory
	@FindBy(xpath="//div/div[2]/div/div[2]/a[1]")
	private WebElement contacts_btn;
	@FindBy(xpath="//th//div[@class='ui fitted checkbox']")
	private WebElement deleteallcheckbox;
	@FindBy(xpath="//div[2]/div/button[contains(text(),'Purge Selected')]")
	private WebElement purgeselected;
	@FindBy(xpath="//button[contains(text(),'OK')]")
	private WebElement clickOk_btn;
}
