package com.linxz.main.ui.index;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linxz.core.ui.recycle.DataConverter;
import com.linxz.core.ui.recycle.MultipleItemEntity;
import com.linxz.main.pojo.index.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月10日0:35  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class IndexUserConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        List<UserEntity> userEntityList = new Gson().fromJson(getJsonData(), new TypeToken<List<UserEntity>>() {
        }.getType());
        for (UserEntity userEntity : userEntityList) {
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(IndexItemType.ACTIVE_USERS)
                    .setField(IndexItemFields.USERS, userEntity)
                    .build();
            ENTITIES.add(entity);
        }
        return ENTITIES;
    }
}
