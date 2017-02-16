package com.yjs.ganhuo.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yjs.ganhuo.R;
import com.yjs.ganhuo.bean.GankDailyEntity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yangjingsong on 16/6/16.
 */
public class GanhuoAdapter extends BaseRecyclerAdapter<GankDailyEntity, GanhuoAdapter.ViewHolder> {

    public OnRecyclerViewItemClick onRecyclerViewItemClick;

    public void setOnRecyclerViewItemClick(OnRecyclerViewItemClick onRecyclerViewItemClick) {
        this.onRecyclerViewItemClick = onRecyclerViewItemClick;
    }

    @Override
    public ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ganhuo_item, parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(ViewHolder holder, int position) {
        holder.itemTitle.setText(mData.get(position).getDesc());
        holder.itemAuthor.setText(mData.get(position).getWho());
        holder.itemDate.setText(mData.get(position).getPublishedAt());
        //setAnimation(holder.cardView,position);
        holder.cardView.setOnClickListener(
                v -> onRecyclerViewItemClick.onItemClick(position)
               );
    }





    static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        @Bind(R.id.item_title)
        TextView itemTitle;
        @Bind(R.id.item_date)
        TextView itemDate;
        @Bind(R.id.item_author)
        TextView itemAuthor;
        @Bind(R.id.card_view)
        CardView cardView;


    }

    public interface OnRecyclerViewItemClick{
        void onItemClick(int position);
    }
}
