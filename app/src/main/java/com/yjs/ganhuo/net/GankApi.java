package com.yjs.ganhuo.net;

import com.yjs.ganhuo.bean.GankDailyEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by yangjingsong on 16/6/15.
 */
public interface GankApi {
    @GET("data/{type}/{count}/{pageIndex}")
    Observable<ApiResponse<List<GankDailyEntity>>> getCommonDateNews(@Path("type") String type,
                                                                     @Path("count") int count,
                                                                     @Path("pageIndex") int page);
}
