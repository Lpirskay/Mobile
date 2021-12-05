package com.rodion.filmproject.data.network.service;

import androidx.annotation.NonNull;

import com.rodion.filmproject.data.network.model.ConfigurationResponse;
import com.rodion.filmproject.data.network.model.FilmResponse;

import java.io.IOException;

import io.reactivex.Single;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface MovieService {
    @GET("discover/movie")
    Single<FilmResponse> getMovies();
    @GET("configuration")
    Single<ConfigurationResponse> getConfiguration();

    static MovieService getInstance() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(new Interceptor() {
                    @NonNull
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        HttpUrl url = chain.request().url()
                                    .newBuilder()
                                    .addQueryParameter("api_key", API_KEY)
                                    .build();
                        Request newReq = chain.request().newBuilder()
                                            .url(url)
                                            .build();
                        return chain.proceed(newReq);
                    }
                }).build();
        return new Retrofit.Builder()
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://api.themoviedb.org/3/")
                    .build()
                    .create(MovieService.class);
    }

    String API_KEY = "368f92132c8a4bed38d9d6c88efeb092";
}
