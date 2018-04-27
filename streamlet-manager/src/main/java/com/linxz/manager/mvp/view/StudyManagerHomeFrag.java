package com.linxz.manager.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.linxz.core.activitys.BaseActivity;
import com.linxz.core.mvp.BaseMvpFragment;
import com.linxz.manager.R;
import com.linxz.manager.mvp.presenter.UserManagerHomePresenter;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年04月22日19:45  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class StudyManagerHomeFrag extends BaseMvpFragment<UserManagerHomePresenter> implements UserManagerHomeView{

    @Override
    protected UserManagerHomePresenter onCreatePresenter() {
        return new UserManagerHomePresenter(this);
    }

    @Override
    public Object setLayout() {
        return R.layout.manager_frag_study_home;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @Override
    public void initUI() {

    }

    @Override
    public void initData() {

    }

    @Override
    public BaseActivity getContext() {
        return _activity;
    }
}
