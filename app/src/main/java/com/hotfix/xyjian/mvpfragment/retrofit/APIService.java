package com.hotfix.xyjian.mvpfragment.retrofit;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;


/**
 * Retrofit 网络请求服务类 统一管理  一般也就这五个   get psot 单文件上传 多文件上传  文件下载
 */
public interface APIService {

    //get请求
    @GET("{url}")
    Flowable<ResponseBody> executeGet(
            @Path(value = "url", encoded = true) String url,
            @QueryMap Map<String, String> maps);


    //post请求
    @FormUrlEncoded
    @POST("{url}")
    Flowable<ResponseBody> executePost(
            @Path(value = "url", encoded = true) String url,
            @FieldMap Map<String, String> maps);

    //单文件或图片上传
    @Multipart
    @POST("{url}")
    Observable<ResponseBody> upLoadFile(
            @Path(value = "url", encoded = true) String url,
            @Part("image\";filename =\"image.jpg") RequestBody avatar);

    //多文件或者图片上传
    @POST("{url}")
    Observable<ResponseBody> uploadFiles(
            @Path(value = "url", encoded = true) String url,
            @Path("headers") Map<String, String> headers,
            @Part("filename") String description,
            @PartMap() Map<String, RequestBody> maps);

    //下载文件
    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String fileUrl);
}
