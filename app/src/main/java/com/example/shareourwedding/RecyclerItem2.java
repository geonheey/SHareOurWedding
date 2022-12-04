package com.example.shareourwedding;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class RecyclerItem2 {

    private String title;
    private String content;
    private String coupleId;

    public RecyclerItem2(){};



    public RecyclerItem2(String title, String content, String coupleId) {
        this.title = title;
        this.content = content;
        this.coupleId = coupleId;
    }


    public String getCoupleId() { return coupleId; }

    public void setCoupleId(String userId) {
        this.coupleId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



}