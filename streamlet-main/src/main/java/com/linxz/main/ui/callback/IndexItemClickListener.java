package com.linxz.main.ui.callback;

import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.linxz.core.ui.recycle.MultipleItemEntity;
import com.linxz.main.pojo.index.NewsEntity;
import com.linxz.main.ui.index.IndexItemFields;
import com.linxz.main.ui.index.IndexItemType;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月20日0:39  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class IndexItemClickListener extends SimpleClickListener {

    public static IndexItemClickListener create(){
        return new IndexItemClickListener();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        switch (entity.getItemType()) {
            case IndexItemType.TITLE:
                break;
            case IndexItemType.BANNER:
                break;
            case IndexItemType.ACTIVE_USERS:
                break;
            case IndexItemType.NEWS_IT:
                final NewsEntity newsEntity = entity.getField(IndexItemFields.NEWS);
                ARouter.getInstance()
                        .build("/study/blog")
                        .withString("blogId",newsEntity.getId())
                        .withString("title",newsEntity.getTitle())
                        .withString("url",newsEntity.getLinkUrl())
                        .navigation();
                break;
            case IndexItemType.DISCOVER:
                break;
            default:
                break;
        }
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
