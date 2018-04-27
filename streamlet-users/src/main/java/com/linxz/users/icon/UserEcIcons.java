package com.linxz.users.icon;

import com.joanzapata.iconify.Icon;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月08日9:51  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public enum UserEcIcons implements Icon{

    user_studycoin('\ue614'),
    user_collection('\ue64d'),
    user_read('\ue68f'),
    user_push('\ue763'),
    user_studywary('\ue659'),
    user_bodybuild('\ue6b1'),
    user_fans('\ue6d9'),
    user_up('\ue644'),
    user_down('\ue604'),
    user_pushread('\ue671');

    private char character;

    UserEcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
