package com.example.zhoukao1_218;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zhoukao1_218.GreenDao.DaoMaster;
import com.example.zhoukao1_218.GreenDao.DaoSession;
import com.example.zhoukao1_218.GreenDao.GreenDaoDao;
import com.example.zhoukao1_218.adapter.MlshAdapter;
import com.example.zhoukao1_218.adapter.PzshAdapter;
import com.example.zhoukao1_218.adapter.ShowAdapter;
import com.example.zhoukao1_218.adapter.ViewPageAdapter;
import com.example.zhoukao1_218.entity.BannerEntity;
import com.example.zhoukao1_218.entity.ShowEntity;
import com.example.zhoukao1_218.showmvp.presenter.Showpresenter;
import com.example.zhoukao1_218.showmvp.view.Showview;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements Showview {

    @BindView(R.id.vp_banner)
    ViewPager vpBanner;
    @BindView(R.id.xrv_rxxp)
    XRecyclerView xrvRxxp;
    @BindView(R.id.xrv_pzsh)
    XRecyclerView xrvPzsh;
    @BindView(R.id.xrv_mlss)
    XRecyclerView xrvMlss;
    private List<BannerEntity.ResultBean> bannerlist;
    private Unbinder bind;
    private List<ShowEntity.ResultBean.RxxpBean.CommodityListBean> rxxplist;
    private List<ShowEntity.ResultBean.PzshBean.CommodityListBeanX> pzshlist;
    private List<ShowEntity.ResultBean.MlssBean.CommodityListBeanXX> mlsslist;
    private Showpresenter showpresenter;
    private ShowAdapter showAdapter;
    private Handler handler = new Handler();
    private PzshAdapter pzshAdapter;
    private MlshAdapter mlshAdapter;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private GreenDaoDao daoDao;
    private ViewPageAdapter viewPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind = ButterKnife.bind(this);
          //初始化集合
        initList();
        //初始化bannerp层
        initPresenter();
        //banner的适配器
        initAdapter();
        //下拉刷新  上拉加载
        initLoad();
    }

    private void initList() {
        bannerlist = new ArrayList<>();
        rxxplist = new ArrayList<>();
        pzshlist = new ArrayList<>();
        mlsslist = new ArrayList<>();
    }

    private void initLoad() {
        xrvRxxp.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
            }
        });
    }

    private void initAdapter() {
        showAdapter = new ShowAdapter(this, rxxplist);
        pzshAdapter = new PzshAdapter(this, pzshlist);
        mlshAdapter = new MlshAdapter(this, mlsslist);
        viewPageAdapter = new ViewPageAdapter(this,bannerlist);
        vpBanner.setAdapter(viewPageAdapter);
        //recycleview的点击事件
        mlshAdapter.setOnShowListener(new MlshAdapter.OnShowListener() {
            @Override
            public void onShowClick(String CommodityName) {
                Toast.makeText(MainActivity.this, CommodityName, Toast.LENGTH_SHORT).show();
            }
        });
        //recycleview的点击事件
        pzshAdapter.setOnShowListener(new PzshAdapter.OnShowListener() {
            @Override
            public void onShowClick(String CommodityName) {
                Toast.makeText(MainActivity.this, CommodityName, Toast.LENGTH_SHORT).show();
            }
        });
        //recycleview的点击事件
        showAdapter.setOnShowListener(new ShowAdapter.OnShowListener() {
            @Override
            public void onShowClick(String CommodityName) {
                Toast.makeText(MainActivity.this, CommodityName, Toast.LENGTH_SHORT).show();
            }
        });
        xrvRxxp.setAdapter(showAdapter);
        xrvPzsh.setAdapter(pzshAdapter);
        xrvMlss.setAdapter(mlshAdapter);
        //布局管理器设置
        xrvRxxp.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        xrvPzsh.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        xrvMlss.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    private void initPresenter() {

        showpresenter = new Showpresenter();
        showpresenter.attach(this);
        showpresenter.getBannerIP();
        showpresenter.getShowIP();
    }



    @Override
    public void failedure(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void rxxp(List<ShowEntity.ResultBean.RxxpBean> rxxp) {
        if (rxxp != null) {
            for (ShowEntity.ResultBean.RxxpBean rxxpBean : rxxp) {
                rxxplist.clear();
                rxxplist.addAll(rxxpBean.getCommodityList());
                showAdapter.notifyDataSetChanged();
            }

        }
    }

    @Override
    public void pzsh(List<ShowEntity.ResultBean.PzshBean> pzsh) {
        if (pzsh != null) {
            for (ShowEntity.ResultBean.PzshBean pzshBean : pzsh) {
                pzshlist.clear();
                pzshlist.addAll(pzshBean.getCommodityList());
                pzshAdapter.notifyDataSetChanged();
            }

        }
    }

    @Override
    public void mlss(List<ShowEntity.ResultBean.MlssBean> mlss) {
        if (mlss != null) {
            for (ShowEntity.ResultBean.MlssBean mlssBean : mlss) {
                mlsslist.clear();
                mlsslist.addAll(mlssBean.getCommodityList());
                mlshAdapter.notifyDataSetChanged();
            }

        }
    }

    @Override
    public void successful(BannerEntity bannerEntity) {
        List<BannerEntity.ResultBean> result = bannerEntity.getResult();
        if (bannerEntity != null) {
            bannerlist.clear();
        }
            bannerlist.addAll(bannerEntity.getResult());
        viewPageAdapter.notifyDataSetChanged();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
        showpresenter.detach();
    }
}
