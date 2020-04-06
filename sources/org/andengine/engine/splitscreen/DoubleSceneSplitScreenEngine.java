package org.andengine.engine.splitscreen;

import android.opengl.GLES20;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;

public class DoubleSceneSplitScreenEngine extends Engine {
    private final Camera mSecondCamera;
    private Scene mSecondScene;

    public DoubleSceneSplitScreenEngine(EngineOptions pEngineOptions, Camera pSecondCamera) {
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

    @Deprecated
    public Scene getScene() {
        return super.getScene();
    }

    public Scene getFirstScene() {
        return super.getScene();
    }

    public Scene getSecondScene() {
        return this.mSecondScene;
    }

    @Deprecated
    public void setScene(Scene pScene) {
        setFirstScene(pScene);
    }

    public void setFirstScene(Scene pScene) {
        super.setScene(pScene);
    }

    public void setSecondScene(Scene pScene) {
        this.mSecondScene = pScene;
    }

    /* access modifiers changed from: protected */
    public void onDrawScene(GLState pGLState, Camera pFirstCamera) {
        Camera secondCamera = getSecondCamera();
        int surfaceWidthHalf = this.mSurfaceWidth >> 1;
        int surfaceHeight = this.mSurfaceHeight;
        pGLState.enableScissorTest();
        if (this.mScene != null) {
            GLES20.glScissor(0, 0, surfaceWidthHalf, surfaceHeight);
            GLES20.glViewport(0, 0, surfaceWidthHalf, surfaceHeight);
            this.mScene.myDraw(pGLState, pFirstCamera);
            pFirstCamera.onDrawHUD(pGLState);
        }
        if (this.mSecondScene != null) {
            GLES20.glScissor(surfaceWidthHalf, 0, surfaceWidthHalf, surfaceHeight);
            GLES20.glViewport(surfaceWidthHalf, 0, surfaceWidthHalf, surfaceHeight);
            this.mSecondScene.myDraw(pGLState, secondCamera);
            secondCamera.onDrawHUD(pGLState);
        }
        pGLState.disableScissorTest();
    }

    /* access modifiers changed from: protected */
    public Camera getCameraFromSurfaceTouchEvent(TouchEvent pTouchEvent) {
        if (pTouchEvent.getX() <= ((float) (this.mSurfaceWidth >> 1))) {
            return getFirstCamera();
        }
        return getSecondCamera();
    }

    /* access modifiers changed from: protected */
    public Scene getSceneFromSurfaceTouchEvent(TouchEvent pTouchEvent) {
        if (pTouchEvent.getX() <= ((float) (this.mSurfaceWidth >> 1))) {
            return getFirstScene();
        }
        return getSecondScene();
    }

    /* access modifiers changed from: protected */
    public void onUpdateScene(float pSecondsElapsed) {
        super.onUpdateScene(pSecondsElapsed);
        if (this.mSecondScene != null) {
            this.mSecondScene.onUpdate(pSecondsElapsed);
        }
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
