package org.andengine.input.touch.detector;

import android.content.Context;

public class SurfaceGestureDetectorAdapter extends SurfaceGestureDetector {
    public SurfaceGestureDetectorAdapter(Context pContext) {
        super(pContext);
    }

    public SurfaceGestureDetectorAdapter(Context pContext, float pSwipeMinDistance) {
        super(pContext, pSwipeMinDistance);
    }

    /* access modifiers changed from: protected */
    public boolean onDoubleTap() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onSingleTap() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onSwipeDown() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onSwipeLeft() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onSwipeRight() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onSwipeUp() {
        return false;
    }
}
