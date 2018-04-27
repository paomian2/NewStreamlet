package com.linxz.androidstudy.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.linxz.androidstudy.R;
import com.linxz.core.activitys.BaseActivity;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年03月10日17:05  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public final class ArticleDetailDialog {

    private BaseActivity mContext;
    private Dialog dialog;

    private ArticleDetailDialog(BaseActivity mContext) {
        this.mContext = mContext;
        dialog = new AlertDialog.Builder(mContext, R.style.car_dialog_style).create();
    }

    public static ArticleDetailDialog build(BaseActivity activity) {
        return new ArticleDetailDialog(activity);
    }

    public ArticleDetailDialog withUI(View view) {
        LayoutInflater inflater = mContext.getLayoutInflater();
        dialog.show();
        dialog.setContentView(view);
        WindowManager m = mContext.getWindowManager();
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setWindowAnimations(R.style.car_dialog_style);
        // 为获取屏幕宽、高
        Display d = m.getDefaultDisplay();
        final WindowManager.LayoutParams windowLP = mContext.getWindow().getAttributes();
        windowLP.alpha = 0.5f;
        mContext.getWindow().setAttributes(windowLP);
        final WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = d.getWidth();
        dialogWindow.setGravity(Gravity.BOTTOM);
        //设置bottom的偏移量
        //params.y=30;
        dialog.getWindow().setAttributes(params);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                windowLP.alpha = 1.0f;
                mContext.getWindow().setAttributes(windowLP);
            }
        });
        return this;
    }

    public void dissmiss(){
        dialog.dismiss();
    }

    public void show(){
        dialog.show();
    }

}

