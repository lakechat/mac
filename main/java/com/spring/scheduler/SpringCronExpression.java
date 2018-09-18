package com.spring.scheduler;

import java.util.Date;
import java.util.TimeZone;

import org.springframework.scheduling.support.CronSequenceGenerator;

public class SpringCronExpression {
    
    public static void main(String[] args){
	 String startExp = "0 30 1 ? * TUE";//"0 30 1 ? * TUE";
	    String endExp = "0 30 2 ? * FRI";
	    String expr = "0 0/5 1 LW * ?";
	    
	   // System.out.println(CronSequenceGenerator.isValidExpression(expr));
	    
	    TimeZone timeZone = TimeZone.getTimeZone("America/Los_Angeles");
	    timeZone = TimeZone.getDefault();
	    
	    Date current = new Date(System.currentTimeMillis());
	    
	    CronSequenceGenerator cronStart = new CronSequenceGenerator(startExp,timeZone );
	    CronSequenceGenerator cronEnd = new CronSequenceGenerator(endExp, timeZone);
	    
	    Date nextStart = cronStart.next(current);
	    
	    
	    Date nextEnd = cronEnd.next(nextStart);
	    
	   System.out.println("next start: "+nextStart);
	   System.out.println(" next end: "+nextEnd);
    }
   

}
