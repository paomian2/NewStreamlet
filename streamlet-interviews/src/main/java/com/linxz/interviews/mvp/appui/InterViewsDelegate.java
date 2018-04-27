package com.linxz.interviews.mvp.appui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;

import com.linxz.core.activitys.BaseActivity;
import com.linxz.core.mvp.BaseMvpFragment;
import com.linxz.core.utils.dimen.DimenUtil;
import com.linxz.core.widget.PagerTabStrip;
import com.linxz.interviews.R;
import com.linxz.interviews.R2;
import com.linxz.interviews.mvp.presenter.InterviewsDelegatePresenter;
import com.linxz.interviews.pojo.Cate;
import com.linxz.interviews.ui.InterviewsPagerAdapter;

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
 * V1.0   2018年03月07日17:48  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class InterViewsDelegate extends BaseMvpFragment<InterviewsDelegatePresenter> implements InterviewsDelegateView{

    @BindView(R2.id.tab_strip)
    PagerTabStrip tabStrip;
    @BindView(R2.id.view_pager)
    ViewPager viewPager;

    public static InterViewsDelegate newInstance(){
        return new InterViewsDelegate();
    }

    @Override
    protected InterviewsDelegatePresenter onCreatePresenter() {
        return new InterviewsDelegatePresenter(this);
    }

    @Override
    public Object setLayout() {
        return R.layout.interviews_delegate_interviews;
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
        Map<String,Object> params=new HashMap<>(16);
        params.put("parentId","c10043");
        mvpPresenter.getCate(params);
    }

    @Override
    public void getCateSuccess(List<Cate> cateList) {
        InterviewsPagerAdapter pagerAdapter=new InterviewsPagerAdapter(getChildFragmentManager(),activity,cateList);
        viewPager.setAdapter(pagerAdapter);
        initTabsValue();
    }

    @Override
    public void getCateFailuer(String msg) {

    }



    @Override
    public BaseActivity getContext() {
        return null;
    }
}
