package com.yjs.ganhuo.fragment.jiandan;

import android.content.Context;

import com.yjs.ganhuo.base.BasePresenter;
import com.yjs.ganhuo.bean.JokeItem;
import com.yjs.ganhuo.fragment.baserecycleFragment.BaseRecycleFragmentIView;
import com.yjs.ganhuo.net.ApiFactory;

import java.util.List;

import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by yangjingsong on 16/7/7.
 */
public class JokePresenter extends BasePresenter<BaseRecycleFragmentIView> implements JokeIPresenter {
    public JokePresenter(BaseRecycleFragmentIView view, Context context) {
        super(view, context);
    }

    @Override
    public void getJokeList(int page) {
        mView.showLoading();

        Subscriber subscriber = (Subscriber) ApiFactory.getJiandanApi().getJokeList("jandan.get_duan_comments", page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(jokeItem -> jokeItem.getComments())
                .subscribe(

                        jokeItems -> {
                            mView.showContent();
                            if (page == 0) {
                                mView.refreshData(jokeItems);
                            } else {
                                mView.loadData(jokeItems);
                            }
                        },
                        throwable -> mView.showError()

                );
        addSubscribe(subscriber);

    }
}
