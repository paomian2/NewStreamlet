package com.linxz.main.mvp.presenter;

import com.linxz.core.mvp.BaseModel;
import com.linxz.core.mvp.BasePresenter;
import com.linxz.main.mvp.appui.AppLauncherView;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月06日21:21  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public class AppLauncherPresenter extends BasePresenter<AppLauncherView,BaseModel>{

    public AppLauncherPresenter(AppLauncherView mView) {
        super(mView);
    }

    @Override
    public BaseModel onCreateModel() {
        return new BaseModel();
    }
}
