package com.linxz.androidstudy.mvp.appui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.util.TypedValue;
import android.view.View;
import com.alibaba.android.arouter.launcher.ARouter;
import com.linxz.androidstudy.R;
import com.linxz.androidstudy.R2;
import com.linxz.androidstudy.mvp.presenter.BasicAndroidPresenter;
import com.linxz.androidstudy.pojo.Cate;
import com.linxz.androidstudy.ui.index.StudyPagerAdapter;
import com.linxz.core.activitys.BaseActivity;
import com.linxz.core.mvp.BaseMvpFragment;
import com.linxz.core.utils.dimen.DimenUtil;
import com.linxz.core.widget.PagerTabStrip;
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
 * V1.0   2018年03月24日20:14  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class BasicAndroidDelegate extends BaseMvpFragment<BasicAndroidPresenter> implements BasicAndroidView {


    @BindView(R2.id.tv_Android_lib)
    AppCompatTextView tvAndroidLib;
    @BindView(R2.id.tab_strip)
    PagerTabStrip tabStrip;
    @BindView(R2.id.view_pager)
    ViewPager viewPager;

    @OnClick(R2.id.tv_Android_lib)
    public void goToAndroidLib(){
        ARouter.getInstance()
                .build("/study/lib")
                .navigation();
    }

    public static BasicAndroidDelegate newInstance(){
        return new BasicAndroidDelegate();
    }

    @Override
    protected BasicAndroidPresenter onCreatePresenter() {
        return new BasicAndroidPresenter(this);
    }

    @Override
    public Object setLayout() {
        return R.layout.study_index_delegate_study;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @Override
    public void initUI() {

    }

    @Override
    public void initData() {
        getCate();
    }

    /**
     * mPagerSlidingTabStrip默认值配置
     */
    private void initTabsValue() {
        tabStrip.setViewPager(viewPager);
        // 底部游标颜色
        tabStrip.setIndicatorColor(ContextCompat.getColor(activity, R.color.yellow));
        // tab的分割线颜色
        tabStrip.setDividerColor(Color.TRANSPARENT);
        // tab背景
        tabStrip.setBackgroundColor(ContextCompat.getColor(activity,R.color.colorPrimary));
        // tab底线高度
        tabStrip.setUnderlineHeight(1);
        // 游标高度
        tabStrip.setIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                3, getResources().getDisplayMetrics()));
        // 选中的文字颜色
        tabStrip.setSelectedTextColor(ContextCompat.getColor(activity, R.color.yellow));
        // 正常文字颜色
        tabStrip.setTextColor(ContextCompat.getColor(activity, R.color.tab_top_text_2));
        //设置tab文字大小
        tabStrip.setTextSize(DimenUtil.dip2px(14));
        //设置游标边距
        tabStrip.setIndicatorMargin(DimenUtil.dip2px(7));
    }


    @Override
    public void getCate() {
        Map<String, Object> params = new HashMap<>(16);
        params.put("parentId","c10009");
        mvpPresenter.getCate(params);
    }

    @Override
    public void getCateSuccess(List<Cate> cateList) {
        StudyPagerAdapter pagerAdapter = new StudyPagerAdapter(getChildFragmentManager(), activity, cateList);
        viewPager.setAdapter(pagerAdapter);
        initTabsValue();
        tvAndroidLib.setVisibility(View.VISIBLE);
    }

    @Override
    public void getCateFailuer(String msg) {

    }



    @Override
    public BaseActivity getContext() {
        return null;
    }
}
