package com.linxz.users.mvp.appui;

import com.linxz.core.mvp.BaseView;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月17日17:07  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public interface SignInI extends BaseView{

    /**发起登录请求*/
    void signIn();
    /**登录成功*/
    void signInSuccess();
    /**登录失败*/
    void signInFauler(String msg);
}
