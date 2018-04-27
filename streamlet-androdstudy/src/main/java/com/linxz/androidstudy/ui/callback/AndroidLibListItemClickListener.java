package com.linxz.androidstudy.ui.callback;

import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.linxz.androidstudy.pojo.ArticleEntity;
import com.linxz.core.ui.recycle.MultipleFields;
import com.linxz.core.ui.recycle.MultipleItemEntity;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月21日2:38  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class AndroidLibListItemClickListener extends SimpleClickListener {

    public static AndroidLibListItemClickListener creater(){
        return new AndroidLibListItemClickListener();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        final ArticleEntity articleEntity=entity.getField(MultipleFields.TEXT);
        ARouter.getInstance()
                .build("/study/blog")
                .withString("blogId",articleEntity.getBasicKnowledgeId())
                .withString("title","Android开源库")
                .withString("url",articleEntity.getLinkUrl())
                .navigation();
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
