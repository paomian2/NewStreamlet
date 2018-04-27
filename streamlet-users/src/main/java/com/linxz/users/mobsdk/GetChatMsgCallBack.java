package com.linxz.users.mobsdk;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月24日10:06  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public interface GetChatMsgCallBack {
    void onSuccess(Object obj);
    void onError(int i, String s);
}
