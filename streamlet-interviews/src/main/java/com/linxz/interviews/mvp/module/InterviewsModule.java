package com.linxz.interviews.mvp.module;

import com.alibaba.fastjson.JSON;
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
 * V1.0   2018年03月21日0:52  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class InterviewsModule extends BaseModel{

    /**获取面试题*/
    public void getInterview(Map<String,Object> params, final ISuccess success, final IError error){
         final RestClient restClient=RestClient
                 .builder()
                 .url(InterviewApiStore.INTERVIEW)
                 .raw(params)
                 .success(new ISuccess() {
                     @Override
                     public void onSuccess(String response) {
                         JSONObject jsonObject= JSON.parseObject(response);
                         int code=jsonObject.getInteger("status");
                         String msg=jsonObject.getString("msg");
                         if (code==SUCCESS_CODE){
                             JSONObject object=jsonObject.getJSONObject("data");
                             String json=JSON.toJSONString(object);
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
