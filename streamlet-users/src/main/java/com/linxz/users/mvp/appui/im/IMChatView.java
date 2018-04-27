package com.linxz.users.mvp.appui.im;

import com.linxz.core.mvp.BaseView;
import com.mob.imsdk.model.IMMessage;

import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月25日19:14  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public interface IMChatView extends BaseView{

    /**获取历史消息列表*/
    void getChatMessageListSuccess(List<IMMessage> list);
}
