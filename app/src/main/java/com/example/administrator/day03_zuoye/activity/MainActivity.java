package com.example.administrator.day03_zuoye.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.administrator.day03_zuoye.R;
import com.example.administrator.day03_zuoye.adapter.RLVAdapter;
import com.example.administrator.day03_zuoye.adapter.VPAdapter;
import com.example.administrator.day03_zuoye.bean.DataInfos;
import com.example.administrator.day03_zuoye.service.ApiService;

import java.io.File;
import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private Toolbar mToolbar;
    private RecyclerView mRecycler;
    private NavigationView mNavView;
    private DrawerLayout mDrawer;
    private LinearLayout mLl;
    private ViewPager mVp;
    private ArrayList<DataInfos.ResultsBean> mResultsBeans;
    private VPAdapter mVPAdapter;
    private ArrayList<DataInfos.ResultsBean> mList;
    private RLVAdapter mRLVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mLl = (LinearLayout) findViewById(R.id.ll);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mVp = (ViewPager) findViewById(R.id.vp);
        mRecycler = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecycler.setLayoutManager(manager);
        mNavView = (NavigationView) findViewById(R.id.nav_view);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.srl_content_empty, R.string.srl_content_empty);
        mDrawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        initVPData();
        initRecyclerData();
        mVp.addOnPageChangeListener(this);
    }

    private void initRecyclerData() {
        mList = new ArrayList<>();
        retrofitData();
        mRLVAdapter = new RLVAdapter(this, mList);
        mRecycler.setAdapter(mRLVAdapter);
        mRLVAdapter.setMyItemOnClick(new RLVAdapter.MyItemOnClick() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(MainActivity.this, ShowActivity.class);
                intent.putExtra("list",mRLVAdapter.mList);
                intent.putExtra("position",position);
                startActivity(intent);
            }
        });
        mRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = mRecycler.getLayoutManager();
                int position = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                mVp.setCurrentItem(position);
            }
        });
    }

    private void initVPData() {
        mResultsBeans = new ArrayList<>();
        retrofitData();
        mVPAdapter = new VPAdapter(this, mResultsBeans);
        mVp.setAdapter(mVPAdapter);
    }

    private void retrofitData() {
        File dir = getCacheDir().getAbsoluteFile();
        Cache cache = new Cache(dir,1024*1024*20);
        OkHttpClient client = new OkHttpClient.Builder()
                .cache(cache)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService.mBaseUrl)
                .build();
        retrofit.create(ApiService.class)
                .getDataInfoa()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataInfos>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DataInfos dataInfos) {
                        if (dataInfos!=null){
                            mVPAdapter.addData(dataInfos.getResults());
                            mRLVAdapter.addData(dataInfos.getResults());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onError: ", e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        LinearLayoutManager layoutManager = (LinearLayoutManager)mRecycler.getLayoutManager();
        layoutManager.scrollToPositionWithOffset(position,0);
        //mRecycler.smoothScrollToPosition(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
