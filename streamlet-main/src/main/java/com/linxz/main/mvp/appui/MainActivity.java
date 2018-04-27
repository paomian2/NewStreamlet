package com.linxz.main.mvp.appui;

import android.graphics.Color;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.linxz.core.activitys.bottom.BaseBottomActivity;
import com.linxz.core.activitys.bottom.BottomTabBean;
import com.linxz.core.activitys.bottom.ItemBuilder;
import com.linxz.core.base.ClassUtils;
import com.linxz.core.base.IViewDelegate;
import com.linxz.core.base.ViewManager;
import com.linxz.core.fragments.BaseFragment;
import com.linxz.main.mvp.appui.frags.IndexDelegate;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月07日17:44  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
@Route(path = "/main/home")
public class MainActivity extends BaseBottomActivity {

    @Override
    public LinkedHashMap<BottomTabBean, Fragment> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, Fragment> items = new LinkedHashMap<>();
     /*   items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{icon-msg}", "消息"), getUsersFragment());*/
        items.put(new BottomTabBean("{icon-study}", "基础"), getStudyFragment());
        items.put(new BottomTabBean("{icon-offer}", "面试"), getInterviewsFragment());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setOffscreenPageLimit() {
        return 4;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }

    @Override
    public void initEnvent() {
        super.initEnvent();
        hideToolBar(true);
    }

    /**
     * 在Users模块中寻找实现的Fragment
     *
     * @return Fragment
     */
    private BaseFragment getUsersFragment() {
        List<BaseFragment> frags=ViewManager.getInstance().getAllFragment();
        BaseFragment newsFragment = null;
        List<IViewDelegate> viewDelegates = ClassUtils.getObjectsWithInterface(this, IViewDelegate.class, "com.linxz.users");
        if (viewDelegates != null && !viewDelegates.isEmpty()) {
            newsFragment = viewDelegates.get(0).getFragment("");
        }
        return newsFragment;
    }

    /**
     * 在Study模块中寻找实现的Fragment
     *
     * @return Fragment
     */
    private BaseFragment getStudyFragment() {
        List<BaseFragment> frags=ViewManager.getInstance().getAllFragment();
        BaseFragment newsFragment = null;
        List<IViewDelegate> viewDelegates = ClassUtils.getObjectsWithInterface(this, IViewDelegate.class, "com.linxz.androidstudy");
        if (viewDelegates != null && !viewDelegates.isEmpty()) {
            newsFragment = viewDelegates.get(0).getFragment("");
        }
        return newsFragment;
    }

    /**
     * 在Interviews模块中寻找实现的Fragment
     *
     * @return Fragment
     */
    private BaseFragment getInterviewsFragment() {
        List<BaseFragment> frags=ViewManager.getInstance().getAllFragment();
        BaseFragment newsFragment = null;
        List<IViewDelegate> viewDelegates = ClassUtils.getObjectsWithInterface(this, IViewDelegate.class, "com.linxz.interviews");
        if (viewDelegates != null && !viewDelegates.isEmpty()) {
            newsFragment = viewDelegates.get(0).getFragment("");
        }
        return newsFragment;
    }
}
