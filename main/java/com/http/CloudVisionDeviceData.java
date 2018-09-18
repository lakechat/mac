/**
 * Copyright 2017 (C) FixStream Networks, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.http;

import java.util.List;

public class CloudVisionDeviceData {

    private List<DeviceNotification> notifications;

    public List<DeviceNotification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<DeviceNotification> notification) {
        this.notifications = notification;
    }

}
