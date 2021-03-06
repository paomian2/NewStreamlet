package com.linxz.users.mvp.module;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linxz.core.http.RestClient;
import com.linxz.core.http.callback.IError;
import com.linxz.core.http.callback.ISuccess;
import com.linxz.core.mvp.BaseModel;
import com.linxz.users.http.UserApiStore;
import com.linxz.users.utils.UserSharePrefreshHanlder;

import java.util.HashMap;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年04月01日17:34  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class UserInfoModule extends BaseModel {

    public void getUserInfo(HashMap<String, Object> params, final ISuccess success, final IError error) {
        final RestClient restClient = RestClient
                .builder()
                .url(UserApiStore.USER_INFO)
                .raw(params)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        JSONObject jsonObject = JSON.parseObject(response);
                        int code = jsonObject.getInteger("status");
                        String msg = jsonObject.getString("msg");
                        if (code == SUCCESS_CODE) {
                            String userStr = jsonObject.getString("data");
                            UserSharePrefreshHanlder.updateUserInfo(userStr);
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

    /**
     * 编辑个人信息
     */
    public void edtUserInfo(HashMap<String, Object> params, final ISuccess success, final IError error) {
        final RestClient restClient = RestClient.builder()
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
}
