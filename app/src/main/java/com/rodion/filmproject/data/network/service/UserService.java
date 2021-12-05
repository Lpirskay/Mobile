package com.rodion.filmproject.data.network.service;

import com.rodion.filmproject.data.network.model.Credential;
import com.rodion.filmproject.data.storage.model.User;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("user/login")
    Single<User> loginUser(@Body Credential c);

    static UserService getInstance() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                                    .addInterceptor(logging)
                                    .build();
        return new Retrofit.Builder()
                    .baseUrl("http://mskko2021.mad.hakta.pro/api/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(UserService.class);
    }
}
