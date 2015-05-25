package TestSuite;


import java.util.List;
import java.util.concurrent.TimeUnit;

import ExcelUtil.*;
import PageObject.*;


import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.chrome.ChromeDriver;




public class Scorecard {

	public static void main(String[] args) {
		
		String homeUrl="http://198.11.247.44/valeant_uat";
		String inputExcel="C:\\Users\\akash.gupta\\Desktop\\export.xlsx";
		String excelSheetName="Export Worksheet";
		
		try
		{
			//Get the firefox driver
			WebDriver driver =new FirefoxDriver();
			
			//Puts an Implicit wait, Will wait for 10 seconds before throwing exception
		    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		    
		    //Launch Website
		    driver.get(homeUrl);
		    
		    //Maximize the browser
		    driver.manage().window().maximize();
		    
			
			//Read the excel (input file)
			ExcelUtils excel=new ExcelUtils(inputExcel,excelSheetName);
			
			
			int numberOfRows; //number of rows in excel
			int rowNumber=1;  //start from ist row of excel
			int colNumber=1;  //column number can be 0 or 1 (i.e. username and password)
			
			numberOfRows=excel.excel_get_rows();
			System.out.println(numberOfRows);
			
			while(rowNumber<numberOfRows)
			{
				String username=excel.getCellDataasstring(rowNumber,colNumber);
				String password=excel.getCellDataasstring(rowNumber, colNumber+1);
				
				System.out.println("row:"+rowNumber+" "+colNumber+" "+username+" "+password);
				rowNumber++; 
				
				PageObjectScorecard.usernameField(driver).clear();
			    PageObjectScorecard.usernameField(driver).sendKeys(username);
			    
			    PageObjectScorecard.passwordField(driver).clear();
			    PageObjectScorecard.passwordField(driver).sendKeys(password);
			    
			    PageObjectScorecard.loginButton(driver).click();
			    
			    //List<WebElement> list=PageObjectScorecard.PersonPayout(driver).findElements(By.tagName("text"));
			    
			    //System.out.println(list.get(2).getText());
			    
			    
			    //System.out.println(PageObjectScorecard.monthName(driver).getText());
				
			    PageObjectScorecard.personPositionDropdown(driver).click();
			    
			    PageObjectScorecard.signOutOption(driver).click();
				
			}
			
			//Get the user credentials
			//String username=excel.getCellDataasstring(1, 0);
			//String password=excel.getCellDataasstring(1, 1);
			
			//System.out.println(username);
			//System.out.println(password);
			
			/*
			//Get the firefox driver
			WebDriver driver =new FirefoxDriver();
			//System.setProperty("webdriver.chrome.driver", "C:\\Users\\akash.gupta\\Downloads\\chromedriver_win32\\chromedriver.exe");
			//WebDriver driver =new ChromeDriver();
			
			//Puts an Implicit wait, Will wait for 10 seconds before throwing exception
		    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		    
		    //Launch Website
		    driver.get(homeUrl);
		    
		    //Maximize the browser
		    driver.manage().window().maximize();
		    
		    //Use Page object library
		    
		    PageObjectScorecard.usernameField(driver).clear();
		    PageObjectScorecard.usernameField(driver).sendKeys(username);
		    
		    PageObjectScorecard.passwordField(driver).clear();
		    PageObjectScorecard.passwordField(driver).sendKeys(password);
		    
		    PageObjectScorecard.loginButton(driver).click();
		    
		    
		    List<WebElement> list=PageObjectScorecard.PersonPayout(driver).findElements(By.tagName("text"));
		    
		    System.out.println(list.get(2).getText());
		    
		    
		    System.out.println(PageObjectScorecard.monthName(driver).getText());
		    
		    
		    
		    //PageObjectScorecard.callReportLink(driver).click();
		    
		    //PageObjectScorecard.icReportLink(driver).click();
		    
		    //PageObjectScorecard.icScorecardLink(driver).click();
		    
		    //PageObjectScorecard.icViewLink(driver).click();
		    
		    //Thread.sleep(1000000); 
		    
		    //PageObjectScorecard.icDetailsLink(driver).click();
		    
		    PageObjectScorecard.personPositionDropdown(driver).click();
		    
		    PageObjectScorecard.signOutOption(driver).click();
		    
		    
		    //Close the Browser.
		    driver.close();
		    
		    */
			driver.close();
		    
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
