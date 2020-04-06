package org.andengine.p028ui.activity;

import org.andengine.engine.Engine;
import org.andengine.engine.options.EngineOptions;
import org.andengine.entity.scene.Scene;
import org.andengine.p028ui.IGameInterface.OnCreateResourcesCallback;
import org.andengine.p028ui.IGameInterface.OnCreateSceneCallback;
import org.andengine.p028ui.IGameInterface.OnPopulateSceneCallback;

@Deprecated
/* renamed from: org.andengine.ui.activity.LegacyBaseGameActivity */
public abstract class LegacyBaseGameActivity extends BaseGameActivity {
    /* access modifiers changed from: protected */
    public abstract Scene onLoadComplete();

    /* access modifiers changed from: protected */
    public abstract Engine onLoadEngine();

    /* access modifiers changed from: protected */
    public abstract void onLoadResources();

    /* access modifiers changed from: protected */
    public abstract Scene onLoadScene();

    /* access modifiers changed from: protected */
    public abstract void onUnloadResources();

    public final EngineOptions onCreateEngineOptions() {
        return null;
    }

    public final Engine onCreateEngine(EngineOptions pEngineOptions) {
        return onLoadEngine();
    }

    public final void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws Exception {
        onLoadResources();
        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }

    public final void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws Exception {
        pOnCreateSceneCallback.onCreateSceneFinished(onLoadScene());
    }

    public final void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {
        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }

    public final void onDestroyResources() throws Exception {
        super.onDestroyResources();
        onUnloadResources();
    }

    public synchronized void onGameCreated() {
        super.onGameCreated();
        onLoadComplete();
    }
}
