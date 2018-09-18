package com.enums;

/**
 * Copyright 2017 (C) FixStream Networks, Inc - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */


import java.util.ArrayList;
import java.util.List;


public enum DeviceSubTypeEnum {

    FABRIC_INTERCONNECT("Fabric Interconnect", "Fabric Interconnect", 100, true),
    IOM("IOM", "IOM", 100, false),
    SWITCH("Switch", "Switch", 100, true),
    ROUTER("Router", "Router", 100, true),
    VSWITCH("VSWITCH", "Vswitch", 100, false),
    NUTANIX_VSWITCH("NUTANIX_VSWITCH", "Nutanix Vswitch", 100, false),
    VROUTER("VROUTER", "Vrouter", 100, false),
    VNET("VNET", "Vnet", 100, false),
    SAN_SWITCH("SAN Switch", "San Switch", 100, false),
    SAN_STORAGE("SAN Storage", "San Storage", 100, false),
    FIREWALL("Firewall", "Firewall", 100, true),
    LB("LB", "Load Balancer", 100, true),
    VOLUME("VOLUME", "Volume", 100, false),
    LUN("LUN", "Lun", 100, false),
    NFS("NFS", "Nfs", 100, false),
    BRICK("BRICK", "Brick", 100, false),
    CLUSTER("CLUSTER", "Cluster", 100, false),
    SSD("SSD", "Ssd", 100, false),
    DISK_ARRAY("Disk Array", "Disk Array", 100, false),
    DISK("DISK", "Disk", 100, false),
    STORAGE_POOL("Storage Pool", "Storage Pool", 100, false),
    CHASSIS("CHASSIS", "Chassis", 100, false),
    BARE_METAL("Bare Metal", "Bare Metal", 50, false),
    VM("VM", "VM", 50, true),
    HYPERVISOR("HYPERVISOR", "Hypervisor", 100, true),
    HSRP_VIP("HSRP VIP", "HSRP VIP", 100, false),
    LB_VIRTUAL_SERVER("LB VIRTUAL SERVER", "LB Virtual Server", 100, false),
    NETWORK("NETWORK", "Network", 0, false),
    COMPUTE("COMPUTE", "Compute", 0, true),
    MANUAL("MANUAL", "Manual", 100, false),
    CONTROLLER("CONTROLLER", "Controller", 100, true),
    PORT("PORT", "Port", 100, true),
    STORAGEGROUP("STORAGEGROUP", "StorageGroup", 100, true),
    STORAGE_GROUP("STORAGE_GROUP", "Storage Group", 100, true),
    STORAGE("STORAGE", "Storage", 0, false),
    DATA_STORE("DATA_STORE", "Datastore", 100, false),
    UNKNOWN("UNKNOWN", "Unknown", -100, false),
    OPENSTACK_HYPERVISOR("OPENSTACK_HYPERVISOR", "Openstack Hypervisor", 100, false),
    CLOUD("CLOUD", "Cloud", 100, false),
    AGGREGATE("AGGREGATE", "Aggregate", 100, false),
    VIRTUALNETWORK("VIRTUALNETWORK", "Virtual Network", 100, false),
    DIRECTOR("DIRECTOR", "Director", 100, false),
    STORAGESYSTEM("STORAGESYSTEM", "Storage System", 100, false),
    HARDDISKDRIVE("HARDDISKDRIVE", "Hard Disk Drive", 100, false),
    STORAGEPOOL("STORAGEPOOL", "StoragePool", 100, false),
    STORAGE_CONTAINER("STORAGE_CONTAINER", "Storage Container", 100, false),
    VIRTUAL_DISK("VIRTUAL_DISK", "Virtual Disk", 100, false),
    NODESYSTEM("NODESYSTEM", "Node System", 100, false),
    DYNAMICSTORAGEPOOL("DYNAMICSTORAGEPOOL", "Dynamic Storage Pool", 100, false),
    NODE("NODE", "Node", 100, false),
    GATEWAY("GATEWAY", "Gateway", 50, false),
    DOCKER("DOCKER", "Docker", 100, false),
    SOLARIS_ZONE_PARENT("SOLARIS_ZONE_PARENT", "Solaris Zone Parent", 100, true),
    SOLARIS_ZONE("SOLARIS_ZONE", "Solaris Zone", 100, true),
    ENCLOSURE("ENCLOSURE", "Enclosure", 100, false),
    WIRELESS_LAN_CONTROLLER("WIRELESS_LAN_CONTROLLER", "Wireless Lan Controller", 100, false),
    WIRELESS_ACCESS_POINT("WIRELESS_ACCESS_POINT", "Wireless Access Point", 100, false);

    private String type;
    private String displayName;
    private int rank;
    private boolean isBillable;

    private DeviceSubTypeEnum(String type, String displayName, int rank, boolean isBillable) {
        this.type = type;
        this.displayName = displayName;
        this.rank = rank;
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

   

    public boolean matches(DeviceSubTypeEnum... deviceSubTypeEnums) {

        for (DeviceSubTypeEnum deviceSubTypeEnum : deviceSubTypeEnums) {
            if (this.equals(deviceSubTypeEnum)) {
                return true;
            }
        }
        return false;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int compareDeviceSubTypeRank(DeviceSubTypeEnum deviceSubTypeEnum) {
        if (null == deviceSubTypeEnum) {
            return 1;
        }
        return this.getRank() - deviceSubTypeEnum.getRank();
    }

    public DeviceSubTypeEnum findBetterDeviceSubType(DeviceSubTypeEnum deviceSubTypeEnum) {
        if (null != deviceSubTypeEnum && deviceSubTypeEnum.getRank() >= this.getRank())
            return deviceSubTypeEnum;
        return this;
    }

    public static List<DeviceSubTypeEnum> getBillableDeviceSubTypes() {
        List<DeviceSubTypeEnum> billableDevices = new ArrayList<DeviceSubTypeEnum>();
        for (DeviceSubTypeEnum deviceSubType : DeviceSubTypeEnum.values()) {
            if (deviceSubType.getIsBillable()) {
                billableDevices.add(deviceSubType);

            }
        }
        return billableDevices;
    }

}
