package com.linxz.users.mvp.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linxz.core.http.callback.IError;
import com.linxz.core.http.callback.ISuccess;
import com.linxz.core.mvp.BasePresenter;
import com.linxz.users.mvp.appui.push.MyPushListView;
import com.linxz.users.mvp.module.MyPushListModule;
import com.linxz.users.pojo.push.DynamicEntity;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年04月03日19:38  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class MyPushListPresenter extends BasePresenter<MyPushListView,MyPushListModule>{

    public MyPushListPresenter(MyPushListView mView) {
        super(mView);
    }

    @Override
    public MyPushListModule onCreateModel() {
        return new MyPushListModule();
    }

    /**获取动态列表*/
    public void getDynamicList(Map<String, Object> params) {
        mModel.getDynamicList(params, new ISuccess() {
            @Override
            public void onSuccess(String response) {
                List<DynamicEntity> list=new Gson().fromJson(response,new TypeToken<List<DynamicEntity>>(){}.getType());
                mView.getDynamicListResult(true,200,response,list);
            }
        }, new IError() {
            @Override
            public void onError(int code, String msg) {
                mView.getDynamicListResult(false,code,msg,null);
            }
        });
    }

}
