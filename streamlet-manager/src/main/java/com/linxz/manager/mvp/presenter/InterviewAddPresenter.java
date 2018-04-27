package com.linxz.manager.mvp.presenter;

import com.linxz.core.mvp.BasePresenter;
import com.linxz.manager.mvp.module.InterviewAddMoudel;
import com.linxz.manager.mvp.view.InterviewAddView;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年04月22日21:11  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class InterviewAddPresenter extends BasePresenter<InterviewAddView,InterviewAddMoudel>{

    public InterviewAddPresenter(InterviewAddView mView) {
        super(mView);
    }

    @Override
    public InterviewAddMoudel onCreateModel() {
        return new InterviewAddMoudel();
    }
}
