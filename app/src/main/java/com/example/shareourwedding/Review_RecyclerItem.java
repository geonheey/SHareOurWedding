package com.example.shareourwedding;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Review_RecyclerItem {

    private String userId;
    private String title;
    private String content;

    public Review_RecyclerItem(){};



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public Review_RecyclerItem(String userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }







}