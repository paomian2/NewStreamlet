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
 * V1.0   2018年03月22日22:57  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class AndroidLibConverter extends DataConverter{

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        String dataJson=getJsonData();
        if (dataJson==null || dataJson.isEmpty()){
            final MultipleItemEntity entity=MultipleItemEntity.builder()
                    .setItemType(ItemType.EMPTY_DATA)
                    .build();
            ENTITIES.add(entity);
        }
        final List<ArticleEntity> entityList=new Gson().fromJson(dataJson,new TypeToken<List<ArticleEntity>>(){}.getType());
        for (ArticleEntity entity:entityList){
            final MultipleItemEntity itemEntity=MultipleItemEntity.builder()
                    .setItemType(ItemType.DISCOVER_TEXT)
                    .setField(MultipleFields.TEXT,entity)
                    .build();
            ENTITIES.add(itemEntity);
        }
        return ENTITIES;
    }
}
