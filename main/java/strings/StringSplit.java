package strings;

public class StringSplit {
    
    public static void main(String[] args){
	
	String s = "Summary|Oversized|memory";
	System.out.println(s);
	s = s.toLowerCase();
	System.out.println(s);
	int length = s.length() - s.replaceAll("|", "").length();
	
	VROPSKeyObject ko = new VROPSKeyObject();
	ko.setKey(s);
	
	
	
	
	System.out.println(ko.getServiceName());
	System.out.println(ko.getGroupName());
	System.out.println(ko.getMetricName());
	
	
	
    }

}
