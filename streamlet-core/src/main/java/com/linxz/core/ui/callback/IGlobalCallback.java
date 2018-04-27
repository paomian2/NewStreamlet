package com.linxz.core.ui.callback;

import android.support.annotation.Nullable;

/**
 * Created by 傅令杰
 */

public interface IGlobalCallback<T> {

    void executeCallback(@Nullable T args);
}
