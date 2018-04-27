package com.linxz.users.mvp.appui;

import com.linxz.core.mvp.BaseView;
import com.linxz.users.pojo.ConversationMessage;

import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月24日22:56  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public interface ImView extends BaseView{

    /**获取会话列表信息*/
    void getConversationListSuccess(List<ConversationMessage> conversationMessageList);
    void getgetConversationListFailuer(String msg);
}
