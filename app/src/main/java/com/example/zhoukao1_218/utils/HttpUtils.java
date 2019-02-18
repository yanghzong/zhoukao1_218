package com.example.zhoukao1_218.utils;

import android.os.Handler;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HttpUtils {
    private OkHttpClient client;
    private static HttpUtils instance;
    private Handler handler = new Handler();
    private final Retrofit.Builder builder1;

    private HttpUtils() {
        client = new OkHttpClient.Builder()
                .build();
        Retrofit.Builder builder = new Retrofit.Builder();
        builder1 = builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client);
    }

    //双重锁
    public static HttpUtils getInstance() {
        if (instance == null) {
            synchronized (HttpUtils.class) {
                if (instance == null) {
                    instance = new HttpUtils();
                }
            }
        }
        return instance;
    }
    //创建Retrofit对象
    private MyInterface  getRetrofitIViewObject(String url) {
        Retrofit retrofit = builder1.baseUrl(url)
                .build();
        MyInterface myInterface = retrofit.create(MyInterface.class);
        return myInterface;
    }

    public HttpUtils getbanner(String url, final ICallBack callBack) {
        //得到retrofit对象
        MyInterface retrofitIViewObject = getRetrofitIViewObject(url);
        retrofitIViewObject.getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(callBack));
        return instance;


    }
    public HttpUtils getShow(String url, final ICallBack callBack) {
        //得到retrofit对象
        MyInterface retrofitIViewObject = getRetrofitIViewObject(url);
        retrofitIViewObject.getShop()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getObserver(callBack));
        return instance;


    }
    public Observer getObserver(final ICallBack callBack) {
        Observer observer = new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                if(callBack!=null){
                    try {
                        String result = responseBody.string();
                        if(!result.equals("")){
                            callBack.onSuccessRXU(result);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        if(callBack!=null){
                            callBack.onFailderRXU(e.getMessage());
                        }
                    }
                }
            }
        };
        return observer;

    }
    //接口
    public interface ICallBack{
        void onSuccessRXU(String data);
        void onFailderRXU(String str);
    }

}
