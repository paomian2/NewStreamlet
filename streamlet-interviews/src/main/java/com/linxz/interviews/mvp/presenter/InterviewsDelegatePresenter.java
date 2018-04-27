package com.linxz.interviews.mvp.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linxz.core.http.callback.IError;
import com.linxz.core.http.callback.ISuccess;
import com.linxz.core.mvp.BasePresenter;
import com.linxz.interviews.mvp.appui.InterviewsDelegateView;
import com.linxz.interviews.mvp.module.InterviewsDelegatePModudel;
import com.linxz.interviews.pojo.Cate;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月20日2:08  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class InterviewsDelegatePresenter extends BasePresenter<InterviewsDelegateView, InterviewsDelegatePModudel> {

    public InterviewsDelegatePresenter(InterviewsDelegateView mView) {
        super(mView);
    }

    @Override
    public InterviewsDelegatePModudel onCreateModel() {
        return new InterviewsDelegatePModudel();
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
}
