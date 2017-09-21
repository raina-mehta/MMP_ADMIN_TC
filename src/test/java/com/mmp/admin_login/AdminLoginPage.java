package com.mmp.admin_login;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminLoginPage {
	WebDriver driver;
	com.mmp.admin_login.Utilities utl;
	
	public void enterUnamePword(String username, String password)
	{	
		WebDriverWait wait= new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).clear();//locate username textbox
		driver.findElement(By.id("username")).sendKeys(username);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).clear();//locate password textbox
		driver.findElement(By.id("password")).sendKeys(password);
	}

	public void handlePopup(){

		Alert a = driver.switchTo().alert();
		a.accept();// accept popup
	}

	public void clickOnSignIn()
	{
		WebDriverWait wait= new WebDriverWait(driver, 15);// locate and click on "Sign in"
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='login1']/p[6]/input"))).click();
	}

	public String validatePopup()
	{
		Alert a = driver.switchTo().alert();
		return a.getText(); // get text from popup
	}

	public void clickOnFrgtPword()
	{
		WebDriverWait wait= new WebDriverWait(driver, 15);
		//click on forgot username password link
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("forgot Username / Password"))).click();
	}
	
	public Boolean unameRequired()
	{
		utl= new Utilities(driver);
	WebDriverWait wait= new WebDriverWait(driver, 10);
	//locate username
	WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
		return utl.eleRequired(e);// return true if the web element has attribute"required"
	}

	public Boolean pwordRequired()
	{
		utl= new Utilities(driver);
		WebDriverWait wait= new WebDriverWait(driver, 10);
		//locate password
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
		return utl.eleRequired(e);// return true if the web element has attribute"required"
		
	}
	
	
	public AdminLoginPage(WebDriver driver)
	{
		this.driver= driver;
	}

}
