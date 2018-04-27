package com.linxz.manager.mvp.view;

import com.linxz.core.activitys.BaseActivity;
import com.linxz.core.mvp.BaseMvpActivity;
import com.linxz.manager.R;
import com.linxz.manager.mvp.presenter.InterviewAddPresenter;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年04月22日21:10  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class InterviewAddActivity extends BaseMvpActivity<InterviewAddPresenter> implements InterviewAddView{

    @Override
    public BaseActivity getContext() {
        return activity;
    }

    @Override
    protected InterviewAddPresenter createPresenter() {
        return new InterviewAddPresenter(this);
    }

    @Override
    public Object setLayout() {
        return R.layout.manager_act_interview_add;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initEnvent() {

    }

    @Override
    public void initData() {

    }
}
