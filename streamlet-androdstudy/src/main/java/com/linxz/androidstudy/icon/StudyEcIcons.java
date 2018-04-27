package com.linxz.androidstudy.icon;

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
public enum  StudyEcIcons implements Icon{

    studyIcon_read('\ue61c'),
    studyIcon_knowd('\ue659'),
    studyIcon_discuss('\ue678'),
    studyIcon_share('\ue778'),
    studyIcon_colleage('\ue65f');

    private char character;

    StudyEcIcons(char character) {
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
