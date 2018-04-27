package com.linxz.core.utils.dimen;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.linxz.core.app.Linxz;

/**
 * @author linxz
 */

public final class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Linxz.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Linxz.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }

    /**
     * dp2px
     */
    public static int dip2px(float dipValue) {
        return (int) (dipValue * getDensity(Linxz.getApplicationContext()) + 0.5f);
    }

    /**
     * 获取当前手机的独立像素
     *
     * @param context
     * @return
     */
    private static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }
}
