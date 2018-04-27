package com.linxz.users.mvp.appui;

import com.linxz.core.mvp.BaseView;
import com.linxz.users.pojo.UserEntity;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月29日13:06  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public interface UserInfoUpdateView extends BaseView {

    /**
     * 添加地址信息
     */
    void addAdressMsg();

    void addAdressMsgResult(boolean success, String msg);

    /**
     * 编辑个人信息
     */
    void edtUserInfo();

    void edtUserInfoResult(boolean success, int code,String msg);
}
