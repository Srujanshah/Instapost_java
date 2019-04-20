package com.example.instapost_java;

public class Upload {
    private String mName;
    private String ot;
    private String mImageUrl;
    public String userID;

    public Upload() {}

    public Upload(String name, String ot1, String ImageUrl, String uID) {
        if (name.trim().equals("")) {
            name = "No Name";
        }
        ot = ot1;
        mName = name;
        mImageUrl = ImageUrl;
        userID = uID;
    }

    public void setOt(String ot) {
        this.ot = ot;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getOt() {
        return ot;
    }
}
