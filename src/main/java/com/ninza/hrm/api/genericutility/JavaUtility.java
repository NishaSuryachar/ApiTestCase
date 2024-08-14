package com.ninza.hrm.api.genericutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

	public int getRandomNum()
	{
		Random ran=new Random();
		int ranDom = ran.nextInt(5000);
		return ranDom;
	}
	
	public String getSystemData()
	{
		Date dateObj=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sim.format(dateObj);
		return startDate;
	}
	
	public String getRequiredDate(int days)
	{
		Date dateObj=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sim.format(dateObj);
		
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
	    String endDate = sim.format(cal.getTime());
		return endDate;
	}
	
}
