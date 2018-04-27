package com.linxz.interviews.mvp.appui;

import com.linxz.core.mvp.BaseView;
import com.linxz.interviews.pojo.InterviewsEntity;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月21日0:51  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public interface InterviewsView extends BaseView{

    /**获取面试题*/
    void getInterview();
    void getInterviewSuccess(InterviewsEntity interviewsEntity);
    void getInterviewFailuer();
}
