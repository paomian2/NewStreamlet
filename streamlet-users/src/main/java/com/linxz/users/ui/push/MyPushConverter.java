package com.linxz.users.ui.push;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linxz.core.ui.recycle.DataConverter;
import com.linxz.core.ui.recycle.MultipleItemEntity;
import com.linxz.users.pojo.push.DynamicEntity;
import com.linxz.users.pojo.push.PushItemType;
import com.linxz.users.pojo.push.PushMultipleFields;
import com.linxz.users.pojo.UserEntity;

import java.util.ArrayList;
import java.util.List;
/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月13日7:21  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class MyPushConverter extends DataConverter {

    @Override
    public ArrayList<MultipleItemEntity> convert() {

        final JSONObject jsonObject = JSON.parseObject(getJsonData());
        final JSONObject userObject = jsonObject.getJSONObject("userInfo");
        final JSONArray pushListArray = jsonObject.getJSONArray("pushList");

        if (userObject != null) {
            final UserEntity userEntity = new Gson().fromJson(userObject.toJSONString(), UserEntity.class);
            final MultipleItemEntity userItemEntity = MultipleItemEntity.builder()
                    .setItemType(PushItemType.HEANDER)
                    .setField(PushMultipleFields.HEADER, userEntity)
                    .build();
            ENTITIES.add(userItemEntity);

        }

        if (pushListArray != null && pushListArray.size()>0) {

            List<DynamicEntity> pushListEntities = new Gson().fromJson(pushListArray.toJSONString(),
                    new TypeToken<List<DynamicEntity>>() {
                    }.getType());

            if (pushListEntities != null) {
                for (DynamicEntity pushListEntity : pushListEntities) {
                    int type;
                    if (pushListEntity.getImgs() == null || pushListEntity.getImgs().isEmpty()) {
                        type = PushItemType.TEXT;
                    } else {
                        if (pushListEntity.getImgs().split("`").length > 1) {
                            type = PushItemType.MOREN_IMG;
                        } else {
                            type = PushItemType.ON_IMG;
                        }
                    }

                    final MultipleItemEntity userItemEntity = MultipleItemEntity.builder()
                            .setItemType(type)
                            .setField(PushMultipleFields.MORE_IMG, pushListEntity)
                            .build();

                    ENTITIES.add(userItemEntity);
                }
            }
        }
        return ENTITIES;
    }
}
