package com.example.administrator.day02_text.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.PopupWindow;

import com.example.administrator.day02_text.MyApp;
import com.example.administrator.day02_text.R;
import com.example.administrator.day02_text.model.bean.RecentBean;
import com.example.administrator.day02_text.presenter.MainPresenter;
import com.example.administrator.day02_text.view.MainView;
import com.example.administrator.day02_text.view.activity.ShowActivity;
import com.example.administrator.day02_text.view.adapter.RLVAdapter;
import com.example.xts.greendaodemo.db.RecentBeanDao;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment implements MainView{


    private View view;
    private RecyclerView mRecycler;
    private ArrayList<RecentBean> mRecentBeans;
    private RLVAdapter mRLVAdapter;
    private MainPresenter mMainPresenter;
    private PopupWindow mPopupWindow;
    private RecentBeanDao mRecentBeanDao;

    public MyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_my, container, false);
        mMainPresenter = new MainPresenter(this);
        initData();
        initView(inflate);
        mRecentBeanDao = MyApp.mDaoSession.getRecentBeanDao();
        return inflate;
    }

    private void initData() {
        mMainPresenter.requestData();
    }

    private void initView(View inflate) {
        mRecycler = (RecyclerView) inflate.findViewById(R.id.recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycler.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        mRecentBeans = new ArrayList<>();
        mRLVAdapter = new RLVAdapter(getContext(), mRecentBeans);
        mRecycler.setAdapter(mRLVAdapter);


        mRLVAdapter.setOnClick(new RLVAdapter.onClick() {
            @Override
            public void onClick(String url) {
                EventBus.getDefault().postSticky(url);
                startActivity(new Intent(getContext(), ShowActivity.class));
            }

            @Override
            public void onLongClick(int position) {
                popwindow(position);
            }
        });
    }

    private void popwindow(final int position) {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.pop_item, null);
        Button btn_yes = inflate.findViewById(R.id.btn_yes);
        Button btn_no = inflate.findViewById(R.id.btn_no);
        mPopupWindow = new PopupWindow(inflate, GridLayout.LayoutParams.WRAP_CONTENT,GridLayout.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.showAtLocation(inflate, Gravity.CENTER,0,0);
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecentBeanDao.insertOrReplace(mRecentBeans.get(position));
                mPopupWindow.dismiss();
            }
        });
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
    }

    @Override
    public void onNext(List<RecentBean> recentBean) {
        mRecentBeans.addAll(recentBean);
        mRLVAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(Throwable e) {
        Log.e("TAG", "onError: ", e);
    }
}
