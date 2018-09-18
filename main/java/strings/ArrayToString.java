package strings;

import java.util.ArrayList;
import java.util.List;

public class ArrayToString {

    public static void main(String[] args){
	List<String> tmp = new ArrayList<>();
	tmp.add("1");
	tmp.add("2");
	tmp.add("3");
	
	tmp = new  ArrayList<>();
	String result = String.join(",", tmp);
	
	System.out.println(result);
		
    }
}
