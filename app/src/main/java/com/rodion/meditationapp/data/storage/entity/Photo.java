package com.rodion.meditationapp.data.storage.entity;

import android.net.Uri;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Entity
public class Photo {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "imageUri")
    public String imageUri;
    @ColumnInfo(name = "date")
    public String time;

    public Photo(String imageUri, String time) {
        this.imageUri = imageUri;
        this.time = time;
    }

    @Ignore
    public Photo(String imageUri) {
        this.imageUri = imageUri;
        SimpleDateFormat df = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        this.time = df.format(new Date());
    }

    public static List<Photo> createPhotos(List<Uri> imageUris) {
        List<Photo> photos = new ArrayList<>();
        for (Uri imageUri : imageUris) {
            photos.add(new Photo(imageUri.toString()));
        }
        return photos;
    }
}
