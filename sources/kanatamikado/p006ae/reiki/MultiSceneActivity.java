package kanatamikado.p006ae.reiki;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import java.util.ArrayList;
import org.andengine.p028ui.activity.SimpleLayoutGameActivity;

/* renamed from: kanatamikado.ae.reiki.MultiSceneActivity */
public abstract class MultiSceneActivity extends SimpleLayoutGameActivity {
    Handler goneNativeViewHandler = new Handler();
    Handler invisibleNativeViewHandler = new Handler();
    private ResourceUtil mResourceUtil;
    private ArrayList<KeyListenScene> mSceneArray;
    private final String packageName = "";
    Handler visibleNativeViewHandler = new Handler();

    public abstract void appendScene(KeyListenScene keyListenScene);

    public abstract void backToInitial();

    public abstract void refreshRunningScene(KeyListenScene keyListenScene);

    /* access modifiers changed from: protected */
    public void onCreateResources() {
        this.mResourceUtil = ResourceUtil.getInstance(this);
        this.mSceneArray = new ArrayList<>();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    public ResourceUtil getResourceUtil() {
        return this.mResourceUtil;
    }

    public ArrayList<KeyListenScene> getSceneArray() {
        return this.mSceneArray;
    }

    public void browserStart(String url) {
        getSceneArray().clear();
        ResourceUtil.getInstance(this).resetAllTexture();
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
        finish();
    }

    public void goToMarket() {
        getSceneArray().clear();
        ResourceUtil.getInstance(this).resetAllTexture();
        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=&hl=ja")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        finish();
    }

    public void snsStart() {
        getSceneArray().clear();
        ResourceUtil.getInstance(this).resetAllTexture();
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", "紹介テキスト");
        intent.setType("text/plain");
        startActivity(intent);
        finish();
    }

    public void goneNativeViewFromId(final int id) {
        new Thread(new Runnable() {
            public void run() {
                MultiSceneActivity.this.goneNativeViewHandler.post(new Runnable() {
                    public void run() {
                        MultiSceneActivity.this.findViewById(id).setVisibility(8);
                    }
                });
            }
        }).start();
    }

    public void invisibleNativeViewFromId(final int id) {
        new Thread(new Runnable() {
            public void run() {
                MultiSceneActivity.this.invisibleNativeViewHandler.post(new Runnable() {
                    public void run() {
                        MultiSceneActivity.this.findViewById(id).setVisibility(4);
                    }
                });
            }
        }).start();
    }

    public void visibleNativeViewFromId(final int id) {
        new Thread(new Runnable() {
            public void run() {
                MultiSceneActivity.this.visibleNativeViewHandler.post(new Runnable() {
                    public void run() {
                        MultiSceneActivity.this.findViewById(id).setVisibility(0);
                    }
                });
            }
        }).start();
    }
}
