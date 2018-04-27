package com.linxz.core.app;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;
import com.linxz.core.activitys.web.event.Event;
import com.linxz.core.activitys.web.event.EventManager;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * <p>
 * Function： App缓存工具
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月05日19:23  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public final class Configurator {

    private static final HashMap<Object,Object> LINXZ_CONFIGS=new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final Handler HANDLER=new Handler();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();


    private Configurator(){
        LINXZ_CONFIGS.put(ConfigKeys.CONFIG_READY,false);
        LINXZ_CONFIGS.put(ConfigKeys.HANDLER,HANDLER);
    }

    private static class Holder{
        private static final Configurator INSTANCE=new Configurator();
    }

    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) LINXZ_CONFIGS.get(ConfigKeys.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    public final void configure() {
        initIcons();
        Logger.addLogAdapter(new AndroidLogAdapter());
        LINXZ_CONFIGS.put(ConfigKeys.CONFIG_READY, true);
    }

    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 0; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public Configurator withJavascriptInterface(@NonNull String name) {
        LINXZ_CONFIGS.put(ConfigKeys.JAVASCRIPT_INTERFACE, name);
        return this;
    }

    public Configurator withWebEvent(@NonNull String name, @NonNull Event event) {
        final EventManager manager = EventManager.getInstance();
        manager.addEvent(name, event);
        return this;
    }


    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfiguration();
        final Object value = LINXZ_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) LINXZ_CONFIGS.get(key);
    }


    public final HashMap<Object,Object> getLinxzConfigs(){
        return LINXZ_CONFIGS;
    }

    /**配置网络地址*/
    public Configurator withApiHost(String host){
        LINXZ_CONFIGS.put(ConfigKeys.API_HOST,host);
        return this;
    }

    /**配置Activity*/
    public Configurator withActivity(Activity activity){
        LINXZ_CONFIGS.put(ConfigKeys.ACTIVITY,activity);
        return this;
    }

    /**网络加载框延时*/
    public final Configurator withLoaderDelayed(long delayed) {
        LINXZ_CONFIGS.put(ConfigKeys.LOADER_DELAYED, delayed);
        return this;
    }


    /**配置网络拦截器*/
    public Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        LINXZ_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    /**配置网络拦截器*/
    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LINXZ_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    /**微信AppId*/
    public final Configurator withWeChatAppId(String appId) {
        LINXZ_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID, appId);
        return this;
    }

    /**微信密钥*/
    public final Configurator withWeChatAppSecret(String appSecret) {
        LINXZ_CONFIGS.put(ConfigKeys.WE_CHAT_APP_SECRET, appSecret);
        return this;
    }

    /**文字图标*/
    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }




}
