package com.yjs.ganhuo.fragment.zhihu;


import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yjs.ganhuo.R;
import com.yjs.ganhuo.activity.zhihudetail.ZhihuDetailActivity;
import com.yjs.ganhuo.adapter.ZhihuAdapter;
import com.yjs.ganhuo.base.IPresenter;
import com.yjs.ganhuo.bean.ZhihuDailyItem;
import com.yjs.ganhuo.fragment.baserecycleFragment.BasePullToRefreshFragment;
import com.yjs.ganhuo.view.LoadingView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhihuFragment extends BasePullToRefreshFragment<ZhihuDailyItem, ZhihuAdapter> {


    int pageNum = 0;

    ZhihuPresenter presenter;

    @Override
    protected IPresenter getmPresent() {
        return presenter;
    }


    @Override
    protected ZhihuAdapter getAdapter() {
        return new ZhihuAdapter();
    }

    @Override
    protected void initDatas() {
        adapter.setOnClickItemListener(new ZhihuAdapter.OnClickItemListener() {
            @Override
            public void onClickItem(int id, ImageView imageView, Bitmap bitmap) {
                if(Build.VERSION.SDK_INT>20){
                    imageView.setTransitionName("picture");
                }

                Intent intent = new Intent(getActivity(), ZhihuDetailActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("bitmap",bitmap);
//                        //context.startActivity(intent);
////                        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,
////                                ((ItemViewHolder) holder).imageView2,"picture");
////                        ActivityCompat.startActivity(activity,intent,optionsCompat.toBundle());
//                if (Build.VERSION.SDK_INT > 20) {
//                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(),imageView,"picture");
//                    startActivityForResult(intent,1,options.toBundle());
//                } else {
                    getActivity().startActivity(intent);
                //}
            }
        });

    }

    @Override
    protected void refreshDataWithPresenter() {
        presenter = new ZhihuPresenter(this, getActivity());
        pageNum = 0;
        presenter.getZhihuDailyList(0);
        adapter.setActivity(getActivity());

    }

    @Override
    protected void loadDataWithPresenter() {
        pageNum++;
        Log.d("page", pageNum + "");
        presenter.getZhihuDailyList(pageNum);

    }

    @Override
    protected void doSomethingOnScroll(int firstVisiblePsition) {
        super.doSomethingOnScroll(firstVisiblePsition);
        Log.d("position", firstVisiblePsition + "");
        ZhihuDailyItem item = adapter.getmData().get(firstVisiblePsition);
        if (item.getPosition() == 0) {
            Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
            toolbar.setTitle(item.getDate());
        }
    }
}
