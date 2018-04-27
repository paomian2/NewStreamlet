package com.linxz.main.utils;

import com.google.gson.Gson;
import com.linxz.core.utils.LinxzSharedPreference;
import com.linxz.main.pojo.index.UserEntity;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年04月01日17:58  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class MainSharePrefreshHanlder {

    /**判断用户是否登录*/
    public static boolean isSignIn(){
        String userStr= LinxzSharedPreference.getCustomAppProfile(Constans.USER_MESSAGE);
        return !(userStr == null || userStr.isEmpty());
    }

    /**获取用户信息*/
    public static UserEntity getUserInfo(){
        String userStr= LinxzSharedPreference.getCustomAppProfile(Constans.USER_MESSAGE);
        if (userStr==null || userStr.isEmpty()){
            return null;
        }
        return new Gson().fromJson(userStr,UserEntity.class);
    }

    /**修改用户信息*/
    public static void updateUserInfo(String userInfoStr){
        String oldUserStr=LinxzSharedPreference.getCustomAppProfile(Constans.USER_MESSAGE);
        UserEntity oldUser=new Gson().fromJson(oldUserStr,UserEntity.class);

        UserEntity newUser=new Gson().fromJson(userInfoStr,UserEntity.class);
        newUser.setToken(oldUser.getToken());

        String saveJson=new Gson().toJson(newUser);
        LinxzSharedPreference.addCustomAppProfile(Constans.USER_MESSAGE,saveJson);
    }

    /**获取用户Id*/
    public static String getUserId(){
        UserEntity userEntity=getUserInfo();
        if (userEntity==null){
            return "";
        }
        return userEntity.getUserId();
    }

    /**获取token*/
    public static String getToken(){
        UserEntity userEntity=getUserInfo();
        if (userEntity==null){
            return "";
        }
        return userEntity.getToken();
    }
}
