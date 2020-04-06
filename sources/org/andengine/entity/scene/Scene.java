package org.andengine.entity.scene;

import android.util.SparseArray;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.runnable.RunnableHandler;
import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.entity.scene.ITouchArea.ITouchAreaMatcher;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.background.IBackground;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;
import org.andengine.util.adt.list.SmartList;
import org.andengine.util.color.Color;

public class Scene extends Entity {
    private static final int TOUCHAREAS_CAPACITY_DEFAULT = 4;
    private IBackground mBackground = new Background(Color.BLACK);
    private boolean mBackgroundEnabled = true;
    protected Scene mChildScene;
    private boolean mChildSceneModalDraw;
    private boolean mChildSceneModalTouch;
    private boolean mChildSceneModalUpdate;
    private IOnAreaTouchListener mOnAreaTouchListener;
    private boolean mOnAreaTouchTraversalBackToFront = true;
    private IOnSceneTouchListener mOnSceneTouchListener;
    private boolean mOnSceneTouchListenerBindingOnActionDownEnabled = false;
    private final SparseArray<IOnSceneTouchListener> mOnSceneTouchListenerBindings = new SparseArray<>();
    protected Scene mParentScene;
    private final RunnableHandler mRunnableHandler = new RunnableHandler();
    private float mSecondsElapsedTotal;
    private boolean mTouchAreaBindingOnActionDownEnabled = false;
    private boolean mTouchAreaBindingOnActionMoveEnabled = false;
    private final SparseArray<ITouchArea> mTouchAreaBindings = new SparseArray<>();
    protected SmartList<ITouchArea> mTouchAreas = new SmartList<>(4);

    public Scene() {
    }

    @Deprecated
    public Scene(int pChildCount) {
        for (int i = 0; i < pChildCount; i++) {
            attachChild(new Entity());
        }
    }

    public float getSecondsElapsedTotal() {
        return this.mSecondsElapsedTotal;
    }

    public IBackground getBackground() {
        return this.mBackground;
    }

    public void setBackground(IBackground pBackground) {
        this.mBackground = pBackground;
    }

    public boolean isBackgroundEnabled() {
        return this.mBackgroundEnabled;
    }

    public void setBackgroundEnabled(boolean pEnabled) {
        this.mBackgroundEnabled = pEnabled;
    }

    public void setOnSceneTouchListener(IOnSceneTouchListener pOnSceneTouchListener) {
        this.mOnSceneTouchListener = pOnSceneTouchListener;
    }

    public IOnSceneTouchListener getOnSceneTouchListener() {
        return this.mOnSceneTouchListener;
    }

    public boolean hasOnSceneTouchListener() {
        return this.mOnSceneTouchListener != null;
    }

    public void setOnAreaTouchListener(IOnAreaTouchListener pOnAreaTouchListener) {
        this.mOnAreaTouchListener = pOnAreaTouchListener;
    }

    public IOnAreaTouchListener getOnAreaTouchListener() {
        return this.mOnAreaTouchListener;
    }

    public boolean hasOnAreaTouchListener() {
        return this.mOnAreaTouchListener != null;
    }

    private void setParentScene(Scene pParentScene) {
        this.mParentScene = pParentScene;
    }

    public boolean hasChildScene() {
        return this.mChildScene != null;
    }

    public Scene getChildScene() {
        return this.mChildScene;
    }

    public void setChildSceneModal(Scene pChildScene) {
        setChildScene(pChildScene, true, true, true);
    }

    public void setChildScene(Scene pChildScene) {
        setChildScene(pChildScene, false, false, false);
    }

    public void setChildScene(Scene pChildScene, boolean pModalDraw, boolean pModalUpdate, boolean pModalTouch) {
        pChildScene.setParentScene(this);
        this.mChildScene = pChildScene;
        this.mChildSceneModalDraw = pModalDraw;
        this.mChildSceneModalUpdate = pModalUpdate;
        this.mChildSceneModalTouch = pModalTouch;
    }

    public void clearChildScene() {
        this.mChildScene = null;
    }

    public void setOnAreaTouchTraversalBackToFront() {
        this.mOnAreaTouchTraversalBackToFront = true;
    }

    public void setOnAreaTouchTraversalFrontToBack() {
        this.mOnAreaTouchTraversalBackToFront = false;
    }

    public boolean isTouchAreaBindingOnActionDownEnabled() {
        return this.mTouchAreaBindingOnActionDownEnabled;
    }

    public boolean isTouchAreaBindingOnActionMoveEnabled() {
        return this.mTouchAreaBindingOnActionMoveEnabled;
    }

    public void setTouchAreaBindingOnActionDownEnabled(boolean pTouchAreaBindingOnActionDownEnabled) {
        if (this.mTouchAreaBindingOnActionDownEnabled && !pTouchAreaBindingOnActionDownEnabled) {
            this.mTouchAreaBindings.clear();
        }
        this.mTouchAreaBindingOnActionDownEnabled = pTouchAreaBindingOnActionDownEnabled;
    }

