package org.andengine.entity.util;

public class FPSLogger extends AverageFPSCounter {
    protected float mLongestFrame = Float.MIN_VALUE;
    protected float mShortestFrame = Float.MAX_VALUE;

    public FPSLogger() {
    }

    public FPSLogger(float pAverageDuration) {
        super(pAverageDuration);
    }

    /* access modifiers changed from: protected */
    public void onHandleAverageDurationElapsed(float pFPS) {
        onLogFPS();
        this.mLongestFrame = Float.MIN_VALUE;
        this.mShortestFrame = Float.MAX_VALUE;
    }

    public void onUpdate(float pSecondsElapsed) {
        super.onUpdate(pSecondsElapsed);
        this.mShortestFrame = Math.min(this.mShortestFrame, pSecondsElapsed);
        this.mLongestFrame = Math.max(this.mLongestFrame, pSecondsElapsed);
    }

    public void reset() {
        super.reset();
        this.mShortestFrame = Float.MAX_VALUE;
        this.mLongestFrame = Float.MIN_VALUE;
    }

    /* access modifiers changed from: protected */
    public void onLogFPS() {
    }
}
