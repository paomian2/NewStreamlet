package com.linxz.main.ui.index;

import android.support.v7.widget.AppCompatImageView;

import com.linxz.core.ui.recycle.MultipleItemEntity;
import com.linxz.core.ui.recycle.MultipleRecyclerAdapter;
import com.linxz.core.ui.recycle.MultipleViewHolder;
import com.linxz.core.utils.image.ImageHelper;
import com.linxz.main.R;

import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月10日19:29  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class IndexDiscoverImgesAdapter extends MultipleRecyclerAdapter {

    protected IndexDiscoverImgesAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(IndexItemType.DISCOVER_MORE_IMAGE, R.layout.item_item_index_discover_img);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        switch (holder.getItemViewType()){
            case IndexItemType.DISCOVER_MORE_IMAGE:
                final String imgUrl=entity.getField(IndexItemFields.DISCOVER);
                final AppCompatImageView imgDiscover=holder.getView(R.id.item_img);
                ImageHelper.imageNet(mContext,imgUrl,imgDiscover);
                break;
            default:
                break;
        }
    }
}
