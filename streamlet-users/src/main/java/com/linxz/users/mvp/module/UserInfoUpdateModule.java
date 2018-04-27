package com.linxz.users.mvp.module;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linxz.core.http.RestClient;
import com.linxz.core.http.callback.IError;
import com.linxz.core.http.callback.ISuccess;
import com.linxz.core.mvp.BaseModel;
import com.linxz.users.http.UserApiStore;

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
public class UserInfoUpdateModule extends BaseModel{

    /**
     * 编辑个人信息
     */
    public void edtUserInfo(HashMap<String,Object> params, final ISuccess success, final IError error){
        final RestClient restClient=RestClient.builder()
                .url(UserApiStore.UPDATE_USERINFO)
                .raw(params)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject jsonObject = JSON.parseObject(response);
                        int code = jsonObject.getInteger("status");
                        String msg = jsonObject.getString("msg");
                        if (code == SUCCESS_CODE) {
                            success.onSuccess(msg);
                        } else {
                            error.onError(code, msg);
                        }
                    }
                })
                .error(error)
                .build()
                .httpPost();

        putHttpClient(restClient);
    }
}
