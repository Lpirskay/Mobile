package com.rodion.filmprojecttest.view.StartScreen.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rodion.filmprojecttest.data.network.model.Film;
import com.rodion.filmprojecttest.data.network.model.FilmResponse;
import com.rodion.filmprojecttest.data.network.service.ImageResolver;
import com.rodion.filmprojecttest.data.network.service.MovieService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartViewModel extends ViewModel {
    MovieService service;
    ImageResolver imageResolver;

    public MutableLiveData<List<Film>> films = new MutableLiveData<>();

    public StartViewModel() {
        service = MovieService.getInstance();
        imageResolver = ImageResolver.getInstance();
    }

    public void getFilms() {
        service.getMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<FilmResponse>() {
                    @Override
                    public void onSuccess(@NonNull FilmResponse filmResponse) {
                        films.postValue(filmResponse.results);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

}
