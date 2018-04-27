package com.linxz.main.ui.index;
import android.support.v7.widget.AppCompatTextView;

import com.linxz.core.ui.recycle.MultipleItemEntity;
import com.linxz.core.ui.recycle.MultipleRecyclerAdapter;
import com.linxz.core.ui.recycle.MultipleViewHolder;
import com.linxz.core.utils.image.ImageHelper;
import com.linxz.core.widget.CircleImageView;
import com.linxz.main.R;
import com.linxz.main.pojo.index.UserEntity;

import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月10日0:25  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class IndexUserAdapter extends MultipleRecyclerAdapter{

    protected IndexUserAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(IndexItemType.ACTIVE_USERS, R.layout.item_item_index_users);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        final CircleImageView imgUser=holder.getView(R.id.civ_user);
        final AppCompatTextView tvUserName=holder.getView(R.id.tv_user_name);
        final UserEntity userEntity=entity.getField(IndexItemFields.USERS);
        ImageHelper.imageNet(mContext,userEntity.getPortrait(),imgUser);
        tvUserName.setText(userEntity.getUserName());
    }

    @Override
    public void onItemClick(int position) {
        super.onItemClick(position);
    }
}
