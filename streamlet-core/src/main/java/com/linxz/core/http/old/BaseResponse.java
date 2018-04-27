package com.linxz.core.http.old;

import java.io.Serializable;

/**
 * 描述：
 * E-mail:lin_xiao_zhang@163.com
 * 时间:2017年10月09日  15:37
 * 版本：3.0
 * @author linxz
 */

public class BaseResponse implements Serializable {

    /**响应业务状态*/
    private Integer status;
    /**响应消息*/
    private String msg;
    /**响应中的数据*/
    private Object data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
