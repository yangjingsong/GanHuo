package com.yjs.ganhuo.fragment.baserecycleFragment;

import com.yjs.ganhuo.base.IView;
import com.yjs.ganhuo.bean.GankDailyEntity;

import java.util.List;

/**
 * Created by yangjingsong on 16/6/15.
 */
public interface BaseRecycleFragmentIView<T> extends IView{
    void refreshData(List<T> data);
    void loadData(List<T> data);
    void stopFresh();
}
