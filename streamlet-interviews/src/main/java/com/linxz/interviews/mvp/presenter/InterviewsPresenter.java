package com.linxz.interviews.mvp.presenter;

import com.google.gson.Gson;
import com.linxz.core.http.callback.IError;
import com.linxz.core.http.callback.ISuccess;
import com.linxz.core.mvp.BasePresenter;
import com.linxz.interviews.mvp.appui.InterviewsView;
import com.linxz.interviews.mvp.module.InterviewsModule;
import com.linxz.interviews.pojo.InterviewsEntity;

import java.util.Map;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月21日0:51  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class InterviewsPresenter extends BasePresenter<InterviewsView, InterviewsModule> {

    public InterviewsPresenter(InterviewsView mView) {
        super(mView);
    }

    @Override
    public InterviewsModule onCreateModel() {
        return new InterviewsModule();
    }

    /**
     * 获取面试题
     */
    public void getInterview(Map<String, Object> params) {
        mModel.getInterview(params, new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        InterviewsEntity interviewsEntity=new Gson().fromJson(response,InterviewsEntity.class);
                        mView.getInterviewSuccess(interviewsEntity);
                    }
                },
                new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        mView.getInterviewFailuer();
                    }
                });
    }
}
