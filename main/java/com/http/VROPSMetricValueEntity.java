package com.http;

import java.io.Serializable;

public class VROPSMetricValueEntity implements Serializable{
    
    private long[] timestamps;
    private double[] data;
    public long[] getTimestamps() {
        return timestamps;
    }
    public void setTimestamps(long[] timestamps) {
        this.timestamps = timestamps;
    }
    public double[] getData() {
        return data;
    }
    public void setData(double[] data) {
        this.data = data;
    }
    
    

}
