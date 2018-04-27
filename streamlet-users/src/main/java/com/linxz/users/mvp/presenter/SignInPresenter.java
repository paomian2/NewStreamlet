package com.linxz.users.mvp.presenter;
import com.linxz.core.http.callback.IError;
import com.linxz.core.http.callback.ISuccess;
import com.linxz.core.mvp.BasePresenter;
import com.linxz.users.mvp.appui.SignInI;
import com.linxz.users.mvp.module.UserHttpModule;

import java.util.Map;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月17日17:08  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class SignInPresenter extends BasePresenter<SignInI,UserHttpModule>{

    public SignInPresenter(SignInI mView) {
        super(mView);
    }

    @Override
    public UserHttpModule onCreateModel() {
        return new UserHttpModule();
    }

    /**注册*/
    public void signIn(Map<String,Object> params){
        mModel.signIn(params, new ISuccess() {
            @Override
            public void onSuccess(String response) {
                mView.signInSuccess();
            }
        }, new IError() {
            @Override
            public void onError(int code, String msg) {
                mView.signInFauler(msg);
            }
        });
    }
}
