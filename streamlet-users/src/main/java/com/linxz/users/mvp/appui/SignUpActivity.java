package com.linxz.users.mvp.appui;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import com.linxz.core.activitys.AppActivityManager;
import com.linxz.core.activitys.BaseActivity;
import com.linxz.core.mvp.BaseMvpActivity;
import com.linxz.users.R;
import com.linxz.users.R2;
import com.linxz.users.mvp.presenter.SignUpPresenter;

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
 * V1.0   2018年03月12日22:15  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class SignUpActivity extends BaseMvpActivity<SignUpPresenter> implements SignUpI {

    @BindView(R2.id.edit_sign_up_username)
    TextInputEditText editSignUpUserName;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText editSignUpPassword;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText editSignUpRePassword;
    @BindView(R2.id.btn_sign_up)
    AppCompatButton btnSignUp;
    @BindView(R2.id.tv_link_sign_in)
    AppCompatTextView tvLinkSignIn;

    @OnClick(R2.id.btn_sign_up)
    public void btnSignUp() {
        if (checkForm()) {
           signUp();
        }
    }

    @OnClick(R2.id.tv_link_sign_in)
    public void goToSignIn() {
        Intent intent = new Intent(activity, SignInActivity.class);
        AppActivityManager.getInstance().goTo(activity, intent);
        finish();
    }

    @Override
    public Object setLayout() {
        return R.layout.user_act_signup;
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

    private boolean checkForm() {
        final String userName = editSignUpUserName.getText().toString().trim();
        final String password = editSignUpPassword.getText().toString().trim();
        final String repassword = editSignUpRePassword.getText().toString().trim();
        int minUserNameLength=2;
        int maxUserNameLength=12;
        int minLength = 6;
        int maxLength = 12;

        if (userName.isEmpty() || userName.length()<minUserNameLength || userName.length()>maxUserNameLength) {
            editSignUpUserName.setError("请输入2-12位字符");
            return false;
        } else {
            editSignUpUserName.setError(null);
        }

        if (password.isEmpty() || password.length() < minLength || password.length() > maxLength) {
            editSignUpPassword.setError("请输入6-12为密码");
            return false;
        } else {
            editSignUpPassword.setError(null);
        }

        if (repassword.isEmpty() || repassword.length() < minLength || repassword.length() > maxLength) {
            editSignUpRePassword.setError("请输入6-12为密码");
            return false;
        } else {
            editSignUpRePassword.setError(null);
        }

        if (!password.equals(repassword)) {
            editSignUpRePassword.setError("两个密码不一致");
            return false;
        } else {
            editSignUpRePassword.setError(null);
        }

        return true;
    }


    @Override
    public BaseActivity getContext() {
        return activity;
    }

    @Override
    protected SignUpPresenter createPresenter() {
        return new SignUpPresenter(this);
    }

    @Override
    public void signUp() {
        Map<String,Object> params=new HashMap<>(16);
        params.put("name",editSignUpUserName.getText().toString().trim());
        params.put("pwd",editSignUpRePassword.getText().toString().trim());
        mvpPresenter.signUp(params);
    }

    @Override
    public void signUpSuccess() {
         showToast("注册成功！");
         finish();
    }

    @Override
    public void signUpFailer(String msg) {
        showToast(msg);
    }
}
