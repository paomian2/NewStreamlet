package com.linxz.core.base;

import android.support.annotation.Keep;
import android.view.View;
import com.linxz.core.fragments.BaseFragment;

/**
 * <p>
 * Function：
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月24日12:14  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
@Keep
public interface IViewDelegate {
    BaseFragment getFragment(String name);

    View getView(String name);
}
