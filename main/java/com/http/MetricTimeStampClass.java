/**
 * Copyright 2017 (C) FixStream Networks, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.http;

public class MetricTimeStampClass implements Comparable<MetricTimeStampClass> {

    private String timeStamp;
    private double metricValue;
    private String metricAttribute;
    private String severity;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /*
     * public void setTimeStamp(DateTime timeStamp) {
     * this.timeStamp = EntitiesConstants.jodaCompleteDateFormatter.print(timeStamp);
     * }
     */

    public double getMetricValue() {
        return metricValue;
    }

    public void setMetricValue(double metricValue) {
        this.metricValue = metricValue;
    }

    @Override
    public int compareTo(MetricTimeStampClass timeStampB) {
        if (timeStampB == null || timeStampB.timeStamp == null || this.timeStamp == null) {
            return -1;
        }
        return this.timeStamp.compareTo(timeStampB.timeStamp);
    }

    public String getMetricAttribute() {
        return metricAttribute;
    }

    public void setMetricAttribute(String metricAttribute) {
        this.metricAttribute = metricAttribute;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

}
