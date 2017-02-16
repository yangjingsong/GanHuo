package com.yjs.ganhuo.fragment.picture;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.yjs.ganhuo.adapter.PictureAdapter;
import com.yjs.ganhuo.base.IPresenter;
import com.yjs.ganhuo.bean.GankDailyEntity;
import com.yjs.ganhuo.fragment.baserecycleFragment.BasePullToRefreshFragment;
import com.yjs.ganhuo.fragment.ganhuo.GanhuoPresenter;

/**
 * Created by yangjingsong on 16/6/27.
 */
public class PictureFragment extends BasePullToRefreshFragment<GankDailyEntity,PictureAdapter> {
    private GanhuoPresenter ganhuoPresenter;
    @Override
    protected PictureAdapter getAdapter() {
        return new PictureAdapter();
    }

    @Override
    protected void initDatas() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    protected void refreshDataWithPresenter() {
        ganhuoPresenter = new GanhuoPresenter(this,getActivity());
        ganhuoPresenter.pageNum = 1;
        ganhuoPresenter.getListData("福利");

    }

    @Override
    protected void loadDataWithPresenter() {
        ganhuoPresenter.pageNum++;
        ganhuoPresenter.getListData("福利");

    }

    @Override
    protected IPresenter getmPresent() {
        return ganhuoPresenter;
    }
}
