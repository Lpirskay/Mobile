package com.rodion.filmprojecttest.data.network.service;

import androidx.annotation.NonNull;

import com.rodion.filmprojecttest.data.network.model.ConfigurationResponse;
import com.rodion.filmprojecttest.data.network.model.ImagesParams;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ImageResolver {
    private static ImageResolver instance = null;

    private ImagesParams imageConfig;

    ImageResolver() {
        MovieService service = MovieService.getInstance();
        service.getConfiguration()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<ConfigurationResponse>() {
                    @Override
                    public void onSuccess(@NonNull ConfigurationResponse configurationResponse) {
                        imageConfig = configurationResponse.images;
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    public static ImageResolver getInstance() {
        if(instance == null) {
            instance = new ImageResolver();
        }
        return instance;
    }

    public String getPosterUrl(String poster_path) {
        if(imageConfig != null) {
            return imageConfig.base_url+"original"+poster_path;
        } else return null;
    }
}
