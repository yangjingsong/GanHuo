package com.yjs.ganhuo.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.yjs.ganhuo.R;
import com.yjs.ganhuo.base.BaseActivity;
import com.yjs.ganhuo.base.IPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yangjingsong on 16/6/17.
 */
public class GanhuoDetailActivity extends BaseActivity {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.webView)
    WebView webView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ganhuo_detail;
    }

    @Override
    protected void initEventAndData() {
        String url = getIntent().getStringExtra("url");
        String title = getIntent().getStringExtra("title");
        toolbar.setTitle(title);
        initWebView(url);


    }

    private void initWebView(String url) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setDefaultTextEncodingName("UTF-8");
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.loadUrl(url);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if(newProgress == 100){
                    progressBar.setVisibility(View.GONE);
                }else {
                    progressBar.setProgress(newProgress);
                }
            }
        });

    }

    @Override
    protected IPresenter getmPresent() {
        return null;
    }


}
