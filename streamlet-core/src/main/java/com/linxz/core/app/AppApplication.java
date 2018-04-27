package com.linxz.core.app;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.linxz.core.BuildConfig;
import com.linxz.core.ui.icon.FontEcModule;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月04日20:05  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            //一定要在ARouter.init之前调用openDebug
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
        Linxz.init(this)
                .withApiHost("")
                .withLoaderDelayed(1000)
                .withIcon(new FontEcModule())
                .withIcon(new FontAwesomeModule())
                .withJavascriptInterface("latte")
                .configure();

    }
}
