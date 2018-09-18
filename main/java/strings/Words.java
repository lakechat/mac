package strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

public class Words {

    // CLASS BEGINS, THIS CLASS IS REQUIRED

    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public static List<String> retrieveMostFrequentlyUsedWords(String literatureText, List<String> wordsToExclude){
        
	 if(literatureText==null || literatureText.trim().length()==0)
	            return null;
	 if(wordsToExclude == null){
	     wordsToExclude = new ArrayList<String>();
	 }
	 List<String> newExclude = new ArrayList<>();
	 for(String s: wordsToExclude)
	     newExclude.add(s.trim().toLowerCase());
	       String delimiters = "\\s+|,\\s*|\\.\\s*";

	      // analyzing the string 
	      String[] tokensVal = literatureText.split(delimiters);
	      
	      Map<String,Integer> input = new HashMap<>();
	     
	      for(String s : tokensVal){
	       String tmp = s.trim().toLowerCase();
	       if(newExclude.contains(tmp))
		   continue;
	       if(input.get(tmp)!=null){
		   int value = input.get(tmp);
		   input.put(tmp, value+1);
	       }else
		   input.put(tmp, 1);
	      }
	      
	      SortedSet<Map.Entry<String, Integer>> sortedset = new TreeSet<Map.Entry<String, Integer>>(
		            new Comparator<Map.Entry<String, Integer>>() {
		                @Override
		                public int compare(Map.Entry<String, Integer> e1,
		                        Map.Entry<String, Integer> e2) {
		                    	if(e1.getValue() < e2.getValue() )
		                    	    return 1;
		                    	else if (e1.getValue() == e2.getValue() )
		                    	    return 0;
		                    	else
		                    	    return -1;
		                   
		                }
		            });

		  sortedset.addAll(input.entrySet());
		  
		  List<String> result = new ArrayList<>();
		  for(Map.Entry<String,Integer> e : sortedset){
		      String s = e.getKey();
		      int i = e.getValue();
		      System.out.println(s);
		      result.add(s);
		  }
		return result;
		  
	      
       
       
     }
    // METHOD SIGNATURE ENDS
    
    public static void main(String[] args){
	String literatureText = "jack ad jill will go . Cheese is jack's favito food";
	String[] wordsToExclude = {"jack", "and"};
	
	List<String> input = Arrays.asList(wordsToExclude);
	System.out.println("test");
	retrieveMostFrequentlyUsedWords(literatureText,input);
    }

}
