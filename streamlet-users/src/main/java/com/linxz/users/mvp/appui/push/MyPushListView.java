package com.linxz.users.mvp.appui.push;

import com.linxz.core.mvp.BaseView;
import com.linxz.users.pojo.push.DynamicEntity;

import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年04月03日19:38  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public interface MyPushListView extends BaseView{
    /**获取动态列表*/
    void getDynamicList();
    void getDynamicListResult(boolean success, int code, String msg, List<DynamicEntity> list);
}
