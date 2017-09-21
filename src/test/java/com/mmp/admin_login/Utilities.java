package com.mmp.admin_login;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Utilities {

	WebDriver driver;

	public boolean eleRequired(WebElement e)
	{
		WebDriverWait wait= new WebDriverWait(driver, 10);
		if((wait.until(ExpectedConditions.visibilityOf(e)).getAttribute("required")).isEmpty())
			return false;
		else
			System.out.println("The element is required");
		return true;
	}


	@Test
	public String[][] getExcelData() {
		String sheetName= "loginwithInvalid";
		String[][] arrayExcelData = null;
		try {
			FileInputStream fs = new FileInputStream("C:\\Users\\Rajneesh Mehta\\Desktop\\H2Kinfosys\\Eclipse neon\\MMP\\Admin LogIn Data");
			Workbook wb = Workbook.getWorkbook(fs);
			Sheet sh = wb.getSheet(sheetName);

			int totalNoOfCols = sh.getColumns();
			int totalNoOfRows = sh.getRows();

			arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols];

			for (int i= 1 ; i < totalNoOfRows; i++) {

				for (int j=0; j < totalNoOfCols; j++) {
					arrayExcelData[i-1][j] = sh.getCell(j, i).getContents();
				}

			}

			//to print the arry

			for(int i = 0; i<totalNoOfRows; i++)
				for(int j = 0; j<totalNoOfCols; j++)
					arrayExcelData[i][j] = null;

			for(int i = 0; i<totalNoOfRows; i++)
			{
				for(int j = 0; j<totalNoOfCols; j++)
				{
					System.out.print(arrayExcelData[i][j]);
				}
				System.out.println();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		}
		return arrayExcelData;
	}


	public Utilities(WebDriver driver)
	{
		this.driver= driver;

	}


}
