package com.enums;

import java.util.HashMap;
import java.util.Map;

public class Utils {
    
    public static void main(String[] args){
	Utils util = new Utils();
	Map<String,String> result = util.getDevTypes();
	//System.out.println(DeviceSubTypeEnum.class.getSimpleName());
	for( Map.Entry<String, String> entry: result.entrySet()){
	    System.out.println(entry.getValue());
	}
	   
	
	
    }
    
    private Map<String,String> getDevTypes(){
	Map<String, String> typeList = new HashMap<>();
	
	for(DeviceTypeEnum type : DeviceTypeEnum.values()){
	    String name = type.getDisplayName();
	    String value = type.getType();
	    typeList.put(name,value);
	}
	
	return typeList;
    }
    
    private Map<String, String> getDevSubTypes() {
	Map<String, String> typeList = new HashMap<>();

	for (DeviceSubTypeEnum type : DeviceSubTypeEnum.values()) {
	    String name = type.getDisplayName();
	    String value = type.getType();
	    typeList.put(name, value);
	}

	return typeList;
    }
    
//    private Map<String,String> getPairs(Enum classz)
//	classz.name()
//    }

}
