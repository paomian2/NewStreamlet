package com.linxz.users.ui.push;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
 * V1.0   2018年04月03日21:33  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class PushMessageConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        String dataJson = getJsonData();
        if (dataJson != null && !dataJson.isEmpty()) {
            List<String> imageList = new Gson().fromJson(dataJson, new TypeToken<List<String>>() {
            }.getType());
            for (String str : imageList) {
                final MultipleItemEntity entity = MultipleItemEntity
                        .builder()
                        .setItemType(ItemType.DISCOVER_MORE_IMAGE)
                        .setField(MultipleFields.SPAN_SIZE,1)
                        .setField(MultipleFields.IMAGE_URL, str)
                        .build();
                ENTITIES.add(entity);
            }
        }
        return ENTITIES;
    }
}
