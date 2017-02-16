package com.yjs.ganhuo.fragment.baserecycleFragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yjs.ganhuo.R;
import com.yjs.ganhuo.adapter.BaseRecyclerAdapter;
import com.yjs.ganhuo.base.BaseFragment;
import com.yjs.ganhuo.view.LoadingView;

import java.util.List;

import butterknife.Bind;

/**
 * Created by yangjingsong on 16/6/16.
 */
public abstract class BasePullToRefreshFragment<E,T extends BaseRecyclerAdapter> extends BaseFragment implements BaseRecycleFragmentIView<E> {

    public T adapter;
    @Bind(R.id.mRecyclerView)
    public RecyclerView mRecyclerView;
    @Bind(R.id.mSwipeRefreshLayout)
    public SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.mLoading)
    public LoadingView mLoading;
    //protected P mPresent;
    public List<E> mData;

    protected abstract T getAdapter();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ganhuo;
    }



    @Override
    protected void intDataAndEvent() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = getAdapter();
        initDatas();
        //mPresent = getPresenter();
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstVisiblePosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();

                if (((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition() + 1
                        == recyclerView.getAdapter().getItemCount()) {
                    loadDataWithPresenter();

                }
                doSomethingOnScroll(firstVisiblePosition);
            }
        });
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            refreshDataWithPresenter();
        });

        refreshDataWithPresenter();


    }

    protected abstract void initDatas();

    @Override
    public void refreshData(List<E> data) {
        mSwipeRefreshLayout.setRefreshing(false);
        adapter.addRefreshData(data);
    }

    @Override
    public void loadData(List<E> data) {
        adapter.addLoadMoreData(data);
    }

    @Override
    public void showLoading() {
        mLoading.setState(LoadingView.LOADING);

    }

    @Override
    public void showContent() {
        mLoading.setState(LoadingView.SHOW_DATA);

    }

    @Override
    public void showError() {
        mLoading.setState(LoadingView.ERROR);

    }

    @Override
    public void showNoData() {
        mLoading.setState(LoadingView.NO_DATA);

    }

    @Override
    public void stopFresh() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    protected abstract void refreshDataWithPresenter();
    protected abstract void loadDataWithPresenter();
    protected void doSomethingOnScroll(int firstVisiblePsition){

    }
    //protected abstract P getPresenter();

//    @Override
//    public P getmPresent() {
//        return getPresenter();
//    }
}
