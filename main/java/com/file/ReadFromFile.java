package com.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ReadFromFile {

    public static void main(String[] args) throws IOException{
	//List<String> result = readFileNoneBlocking("/usr/share/dict/connectives", "r");
	List<String> result = readFileWithScanner("/usr/share/dict/connectives");
	result.stream().sorted().map(s -> s.toUpperCase()).filter(s -> s.length()==3).forEach(System.out::println);
	System.out.println("total: "+result.stream().count());
    }
    
   
    
    // using Java NIO fileChannel, non-blocking
    private static List<String> readFileNoneBlocking(String fileName, String mode) {
	List<String> result = new ArrayList<>();
	
	try (RandomAccessFile file = new RandomAccessFile(fileName,mode);FileChannel channel = file.getChannel()){
	    
	    ByteBuffer buffer = ByteBuffer.allocate((int)channel.size());
	    channel.read(buffer);
	  
	   if(buffer.hasArray()){
	       String s = new String(buffer.array(),"UTF-8");
	       result = Arrays.asList(s.split("\\r?\\n"));
	   }
		
	} catch (Exception e) {

	} 
	return result;
    }
    
    // using stream
    private static List<String> readFileBlocking(String fileName) throws IOException{
	List<String> result = new ArrayList<>();
	File file = new File(fileName);
	BufferedReader reader = new BufferedReader(new FileReader(file));
	result = reader.lines().collect(Collectors.toList());
	
	return result;
    }
    
    // conventional BufferedReader method
    private static List<String> readFileBlocking2(String fileName) {
	List<String> result = new ArrayList<>();
	File file = new File(fileName);
	try(BufferedReader reader = new BufferedReader(new FileReader(file));){
	    String s = null;
	    while((s=reader.readLine())!=null){
		result.add(s);
	    }
	    System.out.println("size: "+result.size());
	
	}catch(Exception e){
	    
	}
	
	return result;
    }
    
    // Using Files and Paths
    private static List<String> readUsingFilesAndPaths(String fileName){
	List<String> result = null;
	
	try(BufferedReader reader = Files.newBufferedReader(Paths.get(fileName))){
	    result = reader.lines().collect(Collectors.toList());
	}catch(IOException e){
	    e.printStackTrace();
	}
	return result;
    }
    
    // using scanner
    private static List<String> readFileWithScanner(String fileName){
	List<String> result = new ArrayList<>();
	
	try(Scanner scanner = new Scanner(new File(fileName))){
	    while(scanner.hasNextLine())
		result.add(scanner.nextLine());
	}catch(IOException e){
	    e.printStackTrace();
	}
	return result;
	
    }
}
