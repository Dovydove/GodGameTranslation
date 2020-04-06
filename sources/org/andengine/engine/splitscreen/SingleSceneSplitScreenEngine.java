package org.andengine.engine.splitscreen;

import android.opengl.GLES20;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;

public class SingleSceneSplitScreenEngine extends Engine {
    private final Camera mSecondCamera;

    public SingleSceneSplitScreenEngine(EngineOptions pEngineOptions, Camera pSecondCamera) {
        super(pEngineOptions);
        this.mSecondCamera = pSecondCamera;
    }

    @Deprecated
    public Camera getCamera() {
        return this.mCamera;
    }

    public Camera getFirstCamera() {
        return this.mCamera;
    }

    public Camera getSecondCamera() {
        return this.mSecondCamera;
    }

    /* access modifiers changed from: protected */
    public void onDrawScene(GLState pGLState, Camera pFirstCamera) {
        if (this.mScene != null) {
            Camera secondCamera = getSecondCamera();
            int surfaceWidthHalf = this.mSurfaceWidth >> 1;
            int surfaceHeight = this.mSurfaceHeight;
            pGLState.enableScissorTest();
            GLES20.glScissor(0, 0, surfaceWidthHalf, surfaceHeight);
            GLES20.glViewport(0, 0, surfaceWidthHalf, surfaceHeight);
            this.mScene.myDraw(pGLState, pFirstCamera);
            pFirstCamera.onDrawHUD(pGLState);
            GLES20.glScissor(surfaceWidthHalf, 0, surfaceWidthHalf, surfaceHeight);
            GLES20.glViewport(surfaceWidthHalf, 0, surfaceWidthHalf, surfaceHeight);
            this.mScene.myDraw(pGLState, secondCamera);
            secondCamera.onDrawHUD(pGLState);
            pGLState.disableScissorTest();
        }
    }

    /* access modifiers changed from: protected */
    public Camera getCameraFromSurfaceTouchEvent(TouchEvent pTouchEvent) {
        if (pTouchEvent.getX() <= ((float) (this.mSurfaceWidth >> 1))) {
            return getFirstCamera();
        }
        return getSecondCamera();
    }

    /* access modifiers changed from: protected */
    public void convertSurfaceToSceneTouchEvent(Camera pCamera, TouchEvent pSurfaceTouchEvent) {
        int surfaceWidthHalf = this.mSurfaceWidth >> 1;
        if (pCamera == getFirstCamera()) {
            pCamera.convertSurfaceToSceneTouchEvent(pSurfaceTouchEvent, surfaceWidthHalf, this.mSurfaceHeight);
            return;
        }
        pSurfaceTouchEvent.offset((float) (-surfaceWidthHalf), 0.0f);
        pCamera.convertSurfaceToSceneTouchEvent(pSurfaceTouchEvent, surfaceWidthHalf, this.mSurfaceHeight);
    }

    /* access modifiers changed from: protected */
    public void onUpdateUpdateHandlers(float pSecondsElapsed) {
        super.onUpdateUpdateHandlers(pSecondsElapsed);
        getSecondCamera().onUpdate(pSecondsElapsed);
    }

    /* access modifiers changed from: protected */
    public void onUpdateCameraSurface() {
        int surfaceWidthHalf = this.mSurfaceWidth >> 1;
        getFirstCamera().setSurfaceSize(0, 0, surfaceWidthHalf, this.mSurfaceHeight);
        getSecondCamera().setSurfaceSize(surfaceWidthHalf, 0, surfaceWidthHalf, this.mSurfaceHeight);
    }
}
