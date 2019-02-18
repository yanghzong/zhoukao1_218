package com.example.zhoukao1_218.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhoukao1_218.R;
import com.example.zhoukao1_218.entity.ShowEntity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;


public class PzshAdapter extends XRecyclerView.Adapter<PzshAdapter.ViewHolder> {

    public  interface  OnShowListener{
        void  onShowClick(String CommodityName);

    }
    private OnShowListener onShowListener;
    public  void setOnShowListener(OnShowListener onShowListener){
         this.onShowListener=onShowListener;
    }

    private Context context;
    private List<ShowEntity.ResultBean.PzshBean.CommodityListBeanX> list;

    public PzshAdapter(Context context, List<ShowEntity.ResultBean.PzshBean.CommodityListBeanX> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = View.inflate(context, R.layout.item_show, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
     final ShowEntity.ResultBean.PzshBean.CommodityListBeanX commodityListBeanX = list.get(i);
        viewHolder.sdvImg.setImageURI(commodityListBeanX.getMasterPic());
        viewHolder.tvShopName.setText(commodityListBeanX.getCommodityName());
        viewHolder.tvShopPrice.setText(commodityListBeanX.getPrice()+"");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commodityName = commodityListBeanX.getCommodityName();
                onShowListener.onShowClick(commodityName);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView sdvImg;
        private final TextView tvShopName;
        private final TextView tvShopPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sdvImg = itemView.findViewById(R.id.sdv_img);
            tvShopName = itemView.findViewById(R.id.tv_shopname);
            tvShopPrice = itemView.findViewById(R.id.tv_shopprice);
        }
    }
}
