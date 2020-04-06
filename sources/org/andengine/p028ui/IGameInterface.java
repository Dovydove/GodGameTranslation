package org.andengine.p028ui;

import org.andengine.engine.Engine;
import org.andengine.engine.options.EngineOptions;
import org.andengine.entity.scene.Scene;

/* renamed from: org.andengine.ui.IGameInterface */
public interface IGameInterface {

    /* renamed from: org.andengine.ui.IGameInterface$OnCreateResourcesCallback */
    public interface OnCreateResourcesCallback {
        void onCreateResourcesFinished();
    }

    /* renamed from: org.andengine.ui.IGameInterface$OnCreateSceneCallback */
    public interface OnCreateSceneCallback {
        void onCreateSceneFinished(Scene scene);
    }

    /* renamed from: org.andengine.ui.IGameInterface$OnPopulateSceneCallback */
    public interface OnPopulateSceneCallback {
        void onPopulateSceneFinished();
    }

    Engine onCreateEngine(EngineOptions engineOptions);

    EngineOptions onCreateEngineOptions();

    void onCreateResources(OnCreateResourcesCallback onCreateResourcesCallback) throws Exception;

    void onCreateScene(OnCreateSceneCallback onCreateSceneCallback) throws Exception;

    void onDestroyResources() throws Exception;

    void onGameCreated();

    void onGameDestroyed();

    void onPauseGame();

    void onPopulateScene(Scene scene, OnPopulateSceneCallback onPopulateSceneCallback) throws Exception;

    void onReloadResources() throws Exception;

    void onResumeGame();
}
