package com.linxz.main.ui.index;

import com.ToxicBakery.viewpager.transforms.DefaultTransformer;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.linxz.main.pojo.index.BannerEntity;
import com.linxz.main.ui.banners.BannerHolderCreator;

import java.util.ArrayList;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月18日23:18  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class IndexBannerCreator {

    public static void setDefault(ConvenientBanner<BannerEntity> convenientBanner,
                                  ArrayList<BannerEntity> banners,
                                  OnItemClickListener clickListener) {

        convenientBanner
                .setPages(new BannerHolderCreator(), banners)
                .setPageIndicator(new int[]{com.linxz.core.R.drawable.dot_normal, com.linxz.core.R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(clickListener)
                .setPageTransformer(new DefaultTransformer())
                .startTurning(3000)
                .setCanLoop(true);

    }
}
