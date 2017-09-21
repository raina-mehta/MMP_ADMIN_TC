package com.mmp.admin_login;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class AdminHomePage {


	WebDriver driver;

	public boolean checkForAdminHome()
	{	
		System.out.println(driver.getCurrentUrl());  //get current url and compare
		if(driver.getCurrentUrl().contains("Admin-Build.2.1.000/index"))
		{
			System.out.println("logged in");
			return true;}
		else
		{
			System.out.println("not loggedin");
			return false ;}
	}


	public void clickOnUsers()
	{	
		WebDriverWait wait=new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Users"))).click(); // Click on Users tab
	}

	public boolean verifyForUsers()
	{
		WebDriverWait wait=new WebDriverWait(driver, 15);
		WebElement e=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/div[1]/div/div[2]/div[2]/div/div/div[2]/div/div[2]/div[1]/h1")));
		if(e.getText().equals("Search for patient by Status"))// verify the title 
			return true;
		else
			return false;
	}

	public AdminHomePage(WebDriver driver)
	{
		this.driver= driver;
	}



}
