package com.linxz.core.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.linxz.core.fragments.BaseFragment;


/**
 * 描述：
 * E-mail:lin_xiao_zhang@163.com
 * 时间:2017年07月12日  21:05
 * 版本：3.0
 *
 * @author linxz
 */
public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment {

    public P mvpPresenter;

    /**
     * 初始化Presenter
     * @return P
     */
    protected abstract P onCreatePresenter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mvpPresenter = onCreatePresenter();
        if (mvpPresenter == null) {
            throw new RuntimeException("Mvp is not create presenter,call " + this.getClass().getSimpleName());
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mvpPresenter != null) {
            mvpPresenter.detachView();
        }
        super.onDestroy();
    }

}
