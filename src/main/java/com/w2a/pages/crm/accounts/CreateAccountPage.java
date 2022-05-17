package com.w2a.pages.crm.accounts;

import com.w2a.base.Page;

public class CreateAccountPage extends Page
{

	public void createAccount(String accountname) {

		type("accountname_XPATH",accountname);
	//	driver.findElement(By.xpath("//input[@id='Crm_Accounts_ACCOUNTSITE_LInput']")).sendKeys();
	}

}
