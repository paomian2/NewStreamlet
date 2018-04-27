package com.linxz.core.base;

import android.support.annotation.Keep;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月24日12:36  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
@Keep
public interface IApplicationDelegate {
    void onCreate();

    void onTerminate();

    void onLowMemory();

    void onTrimMemory(int level);
}
