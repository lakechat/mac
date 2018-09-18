package com.dateformat;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTest {

    private static DateFormat uiDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public static void main(String[] args){
	long mills = 1507156380000L;
	String date = formatDate(0);
	//System.out.println(date);
	
	String start_date = "2018-05-18 00:24:14";
	String end_date = "2018-05-30 00:24:18";
	try{
	    uiDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
	    Date start = uiDateFormat.parse(start_date);
	    Date end = uiDateFormat.parse(end_date);
	    
	    System.out.println(start.getTime());
	    System.out.println(end.getTime());
	}
	catch(Exception e){
	    
	}
	
	
    }
    
    private static String formatDate(long dateInMillis) {
	    Date date = new Date(dateInMillis);
	    return uiDateFormat.format(date);
	}
}
