package com.linxz.main.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月08日9:45  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class MainFontEcModule implements IconFontDescriptor {
    @Override
    public String ttfFileName() {
        return "main_iconfont.ttf";
    }

    @Override
    public Icon[] characters() {
        return new Icon[0];
    }
}
