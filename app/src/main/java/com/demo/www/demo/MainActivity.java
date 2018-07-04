package com.demo.www.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtGreenDao;
    private Button mBtPicking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        initEvent();
    }

    private void initData() {

    }

    private void initView() {
        mBtGreenDao = findViewById(R.id.bt_green_dao);
        mBtPicking = findViewById(R.id.bt_picking);
    }

    private void initEvent() {
        mBtGreenDao.setOnClickListener(this);
        mBtPicking.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.bt_green_dao:
                break;
            case R.id.bt_picking:
                intent.setClass(this, PickingActivity.class);
                break;

        }
        startActivity(intent);
    }
}
