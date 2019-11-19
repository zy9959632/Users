package com.example.administrator.day02_text.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.day02_text.R;
import com.example.administrator.day02_text.view.adapter.FPAdapter;
import com.example.administrator.day02_text.view.fragment.CollectFragment;
import com.example.administrator.day02_text.view.fragment.MyFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout mTab;
    private ViewPager mVp;
    private ArrayList<Fragment> mFragments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mTab = (TabLayout) findViewById(R.id.tab);
        mVp = (ViewPager) findViewById(R.id.vp);
        mFragments = new ArrayList<>();
        mFragments.add(new MyFragment());
        mFragments.add(new CollectFragment());
        FPAdapter fpAdapter = new FPAdapter(getSupportFragmentManager(), mFragments);
        mVp.setAdapter(fpAdapter);
        mTab.setupWithViewPager(mVp);
        mTab.getTabAt(0).setText("我的");
        mTab.getTabAt(1).setText("收藏");
        mTab.addOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        if (position==1){
            CollectFragment fragment = (CollectFragment) mFragments.get(1);
            fragment.initData();
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
