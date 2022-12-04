package com.example.shareourwedding;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class RecyclerItem2 {

    private String title;
    private String content;
    private String userId;

    public RecyclerItem2(){};



    public RecyclerItem2(String title, String content, String userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }


    public String getUserId() { return userId; }

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



}