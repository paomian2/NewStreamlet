package com.linxz.users.mvp.appui.push;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.joanzapata.iconify.widget.IconTextView;
import com.linxz.core.activitys.AppActivityManager;
import com.linxz.core.activitys.BaseActivity;
import com.linxz.core.mvp.BaseMvpActivity;
import com.linxz.core.ui.loader.LatteLoader;
import com.linxz.core.ui.recycle.BaseDecoration;
import com.linxz.core.ui.recycle.MultipleItemEntity;
import com.linxz.core.utils.dialogs.DialogHelper;
import com.linxz.core.utils.dialogs.OnPositiveClickListener;
import com.linxz.users.R;
import com.linxz.users.R2;
import com.linxz.users.app.ActivityPathManager;
import com.linxz.users.mvp.presenter.MyPushListPresenter;
import com.linxz.users.pojo.push.DynamicEntity;
import com.linxz.users.ui.push.MyPushAdapter;
import com.linxz.users.ui.push.MyPushConverter;
import com.linxz.users.utils.UserSharePrefreshHanlder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年04月03日19:37  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class MyPushListActivity extends BaseMvpActivity<MyPushListPresenter> implements MyPushListView
        , SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R2.id.tv_reback)
    IconTextView tvReback;
    @BindView(R2.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R2.id.tv_right)
    AppCompatTextView tvRight;
    @BindView(R2.id.rv_pushlist)
    RecyclerView rvPushlist;
    @BindView(R2.id.layout_refresh)
    SwipeRefreshLayout layoutRefresh;

    private MyPushConverter mMyPushConverter;
    private MyPushAdapter mMyPushAdapter;
    private int page = 0;
    private int size = 10;
    private boolean refresh;


    @Override
    protected MyPushListPresenter createPresenter() {
        return new MyPushListPresenter(this);
    }

    @Override
    public Object setLayout() {
        return R.layout.user_act_user_pushlist;
    }

    @Override
    public void initUI() {
        tvTitle.setText("我的发布");
        tvRight.setText("新建");
        tvRight.setBackgroundColor(ContextCompat.getColor(activity, R.color.title_color));
        tvRight.setVisibility(View.VISIBLE);
        initRefreshLayout();

        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvPushlist.setLayoutManager(layoutManager);
        rvPushlist.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(activity, R.color.page_bg), 60));

        mMyPushConverter = new MyPushConverter();
        mMyPushAdapter = new MyPushAdapter(new ArrayList<MultipleItemEntity>());
        rvPushlist.setAdapter(mMyPushAdapter);
        mMyPushAdapter.setOnLoadMoreListener(this, rvPushlist);
    }

    @Override
    public void initEnvent() {

    }

    @Override
    public void initData() {
        LatteLoader.showLoading(activity);
        getDynamicList();
    }

    @Override
    public BaseActivity getContext() {
        return activity;
    }

    private void initRefreshLayout() {
        layoutRefresh.setOnRefreshListener(this);
        layoutRefresh.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        layoutRefresh.setProgressViewOffset(true, 80, 300);
    }




    @Override
    public void getDynamicList() {
        Map<String, Object> params = new HashMap<>(1);
        params.put("token", UserSharePrefreshHanlder.getToken());
        params.put("page", page);
        params.put("size", size);
        mvpPresenter.getDynamicList(params);
    }

    @Override
    public void getDynamicListResult(boolean success, int code, String msg, List<DynamicEntity> list) {
        LatteLoader.stopLoading();
        layoutRefresh.setRefreshing(false);
        mMyPushAdapter.loadMoreComplete();
        if (success) {
            if (mMyPushAdapter.getData().size()==0 && list.size()==0){
                mMyPushAdapter.setEmptyView(R.layout.core_item_empty_data);
                return;
            }
            if (list.size()==0){
                mMyPushAdapter.loadMoreEnd();
                return;
            }
            if (list.size() <= size) {
                mMyPushAdapter.loadMoreEnd();
            }
            page++;
            Map<String, Object> datas = new HashMap<>(16);
            datas.put("pushList", list);
            String dataJson = new Gson().toJson(datas);
            mMyPushConverter.clearData();
            ArrayList<MultipleItemEntity> data = mMyPushConverter.setJsonData(dataJson).convert();
            if (refresh) {
                mMyPushAdapter.setNewData(data);
            } else {
                mMyPushAdapter.addData(data);
            }
            refresh = false;
        } else if (code == 202) {
            DialogHelper
                    .builder()
                    .withContext(activity)
                    .withPositiveClick(new OnPositiveClickListener() {
                        @Override
                        public void onPositiveClick(Object object) {
                            ARouter.getInstance()
                                    .build(ActivityPathManager.SIGN_IN)
                                    .navigation();
                        }
                    }).build()
                    .showAuthDialog();

        } else {
            showToast(msg);
        }
    }

    @Override
    public void onRefresh() {
        page = 0;
        refresh = true;
        layoutRefresh.setRefreshing(true);
        mMyPushAdapter.setEnableLoadMore(true);
        getDynamicList();
    }

    @Override
    public void onLoadMoreRequested() {
        getDynamicList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==PushActivity.REQUEST_CODE && resultCode==RESULT_OK){
            onRefresh();
        }
    }


    @OnClick(R2.id.tv_reback)
    public void reback() {
        finish();
    }

    @OnClick(R2.id.tv_right)
    public void rightClick() {
        AppActivityManager.getInstance().goTo(activity, PushActivity.class);
    }

}
