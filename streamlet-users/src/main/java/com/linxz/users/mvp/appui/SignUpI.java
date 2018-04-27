package com.linxz.users.mvp.appui;

import com.linxz.core.mvp.BaseView;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月18日17:01  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public interface SignUpI extends BaseView{

    /**注册*/
    void signUp();
    /**注册成功*/
    void signUpSuccess();
    /**注册失败*/
    void signUpFailer(String msg);
}
