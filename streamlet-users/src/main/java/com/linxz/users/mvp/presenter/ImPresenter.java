package com.linxz.users.mvp.presenter;

import com.linxz.core.mvp.BasePresenter;
import com.linxz.users.mobsdk.GetChatMsgCallBack;
import com.linxz.users.mobsdk.GetMessageCallBack;
import com.linxz.users.mobsdk.MobImSdkHander;
import com.linxz.users.mvp.appui.ImView;
import com.linxz.users.mvp.module.ImModule;
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
public class ImPresenter extends BasePresenter<ImView, ImModule> implements GetChatMsgCallBack, GetMessageCallBack {

    private MobImSdkHander mobImSdkHander;

    public ImPresenter(ImView mView) {
        super(mView);
        mobImSdkHander = MobImSdkHander
                .builder()
                .withContext(mView.getContext())
                .build();
        mobImSdkHander.setmGetChatMsgCallBack(this);
        mobImSdkHander.setmGetMessageCallBack(this);

        String targetId="u10000";
        int type=2;
        int pageSize=1000;
        int page=0;
        mobImSdkHander.getMessageList(targetId,type,pageSize,page);
    }

    @Override
    public ImModule onCreateModel() {
        return new ImModule();
    }

    /**
     * 获取会话列表信息
     */
    public void getConversationList() {

    }

    @Override
    public void onSuccess(Object obj) {
        if (mView != null) {
            /*List<ConversationMessage> conversationMessageList = new Gson().fromJson(obj + "", new TypeToken<List<ConversationMessage>>() {
            }.getType());*/
            mView.getConversationListSuccess((List<ConversationMessage>) obj);
        }
    }

    @Override
    public void onError(int i, String s) {

    }

    @Override
    public void onMessageReceived(String json) {
        mobImSdkHander.getChatConverstaionMsg();
    }
}
