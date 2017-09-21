package com.mmp.admin_login;

import org.testng.annotations.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest
{
	WebDriver driver;
	HomePage hp;
	AdminLoginPage alp;
	AdminHomePage ahp;
	ForgotPasswordPage fpp;
	Utilities utl;
	
	@BeforeMethod
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver= new ChromeDriver();
		String url= "http://96.84.175.78/MMP-Release2-Integrated-Build.6.8.000/";
		driver.get(url);
		hp= new HomePage(driver);
		alp= new AdminLoginPage(driver);
		ahp= new AdminHomePage(driver);
		fpp= new ForgotPasswordPage(driver);
	}

	@Test(priority=0,dataProvider="credentials",testName = "TC50", description = "validate the Admin LOGIN form with all the valid values in the given fields") 
	public void logInWithValid(String uname, String pword)  
	{
		try{
			hp.clickOnAdminLogin();
			alp.enterUnamePword(uname, pword);
			alp.clickOnSignIn();
			assertTrue(ahp.checkForAdminHome());	// validate that the user is logged in
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Test(priority=1,dataProvider="credentials",testName = "TC51", description = "validate the Admin LOGIN form with all the invalid values in the given fields") 
	public void logInWithInvalid(String uname, String pword)  
	{
		try{
			hp.clickOnAdminLogin();
			alp.enterUnamePword(uname, pword);
			alp.clickOnSignIn();
			assertEquals(alp.validatePopup(), "Wrong username and password. "); //validate the error mesg
			System.out.println("Error: "+ alp.validatePopup());
			alp.handlePopup();
			assertFalse(ahp.checkForAdminHome());	// validate that the user is not logged in
			System.out.println("Test passed for: uname: "+uname+ " and pword: "+pword );
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	@Test(priority=2,testName = "TC52", description = "validate the Admin LOGIN form with the fields blank")
	public void logInWithBlank()
	{
		try{
			hp.clickOnAdminLogin();
			assertTrue(alp.unameRequired()); 	//assert if the User name is a required text box
			assertTrue(alp.pwordRequired());	//assert if the password is a required text box
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}


	@Test(priority=3, testName = "TC53", description = " validate the Forgot Password Link.")
	public void forgotPwordLink()
	{
		try{
			hp.clickOnAdminLogin();		
			alp.clickOnFrgtPword();		//click on forgot password link
			assertTrue(fpp.pwordPageCurrentUrl().contains("/forgetpassword.php"));
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}


	@Test(priority=4,testName = "TC54",dataProvider="credentials", description = "validate the Forgot Password functionality with valid values")
	public void forgotPwordValid(String email,String uname)
	{
		try 
		{
			hp.clickOnAdminLogin();
			alp.clickOnFrgtPword(); 		//click on forgot password link
			fpp.enterEmailAndClickSend(email);//no confirmation message to verify
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}


	@Test(priority=5, testName = "TC55", description = "validate the Forgot Password functionality with invalid values")
	public void forgotPwordInvalid()
	{
		hp.clickOnAdminLogin();
		alp.clickOnFrgtPword();		//click on forgot password link
		assertTrue(fpp.emailRequired());// to verify email is a required field
		/*The invalid value scope is not defined.		 
		 */
	}


	@AfterMethod	public void closeDriver()
	{
		driver.close();
	}



	@DataProvider(name="credentials")
	public Object[][] getDataFromDataprovider(Method m)
	{
		
		//ut.getExcelData(m.getName());
		
		
		try
		{
			if(m.getName().equalsIgnoreCase("loginwithValid"))
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
