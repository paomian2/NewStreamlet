package com.linxz.androidstudy.mvp.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linxz.androidstudy.mvp.appui.AndroidLibView;
import com.linxz.androidstudy.mvp.module.AndroidLibModule;
import com.linxz.androidstudy.pojo.ArticleEntity;
import com.linxz.core.http.callback.IError;
import com.linxz.core.http.callback.ISuccess;
import com.linxz.core.mvp.BasePresenter;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月21日23:03  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class AndroidLibPresenter extends BasePresenter<AndroidLibView,AndroidLibModule>{

    public AndroidLibPresenter(AndroidLibView mView) {
        super(mView);
    }

    @Override
    public AndroidLibModule onCreateModel() {
        return new AndroidLibModule();
    }

    /**获取Android库*/
    public void getAndroidLibs(Map<String,Object> params){
        mModel.getAndroidLibs(params, new ISuccess() {
            @Override
            public void onSuccess(String response) {
                List<ArticleEntity> articleEntityList=new Gson().fromJson(response,new TypeToken<List<ArticleEntity>>(){}.getType());
                mView.getAndroidLibsSuccess(articleEntityList);
            }
        }, new IError() {
            @Override
            public void onError(int code, String msg) {
                mView.getAndroidLibsFailuer();
            }
        });
    }
}
