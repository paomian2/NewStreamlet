package com.linxz.core.http.old;
import retrofit2.http.POST;
import rx.Observable;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月06日11:17  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 */
public interface HttpStore {

    /**发送验证码*/
    @POST("api/Consignor/SendValidateCode")
    Observable sendValidateCode();
}
