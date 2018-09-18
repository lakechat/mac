package com.sort;

import java.util.Comparator;

public class StringCompare implements Comparator<String>{
    
    private String s1;
    
    public int compareTo(String s2){
	int l1 = s1.length();
	int l2 = s2.length();
	int l = Math.min(l1, l2);
	
	char[] ca1 = s1.toCharArray();
	char[] ca2 = s2.toCharArray();
	
	for(int i = 0; i<l; i++){
	    char c1 = ca1[i];
	    char c2 = ca2[i];
	    if(c1!=c2)
		return c1-c2;
	    
	}
	
	
	return l1-l2;
    }
    
    public int compare(String s1, String s2){
	int l1 = s1.length();
	int l2 = s2.length();
	int l = Math.min(l1, l2);
	
	char[] ca1 = s1.toCharArray();
	char[] ca2 = s2.toCharArray();
	
	for(int i = 0; i<l; i++){
	    char c1 = ca1[i];
	    char c2 = ca2[i];
	    if(c1!=c2)
		return c1-c2;
	    
	}
	
	
	return l1-l2;
    }

}
