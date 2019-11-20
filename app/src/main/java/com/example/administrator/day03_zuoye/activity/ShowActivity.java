package com.example.administrator.day03_zuoye.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.day03_zuoye.R;
import com.example.administrator.day03_zuoye.bean.DataInfos;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private Banner mBanner;
    private TextView mTvPosition;
    private ArrayList<String> mResultsBeans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();
    }

    private void initView() {
        mBanner = (Banner) findViewById(R.id.banner);
        mTvPosition = (TextView) findViewById(R.id.tv_position);
        mResultsBeans = new ArrayList<>();
        Intent intent = getIntent();
        ArrayList<DataInfos.ResultsBean> list = (ArrayList<DataInfos.ResultsBean>) intent.getSerializableExtra("list");
        for (int i = 0; i < list.size(); i++) {
            String url = list.get(i).getUrl();
            mResultsBeans.add(url);
            //mTvPosition.setText("第 "+(i)+" 张/共 "+mResultsBeans.size()+" 张");
        }
        mBanner.setImages(mResultsBeans)
                .setBannerStyle(BannerConfig.NUM_INDICATOR)
                .setBannerAnimation(Transformer.ZoomOut)
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context)
                                .load(path)
                                .into(imageView);
                    }
                })
                .start();
        int mPosition = intent.getIntExtra("possition", -1);
        mTvPosition.setText("第 "+(mPosition+2)+" 张/共 "+mResultsBeans.size()+" 张");
        mBanner.setOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mTvPosition.setText("第 "+(position+1)+" 张/共 "+mResultsBeans.size()+" 张");
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
