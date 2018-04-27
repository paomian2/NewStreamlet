package com.linxz.interviews.mvp.appui;

import com.linxz.core.mvp.BaseView;
import com.linxz.interviews.pojo.Cate;

import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月20日2:08  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public interface InterviewsDelegateView extends BaseView{

    /**根据父类ID获取子类*/
    void getCate();
    void getCateSuccess(List<Cate> cateList);
    void getCateFailuer(String msg);
}
