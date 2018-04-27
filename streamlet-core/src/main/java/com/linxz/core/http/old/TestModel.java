package com.linxz.core.http.old;
import com.linxz.core.mvp.BaseModel;
import java.util.WeakHashMap;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月06日0:01  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 * @author linxz
 */
public class TestModel extends BaseModel {

    public void sendCode(WeakHashMap<String,Object> params){
        HttpStore apiStore= ApiClient.retrofit().create(HttpStore.class);
    }
}
