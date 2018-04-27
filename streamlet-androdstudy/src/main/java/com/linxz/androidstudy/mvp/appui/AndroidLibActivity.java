package com.linxz.androidstudy.mvp.appui;

import android.annotation.SuppressLint;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.linxz.androidstudy.R;
import com.linxz.androidstudy.R2;
import com.linxz.androidstudy.mvp.presenter.AndroidLibPresenter;
import com.linxz.androidstudy.pojo.ArticleEntity;
import com.linxz.androidstudy.ui.AndroidLibAdapter;
import com.linxz.androidstudy.ui.AndroidLibConverter;
import com.linxz.androidstudy.ui.callback.AndroidLibListItemClickListener;
import com.linxz.core.activitys.BaseActivity;
import com.linxz.core.mvp.BaseMvpActivity;
import com.linxz.core.ui.recycle.BaseDecoration;
import com.linxz.core.ui.recycle.MultipleItemEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月21日22:58  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
@Route(path = "/study/lib")
public class AndroidLibActivity extends BaseMvpActivity<AndroidLibPresenter> implements AndroidLibView{

    @BindView(R2.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R2.id.layout_refresh)
    SwipeRefreshLayout layoutRefresh;
    @BindView(R2.id.recycle_view)
    RecyclerView recycleView;

    private AndroidLibConverter mAndroidLibConverter;
    private AndroidLibAdapter mAndroidLibAdapter;

    private int page;

    @OnClick(R2.id.tv_reback)
    public void tv_reback(){
        finish();
    }

    @Override
    protected AndroidLibPresenter createPresenter() {
        return new AndroidLibPresenter(this);
    }

    @Override
    public Object setLayout() {
        return R.layout.study_act_study_list;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void initUI() {
        tvTitle.setText("Android开源库");

        LinearLayoutManager layoutManager=new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(layoutManager);
        recycleView.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(activity,R.color.page_bg),1));

        mAndroidLibConverter=new AndroidLibConverter();
        mAndroidLibAdapter=AndroidLibAdapter.creater(new ArrayList<MultipleItemEntity>());
        recycleView.setAdapter(mAndroidLibAdapter);

        recycleView.addOnItemTouchListener(AndroidLibListItemClickListener.creater());
    }

    @Override
    public void initEnvent() {

    }

    @Override
    public void initData() {
        getAndroidLibs();
    }

    @Override
    public BaseActivity getContext() {
        return null;
    }


    @Override
    public void getAndroidLibs() {
        final HashMap<String,Object> params=new HashMap<>(2);
        params.put("page",page);
        params.put("size",15);
        mvpPresenter.getAndroidLibs(params);
    }

    @Override
    public void getAndroidLibsSuccess(List<ArticleEntity> articleEntityList) {
        if (articleEntityList==null || articleEntityList.size()==0){
            mAndroidLibAdapter.addData(mAndroidLibConverter.setJsonData("").convert());
        }else{
            mAndroidLibConverter.clearData();
            final String response=new Gson().toJson(articleEntityList);
            final ArrayList<MultipleItemEntity> data=mAndroidLibConverter.setJsonData(response).convert();
            mAndroidLibAdapter.addData(data);
        }

    }

    @Override
    public void getAndroidLibsFailuer() {
        mAndroidLibAdapter.addData(mAndroidLibConverter.setJsonData("").convert());
    }
}
