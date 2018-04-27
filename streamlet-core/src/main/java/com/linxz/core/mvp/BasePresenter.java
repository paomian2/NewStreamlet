package com.linxz.core.mvp;

/**
 * 描述：
 * E-mail:lin_xiao_zhang@163.com
 * 时间:2017年07月12日  18:02
 * 版本：2.0
 * @author linxz
 */

public abstract class BasePresenter<T extends BaseView, M extends BaseModel> {

    public T mView;
    public M mModel;

    public BasePresenter(T mView) {
        attachView(mView);
        mModel = onCreateModel();
        if (mModel == null) {
            throw new RuntimeException("Mvp is not create model,call " + this.getClass().getSimpleName());
        }
    }

    private void attachView(T mView) {
        this.mView = mView;
    }

    void detachView() {
        if (mModel != null) {
            mModel.onUnsubscribe();
        }
        this.mView = null;
    }

    /**
     * 初始化Model
     */
    public abstract M onCreateModel();

}
