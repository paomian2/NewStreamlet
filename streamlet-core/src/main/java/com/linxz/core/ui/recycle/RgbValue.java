package com.linxz.core.ui.recycle;

import com.google.auto.value.AutoValue;

/**
 * @author linxz
 */

@AutoValue
public abstract class RgbValue {

    public abstract int red();

    public abstract int green();

    public abstract int blue();

    public static RgbValue create(int red, int green, int blue) {
        return new AutoValue_RgbValue(red, green, blue);
    }
}
