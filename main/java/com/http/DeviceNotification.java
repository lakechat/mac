/**
 * Copyright 2017 (C) FixStream Networks, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.http;

import java.util.Map;

public class DeviceNotification {

    private long timestamp;
    private String path;
    private Map<String, DeviceUpdate> updates;

   

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, DeviceUpdate> getUpdates() {
        return updates;
    }

    public void setUpdates(Map<String, DeviceUpdate> deviceUpdate) {
        this.updates = deviceUpdate;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
    

}
