package com.linxz.newstreamlet;
import com.alibaba.android.arouter.launcher.ARouter;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.linxz.androidstudy.icon.StudyFontEcModule;
import com.linxz.core.BuildConfig;
import com.linxz.core.app.Linxz;
import com.linxz.core.base.BaseApplication;
import com.linxz.core.http.interceptors.DebugInterceptor;
import com.linxz.core.ui.icon.FontEcModule;
import com.linxz.main.icon.MainFontEcModule;
import com.linxz.users.icon.UserFontEcModule;
import com.linxz.users.mobsdk.MobImSdkHander;

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
public class AppApplication extends BaseApplication {

    @Override
    public void onCreate() {
        if (BuildConfig.DEBUG) {
            //一定要在ARouter.init之前调用openDebug
            ARouter.openDebug();
            ARouter.openLog();
        }

        ARouter.init(this);

        MobImSdkHander.builder()
                .withContext(this)
                .build();
        //http://116.196.92.81:8080/
        //http://192.168.1.102:8080/
        Linxz.init(this)
                .withApiHost("http://116.196.92.81:8080/")
                .withInterceptor(new DebugInterceptor("index", 0))
                .withLoaderDelayed(1000)
                .withIcon(new FontEcModule())
                .withIcon(new FontAwesomeModule())
                .withIcon(new MainFontEcModule())
                .withIcon(new StudyFontEcModule())
                .withIcon(new UserFontEcModule())
                .withJavascriptInterface("latte")
                .configure();

        super.onCreate();
    }
}
