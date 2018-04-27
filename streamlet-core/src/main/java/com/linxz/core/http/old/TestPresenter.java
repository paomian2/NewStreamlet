package com.linxz.core.http.old;

import com.linxz.core.mvp.BasePresenter;

import java.util.WeakHashMap;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月06日0:28  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 * @author linxz
 */
public class TestPresenter extends BasePresenter<TestView,TestModel> {

    public TestPresenter(TestView mView) {
        super(mView);
    }

    @Override
    public TestModel onCreateModel() {
        return new TestModel();
    }

    public void sendCode(WeakHashMap<String,Object> params){
         mModel.sendCode(params);
    }
}
