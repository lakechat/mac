package strings;

public class Alphabetic {
    
    static String str(int i) {
	String s = i < 0 ? "" : str((i / 26) - 1)+(char)(65 + i % 26);    
	  System.out.println(i + " -> " +s); 
	return s;
	}
    
    public static void main(String[] args) {
	    
	   str(727)  ;
	   
	}

}
