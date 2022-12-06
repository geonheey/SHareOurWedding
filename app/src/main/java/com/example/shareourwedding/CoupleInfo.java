package com.example.shareourwedding;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class CoupleInfo {
    public String wname;
    public String hname;
    public String date;
    public String place;
    public String idToken;
    public String imageUrl;


    public CoupleInfo() {}

    public CoupleInfo(String idToken, String hname, String wname, String place, String date, String imageUrl) {
        this.hname = hname;
        this.wname = wname;
        this.place = place;
        this.date = date;
        this.idToken = idToken;
        this.imageUrl = imageUrl;

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }


    @Override
    public String toString() {
        return "User{" +
                "husband_name='" + hname + '\'' +
                ", wife_name='" + wname + '\'' +
                ", place='"+place+'\''+
                ", date=" + date +
                '}';
    }
}