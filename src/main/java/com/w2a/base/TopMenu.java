package com.w2a.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.w2a.pages.crm.accounts.AccountsPage;

public class TopMenu
{

	/*
	 *
	 * TopMenu ISA Page  ???
	 *
	 * HomePage HASA TopMenu
	 * AccountPage HASA TopMenu
	 *
	 * Page HASA TopMenu
	 *
	 *
	 */

	WebDriver driver;

	public TopMenu(WebDriver driver)			//we want driver in this class from PageClass so we used constructor
	{
		this.driver=driver;
	}



	public void gotoHome() {

	}

	public void gotoLeads() {

	}

	public void gotoContacts() {

	}

	public AccountsPage gotoAccounts() {

		Page.click("accountstab_XPATH");
		return new AccountsPage();
	}

	public void gotoDeals() {

	}

	public void gotoTasks() {

	}

	public void gotoMeetings() {

	}

	public void gotoCalls() {

	}

	public void gotoProjects() {

	}

	public void signOut() {

	}

}
