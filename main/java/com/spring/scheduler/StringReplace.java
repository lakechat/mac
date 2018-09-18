package com.spring.scheduler;

public class StringReplace {
    
    public static void main(String[] args){
	
	String tmp = "0 10 0/1 1/1 1 ? 2";
	
	String cron = tmp.replaceAll("STAR", "\\*");
	
	System.out.println(cron);
    }

}
