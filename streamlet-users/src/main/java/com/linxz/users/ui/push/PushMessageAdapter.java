package com.linxz.users.ui.push;

import android.support.v7.widget.AppCompatImageView;

import com.linxz.core.ui.recycle.ItemType;
import com.linxz.core.ui.recycle.MultipleFields;
import com.linxz.core.ui.recycle.MultipleItemEntity;
import com.linxz.core.ui.recycle.MultipleRecyclerAdapter;
import com.linxz.core.ui.recycle.MultipleViewHolder;
import com.linxz.core.utils.image.ImageHelper;
import com.linxz.users.R;

import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年04月03日22:04  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class PushMessageAdapter extends MultipleRecyclerAdapter{

    public static PushMessageAdapter build(List<MultipleItemEntity> data){
        return new PushMessageAdapter(data);
    }

    protected PushMessageAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ItemType.DISCOVER_MORE_IMAGE, R.layout.user_item_push_img);
    }

    @Override
    protected void convert(MultipleViewHolder holder, MultipleItemEntity entity) {
        switch (holder.getItemViewType()){
            case ItemType.DISCOVER_MORE_IMAGE:
                final AppCompatImageView iv=holder.getView(R.id.img);
                String iamgePath=entity.getField(MultipleFields.IMAGE_URL);
                if (iamgePath==null || iamgePath.isEmpty()){
                    iv.setImageResource(R.drawable.user_img_add);
                }else{
                    ImageHelper.imageLocal(mContext,iamgePath,iv);
                }
                break;
            default:
                break;
        }
    }
}
