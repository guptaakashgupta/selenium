package PageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PageObjectScorecard {
	
	private static WebElement element=null;
	private static final int  waitSeconds=40;
	
	//login page username textbox
	public static WebElement usernameField(WebDriver driver)
	{
		element=new WebDriverWait(driver,waitSeconds).until(ExpectedConditions.presenceOfElementLocated(By.id("it1::content")));

		//element=driver.findElement(By.id("it1::content"));
		
		return element;

	}
	
	//login page password textbox
	public static WebElement passwordField(WebDriver driver)
	{
		element=new WebDriverWait(driver, waitSeconds).until(ExpectedConditions.presenceOfElementLocated(By.id("it2::content")));

		//element=driver.findElement(By.id("it2::content"));
		
		return element;
	}
	
	//Login Button on login page
	public static WebElement loginButton(WebDriver driver)
	{
		element=new WebDriverWait(driver, waitSeconds).until(ExpectedConditions.presenceOfElementLocated(By.id("b1")));

		//element=driver.findElement(By.id("b1"));
		
		return element;
	}
	
	//Payout shown on communications tab after login
	public static WebElement PersonPayout(WebDriver driver)
	{
		
		return driver.findElement(By.id("pt:mr:0:r1:0:g3"));
	}
	
	//Month Year object on communications tab after login
	public static WebElement monthName(WebDriver driver)
	{
		element=new WebDriverWait(driver, waitSeconds).until(ExpectedConditions.presenceOfElementLocated(By.id("pt:mr:0:r1:0:of2")));

		//element=driver.findElement(By.id("pt:mr:0:r1:0:of2"));
		
		return element;
	}
	//Drop down at top right corner when user is logged in (having options change password and logout
	public static WebElement personPositionDropdown(WebDriver driver)
	{
		element=new WebDriverWait(driver, waitSeconds).until(ExpectedConditions.presenceOfElementLocated(By.className("x158")));

		//element=driver.findElement(By.className("x158"));
		
		return element;
	}
	
	//Sign out option on top right corner of page 
	public static WebElement signOutOption(WebDriver driver)
	{
		element=new WebDriverWait(driver, waitSeconds).until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='pt:cmi1']/td[2]")));

		//element=driver.findElement(By.xpath(".//*[@id='pt:cmi1']/td[2]"));
		
		return element;
	}
	
	//call reports option on communications tab after login
	public static WebElement callReportLink(WebDriver driver)
	{
		element=driver.findElement(By.id("pt:mr:0:r1:0:numTime1"));
		
		return element;
	}
	
	//IC Reports Link on sidebar
	public static WebElement icReportLink(WebDriver driver)
	{
		element=driver.findElement(By.id("pt:mr:0:l7"));
		
		return element;
	}
	
	//Ic Scorecard Link on IC Reports Page
	public static WebElement icScorecardLink(WebDriver driver)
	{
		element=driver.findElement(By.id("pt:mr:0:r1:0:j_id-1798455283_47e3d22e:0:sdi2::disAcr"));
		
		return element;
	}
	
	//click on view link of scorecard for a communication
	public static WebElement icViewLink(WebDriver driver)
	{
		element=driver.findElement(By.id("pt:mr:0:r1:0:j_id-1798455283_47e3d22e:0:t3:1:l98"));
		
		return element;
	}
	
	//click on details tab in IC reports
	public static WebElement icDetailsLink(WebDriver driver)
	{
		element=driver.findElement(By.id("pt:mr:0:r1:1:sdi1::disAcr"));
		//element=driver.findElement(By.id("pt:mr:0:r1:1:tab1::disAcr"));
		
		return element;
	}
	
	
}
