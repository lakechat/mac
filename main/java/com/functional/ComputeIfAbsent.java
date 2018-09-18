package com.functional;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ComputeIfAbsent {
    public static void main(String[] s1) {
        Map<String, Boolean> whoLetDogsOut = new ConcurrentHashMap<>();
        whoLetDogsOut.computeIfAbsent("snoop1", key -> f(key));
        whoLetDogsOut.computeIfAbsent("snoop2", k -> f(k));
        for(Map.Entry<String, Boolean> entry : whoLetDogsOut.entrySet())
            System.out.println(entry.getKey()+" : "+entry.getValue());
        
        String s = null;
        
        System.out.println(s.isEmpty());
    }
    static boolean f(String s) {
        System.out.println("creating a value for \""+s+'"');
        return s.isEmpty();
    }

}
