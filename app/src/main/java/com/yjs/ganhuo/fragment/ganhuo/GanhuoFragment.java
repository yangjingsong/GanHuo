package com.yjs.ganhuo.fragment.ganhuo;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.yjs.ganhuo.activity.GanhuoDetailActivity;
import com.yjs.ganhuo.adapter.GanhuoAdapter;
import com.yjs.ganhuo.bean.GankDailyEntity;
import com.yjs.ganhuo.fragment.baserecycleFragment.BasePullToRefreshFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class GanhuoFragment extends BasePullToRefreshFragment<GankDailyEntity, GanhuoAdapter> {

    GanhuoPresenter ganhuoPresenter;
    String type;

    public static GanhuoFragment instance(String type) {
        GanhuoFragment fragment = new GanhuoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected GanhuoAdapter getAdapter() {
        return new GanhuoAdapter();
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void refreshDataWithPresenter() {
        type = getArguments().getString("type");
        ganhuoPresenter = new GanhuoPresenter(this, getActivity());
        ganhuoPresenter.pageNum = 1;
        ganhuoPresenter.getListData(type);
        adapter.setOnRecyclerViewItemClick(
                position -> {
                    Intent intent = new Intent(getActivity(), GanhuoDetailActivity.class);
                    intent.putExtra("url",adapter.getmData().get(position).getUrl());
                    intent.putExtra("title",adapter.getmData().get(position).getDesc());
                    getActivity().startActivity(intent);

                }
        );

    }

    @Override
    protected void loadDataWithPresenter() {


        ganhuoPresenter.pageNum++;
        Log.d("page",  ganhuoPresenter.pageNum + "");
        ganhuoPresenter.getListData(type);
    }

    @Override
    public GanhuoPresenter getmPresent() {
        return ganhuoPresenter;
    }
}
