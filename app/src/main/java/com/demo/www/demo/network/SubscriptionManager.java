package com.demo.www.demo.network;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * author:  zhouchaoxiang
 * date:    2018/6/27
 * explain:
 */
public class SubscriptionManager {

    private CompositeDisposable mDisposables;

    private static class Instance {
        private static SubscriptionManager instance = new SubscriptionManager();
    }

    public static SubscriptionManager getInstance() {
        return Instance.instance;
    }

    private SubscriptionManager() {
        mDisposables = new CompositeDisposable();
    }

    public void add(Disposable d) {
        if (null != d)
            mDisposables.add(d);
    }

    public void addAll(Disposable... d) {
        if (null != d)
            mDisposables.addAll(d);
    }

    public void cancel(Disposable d) {
        if (null != d)
            mDisposables.delete(d);
    }

    public void cancelAll() {
        mDisposables.clear();
    }
}
