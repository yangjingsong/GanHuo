package com.yjs.ganhuo.activity.zhihudetail;

import android.content.Context;

import com.yjs.ganhuo.base.BasePresenter;
import com.yjs.ganhuo.bean.ZhihuDetail;
import com.yjs.ganhuo.fragment.zhihu.ZhihuIPresenter;
import com.yjs.ganhuo.net.ApiFactory;

import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by yangjingsong on 16/6/17.
 */
public class ZhihuDetailPresenter extends BasePresenter<ZhihuDetailIView> implements ZhihuDetailIPresenter {
    public ZhihuDetailPresenter(ZhihuDetailIView view, Context context) {
        super(view, context);
    }


    @Override
    public void getZhihuDetail(int id) {
        Subscriber subscriber = (Subscriber) ApiFactory.getZhiApi().getZhihuDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        zhihuDetail -> mView.setZhihuDetail(zhihuDetail)
                        , throwable -> mView.showError()
                );
        addSubscribe(subscriber);

    }
}
