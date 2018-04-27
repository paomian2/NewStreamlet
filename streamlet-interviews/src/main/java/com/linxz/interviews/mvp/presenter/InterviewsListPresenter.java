package com.linxz.interviews.mvp.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linxz.core.http.callback.IError;
import com.linxz.core.http.callback.ISuccess;
import com.linxz.core.mvp.BasePresenter;
import com.linxz.interviews.mvp.appui.InterviewsListView;
import com.linxz.interviews.mvp.module.InterviewsListModule;
import com.linxz.interviews.pojo.InterviewsEntity;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月20日23:03  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class InterviewsListPresenter extends BasePresenter<InterviewsListView, InterviewsListModule> {

    public InterviewsListPresenter(InterviewsListView mView) {
        super(mView);
    }

    @Override
    public InterviewsListModule onCreateModel() {
        return new InterviewsListModule();
    }

    /**
     * 根据分类Id获取面试题列表
     */
    public void getInterviews(Map<String, Object> params) {
        mModel.getInterviews(params, new ISuccess() {
            @Override
            public void onSuccess(String response) {
                List<InterviewsEntity> interviewEntities=new Gson().fromJson(response,new TypeToken<List<InterviewsEntity>>(){}.getType());
                 mView.getInterviewsSuccess(interviewEntities);
            }
        }, new IError() {
            @Override
            public void onError(int code, String msg) {
                mView.getInterviewsFailuer(msg);
            }
        });
    }
}
