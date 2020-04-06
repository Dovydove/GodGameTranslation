package org.andengine.input.touch.detector;

import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.detector.ScrollDetector.IScrollDetectorListener;

public class SurfaceScrollDetector extends ScrollDetector {
    public SurfaceScrollDetector(float pTriggerScrollMinimumDistance, IScrollDetectorListener pScrollDetectorListener) {
        super(pTriggerScrollMinimumDistance, pScrollDetectorListener);
    }

    public SurfaceScrollDetector(IScrollDetectorListener pScrollDetectorListener) {
        super(pScrollDetectorListener);
    }

    /* access modifiers changed from: protected */
    public float getX(TouchEvent pTouchEvent) {
        return pTouchEvent.getMotionEvent().getX();
    }

    /* access modifiers changed from: protected */
    public float getY(TouchEvent pTouchEvent) {
        return pTouchEvent.getMotionEvent().getY();
    }
}
