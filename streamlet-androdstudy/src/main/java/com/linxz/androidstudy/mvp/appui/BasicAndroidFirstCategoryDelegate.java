package com.linxz.androidstudy.mvp.appui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.linxz.androidstudy.R;
import com.linxz.androidstudy.R2;
import com.linxz.androidstudy.mvp.presenter.BasicAndroidPresenter;
import com.linxz.androidstudy.pojo.Cate;
import com.linxz.androidstudy.ui.callback.BasicAndroidItemClickListener;
import com.linxz.androidstudy.ui.index.BasicAndroidCategoryAdapter;
import com.linxz.androidstudy.ui.index.BasicAndroidPagerConverter;
import com.linxz.core.activitys.BaseActivity;
import com.linxz.core.mvp.BaseMvpFragment;
import com.linxz.core.ui.recycle.BaseDecoration;
import com.linxz.core.ui.recycle.MultipleItemEntity;
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
 * V1.0   2018年03月11日0:19  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class BasicAndroidFirstCategoryDelegate extends BaseMvpFragment<BasicAndroidPresenter> implements BasicAndroidView {

    @BindView(R2.id.recycle_view)
    RecyclerView recycleView;
    private BasicAndroidPagerConverter mBasicAndroidPagerConverter;
    private BasicAndroidCategoryAdapter mBasicAndroidCategoryAdapter;

    private String parentId;

    @Override
    public void onGetBundle(Bundle bundle) {
        super.onGetBundle(bundle);
        parentId = bundle.getString("parentId");
    }

    @Override
    protected BasicAndroidPresenter onCreatePresenter() {
        return new BasicAndroidPresenter(this);
    }

    @Override
    public Object setLayout() {
        return R.layout.study_recycleview;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @Override
    public void initUI() {
        final LinearLayoutManager manager = new LinearLayoutManager(activity);
        recycleView.setLayoutManager(manager);
        recycleView.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(activity, R.color.page_bg), 60));
        mBasicAndroidPagerConverter = new BasicAndroidPagerConverter();
        mBasicAndroidCategoryAdapter = new BasicAndroidCategoryAdapter(new ArrayList<MultipleItemEntity>());
        recycleView.setAdapter(mBasicAndroidCategoryAdapter);
        recycleView.addOnItemTouchListener(BasicAndroidItemClickListener.create());
    }

    @Override
    public void initData() {
        getCate();
    }


    @Override
    public void getCate() {
        Map<String, Object> params = new HashMap<>(16);
        params.put("parentId", parentId);
        mvpPresenter.getCate(params);
    }

    @Override
    public void getCateSuccess(List<Cate> cateList) {
        if (cateList == null || cateList.size() == 0) {
            mBasicAndroidPagerConverter.clearData();
            final List<MultipleItemEntity> data =
                    mBasicAndroidPagerConverter.setJsonData("").convert();
            mBasicAndroidCategoryAdapter.addData(data);
        } else {
            final String dataJson = new Gson().toJson(cateList);
            mBasicAndroidPagerConverter.clearData();
            final List<MultipleItemEntity> data =
                    mBasicAndroidPagerConverter.setJsonData(dataJson).convert();
            mBasicAndroidCategoryAdapter.addData(data);
        }
    }

    @Override
    public void getCateFailuer(String msg) {
        mBasicAndroidPagerConverter.clearData();
        final List<MultipleItemEntity> data =
                mBasicAndroidPagerConverter.setJsonData("").convert();
        mBasicAndroidCategoryAdapter.addData(data);
    }


    @Override
    public BaseActivity getContext() {
        return null;
    }
}
