package com.demo.www.demo.text;

import com.demo.www.demo.base.BaseView;
import com.demo.www.demo.network.ExceptionHandle;

/**
 * author:  zhouchaoxiang
 * date:    2018/6/28
 * explain:
 */
public interface TextView extends BaseView {

    void onPostSuccess();

    void onPostFail(ExceptionHandle.ERROR fail);
}
