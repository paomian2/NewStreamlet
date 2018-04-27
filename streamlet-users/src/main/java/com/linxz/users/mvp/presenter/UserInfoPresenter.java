package com.linxz.users.mvp.presenter;

import com.linxz.core.http.callback.IError;
import com.linxz.core.http.callback.ISuccess;
import com.linxz.core.mvp.BasePresenter;
import com.linxz.users.mvp.appui.UserInfoView;
import com.linxz.users.mvp.module.UserInfoModule;
import com.linxz.users.pojo.UserEntity;
import com.linxz.users.utils.UserSharePrefreshHanlder;
import java.util.HashMap;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月28日15:14  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class UserInfoPresenter extends BasePresenter<UserInfoView,UserInfoModule>{

    public UserInfoPresenter(UserInfoView mView) {
        super(mView);
    }

    @Override
    public UserInfoModule onCreateModel() {
        return new UserInfoModule();
    }

    /**获取用户信息*/
    public void getUserInfo(HashMap<String,Object> params){
        mModel.getUserInfo(params, new ISuccess() {
            @Override
            public void onSuccess(String response) {
                UserEntity userEntity= UserSharePrefreshHanlder.getUserInfo();
                mView.getUserInfoResult(true,userEntity,response);
            }
        }, new IError() {
            @Override
            public void onError(int code, String msg) {
                mView.getUserInfoResult(false,null,msg);
            }
        });
    }

    /**
     * 编辑个人信息
     */
    public void edtUserInfo(HashMap<String,Object> params){
        mModel.edtUserInfo(params, new ISuccess() {
            @Override
            public void onSuccess(String response) {
                mView.edtUserInfoResult(true,200,"更新成功");
            }
        }, new IError() {
            @Override
            public void onError(int code, String msg) {
                mView.edtUserInfoResult(false,code,msg);
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

}
