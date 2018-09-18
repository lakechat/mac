package com.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConvertMillsToDate {
    private static final SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");
    private static final Logger logger = LogManager.getLogger(ConvertMillsToDate.class);
    
    public static void main(String[] args){
	Long firstOccurred = 1533710183423L;
	Long lastOccurred = 1533710683423L;
	
	if(convertMillsToDate(firstOccurred) != null){
	    System.out.println(convertMillsToDate(firstOccurred));
	}
	
	if(convertMillsToDate(lastOccurred) != null){
	    System.out.println(convertMillsToDate(lastOccurred));
	}
    }
    
    private static String convertMillsToDate(Long timestamp){
	String result = null;
	try{
	    result = convertMillisToDate(timestamp,simpleFormat);
	    
	}catch(Exception e){
	    logger.error("convert mills to date exception: "+e.getMessage());
	}
	return result;
    }
    
    private static String convertMillisToDate(long dateInMillis, DateFormat dateFormat) {
        Date date = new Date(dateInMillis);
        return dateFormat.format(date);
    }


}
