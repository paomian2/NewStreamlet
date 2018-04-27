package com.linxz.users.mvp.module;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linxz.core.http.RestClient;
import com.linxz.core.http.callback.IError;
import com.linxz.core.http.callback.ISuccess;
import com.linxz.core.mvp.BaseModel;
import com.linxz.core.utils.LinxzSharedPreference;
import com.linxz.users.http.UserApiStore;
import com.linxz.users.utils.Constans;

import java.util.Map;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月18日17:02  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class SignUpModule extends BaseModel {

    /**
     * 注册
     */
    public void signUp(Map<String, Object> params, final ISuccess success, final IError error) {
        final RestClient restClient = RestClient.builder()
                .url(UserApiStore.SIGN_UP)
                .raw(params)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject jsonObject = JSON.parseObject(response);
                        int code = jsonObject.getInteger("status");
                        String msg = jsonObject.getString("msg");
                        if (code == SUCCESS_CODE) {
                            String userJson = jsonObject.getString("data");
                            LinxzSharedPreference.addCustomAppProfile(Constans.USER_MESSAGE,userJson);
                            LinxzSharedPreference.setAppFlag(Constans.HAS_SIGN_IN,true);
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
