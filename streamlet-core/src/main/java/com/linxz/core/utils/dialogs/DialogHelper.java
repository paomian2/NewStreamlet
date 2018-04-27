package com.linxz.core.utils.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.linxz.core.R;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年04月02日10:41  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class DialogHelper {

    private Context context;
    private String title;
    private String message;
    private OnPositiveClickListener onPositiveClickListener;
    private OnNegativeClickListener onNegativeClickListener;

    private DialogHelper(Context context,String title,String message,
                         OnPositiveClickListener onPositiveClickListener,OnNegativeClickListener onNegativeClickListener){
        this.context=context;
        this.title=title;
        this.message=message;
        this.onPositiveClickListener=onPositiveClickListener;
        this.onNegativeClickListener=onNegativeClickListener;
    }

    public static DialogBuilder builder(){
        return new DialogBuilder();
    }

    public static class  DialogBuilder{
        private Context context;
        private String title;
        private String message;
        private OnPositiveClickListener onPositiveClickListener;
        private OnNegativeClickListener onNegativeClickListener;

        public DialogBuilder withContext(Context context){
            this.context=context;
            return this;
        }

        public DialogBuilder withTitle(String title){
            this.title=title;
            return this;
        }

        public DialogBuilder withMessage(String message){
            this.message=message;
            return this;
        }

        public DialogBuilder withPositiveClick(OnPositiveClickListener onPositiveClickListener){
            this.onPositiveClickListener=onPositiveClickListener;
            return this;
        }

        public DialogBuilder withOnNegativeClick(OnNegativeClickListener onNegativeClickListener){
            this.onNegativeClickListener=onNegativeClickListener;
            return this;
        }

        public DialogHelper build(){
            return new DialogHelper(context,title,message,onPositiveClickListener,onNegativeClickListener);
        }

    }

    /**token失效Dialogs*/
    public Dialog showAuthDialog() {
        try {
            final Dialog dialog = new Dialog(context, R.style.CustomDialog);
            @SuppressLint("InflateParams") View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_tow_button, null);
            TextView tvTitle = contentView.findViewById(R.id.tv_title);
            TextView tvMsg = contentView.findViewById(R.id.tv_msg);
            TextView btn1 =  contentView.findViewById(R.id.btn1);
            TextView btn2 = contentView.findViewById(R.id.btn2);
            tvTitle.setText("账号异常");
            tvMsg.setText("该账号已在其它设备登录，是否重新登录");
            btn1.setText("确定");
            btn2.setText("取消");
            btn1.setOnClickListener(v -> {
                dialog.dismiss();
                if (onPositiveClickListener != null) {
                    onPositiveClickListener.onPositiveClick(null);
                }
            });
            btn2.setOnClickListener(v -> {
                dialog.dismiss();
                if (onNegativeClickListener != null) {
                    onNegativeClickListener.onNegativeClick(null);
                }
            });
            dialog.setContentView(contentView);
            dialog.show();
            return dialog;
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }


    public Dialog showBasicDialog() {
        try {
            final Dialog dialog = new Dialog(context, R.style.CustomDialog);
            @SuppressLint("InflateParams") View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_tow_button, null);
            TextView tvTitle = contentView.findViewById(R.id.tv_title);
            TextView tvMsg = contentView.findViewById(R.id.tv_msg);
            TextView btn1 =  contentView.findViewById(R.id.btn1);
            TextView btn2 = contentView.findViewById(R.id.btn2);
            tvTitle.setText(title);
            tvMsg.setText(message);
            btn1.setText("确定");
            btn2.setText("取消");
            btn1.setOnClickListener(v -> {
                dialog.dismiss();
                if (onPositiveClickListener != null) {
                    onPositiveClickListener.onPositiveClick(null);
                }
            });
            btn2.setOnClickListener(v -> {
                dialog.dismiss();
                if (onNegativeClickListener != null) {
                    onNegativeClickListener.onNegativeClick(null);
                }
            });
            dialog.setContentView(contentView);
            dialog.show();
            return dialog;
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }



}
