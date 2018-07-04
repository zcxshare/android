package com.demo.www.demo.text;

import com.demo.www.demo.base.BasePresent;
import com.demo.www.demo.network.DemoObserver;
import com.demo.www.demo.network.ExceptionHandle;
import com.demo.www.demo.network.NetService;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * author:  zhouchaoxiang
 * date:    2018/6/28
 * explain:
 */
public class TextPresent extends BasePresent<TextView> {
    private static final String TAG = "http";

    public TextPresent(TextView view) {
        super(view);
    }

    public void post(String param) {
        NetService.post(param, new DemoObserver<TextBean>(this) {
            @Override
            protected void onSuccess(TextBean value) {
                view.onPostSuccess();
            }

            @Override
            protected void onFail(ExceptionHandle.ERROR error) {
                view.onPostFail(error);
            }
        });
    }

    public void upload(File file) {
        // 创建 RequestBody，用于封装构建RequestBody
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part  和后端约定好Key，这里的partName是用image
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        // 添加描述
        String descriptionString = "hello, 这是文件描述";
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);
        NetService.upload(description, body, new DemoObserver<ResponseBody>(this) {
            @Override
            protected void onSuccess(ResponseBody value) {
                view.onPostSuccess();
            }

            @Override
            protected void onFail(ExceptionHandle.ERROR error) {
                view.onPostFail(error);
            }
        });
    }
}
