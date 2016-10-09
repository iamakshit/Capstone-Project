package com.android.akshitgupta.capstoneproject.object;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;
    private String userName;
    private String userGender;
    private String dobDate;
    private String dobTIme;
    private String userImage;
    private String cityName;
    private String coordLat;
    private String coordLong;

    public User() {

    }

    public User(Integer id, String userName, String userGender, String dobDate, String dobTIme, String userImage, String cityName, String coordLat, String coordLong) {
        this.id = id;
        this.userName = userName;
        this.userGender = userGender;
        this.dobDate = dobDate;
        this.dobTIme = dobTIme;
        this.userImage = userImage;
        this.cityName = cityName;
        this.coordLat = coordLat;
        this.coordLong = coordLong;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public String getDobDate() {
        return dobDate;
    }

    public void setDobDate(String dobDate) {
        this.dobDate = dobDate;
    }

    public String getDobTIme() {
        return dobTIme;
    }

    public void setDobTIme(String dobTIme) {
        this.dobTIme = dobTIme;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCoordLat() {
        return coordLat;
    }

    public void setCoordLat(String coordLat) {
        this.coordLat = coordLat;
    }

    public String getCoordLong() {
        return coordLong;
    }

    public void setCoordLong(String coordLong) {
        this.coordLong = coordLong;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userGender='" + userGender + '\'' +
                ", dobDate='" + dobDate + '\'' +
                ", dobTIme='" + dobTIme + '\'' +
                ", userImage='" + userImage + '\'' +
                ", cityName='" + cityName + '\'' +
                ", coordLat='" + coordLat + '\'' +
                ", coordLong='" + coordLong + '\'' +
                '}';
    }
}

