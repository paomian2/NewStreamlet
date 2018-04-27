package com.linxz.users.mvp.presenter;

import com.linxz.core.mvp.BaseModel;
import com.linxz.core.mvp.BasePresenter;
import com.linxz.users.mobsdk.GetChatMessageCallBack;
import com.linxz.users.mobsdk.GetChatMsgCallBack;
import com.linxz.users.mobsdk.MobImSdkHander;
import com.linxz.users.mvp.appui.im.IMChatView;
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
public class IMChatPresenter extends BasePresenter<IMChatView,BaseModel> implements GetChatMsgCallBack,GetChatMessageCallBack {

    private  MobImSdkHander mMobImSdkHander;

    public IMChatPresenter(IMChatView mView) {
        super(mView);
        mMobImSdkHander=MobImSdkHander
                .builder()
                .withContext(mView.getContext())
                .build();
        mMobImSdkHander.setmGetChatMsgCallBack(this);
        mMobImSdkHander.setmGetChatMessageCallBack(this);
    }

    @Override
    public BaseModel onCreateModel() {
        return new BaseModel();
    }

    @Override
    public void onSuccess(Object obj) {

    }

    @Override
    public void onError(int i, String s) {

    }

    @Override
    public void onChatMessageSuccess(List<IMMessage> list) {
        mView.getChatMessageListSuccess(list);
    }

    @Override
    public void onChatMessageError(int i, String s) {

    }
}
