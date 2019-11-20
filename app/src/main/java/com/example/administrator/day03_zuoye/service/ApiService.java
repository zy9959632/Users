package com.example.administrator.day03_zuoye.service;

import com.example.administrator.day03_zuoye.bean.DataInfos;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2019/11/20 0020.
 */

public interface ApiService {
    //http://gank.io/api/data/%E7%A6%8F%E5%88%A9/10/1
    String mBaseUrl = "http://gank.io/api/data/";
    @GET("%E7%A6%8F%E5%88%A9/10/1")
    Observable<DataInfos> getDataInfoa();
}
