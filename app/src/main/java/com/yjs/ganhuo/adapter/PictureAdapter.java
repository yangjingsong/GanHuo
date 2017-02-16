package com.yjs.ganhuo.adapter;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yjs.ganhuo.R;
import com.yjs.ganhuo.activity.BigPictureActivity;
import com.yjs.ganhuo.bean.GankDailyEntity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yangjingsong on 16/6/27.
 */
public class PictureAdapter extends BaseRecyclerAdapter<GankDailyEntity, PictureAdapter.ViewHolder> {


    @Override
    public ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_welfare_staggered, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            holder.tvShowTime.setText(mData.get(position).getCreatedAt().split("T")[0]);
            Glide
                    .with(holder.image.getContext())
                    .load(mData.get(position).getUrl())
                    .placeholder(R.mipmap.pic_gray_bg)
                    .error(R.mipmap.pic_gray_bg)
                    .centerCrop()
                    .into(holder.image);
            //setAnimation(holder.rlRoot,position);
            ((ViewHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(holder.image.getContext(), BigPictureActivity.class);
                    intent.putExtra("url",mData.get(position).getUrl());
                    holder.image.getContext().startActivity(intent);

                }
            });
        }

    }



    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.image)
        ImageView image;
        @Bind(R.id.tvShowTime)
        TextView tvShowTime;
        @Bind(R.id.rl_root)
        RelativeLayout rlRoot;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
