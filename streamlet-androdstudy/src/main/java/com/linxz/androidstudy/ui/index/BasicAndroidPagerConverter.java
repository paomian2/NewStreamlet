package com.linxz.androidstudy.ui.index;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.linxz.androidstudy.pojo.Cate;
import com.linxz.core.ui.recycle.DataConverter;
import com.linxz.core.ui.recycle.ItemType;
import com.linxz.core.ui.recycle.MultipleItemEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月11日0:54  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class BasicAndroidPagerConverter extends DataConverter{

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        String dataJson=getJsonData();
        if (dataJson==null || getJsonData().isEmpty()){
            //暂无数据
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setItemType(ItemType.EMPTY_DATA)
                    .build();
            ENTITIES.add(entity);
        }else{
            List<Cate> categaryList=new Gson().fromJson(getJsonData(),new TypeToken<List<Cate>>(){}.getType());
            for (Cate articleCategary:categaryList){

                final MultipleItemEntity entity = MultipleItemEntity.builder()
                        .setItemType(StudyItemType.STUDY_ITEM)
                        .setField(StudyItemFields.STUDY,articleCategary)
                        .build();
                ENTITIES.add(entity);
            }
        }
        return ENTITIES;
    }
}
