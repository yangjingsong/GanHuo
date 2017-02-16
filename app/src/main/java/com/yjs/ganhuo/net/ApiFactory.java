package com.yjs.ganhuo.net;

import com.yjs.ganhuo.URL;

/**
 * Created by yangjingsong on 16/6/15.
 */
public class ApiFactory {
    private static GankApi mApi = null;
    private static ZhihuApi zhiApi = null;
    private static JiandanApi jiandanApi = null;
    private static Object watch = new Object();
    public ApiFactory(){

    }
    public static GankApi getmApi(){
        synchronized (watch){
            if(mApi == null){
                mApi = ApiService.getApi(URL.GANK_BASEURL,GankApi.class);
            }
        }
        return mApi;
    }

    public static ZhihuApi getZhiApi(){
        synchronized (watch){
            if(zhiApi == null){
                zhiApi =  ApiService.getApi(URL.Zhihu_BASEURL,ZhihuApi.class);
            }
        }
        return zhiApi;
    }

    public static JiandanApi getJiandanApi(){
        synchronized (watch){
            if(jiandanApi == null){
                jiandanApi = ApiService.getApi(URL.JianDan_BASEURL,JiandanApi.class);
            }
        }
        return jiandanApi;
    }
}
