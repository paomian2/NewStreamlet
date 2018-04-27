package com.linxz.users.ui.push;

import android.support.v7.widget.AppCompatTextView;

import com.linxz.core.ui.recycle.ItemType;
import com.linxz.core.ui.recycle.MultipleFields;
import com.linxz.core.ui.recycle.MultipleItemEntity;
import com.linxz.core.ui.recycle.MultipleRecyclerAdapter;
import com.linxz.core.ui.recycle.MultipleViewHolder;
import com.linxz.core.utils.image.ImageHelper;
import com.linxz.core.widget.CircleImageView;
import com.linxz.users.R;
import com.linxz.users.pojo.push.DiscussEntity;

import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月14日2:13  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class DiscussAdapter extends MultipleRecyclerAdapter {

    public DiscussAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ItemType.DISCOVER_TEXT, R.layout.user_view_discuss);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        switch (holder.getItemViewType()) {
            case ItemType.DISCOVER_TEXT:
                final CircleImageView img_users=holder.getView(R.id.img_users);
                final AppCompatTextView tv_user_name_discuss=holder.getView(R.id.tv_user_name_discuss);

                final DiscussEntity discussEntity=entity.getField(MultipleFields.TEXT);
                ImageHelper.imageNet(mContext,discussEntity.getUserImg(),img_users);
                tv_user_name_discuss.setText(discussEntity.getUserName()+""+discussEntity.getDiscussContent());
                break;
            default:
                break;
        }
    }
}
