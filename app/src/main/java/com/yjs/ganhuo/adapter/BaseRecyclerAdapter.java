package com.yjs.ganhuo.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yjs.ganhuo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangjingsong on 16/6/16.
 */
public abstract class BaseRecyclerAdapter<T,VH extends RecyclerView.ViewHolder>extends RecyclerView.Adapter {
    public static final int Footer = 1;
    public static final int body = 0;
    protected List<T> mData = new ArrayList<>();
    protected boolean hasMore = true;
    protected int lastPosition = -1;

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == Footer){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_footer,parent,false);
            return new FooterView(view);

        }else {
            return onCreateItemViewHolder(parent,viewType);
        }

    }

    public abstract VH onCreateItemViewHolder(ViewGroup parent,int viewType);
    public abstract void onBindItemViewHolder(VH holder,int position);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof FooterView){
            if(hasMore){
                ((FooterView) holder).mTextView.setText("加载中...");
                ((FooterView) holder).mProgressBar.setVisibility(View.VISIBLE);
            }else {
                ((FooterView) holder).mTextView.setText("加载完毕");
                ((FooterView) holder).mProgressBar.setVisibility(View.GONE);
            }

        }else {
            onBindItemViewHolder((VH)holder,position);
        }

    }

    @Override
    public int getItemCount() {
        return mData.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position == mData.size()){
            return Footer;
        }
        else {
            return body;
        }
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        //super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if(layoutManager instanceof GridLayoutManager){
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if(position == mData.size()){
                        return ((GridLayoutManager) layoutManager).getSpanCount();
                    }else {
                        return 1;
                    }

                }
            });
            ((GridLayoutManager) layoutManager).setSpanCount(((GridLayoutManager) layoutManager).getSpanCount());
        }
    }

    private static class FooterView extends RecyclerView.ViewHolder{
        ProgressBar mProgressBar;
        TextView mTextView;

        public FooterView(View itemView) {
            super(itemView);
            mProgressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
            mTextView = (TextView) itemView.findViewById(R.id.textView);
        }
    }

    public void addLoadMoreData(List<T> list){

        mData.addAll(list);
        //hasMore = list.size() == 10;
        notifyDataSetChanged();
    }

    public void addRefreshData(List<T> list){
        lastPosition = -1;
        mData.clear();
        mData.addAll(list);
        //hasMore = list.size() == 10;
        notifyDataSetChanged();
    }

    public void setAnimation(View item,int position){
        if(position>lastPosition){
            Animation animation = AnimationUtils.loadAnimation(item.getContext(), R.anim.anim_recycle_item_in);
            item.startAnimation(animation);
            lastPosition = position;
        }
    }

    public List<T> getmData(){
        return mData;
    }
}
