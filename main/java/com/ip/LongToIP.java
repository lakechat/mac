package com.ip;

public class LongToIP {
    
    public static void main(String[] args)
    {
    Long l = 3232236033L;
    String tmp[] = new String[4];
    
    
    tmp[0] = String.valueOf(l>>>24);
    tmp[1] = String.valueOf((l>>>16)&0xff);
    tmp[2] = String.valueOf((l>>>8)&0xff);
    tmp[3] = String.valueOf(l&0xff);
   
    
//	for (int i = 0; i < 4; i++)
//	    System.out.println(tmp[i]);
	
	
	System.out.println(Integer.toBinaryString(-1));
	System.out.println(Integer.toBinaryString(1));
//
   }
    

}
