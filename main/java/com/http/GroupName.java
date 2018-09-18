/**
 * Copyright 2017 (C) FixStream Networks, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.http;

import java.util.Map;

public class GroupName {

    private String groupName;
    private Map<String, String> metricNames;

    public GroupName(String groupName, Map<String, String> metricNames) {
        super();
        this.groupName = groupName;
        this.metricNames = metricNames;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Map<String, String> getMetricNames() {
        return metricNames;
    }

    public void setMetricNames(Map<String, String> metricNames) {
        this.metricNames = metricNames;
    }
}
