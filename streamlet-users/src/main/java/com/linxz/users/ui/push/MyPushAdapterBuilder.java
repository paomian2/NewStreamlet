package com.linxz.users.ui.push;

import com.linxz.core.ui.recycle.MultipleItemEntity;
import com.linxz.users.ui.push.callback.ISettingCallBack;

import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月16日20:06  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public final class MyPushAdapterBuilder {

    private ISettingCallBack mISettingCallBack;

    MyPushAdapterBuilder() {
    }

    public MyPushAdapterBuilder withSettingCallBack(ISettingCallBack settingCallBack) {
        this.mISettingCallBack = settingCallBack;
        return this;
    }

    public final MyPushAdapter build(List<MultipleItemEntity> data) {
        return new MyPushAdapter(data);
    }

}
