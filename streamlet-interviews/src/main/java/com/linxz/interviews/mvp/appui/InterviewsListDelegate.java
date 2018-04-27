package com.linxz.interviews.mvp.appui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.linxz.core.activitys.BaseActivity;
import com.linxz.core.app.Linxz;
import com.linxz.core.mvp.BaseMvpFragment;
import com.linxz.core.ui.recycle.BaseDecoration;
import com.linxz.core.ui.recycle.MultipleItemEntity;
import com.linxz.core.utils.StringUtils;
import com.linxz.core.utils.log.LinxzLogger;
import com.linxz.interviews.R;
import com.linxz.interviews.R2;
import com.linxz.interviews.mvp.presenter.InterviewsListPresenter;
import com.linxz.interviews.pojo.InterviewsEntity;
import com.linxz.interviews.ui.InterviewsListAdapter;
import com.linxz.interviews.ui.InterviewsListConverter;
import com.linxz.interviews.ui.callback.InterviewsItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import butterknife.BindView;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月12日2:15  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class InterviewsListDelegate extends BaseMvpFragment<InterviewsListPresenter> implements InterviewsListView,
        SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener{

    @BindView(R2.id.layout_refresh)
    SwipeRefreshLayout layoutRefresh;
    @BindView(R2.id.recycle_view)
    RecyclerView recycleView;
    private InterviewsListConverter mInterviewsListConverter;
    private InterviewsListAdapter mInterviewsListAdapter;

    private String categoryId;
    private int page = 0;
    private int size = 10;
    private boolean refresh;

    @Override
    protected InterviewsListPresenter onCreatePresenter() {
        return new InterviewsListPresenter(this);
    }

    @Override
    public void onGetBundle(Bundle bundle) {
        super.onGetBundle(bundle);
        categoryId=bundle.getString("categoryId");
    }

    @Override
    public Object setLayout() {
        return R.layout.intervies_delegate_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @Override
    public void initUI() {
        //initRefreshLayout();
        layoutRefresh.setEnabled(false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(layoutManager);
        recycleView.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(activity,R.color.page_bg),60));

        mInterviewsListConverter=new InterviewsListConverter();
        mInterviewsListAdapter=new InterviewsListAdapter(new ArrayList<MultipleItemEntity>());
        recycleView.setAdapter(mInterviewsListAdapter);
        recycleView.addOnItemTouchListener(InterviewsItemClickListener.create());

        mInterviewsListAdapter.setOnLoadMoreListener(this, recycleView);
        mInterviewsListAdapter.setEnableLoadMore(true);
    }

    @Override
    public void initData() {
        getInterviews();
    }

    @Override
    public BaseActivity getContext() {
        return null;
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

    private void refresh() {
        page = 0;
        refresh = true;
        layoutRefresh.setRefreshing(true);
        mInterviewsListAdapter.setEnableLoadMore(true);
        Linxz.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                layoutRefresh.setRefreshing(false);
                getInterviews();
            }
        }, 1000);
    }



    @Override
    public void getInterviews() {
        if (mInterviewsListAdapter.isLoadMoreEnable()){
            Map<String,Object> params=new HashMap<>(16);
            params.put("categoryId",categoryId);
            params.put("page",page);
            params.put("size",15);
            mvpPresenter.getInterviews(params);
        }
    }

    @Override
    public void getInterviewsSuccess(List<InterviewsEntity> interviewEntityList) {
        layoutRefresh.setRefreshing(false);
        mInterviewsListAdapter.loadMoreComplete();

        mInterviewsListConverter.clearData();
        if (interviewEntityList==null){
            mInterviewsListAdapter.loadMoreEnd(true);
            mInterviewsListAdapter.setEnableLoadMore(false);
            mInterviewsListAdapter.addData(mInterviewsListConverter.setJsonData("").convert());
        }else if (interviewEntityList.size()==0){
            if (StringUtils.isEmpty(mInterviewsListConverter.getJsonData())){
                mInterviewsListAdapter.loadMoreEnd(true);
                mInterviewsListAdapter.addData(mInterviewsListConverter.setJsonData("").convert());
            }else{
                mInterviewsListAdapter.loadMoreEnd();
            }
            mInterviewsListAdapter.setEnableLoadMore(false);
        } else if (interviewEntityList.size()>0){
            if (interviewEntityList.size() <= size) {
                mInterviewsListAdapter.loadMoreEnd();
                mInterviewsListAdapter.setEnableLoadMore(false);
            }
            page++;
            final String dataJson=new Gson().toJson(interviewEntityList);
            LinxzLogger.json(dataJson);
            final ArrayList<MultipleItemEntity> data=mInterviewsListConverter.setJsonData(dataJson).convert();
            if (refresh) {
                mInterviewsListAdapter.setNewData(data);
            } else {
                mInterviewsListAdapter.addData(data);
            }
            refresh = false;
        }
    }

    @Override
    public void getInterviewsFailuer(String msg) {
        mInterviewsListConverter.clearData();
        mInterviewsListAdapter.addData(mInterviewsListConverter.setJsonData("").convert());
    }


    @Override
    public void onRefresh() {
       refresh();
    }

    @Override
    public void onLoadMoreRequested() {
        getInterviews();
    }
}
