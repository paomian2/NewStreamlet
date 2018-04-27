package com.linxz.core.utils.permission;

import android.app.Activity;

import com.tbruyelle.rxpermissions2.RxPermissions;

/**
 * <p>
 * Function： TODO
 * <p>
 * ver     date      		author
 * ──────────────────────────────────
 * V1.0   2018年04月03日13:37  lin_xiao_zhang@163.com
 * <p>
 * Copyright (c) 2018,  All Rights Reserved.
 *
 * @author linxz
 */
public class PermissionHandle {

    private Activity activity;
    private String[] permissions;
    private OnPermissionGranted mOnPermissionGranted;
    private OnpermissionRationale mOnpermissionRationale;
    private OnPermissionDeniedWithNeverAsk mOnPermissionDeniedWithNeverAsk;

    private PermissionHandle(Activity activity, String[] permissions,OnPermissionGranted mOnPermissionGranted,
                             OnpermissionRationale mOnpermissionRationale, OnPermissionDeniedWithNeverAsk mOnPermissionDeniedWithNeverAsk) {
        this.activity = activity;
        this.permissions=permissions;
        this.mOnPermissionGranted = mOnPermissionGranted;
        this.mOnpermissionRationale = mOnpermissionRationale;
        this.mOnPermissionDeniedWithNeverAsk = mOnPermissionDeniedWithNeverAsk;
    }

    public static PermissionBuilder builder() {
        return new PermissionBuilder();
    }

    public static class PermissionBuilder {
        private Activity activity;
        private String[] permissions;
        private OnPermissionGranted mOnPermissionGranted;
        private OnpermissionRationale mOnpermissionRationale;
        private OnPermissionDeniedWithNeverAsk mOnPermissionDeniedWithNeverAsk;

        public PermissionBuilder withActivity(Activity activity) {
            this.activity = activity;
            return this;
        }

        public PermissionBuilder withPermissions(String ...permissions) {
            this.permissions = permissions;
            return this;
        }


        public PermissionBuilder withOnPermissionGranted(OnPermissionGranted mOnPermissionGranted) {
            this.mOnPermissionGranted = mOnPermissionGranted;
            return this;
        }

        public PermissionBuilder withOnpermissionRationale(OnpermissionRationale mOnpermissionRationale) {
            this.mOnpermissionRationale = mOnpermissionRationale;
            return this;
        }

        public PermissionBuilder withOnPermissionDeniedWithNeverAsk(OnPermissionDeniedWithNeverAsk mOnPermissionDeniedWithNeverAsk) {
            this.mOnPermissionDeniedWithNeverAsk = mOnPermissionDeniedWithNeverAsk;
            return this;
        }

        public PermissionHandle build() {
            return new PermissionHandle(activity,permissions, mOnPermissionGranted, mOnpermissionRationale, mOnPermissionDeniedWithNeverAsk);
        }

    }

    public void permissionCheck() {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions
                .requestEach(permissions)
                .subscribe(permission -> {
                    if (permission.granted) {
                        if (mOnPermissionGranted != null) {
                            mOnPermissionGranted.onGranted();
                        }
                    } else if (permission.shouldShowRequestPermissionRationale) {
                        if (mOnpermissionRationale != null) {
                            mOnpermissionRationale.showRequestPermissionRationale();
                        }
                    } else {
                        if (mOnPermissionDeniedWithNeverAsk != null) {
                            mOnPermissionDeniedWithNeverAsk.deniedWithNeverAsk();
                        }
                    }
                });
    }
}
