package com.yjs.ganhuo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yjs.ganhuo.R;

import butterknife.Bind;

/**
 * Created by yangjingsong on 16/6/16.
 */
public class LoadingView extends RelativeLayout {
    public static final int LOADING = 0;
    public static final int NO_DATA = 1;
    public static final int ERROR = 2;
    public static final int SHOW_DATA = 3;
    ProgressBar mProgress;
    TextView mTextView;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.loading_layout, this, true);
        mProgress = (ProgressBar) findViewById(R.id.progressBar);
        mTextView = (TextView) findViewById(R.id.textView);

    }
    public void setState(int state){
        switch (state){
            case LOADING:
                mProgress.setVisibility(VISIBLE);
                mTextView.setText("正在全力加载中");
                break;
            case NO_DATA:
                mProgress.setVisibility(GONE);
                mTextView.setText("暂无数据");
                break;
            case ERROR:
                mProgress.setVisibility(GONE);
                mTextView.setText("网络出错");
                break;
            case SHOW_DATA:
                this.setVisibility(GONE);
        }
    }
}
