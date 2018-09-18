package com.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Week {
    
    public static void main(String[] args){
	Calendar now = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
	
	//Calendar now = new GregorianCalendar(2013,2,28,13,24,56);

	System.out.println(now.get(Calendar.HOUR_OF_DAY));
	    System.out.println("Current date : " + (now.get(Calendar.MONTH) + 1) + "-"
	        + now.get(Calendar.DATE) + "-" + now.get(Calendar.YEAR));

	    String[] strDays = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thusday",
	        "Friday", "Saturday" };
	    // Day_OF_WEEK starts from 1 while array index starts from 0
	    System.out.println("Current day is : "+ now.get(Calendar.DAY_OF_WEEK) );
	    System.out.println("Sunday: "+Calendar.SUNDAY);
	    System.out.println("Saturday: "+Calendar.SATURDAY);
	    System.out.println("day: "+Calendar.DAY_OF_MONTH);
	    System.out.println("month: "+Calendar.MONTH);
	    
	    System.out.println("date: "+now.get(Calendar.YEAR)+"-"+(now.get(Calendar.MONTH)+1)+"-"+now.get(Calendar.DATE));
	    
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
	    System.out.println(dateFormat.format(new Date()));
	    
	    now.add(Calendar.DATE, 2);
	    System.out.println(dateFormat.format(now.getTime()));
	    
	    now.add(Calendar.DATE, -2);
	    
	    now.add(Calendar.DATE, 2);
	    System.out.println(dateFormat.format(now.getTime()));
	    
	    String date = "2018-04-26T22:59:00.000Z";
	    
	    String[] tmp = date.split("T");
	    System.out.println("***** "+tmp[0]);
	    
	    System.out.println(30%30);
    }

}
