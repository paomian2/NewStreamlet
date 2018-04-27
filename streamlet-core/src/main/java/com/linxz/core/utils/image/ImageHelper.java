package com.linxz.core.utils.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.linxz.core.R;
import com.linxz.core.app.ConfigKeys;
import com.linxz.core.app.Linxz;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月10日0:55  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class ImageHelper {

    /**设置图片加载策略*/
    private static final RequestOptions RECYCLER_OPTIONS =
            new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.img_def)
                    .error(R.drawable.img_error)
                    .dontAnimate();
    /**
     * 不带默认图
     */
    public static void imageNet(Context mContext, String imageUrl, ImageView view) {
        if (imageUrl!=null && !imageUrl.contains("http")){
            imageUrl= Linxz.getConfiguration(ConfigKeys.API_HOST)+imageUrl;
        }
        Glide.with(mContext)
                .load(imageUrl)
                .apply(RECYCLER_OPTIONS)
                .into(view);
    }

    public static void imageLocal(Context mContext, String filePath, ImageView view) {
        Glide.with(mContext).load(filePath).into(view);
    }
}
