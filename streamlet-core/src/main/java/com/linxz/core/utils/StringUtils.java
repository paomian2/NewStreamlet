package com.linxz.core.utils;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月28日16:22  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class StringUtils {

    public static boolean isEmpty(String str){
        if (str==null || str.isEmpty()){
            return true;
        }
        return false;
    }
}
