package com.example.zhoukao1_218.showmvp.model;

import com.example.zhoukao1_218.utils.MyCallBack;

import java.lang.reflect.Type;

public interface modelInterface {
    void  getShowIM(String url, MyCallBack myCallBack, Type type);
    void   getBannerIM(String  url,MyCallBack myCallBack,Type type);
}
