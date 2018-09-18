package strings;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ParseLie {
    

private static final Scanner scan = new Scanner(System.in);
    
    public static void main(String args[]) throws Exception {
	// read the string filename
	try {
	    String filename;
	    filename = scan.nextLine();
	    File file = new File(filename);

	    Scanner scanner2 = new Scanner(file);

	    Map<String, Integer> resultMap = new HashMap<>();

	    while (scanner2.hasNextLine()) {
		String line = scanner2.nextLine();
		String timestamp = parseLine(line);
		Integer n = resultMap.get(timestamp);
		if (n == null)
		    resultMap.put(timestamp, 1);
		else
		    resultMap.put(timestamp, n + 1);
	    }
	    scan.close();

	    PrintWriter writer = new PrintWriter("req_" + filename);
	    for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
		String key = entry.getKey();
		Integer value = entry.getValue();
		writer.println("The timestamp " + key + " occurrs " + value + "times");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }
    
    private static String parseLine(String input){
        if(input !=null){
            int startIndex = input.indexOf('[');
            if(startIndex>0){
                int endIndex =  input.indexOf(']');
                if(endIndex >0){
                    String part = input.substring(beginIndex, endIndex)
                    String[] timestampStr = input.split(" ");
                    if(timestampStr[0]!=null)
                        return timestampStr[0];
                }
            }
        }
        return null;
    }



}
