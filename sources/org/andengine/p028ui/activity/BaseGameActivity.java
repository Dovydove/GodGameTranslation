package org.andengine.p028ui.activity;

import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import org.andengine.audio.music.MusicManager;
import org.andengine.audio.sound.SoundManager;
import org.andengine.engine.Engine;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.WakeLockOptions;
import org.andengine.entity.scene.Scene;
import org.andengine.input.sensor.acceleration.AccelerationSensorOptions;
import org.andengine.input.sensor.acceleration.IAccelerationListener;
import org.andengine.input.sensor.location.ILocationListener;
import org.andengine.input.sensor.location.LocationSensorOptions;
import org.andengine.input.sensor.orientation.IOrientationListener;
import org.andengine.input.sensor.orientation.OrientationSensorOptions;
import org.andengine.opengl.font.FontManager;
import org.andengine.opengl.shader.ShaderProgramManager;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.util.GLState;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.opengl.view.IRendererListener;
import org.andengine.opengl.view.RenderSurfaceView;
import org.andengine.p028ui.IGameInterface;
import org.andengine.p028ui.IGameInterface.OnCreateResourcesCallback;
import org.andengine.p028ui.IGameInterface.OnCreateSceneCallback;
import org.andengine.p028ui.IGameInterface.OnPopulateSceneCallback;
import org.andengine.util.ActivityUtils;
import org.andengine.util.Constants;
import org.andengine.util.debug.Debug;
import org.andengine.util.system.SystemUtils;

