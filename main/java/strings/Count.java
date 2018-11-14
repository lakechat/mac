package strings;

import java.util.HashMap;
import java.util.Map;

public class Count {
    
    public static void main(String args[] ){
	int[] A = {2,-2,3,-4,0,1};
	int x = solution(A);
	System.out.println(x);
    }
    
   
	    public  static int solution(int[] A) {
		// write your code in Java SE 8
	        int count = 0;
	        int length = A.length;
	        int sum = 0;
	        Map<Integer,Integer> results = new HashMap<>();
	       results.put(0, 1);
	        for(int i = 0; i<length; i++){
	            sum += A[i];
	            if(results.containsKey(sum)){
	                count += results.get(sum);
	                
	            }
	            results.put(sum,results.getOrDefault(sum,0)+1);
	        }
	        return count>100000000?-1:count;
	    }
	

}
