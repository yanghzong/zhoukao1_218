package com.example.zhoukao1_218.showmvp.view;

import com.example.zhoukao1_218.entity.BannerEntity;
import com.example.zhoukao1_218.entity.ShowEntity;

import java.util.List;

public interface Showview {
    void  rxxp(List<ShowEntity.ResultBean.RxxpBean> rxxp);
    void  pzsh(List<ShowEntity.ResultBean.PzshBean> pzsh);
    void  mlss(List<ShowEntity.ResultBean.MlssBean> mlss);
    void  successful(BannerEntity bannerEntity);
    void   failedure(String str);
}