/* renamed from: org.andengine.ui.activity.BaseGameActivity */
public abstract class BaseGameActivity extends BaseActivity implements IGameInterface, IRendererListener {
    private boolean mCreateGameCalled;
    protected Engine mEngine;
    private boolean mGameCreated;
    private boolean mGamePaused;
    private boolean mOnReloadResourcesScheduled;
    protected RenderSurfaceView mRenderSurfaceView;
    private WakeLock mWakeLock;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        this.mGamePaused = true;
        this.mEngine = onCreateEngine(onCreateEngineOptions());
        this.mEngine.startUpdateThread();
        applyEngineOptions();
        onSetContentView();
    }

    public Engine onCreateEngine(EngineOptions pEngineOptions) {
        return new Engine(pEngineOptions);
    }

    public synchronized void onSurfaceCreated(GLState pGLState) {
        if (this.mGameCreated) {
            onReloadResources();
            if (this.mGamePaused && this.mGameCreated) {
                onResumeGame();
            }
        } else if (this.mCreateGameCalled) {
            this.mOnReloadResourcesScheduled = true;
        } else {
            this.mCreateGameCalled = true;
            onCreateGame();
        }
    }

    public synchronized void onSurfaceChanged(GLState pGLState, int pWidth, int pHeight) {
    }

    /* access modifiers changed from: protected */
    public synchronized void onCreateGame() {
        final OnPopulateSceneCallback onPopulateSceneCallback = new OnPopulateSceneCallback() {
            public void onPopulateSceneFinished() {
                try {
                    BaseGameActivity.this.onGameCreated();
                } catch (Throwable pThrowable) {
                    Debug.m1274e(BaseGameActivity.this.getClass().getSimpleName() + ".onGameCreated failed. @(Thread: '" + Thread.currentThread().getName() + "')", pThrowable);
                }
                BaseGameActivity.this.callGameResumedOnUIThread();
            }
        };
        final OnCreateSceneCallback onCreateSceneCallback = new OnCreateSceneCallback() {
            public void onCreateSceneFinished(Scene pScene) {
                BaseGameActivity.this.mEngine.setScene(pScene);
                try {
                    BaseGameActivity.this.onPopulateScene(pScene, onPopulateSceneCallback);
                } catch (Throwable pThrowable) {
                    Debug.m1274e(BaseGameActivity.this.getClass().getSimpleName() + ".onPopulateScene failed. @(Thread: '" + Thread.currentThread().getName() + "')", pThrowable);
                }
            }
        };
        try {
            onCreateResources(new OnCreateResourcesCallback() {
                public void onCreateResourcesFinished() {
                    try {
                        BaseGameActivity.this.onCreateScene(onCreateSceneCallback);
                    } catch (Throwable pThrowable) {
                        Debug.m1274e(BaseGameActivity.this.getClass().getSimpleName() + ".onCreateScene failed. @(Thread: '" + Thread.currentThread().getName() + "')", pThrowable);
                    }
                }
            });
        } catch (Throwable pThrowable) {
            Debug.m1274e(getClass().getSimpleName() + ".onCreateGame failed. @(Thread: '" + Thread.currentThread().getName() + "')", pThrowable);
        }
        return;
    }

    public synchronized void onGameCreated() {
        this.mGameCreated = true;
        if (this.mOnReloadResourcesScheduled) {
            this.mOnReloadResourcesScheduled = false;
            try {
                onReloadResources();
            } catch (Throwable pThrowable) {
                Debug.m1274e(getClass().getSimpleName() + ".onReloadResources failed. @(Thread: '" + Thread.currentThread().getName() + "')", pThrowable);
            }
        }
        return;
    }

    /* access modifiers changed from: protected */
    public synchronized void onResume() {
        super.onResume();
        acquireWakeLock();
        this.mRenderSurfaceView.onResume();
    }

    public synchronized void onResumeGame() {
        this.mEngine.start();
        this.mGamePaused = false;
    }

    public synchronized void onWindowFocusChanged(boolean pHasWindowFocus) {
        super.onWindowFocusChanged(pHasWindowFocus);
        if (pHasWindowFocus && this.mGamePaused && this.mGameCreated) {
            onResumeGame();
        }
    }

    public void onReloadResources() {
        this.mEngine.onReloadResources();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.mRenderSurfaceView.onPause();
        releaseWakeLock();
        if (!this.mGamePaused) {
            onPauseGame();
        }
    }

    public synchronized void onPauseGame() {
        this.mGamePaused = true;
        this.mEngine.stop();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mEngine.onDestroy();
        try {
            onDestroyResources();
        } catch (Throwable pThrowable) {
            Debug.m1274e(getClass().getSimpleName() + ".onDestroyResources failed. @(Thread: '" + Thread.currentThread().getName() + "')", pThrowable);
        }
        onGameDestroyed();
        this.mEngine = null;
    }

    public void onDestroyResources() throws Exception {
        if (this.mEngine.getEngineOptions().getAudioOptions().needsMusic()) {
            getMusicManager().releaseAll();
        }
        if (this.mEngine.getEngineOptions().getAudioOptions().needsSound()) {
            getSoundManager().releaseAll();
        }
    }

    public synchronized void onGameDestroyed() {
        this.mGameCreated = false;
    }

    public Engine getEngine() {
        return this.mEngine;
    }

    public boolean isGamePaused() {
        return this.mGamePaused;
    }

    public boolean isGameRunning() {
        return !this.mGamePaused;
    }

    public boolean isGameLoaded() {
        return this.mGameCreated;
    }

    public VertexBufferObjectManager getVertexBufferObjectManager() {
        return this.mEngine.getVertexBufferObjectManager();
    }

    public TextureManager getTextureManager() {
        return this.mEngine.getTextureManager();
    }

    public FontManager getFontManager() {
        return this.mEngine.getFontManager();
    }

    public ShaderProgramManager getShaderProgramManager() {
        return this.mEngine.getShaderProgramManager();
    }

    public SoundManager getSoundManager() {
        return this.mEngine.getSoundManager();
    }

    public MusicManager getMusicManager() {
        return this.mEngine.getMusicManager();
    }

    /* access modifiers changed from: private */
    public void callGameResumedOnUIThread() {
        runOnUiThread(new Runnable() {
            public void run() {
                BaseGameActivity.this.onResumeGame();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onSetContentView() {
        this.mRenderSurfaceView = new RenderSurfaceView(this);
        this.mRenderSurfaceView.setRenderer(this.mEngine, this);
        setContentView(this.mRenderSurfaceView, createSurfaceViewLayoutParams());
    }

    public void runOnUpdateThread(Runnable pRunnable) {
        this.mEngine.runOnUpdateThread(pRunnable);
    }

    public void runOnUpdateThread(Runnable pRunnable, boolean pOnlyWhenEngineRunning) {
        this.mEngine.runOnUpdateThread(pRunnable, pOnlyWhenEngineRunning);
    }

    private void acquireWakeLock() {
        acquireWakeLock(this.mEngine.getEngineOptions().getWakeLockOptions());
    }

    private void acquireWakeLock(WakeLockOptions pWakeLockOptions) {
        if (pWakeLockOptions == WakeLockOptions.SCREEN_ON) {
            ActivityUtils.keepScreenOn(this);
            return;
        }
        this.mWakeLock = ((PowerManager) getSystemService("power")).newWakeLock(pWakeLockOptions.getFlag() | ErrorDialogData.DYNAMITE_CRASH, Constants.DEBUGTAG);
        try {
            this.mWakeLock.acquire();
        } catch (SecurityException pSecurityException) {
            Debug.m1274e("You have to add\n\t<uses-permission android:name=\"android.permission.WAKE_LOCK\"/>\nto your AndroidManifest.xml !", (Throwable) pSecurityException);
        }
    }

    private void releaseWakeLock() {
        if (this.mWakeLock != null && this.mWakeLock.isHeld()) {
            this.mWakeLock.release();
        }
    }

    private void applyEngineOptions() {
        EngineOptions engineOptions = this.mEngine.getEngineOptions();
        if (engineOptions.isFullscreen()) {
            ActivityUtils.requestFullscreen(this);
        }
        if (engineOptions.getAudioOptions().needsMusic() || engineOptions.getAudioOptions().needsSound()) {
            setVolumeControlStream(3);
        }
        switch (engineOptions.getScreenOrientation()) {
            case LANDSCAPE_FIXED:
                setRequestedOrientation(0);
                return;
            case LANDSCAPE_SENSOR:
                if (SystemUtils.SDK_VERSION_GINGERBREAD_OR_LATER) {
                    setRequestedOrientation(6);
                    return;
                }
                Debug.m1284w(ScreenOrientation.class.getSimpleName() + "." + ScreenOrientation.LANDSCAPE_SENSOR + " is not supported on this device. Falling back to " + ScreenOrientation.class.getSimpleName() + "." + ScreenOrientation.LANDSCAPE_FIXED);
                setRequestedOrientation(0);
                return;
            case PORTRAIT_FIXED:
                setRequestedOrientation(1);
                return;
            case PORTRAIT_SENSOR:
                if (SystemUtils.SDK_VERSION_GINGERBREAD_OR_LATER) {
                    setRequestedOrientation(7);
                    return;
                }
                Debug.m1284w(ScreenOrientation.class.getSimpleName() + "." + ScreenOrientation.PORTRAIT_SENSOR + " is not supported on this device. Falling back to " + ScreenOrientation.class.getSimpleName() + "." + ScreenOrientation.PORTRAIT_FIXED);
                setRequestedOrientation(1);
                return;
            default:
                return;
        }
    }

    protected static LayoutParams createSurfaceViewLayoutParams() {
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        return layoutParams;
    }

    /* access modifiers changed from: protected */
    public void enableVibrator() {
        this.mEngine.enableVibrator(this);
    }

    /* access modifiers changed from: protected */
    public void enableLocationSensor(ILocationListener pLocationListener, LocationSensorOptions pLocationSensorOptions) {
        this.mEngine.enableLocationSensor(this, pLocationListener, pLocationSensorOptions);
    }

    /* access modifiers changed from: protected */
    public void disableLocationSensor() {
        this.mEngine.disableLocationSensor(this);
    }

    /* access modifiers changed from: protected */
    public boolean enableAccelerationSensor(IAccelerationListener pAccelerationListener) {
        return this.mEngine.enableAccelerationSensor(this, pAccelerationListener);
    }

    /* access modifiers changed from: protected */
    public boolean enableAccelerationSensor(IAccelerationListener pAccelerationListener, AccelerationSensorOptions pAccelerationSensorOptions) {
        return this.mEngine.enableAccelerationSensor(this, pAccelerationListener, pAccelerationSensorOptions);
    }

    /* access modifiers changed from: protected */
    public boolean disableAccelerationSensor() {
        return this.mEngine.disableAccelerationSensor(this);
    }

    /* access modifiers changed from: protected */
    public boolean enableOrientationSensor(IOrientationListener pOrientationListener) {
        return this.mEngine.enableOrientationSensor(this, pOrientationListener);
    }

    /* access modifiers changed from: protected */
    public boolean enableOrientationSensor(IOrientationListener pOrientationListener, OrientationSensorOptions pLocationSensorOptions) {
        return this.mEngine.enableOrientationSensor(this, pOrientationListener, pLocationSensorOptions);
    }

    /* access modifiers changed from: protected */
    public boolean disableOrientationSensor() {
        return this.mEngine.disableOrientationSensor(this);
    }
}
