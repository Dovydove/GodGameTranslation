package org.andengine.p028ui.activity;

import org.andengine.opengl.view.RenderSurfaceView;

/* renamed from: org.andengine.ui.activity.LayoutGameActivity */
public abstract class LayoutGameActivity extends BaseGameActivity {
    /* access modifiers changed from: protected */
    public abstract int getLayoutID();

    /* access modifiers changed from: protected */
    public abstract int getRenderSurfaceViewID();

    /* access modifiers changed from: protected */
    public void onSetContentView() {
        super.setContentView(getLayoutID());
        this.mRenderSurfaceView = (RenderSurfaceView) findViewById(getRenderSurfaceViewID());
        this.mRenderSurfaceView.setRenderer(this.mEngine, this);
    }
}
