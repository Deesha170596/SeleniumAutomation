package com.qa.util;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonFunctions 
{
	protected static WebDriver driver;
	static DataFormatter formatter = new DataFormatter();

	//Static Wait
	public static void waitFor(int value)
	{
		try {
			Thread.sleep(value*1000);
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}


	public  void doClick(WebElement element)
	{
		try {
			element.click();
		}catch (NoSuchElementException e) {
			System.out.println("Element not found");
		}catch (ElementClickInterceptedException e) {
			System.out.println("Selenium Click was not able to click on element");
		}

	}

	public void doSendKeys(WebElement locators,String text)
	{
		locators.clear();
		try
		{
			locators.sendKeys(text);
		}catch (ElementNotInteractableException e) {
			System.out.println("element is not interactable");
		}
	}

//	protected boolean isElementPresent(WebElement ele) {
//	try{
//	driver.findElement((By) ele);
//	return true;
//	}
//	catch(Exception e)
//	{
//	return false;
//	}
//	}

	//For Dropdown list using select class
	protected void selectByText(WebElement element,String text)
	{
		Select select= new Select(element);
		try {
			select.selectByVisibleText(text);
		}catch(InvalidSelectorException e)
		{
			System.out.println("Selector is incorrect or syntactically invalid");
		}
	}
	protected  void selectByIndex(WebElement selcmds, int i)
	{
		Select sel=new Select(selcmds);
		try {
			sel.selectByIndex(i);
		}catch(InvalidSelectorException e)
		{
			System.out.println("Selector is incorrect or syntactically invalid");
		}

	}

	public void acceptAlert(WebDriver driver)
	{
		try {
			driver.switchTo().alert().accept();	
		}catch (NoAlertPresentException e) {
			System.out.println("No alert was present");
		}
	}

	public WebElement waitForElement(WebElement elementName, long waitTimeInSeconds)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(waitTimeInSeconds));
		WebElement element=wait.until(ExpectedConditions.elementToBeClickable(elementName));
		return element;
	}



	public static void nonSelectDropdown(List<WebElement> element, String text)
	{
		for(int i=0;i<element.size();i++)
		{
			System.out.println(element.get(i).getText());
			if(element.get(i).getText().equalsIgnoreCase(text))
			{
				element.get(i).click();
				break;
			}
		}
	}

	//For capturing failure screenshot
	public static String CaptureScreen(WebDriver driver) throws IOException
	{
		File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File Destinationfile = new File("src//..//images"+System.currentTimeMillis()+".png");
		String absolutepath_screen=Destinationfile.getAbsolutePath();	
		FileUtils.copyFile(srcfile, Destinationfile);
		return absolutepath_screen;
	}	

	//For reading data from excel
	public static Object[][] testData(String Excelpath,String sheetName) throws IOException
	{
		try (XSSFWorkbook wb = new XSSFWorkbook(Excelpath))
		{
			XSSFSheet sheet = wb.getSheet(sheetName);
			int rowCount=sheet.getPhysicalNumberOfRows();
			XSSFRow row=sheet.getRow(0);
			int colCount=row.getLastCellNum();
			Object data[][]=new Object[rowCount-1][colCount];
			for(int i=0;i<rowCount-1;i++)
			{
				row=sheet.getRow(i+1);
				for(int j=0;j<colCount;j++)
				{
					XSSFCell cell=row.getCell(j);
					data[i][j]=formatter.formatCellValue(cell);
				}
			}
			return data;
		}
	}
	
	
	public void waitForWebElementToAppear(WebElement findby)
	{
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findby));
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	@FindBy(css="div.ui.negative.message")
	private WebElement errorMessage;
}