package com.basic;

public class EnumTest {
    
    public enum Test{
	Monday("Mon","1"),
	Tuesday("Tue","2");
	
	private String shortName;
	private String num;
	
	private Test(String name, String number){
	    this.shortName = name;
	    this.num = number;
	}
	
    }
    
    public static void main(String[] args){
	String name = Test.Monday.name();
	System.out.println("get name: "+name);
    }

}
