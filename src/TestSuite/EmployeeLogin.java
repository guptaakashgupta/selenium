package TestSuite;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import PageObject.*;
import ExcelUtil.*;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;

public class EmployeeLogin implements Runnable {
	private Thread t;
	private String threadName;
	private String username;
	private String password;
	private final String homeUrl="http://198.11.247.44/valeant_uat";
	
	public static final Logger logger=LogManager.getLogger(EmployeeLogin.class.getName());
	
	public EmployeeLogin(String username,String password,String threadName)
	{
		this.username=username;
		this.password=password;
		this.threadName=threadName;
	}
	
	public void start()
	{
		System.out.println("Starting " +  threadName );
		logger.info("Starting "+threadName+" for "+username);
		
	      if (t == null)
	      {
	         t = new Thread (this, threadName);
	         t.start ();
	      }
	}
	public void run()
	{
		try
		{
			
			//Get the firefox driver
			WebDriver driver =new FirefoxDriver();
			logger.info("Firefox started "+threadName+" "+username);
			
			//Puts an Implicit wait, Will wait for 10 seconds before throwing exception
		    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		    
		    //Launch Website
		    driver.get(homeUrl);
		    logger.info("Home page opened "+threadName+" "+username);
		    
		    //Maximize the browser
		    driver.manage().window().maximize();
		    
		    PageObjectScorecard.usernameField(driver).clear();
		    PageObjectScorecard.usernameField(driver).sendKeys(username);
		    logger.info("Username Entered "+threadName+" "+username);
		    
		    PageObjectScorecard.passwordField(driver).clear();
		    PageObjectScorecard.passwordField(driver).sendKeys(password);
		    logger.info("Password Entered "+threadName+" "+username);
		    
		    PageObjectScorecard.loginButton(driver).click();
		    logger.info("User Logging in "+threadName+" "+username);
		    
		    if(PageObjectScorecard.personPositionDropdown(driver).isDisplayed())
		    	logger.info("User Logged in "+threadName+" "+username);
		    else
		    	logger.error("User Not Logged in "+threadName+" "+username);
		    
		    
		    
		    Thread.sleep(10000);
		    //close the browser
		    driver.close();
		    
		    System.out.println("Exiting:"+threadName+" "+username);
		    logger.info("Exiting:"+threadName+" "+username);
		    
		}catch(Exception e)
		{
			System.out.println(e);
			logger.error("Exception on "+threadName+" "+username+" message "+e);
		}
	    
	}
	
	//Login Employees simultaneously on portal
	public static void main(String[] arguments) {
		//input objects
		String inputExcel="C:\\Users\\akash.gupta\\Desktop\\export.xlsx";
		String excelSheetName="Export Worksheet";
		int testInput=3;
		
		//ArrayList of Threads
		ArrayList<EmployeeLogin> threads=new ArrayList<EmployeeLogin>();
		
		

		
		DOMConfigurator.configure("D://Java//ScorecardTest//src//log4j.xml");
		logger.info("##################################");
		logger.info("TEST Has Started");
		
		
		try
		{
			
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
				
				
				//(new EmployeeLogin(username,password,("Thread"+rowNumber))).start();
				threads.add(new EmployeeLogin(username, password, ("Thread"+rowNumber)));
				threads.get(rowNumber-1).start();
				
				
				rowNumber++;
				
				
				if(rowNumber==testInput) //Break after this much of inputs
					break;
			}
		
		}catch(Exception e)
		{
			System.out.println(e);
			logger.error("Exception on "+Thread.currentThread()+" message "+e);
		}
		
		try{
			//Wait for main thread to exit
			for(EmployeeLogin e:threads)
				e.t.join();
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		logger.info("##################################");
		logger.info("TEST Has Ended");
		
	}

}
