package com.linxz.main.mvp.appui.frags;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.linxz.core.actions.observers.AffairConstans;
import com.linxz.core.actions.observers.AffairObserver;
import com.linxz.core.actions.observers.AffairObserverableExcute;
import com.linxz.core.activitys.AppActivityManager;
import com.linxz.core.activitys.BaseActivity;
import com.linxz.core.mvp.BaseMvpFragment;
import com.linxz.core.ui.loader.LatteLoader;
import com.linxz.core.ui.recycle.MultipleItemEntity;
import com.linxz.core.ui.recycle.MultipleRecyclerAdapter;
import com.linxz.core.utils.LinxzSharedPreference;
import com.linxz.core.utils.image.ImageHelper;
import com.linxz.core.widget.CircleImageView;
import com.linxz.main.R;
import com.linxz.main.R2;
import com.linxz.main.app.ActivityPathManager;
import com.linxz.main.app.UserAppActivityManager;
import com.linxz.main.mvp.appui.SettingActivity;
import com.linxz.main.mvp.presenter.IndexPresenter;
import com.linxz.main.pojo.index.BannerEntity;
import com.linxz.main.pojo.index.DiscoverEntity;
import com.linxz.main.pojo.index.IndexEntity;
import com.linxz.main.pojo.index.NewsEntity;
import com.linxz.main.pojo.index.UserEntity;
import com.linxz.main.ui.IndexSpaceItemDecoration;
import com.linxz.main.ui.callback.BannerCallBack;
import com.linxz.main.ui.callback.IndexItemClickListener;
import com.linxz.main.ui.index.IndexAdapter;
import com.linxz.main.ui.index.IndexConverter;
import com.linxz.main.ui.index.IndexItemType;
import com.linxz.main.utils.Constans;
import com.linxz.main.utils.MainSharePrefreshHanlder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月07日17:47  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class IndexDelegate extends BaseMvpFragment<IndexPresenter> implements IndexI, AffairObserver {

    @BindView(R2.id.layout_refresh)
    SwipeRefreshLayout layoutRefresh;
    @BindView(R2.id.view_pager)
    RecyclerView mRecyclerView;


    @BindView(R2.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R2.id.nav_view)
    NavigationView navView;

    private MultipleRecyclerAdapter mAdapter = null;
    private IndexConverter indexConverter = null;
    private IndexAdapter addressAdapter = null;

    public static IndexDelegate newInstance() {
        return new IndexDelegate();
    }

    @Override
    protected IndexPresenter onCreatePresenter() {
        return new IndexPresenter(this);
    }

    @Override
    public Object setLayout() {
        return R.layout.main_delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @Override
    public void initUI() {
        AffairObserverableExcute.getInstance().attach(this);

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.addItemDecoration(new IndexSpaceItemDecoration(16));

        indexConverter = new IndexConverter();
        addressAdapter = new IndexAdapter(new ArrayList<MultipleItemEntity>())
                .setBannerClickListener(mBannerCallBack);
        mRecyclerView.setAdapter(addressAdapter);
        mRecyclerView.addOnItemTouchListener(IndexItemClickListener.create());

        initNavigationData();
    }

    @Override
    public void initData() {
        LatteLoader.showLoading(activity);
        getBanners();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AffairObserverableExcute.getInstance().detach(this);
    }

    /**
     * 侧滑左侧页面
     */
    @SuppressLint("NewApi")
    private void initNavigationData() {
        //侧滑页面
        setupDrawerContent(navView);
        View navigationHeaderView = navView.getHeaderView(0);
        AppCompatTextView tvImgUser = navigationHeaderView.findViewById(R.id.tv_img_user);
        CircleImageView imgUser = navigationHeaderView.findViewById(R.id.img_user);
        AppCompatTextView tvUserName = navigationHeaderView.findViewById(R.id.tv_user_name);

        UserEntity userEntity = MainSharePrefreshHanlder.getUserInfo();
        if (userEntity != null) {
            if (userEntity.getPortrait() == null || userEntity.getPortrait().isEmpty()) {
                tvImgUser.setText(userEntity.getUserName().substring(0, 1));
                tvImgUser.setVisibility(View.VISIBLE);
                imgUser.setVisibility(View.GONE);
            } else {
                tvImgUser.setVisibility(View.GONE);
                imgUser.setVisibility(View.VISIBLE);
                ImageHelper.imageNet(activity, userEntity.getPortrait(), imgUser);
            }
            tvUserName.setText(userEntity.getUserName());
        }

        navigationHeaderView.findViewById(R.id.layout_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserAppActivityManager.getInstance()
                        .goToAuth(ActivityPathManager.USER_HOME);
                mDrawerLayout.closeDrawers();
            }
        });
    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    @SuppressLint("InvalidR2Usage")
    public void selectDrawerItem(MenuItem menuItem) {
        Bundle args = new Bundle();

        int i = menuItem.getItemId();
        if (i == R.id.nav_first_fragment) {
            if (LinxzSharedPreference.getAppFlag(Constans.HAS_SIGN_IN)) {
                ARouter.getInstance().build("/user/home").navigation();
            } else {
                ARouter.getInstance().build("/user/signIn").navigation();
            }

        } else if (i == R.id.nav_second_fragment) {
            showToast("第二行");

        } else if (i == R.id.nav_third_fragment) {
            showToast("该模块尚未开通，敬请期待！");

        } else if (i == R.id.nav_fourth_fragment) {
            AppActivityManager.getInstance().goTo(activity, SettingActivity.class);
        } else {
            ARouter.getInstance().build("/user/im").navigation();
        }
    }

    @Override
    public BaseActivity getContext() {
        return null;
    }

    @Override
    public void getBanners() {
        mvpPresenter.getBanners();
    }

    @Override
    public void getBannersSuccess(List<BannerEntity> banners) {
        List<IndexEntity> entityList = new ArrayList<>();
        IndexEntity entity = new IndexEntity();
        entity.setItemType(IndexItemType.BANNER);
        entity.setBannerEntity(banners);
        entityList.add(entity);
        String json = new Gson().toJson(entityList);
        ArrayList<MultipleItemEntity> data = indexConverter.setJsonData(json).convert();
        addressAdapter.addData(data);

        getActiveUsers();
    }

    @Override
    public void getBannerFailer(String msg) {
        LatteLoader.stopLoading();
    }

    @Override
    public void getActiveUsers() {
        mvpPresenter.getActiveUsers();
    }

    @Override
    public void getActiveUsersSuccess(List<UserEntity> users) {
        List<IndexEntity> entityList = new ArrayList<>();
        IndexEntity titleEntity = new IndexEntity();
        titleEntity.setItemType(IndexItemType.TITLE);
        titleEntity.setTitle("活跃用户");
        entityList.add(titleEntity);

        IndexEntity usesEntity = new IndexEntity();
        usesEntity.setItemType(IndexItemType.ACTIVE_USERS);
        usesEntity.setUsers(users);
        entityList.add(usesEntity);

        String json = new Gson().toJson(entityList);
        indexConverter.clearData();
        final ArrayList<MultipleItemEntity> data = indexConverter.setJsonData(json).convert();
        addressAdapter.addData(data);

        getITNews();
    }

    @Override
    public void getActiveUsersFailuer() {
        LatteLoader.stopLoading();
    }

    @Override
    public void getITNews() {
        mvpPresenter.getITNews();
    }

    @Override
    public void getITNewsSuccess(List<NewsEntity> itnews) {
        List<IndexEntity> entityList = new ArrayList<>();
        IndexEntity titleEntity = new IndexEntity();
        titleEntity.setItemType(IndexItemType.TITLE);
        titleEntity.setTitle("IT资讯");
        entityList.add(titleEntity);

        for (NewsEntity entity : itnews) {
            IndexEntity indexEntity = new IndexEntity();
            indexEntity.setItemType(IndexItemType.NEWS_IT);
            indexEntity.setNewsEntity(entity);
            entityList.add(indexEntity);
        }

        String json = new Gson().toJson(entityList);
        indexConverter.clearData();
        final ArrayList<MultipleItemEntity> data = indexConverter.setJsonData(json).convert();
        addressAdapter.addData(data);

        getPushs();
    }

    @Override
    public void getITNewsFailuer() {
        LatteLoader.stopLoading();
    }

    @Override
    public void getPushs() {
        HashMap<String,Object> params=new HashMap<>(16);
        params.put("page",0);
        params.put("size",100);
        mvpPresenter.getDicovers(params);
    }

    @Override
    public void getPushSuccess(List<DiscoverEntity> discovers) {
        List<IndexEntity> entityList = new ArrayList<>();
        IndexEntity titleEntity = new IndexEntity();
        titleEntity.setItemType(IndexItemType.TITLE);
        titleEntity.setTitle("秀一秀");
        entityList.add(titleEntity);

        for (DiscoverEntity entity : discovers) {
            IndexEntity usesEntity = new IndexEntity();
            usesEntity.setItemType(IndexItemType.DISCOVER);
            usesEntity.setDiscoverEntity(entity);
            entityList.add(usesEntity);

        }
        String json = new Gson().toJson(entityList);
        indexConverter.clearData();
        final ArrayList<MultipleItemEntity> data = indexConverter.setJsonData(json).convert();
        addressAdapter.addData(data);
        LatteLoader.stopLoading();
    }

    @Override
    public void getPushsFailuer() {
        LatteLoader.stopLoading();
    }


    /**
     * Banner点击事件
     */
    private BannerCallBack mBannerCallBack = new BannerCallBack() {
        @Override
        public void onBannerClick(BannerEntity bannerEntity) {
            showToast(bannerEntity.getPic_url());
            ARouter.getInstance()
                    .build("/study/blog")
                    .withString("blogId", bannerEntity.getLink_id())
                    .withString("title", "Android")
                    .withString("url", bannerEntity.getLink_url())
                    .navigation();
        }
    };


    /**
     * UserInfoUpdateActivity#edtUserInfoResult
     */

    @Override
    public void updateAffair(String tag, Object object) {
        if (AffairConstans.UPDATE_USER_INFO.equals(tag)) {
            initNavigationData();
        }
    }
}
