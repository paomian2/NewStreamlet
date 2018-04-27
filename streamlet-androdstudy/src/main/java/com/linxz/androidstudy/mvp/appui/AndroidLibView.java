package com.linxz.androidstudy.mvp.appui;

import com.linxz.androidstudy.pojo.ArticleEntity;
import com.linxz.core.mvp.BaseView;

import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月21日23:03  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public interface AndroidLibView extends BaseView{

    /**获取Android库*/
    void getAndroidLibs();
    void getAndroidLibsSuccess(List<ArticleEntity> articleEntityList);
    void getAndroidLibsFailuer();

}
