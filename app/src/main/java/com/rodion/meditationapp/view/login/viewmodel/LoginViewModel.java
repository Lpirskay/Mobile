package com.rodion.meditationapp.view.login.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.rodion.meditationapp.data.network.model.Credential;
import com.rodion.meditationapp.data.network.model.Error;
import com.rodion.meditationapp.data.network.model.Quote;
import com.rodion.meditationapp.data.network.model.QuotesResponse;
import com.rodion.meditationapp.data.storage.entity.User;
import com.rodion.meditationapp.data.network.service.MeditationService;
import com.rodion.meditationapp.data.storage.dao.UserDao;
import com.rodion.meditationapp.data.storage.db.AppDatabase;

import java.net.UnknownHostException;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

public class LoginViewModel extends AndroidViewModel {
    MeditationService service;
    UserDao userDao;
    public MutableLiveData<User> userInfo = new MutableLiveData<>();
    public MutableLiveData<String> errorInfo = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        userDao = AppDatabase.getAppDatabase(application).userDao();
        service = MeditationService.getService();
    }

    public void loginUser(String email, String pass) {
        Credential c = new Credential(email, pass);
        Disposable d = service.loginUser(c)
                .doOnError((t) -> {
                    if(t instanceof UnknownHostException) {
                        errorInfo.postValue("No internet connection");
                    }
                    ResponseBody response = ((HttpException) t).response().errorBody();
                    Error err = new Gson().fromJson(response.charStream(), Error.class);
                    errorInfo.postValue(err.getError());
                })
                .doAfterSuccess((user) -> {
                    userInfo.postValue(user);
                })
                .flatMap((u) -> {
                    u.uid = 1;
                    return userDao.insertUser(u);
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn((t) -> {
                    t.printStackTrace();
                    return 0L;
                })
                .subscribe();
    }

    public void getUser() {
        userDao.getUser(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<User>() {
                    @Override
                    public void onSuccess(@NonNull User user) {
                        userInfo.setValue(user);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    public void registerUser(String email, String pass) {
        User newUser = new User(1, email, pass);
        userDao.insertUser(newUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                new DisposableSingleObserver<Long>() {
                    @Override
                    public void onSuccess(@NonNull Long aLong) {
                        Log.d("Inserted User: ", aLong.toString());
                        userInfo.postValue(newUser);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    public void printQuotes() {
        service.getQuotes()
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new DisposableSingleObserver<QuotesResponse>() {
                    @Override
                    public void onSuccess(@NonNull QuotesResponse quotesResponse) {
                        if(quotesResponse.success.equals("true")) {
                            List<Quote> quoteList = quotesResponse.data;
                            for(Quote q : quoteList) {
                                Log.d("Get Quotes", "Title: "+q.getTitle());
                            }
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("Get Quotes", "Problems with request");
                        e.printStackTrace();
                    }
                });
    }
}
