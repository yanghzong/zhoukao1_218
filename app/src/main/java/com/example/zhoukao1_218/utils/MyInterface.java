package com.example.zhoukao1_218.utils;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;


public interface MyInterface {

    @GET("small/commodity/v1/bannerShow")
    Observable<ResponseBody> getBanner();

    @GET("small/commodity/v1/commodityList")
    Observable<ResponseBody> getShop();
}
