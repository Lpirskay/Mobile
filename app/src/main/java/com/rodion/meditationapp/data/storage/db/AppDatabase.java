package com.rodion.meditationapp.data.storage.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.rodion.meditationapp.data.storage.dao.PhotoDao;
import com.rodion.meditationapp.data.storage.dao.UserDao;
import com.rodion.meditationapp.data.storage.entity.Photo;
import com.rodion.meditationapp.data.storage.entity.User;

import kotlin.jvm.Volatile;

@Database(entities = {User.class, Photo.class}, version = 6)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract PhotoDao photoDao();

    @Volatile
    static AppDatabase instance;

    public static AppDatabase getAppDatabase(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    DATABASE_NAME
            ).fallbackToDestructiveMigration().build();
        }

        return instance;

    }

    static final String DATABASE_NAME = "APP_DATABASE";
}
