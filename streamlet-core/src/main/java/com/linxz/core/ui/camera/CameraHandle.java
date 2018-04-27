package com.linxz.core.ui.camera;

import android.app.Activity;
import android.content.Intent;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年04月03日15:09  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class CameraHandle {


    private Activity activity;
    private int requestCode;
    private int maxSelectNum;
    private boolean enableCrop;

    private CameraHandle(Activity activity,int requestCode,int maxSelectNum,boolean enableCrop){
        this.activity=activity;
        this.requestCode=requestCode;
        this.maxSelectNum=maxSelectNum;
        this.enableCrop=enableCrop;
    }

    public static CameraBuilder builder(){
        return new CameraBuilder();
    }

    public static class CameraBuilder{
        private Activity activity;
        private int requestCode=PictureConfig.CHOOSE_REQUEST;
        private int maxSelectNum=1;
        private boolean enableCrop;

        public CameraBuilder withActivity(Activity activity){
            this.activity=activity;
            return this;
        }

        public CameraBuilder withRequestCode(int requestCode){
            this.requestCode=requestCode;
            return this;
        }

        public CameraBuilder withMaxSelectNum(int maxSelectNum){
            this.maxSelectNum=maxSelectNum;
            return this;
        }

        public CameraBuilder enableCrop(boolean enableCrop){
            this.enableCrop=enableCrop;
            return this;
        }


        public CameraHandle build(){
            return new CameraHandle(activity,requestCode,maxSelectNum,enableCrop);
        }
    }

    public void pictureSelector(){
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())
                .compress(true)
                .enableCrop(enableCrop)
                .maxSelectNum(maxSelectNum)
                .forResult(requestCode);
    }


    /**结果处理*/
    public static List<String> pickerResult(Intent data){
        List<String> picPaths=new ArrayList<>();
        List<LocalMedia> selectList=PictureSelector.obtainMultipleResult(data);
        for (LocalMedia media : selectList) {
            picPaths.add(media.getPath());
        }
        return picPaths;
    }


}
