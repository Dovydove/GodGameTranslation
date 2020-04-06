package org.andengine.util.algorithm;

import org.andengine.util.algorithm.path.Direction;

public class Spiral {
    private final int mCenterX;
    private final int mCenterY;
    private Direction mDirection;
    private int mDirectionSegmentIndex;
    private int mDirectionSegmentLength;
    private final int mStepSize;

    /* renamed from: mX */
    private int f1221mX;

    /* renamed from: mY */
    private int f1222mY;

    public Spiral(int pCenterX, int pCenterY) {
        this(pCenterX, pCenterY, 1);
    }

    public Spiral(int pCenterX, int pCenterY, int pStepSize) {
        this.mCenterX = pCenterX;
        this.mCenterY = pCenterY;
        this.mStepSize = pStepSize;
        this.f1221mX = pCenterX;
        this.f1222mY = pCenterY;
        this.mDirection = Direction.RIGHT;
        this.mDirectionSegmentLength = 1;
        this.mDirectionSegmentIndex = 0;
    }

    public int getCenterX() {
        return this.mCenterX;
    }

    public int getCenterY() {
        return this.mCenterY;
    }

    public int getX() {
        return this.f1221mX;
    }

    public int getY() {
        return this.f1222mY;
    }

    public void step() {
        this.f1221mX += this.mDirection.getDeltaX() * this.mStepSize;
        this.f1222mY += this.mDirection.getDeltaY() * this.mStepSize;
        this.mDirectionSegmentIndex++;
        if (this.mDirectionSegmentIndex == this.mDirectionSegmentLength) {
            this.mDirectionSegmentIndex = 0;
            this.mDirection = this.mDirection.rotateRight();
            if (this.mDirection.isHorizontal()) {
                this.mDirectionSegmentLength++;
            }
        }
    }
}
