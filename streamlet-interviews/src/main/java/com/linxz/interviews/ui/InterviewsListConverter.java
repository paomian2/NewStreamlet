package com.linxz.interviews.ui;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linxz.core.ui.recycle.DataConverter;
import com.linxz.core.ui.recycle.ItemType;
import com.linxz.core.ui.recycle.MultipleFields;
import com.linxz.core.ui.recycle.MultipleItemEntity;
import com.linxz.interviews.pojo.InterviewsEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月12日2:28  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class InterviewsListConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final String dataJson = getJsonData();
        if (dataJson == null || dataJson.isEmpty()) {
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(ItemType.EMPTY_DATA)
                    .build();
            ENTITIES.add(entity);
        } else {
            List<InterviewsEntity> list = new Gson().fromJson(getJsonData(), new TypeToken<List<InterviewsEntity>>() {
            }.getType());
            for (InterviewsEntity interviewEntity : list) {

                final MultipleItemEntity entity = MultipleItemEntity.builder()
                        .setItemType(ItemType.DISCOVER_TEXT)
                        .setField(MultipleFields.TEXT, interviewEntity)
                        .build();
                ENTITIES.add(entity);
            }
        }
        return ENTITIES;
    }
}
