package com.linxz.main.ui.banners;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月19日6:22  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class BannerHolderCreator implements CBViewHolderCreator<BannerImageHolder> {
    @Override
    public BannerImageHolder createHolder() {
        return new BannerImageHolder();
    }
}
