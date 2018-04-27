package com.linxz.androidstudy.ui.index;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.RelativeLayout;

import com.linxz.androidstudy.R;
import com.linxz.androidstudy.pojo.Cate;
import com.linxz.core.ui.recycle.ItemType;
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
 * V1.0   2018年03月11日1:09  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class BasicAndroidCategoryAdapter extends MultipleRecyclerAdapter {

    public BasicAndroidCategoryAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ItemType.EMPTY_DATA, R.layout.core_item_empty_data);
        addItemType(StudyItemType.STUDY_ITEM, R.layout.study_item_first_page);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        switch (holder.getItemViewType()){
            case ItemType.EMPTY_DATA:
                final RelativeLayout layoutMainFail=holder.getView(R.id.layout_main_fail);
                layoutMainFail.setVisibility(View.VISIBLE);
                break;
            case StudyItemType.STUDY_ITEM:
                final Cate categary=entity.getField(StudyItemFields.STUDY);
                final AppCompatTextView tvCategory=holder.getView(R.id.tv_category);
                tvCategory.setText(categary.getTypeName());
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(int position) {
        super.onItemClick(position);
    }
}
