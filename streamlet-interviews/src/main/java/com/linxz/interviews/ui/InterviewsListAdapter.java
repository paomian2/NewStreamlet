package com.linxz.interviews.ui;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.RelativeLayout;

import com.linxz.core.ui.recycle.ItemType;
import com.linxz.core.ui.recycle.MultipleFields;
import com.linxz.core.ui.recycle.MultipleItemEntity;
import com.linxz.core.ui.recycle.MultipleRecyclerAdapter;
import com.linxz.core.ui.recycle.MultipleViewHolder;
import com.linxz.interviews.R;
import com.linxz.interviews.pojo.InterviewsEntity;

import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月12日2:29  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class InterviewsListAdapter extends MultipleRecyclerAdapter{

    public InterviewsListAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ItemType.EMPTY_DATA, R.layout.core_item_empty_data);
        addItemType(ItemType.DISCOVER_TEXT, R.layout.interviews_item_list);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        switch (holder.getItemViewType()){
            case ItemType.EMPTY_DATA:
                final RelativeLayout layoutMainFail=holder.getView(R.id.layout_main_fail);
                layoutMainFail.setVisibility(View.VISIBLE);
                break;
            case ItemType.DISCOVER_TEXT:
                final InterviewsEntity interviewEntity=entity.getField(MultipleFields.TEXT);
                final AppCompatTextView tvVitle=holder.getView(R.id.tv_title);
                tvVitle.setText(interviewEntity.getQuestion());
                break;
            default:
                break;
        }
    }
}
