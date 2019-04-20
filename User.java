package com.example.instapost_java;

public class User {
    public String name, email, nickname,uid;

    public User() {}

    public User(String uname, String uemail, String unickname, String uid) {
        this.name = uname;
        this.email = uemail;
        this.nickname = unickname;
        this.uid = uid;
    }

    public User(String name, String email, String nickname) {
        this.name = name;
        this.email = email;
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}

