package com.rodion.filmproject.view.LoginScreen.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.rodion.filmproject.R;
import com.rodion.filmproject.data.network.model.Credential;
import com.rodion.filmproject.data.network.model.Error;
import com.rodion.filmproject.data.network.service.UserService;
import com.rodion.filmproject.data.storage.model.User;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class LoginViewModel extends AndroidViewModel {
    SharedPreferences preferences;
    UserService service;
    Gson gson;

    final String USER_DATA = "user_data";

    public MutableLiveData<String> errorInfo = new MutableLiveData<>();
    public MutableLiveData<User> userInfo = new MutableLiveData<>();
    public MutableLiveData<Boolean> userRegistered = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        Context context = application.getApplicationContext();
        preferences = context.getSharedPreferences(
                context.getString(R.string.user_data),
                Context.MODE_PRIVATE
        );
        service = UserService.getInstance();
        gson = new Gson();
    }

    public void login(String email, String password) {
        service.loginUser(new Credential(email, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<User>() {
                    @Override
                    public void onSuccess(@NonNull User user) {
                        preferences.edit()
                                .putString(USER_DATA, gson.toJson(user))
                                .apply();
                        userInfo.postValue(user);
                        userRegistered.postValue(true);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        if(e instanceof HttpException) {
                            ResponseBody body = ((HttpException) e).response().errorBody();
                            Error err = gson.fromJson(body.charStream(), Error.class);
                            errorInfo.postValue(err.getError());
                        }
                    }
                });

    }

    public void getUser() {
        String jsonData = preferences.getString(USER_DATA, "");
        User u = gson.fromJson(jsonData, User.class);
        if(u != null) {
            userInfo.postValue(u);
        }
    }
}