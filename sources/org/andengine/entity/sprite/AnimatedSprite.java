package org.andengine.entity.sprite;

import org.andengine.entity.sprite.vbo.ITiledSpriteVertexBufferObject;
import org.andengine.opengl.shader.PositionColorTextureCoordinatesShaderProgram;
import org.andengine.opengl.shader.ShaderProgram;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.DrawType;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class AnimatedSprite extends TiledSprite {
    private static final int FRAMEINDEX_INVALID = -1;
    private final IAnimationData mAnimationData = new AnimationData();
    private IAnimationListener mAnimationListener;
    private long mAnimationProgress;
    private boolean mAnimationRunning;
    private boolean mAnimationStartedFired;
    private int mCurrentFrameIndex;
    private int mRemainingLoopCount;

    public interface IAnimationListener {
        void onAnimationFinished(AnimatedSprite animatedSprite);

        void onAnimationFrameChanged(AnimatedSprite animatedSprite, int i, int i2);

        void onAnimationLoopFinished(AnimatedSprite animatedSprite, int i, int i2);

        void onAnimationStarted(AnimatedSprite animatedSprite, int i);
    }

    public AnimatedSprite(float pX, float pY, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager, DrawType.DYNAMIC);
    }

    public AnimatedSprite(float pX, float pY, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager, ShaderProgram pShaderProgram) {
        super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager, DrawType.DYNAMIC, pShaderProgram);
    }

    public AnimatedSprite(float pX, float pY, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager, DrawType pDrawType) {
        super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager, pDrawType);
    }

    public AnimatedSprite(float pX, float pY, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager, DrawType pDrawType, ShaderProgram pShaderProgram) {
        super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager, pDrawType, pShaderProgram);
    }

    public AnimatedSprite(float pX, float pY, ITiledTextureRegion pTiledTextureRegion, ITiledSpriteVertexBufferObject pTiledSpriteVertexBufferObject) {
        super(pX, pY, pTiledTextureRegion, pTiledSpriteVertexBufferObject);
    }

    public AnimatedSprite(float pX, float pY, ITiledTextureRegion pTiledTextureRegion, ITiledSpriteVertexBufferObject pTiledSpriteVertexBufferObject, ShaderProgram pShaderProgram) {
        super(pX, pY, pTiledTextureRegion, pTiledSpriteVertexBufferObject, pShaderProgram);
    }

    public AnimatedSprite(float pX, float pY, float pWidth, float pHeight, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager) {
        super(pX, pY, pWidth, pHeight, pTiledTextureRegion, pVertexBufferObjectManager, DrawType.DYNAMIC);
    }

    public AnimatedSprite(float pX, float pY, float pWidth, float pHeight, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager, ShaderProgram pShaderProgram) {
        super(pX, pY, pWidth, pHeight, pTiledTextureRegion, pVertexBufferObjectManager, DrawType.DYNAMIC, pShaderProgram);
    }

    public AnimatedSprite(float pX, float pY, float pWidth, float pHeight, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager, DrawType pDrawType) {
        super(pX, pY, pWidth, pHeight, pTiledTextureRegion, pVertexBufferObjectManager, pDrawType);
    }

    public AnimatedSprite(float pX, float pY, float pWidth, float pHeight, ITiledTextureRegion pTiledTextureRegion, VertexBufferObjectManager pVertexBufferObjectManager, DrawType pDrawType, ShaderProgram pShaderProgram) {
        super(pX, pY, pWidth, pHeight, pTiledTextureRegion, pVertexBufferObjectManager, pDrawType, pShaderProgram);
    }

    public AnimatedSprite(float pX, float pY, float pWidth, float pHeight, ITiledTextureRegion pTiledTextureRegion, ITiledSpriteVertexBufferObject pTiledSpriteVertexBufferObject) {
        super(pX, pY, pWidth, pHeight, pTiledTextureRegion, pTiledSpriteVertexBufferObject, (ShaderProgram) PositionColorTextureCoordinatesShaderProgram.getInstance());
    }

    public AnimatedSprite(float pX, float pY, float pWidth, float pHeight, ITiledTextureRegion pTiledTextureRegion, ITiledSpriteVertexBufferObject pTiledSpriteVertexBufferObject, ShaderProgram pShaderProgram) {
        super(pX, pY, pWidth, pHeight, pTiledTextureRegion, pTiledSpriteVertexBufferObject, pShaderProgram);
    }

    public boolean isAnimationRunning() {
        return this.mAnimationRunning;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: CFG modification limit reached, blocks count: 148 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onManagedUpdate(float r15) {
        /*
            r14 = this;
            r13 = -1
            r12 = 0
            super.onManagedUpdate(r15)
            boolean r7 = r14.mAnimationRunning
            if (r7 == 0) goto L_0x00b9
            org.andengine.entity.sprite.IAnimationData r7 = r14.mAnimationData
            int r3 = r7.getLoopCount()
            org.andengine.entity.sprite.IAnimationData r7 = r14.mAnimationData
            int[] r2 = r7.getFrames()
            org.andengine.entity.sprite.IAnimationData r7 = r14.mAnimationData
            long r0 = r7.getAnimationDuration()
            boolean r7 = r14.mAnimationStartedFired
            if (r7 != 0) goto L_0x0045
            long r8 = r14.mAnimationProgress
            r10 = 0
            int r7 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
            if (r7 != 0) goto L_0x0045
            r7 = 1
            r14.mAnimationStartedFired = r7
            if (r2 != 0) goto L_0x0068
            org.andengine.entity.sprite.IAnimationData r7 = r14.mAnimationData
            int r7 = r7.getFirstFrameIndex()
            r14.setCurrentTileIndex(r7)
        L_0x0035:
            r14.mCurrentFrameIndex = r12
            org.andengine.entity.sprite.AnimatedSprite$IAnimationListener r7 = r14.mAnimationListener
            if (r7 == 0) goto L_0x0045
            org.andengine.entity.sprite.AnimatedSprite$IAnimationListener r7 = r14.mAnimationListener
            r7.onAnimationStarted(r14, r3)
            org.andengine.entity.sprite.AnimatedSprite$IAnimationListener r7 = r14.mAnimationListener
            r7.onAnimationFrameChanged(r14, r13, r12)
        L_0x0045:
            r7 = 1315859240(0x4e6e6b28, float:1.0E9)
            float r7 = r7 * r15
            long r4 = (long) r7
            long r8 = r14.mAnimationProgress
            long r8 = r8 + r4
            r14.mAnimationProgress = r8
            if (r3 != r13) goto L_0x0079
        L_0x0051:
            long r8 = r14.mAnimationProgress
            int r7 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r7 <= 0) goto L_0x008e
            long r8 = r14.mAnimationProgress
            long r8 = r8 - r0
            r14.mAnimationProgress = r8
            org.andengine.entity.sprite.AnimatedSprite$IAnimationListener r7 = r14.mAnimationListener
            if (r7 == 0) goto L_0x0051
            org.andengine.entity.sprite.AnimatedSprite$IAnimationListener r7 = r14.mAnimationListener
            int r8 = r14.mRemainingLoopCount
            r7.onAnimationLoopFinished(r14, r8, r3)
            goto L_0x0051
        L_0x0068:
            r7 = r2[r12]
            r14.setCurrentTileIndex(r7)
            goto L_0x0035
        L_0x006e:
            org.andengine.entity.sprite.AnimatedSprite$IAnimationListener r7 = r14.mAnimationListener
            if (r7 == 0) goto L_0x0079
            org.andengine.entity.sprite.AnimatedSprite$IAnimationListener r7 = r14.mAnimationListener
            int r8 = r14.mRemainingLoopCount
            r7.onAnimationLoopFinished(r14, r8, r3)
        L_0x0079:
            long r8 = r14.mAnimationProgress
            int r7 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r7 <= 0) goto L_0x008e
            long r8 = r14.mAnimationProgress
            long r8 = r8 - r0
            r14.mAnimationProgress = r8
            int r7 = r14.mRemainingLoopCount
            int r7 = r7 + -1
            r14.mRemainingLoopCount = r7
            int r7 = r14.mRemainingLoopCount
            if (r7 >= 0) goto L_0x006e
        L_0x008e:
            if (r3 == r13) goto L_0x0094
            int r7 = r14.mRemainingLoopCount
            if (r7 < 0) goto L_0x00c0
        L_0x0094:
            org.andengine.entity.sprite.IAnimationData r7 = r14.mAnimationData
            long r8 = r14.mAnimationProgress
            int r6 = r7.calculateCurrentFrameIndex(r8)
            int r7 = r14.mCurrentFrameIndex
            if (r7 == r6) goto L_0x00b7
            if (r2 != 0) goto L_0x00ba
            org.andengine.entity.sprite.IAnimationData r7 = r14.mAnimationData
            int r7 = r7.getFirstFrameIndex()
            int r7 = r7 + r6
            r14.setCurrentTileIndex(r7)
        L_0x00ac:
            org.andengine.entity.sprite.AnimatedSprite$IAnimationListener r7 = r14.mAnimationListener
            if (r7 == 0) goto L_0x00b7
            org.andengine.entity.sprite.AnimatedSprite$IAnimationListener r7 = r14.mAnimationListener
            int r8 = r14.mCurrentFrameIndex
            r7.onAnimationFrameChanged(r14, r8, r6)
        L_0x00b7:
            r14.mCurrentFrameIndex = r6
        L_0x00b9:
            return
        L_0x00ba:
            r7 = r2[r6]
            r14.setCurrentTileIndex(r7)
            goto L_0x00ac
        L_0x00c0:
            r14.mAnimationRunning = r12
            org.andengine.entity.sprite.AnimatedSprite$IAnimationListener r7 = r14.mAnimationListener
            if (r7 == 0) goto L_0x00b9
            org.andengine.entity.sprite.AnimatedSprite$IAnimationListener r7 = r14.mAnimationListener
            r7.onAnimationFinished(r14)
            goto L_0x00b9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.andengine.entity.sprite.AnimatedSprite.onManagedUpdate(float):void");
    }

    public void stopAnimation() {
        this.mAnimationRunning = false;
    }

    public void stopAnimation(int pTileIndex) {
        this.mAnimationRunning = false;
        setCurrentTileIndex(pTileIndex);
    }

    public void animate(long pFrameDurationEach) {
        animate(pFrameDurationEach, (IAnimationListener) null);
    }

    public void animate(long pFrameDurationEach, IAnimationListener pAnimationListener) {
        this.mAnimationData.set(pFrameDurationEach, getTileCount());
        initAnimation(pAnimationListener);
    }

    public void animate(long pFrameDurationEach, boolean pLoop) {
        animate(pFrameDurationEach, pLoop, (IAnimationListener) null);
    }

    public void animate(long pFrameDurationEach, boolean pLoop, IAnimationListener pAnimationListener) {
        this.mAnimationData.set(pFrameDurationEach, getTileCount(), pLoop);
        initAnimation(pAnimationListener);
    }

    public void animate(long pFrameDurationEach, int pLoopCount) {
        animate(pFrameDurationEach, pLoopCount, (IAnimationListener) null);
    }

    public void animate(long pFrameDurationEach, int pLoopCount, IAnimationListener pAnimationListener) {
        this.mAnimationData.set(pFrameDurationEach, getTileCount(), pLoopCount);
        initAnimation(pAnimationListener);
    }

    public void animate(long[] pFrameDurations) {
        animate(pFrameDurations, (IAnimationListener) null);
    }

    public void animate(long[] pFrameDurations, IAnimationListener pAnimationListener) {
        this.mAnimationData.set(pFrameDurations);
        initAnimation(pAnimationListener);
    }

    public void animate(long[] pFrameDurations, boolean pLoop) {
        animate(pFrameDurations, pLoop, (IAnimationListener) null);
    }

    public void animate(long[] pFrameDurations, boolean pLoop, IAnimationListener pAnimationListener) {
        this.mAnimationData.set(pFrameDurations, pLoop);
        initAnimation(pAnimationListener);
    }

    public void animate(long[] pFrameDurations, int pLoopCount) {
        animate(pFrameDurations, pLoopCount, (IAnimationListener) null);
    }

    public void animate(long[] pFrameDurations, int pLoopCount, IAnimationListener pAnimationListener) {
        this.mAnimationData.set(pFrameDurations, pLoopCount);
        initAnimation(pAnimationListener);
    }

    public void animate(long[] pFrameDurations, int pFirstTileIndex, int pLastTileIndex, boolean pLoop) {
        animate(pFrameDurations, pFirstTileIndex, pLastTileIndex, pLoop, (IAnimationListener) null);
    }

    public void animate(long[] pFrameDurations, int pFirstTileIndex, int pLastTileIndex, boolean pLoop, IAnimationListener pAnimationListener) {
        this.mAnimationData.set(pFrameDurations, pFirstTileIndex, pLastTileIndex, pLoop);
        initAnimation(pAnimationListener);
    }

    public void animate(long[] pFrameDurations, int pFirstTileIndex, int pLastTileIndex, int pLoopCount) {
        animate(pFrameDurations, pFirstTileIndex, pLastTileIndex, pLoopCount, (IAnimationListener) null);
    }

    public void animate(long[] pFrameDurations, int pFirstTileIndex, int pLastTileIndex, int pLoopCount, IAnimationListener pAnimationListener) {
        this.mAnimationData.set(pFrameDurations, pFirstTileIndex, pLastTileIndex, pLoopCount);
        initAnimation(pAnimationListener);
    }

    public void animate(long[] pFrameDurations, int[] pFrames) {
        animate(pFrameDurations, pFrames, (IAnimationListener) null);
    }

    public void animate(long[] pFrameDurations, int[] pFrames, IAnimationListener pAnimationListener) {
        this.mAnimationData.set(pFrameDurations, pFrames);
        initAnimation(pAnimationListener);
    }

    public void animate(long[] pFrameDurations, int[] pFrames, boolean pLoop) {
        animate(pFrameDurations, pFrames, pLoop, (IAnimationListener) null);
    }

    public void animate(long[] pFrameDurations, int[] pFrames, boolean pLoop, IAnimationListener pAnimationListener) {
        this.mAnimationData.set(pFrameDurations, pFrames, pLoop);
        initAnimation(pAnimationListener);
    }

    public void animate(long[] pFrameDurations, int[] pFrames, int pLoopCount) {
        animate(pFrameDurations, pFrames, pLoopCount, (IAnimationListener) null);
    }

    public void animate(long[] pFrameDurations, int[] pFrames, int pLoopCount, IAnimationListener pAnimationListener) {
        this.mAnimationData.set(pFrameDurations, pFrames, pLoopCount);
        initAnimation(pAnimationListener);
    }

    public void animate(IAnimationData pAnimationData) {
        animate(pAnimationData, (IAnimationListener) null);
    }

    public void animate(IAnimationData pAnimationData, IAnimationListener pAnimationListener) {
        this.mAnimationData.set(pAnimationData);
        initAnimation(pAnimationListener);
    }

    private void initAnimation(IAnimationListener pAnimationListener) {
        this.mAnimationStartedFired = false;
        this.mAnimationListener = pAnimationListener;
        this.mRemainingLoopCount = this.mAnimationData.getLoopCount();
        this.mAnimationProgress = 0;
        this.mAnimationRunning = true;
    }
}
