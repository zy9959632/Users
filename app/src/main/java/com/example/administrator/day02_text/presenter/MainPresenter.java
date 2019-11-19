package com.example.administrator.day02_text.presenter;

import com.example.administrator.day02_text.model.MainCallBack;
import com.example.administrator.day02_text.model.MainModel;
import com.example.administrator.day02_text.model.bean.RecentBean;
import com.example.administrator.day02_text.view.MainView;

import java.util.List;

/**
 * Created by Administrator on 2019/11/19 0019.
 */

public class MainPresenter {
    private final MainModel mMainModel;
    private MainView mMainView;

    public MainPresenter(MainView mainView) {
        mMainView = mainView;
        mMainModel = new MainModel();
    }
    public void requestData(){
        mMainModel.requestData(new MainCallBack() {
            @Override
            public void onNext(List<RecentBean> recentBean) {
                mMainView.onNext(recentBean);
            }

            @Override
            public void onError(Throwable e) {
                mMainView.onError(e);
            }
        });
    }
}
