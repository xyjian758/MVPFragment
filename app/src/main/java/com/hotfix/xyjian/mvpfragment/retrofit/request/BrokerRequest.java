package com.hotfix.xyjian.mvpfragment.retrofit.request;

import org.reactivestreams.Subscriber;

import java.util.Map;

/**
 * 把所有的请求统一放到一个类下面 方便统一管理和复用
 */
public class BrokerRequest {

    /**
     * 经纪人 热门城市定位 获取当前定位城市信息
     */
    public static void getLocationCityInfo(Map<String, String> params, Subscriber subscriber) {
//        RetrofitClient.getInstance()
//                .get(API.RETROFIT_COUNT_AGENT_JOB_BY_CITYID, params, LocationCity.class)
//                .subscribe(subscriber);
    }

}
