package com.example.administrator.day03_zuoye.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.day03_zuoye.R;
import com.example.administrator.day03_zuoye.bean.DataInfos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/11/20 0020.
 */

public class VPAdapter extends PagerAdapter{
    private Context baseContext;
    private ArrayList<DataInfos.ResultsBean> mResultsBeans;

    public VPAdapter(Context baseContext, ArrayList<DataInfos.ResultsBean> resultsBeans) {
        this.baseContext = baseContext;
        mResultsBeans = resultsBeans;
    }

    @Override
    public int getCount() {
        return mResultsBeans.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View inflate = LayoutInflater.from(baseContext).inflate(R.layout.img, null);
        ImageView iv = inflate.findViewById(R.id.iv);
        Glide.with(baseContext)
                .load(mResultsBeans.get(position).getUrl())
                .into(iv);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        container.addView(inflate);
        return inflate;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public void addData(List<DataInfos.ResultsBean> results) {
        mResultsBeans.addAll(results);
        notifyDataSetChanged();
    }
}
