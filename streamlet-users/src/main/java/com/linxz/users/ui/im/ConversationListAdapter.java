package com.linxz.users.ui.im;

import android.annotation.SuppressLint;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.linxz.core.ui.recycle.ItemType;
import com.linxz.core.ui.recycle.MultipleFields;
import com.linxz.core.ui.recycle.MultipleItemEntity;
import com.linxz.core.ui.recycle.MultipleRecyclerAdapter;
import com.linxz.core.ui.recycle.MultipleViewHolder;
import com.linxz.core.utils.image.ImageHelper;
import com.linxz.core.utils.time.TimeUtils;
import com.linxz.core.widget.CircleImageView;
import com.linxz.users.R;
import com.linxz.users.pojo.ConversationMessage;
import com.mob.imsdk.model.IMUser;

import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月24日23:15  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class ConversationListAdapter extends MultipleRecyclerAdapter {

    public static ConversationListAdapter newInstance(List<MultipleItemEntity> data) {
        return new ConversationListAdapter(data);
    }

    private ConversationListAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ItemType.EMPTY_DATA, com.linxz.core.R.layout.core_item_empty_data);
        addItemType(ItemType.ACTIVE_USERS, R.layout.user_item_conversation);
    }


    @SuppressLint("SetTextI18n")
    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        switch (holder.getItemViewType()) {
            case ItemType.EMPTY_DATA:
                RecyclerView layoutMainFail=holder.getView(R.id.layout_main_fail);
                layoutMainFail.setVisibility(View.VISIBLE);
                break;
            case ItemType.ACTIVE_USERS:
                final CircleImageView imgUser=holder.getView(R.id.img_user);
                final AppCompatTextView tvUserName=holder.getView(R.id.tv_user_name);
                final AppCompatTextView tvLastMsg=holder.getView(R.id.tv_last_msg);
                final AppCompatTextView tvTime=holder.getView(R.id.tv_time);
                final AppCompatTextView tvUnread=holder.getView(R.id.tv_unread);

                final ConversationMessage message=entity.getField(MultipleFields.IM_USERS);
                final IMUser imUser=message.getLastMessage().getFromUserInfo();
                int unreadCount=message.getUnreadMsgCount();

                ImageHelper.imageNet(mContext,imUser.getAvatar(),imgUser);
                tvUserName.setText(imUser.getNickname());
                tvLastMsg.setText(message.getLastMessage().getBody());
                tvTime.setText(TimeUtils.fmttoCN(message.getLastMessage().getCreateTime()+""));
                tvUnread.setText(unreadCount>99?"99+":(unreadCount+""));
                break;
            default:
                break;
        }
    }
}
