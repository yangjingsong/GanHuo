package com.yjs.ganhuo.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yjs.ganhuo.R;
import com.yjs.ganhuo.bean.JokeItem;
import com.yjs.ganhuo.utils.String2TimeUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yangjingsong on 16/7/7.
 */
public class JokeAdapter extends BaseRecyclerAdapter<JokeItem.CommentsBean,JokeAdapter.JokeViewHolder> {
    @Override
    public JokeAdapter.JokeViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_joke, parent, false);
        return new JokeViewHolder(view);
    }

    @Override
    public void onBindItemViewHolder(JokeAdapter.JokeViewHolder holder, int position) {
        JokeItem.CommentsBean joke = mData.get(position);
        holder.tvContent.setText(joke.getComment_content());
        holder.tvAuthor.setText(joke.getComment_author());
        holder.tvTime.setText(String2TimeUtil.dateString2GoodExperienceFormat(joke.getComment_date()));
        holder.tvLike.setText(joke.getVote_positive());

        holder.tvUnlike.setText(joke.getVote_negative());
        setAnimation(holder.card,position);

    }

    static class JokeViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.tv_author)
        TextView tvAuthor;
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.tv_content)
        TextView tvContent;
        @Bind(R.id.tv_support_des)
        TextView tvSupportDes;
        @Bind(R.id.tv_like)
        TextView tvLike;
        @Bind(R.id.ll_support)
        LinearLayout llSupport;
        @Bind(R.id.tv_un_support_des)
        TextView tvUnSupportDes;
        @Bind(R.id.tv_unlike)
        TextView tvUnlike;
        @Bind(R.id.ll_unsupport)
        LinearLayout llUnsupport;
        @Bind(R.id.tv_comment_count)
        TextView tvCommentCount;
        @Bind(R.id.ll_comment)
        LinearLayout llComment;
        @Bind(R.id.img_share)
        ImageView imgShare;
        @Bind(R.id.bottom)
        RelativeLayout bottom;
        @Bind(R.id.card)
        CardView card;

        JokeViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
