package com.rodion.meditationapp.data.network.service;

import com.rodion.meditationapp.data.network.model.Credential;
import com.rodion.meditationapp.data.network.model.FeelingsResponse;
import com.rodion.meditationapp.data.network.model.QuotesResponse;
import com.rodion.meditationapp.data.storage.entity.User;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MeditationService {
    @GET("quotes")
    Single<QuotesResponse> getQuotes();

    @GET("feelings")
    Single<FeelingsResponse> getFeelings();

    @POST("user/login")
    Single<User> loginUser(@Body Credential c);

    static MeditationService getService() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
        return new Retrofit.Builder()
                .baseUrl("http://mskko2021.mad.hakta.pro/api/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(MeditationService.class);
    }
}
