package com.linxz.users.mvp.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linxz.core.http.callback.IError;
import com.linxz.core.http.callback.ISuccess;
import com.linxz.core.mvp.BasePresenter;
import com.linxz.users.mvp.appui.push.PushView;
import com.linxz.users.mvp.module.PushModule;
import com.linxz.users.pojo.Cate;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年04月03日20:36  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class PushPresenter extends BasePresenter<PushView,PushModule>{

    public PushPresenter(PushView mView) {
        super(mView);
    }

    @Override
    public PushModule onCreateModel() {
        return new PushModule();
    }

    public void getCate(Map<String, Object> params) {
        mModel.getCate(params, new ISuccess() {
            @Override
            public void onSuccess(String response) {
                List<Cate> cateList=new Gson().fromJson(response,new TypeToken<List<Cate>>(){}.getType());
                mView.getCateSuccess(cateList);
            }
        }, new IError() {
            @Override
            public void onError(int code, String msg) {
                mView.getCateFailuer(msg);
            }
        });
    }

    /**图片上传*/
    public void upload(String paths){
        mModel.upload(paths, new ISuccess() {
            @Override
            public void onSuccess(String response) {
                mView.uploadResult(true,"",response);
            }
        }, new IError() {
            @Override
            public void onError(int code, String msg) {
                mView.uploadResult(false,msg,null);
            }
        });
    }

    public void pushMessage(Map<String, Object> params) {
        mModel.pushMessage(params, new ISuccess() {
            @Override
            public void onSuccess(String response) {
                mView.pushMessageResult(true,200,response);
            }
        }, new IError() {
            @Override
            public void onError(int code, String msg) {
                mView.pushMessageResult(false,code,msg);
            }
        });
    }

}
