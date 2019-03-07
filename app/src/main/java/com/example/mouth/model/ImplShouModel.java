package com.example.mouth.model;


import com.example.mouth.api.ApiService;
import com.example.mouth.bean.Bean;
import com.example.mouth.utils.RetrofitUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ImplShouModel implements IShouModel {

    private ApiService apiService;

    @Override
    public void getIShouModel(String url, final NetCallBack netCallBack)
    {
        apiService = RetrofitUtils.getInstance().create(ApiService.class);
        apiService.shou(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Bean>() {
                    @Override
                    public void onNext(Bean value) {
                        netCallBack.loadSuccess(value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
