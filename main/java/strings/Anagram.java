package strings;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Anagram {
    
    public static void main(String[] args){
	Map<String,List<String>> result = new HashMap<>();
	solver(result,"astronaut");
    }
    
    private static void solver(Map<String, List<String>> result, String word){
	System.out.println("word: "+word);
	if(word != null && word.trim().length()>0){
	    char[] charArray = word.replaceAll(" ", "").toLowerCase().toCharArray();
	    
	    Arrays.sort(charArray);
	   
	    System.out.println("s: "+String.valueOf(charArray));
	}
    }
    
    
    public Character[] toCharacterArray( String s ) {

	   if ( s == null ) {
	     return null;
	   }

	   int len = s.length();
	   Character[] array = new Character[len];
	   for (int i = 0; i < len ; i++) {
	      array[i] = new Character(s.charAt(i));
	   }

	   return array;
	}

}
