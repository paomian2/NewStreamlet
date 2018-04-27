package com.linxz.core.http.old;

/**
 * 描述：MVP中model数据出来结果回调
 * 作者：Linxz
 * E-mail:lin_xiao_zhang@163.com
 * 时间:2017年07月12日  22:11
 * 版本：3.0
 */

public interface ModelCallBack<M> {

    /**网络请求成功回调*/
     void onSuccess(M model);
    /**网络请求失败回调*/
     void onFailure(String msg);

}
