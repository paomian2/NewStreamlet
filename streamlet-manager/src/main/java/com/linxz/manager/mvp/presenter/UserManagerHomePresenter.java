package com.linxz.manager.mvp.presenter;

import com.linxz.core.mvp.BaseModel;
import com.linxz.core.mvp.BasePresenter;
import com.linxz.manager.mvp.view.UserManagerHomeView;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年04月22日19:47  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class UserManagerHomePresenter extends BasePresenter<UserManagerHomeView,BaseModel>{

    public UserManagerHomePresenter(UserManagerHomeView mView) {
        super(mView);
    }

    @Override
    public BaseModel onCreateModel() {
        return new BaseModel();
    }
}
