package com.mmp.admin_login;

import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UsersTest 
{
	WebDriver driver;
	HomePage hp;
	AdminLoginPage alp;
	AdminHomePage ahp;
	ForgotPasswordPage fpp;
	UsersTab ut;

	@BeforeSuite
	public void setUp()
	{
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\Rajneesh Mehta\\Desktop\\Selenium\\chromedriver\\chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver= new ChromeDriver();
		String url= "http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/";
		driver.get(url);
		hp= new HomePage(driver);
		alp= new AdminLoginPage(driver);
		ahp= new AdminHomePage(driver);
		fpp= new ForgotPasswordPage(driver);
		ut= new UsersTab(driver);


	} 
	@Test(priority=0,testName = "TC56", description = "",dataProvider="credentials")
	public void usersLink(String username, String password)
	{
		try 
		{
			hp.clickOnAdminLogin(); 				//click on admin login
			alp.enterUnamePword(username, password);//enter username,password
			alp.clickOnSignIn();
			ahp.clickOnUsers();						 //click on users tab
			assertTrue(ahp.verifyForUsers());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	@Test(priority=1,testName= "TC57",description = "")
	public void viewPatByStatus()
	{
		try 
		{
			ut.selectStatus("Accepted");
			System.out.println("status selected");
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	@Test(priority= 2)
	public void changeStatusValid() throws InterruptedException
	{
		try {
			ut.selectPatient();					//select a patient
			String[] pDetails=ut.getPatDetails();//get the patient details
			ut.changeStatus("Rejected");		//change the patient status
			ut.clickSubmit();
			ut.handlePopup();
			ut.selectStatus("Rejected");		//verify the changed status
			Thread.sleep(3000);
			assertTrue(ut.searchPatientList(pDetails));
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	@Test(priority= 3)
	public void changeStatusBlank() throws InterruptedException
	{
		try {
			ut.selectStatus("Rejected");		
			ut.selectPatient();					//select a patient
			String[] pDetails=ut.getPatDetails();//get the patient details
			ut.clickSubmit();
			ut.handlePopup();
			ut.selectStatus("Rejected");		//verify no change in status
			Thread.sleep(3000);
			assertTrue(ut.searchPatientList(pDetails));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	@AfterSuite
	public void closeDriver()
	{
		driver.close();
	}


	@DataProvider(name="credentials")
	public Object[][] getDataFromDataprovider(Method m)
	{

		try
		{
			if(m.getName().equalsIgnoreCase("loginwithValid")||m.getName().equalsIgnoreCase("usersLink"))
			{
				return new Object[][] 
						{{ "samjones", "Samjones123" }};
			}
			if(m.getName().equalsIgnoreCase("loginwithInvalid"))
			{
				return new Object[][] 
						{{ "samjones", "India" },{ "swmod", "Samjones123" },{ "charlotte", "USA" }};
			}
			if (m.getName().equalsIgnoreCase("loginWithBlank"))
			{
				return new Object[][] 
						{{ "samjones", "" },{ "", "Samjones123"},	{ "","" }};
			}
			if (m.getName().equalsIgnoreCase("forgotPwordValid"))
			{
				return new Object[][] 
						{{ "samjones@gmail.com", "samjones" }};
			}
			if (m.getName().equalsIgnoreCase("forgotPwordInvalid"))
			{
				return new Object[][] 
						{{ "qwert", "samjones" }};
			} 
		}
		catch(Exception e)
		{e.printStackTrace();}
		return null;
	}


}
