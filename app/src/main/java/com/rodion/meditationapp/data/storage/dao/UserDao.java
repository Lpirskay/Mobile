package com.rodion.meditationapp.data.storage.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.rodion.meditationapp.data.storage.entity.User;

import java.util.List;

import io.reactivex.Single;


@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    List<User> getAllUsersByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE uid LIKE :uid")
    Single<User> getUser(int uid);

    @Query("SELECT * FROM user WHERE email LIKE :login AND " +
            "password LIKE :password LIMIT 1")
    User findByLoginPassword(String login, String password);

    @Insert
    void insertUsers(User... user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<Long> insertUser(User user);
}
