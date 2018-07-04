package com.demo.www.demo.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * author:  zhouchaoxiang
 * date:    2018/6/28
 * explain:
 */
public abstract class BaseActivity<p extends BasePresent> extends AppCompatActivity {

    protected p mPresent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresent = getPresent();
        initData();
        initView();
        initEvent();
    }
    protected abstract p getPresent();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initEvent();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //分离present
        if (null != mPresent) {
            mPresent.detach();
            mPresent = null;
        }
    }
}
