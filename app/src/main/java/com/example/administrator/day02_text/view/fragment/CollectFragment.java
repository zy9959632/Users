package com.example.administrator.day02_text.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.day02_text.MyApp;
import com.example.administrator.day02_text.R;
import com.example.administrator.day02_text.model.bean.RecentBean;
import com.example.administrator.day02_text.view.adapter.RLVAdapter;
import com.example.xts.greendaodemo.db.RecentBeanDao;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectFragment extends Fragment {


    private View view;
    private RecyclerView mRecycler;
    private ArrayList<RecentBean> mRecentBeans;
    private RLVAdapter mRLVAdapter;
    private RecentBeanDao mRecentBeanDao;

    public CollectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_collect, container, false);
        mRecentBeanDao = MyApp.mDaoSession.getRecentBeanDao();
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        mRecycler = (RecyclerView) inflate.findViewById(R.id.recycler);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycler.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        mRecentBeans = new ArrayList<>();
        mRLVAdapter = new RLVAdapter(getContext(), mRecentBeans);
        mRecycler.setAdapter(mRLVAdapter);
    }

    /*@Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden==false){
            initData();
        }
    }
*/
    public void initData() {
        mRecentBeans.clear();
        List<RecentBean> recentBeans = mRecentBeanDao.loadAll();
        mRecentBeans.addAll(recentBeans);
        mRLVAdapter.notifyDataSetChanged();
    }
}
