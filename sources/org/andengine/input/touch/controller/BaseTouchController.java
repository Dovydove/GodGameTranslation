package org.andengine.input.touch.controller;

import android.view.MotionEvent;
import org.andengine.input.touch.TouchEvent;
import org.andengine.util.adt.pool.RunnablePoolItem;
import org.andengine.util.adt.pool.RunnablePoolUpdateHandler;

public abstract class BaseTouchController implements ITouchController {
    /* access modifiers changed from: private */
    public ITouchEventCallback mTouchEventCallback;
    private final RunnablePoolUpdateHandler<TouchEventRunnablePoolItem> mTouchEventRunnablePoolUpdateHandler = new RunnablePoolUpdateHandler<TouchEventRunnablePoolItem>() {
        /* access modifiers changed from: protected */
        public TouchEventRunnablePoolItem onAllocatePoolItem() {
            return new TouchEventRunnablePoolItem();
        }
    };

    class TouchEventRunnablePoolItem extends RunnablePoolItem {
        private TouchEvent mTouchEvent;

        TouchEventRunnablePoolItem() {
        }

        public void set(TouchEvent pTouchEvent) {
            this.mTouchEvent = pTouchEvent;
        }

        public void run() {
            BaseTouchController.this.mTouchEventCallback.onTouchEvent(this.mTouchEvent);
        }

        /* access modifiers changed from: protected */
        public void onRecycle() {
            super.onRecycle();
            TouchEvent touchEvent = this.mTouchEvent;
            touchEvent.getMotionEvent().recycle();
            touchEvent.recycle();
        }
    }

    public void setTouchEventCallback(ITouchEventCallback pTouchEventCallback) {
        this.mTouchEventCallback = pTouchEventCallback;
    }

    public void reset() {
        this.mTouchEventRunnablePoolUpdateHandler.reset();
    }

    public void onUpdate(float pSecondsElapsed) {
        this.mTouchEventRunnablePoolUpdateHandler.onUpdate(pSecondsElapsed);
    }

    /* access modifiers changed from: protected */
    public void fireTouchEvent(float pX, float pY, int pAction, int pPointerID, MotionEvent pMotionEvent) {
        TouchEventRunnablePoolItem touchEventRunnablePoolItem = (TouchEventRunnablePoolItem) this.mTouchEventRunnablePoolUpdateHandler.obtainPoolItem();
        touchEventRunnablePoolItem.set(TouchEvent.obtain(pX, pY, pAction, pPointerID, MotionEvent.obtain(pMotionEvent)));
        this.mTouchEventRunnablePoolUpdateHandler.postPoolItem(touchEventRunnablePoolItem);
    }
}
