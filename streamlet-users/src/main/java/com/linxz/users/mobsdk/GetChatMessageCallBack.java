package com.linxz.users.mobsdk;

import com.mob.imsdk.model.IMMessage;

import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月25日20:32  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public interface GetChatMessageCallBack {

    void onChatMessageSuccess(List<IMMessage> list);
    void onChatMessageError(int i, String s);
}
