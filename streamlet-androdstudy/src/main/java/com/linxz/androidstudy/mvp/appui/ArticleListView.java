package com.linxz.androidstudy.mvp.appui;

import com.linxz.androidstudy.pojo.Cate;
import com.linxz.core.mvp.BaseView;

import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月21日2:27  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public interface ArticleListView extends BaseView{

    /**获取基础知识分类*/
    void getCateygory();
    void getCateygorySuccess(List<Cate> cateList);
    void getCateygoryFailuer();
}
