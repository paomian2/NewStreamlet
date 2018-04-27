package com.linxz.users.pojo;

import com.mob.imsdk.model.IMMessage;

/**
 * <p>
 * Function： 会话消息
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月24日22:41  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class ConversationMessage {

    private String id;
    private long createTime;
    private boolean disturb;
    private int type;
    private int unreadMsgCount;
    private IMMessage lastMessage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public boolean isDisturb() {
        return disturb;
    }

    public void setDisturb(boolean disturb) {
        this.disturb = disturb;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUnreadMsgCount() {
        return unreadMsgCount;
    }

    public void setUnreadMsgCount(int unreadMsgCount) {
        this.unreadMsgCount = unreadMsgCount;
    }

    public IMMessage getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(IMMessage lastMessage) {
        this.lastMessage = lastMessage;
    }
}
