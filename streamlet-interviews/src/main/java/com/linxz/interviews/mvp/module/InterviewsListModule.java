package com.linxz.interviews.mvp.module;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.linxz.core.http.RestClient;
import com.linxz.core.http.callback.IError;
import com.linxz.core.http.callback.ISuccess;
import com.linxz.core.mvp.BaseModel;
import com.linxz.interviews.http.InterviewApiStore;
import java.util.Map;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月20日23:04  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class InterviewsListModule extends BaseModel{

    /**根据分类Id获取面试题列表*/
    public void getInterviews(Map<String,Object> params, final ISuccess success, final IError error){
        final RestClient restClient=RestClient
                .builder()
                .url(InterviewApiStore.INTERVIEWS)
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
