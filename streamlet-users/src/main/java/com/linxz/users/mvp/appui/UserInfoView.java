package com.linxz.users.mvp.appui;

import com.linxz.core.mvp.BaseView;
import com.linxz.users.pojo.UserEntity;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月28日15:14  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public interface UserInfoView extends BaseView {

    /**
     * 获取用户信息
     */
    void getUserInfo();

    void getUserInfoResult(boolean success, UserEntity userEntity, String msg);

    /**图片上传*/
    void upload(String filePath);
    void uploadResult(boolean success,String msg,String path);

    /**
     * 编辑个人信息
     */
    void edtUserInfo(int actionCode,String updateContent);

    void edtUserInfoResult(boolean success, int code,String msg);


}
