package com.linxz.users.mvp.appui;

import android.Manifest;
import android.content.Intent;
import android.support.v7.widget.AppCompatTextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.joanzapata.iconify.widget.IconTextView;
import com.linxz.core.actions.observers.AffairConstans;
import com.linxz.core.actions.observers.AffairObserverableExcute;
import com.linxz.core.activitys.BaseActivity;
import com.linxz.core.mvp.BaseMvpActivity;
import com.linxz.core.ui.camera.CameraHandle;
import com.linxz.core.ui.loader.LatteLoader;
import com.linxz.core.utils.LinxzSharedPreference;
import com.linxz.core.utils.StringUtils;
import com.linxz.core.utils.dialogs.BasicPickerDialog;
import com.linxz.core.utils.dialogs.DialogHelper;
import com.linxz.core.utils.dialogs.OnItemSelectedListener;
import com.linxz.core.utils.dialogs.OnPositiveClickListener;
import com.linxz.core.utils.image.ImageHelper;
import com.linxz.core.utils.permission.OnPermissionDeniedWithNeverAsk;
import com.linxz.core.utils.permission.OnPermissionGranted;
import com.linxz.core.utils.permission.OnpermissionRationale;
import com.linxz.core.utils.permission.PermissionHandle;
import com.linxz.core.widget.CircleImageView;
import com.linxz.users.R;
import com.linxz.users.R2;
import com.linxz.users.app.ActivityPathManager;
import com.linxz.users.mvp.UserInfoUpdateActivity;
import com.linxz.users.mvp.presenter.UserInfoPresenter;
import com.linxz.users.pojo.UserEntity;
import com.linxz.users.utils.Constans;
import com.linxz.users.utils.UserSharePrefreshHanlder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月28日15:13  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class UserInfoActivity extends BaseMvpActivity<UserInfoPresenter> implements UserInfoView {


    @BindView(R2.id.tv_reback)
    IconTextView tvReback;
    @BindView(R2.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R2.id.img_user)
    CircleImageView imgUser;
    @BindView(R2.id.tv_nick_name)
    AppCompatTextView tvNickName;
    @BindView(R2.id.tv_desc)
    AppCompatTextView tvDesc;
    @BindView(R2.id.tv_sex)
    AppCompatTextView tvSex;
    @BindView(R2.id.tv_phone)
    AppCompatTextView tvPhone;
    @BindView(R2.id.tv_eamil)
    AppCompatTextView tvEamil;
    @BindView(R2.id.tv_adress)
    AppCompatTextView tvAdress;

    /**
     * 性别编辑
     */
    private final int ACTION_CODE_IMG = 1;
    /**
     * 头像编辑
     */
    private final int ACTION_CODE_SEX = 2;
    /**拍照请求码*/
    private final int CAMERA_REQUEST_CODE=188;

    @Override
    protected UserInfoPresenter createPresenter() {
        return new UserInfoPresenter(this);
    }

    @Override
    public Object setLayout() {
        return R.layout.user_act_userinfo;
    }

    @Override
    public void initUI() {
        tvTitle.setText("个人信息");
    }

    @Override
    public void initEnvent() {
    }

    @Override
    public void initData() {
        String json = LinxzSharedPreference.getCustomAppProfile(Constans.USER_MESSAGE);
        UserEntity userEntity = new Gson().fromJson(json, UserEntity.class);
        if (userEntity != null) {
            ImageHelper.imageNet(activity, userEntity.getPortrait(), imgUser);
            tvNickName.setText(userEntity.getNickName());
            tvDesc.setText(StringUtils.isEmpty(userEntity.getSign()) ? "来个有逼格的签名吧~~~" : userEntity.getSign());
            tvSex.setText("0".equals(userEntity.getSex()) ? "男" : "女");
            tvPhone.setText(StringUtils.isEmpty(userEntity.getPhone()) ? "未绑定" : userEntity.getPhone());
            tvEamil.setText(StringUtils.isEmpty(userEntity.getEmail()) ? "未绑定" : userEntity.getEmail());
            tvAdress.setText(StringUtils.isEmpty(userEntity.getAdress()) ? "未绑定" : userEntity.getAdress());
        }
    }

    @Override
    public BaseActivity getContext() {
        return activity;
    }


    @Override
    public void getUserInfo() {
        HashMap<String, Object> params = new HashMap<>(1);
        params.put("userId", UserSharePrefreshHanlder.getUserId());
        mvpPresenter.getUserInfo(params);
    }

    @Override
    public void getUserInfoResult(boolean success, UserEntity userEntity, String msg) {
        initData();
        LatteLoader.stopLoading();
    }

    @Override
    public void upload(String filePath) {
        mvpPresenter.upload(filePath);
        LatteLoader.showLoading(activity);
    }

    @Override
    public void uploadResult(boolean success, String msg, String path) {
         showToast(msg);
         if (success){
             edtUserInfo(ACTION_CODE_IMG,path);
         }else{
             LatteLoader.stopLoading();
         }
    }

    @Override
    public void edtUserInfo(int actionCode, String updateContent) {
        HashMap<String, Object> params = new HashMap<>(1);
        String userInfo = LinxzSharedPreference.getCustomAppProfile(Constans.USER_MESSAGE);
        UserEntity userEntity = new Gson().fromJson(userInfo, UserEntity.class);
        params.put("token", UserSharePrefreshHanlder.getToken());
        switch (actionCode) {
            case ACTION_CODE_IMG:
                params.put("headimg", updateContent);
                break;
            case ACTION_CODE_SEX:
                params.put("sex", updateContent);
                break;
            default:
                break;
        }
        mvpPresenter.edtUserInfo(params);
    }

    @Override
    public void edtUserInfoResult(boolean success, int code, String msg) {
        if (success) {
            showToast(msg);
            AffairObserverableExcute.getInstance().notifyAffairObserver(AffairConstans.UPDATE_USER_INFO, null);
            getUserInfo();
        } else if (code == 202) {
            DialogHelper
                    .builder()
                    .withContext(activity)
                    .withPositiveClick(new OnPositiveClickListener() {
                        @Override
                        public void onPositiveClick(Object object) {
                            ARouter.getInstance()
                                    .build(ActivityPathManager.SIGN_IN)
                                    .navigation();
                        }
                    }).build()
                    .showAuthDialog();
        } else {
            showToast(msg);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            switch (requestCode){
                case UserInfoUpdateActivity.REQUEST_CODE:
                    getUserInfo();
                    break;
                case CAMERA_REQUEST_CODE:
                    List<String> picPaths=CameraHandle.pickerResult(data);
                    if (picPaths!=null && picPaths.size()>0){
                          upload(picPaths.get(0));
                    }else{
                        showToast("图片操作异常!");
                    }
                    break;
                default:
                    break;
            }
        }
    }


    @OnClick(R2.id.tv_reback)
    public void reback() {
        finish();
    }

    @OnClick(R2.id.img_user)
    public void imgUserClick() {
        PermissionHandle
                .builder()
                .withActivity(activity)
                .withPermissions(Manifest.permission.CAMERA)
                .withOnPermissionGranted(new OnPermissionGranted() {
                    @Override
                    public void onGranted() {
                        CameraHandle
                                .builder()
                                .withActivity(activity)
                                .withRequestCode(CAMERA_REQUEST_CODE)
                                .withMaxSelectNum(1)
                                .build()
                                .pictureSelector();
                    }
                })
                .withOnpermissionRationale(new OnpermissionRationale() {
                    @Override
                    public void showRequestPermissionRationale() {

                    }
                })
                .withOnPermissionDeniedWithNeverAsk(new OnPermissionDeniedWithNeverAsk() {
                    @Override
                    public void deniedWithNeverAsk() {

                    }
                })
                .build()
                .permissionCheck();
    }

    @OnClick(R2.id.tv_nick_name)
    public void tvNickNameClick() {
        UserInfoUpdateActivity.launcher(activity, UserInfoUpdateActivity.ACTION_CODE_NICKNAME, tvNickName.getText().toString().trim());
    }

    @OnClick(R2.id.tv_desc)
    public void tvDescClick() {
        UserInfoUpdateActivity.launcher(activity, UserInfoUpdateActivity.ACTION_CODE_SIGN, tvDesc.getText().toString().trim());
    }

    @OnClick(R2.id.tv_sex)
    public void tvSexClick() {
        List<String> sexList = new ArrayList<>();
        sexList.add("男");
        sexList.add("女");
        BasicPickerDialog
                .builder()
                .withContext(activity)
                .withsItemSelectdListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(int index, String item) {
                        edtUserInfo(ACTION_CODE_SEX, index + "");
                    }
                })
                .withOptionsItems(sexList)
                .build()
                .showPickerDialog();
    }

    @OnClick(R2.id.tv_phone)
    public void tvPhoneClick() {
        showToast("手机号绑定功能尚未开通");
        // UserInfoUpdateActivity.launcher(activity, UserInfoUpdateActivity.ACTION_CODE_NICKNAME, tvNickName.getText().toString().trim());
    }

    @OnClick(R2.id.tv_eamil)
    public void tvEmailClick() {
        UserInfoUpdateActivity.launcher(activity, UserInfoUpdateActivity.ACTION_CODE_EAMIL, tvEamil.getText().toString().trim());
    }

    @OnClick(R2.id.tv_adress)
    public void tvAdressClick() {
        UserInfoUpdateActivity.launcher(activity, UserInfoUpdateActivity.ACTION_CODE_ADRESS, tvAdress.getText().toString().trim());
    }


}
