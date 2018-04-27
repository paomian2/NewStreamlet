package com.linxz.androidstudy.mvp;

import android.view.View;

import com.linxz.androidstudy.mvp.appui.BasicAndroidDelegate;
import com.linxz.core.base.IViewDelegate;
import com.linxz.core.fragments.BaseFragment;
/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月24日12:17  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class MyViewDelegate implements IViewDelegate {

    @Override
    public BaseFragment getFragment(String name) {
        return BasicAndroidDelegate.newInstance();
    }

    @Override
    public View getView(String name) {
        return null;
    }
}
