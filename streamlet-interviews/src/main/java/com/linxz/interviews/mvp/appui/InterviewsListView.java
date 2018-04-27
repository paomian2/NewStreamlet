package com.linxz.interviews.mvp.appui;

import com.linxz.core.mvp.BaseView;
import com.linxz.interviews.pojo.InterviewsEntity;

import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月20日23:03  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public interface InterviewsListView extends BaseView{

    /**根据分类Id获取面试题列表*/
    void getInterviews();
    void getInterviewsSuccess(List<InterviewsEntity> interviewEntityList);
    void getInterviewsFailuer(String msg);
}
