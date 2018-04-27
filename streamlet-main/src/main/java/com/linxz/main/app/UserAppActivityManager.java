package com.linxz.main.app;

import android.app.Activity;
import android.content.Intent;
import com.alibaba.android.arouter.launcher.ARouter;
import com.linxz.core.utils.LinxzSharedPreference;
import com.linxz.main.utils.Constans;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月26日8:57  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class UserAppActivityManager {


    private static UserAppActivityManager mUserAppActivityManager;

    private UserAppActivityManager(){}

    public static UserAppActivityManager getInstance(){
        if (mUserAppActivityManager==null){
            mUserAppActivityManager=new UserAppActivityManager();
        }
        return mUserAppActivityManager;
    }


    public void goToAuth(Activity self, Class<? extends Activity> cls) {
        if (LinxzSharedPreference.getAppFlag(Constans.HAS_SIGN_IN)){
            Intent it = new Intent(self, cls);
            self.startActivity(it);
        }else{
            ARouter.getInstance().build(ActivityPathManager.SIGN_IN).navigation();
        }
    }


    public void goToAuth(String path) {
        if (LinxzSharedPreference.getAppFlag(Constans.HAS_SIGN_IN)){
            ARouter.getInstance().build(path).navigation();
        }else{
            ARouter.getInstance().build(ActivityPathManager.SIGN_IN).navigation();
        }
    }


}
