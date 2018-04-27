package com.linxz.users.ui.im;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linxz.core.ui.recycle.DataConverter;
import com.linxz.core.ui.recycle.ItemType;
import com.linxz.core.ui.recycle.MultipleFields;
import com.linxz.core.ui.recycle.MultipleItemEntity;
import com.mob.imsdk.model.IMMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月25日20:52  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class ChatMessageListConverter extends DataConverter{

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        String dataJson=getJsonData();
        List<IMMessage> messageList=new Gson().fromJson(dataJson,new TypeToken<List<IMMessage>>(){}.getType());
        for (IMMessage message:messageList){
            final MultipleItemEntity entity=MultipleItemEntity
                    .builder()
                    .setItemType(ItemType.ACTIVE_USERS)
                    .setField(MultipleFields.IM_USERS,message)
                    .build();
            ENTITIES.add(entity);
        }
        return ENTITIES;
    }
}
