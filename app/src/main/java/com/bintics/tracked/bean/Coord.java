package com.bintics.tracked.bean;

/**
 * Created by federico on 08/04/2017.
 */

public class Coord {

    private String label;
    private double lat;
    private double lng;

    public Coord() {

    }

    public Coord(String label, double lat, double lng) {
        this.label = label;
        this.lat = lat;
        this.lng = lng;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
