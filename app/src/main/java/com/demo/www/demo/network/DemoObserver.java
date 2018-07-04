package com.demo.www.demo.network;

import com.demo.www.demo.base.BasePresent;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * author:  zhouchaoxiang
 * date:    2018/6/27
 * explain:
 */
public abstract class DemoObserver<T> implements Observer<T> {

    private final BasePresent mPresent;

    public DemoObserver(BasePresent present) {
        mPresent = present;
    }

    @Override
    public void onSubscribe(Disposable d) {
        mPresent.addDisposable(d);
        SubscriptionManager.getInstance().add(d);
        onBefore(d);
    }


    @Override
    public void onNext(T value) {
        if (null == value) {
            onFail(ExceptionHandle.ERROR.NULL_ERROR);
        } else {
            onSuccess(value);
        }
    }


    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        ExceptionHandle.ERROR error = ExceptionHandle.handleException(e);
        onFail(error);
    }


    @Override
    public void onComplete() {
        onCompleted();
    }

    protected void onBefore(Disposable d) {
    }

    protected void onCompleted() {
    }

    protected abstract void onSuccess(T value);

    protected abstract void onFail(ExceptionHandle.ERROR error);
}
