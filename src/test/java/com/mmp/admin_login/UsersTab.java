package com.mmp.admin_login;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class UsersTab {

	WebDriver driver;


	public void selectStatus(String statusValue)
	{
		Select status= new Select(driver.findElement(By.id("search")));
		status.selectByVisibleText(statusValue);
	}

	public void selectPatient()
	{
		WebDriverWait wait=new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(.//a[contains(@href,'userprofile.php?pid')])[1]"))).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	public void changeStatus(String changeTo)
	{
		WebDriverWait wait= new WebDriverWait(driver, 15);
		Select status= new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sapproval"))));
		status.selectByVisibleText(changeTo);
	}

	public Boolean searchPatientList(String[] pDetails)
	{	
		WebDriverWait wait= new WebDriverWait(driver, 15);
		List<WebElement> patList= wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//*[contains(@href,'userprofile.php?pid')]")));
		String pName=pDetails[0];
		int count= patList.size();
		System.out.println("The list size is "+ count);
		Boolean result=false;
		
		for(int i=0;i<count;i++)
		{
			if(pName.equalsIgnoreCase(patList.get(i).getText()))
			{
				System.out.println("the patient is in the list");
				System.out.println("List patient "+patList.get(i).getText());
				System.out.println("pDetail "+pDetails[0] );
				result= true;
			}
			else
			{
				System.out.println("The patient is not in the list");
			}
		}
		return result;
	}
	public void handlePopup(){
		
		Alert a = driver.switchTo().alert();
		a.accept();
		
	}
	public String[] getPatDetails()
	{
		WebDriverWait wait= new WebDriverWait(driver, 15);
		String[]  details= {wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fname"))).getAttribute("value"),wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ssn"))).getAttribute("value")};
		return details;
	}

	public void clickSubmit() 
	{
		try {
			WebDriverWait wait= new WebDriverWait(driver, 15);
			Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@value='Submit']"))).click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public UsersTab(WebDriver driver)
	{	this.driver= driver;}


}
