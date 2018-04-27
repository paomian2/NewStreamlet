package com.linxz.users.mvp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.joanzapata.iconify.widget.IconTextView;
import com.linxz.core.actions.observers.AffairConstans;
import com.linxz.core.actions.observers.AffairObserverableExcute;
import com.linxz.core.activitys.AppActivityManager;
import com.linxz.core.activitys.BaseActivity;
import com.linxz.core.mvp.BaseMvpActivity;
import com.linxz.core.utils.LinxzSharedPreference;
import com.linxz.core.utils.dialogs.DialogHelper;
import com.linxz.core.utils.dialogs.OnPositiveClickListener;
import com.linxz.users.R;
import com.linxz.users.R2;
import com.linxz.users.app.ActivityPathManager;
import com.linxz.users.mvp.appui.UserInfoUpdateView;
import com.linxz.users.mvp.presenter.UserInfoUpdatePresenter;
import com.linxz.users.pojo.UserEntity;
import com.linxz.users.utils.Constans;
import com.linxz.users.utils.UserSharePrefreshHanlder;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月29日12:58  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class UserInfoUpdateActivity extends BaseMvpActivity<UserInfoUpdatePresenter> implements UserInfoUpdateView {


    /**
     * 请求码
     */
    public static final int REQUEST_CODE = 10086;
    /**
     * 昵称修改
     */
    public static final int ACTION_CODE_NICKNAME = 1001;
    /**
     * 签名修改
     */
    public static final int ACTION_CODE_SIGN = 1002;
    /**
     * Email
     */
    public static final int ACTION_CODE_EAMIL = 1003;
    /**
     * 地址
     */
    public static final int ACTION_CODE_ADRESS = 1004;

    private int actionCode;
    private static final String ACTION_CODE = "actionCode";
    private String targetStr;
    private static final String TARGET_STR = "targetStr";

    @BindView(R2.id.tv_reback)
    IconTextView tvReback;
    @BindView(R2.id.tv_title)
    AppCompatTextView tvTitle;
    @BindView(R2.id.tv_right)
    AppCompatTextView tvRight;
    @BindView(R2.id.edt_content)
    AppCompatEditText edtContent;
    @BindView(R2.id.tv_desc)
    AppCompatTextView tvDesc;

    @OnClick(R2.id.tv_reback)
    public void reBack() {
        finish();
    }

    @OnClick(R2.id.tv_right)
    public void btnRight() {
        edtUserInfo();
    }

    public static void launcher(BaseActivity activity, int actionCode, String targetStr) {
        Intent intent = new Intent();
        intent.setClass(activity, UserInfoUpdateActivity.class);
        intent.putExtra(ACTION_CODE, actionCode);
        intent.putExtra(TARGET_STR, targetStr);
        AppActivityManager.getInstance().goFoResult(activity, intent, REQUEST_CODE);
    }

    @Override
    protected UserInfoUpdatePresenter createPresenter() {
        return new UserInfoUpdatePresenter(this);
    }

    @Override
    public void onGetBundle(Bundle bundle) {
        super.onGetBundle(bundle);
        actionCode = getIntent().getIntExtra(ACTION_CODE, ACTION_CODE_NICKNAME);
        targetStr = getIntent().getStringExtra(TARGET_STR);
    }

    @Override
    public Object setLayout() {
        return R.layout.user_act_userupdate;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void initUI() {
        tvRight.setVisibility(View.VISIBLE);
        switch (actionCode) {
            case ACTION_CODE_NICKNAME:
                tvTitle.setText("昵称编辑");
                tvDesc.setText("一个有逼格的名字可以让其他人很容易关注你");
                break;
            case ACTION_CODE_SIGN:
                tvTitle.setText("签名编辑");
                tvDesc.setText("来一个有逼格的签名吧...");
                break;
            case ACTION_CODE_EAMIL:
                tvTitle.setText("Email编辑");
                tvDesc.setText("绑定邮箱有助于找回账号...");
                break;
            case ACTION_CODE_ADRESS:
                tvTitle.setText("地址编辑");
                tvDesc.setText("其实地址并没有什么卵用啦!!!");
                break;
            default:
                break;
        }
    }

    @Override
    public void initEnvent() {
        tvRight.setEnabled(false);
        if (targetStr == null || targetStr.isEmpty()) {
            //添加
            tvRight.setText("添加");
        }
        edtContent.addTextChangedListener(mTextWatcher);
    }

    @Override
    public void initData() {

    }

    @Override
    public BaseActivity getContext() {
        return activity;
    }

    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String tmp = edtContent.getText().toString().trim();
            if (targetStr == null || targetStr.isEmpty()) {
                //添加
                if (tmp.length() > 0) {
                    tvRight.setEnabled(true);
                    tvRight.setBackgroundColor(ContextCompat.getColor(activity, R.color.title_color));
                } else {
                    tvRight.setEnabled(false);
                    tvRight.setBackgroundColor(ContextCompat.getColor(activity, R.color.btn_green));
                }
            } else {
                //编辑
                if (targetStr.equals(tmp) || tmp.length() == 0) {
                    tvRight.setEnabled(false);
                    tvRight.setBackgroundColor(ContextCompat.getColor(activity, R.color.btn_green));
                } else {
                    tvRight.setEnabled(true);
                    tvRight.setBackgroundColor(ContextCompat.getColor(activity, R.color.title_color));
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void addAdressMsg() {

    }

    @Override
    public void addAdressMsgResult(boolean success, String msg) {
        showToast(msg);
        if (success) {
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    public void edtUserInfo() {
        HashMap<String, Object> params = new HashMap<>(1);
        String userInfo = LinxzSharedPreference.getCustomAppProfile(Constans.USER_MESSAGE);
        UserEntity userEntity = new Gson().fromJson(userInfo, UserEntity.class);
        params.put("token", UserSharePrefreshHanlder.getToken());
        switch (actionCode) {
            case ACTION_CODE_NICKNAME:
                params.put("name", edtContent.getText().toString().trim());
                break;
            case ACTION_CODE_SIGN:
                params.put("sign", edtContent.getText().toString().trim());
                break;
            case ACTION_CODE_EAMIL:
                params.put("email", edtContent.getText().toString().trim());
                break;
            case ACTION_CODE_ADRESS:
                params.put("adress", edtContent.getText().toString().trim());
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
            AffairObserverableExcute.getInstance().notifyAffairObserver(AffairConstans.UPDATE_USER_INFO,null);
            setResult(RESULT_OK);
            finish();
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
        }else{
            showToast(msg);
        }
    }
}
