package com.example.administrator.day02_text.model.service;

import com.example.administrator.day02_text.model.bean.Users;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2019/11/19 0019.
 */

public interface ApiService {
    //http://news-at.zhihu.com/api/4/news/hot
    String mBaseUrl = "http://news-at.zhihu.com/";
    @GET("api/4/news/hot")
    Observable<Users> getUsers();
}