    public void setTouchAreaBindingOnActionMoveEnabled(boolean pTouchAreaBindingOnActionMoveEnabled) {
        if (this.mTouchAreaBindingOnActionMoveEnabled && !pTouchAreaBindingOnActionMoveEnabled) {
            this.mTouchAreaBindings.clear();
        }
        this.mTouchAreaBindingOnActionMoveEnabled = pTouchAreaBindingOnActionMoveEnabled;
    }

    public boolean isOnSceneTouchListenerBindingOnActionDownEnabled() {
        return this.mOnSceneTouchListenerBindingOnActionDownEnabled;
    }

    public void setOnSceneTouchListenerBindingOnActionDownEnabled(boolean pOnSceneTouchListenerBindingOnActionDownEnabled) {
        if (this.mOnSceneTouchListenerBindingOnActionDownEnabled && !pOnSceneTouchListenerBindingOnActionDownEnabled) {
            this.mOnSceneTouchListenerBindings.clear();
        }
        this.mOnSceneTouchListenerBindingOnActionDownEnabled = pOnSceneTouchListenerBindingOnActionDownEnabled;
    }

    /* access modifiers changed from: protected */
    public void onManagedDraw(GLState pGLState, Camera pCamera) {
        Scene childScene = this.mChildScene;
        if (childScene == null || !this.mChildSceneModalDraw) {
            if (this.mBackgroundEnabled) {
                pGLState.pushProjectionGLMatrix();
                pCamera.onApplySceneBackgroundMatrix(pGLState);
                pGLState.loadModelViewGLMatrixIdentity();
                this.mBackground.myDraw(pGLState, pCamera);
                pGLState.popProjectionGLMatrix();
            }
            pGLState.pushProjectionGLMatrix();
            onApplyMatrix(pGLState, pCamera);
            pGLState.loadModelViewGLMatrixIdentity();
            super.onManagedDraw(pGLState, pCamera);
            pGLState.popProjectionGLMatrix();
        }
        if (childScene != null) {
            childScene.myDraw(pGLState, pCamera);
        }
    }

    /* access modifiers changed from: protected */
    public void onApplyMatrix(GLState pGLState, Camera pCamera) {
        pCamera.onApplySceneMatrix(pGLState);
    }

    /* access modifiers changed from: protected */
    public void onManagedUpdate(float pSecondsElapsed) {
        this.mSecondsElapsedTotal += pSecondsElapsed;
        this.mRunnableHandler.onUpdate(pSecondsElapsed);
        Scene childScene = this.mChildScene;
        if (childScene == null || !this.mChildSceneModalUpdate) {
            this.mBackground.onUpdate(pSecondsElapsed);
            super.onManagedUpdate(pSecondsElapsed);
        }
        if (childScene != null) {
            childScene.onUpdate(pSecondsElapsed);
        }
    }

