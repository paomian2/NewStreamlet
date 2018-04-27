package com.linxz.users.mvp.appui.im;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import com.joanzapata.iconify.widget.IconTextView;
import com.linxz.core.activitys.BaseActivity;
import com.linxz.core.mvp.BaseMvpActivity;
import com.linxz.users.R;
import com.linxz.users.R2;
import com.linxz.users.mvp.presenter.IMChatPresenter;
import com.mob.imsdk.model.IMMessage;

import java.util.List;

import butterknife.BindView;
/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月25日19:02  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class IMChatActivity extends BaseMvpActivity<IMChatPresenter> implements IMChatView {

    @BindView(R2.id.tv_reback)
    IconTextView tvReback;
    @BindView(R2.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R2.id.rv_chats)
    RecyclerView rvChats;

    @Override
    protected IMChatPresenter createPresenter() {
        return new IMChatPresenter(this);
    }

    @Override
    public Object setLayout() {
        return R.layout.user_im_chat;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initEnvent() {

    }

    @Override
    public void initData() {

    }

    @Override
    public BaseActivity getContext() {
        return activity;
    }


    @Override
    public void getChatMessageListSuccess(List<IMMessage> list) {

    }
}
