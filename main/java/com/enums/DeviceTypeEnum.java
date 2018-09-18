package com.enums;

import java.util.ArrayList;
import java.util.List;

public enum DeviceTypeEnum {

    NETWORK("NETWORK", "Network", true),
    COMPUTE("COMPUTE", "Compute", true),
    STORAGE("STORAGE", "Storage", true),
    CLUSTER("CLUSTER", "Cluster", true),
    UNKNOWN("UNKNOWN", "Unknown", false),
    CLOUD_AZURE("CLOUD_AZURE", "Cloud Azure", false),
    CLOUD("CLOUD", "Cloud", false);

    private String type;
    private String displayName;
    private boolean isBillable;

    private DeviceTypeEnum(String type, String displayName, boolean isBillable) {
        this.type = type;
        this.displayName = displayName;
        this.isBillable = isBillable;
    }

    public String getType() {
        return type;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean getIsBillable() {
        return isBillable;
    }

    public boolean matches(DeviceTypeEnum... deviceTypeEnums) {

        for (DeviceTypeEnum deviceTypeEnum : deviceTypeEnums) {
            if (this.equals(deviceTypeEnum)) {
                return true;
            }
        }
        return false;
    }

    public static DeviceTypeEnum getDeviceType(String type) {
        for (DeviceTypeEnum deviceType : DeviceTypeEnum.values()) {
            if (deviceType.getType().equals(type)) {
                return deviceType;
            }
        }
        return UNKNOWN;
    }

    public static DeviceTypeEnum getDeviceTypeByDisplayName(String displayName) {
        for (DeviceTypeEnum deviceType : DeviceTypeEnum.values()) {
            if (deviceType.getDisplayName().equals(displayName)) {
                return deviceType;
            }
        }
        return UNKNOWN;
    }

    public static List<DeviceTypeEnum> getBillableDeviceTypes() {
        List<DeviceTypeEnum> billableDevices = new ArrayList<DeviceTypeEnum>();
        for (DeviceTypeEnum deviceType : DeviceTypeEnum.values()) {
            if (deviceType.getIsBillable()) {
                billableDevices.add(deviceType);

            }
        }
        return billableDevices;
    }

}
