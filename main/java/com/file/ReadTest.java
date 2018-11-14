package com.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ReadTest {

    public static void main(String[] args){
	List<String> strings = readFile("/Users/taoli/tmp/meridianLicense.properties");
	for(String s : strings)
	    System.out.println(s);
	
    }
    
    private static List<String> readFile(String fileName){
	List<String> result = null;
	File file = new File(fileName);
	try(BufferedReader reader = new BufferedReader(new FileReader(file))){
	    result = reader.lines().collect(Collectors.toList());
	}catch(IOException e){
	    
	}
	return result;
    }
    
    private static List<String> readFile2(String fileName){
	List<String> result = null;
	File file = new File(fileName);
	try(BufferedReader reader = new BufferedReader(new FileReader(file))){
	    String s = null;
	    while((s = reader.readLine()) != null){
		result.add(s);
	    }
		
	    
	}catch(IOException e){
	    
	}
	return result;
    }
}
