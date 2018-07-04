package com.demo.www.demo.text;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.demo.www.demo.R;
import com.demo.www.demo.base.BaseActivity;
import com.demo.www.demo.network.ExceptionHandle;

/**
 * author:  zhouchaoxiang
 * date:    2018/6/28
 * explain:
 */
public class TextActivity extends BaseActivity<TextPresent> implements TextView {
    private static final String TAG = "http";
    Button mBtNetworking;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.acitivity_text);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected TextPresent getPresent() {
        return new TextPresent(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mBtNetworking = findViewById(R.id.bt_networking);
    }

    @Override
    protected void initEvent() {
        mBtNetworking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresent.post("param");
            }
        });
    }

    @Override
    public void onPostSuccess() {
        Log.i(TAG, "onPostSuccess: 请求成功");
    }

    @Override
    public void onPostFail(ExceptionHandle.ERROR fail) {
        Log.i(TAG, "onPostSuccess: 请求失败：" + fail.getMessage());
    }

}
