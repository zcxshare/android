package com.demo.www.demo.base;

import com.demo.www.demo.network.SubscriptionManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * author:  zhouchaoxiang
 * date:    2018/6/27
 * explain:
 */
public class BasePresent<V extends BaseView> {
    protected V view;
    private List<Disposable> mDisposables = new ArrayList<>();//订阅集合

    public BasePresent(V view) {
        this.view = view;
    }

    /**
     * 断开连接
     */
    public void detach() {
        if (null != view) view = null;
        //取消联网
        for (Disposable disposable : mDisposables) {
            if (!disposable.isDisposed()) {
                SubscriptionManager.getInstance().cancel(disposable);
            }
        }
        mDisposables.clear();
    }

    /**
     * 添加联网标识，方便移除
     */
    public void addDisposable(Disposable disposable) {
        mDisposables.add(disposable);
    }
}
