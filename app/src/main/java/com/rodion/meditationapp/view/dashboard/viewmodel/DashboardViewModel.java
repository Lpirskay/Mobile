package com.rodion.meditationapp.view.dashboard.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.rodion.meditationapp.data.network.model.Feeling;
import com.rodion.meditationapp.data.network.model.FeelingsResponse;
import com.rodion.meditationapp.data.network.model.Quote;
import com.rodion.meditationapp.data.network.model.QuotesResponse;
import com.rodion.meditationapp.data.network.service.MeditationService;

import java.util.Collections;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class DashboardViewModel extends AndroidViewModel {

    MeditationService service;

    public MutableLiveData<List<Quote>> quotesData = new MutableLiveData<>();
    public MutableLiveData<List<Feeling>> feelingsData = new MutableLiveData<>();
    public MutableLiveData<String> errorData = new MutableLiveData<>();

    public DashboardViewModel(@NonNull Application application) {
        super(application);
        service = MeditationService.getService();
    }

    public void loadQuotes() {
        service.getQuotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<QuotesResponse>() {
                    @Override
                    public void onSuccess(@NonNull QuotesResponse quotesResponse) {
                        if(quotesResponse.success.equals("true")) {
                            quotesData.setValue(quotesResponse.data);
                        } else quotesData.setValue(null);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        errorData.setValue("Network error occurred");
                    }
                });
    }

    public void loadFeelings() {
        service.getFeelings()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<FeelingsResponse>() {
                    @Override
                    public void onSuccess(@NonNull FeelingsResponse feelingsResponse) {
                        if(feelingsResponse.success) {
                            List<Feeling> f = feelingsResponse.data;
                            Collections.sort(f);
                            feelingsData.setValue(f);
                        } else feelingsData.setValue(null);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                        errorData.setValue("Network error occurred");
                    }
                });
    }

}