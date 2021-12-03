package com.rodion.meditationapp.data.storage.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "password")
    public String password;

    @ColumnInfo(name = "nickName")
    public String nickName;

    @ColumnInfo(name = "avatar")
    public String avatar;

    @ColumnInfo(name = "token")
    public String token;


    public User(int uid, String email, String password) {
        this.uid = uid;
        this.email = email;
        this.password = password;
    }

    @NonNull
    public String toString() {
        return "{uid: "+uid+", email: " + email + ", password: "+password+"}";
    }
}
