package com.android.akshitgupta.capstoneproject.object.request;

import java.io.Serializable;

/**
 * Created by akshitgupta on 08/10/16.
 */
public class AstroRequest implements Serializable {

    Integer day;
    Integer month;
    Integer year;
    Integer hour;
    Integer min;
    Double lat;
    Double lon;
    Double tzone;
    String name;
    String astroURL;

    public String getAstroURL() {
        return astroURL;
    }

    public void setAstroURL(String astroURL) {
        this.astroURL = astroURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getTzone() {
        return tzone;
    }

    public void setTzone(Double tzone) {
        this.tzone = tzone;
    }
}
