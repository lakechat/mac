/**
 * Copyright 2017 (C) FixStream Networks, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.http;

import java.util.List;

public class ServiceName {

    private String serviceName;
    private List<GroupName> metricList;

    public ServiceName(String serviceName, List<GroupName> metricList) {
        super();
        this.serviceName = serviceName;
        this.metricList = metricList;
    }

    public ServiceName() {
        super();
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public List<GroupName> getMetricList() {
        return metricList;
    }

    public void setMetricList(List<GroupName> metricList) {
        this.metricList = metricList;
    }

}
