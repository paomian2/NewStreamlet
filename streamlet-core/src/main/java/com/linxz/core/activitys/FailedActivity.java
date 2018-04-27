package com.linxz.core.activitys;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

import com.linxz.core.R;
import com.linxz.core.R2;
import com.linxz.core.utils.StringUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年04月20日9:58  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class FailedActivity extends BaseActivity{

    private String mErrorCode="加载错误";

    @BindView(R2.id.tv_title)
    AppCompatTextView tvTitle;

    @OnClick(R2.id.tv_reback)
    public void reBackClick(){
        finish();
    }

    @Override
    public void onGetBundle(Bundle bundle) {
        super.onGetBundle(bundle);
        String errorCode=getIntent().getStringExtra("errorCode");
        if (!StringUtils.isEmpty(errorCode)){
            mErrorCode=errorCode;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.base_act_failed;
    }

    @Override
    public void initUI() {
        tvTitle.setText(mErrorCode);
    }

    @Override
    public void initEnvent() {

    }

    @Override
    public void initData() {

    }
}
