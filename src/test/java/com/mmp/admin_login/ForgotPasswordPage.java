package com.mmp.admin_login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordPage {

	WebDriver driver;
	Utilities utl; 

	public String pwordPageCurrentUrl()
	{
		return driver.getCurrentUrl();
	}

	public void enterEmailAndClickSend(String email)
	{
		WebDriverWait wait= new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email"))).sendKeys(email);//enter email
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".login.button>input"))).click();// click send
	}

	public boolean emailRequired()
	{
		utl= new Utilities(driver);
		WebDriverWait wait= new WebDriverWait(driver, 10);
		//locate email  
		WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		return utl.eleRequired(e);// return true if the web element has attribute"required"
	}

	public ForgotPasswordPage(WebDriver driver)
	{
		this.driver= driver;
	}

}
