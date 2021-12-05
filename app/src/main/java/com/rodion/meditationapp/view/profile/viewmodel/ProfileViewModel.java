package com.rodion.meditationapp.view.profile.viewmodel;

import android.app.Application;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rodion.meditationapp.data.storage.dao.PhotoDao;
import com.rodion.meditationapp.data.storage.db.AppDatabase;
import com.rodion.meditationapp.data.storage.entity.Photo;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ProfileViewModel extends AndroidViewModel {

    private PhotoDao photoDao;

    public MutableLiveData<List<Photo>> photosData = new MutableLiveData<>();

    public ProfileViewModel(@NonNull Application application) {
        super(application);
        photoDao = AppDatabase.getAppDatabase(application.getApplicationContext()).photoDao();
    }

    public void saveImages(List<Uri> imageUris) {
        photoDao.insertPhotos(Photo.createPhotos(imageUris))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        loadImages();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    public void loadImages() {
        photoDao.getAllPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<Photo>>() {
                    @Override
                    public void onSuccess(@NonNull List<Photo> photos) {
                        photosData.postValue(photos);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }
                });
    }
}