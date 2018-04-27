package com.linxz.main.ui.index;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.linxz.core.ui.recycle.DataConverter;
import com.linxz.core.ui.recycle.MultipleItemEntity;
import com.linxz.main.pojo.index.IndexEntity;
import java.util.ArrayList;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月09日17:18  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public final class IndexConverter extends DataConverter {


    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final JSONArray dataArray = JSON.parseArray(getJsonData());
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            String bean = data.toJSONString();
            IndexEntity indexEntity = new Gson().fromJson(bean, IndexEntity.class);

            int itemType = data.getInteger("itemType");
            switch (itemType) {
                case IndexItemType.TITLE:
                    break;
                case IndexItemType.BANNER:
                    break;
                case IndexItemType.ACTIVE_USERS:
                    break;
                case IndexItemType.NEWS_IT:
                    break;
                case IndexItemType.DISCOVER:
                    final String imgs = indexEntity.getDiscoverEntity().getImgs();
                    if (imgs == null || "".equals(imgs)) {
                        itemType = IndexItemType.DISCOVER_TEXT;
                    } else {
                        String[] array_imgs = imgs.split("`");
                        if (array_imgs.length == 1) {
                            itemType = IndexItemType.DISCOVER_ONE_IMAGE;
                        } else {
                            itemType = IndexItemType.DISCOVER_MORE_IMAGE;
                        }
                    }
                    break;
                default:
                    break;
            }

            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(itemType)
                    .setField(IndexItemFields.TITLE, indexEntity.getTitle())
                    .setField(IndexItemFields.BANNER, indexEntity.getBannerEntity())
                    .setField(IndexItemFields.USERS, indexEntity.getUsers())
                    .setField(IndexItemFields.NEWS, indexEntity.getNewsEntity())
                    .setField(IndexItemFields.DISCOVER, indexEntity.getDiscoverEntity())
                    .build();
            ENTITIES.add(entity);
        }
        return ENTITIES;
    }
}
