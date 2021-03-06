package org.andengine.util.modifier.ease;

import android.util.FloatMath;

public class EaseSineOut implements IEaseFunction {
    private static EaseSineOut INSTANCE;

    private EaseSineOut() {
    }

    public static EaseSineOut getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EaseSineOut();
        }
        return INSTANCE;
    }

    public float getPercentage(float pSecondsElapsed, float pDuration) {
        return getValue(pSecondsElapsed / pDuration);
    }

    public static float getValue(float pPercentage) {
        return FloatMath.sin(1.5707964f * pPercentage);
    }
}
