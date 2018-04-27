package com.linxz.main.ui.callback;

import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.linxz.core.ui.recycle.MultipleItemEntity;
import com.linxz.main.pojo.index.UserEntity;
import com.linxz.main.ui.index.IndexItemFields;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月20日1:10  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class ActiveUsersItemClickListener extends SimpleClickListener {

    public static ActiveUsersItemClickListener create(){
        return new ActiveUsersItemClickListener();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        final UserEntity userEntity=entity.getField(IndexItemFields.USERS);
        ARouter.getInstance()
                .build("/user/home2")
                .withString("userId",userEntity.getUserId())
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
