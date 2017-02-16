package com.yjs.ganhuo.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.yjs.ganhuo.R;

import butterknife.ButterKnife;

/**
 * Created by yangjingsong on 16/6/15.
 */
public abstract class BaseActivity<T extends IPresenter> extends AppCompatActivity{
    protected T mPresent;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        ButterKnife.bind(this);
        initEventAndData();
        mPresent = getmPresent();
        initToolBar();
    }

    protected abstract int getLayoutId();

    protected abstract void initEventAndData();
    protected abstract T getmPresent();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresent!=null){
            mPresent.detachView();
        }

    }

    public void initToolBar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
