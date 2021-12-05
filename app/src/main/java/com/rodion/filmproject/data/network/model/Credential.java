package com.rodion.filmproject.data.network.model;

public class Credential {
    String email;
    String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Credential(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
