package com.w2a.utilities;

import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;



//Not Under use for Now
public class TestLogs
{
	public static Logger log = Logger.getLogger(TestLogs.class.getName());

	public static void main(String[] args)
	{

		Date d=new Date();
		System.setProperty("current.date",d.toString().replace(":","_").replace(" ","_"));

		PropertyConfigurator.configure("./src/test/resources/com/w2a/properties/log4j.properties");
		log.info("jkkdkdhb");
		log.debug("jksdhk");
	}


}
