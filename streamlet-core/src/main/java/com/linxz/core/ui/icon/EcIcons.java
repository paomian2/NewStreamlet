package com.linxz.core.ui.icon;

import com.joanzapata.iconify.Icon;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月07日18:34  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public enum EcIcons implements Icon {

    icon_msg('\ue745'),
    icon_study('\ue63b'),
    icon_offer('\ue6c0'),
    icon_add('\ue64d');

    private char character;

    EcIcons(char character) {
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
