package com.yjs.ganhuo.net;

import com.yjs.ganhuo.bean.JokeItem;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by yangjingsong on 16/7/7.
 */
public interface JiandanApi {
    @GET("/")
    Observable<JokeItem> getJokeList(@Query("oxwlxojflwblxbsapi")String api,@Query("page")int page);
}
