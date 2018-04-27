package com.linxz.main.mvp.appui;

import android.view.WindowManager;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.linxz.core.activitys.AppActivityManager;
import com.linxz.core.activitys.BaseActivity;
import com.linxz.core.utils.LinxzSharedPreference;
import com.linxz.main.R;
import com.linxz.main.ui.launcher.LauncherHolderCreator;
import com.linxz.main.utils.Constans;

import java.util.ArrayList;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月07日14:29  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class LauncherScrollActivity extends BaseActivity {

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();

    @Override
    public Object setLayout() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mConvenientBanner = new ConvenientBanner<>(activity);
        return mConvenientBanner;
    }

    @Override
    public void initUI() {
        hideToolBar(true);
    }

    @Override
    public void initEnvent() {

    }

    @Override
    public void initData() {
        initBanner();
    }

    private void initBanner() {
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        mConvenientBanner
                .setPages(new LauncherHolderCreator(), INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(onItemClickListener)
                .setCanLoop(false);
    }


    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            //如果点击的是最后一个
            if (position == INTEGERS.size() - 1) {
                LinxzSharedPreference.setAppFlag(Constans.HAS_FIRST_LAUNCHER_APP, true);
                AppActivityManager.getInstance().goTo(activity,MainActivity.class);
                finish();
            }
        }
    };

}
