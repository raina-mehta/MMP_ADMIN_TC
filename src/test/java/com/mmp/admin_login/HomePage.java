package com.mmp.admin_login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

	WebDriver driver;
	public void clickOnAdminLogin()
	{
		WebDriverWait wait= new WebDriverWait(driver, 15); //click on admin login button
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='welcome']/div/div[2]/a[1]"))).click();
	}

	public HomePage(WebDriver driver)
	{
		this.driver= driver;
	}

}
