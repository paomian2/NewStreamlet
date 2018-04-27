package com.linxz.users.mvp.presenter;

import com.linxz.core.http.callback.IError;
import com.linxz.core.http.callback.ISuccess;
import com.linxz.core.mvp.BasePresenter;
import com.linxz.users.mvp.appui.SignUpI;
import com.linxz.users.mvp.module.SignUpModule;

import java.util.Map;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月18日17:01  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class SignUpPresenter extends BasePresenter<SignUpI,SignUpModule>{

    public SignUpPresenter(SignUpI mView) {
        super(mView);
    }

    @Override
    public SignUpModule onCreateModel() {
        return new SignUpModule();
    }

    /**注册*/
    public void signUp(Map<String,Object> params){
        mModel.signUp(params, new ISuccess() {
            @Override
            public void onSuccess(String response) {
                mView.signUpSuccess();
            }
        }, new IError() {
            @Override
            public void onError(int code, String msg) {
                mView.signUpFailer(msg);
            }
        });
    }
}
