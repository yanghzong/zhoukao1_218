package com.example.zhoukao1_218.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;


import com.example.zhoukao1_218.entity.BannerEntity;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class ViewPageAdapter extends PagerAdapter {
    private Context context;
    private List<BannerEntity.ResultBean> list;
    private GenericDraweeHierarchyBuilder mBuilder;


    public ViewPageAdapter(Context context, List<BannerEntity.ResultBean> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        mBuilder = new GenericDraweeHierarchyBuilder(Resources.getSystem());
        String imageUrl = list.get(position).getImageUrl();
        RoundingParams roundingParams = RoundingParams.fromCornersRadius(100f);

        GenericDraweeHierarchy build = mBuilder.setRoundingParams(roundingParams).build();
        SimpleDraweeView img = new SimpleDraweeView(context);
        img.setScaleType(SimpleDraweeView.ScaleType.FIT_XY);
        img.setHierarchy(build);
        img.setImageURI(imageUrl);
        container.addView(img);
        return img;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((View) object);
    }
}
