package com.linxz.users.icon;

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
public class UserFontEcModule implements IconFontDescriptor {
    @Override
    public String ttfFileName() {
        return "user_icon.ttf";
    }

    @Override
    public Icon[] characters() {
        return  UserEcIcons.values();
    }
}
