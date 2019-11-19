package com.example.administrator.day02_text.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.day02_text.R;
import com.example.administrator.day02_text.model.bean.RecentBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2019/11/19 0019.
 */

public class RLVAdapter extends RecyclerView.Adapter<RLVAdapter.MyHolder> {
    private Context bsseContext;
    private ArrayList<RecentBean> mRecentBeans;

    public RLVAdapter(Context bsseContext, ArrayList<RecentBean> recentBeans) {
        this.bsseContext = bsseContext;
        mRecentBeans = recentBeans;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(bsseContext).inflate(R.layout.recycler_item, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
        holder.mTv1.setText(mRecentBeans.get(position).getTitle());
        holder.mTv2.setText(mRecentBeans.get(position).getUrl());
        Glide.with(bsseContext)
                .load(mRecentBeans.get(position).getThumbnail())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.mIma);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onClick(mRecentBeans.get(position).getUrl());
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onClick.onLongClick(position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRecentBeans.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {
        ImageView mIma;
        TextView mTv1;
        TextView mTv2;
        public MyHolder(View itemView) {
            super(itemView);
            this.mIma = (ImageView) itemView.findViewById(R.id.ima);
            this.mTv1 = (TextView) itemView.findViewById(R.id.tv1);
            this.mTv2 = (TextView) itemView.findViewById(R.id.tv2);
        }
    }
    public interface onClick{
        void onClick(String url);
        void onLongClick(int position);
    }
    private onClick onClick;

    public void setOnClick(RLVAdapter.onClick onClick) {
        this.onClick = onClick;
    }
}
