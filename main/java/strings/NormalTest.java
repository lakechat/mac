package strings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NormalTest {

    public static void main(String[] args){
	
	
	String s1 = "1";
	
	List<String> l = new ArrayList<>();
	l.add("4");
	String s2 = other(l,s1);
	System.out.println(s1+",,"+s2+",,"+l.get(0));
	
    }
    
    private static String  other(List<String> l,String s){
	s= "3";
	String ss= l.get(0);
	ss = "0";
	l.add(0, ss);
	return s;
    }
}
