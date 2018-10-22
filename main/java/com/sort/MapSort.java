package com.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapSort {
    public static void main(String[] args) {
	Map<String, Integer> unsortMap = new HashMap<>();

	unsortMap.put("z", 10);
	unsortMap.put("b", 5);
	unsortMap.put("a", 6);
	unsortMap.put("c", 20);
	unsortMap.put("d", 1);
	unsortMap.put("e", 7);
	unsortMap.put("y", 8);
	unsortMap.put("n", 99);
	unsortMap.put("j", 50);
	unsortMap.put("m", 2);
	unsortMap.put("f", 9);

	System.out.println("Unsort Map......");
	printMap(unsortMap);
	
	Map<String, Integer> sortMap = sortByValue(unsortMap);
	
	System.out.println("sort Map......");
	
	printMap(sortMap);
    }
    
    static Map<String,Integer> sortByValue(Map<String,Integer> unsortMap){
	
	List<Map.Entry<String,Integer>> mapList = new ArrayList<>(unsortMap.entrySet());
	Collections.sort(mapList, new Comparator<Map.Entry<String,Integer>>(){
	    public int compare(Map.Entry<String,Integer> entry1, Map.Entry<String,Integer> entry2){
		return entry1.getValue()-entry2.getValue();
	    }
	});
	
	Map<String,Integer> sortedMap = new LinkedHashMap<>();
	for(Map.Entry<String,Integer> entry : mapList)
	    sortedMap.put(entry.getKey(), entry.getValue());
	return sortedMap;
    
    }
    
    private static void printMap(Map<String,Integer> map){
	for(Map.Entry<String, Integer> entry : map.entrySet())
	    System.out.println("key: "+entry.getKey()+" , value: "+entry.getValue());
    }
    
    static <K,V extends Comparable<? super V>> Map<K,V> sortByValueGeneric(Map<K,V> unsortMap){
	List<Map.Entry<K, V>> entryList = new ArrayList<>();
	
	Collections.sort(entryList, new Comparator<Map.Entry<K, V>>(){
	    public int compare(Map.Entry<K,V> entry1, Map.Entry<K,V> entry2){
		return entry1.getValue().compareTo(entry2.getValue());
	    }
	});
	
	Map<K,V> result = new LinkedHashMap<>();
	for(Map.Entry<K, V> entry : entryList)
	    result.put(entry.getKey(), entry.getValue());
	
	return result;
    }

}
