package com.linxz.users.mvp.module;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.linxz.core.http.RestClient;
import com.linxz.core.http.callback.IError;
import com.linxz.core.http.callback.ISuccess;
import com.linxz.core.mvp.BaseModel;
import com.linxz.users.http.UserApiStore;
import java.util.Map;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年04月03日19:38  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class MyPushListModule extends BaseModel{

    /**获取动态列表*/
    public void getDynamicList(Map<String,Object> params, final ISuccess success, final IError error){
        final RestClient restClient=RestClient
                .builder()
                .url(UserApiStore.PUSH_LIST)
                .raw(params)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject jsonObject = JSON.parseObject(response);
                        int code = jsonObject.getInteger("status");
                        String msg = jsonObject.getString("msg");
                        if (code == SUCCESS_CODE) {
                            JSONObject dataObject=jsonObject.getJSONObject("data");
                            JSONArray images = dataObject.getJSONArray("list");
                            success.onSuccess(images.toJSONString());
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
