package com.android.akshitgupta.capstoneproject.object.request;

import java.io.Serializable;

/**
 * Created by akshitgupta on 08/10/16.
 */
public class AstroRequest implements Serializable {

    String day;
    String month;
    String year;
    String hour;
    String min;
    String lat;
    String lon;
    Double tzone;
    String name;
    String astroURL;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public Double getTzone() {
        return tzone;
    }

    public void setTzone(Double tzone) {
        this.tzone = tzone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAstroURL() {
        return astroURL;
    }

    public void setAstroURL(String astroURL) {
        this.astroURL = astroURL;
    }
}