    public boolean onSceneTouchEvent(TouchEvent pSceneTouchEvent) {
        int action = pSceneTouchEvent.getAction();
        boolean isActionDown = pSceneTouchEvent.isActionDown();
        boolean isActionMove = pSceneTouchEvent.isActionMove();
        if (!isActionDown) {
            if (this.mOnSceneTouchListenerBindingOnActionDownEnabled && ((IOnSceneTouchListener) this.mOnSceneTouchListenerBindings.get(pSceneTouchEvent.getPointerID())) != null) {
                switch (action) {
                    case 1:
                    case 3:
                        this.mOnSceneTouchListenerBindings.remove(pSceneTouchEvent.getPointerID());
                        break;
                }
                Boolean handled = Boolean.valueOf(this.mOnSceneTouchListener.onSceneTouchEvent(this, pSceneTouchEvent));
                if (handled != null && handled.booleanValue()) {
                    return true;
                }
            }
            if (this.mTouchAreaBindingOnActionDownEnabled) {
                SparseArray<ITouchArea> touchAreaBindings = this.mTouchAreaBindings;
                ITouchArea boundTouchArea = (ITouchArea) touchAreaBindings.get(pSceneTouchEvent.getPointerID());
                if (boundTouchArea != null) {
                    float sceneTouchEventX = pSceneTouchEvent.getX();
                    float sceneTouchEventY = pSceneTouchEvent.getY();
                    switch (action) {
                        case 1:
                        case 3:
                            touchAreaBindings.remove(pSceneTouchEvent.getPointerID());
                            break;
                    }
                    Boolean handled2 = onAreaTouchEvent(pSceneTouchEvent, sceneTouchEventX, sceneTouchEventY, boundTouchArea);
                    if (handled2 != null && handled2.booleanValue()) {
                        return true;
                    }
                }
            }
        }
        if (this.mChildScene != null) {
            if (onChildSceneTouchEvent(pSceneTouchEvent)) {
                return true;
            }
            if (this.mChildSceneModalTouch) {
                return false;
            }
        }
        float sceneTouchEventX2 = pSceneTouchEvent.getX();
        float sceneTouchEventY2 = pSceneTouchEvent.getY();
        SmartList<ITouchArea> touchAreas = this.mTouchAreas;
        if (touchAreas != null) {
            int touchAreaCount = touchAreas.size();
            if (touchAreaCount > 0) {
                if (this.mOnAreaTouchTraversalBackToFront) {
                    for (int i = 0; i < touchAreaCount; i++) {
                        ITouchArea touchArea = (ITouchArea) touchAreas.get(i);
                        if (touchArea.contains(sceneTouchEventX2, sceneTouchEventY2)) {
                            Boolean handled3 = onAreaTouchEvent(pSceneTouchEvent, sceneTouchEventX2, sceneTouchEventY2, touchArea);
                            if (handled3 != null && handled3.booleanValue()) {
                                if ((this.mTouchAreaBindingOnActionDownEnabled && isActionDown) || (this.mTouchAreaBindingOnActionMoveEnabled && isActionMove)) {
                                    this.mTouchAreaBindings.put(pSceneTouchEvent.getPointerID(), touchArea);
                                }
                                return true;
                            }
                        }
                    }
                } else {
                    for (int i2 = touchAreaCount - 1; i2 >= 0; i2--) {
                        ITouchArea touchArea2 = (ITouchArea) touchAreas.get(i2);
                        if (touchArea2.contains(sceneTouchEventX2, sceneTouchEventY2)) {
                            Boolean handled4 = onAreaTouchEvent(pSceneTouchEvent, sceneTouchEventX2, sceneTouchEventY2, touchArea2);
                            if (handled4 != null && handled4.booleanValue()) {
                                if ((this.mTouchAreaBindingOnActionDownEnabled && isActionDown) || (this.mTouchAreaBindingOnActionMoveEnabled && isActionMove)) {
                                    this.mTouchAreaBindings.put(pSceneTouchEvent.getPointerID(), touchArea2);
                                }
                                return true;
                            }
                        }
                    }
                }
            }
        }
        if (this.mOnSceneTouchListener == null) {
            return false;
        }
        Boolean handled5 = Boolean.valueOf(this.mOnSceneTouchListener.onSceneTouchEvent(this, pSceneTouchEvent));
        if (handled5 == null || !handled5.booleanValue()) {
            return false;
        }
        if (this.mOnSceneTouchListenerBindingOnActionDownEnabled && isActionDown) {
            this.mOnSceneTouchListenerBindings.put(pSceneTouchEvent.getPointerID(), this.mOnSceneTouchListener);
        }
        return true;
    }

    private Boolean onAreaTouchEvent(TouchEvent pSceneTouchEvent, float sceneTouchEventX, float sceneTouchEventY, ITouchArea touchArea) {
        float[] touchAreaLocalCoordinates = touchArea.convertSceneToLocalCoordinates(sceneTouchEventX, sceneTouchEventY);
        float touchAreaLocalX = touchAreaLocalCoordinates[0];
        float touchAreaLocalY = touchAreaLocalCoordinates[1];
        if (touchArea.onAreaTouched(pSceneTouchEvent, touchAreaLocalX, touchAreaLocalY)) {
            return Boolean.TRUE;
        }
        if (this.mOnAreaTouchListener != null) {
            return Boolean.valueOf(this.mOnAreaTouchListener.onAreaTouched(pSceneTouchEvent, touchArea, touchAreaLocalX, touchAreaLocalY));
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean onChildSceneTouchEvent(TouchEvent pSceneTouchEvent) {
        return this.mChildScene.onSceneTouchEvent(pSceneTouchEvent);
    }

    public void reset() {
        super.reset();
        clearChildScene();
    }

    public void setParent(IEntity pEntity) {
    }

    public void postRunnable(Runnable pRunnable) {
        this.mRunnableHandler.postRunnable(pRunnable);
    }

    public void registerTouchArea(ITouchArea pTouchArea) {
        this.mTouchAreas.add(pTouchArea);
    }

    public boolean unregisterTouchArea(ITouchArea pTouchArea) {
        return this.mTouchAreas.remove(pTouchArea);
    }

    public boolean unregisterTouchAreas(ITouchAreaMatcher pTouchAreaMatcher) {
        return this.mTouchAreas.removeAll(pTouchAreaMatcher);
    }

    public void clearTouchAreas() {
        this.mTouchAreas.clear();
    }

    public SmartList<ITouchArea> getTouchAreas() {
        return this.mTouchAreas;
    }

    public void back() {
        clearChildScene();
        if (this.mParentScene != null) {
            this.mParentScene.clearChildScene();
            this.mParentScene = null;
        }
    }
}
