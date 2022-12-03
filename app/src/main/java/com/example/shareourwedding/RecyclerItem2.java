package com.example.shareourwedding;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class RecyclerItem2 {

    private String userId;
    private String title;
    private String content;
    private String ttAct;

    public RecyclerItem2(){};



    public RecyclerItem2(String mTitle, String mContents) {
        this.title = mTitle;
        this.content = mContents;
        //this.ttAct = ttAct;
    }


//    public String getUserId() {
//        return userId;
//    }

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

    public String getTtAct() {
        return ttAct;
    }

    public void setTtAct(String content) {
        this.content = ttAct;
    }


//
//    public Review_RecyclerItem2(String title, String content, String ttAct) {
//        //this.userId = userId;
//
//    }







}