package com.linxz.androidstudy.app;

import com.linxz.androidstudy.mvp.appui.BasicAndroidDelegate;
import com.linxz.core.base.IApplicationDelegate;
import com.linxz.core.base.ViewManager;
/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月24日12:37  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class MyDelegate implements IApplicationDelegate {
    @Override
    public void onCreate() {
        //主动添加
        ViewManager.getInstance().addFragment(0, BasicAndroidDelegate.newInstance());
    }

    @Override
    public void onTerminate() {

    }

    @Override
    public void onLowMemory() {

    }

    @Override
    public void onTrimMemory(int level) {

    }
}