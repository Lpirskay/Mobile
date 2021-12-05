package com.rodion.filmproject.data.storage.model;

public class User {

    public String email;

    public String password;

    public String nickName;

    public String avatar;

    public String token;


    public User(String email, String token) {
        this.email = email;
        this.token = token;
    }
}
