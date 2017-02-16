package com.yjs.ganhuo.fragment.tabFragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.yjs.ganhuo.R;
import com.yjs.ganhuo.adapter.FragmentAdapter;
import com.yjs.ganhuo.base.BaseFragment;

import com.yjs.ganhuo.fragment.ganhuo.GanhuoFragment;

import java.util.List;

import butterknife.Bind;

/**
 * Created by yangjingsong on 16/6/15.
 */
public class TabViewPagerFragment extends BaseFragment<TabPresenter> implements TabFragmentView {
    @Bind(R.id.mTabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.mViewPager)
    ViewPager mViewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_tab_viewpager;
    }

    @Override
    protected TabPresenter getmPresent() {
        return new TabPresenter(this,getActivity());
    }

    @Override
    protected void intDataAndEvent() {
        mPresent.getTabs();

    }


    @Override
    public void addTabs(List<String> tabs) {
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getChildFragmentManager());
        for(String tab:tabs){
            GanhuoFragment ganhuoFragment = GanhuoFragment.instance(tab);
            fragmentAdapter.addFragment(ganhuoFragment,tab);
        }
        mViewPager.setAdapter(fragmentAdapter);
        mViewPager.setOffscreenPageLimit(6);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void showError() {

    }

    @Override
    public void showNoData() {

    }
}
