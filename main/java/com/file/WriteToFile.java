package com.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class WriteToFile {
    
    public static void main(String[] args){
	String destName = "/tmp/test.txt";
	String sourceName = "/usr/share/dict/connectives";
	writeWithBufferedWriter(readFile(sourceName), destName);
    }
    
    // Classic BufferedWriter
    private static void writeWithBufferedWriter(List<String> content, String fileName){
	
	try(FileWriter fw = new FileWriter(fileName,true); BufferedWriter bw = new BufferedWriter(fw)){
	    if (content != null){
		String separator = System.getProperty("line.separator");
		
	    for(String s : content){
		//bw.append(s+separator);
		bw.append(s+"\n");
	    }
	    }
	}catch(IOException e){
	    e.printStackTrace();
	}
    }
    
    // read from a dictionary file
    private static List<String> readFile(String fileName){
	
	List<String> result = null;
	try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
	    result = reader.lines().collect(Collectors.toList());
	}catch(IOException e){
	    e.printStackTrace();
	}
	
	return result;
    }
}
