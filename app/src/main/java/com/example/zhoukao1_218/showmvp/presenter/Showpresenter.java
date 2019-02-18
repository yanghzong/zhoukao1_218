package com.example.zhoukao1_218.showmvp.presenter;

import com.example.zhoukao1_218.entity.BannerEntity;
import com.example.zhoukao1_218.entity.ShowEntity;
import com.example.zhoukao1_218.inter.ApiUrl;
import com.example.zhoukao1_218.showmvp.view.Showview;
import com.example.zhoukao1_218.showmvp.model.Showmodel;
import com.example.zhoukao1_218.utils.MyCallBack;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Showpresenter implements presenterInterface{
    private Showmodel showmodel;
    private Showview showview;

    public  void  attach(Showview showview){
        this.showview=showview;
        showmodel=new Showmodel();
    }


    public  void  detach(){
        if (showview!=null){
            showview=null;
        }
        if (showmodel!=null){
            showmodel=null;
        }
    }

    @Override
    public void getBannerIP() {
        Type type=new TypeToken<BannerEntity>(){}.getType();
        showmodel.getBannerIM(ApiUrl.BaseUrl, new MyCallBack() {
            @Override
            public void onSuccess(Object obj) {
                BannerEntity bannerEntity= (BannerEntity) obj;
                if (bannerEntity!=null){
                   showview.successful(bannerEntity);
                }
            }

            @Override
            public void onFailed(String str) {
                showview.failedure(str);
            }


        },type);
    }

    @Override
    public void getShowIP() {
        Type type=new TypeToken<ShowEntity>(){}.getType();
        showmodel.getShowIM(ApiUrl.BaseUrl, new MyCallBack() {
            @Override
            public void onSuccess(Object obj) {
                ShowEntity showEntity= (ShowEntity) obj;
                if (showEntity!=null){
                    showview.rxxp(showEntity.getResult().getRxxp());
                    showview.pzsh(showEntity.getResult().getPzsh());
                    showview.mlss(showEntity.getResult().getMlss());
                }
            }

            @Override
            public void onFailed(String str) {
                showview.failedure(str);
            }


        },type);
    }
}
