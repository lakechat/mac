package com.http;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.json.JsonUtil;


public class VROPSMetricTest {
    
    public static void main(String[] args){
	String serviceName = "datastore";
	String metricGroup = "72";
	String metricName = "commandsAveraged_average";
	
	String deviceId = "00d68281-41bf-43e0-9bad-2afd7a55c480";
	List<String> ids = Arrays.asList(deviceId);
	String key = reconstructKey(serviceName,metricGroup,metricName);
	List<String> statKey = Arrays.asList(key);
	String intervalType = "MINUTES";
	int days = 1;
	if(days >3)
	    intervalType = "HOURS";
	long begin = 1538165604000L;//System.currentTimeMillis() - days*86400*1000;
	VROPSMetricRequest request = new VROPSMetricRequest(ids,begin,statKey,"HOURS","AVG");
	String jsonRequest = JsonUtil.getJson(request);
	
	try{
	String response = HttpClientCalls.httpPost3(jsonRequest, "https://192.168.128.99/suite-api/api/resources/stats/query");
	JsonNode rootNode = new ObjectMapper().readTree(response);
        JsonNode valueList = rootNode.get("values");
        
        if(valueList.isArray())
            System.out.println("value list is an array");
        
        for(JsonNode valueNode : valueList){
            System.out.println(valueNode.get("resourceId").asText());
            JsonNode statList = valueNode.get("stat-list");
            JsonNode stat = statList.get("stat");
            if(stat.isArray()){
        	JsonNode core = stat.get(0);
        	System.out.println("core: ");
        	//System.out.println(core.asText());
        	VROPSMetricValueEntity entity = JsonUtil.getObjectFromJson(core.toString(), VROPSMetricValueEntity.class);
        	
        	long[] timestamps = entity.getTimestamps();
        	double[] data = entity.getData();
        	
        	int l1= timestamps.length;
        	int l2 = data.length;
        	if(l1 >0 && l2 > 0 && (l1 == l2)){
        	    for(int i=0; i<l1; i++)
        	    System.out.println(timestamps[i]+ " : "+data[i]);
        	}
            }
        }
	
	}catch(Exception e){
	    e.printStackTrace();
	}
    }
    
    
    private static String reconstructKey(String serviceName, String metricGroup, String metricName){
	
	// final format serviceName:metricGroup|metricName
	
	if(metricGroup.equals("default"))
	    return serviceName+"|"+metricName;
	else
	    return serviceName+":"+metricGroup+"|"+metricName;
	
    }

}
