package com.linxz.androidstudy.ui;

import android.support.v7.widget.AppCompatTextView;

import com.linxz.androidstudy.R;
import com.linxz.androidstudy.pojo.ArticleEntity;
import com.linxz.core.ui.recycle.ItemType;
import com.linxz.core.ui.recycle.MultipleFields;
import com.linxz.core.ui.recycle.MultipleItemEntity;
import com.linxz.core.ui.recycle.MultipleRecyclerAdapter;
import com.linxz.core.ui.recycle.MultipleViewHolder;

import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月22日23:02  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class AndroidLibAdapter extends MultipleRecyclerAdapter {

    public static AndroidLibAdapter creater(List<MultipleItemEntity> data) {
        return new AndroidLibAdapter(data);
    }

    protected AndroidLibAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ItemType.EMPTY_DATA, com.linxz.core.R.layout.core_item_empty_data);
        addItemType(ItemType.DISCOVER_TEXT, R.layout.study_item_android_lib);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        switch (holder.getItemViewType()) {
            case ItemType.EMPTY_DATA:
                break;
            case ItemType.DISCOVER_TEXT:
                final AppCompatTextView tvAndroidLib=holder.getView(R.id.tvAndroidLib);
                final ArticleEntity articleEntity=entity.getField(MultipleFields.TEXT);
                tvAndroidLib.setText(articleEntity.getTitle());
                break;
            default:
                break;
        }
    }
}
