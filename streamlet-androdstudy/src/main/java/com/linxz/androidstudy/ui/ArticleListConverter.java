package com.linxz.androidstudy.ui;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linxz.androidstudy.pojo.ArticleEntity;
import com.linxz.core.ui.recycle.DataConverter;
import com.linxz.core.ui.recycle.ItemType;
import com.linxz.core.ui.recycle.MultipleFields;
import com.linxz.core.ui.recycle.MultipleItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月11日14:01  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class ArticleListConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {

        List<ArticleEntity> articleEntities = new Gson().fromJson(getJsonData(), new TypeToken<List<ArticleEntity>>() {
        }.getType());

        for (ArticleEntity articleEntity : articleEntities) {
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(ItemType.DISCOVER_TEXT)
                    .setField(MultipleFields.TITLE, articleEntity)
                    .build();
            ENTITIES.add(entity);
        }
        return ENTITIES;
    }
}
