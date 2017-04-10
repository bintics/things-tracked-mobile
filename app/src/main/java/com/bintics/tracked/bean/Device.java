package com.bintics.tracked.bean;

/**
 * Created by andru-fb on 20/03/17.
 */

public class Device {

    private String name;
    private boolean connected;
    private Coord coords;

    public Device() {
    }

    public Device(String uid, String name, double latitude, double longitude, String label, boolean conected) {
        this.name = name;
        this.connected = conected;
    }

    public Coord getCords() {
        return coords;
    }

    public void setCords(Coord coords) {
        this.coords = coords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

}
