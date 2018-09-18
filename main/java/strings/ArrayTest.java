package strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayTest {
    
    public static void main(String[] args){
	String s = "1,2,3";
	s.len
	List<String> l = new ArrayList<>();
	l.add("2");
	l.add("3");
	String[] tmp = s.split(","); 
	
	List<String> s2 = new ArrayList<String>();
	s2.removeAll(l);

	
	for(String a : l)
	    System.out.println(a);
		
    }

}
