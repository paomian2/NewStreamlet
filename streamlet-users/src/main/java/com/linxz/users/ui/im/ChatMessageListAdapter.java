package com.linxz.users.ui.im;

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
 * V1.0   2018年03月25日20:59  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class ChatMessageListAdapter extends MultipleRecyclerAdapter{

    public static ChatMessageListAdapter newInstance(List<MultipleItemEntity> data){
        return new ChatMessageListAdapter(data);
    }

    protected ChatMessageListAdapter(List<MultipleItemEntity> data) {
        super(data);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
    }
}
