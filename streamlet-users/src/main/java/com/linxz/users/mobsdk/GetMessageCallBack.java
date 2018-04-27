package com.linxz.users.mobsdk;

/**
 * <p>
 * Function： 消息监听回调
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月24日10:15  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public interface GetMessageCallBack {
    void onMessageReceived(String json);
}
