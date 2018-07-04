package com.demo.www.demo.network;

import android.util.Log;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * author:  zhouchaoxiang
 * date:    2018/6/25
 * explain: 请求拦截器
 */
public class LoggingInterceptor implements Interceptor {
    private static final String TAG = "network";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long startTime = System.currentTimeMillis();
        StringBuilder requestSb = new StringBuilder(String.format("发送%s请求 %s on %s%n%s", request.method(), request.url(), chain.connection(), request.headers()));
        RequestBody requestBody = request.body();
        if (requestBody != null) {
            if (requestBody.contentType() != null)
                requestSb.append("\r\nContent-Type:").append(requestBody.contentType());
            if (requestBody.contentLength() != -1)
                requestSb.append("\r\nContent-Length:").append(requestBody.contentLength());
            //参数
            Buffer buffer = new Buffer();
            requestBody.writeTo(buffer);
            Charset charset = Charset.forName("utf-8");
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                Charset charset1 = contentType.charset(Charset.forName("utf-8"));
                if (charset1 != null) charset = charset1;
            }
            requestSb.append("\r\n").append(buffer.readString(charset));
        }
        Connection connection = chain.connection();
        Log.i(TAG, requestSb.toString());
        Response response = chain.proceed(request);
        long endTime = System.currentTimeMillis();
        ResponseBody responseBody = response.peekBody(1024*1024);
        Log.i(TAG, String.format("接收响应：[%s] %n返回json:%s  %.1fms%n%s", response.request().url(),
                responseBody.string(), ((float) (endTime - startTime)) / 1000, request.headers()));
        return response;
    }
}
