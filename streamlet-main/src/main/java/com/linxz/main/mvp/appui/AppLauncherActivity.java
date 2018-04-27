package com.linxz.main.mvp.appui;

import android.os.CountDownTimer;
import android.support.v7.widget.AppCompatTextView;
import android.view.WindowManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.linxz.core.activitys.AppActivityManager;
import com.linxz.core.activitys.BaseActivity;
import com.linxz.core.mvp.BaseMvpActivity;
import com.linxz.core.utils.LinxzSharedPreference;
import com.linxz.main.R;
import com.linxz.main.R2;
import com.linxz.main.mvp.presenter.AppLauncherPresenter;
import com.linxz.main.utils.Constans;

import java.text.MessageFormat;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月05日19:10  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class AppLauncherActivity extends BaseMvpActivity<AppLauncherPresenter> implements AppLauncherView {

    private  CountDownTimer timer;

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView tvLauncherTimer;

    @OnClick(R2.id.tv_launcher_timer)
    public void timeFinishClick(){
        timer.cancel();
        timer=null;
        checkIsShowScroll();
    }

    @Override
    protected AppLauncherPresenter createPresenter() {
        return new AppLauncherPresenter(this);
    }

    @Override
    public Object setLayout() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return R.layout.act_users_app_launcher;
    }

    @Override
    public void initUI() {
         timer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvLauncherTimer.setText(MessageFormat.format("跳过\n{0}s", millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                checkIsShowScroll();
            }
        }.start();

    }

    @Override
    public void initEnvent() {

    }

    @Override
    public void initData() {

    }

    @Override
    public BaseActivity getContext() {
        return activity;
    }

    /**判断是否显示滑动启动页*/
    private void checkIsShowScroll(){
       if (!LinxzSharedPreference.getAppFlag(Constans.HAS_FIRST_LAUNCHER_APP)){
          // AppActivityManager.getInstance().goTo(activity,LauncherScrollActivity.class);
           ARouter.getInstance().build("/manager/home").navigation();
           finish();
       }else{
           //AppActivityManager.getInstance().goTo(activity,MainActivity.class);
           ARouter.getInstance().build("/manager/home").navigation();
           finish();
       }
    }

}
