package com.linxz.users.mvp.module;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
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
 * V1.0   2018年03月17日17:15  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class UserHttpModule extends BaseModel {

    public void signIn(Map<String,Object> params,final ISuccess success,final IError error) {
        final RestClient restClient = RestClient.builder()
                .url(UserApiStore.SIGN_IN)
                .raw(new Gson().toJson(params))
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                         int code= JSON.parseObject(response).getInteger("status");
                         String msg=JSON.parseObject(response).getString("msg");
                         if (code==SUCCESS_CODE){
                             String userJson=JSON.parseObject(response).getString("data");
                             LinxzSharedPreference.addCustomAppProfile(Constans.USER_MESSAGE,userJson);
                             LinxzSharedPreference.setAppFlag(Constans.HAS_SIGN_IN,true);
                             success.onSuccess(msg);
                         }else{
                             error.onError(code,msg);
                         }
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        error.onError(code,msg);
                    }
                })
                .build()
                .httpPost();
        putHttpClient(restClient);
    }
}
