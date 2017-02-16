package com.yjs.ganhuo.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.yjs.ganhuo.R;
import com.yjs.ganhuo.activity.zhihudetail.ZhihuDetailActivity;
import com.yjs.ganhuo.bean.ZhihuDailyItem;
import com.yjs.ganhuo.bean.ZhihuDetail;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yangjingsong on 16/6/16.
 */
public class ZhihuAdapter extends BaseRecyclerAdapter<ZhihuDailyItem,RecyclerView.ViewHolder> {
    //List<ZhihuDailyItem> mData = new ArrayList<>();
    public static final int Footer = 0;
    public static final int Body = 1;
    public static final int DateItem = 2;
    boolean hasMore = true;
    Activity activity;

    private OnClickItemListener onClickItemListener;
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == Body) {
            View body = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
            return new ItemViewHolder(body);

        } else if (viewType == Footer) {
            View footer = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_footer, parent, false);
            return new FooterViewHolder(footer);
        } else {
            View date = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_date, parent, false);
            return new DateViewHolder(date);

        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof FooterViewHolder){
            ((FooterViewHolder) holder).progressBar.setVisibility(View.VISIBLE);
            ((FooterViewHolder) holder).textView.setText("加载中");
        }else if(holder instanceof ItemViewHolder){
            if(Build.VERSION.SDK_INT>20){
                ((ItemViewHolder) holder).imageView2.setTransitionName("picture");
            }

            Context context = ((ItemViewHolder) holder).imageView2.getContext();
                    ((ItemViewHolder) holder).textView.setText(mData.get(position).getTitle());
            final Bitmap[] bitmap = new Bitmap[1];
            Glide.with(context)
                    .load(mData.get(position).getImages())
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            bitmap[0] = resource;
                            ((ItemViewHolder) holder).imageView2.setImageBitmap(resource);
                        }
                    });
            ((ItemViewHolder) holder).cardView.setOnClickListener(
                    v -> {

                        if(onClickItemListener!=null){
                            onClickItemListener.onClickItem(mData.get(position).getId(),((ItemViewHolder) holder).imageView2,bitmap[0]);
                        }

                    }
                   );
        }else if(holder instanceof DateViewHolder){
            Context context = ((DateViewHolder) holder).imageView2.getContext();
                    ((DateViewHolder) holder).textView.setText(mData.get(position).getTitle());
            ((DateViewHolder) holder).tvItemDate.setText(mData.get(position).getDate());
            Glide.with(context)
            .load(mData.get(position).getImages())
                    .into(((DateViewHolder) holder).imageView2);
            ((DateViewHolder) holder).cardrelativeLayout.setOnClickListener(v -> {
                Intent intent = new Intent(context, ZhihuDetailActivity.class);
                intent.putExtra("id", mData.get(position).getId());
                intent.putExtra("url",mData.get(position).getGa_prefix());
                context.startActivity(intent);
            });


        }

    }


    @Override
    public int getItemCount() {
        return mData.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mData.size()) {
            return Footer;
        } else if (mData.get(position).getPosition() == 0) {
            return DateItem;
        } else {
            return Body;
        }

    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageView2)
        ImageView imageView2;
        @Bind(R.id.textView)
        TextView textView;
        @Bind(R.id.cardview)
        CardView cardView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    static class FooterViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.progressBar)
        ProgressBar progressBar;
        @Bind(R.id.textView)
        TextView textView;

        FooterViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    static class DateViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.tv_item_date)
        TextView tvItemDate;
        @Bind(R.id.imageView2)
        ImageView imageView2;
        @Bind(R.id.textView)
        TextView textView;
        @Bind(R.id.card)
        RelativeLayout cardrelativeLayout;

        DateViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void addRefreshData(List<ZhihuDailyItem> list){
        mData.clear();
        mData.addAll(list);
        notifyDataSetChanged();
    }

    public void addLoadMoreData(List<ZhihuDailyItem> list){
        mData.addAll(list);
        notifyDataSetChanged();
    }

    public interface OnClickItemListener{
        void onClickItem(int id,ImageView imageView,Bitmap bitmap);
    }

    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }
}
