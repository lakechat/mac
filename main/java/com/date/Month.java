package com.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Month {
    
    public static void main(String[] args){
	try{
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse("11/02/2017");

        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  

        System.out.println("First Day Of Month : " + calendar.getActualMinimum(Calendar.DAY_OF_MONTH));  
        System.out.println("Last Day of Month  : " + calendar.getActualMaximum(Calendar.DAY_OF_MONTH)); 
	}
	catch(Exception e){
	    
	}
    }

}
