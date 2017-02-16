package com.yjs.ganhuo.fragment.ganhuo;

import android.content.Context;

import com.yjs.ganhuo.base.BasePresenter;
import com.yjs.ganhuo.fragment.baserecycleFragment.BaseRecycleFragmentIView;
import com.yjs.ganhuo.net.ApiFactory;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by yangjingsong on 16/6/15.
 */
public class GanhuoPresenter extends BasePresenter<BaseRecycleFragmentIView> implements GanhuoIPresenter {
    public int pageNum = 1;
    public GanhuoPresenter(BaseRecycleFragmentIView view, Context context) {
        super(view, context);
    }

    @Override
    public void getListData(String type) {
        mView.showLoading();
        Subscriber subscriber = (Subscriber) ApiFactory.getmApi().getCommonDateNews(type, 10, pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(listApiResponse -> listApiResponse.getResults())
                .subscribe(
                        gankDailyEntities ->
                        {
                            mView.stopFresh();
                            mView.showContent();
                            if(pageNum==1){
                                if(gankDailyEntities.size() == 0) mView.showNoData();
                                mView.refreshData(gankDailyEntities);
                            }else {
                                mView.loadData(gankDailyEntities);
                            }
                        }
                        ,
                        throwable -> mView.showError());
        addSubscribe(subscriber);
    }
}
