package com.yjs.ganhuo.activity.zhihudetail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yjs.ganhuo.R;
import com.yjs.ganhuo.base.BaseActivity;
import com.yjs.ganhuo.bean.ZhihuDetail;
import com.yjs.ganhuo.fragment.zhihu.ZhihuPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by yangjingsong on 16/6/17.
 */
public class ZhihuDetailActivity extends BaseActivity<ZhihuDetailPresenter> implements ZhihuDetailIView {

    @Bind(R.id.image)
    ImageView image;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.webView)
    WebView webView;
    ZhihuDetailPresenter zhihuPresenter;
    @Bind(R.id.toolbarLayout)
    CollapsingToolbarLayout toolbarLayout;

    String url;

    Bitmap bitmap;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_zhihu_detail;
    }

    @Override
    protected void initEventAndData() {

        zhihuPresenter = new ZhihuDetailPresenter(this,this);
        zhihuPresenter.getZhihuDetail(getIntent().getIntExtra("id", 0));
        bitmap = getIntent().getParcelableExtra("bitmap");



    }

    @Override
    protected ZhihuDetailPresenter getmPresent() {
        return new ZhihuDetailPresenter(this, ZhihuDetailActivity.this);
    }


    @Override
    public void setZhihuDetail(ZhihuDetail detail) {
        url = detail.getShare_url();
        Glide.with(this).load(detail.getImage()).into(image);
        //image.setImageBitmap(bitmap);
        toolbarLayout.setTitle(detail.getTitle());
        Log.d("content",detail.getBody());

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);

        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">";
        String html = "<html><head>" + css + "</head><body>" + detail.getBody() + "</body></html>";
        html = html.replace("<div class=\"img-place-holder\">", "");
        webView.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.picture_menu,menu);
        menu.findItem(R.id.share).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.removeItem(R.id.save);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.share:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "分享一条新鲜事给你" + " " + url);
                shareIntent.setType("text/plain");
                startActivity(Intent.createChooser(shareIntent, "分享到"));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
