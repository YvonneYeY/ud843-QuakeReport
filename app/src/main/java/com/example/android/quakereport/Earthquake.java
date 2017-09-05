package com.example.android.quakereport;

/**
 * Created by Yvonne on 2017/2/20.
 */

public class Earthquake {
    private double mag;
    private String place;
    private String timed;
    private String timeh;
    private String url;
    public Earthquake(double mag,String place,String timed,String timeh ,String url) {
        this.mag=mag;
        this.place=place;
        this.timed=timed;
        this.timeh=timeh;
        this.url=url;
    }


    public double getMag() {
        return mag;
    }

    public String getPlace() {
        return place;
    }

    public String getTimed() {
        return timed;
    }

    public String getTimeh() {
        return timeh;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Earthquake: " + mag + "|" + place + "|" + timed + "|" + timeh + "|" + url;
    }
}
