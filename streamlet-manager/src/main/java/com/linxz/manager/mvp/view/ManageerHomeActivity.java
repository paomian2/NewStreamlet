package com.linxz.manager.mvp.view;

import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.linxz.core.activitys.BaseActivity;
import com.linxz.core.widget.CircleImageView;
import com.linxz.core.widget.NoScrollViewPager;
import com.linxz.manager.R;
import com.linxz.manager.R2;
import com.linxz.manager.ui.ManagerIndexAdapter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年04月22日14:34  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
@Route(path = "/manager/home")
public class ManageerHomeActivity extends BaseActivity {

    @BindView(R2.id.img_manager)
    CircleImageView imgManager;
    @BindView(R2.id.tv_users)
    AppCompatTextView tvUsers;
    @BindView(R2.id.tv_study)
    AppCompatTextView tvStudy;
    @BindView(R2.id.tv_interview)
    AppCompatTextView tvInterview;
    @BindView(R2.id.viewPager)
    NoScrollViewPager noScrollViewPager;

    public List<Fragment> fragments = new ArrayList<>();

    @OnClick(R2.id.tv_users)
    public void userClick(){
        switchFrag(R.id.tv_users);
    }

    @OnClick(R2.id.tv_study)
    public void studyClick(){
        switchFrag(R.id.tv_study);
    }

    @OnClick(R2.id.tv_interview)
    public void interviewClick(){
        switchFrag(R.id.tv_interview);
    }

    @Override
    public Object setLayout() {
        return R.layout.manager_act_home;
    }

    @Override
    public void initUI() {
        List<Fragment> frgs=new ArrayList<>();
        frgs.add(new UserManagerHomeFrag());
        frgs.add(new StudyManagerHomeFrag());
        frgs.add(new InterviewManagerHomeFrag());
        ManagerIndexAdapter adapter=new ManagerIndexAdapter(getSupportFragmentManager(),frgs);
        noScrollViewPager.setAdapter(adapter);
        noScrollViewPager.setOffscreenPageLimit(3);
    }

    @Override
    public void initEnvent() {

    }

    @Override
    public void initData() {

    }

    private void switchFrag(int resId){
        int switchIndex=0;
        if (resId == R.id.tv_users) {
            switchIndex = 0;
        } else if (resId == R.id.tv_study) {
            switchIndex = 1;
        } else if (resId == R.id.tv_interview) {
            switchIndex = 2;
        }
        if (noScrollViewPager.getCurrentItem()!=switchIndex){
            noScrollViewPager.setCurrentItem(switchIndex);
        }
    }

}
