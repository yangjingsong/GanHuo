package com.yjs.ganhuo.base;


import android.content.Context;

import com.yjs.ganhuo.net.GankApi;
import com.yjs.ganhuo.net.ApiFactory;

import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yangjingsong on 16/6/15.
 */
public class BasePresenter<T extends IView> implements IPresenter {
    protected T mView;
    protected Context mContext;
    protected GankApi mApi = ApiFactory.getmApi();
    protected CompositeSubscription compositeSubscription;
    public BasePresenter(T view,Context context){
        mView = view;
        mContext = context;
    }

    protected void addSubscribe(Subscriber subscriber){
        if(compositeSubscription ==null){
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(subscriber);
    }
    //防止内存泄漏
    protected void unSubscribe(){
        if(compositeSubscription!=null){
            compositeSubscription.unsubscribe();
        }
    }
    @Override
    public void detachView(){
        unSubscribe();
    }
}
