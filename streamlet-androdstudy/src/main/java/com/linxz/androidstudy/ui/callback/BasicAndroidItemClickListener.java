package com.linxz.androidstudy.ui.callback;

import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.linxz.androidstudy.pojo.Cate;
import com.linxz.androidstudy.ui.index.StudyItemFields;
import com.linxz.androidstudy.ui.index.StudyItemType;
import com.linxz.core.app.Linxz;
import com.linxz.core.ui.recycle.MultipleItemEntity;
/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月20日3:08  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class BasicAndroidItemClickListener extends SimpleClickListener {

    public static BasicAndroidItemClickListener create() {
        return new BasicAndroidItemClickListener();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        if (entity.getItemType() == StudyItemType.STUDY_ITEM) {
            final Cate cate = entity.getField(StudyItemFields.STUDY);
            Toast.makeText(Linxz.getApplicationContext(),"该模块尚未开通,请浏览Android开源库",Toast.LENGTH_SHORT).show();
/*
            ARouter.getInstance()
                    .build("/study/list")
                    .withString("categoryId",cate.getTypeId())
                    .navigation();
*/
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
