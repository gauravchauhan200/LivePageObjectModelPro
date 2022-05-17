package com.w2a.pages;

import org.openqa.selenium.By;

import com.w2a.base.Page;

public class HomePage extends Page
{

	public void goToSignUp()
	{
		click("signUp_XPATH");
	}

	public LoginPage goToLogin()
	{
		click("loginlink_XPATH");
		return new LoginPage();
	}


	public void goToContactSales()
	{
		driver.findElement(By.cssSelector("a[class='zh-contact']")).click();
	}

	public void goToSupport()
	{
		driver.findElement(By.cssSelector("a[class='zh-support']")).click();
	}

	public void goToCustomers()
	{
		driver.findElement(By.cssSelector("a[class='zh-customers']")).click();
	}

	public void validFooterLink()
	{
		driver.findElement(By.cssSelector(""));
	}

}
