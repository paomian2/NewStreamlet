package com.linxz.core.http.old;
import com.linxz.core.utils.log.LinxzLogger;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * 描述：网络返回结果统一处理
 * E-mail:lin_xiao_zhang@163.com
 * 时间:2017年07月12日  18:16
 * 版本：3.0
 * @author linxz
 */

public abstract class ApiCallback<M extends BaseResponse> extends Subscriber<M> {


    public abstract void onSuccess(M model);

    public abstract void onFailure(String msg);

    public abstract void onFinish();

    @Override
    public void onCompleted() {
        onFinish();
    }

    @Override
    public void onNext(M model) {
        onSuccess(model);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            String msg = httpException.getMessage();
            LinxzLogger.d("ApiCallback failure:--->>"+msg);
            onFailure(msg);
        } else {
            LinxzLogger.d("ApiCallback failure:--->>"+e.getMessage());
            onFailure("网络异常");
        }
        onFinish();
    }

}
