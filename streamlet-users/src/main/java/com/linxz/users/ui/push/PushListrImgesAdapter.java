package com.linxz.users.ui.push;

import android.support.v7.widget.AppCompatImageView;

import com.linxz.core.ui.recycle.MultipleItemEntity;
import com.linxz.core.ui.recycle.MultipleRecyclerAdapter;
import com.linxz.core.ui.recycle.MultipleViewHolder;
import com.linxz.core.utils.image.ImageHelper;
import com.linxz.users.R;
import com.linxz.users.pojo.push.PushItemType;
import com.linxz.users.pojo.push.PushMultipleFields;

import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年04月05日3:21  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class PushListrImgesAdapter extends MultipleRecyclerAdapter{

    public PushListrImgesAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(PushItemType.ON_IMG, R.layout.user_item_pushlist_img);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        switch (holder.getItemViewType()){
            case PushItemType.ON_IMG:
                final String imgUrl=entity.getField(PushMultipleFields.ONE_IMG);
                final AppCompatImageView imgDiscover=holder.getView(R.id.item_img);
                ImageHelper.imageNet(mContext,imgUrl,imgDiscover);
                break;
            default:
                break;
        }
    }
}
