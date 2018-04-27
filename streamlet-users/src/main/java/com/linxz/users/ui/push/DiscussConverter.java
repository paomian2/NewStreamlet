package com.linxz.users.ui.push;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linxz.core.ui.recycle.DataConverter;
import com.linxz.core.ui.recycle.ItemType;
import com.linxz.core.ui.recycle.MultipleFields;
import com.linxz.core.ui.recycle.MultipleItemEntity;
import com.linxz.users.pojo.push.DiscussEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月14日2:15  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class DiscussConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final List<DiscussEntity> discussEntities = new Gson().fromJson(getJsonData(), new TypeToken<List<DiscussEntity>>() {
        }.getType());

        for (DiscussEntity discussEntity:discussEntities){
            final MultipleItemEntity entity=MultipleItemEntity.builder()
                    .setItemType(ItemType.DISCOVER_TEXT)
                    .setField(MultipleFields.TEXT,discussEntity)
                    .build();

            ENTITIES.add(entity);
        }
        return ENTITIES;
    }
}
