package com.example.zhoukao1_218.showmvp.model;

import com.example.zhoukao1_218.utils.HttpUtils;
import com.example.zhoukao1_218.utils.MyCallBack;
import com.google.gson.Gson;

import java.lang.reflect.Type;

public class Showmodel  implements modelInterface {
    public  void  getShowIM(String url, final MyCallBack mycallBack, final Type type){
        HttpUtils.getInstance().getShow(url, new HttpUtils.ICallBack() {
            @Override
            public void onSuccessRXU(String data) {
                Object o = new Gson().fromJson(data, type);
                if(mycallBack!=null){
                    mycallBack.onSuccess(o);
                }
            }

            @Override
            public void onFailderRXU(String str) {
                if(mycallBack!=null){
                   mycallBack.onFailed(str);
                }
            }
        });





    }

    @Override
    public void getBannerIM(String url, final MyCallBack myCallBack, final Type type) {
        HttpUtils.getInstance().getbanner(url, new HttpUtils.ICallBack() {
            @Override
            public void onSuccessRXU(String data) {
                Object o = new Gson().fromJson(data, type);
                if(myCallBack!=null){
                    myCallBack.onSuccess(o);
                }
            }

            @Override
            public void onFailderRXU(String str) {
                if(myCallBack!=null){
                    myCallBack.onFailed(str);
                }
            }
        });





    }
}
