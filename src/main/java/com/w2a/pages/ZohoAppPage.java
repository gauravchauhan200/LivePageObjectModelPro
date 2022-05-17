package com.w2a.pages;

import org.openqa.selenium.By;

import com.w2a.base.Page;
import com.w2a.pages.crm.CRMHomePage;

public class ZohoAppPage extends Page
{

	public CRMHomePage goToCRM()
	{
		click("crmlink_XPATH");
		return new CRMHomePage();
	}

	public void goToMail()
	{

	}

	public void goToMeeting()
	{

	}


}
