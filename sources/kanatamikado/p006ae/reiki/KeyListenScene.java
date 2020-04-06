package kanatamikado.p006ae.reiki;

import android.view.KeyEvent;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;

/* renamed from: kanatamikado.ae.reiki.KeyListenScene */
public abstract class KeyListenScene extends Scene {
    private MultiSceneActivity baseActivity;

    public abstract boolean dispatchKeyEvent(KeyEvent keyEvent);

    public abstract void init();

    public abstract void onDestroy();

    public abstract void prepareSoundAndMusic();

    public KeyListenScene(MultiSceneActivity baseActivity2) {
        setTouchAreaBindingOnActionDownEnabled(true);
        this.baseActivity = baseActivity2;
        prepareSoundAndMusic();
    }

    public MultiSceneActivity getBaseActivity() {
        return this.baseActivity;
    }

    public Sprite placeToCenter(Sprite sp) {
        sp.setPosition((this.baseActivity.getEngine().getCamera().getWidth() / 2.0f) - (sp.getWidth() / 2.0f), (this.baseActivity.getEngine().getCamera().getHeight() / 2.0f) - (sp.getHeight() / 2.0f));
        return sp;
    }

    public Sprite placeToCenterX(Sprite sp, float y) {
        sp.setPosition((this.baseActivity.getEngine().getCamera().getWidth() / 2.0f) - (sp.getWidth() / 2.0f), y);
        return sp;
    }

    public Sprite placeToCenterY(Sprite sp, float x) {
        sp.setPosition(x, (this.baseActivity.getEngine().getCamera().getHeight() / 2.0f) - (sp.getHeight() / 2.0f));
        return sp;
    }
}
