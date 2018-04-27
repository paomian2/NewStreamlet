package com.linxz.androidstudy.mvp.presenter;

import com.linxz.androidstudy.mvp.appui.ArticleListView;
import com.linxz.androidstudy.mvp.module.ArticleListModule;
import com.linxz.core.mvp.BasePresenter;

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
public class ArticleListPresenter extends BasePresenter<ArticleListView,ArticleListModule>{

    public ArticleListPresenter(ArticleListView mView) {
        super(mView);
    }

    @Override
    public ArticleListModule onCreateModel() {
        return new ArticleListModule();
    }
}
