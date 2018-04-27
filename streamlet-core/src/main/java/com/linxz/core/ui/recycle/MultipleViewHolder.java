package com.linxz.core.ui.recycle;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
/**
 * @author linxz
 */

public class MultipleViewHolder extends BaseViewHolder {

    private MultipleViewHolder(View view) {
        super(view);
    }

    public static MultipleViewHolder create(View view) {
        return new MultipleViewHolder(view);
    }
}
