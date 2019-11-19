package com.example.administrator.day02_text.view;

import com.example.administrator.day02_text.model.bean.RecentBean;

import java.util.List;

/**
 * Created by Administrator on 2019/11/19 0019.
 */

public interface MainView {
    void onNext(List<RecentBean> recentBean);
    void onError(Throwable e);
}
