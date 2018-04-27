package com.linxz.main.mvp.appui;

import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.RelativeLayout;
import com.joanzapata.iconify.widget.IconTextView;
import com.linxz.core.activitys.AppActivityManager;
import com.linxz.core.activitys.BaseActivity;
import com.linxz.main.R;
import com.linxz.main.R2;
import com.linxz.main.ui.MainUiHelper;
import com.linxz.main.utils.MainSharePrefreshHanlder;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年04月17日21:22  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class SettingActivity extends BaseActivity {


    @BindView(R2.id.tv_reback)
    IconTextView tvReback;
    @BindView(R2.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R2.id.layout_up)
    RelativeLayout layoutUp;
    @BindView(R2.id.layout_faceback)
    RelativeLayout layoutFaceback;
    @BindView(R2.id.layout_scroll)
    RelativeLayout layoutScroll;
    @BindView(R2.id.layout_clear)
    RelativeLayout layoutClear;
    @BindView(R2.id.layout_about_us)
    RelativeLayout layoutAboutUs;
    @BindView(R2.id.layout_exist)
    RelativeLayout layoutExist;

    @OnClick(R2.id.tv_reback)
    public void rebackClick(){
        finish();
    }

    @OnClick(R2.id.layout_up)
    public void upClick(){
        MainUiHelper.create().openApplicationMarket(activity,"com.mjh.b");
    }

    @OnClick(R2.id.layout_faceback)
    public void faceBackClick(){

    }

    @OnClick(R2.id.layout_scroll)
    public void scrollPageClick(){
        AppActivityManager.getInstance().goTo(activity,LauncherScrollActivity.class);
    }

    @OnClick(R2.id.layout_clear)
    public void clearClick(){
         showToast("这个功能只是看看啦~~~");
    }

    @OnClick(R2.id.layout_about_us)
    public void aboutUsClick(){

    }

    @OnClick(R2.id.layout_exist)
    public void existClick(){

    }


    @Override
    public Object setLayout() {
        return R.layout.main_act_setting;
    }

    @Override
    public void initUI() {
        tvTitle.setText("设置");
        //判断是否登录
        if (MainSharePrefreshHanlder.isSignIn()){
            layoutExist.setVisibility(View.GONE);
        }

    }

    @Override
    public void initEnvent() {

    }

    @Override
    public void initData() {

    }




}
