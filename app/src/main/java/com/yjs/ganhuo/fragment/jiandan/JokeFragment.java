package com.yjs.ganhuo.fragment.jiandan;

import com.yjs.ganhuo.adapter.JokeAdapter;
import com.yjs.ganhuo.base.IPresenter;
import com.yjs.ganhuo.bean.JokeItem;
import com.yjs.ganhuo.fragment.baserecycleFragment.BasePullToRefreshFragment;

/**
 * Created by yangjingsong on 16/7/7.
 */
public class JokeFragment extends BasePullToRefreshFragment<JokeItem,JokeAdapter> {
    JokePresenter jokePresenter;
    int page =0;
    @Override
    protected JokeAdapter getAdapter() {
        return new JokeAdapter();
    }

    @Override
    protected void initDatas() {


    }

    @Override
    protected void refreshDataWithPresenter() {
        jokePresenter = new JokePresenter(this,getActivity());
        page = 0;
        jokePresenter.getJokeList(0);

    }

    @Override
    protected void loadDataWithPresenter() {
        jokePresenter.getJokeList(++page);

    }

    @Override
    protected IPresenter getmPresent() {
        return jokePresenter;
    }
}
