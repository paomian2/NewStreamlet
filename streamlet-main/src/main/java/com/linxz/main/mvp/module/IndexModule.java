package com.linxz.main.mvp.module;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.linxz.core.http.RestClient;
import com.linxz.core.http.callback.IError;
import com.linxz.core.http.callback.ISuccess;
import com.linxz.core.mvp.BaseModel;
import com.linxz.main.http.MainApiStore;

import java.util.HashMap;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月18日20:39  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class IndexModule extends BaseModel{

    /**获取Banner*/
    public void getBanners(final ISuccess success, final IError error){
        final RestClient restClient=RestClient.builder()
                .url(MainApiStore.BANNERS)
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
                .httpGet();
        putHttpClient(restClient);
    }

    /**获取活跃用户*/
    public void getActiveUsers(final ISuccess success,final IError error){
        final RestClient restClient=RestClient.builder()
                .url(MainApiStore.ACTIVEUSERS)
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
                .httpGet();
        putHttpClient(restClient);
    }

    /**获取IT资讯*/
    public void getITNews(final ISuccess success,final IError error){
        final RestClient restClient=RestClient.builder()
                .url(MainApiStore.ITNEWS)
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
                .httpGet();
        putHttpClient(restClient);
    }

    /**获取发现*/
    public void getDiscovers(HashMap<String,Object> params, final ISuccess success, final IError error){
        final RestClient restClient=RestClient.builder()
                .url(MainApiStore.DISCVER)
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
}
