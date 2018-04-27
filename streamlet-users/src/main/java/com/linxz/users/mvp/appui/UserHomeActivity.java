package com.linxz.users.mvp.appui;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.joanzapata.iconify.widget.IconTextView;
import com.linxz.core.actions.observers.AffairConstans;
import com.linxz.core.actions.observers.AffairObserver;
import com.linxz.core.actions.observers.AffairObserverableExcute;
import com.linxz.core.activitys.AppActivityManager;
import com.linxz.core.activitys.BaseActivity;
import com.linxz.core.utils.image.ImageHelper;
import com.linxz.core.widget.CircleImageView;
import com.linxz.users.R;
import com.linxz.users.R2;
import com.linxz.users.mvp.UserInfoUpdateActivity;
import com.linxz.users.mvp.appui.push.MyPushListActivity;
import com.linxz.users.pojo.HomeItemEntity;
import com.linxz.users.pojo.UserEntity;
import com.linxz.users.ui.HomeItemAdapter;
import com.linxz.users.utils.UserSharePrefreshHanlder;

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
 * V1.0   2018年03月12日15:46  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
@Route(path = "/user/home")
public class UserHomeActivity extends BaseActivity implements AffairObserver {

    @BindView(R2.id.tv_img_user)
    AppCompatTextView tvImgUser;
    @BindView(R2.id.img_user)
    CircleImageView imgUser;
    @BindView(R2.id.tv_user_name)
    AppCompatTextView tvUserName;
    @BindView(R2.id.layout_users)
    LinearLayoutCompat layoutUsers;
    @BindView(R2.id.tv_user_v)
    AppCompatTextView tvUserV;
    @BindView(R2.id.icon_set)
    IconTextView iconSet;
    @BindView(R2.id.gv_users)
    GridView gvUsers;

    @OnClick(R2.id.icon_set)
    public void setClick() {
        showToast("设置");
    }

    @OnClick(R2.id.layout_users)
    public void goToUserInfo() {
        AppActivityManager.getInstance().goTo(activity, UserInfoActivity.class);
    }

    @Override
    public Object setLayout() {
        return R.layout.user_act_home;
    }

    @Override
    public void initUI() {
        final List<HomeItemEntity> list = new ArrayList<>();
        initItemData(list);
        final HomeItemAdapter adapter = new HomeItemAdapter(activity, list);
        gvUsers.setAdapter(adapter);
        gvUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HomeItemEntity entity = list.get(position);
                AppActivityManager.getInstance().goTo(activity, MyPushListActivity.class);
            }
        });
    }

    @Override
    public void initEnvent() {
        AffairObserverableExcute.getInstance().attach(this);
    }

    @Override
    public void initData() {
        UserEntity userEntity = UserSharePrefreshHanlder.getUserInfo();
        if (userEntity.getPortrait() == null || userEntity.getPortrait().isEmpty()) {
           tvImgUser.setText(userEntity.getNickName().substring(0,1));
           tvImgUser.setVisibility(View.VISIBLE);
           imgUser.setVisibility(View.GONE);
        }else{
            tvImgUser.setVisibility(View.GONE);
            imgUser.setVisibility(View.VISIBLE);
            ImageHelper.imageNet(activity,userEntity.getPortrait(),imgUser);
        }

        tvUserName.setText(userEntity.getNickName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AffairObserverableExcute.getInstance().detach(this);
    }

    private void initItemData(List<HomeItemEntity> list) {
        HomeItemEntity coin = new HomeItemEntity("{user-studycoin}", "学习币");
        HomeItemEntity collection = new HomeItemEntity("{user-studycoin}", "收藏");
        HomeItemEntity read = new HomeItemEntity("{user-studycoin}", "浏览历史");
        HomeItemEntity push = new HomeItemEntity("{user-studycoin}", "发布信息");
        HomeItemEntity studyWay = new HomeItemEntity("{user-studycoin}", "学习之路");
        HomeItemEntity healthy = new HomeItemEntity("{user-studycoin}", "健身计划");
        HomeItemEntity fans = new HomeItemEntity("{user-studycoin}", "粉丝");

        list.add(coin);
        list.add(collection);
        list.add(read);
        list.add(push);
        list.add(studyWay);
        list.add(healthy);
        list.add(fans);
    }


    /**
     * @see {@link UserInfoUpdateActivity#edtUserInfoResult(boolean, int, String)}
     * */
    @Override
    public void updateAffair(String tag, Object object) {
        if (AffairConstans.UPDATE_USER_INFO.equals(tag)) {
            initData();
        }
    }


}
