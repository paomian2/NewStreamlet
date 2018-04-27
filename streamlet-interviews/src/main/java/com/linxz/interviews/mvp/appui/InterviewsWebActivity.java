package com.linxz.interviews.mvp.appui;

import android.os.Bundle;

import com.linxz.core.activitys.BaseActivity;
import com.linxz.core.activitys.web.WebDelegateImpl;
import com.linxz.interviews.R;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月12日1:49  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class InterviewsWebActivity extends BaseActivity{

    private String url="";

    @Override
    public void onGetBundle(Bundle bundle) {
        super.onGetBundle(bundle);
        url=getIntent().getStringExtra("url");
    }

    @Override
    public Object setLayout() {
        return R.layout.interviews_act_web;
    }

    @Override
    public void initUI() {
        final WebDelegateImpl delegate = WebDelegateImpl.create(url);
        getSupportDelegate().loadRootFragment(R.id.web_discovery_container, delegate);
    }

    @Override
    public void initEnvent() {

    }

    @Override
    public void initData() {

    }
}
