package com.linxz.users.mobsdk;

import android.content.Context;

import com.google.gson.Gson;
import com.linxz.core.utils.log.LinxzLogger;
import com.mob.MobSDK;
import com.mob.imsdk.MobIM;
import com.mob.imsdk.MobIMCallback;
import com.mob.imsdk.MobIMMessageReceiver;
import com.mob.imsdk.model.IMMessage;
import com.mob.imsdk.model.IMUser;

import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月24日9:45  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class MobImSdkHander {

    private Context mContext;
    /**获取消息列表结果回调*/
    private GetChatMsgCallBack mGetChatMsgCallBack;
    /**接收消息回调*/
    private GetMessageCallBack mGetMessageCallBack;
    /**历史消息列表*/
    private GetChatMessageCallBack mGetChatMessageCallBack;

    private MobImSdkHander(Context context){
        this.mContext=context;
        initMob();
        signInConfig();
    }

    public static MobImSdkHander.MobImSdkBuilder builder(){
        return new MobImSdkHander.MobImSdkBuilder();
    }

    public static class  MobImSdkBuilder{
        private Context mContext;

        public MobImSdkBuilder withContext(Context context){
            this.mContext=context;
            return this;
        }

        public MobImSdkHander build(){
            return new MobImSdkHander(mContext);
        }
    }

    /**初始化SDK*/
    private void initMob(){
        MobSDK.init(mContext);
    }

    public void signInConfig(){
        String userId="u10001";
        String userNname="、璀";
        String portail="https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1521446174&di=3bdbf94cbbcd7fcacc7fd8d0989a4246&src=http://news.cnhubei.com/xw/yl/201605/W020160516305305050803.png";
        MobSDK.setUser(userId, userNname,portail, null);
    }

    /**获取会话列表结果回调*/
    public void setmGetChatMsgCallBack(GetChatMsgCallBack getChatMsgCallBack){
        this.mGetChatMsgCallBack=getChatMsgCallBack;
        getChatConverstaionMsg();
    }

    /**获取会话列表结果回调*/
    public void getChatConverstaionMsg(){
        MobIM.getChatManager().getAllLocalConversations(new MobIMCallback(){
            @Override
            public void onSuccess(Object o) {
                if (mGetChatMsgCallBack!=null){
                    mGetChatMsgCallBack.onSuccess(o);
                }
            }

            @Override
            public void onError(int i, String s) {
                if (mGetChatMsgCallBack!=null){
                    mGetChatMsgCallBack.onError(i,s);
                }
            }
        });
    }

    public void setmGetChatMessageCallBack(GetChatMessageCallBack mGetChatMessageCallBack){
        this.mGetChatMessageCallBack=mGetChatMessageCallBack;

    }
    /**获取会话消息记录*/
    public void getMessageList(String targetId,int conversationType,int pageSize,int page){
        MobIM.getChatManager().getMessageList(targetId,conversationType,pageSize,page,new MobIMCallback<List<IMMessage>>(){
            @Override
            public void onSuccess(List<IMMessage> imMessages) {
                if (mGetChatMessageCallBack!=null){
                    mGetChatMessageCallBack.onChatMessageSuccess(imMessages);
                }
            }

            @Override
            public void onError(int i, String s) {
                if (mGetChatMessageCallBack!=null){
                    mGetChatMessageCallBack.onChatMessageError(i,s);
                }
            }
        });

    }

    /**发送消息*/
    public void sendMessage(String toUserId,String msg){

        String userId="u10001";
        String userNname="、璀";
        String portail="https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1521446174&di=3bdbf94cbbcd7fcacc7fd8d0989a4246&src=http://news.cnhubei.com/xw/yl/201605/W020160516305305050803.png";

        IMUser imUser=new IMUser();
        imUser.setId(userId);
        imUser.setNickname(userNname);
        imUser.setAvatar(portail);


        IMMessage imMessage=new IMMessage();
        imMessage.setBody(msg);
        imMessage.setId(userId);
        imMessage.setTo(toUserId);
        imMessage.setCreateTime(System.currentTimeMillis());
        imMessage.setFromUserInfo(imUser);
        imMessage.setType(IMMessage.TYPE_USER);
        MobIM.getChatManager().sendMessage(imMessage, new MobIMCallback<Void>() {
            @Override
            public void onSuccess(Void result)  {
            }
            @Override
            public void onError(int code, String message)  {
            }
        });
    }

    /**设置消息接收监听回调*/
    public void setmGetMessageCallBack(GetMessageCallBack getMessageCallBack){
        this.mGetMessageCallBack=getMessageCallBack;
        MobIM.addMessageReceiver(new MobIMMessageReceiver(){
            @Override
            public void onMessageReceived(List<IMMessage> list) {
                if (list==null){
                    LinxzLogger.d("onMessageReceived--->>>>getMessage: List<IMMessage> IS NULL");
                    return;
                }
                String dataJson=new Gson().toJson(list);
                if (mGetMessageCallBack!=null){
                    mGetMessageCallBack.onMessageReceived(dataJson);
                }
            }
        });
    }

}
