package com.yjs.ganhuo.fragment.tabFragment;

import android.content.Context;

import com.yjs.ganhuo.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangjingsong on 16/6/15.
 */
public class TabPresenter extends BasePresenter<TabFragmentView> implements TabIPresenter{
    public TabPresenter(TabFragmentView view, Context context) {
        super(view, context);
    }

    @Override
    public void getTabs() {
        List<String> tabs = new ArrayList<>(7);
        tabs.add("Android");
        tabs.add("iOS");
        tabs.add("前端");
        tabs.add("App");
        tabs.add("休息视频");
        tabs.add("拓展资源");
        tabs.add("瞎推荐");
        mView.addTabs(tabs);
    }
}
