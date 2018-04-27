package com.linxz.users.ui.im;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linxz.core.ui.recycle.DataConverter;
import com.linxz.core.ui.recycle.ItemType;
import com.linxz.core.ui.recycle.MultipleFields;
import com.linxz.core.ui.recycle.MultipleItemEntity;
import com.linxz.users.pojo.ConversationMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月24日23:06  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class ConversationListConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        String dataJson = getJsonData();
        if (dataJson == null || dataJson.isEmpty()) {
            final MultipleItemEntity entity = MultipleItemEntity
                    .builder()
                    .setItemType(ItemType.EMPTY_DATA)
                    .build();
            ENTITIES.add(entity);
        }

        List<ConversationMessage> messageList = new Gson().fromJson(dataJson, new TypeToken<List<ConversationMessage>>() {
        }.getType());

        for (ConversationMessage conversationMessage:messageList){
            final MultipleItemEntity entity = MultipleItemEntity
                    .builder()
                    .setItemType(ItemType.ACTIVE_USERS)
                    .setField(MultipleFields.IM_USERS,conversationMessage)
                    .build();
            ENTITIES.add(entity);
        }

        return ENTITIES;
    }
}
