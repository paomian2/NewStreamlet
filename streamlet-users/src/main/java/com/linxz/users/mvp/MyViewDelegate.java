package com.linxz.users.mvp;

import android.view.View;

import com.linxz.core.base.IViewDelegate;
import com.linxz.core.fragments.BaseFragment;
import com.linxz.users.mvp.appui.ImDelegate;

/**
 * <p>
 * Function： 首页
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月24日19:57  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class MyViewDelegate implements IViewDelegate{

    @Override
    public BaseFragment getFragment(String name) {
        return ImDelegate.newInstance();
    }

    @Override
    public View getView(String name) {
        return null;
    }
}
