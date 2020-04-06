package org.andengine.p028ui.activity;

import org.andengine.entity.scene.Scene;
import org.andengine.p028ui.IGameInterface.OnCreateResourcesCallback;
import org.andengine.p028ui.IGameInterface.OnCreateSceneCallback;
import org.andengine.p028ui.IGameInterface.OnPopulateSceneCallback;

/* renamed from: org.andengine.ui.activity.SimpleBaseGameActivity */
public abstract class SimpleBaseGameActivity extends BaseGameActivity {
    /* access modifiers changed from: protected */
    public abstract void onCreateResources();

    /* access modifiers changed from: protected */
    public abstract Scene onCreateScene();

    public final void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback) throws Exception {
        onCreateResources();
        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }

    public final void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback) throws Exception {
        pOnCreateSceneCallback.onCreateSceneFinished(onCreateScene());
    }

    public final void onPopulateScene(Scene pScene, OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {
        pOnPopulateSceneCallback.onPopulateSceneFinished();
    }
}
