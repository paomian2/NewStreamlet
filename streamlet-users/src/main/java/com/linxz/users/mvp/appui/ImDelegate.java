package com.linxz.users.mvp.appui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.linxz.core.activitys.BaseActivity;
import com.linxz.core.mvp.BaseMvpFragment;
import com.linxz.core.ui.recycle.MultipleItemEntity;
import com.linxz.users.R;
import com.linxz.users.R2;
import com.linxz.users.mvp.presenter.ImPresenter;
import com.linxz.users.pojo.ConversationMessage;
import com.linxz.users.ui.im.ConversationListAdapter;
import com.linxz.users.ui.im.ConversationListConverter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月24日19:51  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class ImDelegate extends BaseMvpFragment<ImPresenter> implements ImView {

    @BindView(R2.id.rv_conversation_list)
    RecyclerView rvConversationList;
    private ConversationListConverter conversationListConverter;
    private ConversationListAdapter adapter;

    public static ImDelegate newInstance() {
        return new ImDelegate();
    }

    @Override
    protected ImPresenter onCreatePresenter() {
        return new ImPresenter(this);
    }

    @Override
    public Object setLayout() {
        return R.layout.user_index_delegate;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @Override
    public void initUI() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvConversationList.setLayoutManager(layoutManager);

        conversationListConverter = new ConversationListConverter();
        adapter = ConversationListAdapter.newInstance(new ArrayList<MultipleItemEntity>());
        rvConversationList.setAdapter(adapter);

        rvConversationList.addOnItemTouchListener(null);
    }

    @Override
    public void initData() {
    }

    @Override
    public BaseActivity getContext() {
        return _activity;
    }


    @Override
    public void getConversationListSuccess(List<ConversationMessage> conversationMessageList) {
        conversationListConverter.clearData();
        String dataJson = "";
        if (conversationMessageList != null || conversationMessageList.size() > 0) {
            dataJson = new Gson().toJson(conversationMessageList);
        }
        ArrayList<MultipleItemEntity> data = conversationListConverter.setJsonData(dataJson).convert();
        adapter.replaceData(data);
    }

    @Override
    public void getgetConversationListFailuer(String msg) {
        conversationListConverter.clearData();
        adapter.addData(conversationListConverter.setJsonData("").convert());
    }
}