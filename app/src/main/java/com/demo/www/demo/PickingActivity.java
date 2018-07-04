package com.demo.www.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PickingActivity extends AppCompatActivity implements TextView.OnEditorActionListener {
    private static final String TAG = "PickingActivity";
    @BindView(R.id.et_sku)
    EditText mEtSku;
    @BindView(R.id.rv_sku_list)
    RecyclerView mRvSkuList;
    @BindView(R.id.tv_quantity)
    TextView mTvQuantity;
    @BindView(R.id.fl_list_title)
    FrameLayout mFlListTitle;
    private ArrayList<PickingBean> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picking);
        ButterKnife.bind(this);
        initData();
        initView();
        initEvent();
    }

    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            PickingBean pickingBean = new PickingBean();
            pickingBean.location = "2B10-12-02-06" + i;
            pickingBean.sku = "1A40718" + i;
            pickingBean.needQuantity = "<font color='#0000ff' >12</font>" + i;
            pickingBean.currentQuantity = "<font size=\"20\" color=\"red\">0</font>";
            pickingBean.name = "XB2-ES542 塑料急停钮（塑料式带锁XB2-ES542急停） " + i;
            mDatas.add(pickingBean);
        }
    }

    private void initView() {
        mFlListTitle.setBackgroundResource(R.color.colorAccent);
        mTvQuantity.setText(getResources().getString(R.string.picking_quantity,"需","已"));
        mRvSkuList.setLayoutManager(new LinearLayoutManager(this));
        mRvSkuList.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        PickingAdapter pickingAdapter = new PickingAdapter(mDatas);
        mRvSkuList.setAdapter(pickingAdapter);
    }

    private void initEvent() {
        mEtSku.setOnEditorActionListener(this);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        Log.i(TAG, "onEditorAction: actionId:"+actionId+"\r\nKeyEvent:"+event);
        return true;
    }
}
