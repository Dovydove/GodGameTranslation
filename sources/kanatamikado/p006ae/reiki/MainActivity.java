package kanatamikado.p006ae.reiki;

import android.view.KeyEvent;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;

/* renamed from: kanatamikado.ae.reiki.MainActivity */
public class MainActivity extends MultiSceneActivity {
    public static final int CAMERA_HEIGHT = 960;
    public static final int CAMERA_WIDTH = 540;
    public static final boolean DEBUG_LOG_FLG = false;
    public static final String PREFERRENCES_FILE_NAME = "PrefrencesFile";
    public static String verName = "0.0.0";
    private boolean isSceneCreated = false;

    public EngineOptions onCreateEngineOptions() {
        EngineOptions eo = new EngineOptions(true, ScreenOrientation.PORTRAIT_FIXED, new RatioResolutionPolicy(540.0f, 960.0f), new Camera(0.0f, 0.0f, 540.0f, 960.0f));
        eo.getAudioOptions().setNeedsMusic(true);
        eo.getAudioOptions().setNeedsSound(true);
        return eo;
    }

    /* access modifiers changed from: protected */
    public Scene onCreateScene() {
        this.isSceneCreated = true;
        MusicFactory.setAssetBasePath("mfx/");
        SoundFactory.setAssetBasePath("mfx/");
        return new InitialScene(this);
    }

    public void onStop() {
        super.onStop();
        try {
            getResourceUtil().resetAllTexture();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onStart() {
        super.onStart();
    }

    public void onPause() {
        super.onPause();
        if (!this.isSceneCreated) {
            return;
        }
        if (getEngine().getScene() instanceof InitialScene) {
            ((InitialScene) getEngine().getScene()).bgmChange(true);
            ((InitialScene) getEngine().getScene()).soundChange(true);
        } else if (getEngine().getScene() instanceof QuestScene) {
            ((QuestScene) getEngine().getScene()).bgmChange(true);
            ((QuestScene) getEngine().getScene()).soundChange(true);
        } else if (getEngine().getScene() instanceof MainScene) {
            ((MainScene) getEngine().getScene()).bgmChange(true);
            ((MainScene) getEngine().getScene()).soundChange(true);
        } else if (getEngine().getScene() instanceof MenuScene) {
            ((MenuScene) getEngine().getScene()).bgmChange(true);
            ((MenuScene) getEngine().getScene()).soundChange(true);
        }
    }

    public void onResume() {
        super.onResume();
        if (getEngine().getScene() instanceof InitialScene) {
            ((InitialScene) getEngine().getScene()).bgmChange(false);
            ((InitialScene) getEngine().getScene()).soundChange(false);
        } else if (getEngine().getScene() instanceof QuestScene) {
            ((QuestScene) getEngine().getScene()).bgmChange(false);
            ((QuestScene) getEngine().getScene()).soundChange(false);
        } else if (getEngine().getScene() instanceof MainScene) {
            ((MainScene) getEngine().getScene()).bgmChange(false);
            ((MainScene) getEngine().getScene()).soundChange(false);
        } else if (getEngine().getScene() instanceof MenuScene) {
            ((MenuScene) getEngine().getScene()).bgmChange(false);
            ((MenuScene) getEngine().getScene()).soundChange(false);
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public int getLayoutID() {
        return C0436R.layout.activity_main;
    }

    /* access modifiers changed from: protected */
    public int getRenderSurfaceViewID() {
        return C0436R.C0439id.renderview;
    }

    public void appendScene(KeyListenScene scene) {
    }

    public void backToInitial() {
    }

    public void refreshRunningScene(KeyListenScene scene) {
    }

    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getAction() != 0 || e.getKeyCode() != 4) {
            return false;
        }
        if (getEngine().getScene() instanceof MainScene) {
            ((MainScene) getEngine().getScene()).endConfirmOpen();
        } else if (getEngine().getScene() instanceof MenuScene) {
            ((MenuScene) getEngine().getScene()).endConfirmOpen();
        } else if (getEngine().getScene() instanceof QuestScene) {
            ((QuestScene) getEngine().getScene()).endConfirmOpen();
        }
        return true;
    }
}
