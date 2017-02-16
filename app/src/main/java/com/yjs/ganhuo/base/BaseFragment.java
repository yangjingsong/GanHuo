package com.yjs.ganhuo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by yangjingsong on 16/6/15.
 */
public abstract class BaseFragment<P extends IPresenter> extends Fragment {
    protected P mPresent;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(),container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresent = getmPresent();
        intDataAndEvent();
    }

    protected abstract int getLayoutId();

    protected abstract void intDataAndEvent();

    protected abstract P getmPresent();


    @Override
    public void onDestroy() {
        super.onDestroy();



    }
}
