package strings;

import static java.util.Arrays.asList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Cost {
    
    public static void main(String[] args){
	int[] A = {5,2,4,6,3,7};
	System.out.println(solution(A));
    }
    
    public static int solution(int[] A) {
        // write your code in Java SE 8
	 int length = A.length;
	        Integer[] a = new Integer[length];
	        int  j = 0;
	        for(int i : A)
	             a[j++] = i;
	        List<Integer> list = Arrays.asList(a);
	        Collections.sort(list);
	        int least = list.get(0);
	        int second = list.get(1);
	        
	        return least+second;
    }

}
