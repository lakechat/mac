package com.http;

public class CloudVisionMetricNotification {

    private long timestamp;
    private CloudVisionMetricUpdate updates;
    
    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    public CloudVisionMetricUpdate getUpdates() {
        return updates;
    }
    public void setUpdates(CloudVisionMetricUpdate updates) {
        this.updates = updates;
    }
    
    
    
}
