package com.linxz.main.icon;

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
public enum MainEcIcons implements Icon{

    mainIcon_return('\ue679'),
    mainIcon_next('\ue699'),
    mainIcon_user('\ue7e4'),
    mainIcon_scan('\ue636');

    private char character;

    MainEcIcons(char character) {
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
