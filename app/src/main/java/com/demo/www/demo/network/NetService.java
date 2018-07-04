package com.demo.www.demo.network;

import com.demo.www.demo.text.TextBean;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * author:  zhouchaoxiang
 * date:    2018/6/27
 * explain: 联网实体类
 */
public class NetService {
    private static NetApi mNetApi = NetCore.getInstance().getNetApi();

    public static void get(String param) {
        mNetApi.get("path", param);
    }

    public static void post(String param, DemoObserver<TextBean> observer) {
        requestHandler(mNetApi.post(param))
                .subscribe(observer);
    }

    public static void upload(RequestBody description, MultipartBody.Part file, DemoObserver<ResponseBody> observer) {
        requestHandler(mNetApi.upload(description, file))
                .subscribe(observer);
    }

    /**
     * 联网线程切换，也可做返回数据的统一处理
     */
    private static <RESPONSE> Observable<RESPONSE> requestHandler(Observable<RESPONSE> response) {
        return response.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
