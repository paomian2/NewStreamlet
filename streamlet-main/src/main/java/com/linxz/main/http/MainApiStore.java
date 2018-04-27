package com.linxz.main.http;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月18日20:31  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class MainApiStore {

    /**首页Banner图*/
    public static final String BANNERS="/linxz-user/navigationContent/banner";
    /**获取活跃用户*/
    public static final String ACTIVEUSERS="/linxz-user/user/activeUser";
    /**首页最新IT资讯*/
    public static final String ITNEWS="/linxz-user/itmsg/itnewslist";
    /**首页发现信息*/
    public static final String DISCVER="/linxz-user/dynamic/discovers";

    /**获取Android学习分类*/
    public static final String GET_CATES="/linxz-user/category/leafList";

    /**获取面试题*/
    public static final String INTERVIEWS="/linxz-user/android_interview/interviews";
}
