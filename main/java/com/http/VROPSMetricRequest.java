package com.http;

import java.io.Serializable;
import java.util.List;

public class VROPSMetricRequest implements Serializable{
    
    private List<String> resourceId;
    private long begin; // millionseconds
    private List<String> statKey;
    private String rollUpType;
    private String intervalType;
    
    public VROPSMetricRequest(){
	
    }
    
    public VROPSMetricRequest( List<String> resourceId, long begin, List<String> statKey, String intervalType, String rollUpType){
	this.resourceId = resourceId;
	this.begin = begin;
	this.statKey = statKey;
	this.intervalType = intervalType;
	this.rollUpType = rollUpType;
    }
    
    public List<String> getResourceId() {
        return resourceId;
    }
    public void setResourceId(List<String> resourceId) {
        this.resourceId = resourceId;
    }
    public long getBegin() {
        return begin;
    }
    public void setBegin(long begin) {
        this.begin = begin;
    }
    public List<String> getStatKey() {
        return statKey;
    }
    public void setStatKey(List<String> statKey) {
        this.statKey = statKey;
    }
    public String getIntervalType() {
        return intervalType;
    }
    public void setIntervalType(String intervalType) {
        this.intervalType = intervalType;
    }
    public  String getRollUpType() {
        return rollUpType;
    }
    
    public void setRollUpType(String rollUpType){
	this.rollUpType = rollUpType;
    }
    
    
    

}
