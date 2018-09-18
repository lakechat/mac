package strings;

public class NullOperation {
    
    public static void main(String[] args){
	String s1 = "1";
	String s2 = "2";
	String tmp = null;
	String s = s1+s2+tmp;
	if(s==null)
	    System.out.println("null value");
	System.out.println(s);
    }

}
