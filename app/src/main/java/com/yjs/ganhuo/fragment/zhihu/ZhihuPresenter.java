package com.yjs.ganhuo.fragment.zhihu;

import android.content.Context;

import com.yjs.ganhuo.base.BasePresenter;
import com.yjs.ganhuo.base.IView;
import com.yjs.ganhuo.bean.ZhihuDailyEntity;
import com.yjs.ganhuo.bean.ZhihuDailyItem;
import com.yjs.ganhuo.fragment.baserecycleFragment.BaseRecycleFragmentIView;
import com.yjs.ganhuo.net.ApiFactory;
import com.yjs.ganhuo.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.internal.schedulers.SchedulerLifecycle;
import rx.schedulers.Schedulers;

/**
 * Created by yangjingsong on 16/6/16.
 */
public class ZhihuPresenter extends BasePresenter<IView> implements ZhihuIPresenter {

    public ZhihuPresenter(BaseRecycleFragmentIView view, Context context) {
        super(view, context);
    }

    @Override
    public void getZhihuDailyList(int pageNum) {
        String date = DateUtils.getDate(pageNum);
        Subscriber subscriber = (Subscriber) ApiFactory.getZhiApi().getZhihuDailyList(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(
                        zhihuDailyEntity -> {
                            List<ZhihuDailyItem> list = new ArrayList<ZhihuDailyItem>();
                            for (int i = 0; i < zhihuDailyEntity.getStories().size(); i++) {
                                ZhihuDailyItem item = new ZhihuDailyItem();
                                item.setId(zhihuDailyEntity.getStories().get(i).getId());
                                item.setImages(zhihuDailyEntity.getStories().get(i).getImages().get(0));
                                item.setTitle(zhihuDailyEntity.getStories().get(i).getTitle());
                                item.setPosition(i);
                                item.setDate(DateUtils.getDateTitle(pageNum));
                                list.add(item);
                            }
                            return list;
                        }
                ).subscribe(
                        zhihuDailyItems -> {
                            mView.showContent();
                            ((BaseRecycleFragmentIView)mView).stopFresh();
                            if(pageNum == 0){
                                zhihuDailyItems.get(0).setDate("今日要闻");
                                ((BaseRecycleFragmentIView)mView).refreshData(zhihuDailyItems);
                            }else {
                                ((BaseRecycleFragmentIView)mView).loadData(zhihuDailyItems);
                            }

                        }
                                ,
                        throwable -> mView.showError()
                );
        addSubscribe(subscriber);


    }


}
