package com.ip;

public class IPToLong {

    public static void main(String[] args){
	String ip = "192.168.2.1";
	String[] tmp = ip.split("\\.");
	Long result = 0L;
	for(int i=0;i<tmp.length;i++ ){
	    Long l = stringToLong(tmp[i]);
	    result +=(l<<((3-i)*8));
	    System.out.println(l<<((3-i)*8));
	}
	System.out.println(result);
	
    }
    
    private static Long stringToLong(String s){
	
	return Long.parseLong(s);
    }
}
