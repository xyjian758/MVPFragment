package com.hotfix.xyjian.mvpfragment.retrofit;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 封装的retrofit类 get post upload download
 */
public class RetrofitClient {
    private static final String TAG = "RetrofitClient";
    private static final int DEFAULT_TIMEOUT = 20;
    private OkHttpClient okHttpClient;
    private static String baseUrl = "";// API.APPSERVERVERSION;

    private APIService mBaseApiServices;
    private APIService mUrlApiServices;
    private APIService mApiServices;
    private boolean isBaseUrl = true;
    private Gson gson;

    private static class SingletonHolder {
        private static RetrofitClient INSTANCE = new RetrofitClient();
    }

    public static RetrofitClient getInstance() {

        return SingletonHolder.INSTANCE;
    }

    public static RetrofitClient getInstance(String url) {
        return new RetrofitClient(url);
    }

    private RetrofitClient() {
        this(null);

    }

    private RetrofitClient(String url) {

        createHttpClient();

        if (TextUtils.isEmpty(url)) {
            //都是 baseUrl
            createBaseUrlService();
            mApiServices = mBaseApiServices;
            isBaseUrl = true;
        } else {
            //baseUrl 有变更
            createChangeUrlService(url);
            mApiServices = mUrlApiServices;
            isBaseUrl = false;
        }
    }


    private void createHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new RetrofitNetWorkInterceptor())
                    .addInterceptor(new RetrofitInterceptor())
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .build();
        }
    }

    private void createBaseUrlService() {
        if (mBaseApiServices == null) {
            mBaseApiServices = new Retrofit.Builder()
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(baseUrl)
                    .build()
                    .create(APIService.class);
        }
    }


    private void createChangeUrlService(String changeUrl) {

        mUrlApiServices = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(changeUrl)
                .build()
                .create(APIService.class);

    }

    //get请求
    public <T> Flowable<T> get(String url, Map<String, String> params, final Class<T> clazz) {
        return mApiServices.executeGet(url, params)
                .onBackpressureDrop()//背压丢弃策略
                .map(new Function<ResponseBody, T>() {
                    @Override
                    public T apply(ResponseBody responseBody) throws Exception {
                        T t = null;
                        try {
                            String body = responseBody.string();
                            t = getGson().fromJson(body, clazz);
                        } catch (Exception e) {
                            new Exception(e.getMessage());
                        } finally {
                            if (null != responseBody) {
                                responseBody.close();
                            }
                        }
                        return t;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    //post请求
    public <T> Flowable<T> post(String urlField, Map<String, String> params, final Class<T> clazz) {
        return mApiServices.executePost(urlField, params)
                .onBackpressureDrop()//背压丢弃策略
                .map(new Function<ResponseBody, T>() {
                    @Override
                    public T apply(ResponseBody responseBody) throws Exception {
                        T t = null;
                        try {
                            String body = responseBody.string();
                            t = getGson().fromJson(body, clazz);
                        } catch (Exception e) {
                            new Exception(e.getMessage());
                        } finally {
                            if (null != responseBody) {
                                responseBody.close();
                            }
                        }
                        return t;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }


    private Gson getGson() {
        if (null == gson) {
            gson = new Gson();
        }
        return gson;
    }

    //================================内部类=======================================================
    //cookie管理器
    private class RetrofitCookie implements CookieJar {

        @Override
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl url) {
            return null;
        }
    }

    //网络拦截器
    private class RetrofitNetWorkInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response reponse = chain.proceed(request);
/*            Request request = chain.request()
                    .newBuilder()
                    .addHeader("mac", "f8:00:ea:10:45")
                    .addHeader("uuid", "gdeflatfgfg5454545e")
                    .addHeader("userId", "Fea2405144")
                    .addHeader("netWork", "wifi")
                    .build();*/

            return reponse;
        }
    }

    //整个拦截器
    private class RetrofitInterceptor implements Interceptor {
        private long timeSecond = 1000 * 1000 * 1000 * 1000;

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response reponse = chain.proceed(request);
/*            LogJoneUtil.d(TAG, "拦截器  本次请求数据："
                            + "\n" + "Request："
                            + "\n" + "请求头：" + request.headers()
                            + "\n" + "请求方式：" + request.method()
                            + "\n" + "请求体：" + new Gson().toJson(request.body()).toString()
                            + "\n" + "请求地址：" + request.url()
                            + "\n" + "请求缓存控制：" + request.cacheControl()
                            + "\n" + "请求方式是否Https:" + request.isHttps()
                            + "\n" + "Reponse:"
                            + "\n" + "响应头：" + "\n" + "[" + "\n" + reponse.headers() + "]"
                            + "\n" + "响应协议：" + reponse.protocol()
                            + "\n" + "响应消息：" + reponse.message()
                            + "\n" + "响应握手信息：" + reponse.handshake()
                            + "\n" + "响应缓存控制：" + reponse.cacheControl()
//                    + "\n" + "响应体：" + new Gson().toJson(reponse.body())
                            + "\n" + "响应接收耗时?：" + reponse.receivedResponseAtMillis() / timeSecond + "秒"
                            + "\n" + "响应发送耗时?：" + reponse.sentRequestAtMillis() / timeSecond  + "秒"
                            + "\n\n"
            );*/
            return reponse;
        }
    }

/*
    Observable使用场景:

    数据流最长不超过1000个元素，即随着时间的流逝，应用没有机会报OOME(OutOfMemoryError)错误。
    处理诸如鼠标移动或触摸事件之类的GUI事件

    Flowable使用场景:

    处理超过10K+ 的元素流
    从磁盘读取（解析文件）
    从数据库读取数据
            从网络获取数据流

    作者：依然范特稀西
    链接：http://www.jianshu.com/p/a2aa585ff6fd
    來源：简书
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/


}
