package com.linxz.core.mvp;

import android.os.Bundle;

import com.linxz.core.activitys.BaseActivity;

/**
 * 描述：
 * E-mail:lin_xiao_zhang@163.com
 * 时间:2017年07月12日  21:20
 * 版本：3.0
 * @author linxz
 */

public abstract class BaseMvpActivity<P extends BasePresenter> extends BaseActivity {
    public P mvpPresenter;

    protected abstract P createPresenter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mvpPresenter=createPresenter();
        if (mvpPresenter==null){
            throw new RuntimeException("Mvp is not create presenter,call "+this.getClass().getSimpleName());
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
    }


}
