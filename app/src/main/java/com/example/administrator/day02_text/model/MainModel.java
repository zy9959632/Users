package com.example.administrator.day02_text.model;

import com.example.administrator.day02_text.model.bean.RecentBean;
import com.example.administrator.day02_text.model.bean.Users;
import com.example.administrator.day02_text.model.service.ApiService;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2019/11/19 0019.
 */

public class MainModel {

    public void requestData(final MainCallBack callBack){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofit.create(ApiService.class)
                .getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Users>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Users users) {
                        List<RecentBean> recent = users.getRecent();
                        callBack.onNext(recent);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
