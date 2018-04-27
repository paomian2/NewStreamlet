package com.linxz.users.mvp.appui.push;

import com.linxz.core.mvp.BaseView;
import com.linxz.users.pojo.Cate;

import java.util.List;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年04月03日20:35  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public interface PushView extends BaseView{
    /**根据父类ID获取子类*/
    void getCate();
    void getCateSuccess(List<Cate> cateList);
    void getCateFailuer(String msg);

    /**图片上传*/
    void upload(String filePath);
    void uploadResult(boolean success,String msg,String path);

    /**发布消息*/
    void pushMessage();
    void pushMessageResult(boolean success,int code,String msg);
}
