package com.linxz.core.http.callback;

/**
 * @author linxz
 */

public interface IError {

    void onError(int code, String msg);
}
