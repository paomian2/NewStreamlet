package com.linxz.users.ui.push;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linxz.core.ui.recycle.DataConverter;
import com.linxz.core.ui.recycle.MultipleItemEntity;
import com.linxz.users.pojo.push.PushItemType;
import com.linxz.users.pojo.push.PushMultipleFields;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年04月05日3:16  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class PushListImageConverter extends DataConverter{

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        List<String> images = new Gson().fromJson(getJsonData(), new TypeToken<List<String>>() {
        }.getType());

        for (String img : images) {
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(PushItemType.ON_IMG)
                    .setField(PushMultipleFields.ONE_IMG, img)
                    .build();
            ENTITIES.add(entity);
        }

        return ENTITIES;
    }
}
