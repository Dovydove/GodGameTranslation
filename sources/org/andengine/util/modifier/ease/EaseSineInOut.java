package org.andengine.util.modifier.ease;

import android.util.FloatMath;

public class EaseSineInOut implements IEaseFunction {
    private static EaseSineInOut INSTANCE;

    private EaseSineInOut() {
    }

    public static EaseSineInOut getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EaseSineInOut();
        }
        return INSTANCE;
    }

    public float getPercentage(float pSecondsElapsed, float pDuration) {
        return -0.5f * (FloatMath.cos(3.1415927f * (pSecondsElapsed / pDuration)) - 1.0f);
    }
}
