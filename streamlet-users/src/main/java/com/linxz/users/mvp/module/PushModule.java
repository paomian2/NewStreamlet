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
 * V1.0   2018年04月03日20:35  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class PushModule extends BaseModel{

    public void getCate(Map<String,Object> params, final ISuccess success, final IError error){
        final RestClient restClient=RestClient
                .builder()
                .url(UserApiStore.GET_CATES)
                .raw(params)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject jsonObject= JSON.parseObject(response);
                        int code=jsonObject.getInteger("status");
                        String msg=jsonObject.getString("msg");
                        if (code==SUCCESS_CODE){
                            JSONArray jsonArray=jsonObject.getJSONArray("data");
                            String json=JSON.toJSONString(jsonArray);
                            success.onSuccess(json);
                        }else{
                            error.onError(code,msg);
                        }
                    }
                })
                .error(error)
                .build()
                .httpPost();
        putHttpClient(restClient);
    }

    /**
     * 图片上传
     */
    public void upload(String filePath, final ISuccess success, final IError error) {
        final RestClient restClient = RestClient
                .builder()
                .url(UserApiStore.UPLOAD)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject jsonObject = JSON.parseObject(response);
                        int code = jsonObject.getInteger("status");
                        String msg = jsonObject.getString("msg");
                        if (code == SUCCESS_CODE) {
                            String images = jsonObject.getString("data");
                            success.onSuccess(images);
                        } else {
                            error.onError(code, msg);
                        }
                    }
                })
                .error(error)
                .file(filePath)
                .build()
                .httpUpload();

        putHttpClient(restClient);
    }

    /**发布消息*/
    public void pushMessage(Map<String,Object> params, final ISuccess success, final IError error){
        final RestClient restClient=RestClient
                .builder()
                .url(UserApiStore.PUSH_MESSAGE)
                .raw(params)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject jsonObject= JSON.parseObject(response);
                        int code=jsonObject.getInteger("status");
                        String msg=jsonObject.getString("msg");
                        if (code==SUCCESS_CODE){
                            success.onSuccess(msg);
                        }else{
                            error.onError(code,msg);
                        }
                    }
                })
                .error(error)
                .build()
                .httpPost();
        putHttpClient(restClient);
    }

}
