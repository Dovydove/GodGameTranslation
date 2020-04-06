package org.andengine.p028ui.activity;

import android.content.Context;
import org.andengine.entity.scene.Scene;
import org.andengine.p028ui.IGameInterface.OnCreateResourcesCallback;
import org.andengine.p028ui.IGameInterface.OnCreateSceneCallback;
import org.andengine.p028ui.IGameInterface.OnPopulateSceneCallback;
import org.andengine.util.ActivityUtils;
import org.andengine.util.call.Callback;
import org.andengine.util.progress.IProgressListener;
import org.andengine.util.progress.ProgressCallable;

/* renamed from: org.andengine.ui.activity.SimpleAsyncGameActivity */
public abstract class SimpleAsyncGameActivity extends BaseGameActivity {
    public abstract void onCreateResourcesAsync(IProgressListener iProgressListener) throws Exception;

    public abstract Scene onCreateSceneAsync(IProgressListener iProgressListener) throws Exception;

    public abstract void onPopulateSceneAsync(Scene scene, IProgressListener iProgressListener) throws Exception;

    public void onCreateResources(final OnCreateResourcesCallback pOnCreateResourcesCallback) {
        runOnUiThread(new Runnable() {
            public void run() {
                ActivityUtils.doProgressAsync((Context) SimpleAsyncGameActivity.this, (CharSequence) "Loading Resources...", 17301581, (ProgressCallable<T>) new ProgressCallable<Void>() {
                    public Void call(IProgressListener pProgressListener) throws Exception {
                        SimpleAsyncGameActivity.this.onCreateResourcesAsync(pProgressListener);
                        pProgressListener.onProgressChanged(100);
                        pOnCreateResourcesCallback.onCreateResourcesFinished();
                        return null;
                    }
                }, (Callback<T>) new Callback<Void>() {
                    public void onCallback(Void pCallbackValue) {
                    }
                });
            }
        });
    }

    public void onCreateScene(final OnCreateSceneCallback pOnCreateSceneCallback) {
        runOnUiThread(new Runnable() {
            public void run() {
                ActivityUtils.doProgressAsync((Context) SimpleAsyncGameActivity.this, (CharSequence) "Loading Scene...", 17301581, (ProgressCallable<T>) new ProgressCallable<Void>() {
                    public Void call(IProgressListener pProgressListener) throws Exception {
                        Scene scene = SimpleAsyncGameActivity.this.onCreateSceneAsync(pProgressListener);
                        pProgressListener.onProgressChanged(100);
                        pOnCreateSceneCallback.onCreateSceneFinished(scene);
                        return null;
                    }
                }, (Callback<T>) new Callback<Void>() {
                    public void onCallback(Void pCallbackValue) {
                    }
                });
            }
        });
    }

    public void onPopulateScene(final Scene pScene, final OnPopulateSceneCallback pOnPopulateSceneCallback) {
        runOnUiThread(new Runnable() {
            public void run() {
                ActivityUtils.doProgressAsync((Context) SimpleAsyncGameActivity.this, (CharSequence) "Populating Scene...", 17301581, (ProgressCallable<T>) new ProgressCallable<Void>() {
                    public Void call(IProgressListener pProgressListener) throws Exception {
                        SimpleAsyncGameActivity.this.onPopulateSceneAsync(pScene, pProgressListener);
                        pProgressListener.onProgressChanged(100);
                        pOnPopulateSceneCallback.onPopulateSceneFinished();
                        return null;
                    }
                }, (Callback<T>) new Callback<Void>() {
                    public void onCallback(Void pCallbackValue) {
                    }
                });
            }
        });
    }
}
