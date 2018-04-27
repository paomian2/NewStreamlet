package com.linxz.main.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.linxz.core.activitys.BaseActivity;
import com.linxz.main.R;

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
public final class UpdateDialog {

    private BaseActivity mContext;
    private TextView txtUpdateVersion;
    private TextView txtUpdateContent;
    private ImageView btnUpdateClose;
    private Button btnUpdateYes;
    private Dialog dialog;

    public interface OnUpdateYesClickListener {
        void onYesClickCallBack();
    }

    public interface OnUpdateCloseClickListener {
        void onCloseClickBack();
    }

    private UpdateDialog(BaseActivity mContext) {
        this.mContext = mContext;
        dialog = new AlertDialog.Builder(mContext, R.style.car_dialog_style).create();
        withUI();
    }

    public static UpdateDialog build(BaseActivity activity) {
        return new UpdateDialog(activity);
    }


    private void withUI() {
        LayoutInflater inflater = mContext.getLayoutInflater();
        View view = inflater.inflate(R.layout.wi_update_dialog, null);
        txtUpdateVersion = view.findViewById(R.id.txtUpdateVersion);
        txtUpdateContent = view.findViewById(R.id.txtUpdateContent);
        btnUpdateClose = view.findViewById(R.id.btnUpdateClose);
        btnUpdateYes = view.findViewById(R.id.btnUpdateYes);

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
    }



    public final UpdateDialog withVersion(String version) {
        if (txtUpdateVersion == null) {
            throw new NullPointerException("txtUpdateVersion is NUll,please withUI first");
        }
        txtUpdateVersion.setText(version);
        return this;
    }

    public final UpdateDialog withContent(String content) {
        if (txtUpdateContent == null) {
            throw new NullPointerException("txtUpdateContent is NUll,please withUI first");
        }
        txtUpdateContent.setText(content);
        return this;
    }

    public final UpdateDialog withYesCallBack(final OnUpdateYesClickListener yesCallBack) {
        if (btnUpdateYes == null) {
            throw new NullPointerException("btnUpdateClose is NUll,please withUI first");
        }
        btnUpdateYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesCallBack != null) {
                    yesCallBack.onYesClickCallBack();
                    dialog.dismiss();
                }
            }
        });
        return this;
    }

    public final UpdateDialog withCloseCallBack(final OnUpdateCloseClickListener closeCallBack) {
        if (btnUpdateClose == null) {
            throw new NullPointerException("btnUpdateClose is NUll,please withUI first");
        }
        btnUpdateClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (closeCallBack != null) {
                    closeCallBack.onCloseClickBack();
                    dialog.dismiss();
                }
            }
        });
        return this;
    }



}
