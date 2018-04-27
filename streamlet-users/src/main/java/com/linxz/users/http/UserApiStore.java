package com.linxz.users.http;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月18日17:03  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class UserApiStore {

    /**登录*/
    public static final String SIGN_IN="/linxz-user/user/login";
    /**注册*/
    public static final String SIGN_UP="/linxz-user/user/register";
    /**更新用户信息*/
    public static final String UPDATE_USERINFO="/linxz-user/user/update";
    /**获取用户信息*/
    public static final String USER_INFO="/linxz-user/user/getUserInfo";
    /**文件上传*/
    public static final String UPLOAD="/linxz-user/file/images";
    /**获取Android学习分类*/
    public static final String GET_CATES="/linxz-user/category/leafList";
    /**发布消息*/
    public static final String PUSH_MESSAGE="/linxz-user/dynamic/dynamicPut";
    /**信息列表*/
    public static final String PUSH_LIST="/linxz-user/dynamic/list";
}
