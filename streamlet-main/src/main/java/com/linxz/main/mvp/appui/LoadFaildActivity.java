package com.linxz.main.mvp.appui;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linxz.core.activitys.BaseActivity;
import com.linxz.main.R;

/**
 * <p>
 * Function： 数据加载失败布局
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月20日2:28  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
@Route(path = "/main/fail")
public class LoadFaildActivity extends BaseActivity{

    @Override
    public Object setLayout() {
        return R.layout.main_act_fail;
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
