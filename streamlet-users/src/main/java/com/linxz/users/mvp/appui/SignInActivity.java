package com.linxz.users.mvp.appui;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.linxz.core.activitys.AppActivityManager;
import com.linxz.core.activitys.BaseActivity;
import com.linxz.core.mvp.BaseMvpActivity;
import com.linxz.users.R;
import com.linxz.users.R2;
import com.linxz.users.mvp.presenter.SignInPresenter;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月12日21:51  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
@Route(path = "/user/signIn")
public class SignInActivity extends BaseMvpActivity<SignInPresenter> implements SignInI  {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText editEmail;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText editPassword;

    @OnClick(R2.id.btn_sign_in)
    public void btnSignIn() {
        if (checkForm()){
           signIn();
        }
    }

    @OnClick(R2.id.tv_link_sign_up)
    public void goToSignUp() {
        Intent intent=new Intent(activity,SignUpActivity.class);
        AppActivityManager.getInstance().goTo(activity,intent);
        finish();
    }

    @Override
    protected SignInPresenter createPresenter() {
        return new SignInPresenter(this);
    }

    @Override
    public Object setLayout() {
        return R.layout.user_act_signin;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initEnvent() {
    }

    @Override
    public void initData() {
    }

    @Override
    public BaseActivity getContext() {
        return activity;
    }

    private boolean checkForm() {
        final String email = editEmail.getText().toString().trim();
        final String password = editPassword.getText().toString().trim();
        int minUserNameLength=3;
        int maxUserNameLength=12;

        int minLength=4;
        int maxLength=12;

        if (email.isEmpty() || email.length()<minUserNameLength || email.length()>maxUserNameLength) {
            editEmail.setError("请输入3-12位字符");
            return false;
        } else {
            editEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < minLength || password.length() > maxLength) {
            editPassword.setError("请输入4-12位密码");
            return false;
        } else {
            editPassword.setError(null);
        }

        return true;
    }


    @Override
    public void signIn() {
        Map<String,Object> params=new HashMap<>(16);
        params.put("name",editEmail.getText().toString().trim());
        params.put("pwd", editPassword.getText().toString().trim());
        mvpPresenter.signIn(params);
    }

    @Override
    public void signInSuccess() {
        showToast("登录成功");
        finish();
    }

    @Override
    public void signInFauler(String msg) {
        showToast(msg);
    }
}
