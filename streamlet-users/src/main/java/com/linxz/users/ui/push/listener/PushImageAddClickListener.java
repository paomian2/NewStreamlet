package com.linxz.users.ui.push.listener;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.linxz.core.ui.recycle.MultipleFields;
import com.linxz.core.ui.recycle.MultipleItemEntity;
import com.linxz.users.ui.push.callback.PushImageAddClickCallBack;
import com.linxz.users.ui.push.callback.PushImageClickCallBack;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年04月04日16:14  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class PushImageAddClickListener extends SimpleClickListener {

    private PushImageAddClickCallBack mPushImageAddClickCallBack;
    private PushImageClickCallBack mPushImageClickCallBack;

    private PushImageAddClickListener(PushImageAddClickCallBack mPushImageAddClickCallBack,PushImageClickCallBack mPushImageClickCallBack){
        this.mPushImageAddClickCallBack=mPushImageAddClickCallBack;
        this.mPushImageClickCallBack=mPushImageClickCallBack;
    }

    public static PushImageAddBuilder builder(){
        return new PushImageAddBuilder();
    }

    public static class PushImageAddBuilder{
        private PushImageAddClickCallBack mPushImageAddClickCallBack;
        private PushImageClickCallBack mPushImageClickCallBack;

        public PushImageAddBuilder withPushImageAddClickCallBack(PushImageAddClickCallBack mPushImageAddClickCallBack){
            this.mPushImageAddClickCallBack=mPushImageAddClickCallBack;
            return this;
        }

        public PushImageAddBuilder withPushImageClickCallBack(PushImageClickCallBack mPushImageClickCallBack){
            this.mPushImageClickCallBack=mPushImageClickCallBack;
            return this;
        }

        public PushImageAddClickListener build(){
            return new PushImageAddClickListener(mPushImageAddClickCallBack,mPushImageClickCallBack);
        }

    }



    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        final String imagePath=entity.getField(MultipleFields.IMAGE_URL);
        if (imagePath==null || imagePath.isEmpty()){
            //添加图片
            if (mPushImageAddClickCallBack!=null){
                mPushImageAddClickCallBack.onImageAdd();
            }
        }
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
        final MultipleItemEntity entity = (MultipleItemEntity) baseQuickAdapter.getData().get(position);
        final String imagePath=entity.getField(MultipleFields.IMAGE_URL);
        if (imagePath!=null || !imagePath.isEmpty()){
            //删除图片
            if (mPushImageAddClickCallBack!=null){
                mPushImageClickCallBack.onImageClick(imagePath);
            }
        }
    }
}
