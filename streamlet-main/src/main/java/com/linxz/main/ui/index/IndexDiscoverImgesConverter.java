package com.linxz.main.ui.index;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linxz.core.ui.recycle.DataConverter;
import com.linxz.core.ui.recycle.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月10日18:58  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class IndexDiscoverImgesConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        List<String> images = new Gson().fromJson(getJsonData(), new TypeToken<List<String>>() {
        }.getType());

        for (String img : images) {
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(IndexItemType.DISCOVER_MORE_IMAGE)
                    .setField(IndexItemFields.DISCOVER, img)
                    .build();
            ENTITIES.add(entity);
        }

        return ENTITIES;
    }
}
