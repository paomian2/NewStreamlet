package com.linxz.androidstudy.ui.index;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.linxz.androidstudy.mvp.appui.BasicAndroidFirstCategoryDelegate;
import com.linxz.androidstudy.pojo.Cate;

import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月11日0:17  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class StudyPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private List<Cate> list;


    StudyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public StudyPagerAdapter(FragmentManager fm,Context context,List<Cate> list) {
        super(fm);
        this.mContext=context;
        this.list=list;
    }



    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getTypeName();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Fragment getItem(int position) {
        return Fragment.instantiate(mContext, BasicAndroidFirstCategoryDelegate.class.getName());
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Bundle bundle = new Bundle();
        bundle.putString("parentId", list.get(position).getTypeId());
        BasicAndroidFirstCategoryDelegate f = (BasicAndroidFirstCategoryDelegate) super.instantiateItem(container, position);
        f.onGetBundle(bundle);
        return f;
    }
}
