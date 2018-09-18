/**
 * Copyright 2017 (C) FixStream Networks, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.http;

import java.util.List;

public class MetricTimeSeries {

   // private MetricEnum metricEnum;
    private String metricName;
    private String metricPair;
    private String metricUnit;
    private List<MetricTimeStampClass> dataSet;

    private Double thresholdCriticalHighValue;
    private Double thresholdCriticalLowValue;
    private Double thresholdMinorHighValue;
    private Double thresholdMinorLowValue;
    private boolean thresholdChangeable = true;

    public MetricTimeSeries() {
        super();
    }

    public MetricTimeSeries(String metricName, Double thresholdCriticalHighValue, Double thresholdCriticalLowValue,
                            Double thresholdMinorHighValue, Double thresholdMinorLowValue,
                            List<MetricTimeStampClass> dataSet) {
        super();
        this.metricName = metricName;
        this.thresholdCriticalHighValue = thresholdCriticalHighValue;
        this.thresholdCriticalLowValue = thresholdCriticalLowValue;
        this.thresholdMinorHighValue = thresholdMinorHighValue;
        this.thresholdMinorLowValue = thresholdMinorLowValue;

        this.dataSet = dataSet;
    }

    public MetricTimeSeries(String metricName, String metricUnit, Double thresholdCriticalHighValue,
                            Double thresholdCriticalLowValue, Double thresholdMinorHighValue,
                            Double thresholdMinorLowValue, List<MetricTimeStampClass> dataSet) {
        super();
        this.metricName = metricName;
        this.metricUnit = metricUnit;
        this.thresholdCriticalHighValue = thresholdCriticalHighValue;
        this.thresholdCriticalLowValue = thresholdCriticalLowValue;
        this.thresholdMinorHighValue = thresholdMinorHighValue;
        this.thresholdMinorLowValue = thresholdMinorLowValue;

        this.dataSet = dataSet;
    }

    public MetricTimeSeries(String metricName, List<MetricTimeStampClass> dataSet) {
        super();
        this.metricName = metricName;
        this.dataSet = dataSet;
    }

    public MetricTimeSeries(String metricName, String metricUnit, List<MetricTimeStampClass> dataSet) {
        super();
        this.metricName = metricName;
        this.metricUnit = metricUnit;
        this.dataSet = dataSet;
    }

    public MetricTimeSeries(String metricName, String metricUnit, String metricPair,
                            List<MetricTimeStampClass> dataSet) {
        super();
        this.metricName = metricName;
        this.metricUnit = metricUnit;
        this.metricPair = metricPair;
        this.dataSet = dataSet;
    }

    

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public String getMetricPair() {
        return metricPair;
    }

    public void setMetricPair(String metricPair) {
        this.metricPair = metricPair;
    }

    public String getMetricUnit() {
        return metricUnit;
    }

    public void setMetricUnit(String metricUnit) {
        this.metricUnit = metricUnit;
    }

    public List<MetricTimeStampClass> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<MetricTimeStampClass> dataSet) {
        this.dataSet = dataSet;
    }

    public Double getThresholdCriticalHighValue() {
        return thresholdCriticalHighValue;
    }

    public void setThresholdCriticalHighValue(Double thresholdCriticalHighValue) {
        this.thresholdCriticalHighValue = thresholdCriticalHighValue;
    }

    public Double getThresholdCriticalLowValue() {
        return thresholdCriticalLowValue;
    }

    public void setThresholdCriticalLowValue(Double thresholdCriticalLowValue) {
        this.thresholdCriticalLowValue = thresholdCriticalLowValue;
    }

    public Double getThresholdMinorHighValue() {
        return thresholdMinorHighValue;
    }

    public void setThresholdMinorHighValue(Double thresholdMinorHighValue) {
        this.thresholdMinorHighValue = thresholdMinorHighValue;
    }

    public Double getThresholdMinorLowValue() {
        return thresholdMinorLowValue;
    }

    public void setThresholdMinorLowValue(Double thresholdMinorLowValue) {
        this.thresholdMinorLowValue = thresholdMinorLowValue;
    }

    public boolean isThresholdChangeable() {
        return thresholdChangeable;
    }

    public void setThresholdChangeable(boolean thresholdChangeable) {
        this.thresholdChangeable = thresholdChangeable;
    }

}
