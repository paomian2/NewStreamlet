package com.linxz.core.utils.time;

import java.util.Date;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月25日16:07  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class TimeUtils {

    /**
     * 格式化时间 如：12小时前
     *
     * @param timestr
     *            秒
     */
    public static String fmttoCN(String timestr) {
        String timeText = null;
        if (null == timestr || "".equals(timestr)) {
            return "";
        }

        long time = Long.valueOf(timestr);

        Date dt = new Date();
        long nowSec = dt.getTime();
        long timediff = (nowSec - time) / 1000;
        if (timediff < 60) {
            // 小与1分钟显示 ‘刚刚’
            timeText = "刚刚";
        } else if (timediff >= 60 && timediff < 60 * 60) {
            // 小于1小时 显示‘分钟’
            timeText = String.valueOf((int) timediff / 60) + "分钟前";
        } else if (timediff >= 60 * 60 && timediff < 24 * 60 * 60) {
            // 小于24小时，则显示‘时’
            timeText = String.valueOf((int) timediff / (60 * 60)) + "小时前";
        } else if (timediff >= 24 * 60 * 60 && timediff < 30 * 24 * 60 * 60) {
            // 小于1个月，则显示‘天’
            timeText = String.valueOf((int) timediff / (24 * 60 * 60)) + "天前";
        } else if (timediff >= 30 * 24 * 60 * 60 && timediff < 12 * 30 * 24 * 60 * 60) {
            // 小于1年，则显示‘月’
            timeText = String.valueOf((int) timediff / (30 * 24 * 60 * 60)) + "个月前";
        } else if (timediff >= 12 * 30 * 24 * 60 * 60) {
            // 大于1年显示‘年’
            timeText = String.valueOf((int) timediff / (12 * 30 * 24 * 60 * 60)) + "年前";
        }

        return timeText;

    }
}
