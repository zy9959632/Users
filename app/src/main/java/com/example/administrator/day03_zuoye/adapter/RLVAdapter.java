package com.example.administrator.day03_zuoye.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

public class RLVAdapter extends RecyclerView.Adapter<RLVAdapter.MyHolder> {
    private Context baseContext;
    public ArrayList<DataInfos.ResultsBean> mList;

    public RLVAdapter(Context baseContext, ArrayList<DataInfos.ResultsBean> list) {
        this.baseContext = baseContext;
        mList = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(baseContext).inflate(R.layout.recycler_item, parent, false);

        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        Glide.with(baseContext)
                .load(mList.get(position).getUrl())
                .into(holder.mImg);
        holder.mImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMyItemOnClick!=null){
                    mMyItemOnClick.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(List<DataInfos.ResultsBean> results) {
        mList.addAll(results);
        notifyDataSetChanged();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView mImg;
        public MyHolder(View itemView) {
            super(itemView);
            this.mImg = (ImageView) itemView.findViewById(R.id.img);
        }
    }
    public interface MyItemOnClick{
        void onItemClick(int position);
    }
    private MyItemOnClick mMyItemOnClick;

    public void setMyItemOnClick(MyItemOnClick myItemOnClick) {
        mMyItemOnClick = myItemOnClick;
    }
}
