package com.linxz.users.mvp.presenter;
import com.linxz.core.http.callback.IError;
import com.linxz.core.http.callback.ISuccess;
import com.linxz.core.mvp.BasePresenter;
import com.linxz.users.mvp.appui.UserInfoUpdateView;
import com.linxz.users.mvp.module.UserInfoUpdateModule;

import java.util.HashMap;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月29日13:06  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class UserInfoUpdatePresenter extends BasePresenter<UserInfoUpdateView,UserInfoUpdateModule>{

    public UserInfoUpdatePresenter(UserInfoUpdateView mView) {
        super(mView);
    }

    @Override
    public UserInfoUpdateModule onCreateModel() {
        return new UserInfoUpdateModule();
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
}
