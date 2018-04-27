package com.linxz.core.activitys.web.event;

import com.linxz.core.utils.log.LinxzLogger;

/**
 * Created by 傅令杰
 */

public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        LinxzLogger.e("UndefineEvent", params);
        return null;
    }
}
