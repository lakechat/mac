package com.redlection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

    public static void main(String[] args) {

	Car car = new Car();
	car.setMake("toyota");
	car.setModel("corolla");
	car.setYear("2000");
	// Class aClass = car.getClass();
	// Class[] paramTypes = new Class[1];
	// paramTypes[0] = String.class; // get the actual param type
	//
	// String methodName = "set" + fieldName; // fieldName String
	// Method m = null;
	// try {
	// m = aClass.getMethod("confirmMsg", paramTypes);
	// } catch (NoSuchMethodException nsme) {
	// nsme.printStackTrace();
	// }
	//
	// try {
	// String result = (String) m.invoke(f, fieldValue); // field value
	// System.out.println(result);
	// } catch (IllegalAccessException iae) {
	// iae.printStackTrace();
	// } catch (InvocationTargetException ite) {
	// ite.printStackTrace();
	// }
	//

	java.lang.reflect.Field[] allFields = car.getClass().getFields();
	List<String> fields = new ArrayList<>();
	for (java.lang.reflect.Field field : allFields) {
	    String fieldName = field.getName();
	     //System.out.println("field: "+fieldName);
	   
	}
	try {

	    java.lang.reflect.Method[] allMethods = car.getClass().getMethods();
	    for (java.lang.reflect.Method method : allMethods) {
		String methodName = method.getName();
		if (methodName.startsWith("get") && !methodName.equals("getClass")) {
		    //System.out.println("get field: " + deCamelCase(methodName.substring(3, methodName.length())));
		    //System.out.println("value: " + method.invoke(car, null));
		    fields.add(deCamelCase(methodName.substring(3, methodName.length())));
		}
	    }
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
	
	
	// set values
	 Class aClass = car.getClass();
	 Class[] paramTypes = new Class[1];
	 paramTypes[0] = String.class; // get the actual param type
	for(String field : fields){
	     String methodName = "set" + camelCase(field); // fieldName String
	     System.out.println("method name: "+methodName);
	     java.lang.reflect.Method m = null;
	     try {
	     m = aClass.getMethod(methodName, paramTypes);
	     m.invoke(car, field+"1");
	     } catch (Exception nsme) {
	     nsme.printStackTrace();
	     }
	}
	
	System.out.println(car.getMake()+", "+car.getModel()+", "+car.getYear());
    }
    
    private static String camelCase(String s){
	if(s!=null){
	    String c = s.substring(0,1).toUpperCase();
	    return c+s.substring(1, s.length());
	}
	return null;
    }
    
    private static String deCamelCase(String s){
	if(s!=null){
	    String c = s.substring(0,1).toLowerCase();
	    return c+s.substring(1, s.length());
	}
	return null;
    }
}
