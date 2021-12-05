package com.rodion.meditationapp.data.storage.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.rodion.meditationapp.data.storage.entity.Photo;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface PhotoDao {
    @Query("SELECT * FROM photo;")
    Single<List<Photo>> getAllPhotos();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertPhotos(List<Photo> photos);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPhoto(Photo photo);
}
