package com.spring.scheduler;

import java.util.Date;
import java.util.TimeZone;

import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.scheduling.support.CronSequenceGenerator;

public class QuatzCronExpression {

    public static void main(String[] args){
	try{
	    String startExp = "0 30 0 ? * MON,WED,FRI *";
	    //"0 30 1 ? * TUE";
	    String endExp = "0 30 2 ? * FRI";
	   // String expr = "0 0/5 1 LW * ?";
	    
	   // System.out.println(CronSequenceGenerator.isValidExpression(expr));
	    
	    CronTrigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule(startExp)).build();
	   // TimeZone timeZone = TimeZone.getTimeZone("America/Los_Angeles");
	   // timeZone = TimeZone.getDefault();
	    
	    Date current = new Date(System.currentTimeMillis());
	    
	    CronExpression cronStart = new CronExpression(startExp);
	    
	    CronExpression cronEnd = new CronExpression(endExp);
	    
	    for (int i = 0; i < 5; i++) {
		Date nextStart = cronStart.getNextValidTimeAfter(current);
		Date nextFire = cronStart.getNextValidTimeAfter(nextStart);
		System.out.println("next start: " + nextStart);
		System.out.println("next fire: " + nextFire);
		
		System.out.println("--------");
		current = nextStart;

	    }
	    
	   
	}
	catch(Exception e){
	    System.out.println("exception: "+ e.getMessage());
	    e.printStackTrace();
	}
   }
}
