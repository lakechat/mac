package com.stream;

import java.util.ArrayList;
import java.util.List;

public class EmptyStream {
    
    public static void main(String[] args){
	List<Test> test = new ArrayList<>();
	Test t = new Test("d1", null);
	test.add(t);
	
	String[] db = test.stream().map(e -> e.db).toArray(String[]::new);
	String[] table = test.stream().map(e -> e.table).filter(e -> e!= null).toArray(String[]::new);
	
	System.out.println(table.length);
	
    }
    
    static class Test{
	String db;
	String table;
	
	public  Test(String db, String table){
	    this.db = db;
	    this.table = table;
	}
    }

}
