package com.linxz.users.mvp.appui.im;

import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.linxz.core.activitys.BaseActivity;
import com.linxz.core.utils.log.LinxzLogger;
import com.linxz.users.R;
import com.linxz.users.R2;
import com.linxz.users.mobsdk.GetChatMsgCallBack;
import com.linxz.users.mobsdk.GetMessageCallBack;
import com.linxz.users.mobsdk.MobImSdkHander;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <p>
 * Function： IM测试
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月24日9:59  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
@Route(path = "/user/im")
public class IMTestActivity extends BaseActivity implements GetChatMsgCallBack,GetMessageCallBack{

    @BindView(R2.id.tvChatList)
    TextView tvChatList;

    @BindView(R2.id.tv_getmsg)
    TextView tv_getmsg;

    @OnClick(R2.id.btnSendMsg)
    public void sendMsg(){
        String toUserId="u10000";
        mMobImSdkHander.sendMessage(toUserId,"你好啊！！！");
    }

    private MobImSdkHander mMobImSdkHander;

    @Override
    public Object setLayout() {
        return R.layout.user_act_im_test;
    }

    @Override
    public void initUI() {
        mMobImSdkHander=MobImSdkHander
                .builder()
                .build();
        mMobImSdkHander.setmGetChatMsgCallBack(this);
        mMobImSdkHander.setmGetMessageCallBack(this);
    }

    @Override
    public void initEnvent() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onSuccess(Object onj) {
        String dataJson=new Gson().toJson(onj);
        LinxzLogger.json(dataJson);
        tvChatList.setText(dataJson);
    }

    @Override
    public void onError(int i, String s) {
        tvChatList.setText("错误！"+s);
    }

    @Override
    public void onMessageReceived(String json) {
        tv_getmsg.setText(json);
    }
}
