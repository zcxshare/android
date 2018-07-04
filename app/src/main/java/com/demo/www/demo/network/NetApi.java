package com.demo.www.demo.network;


import com.demo.www.demo.text.RequestBean;
import com.demo.www.demo.text.TextBean;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * author:  zhouchaoxiang
 * date:    2018/6/27
 * explain:
 */
public interface NetApi {
    @GET("get/{path}")
    Observable<ResponseBody> get(@Path("path") String path, @Query("param") String param);

    @FormUrlEncoded
    @POST("post")
    Observable<TextBean> post(@Field("param") String param);

    @POST("http://127.0.0.1:8080/android/post2")
    Observable<ResponseBody> post2(@Body RequestBean requestBean);

    @Multipart
    @POST("upload")
    Observable<ResponseBody> upload(@Part("description") RequestBody description, @Part MultipartBody.Part file);

}
