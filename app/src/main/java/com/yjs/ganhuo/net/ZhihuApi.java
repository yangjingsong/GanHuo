package com.yjs.ganhuo.net;

import com.yjs.ganhuo.bean.ZhihuDailyEntity;
import com.yjs.ganhuo.bean.ZhihuDetail;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by yangjingsong on 16/6/16.
 */
public interface ZhihuApi {
    @GET("before/{date}")
    Observable<ZhihuDailyEntity> getZhihuDailyList(@Path("date")String date);

    @GET("{id}")
    Observable<ZhihuDetail> getZhihuDetail(@Path("id")int id);

}
