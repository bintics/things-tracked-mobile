package com.bintics.tracked.bean;

/**
 * Created by andru-fb on 20/03/17.
 */

public class Device {
    private String name;
    private double latitude;
    private double longitude;
    private String label;
    private boolean conected;
    private String uid;

    public Device() {
    }

    public Device(String uid, String name, double latitude, double longitude, String label, boolean conected) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.label = label;
        this.conected = conected;
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isConected() {
        return conected;
    }

    public void setConected(boolean conected) {
        this.conected = conected;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
