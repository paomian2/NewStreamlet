package com.linxz.main.mvp.appui.frags;

import com.linxz.core.mvp.BaseView;
import com.linxz.main.pojo.index.BannerEntity;
import com.linxz.main.pojo.index.DiscoverEntity;
import com.linxz.main.pojo.index.NewsEntity;
import com.linxz.main.pojo.index.UserEntity;

import java.util.List;
/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月18日20:32  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public interface IndexI extends BaseView{

    /**获取banner*/
    void getBanners();
    void getBannersSuccess(List<BannerEntity> banners);
    void getBannerFailer(String msg);

    /**获取活跃用户*/
    void getActiveUsers();
    void getActiveUsersSuccess(List<UserEntity> users);
    void getActiveUsersFailuer();

    /**获取IT资讯*/
    void getITNews();
    void getITNewsSuccess(List<NewsEntity> itnews);
    void getITNewsFailuer();

    /**获取发布信息*/
    void getPushs();
    void getPushSuccess(List<DiscoverEntity> discovers);
    void getPushsFailuer();
}
