package kanatamikado.p006ae.reiki;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Debug;
import android.support.p000v4.media.session.PlaybackStateCompat;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.andengine.entity.shape.IShape;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.p028ui.activity.BaseGameActivity;

/* renamed from: kanatamikado.ae.reiki.ResourceUtil */
public class ResourceUtil {
    private static Typeface face = null;
    private static BaseGameActivity gameActivity;
    private static ResourceUtil self;
    private List<BuildableBitmapTextureAtlas> bbtaPool;
    private List<BitmapTextureAtlas> btaPool;
    private final boolean debugLogFlg = true;
    private HashMap<String, ITextureRegion> textureRegionPool;
    private HashMap<String, TiledTextureRegion> tiledTextureRegionPool;

    private ResourceUtil() {
        Log.d("Debug", "ResourceUtil_constructor_before:nativeHeap_" + (Debug.getNativeHeapAllocatedSize() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) + "KB");
        if (this.textureRegionPool == null) {
            this.textureRegionPool = new HashMap<>();
        }
        if (this.tiledTextureRegionPool == null) {
            this.tiledTextureRegionPool = new HashMap<>();
        }
        if (this.btaPool == null) {
            this.btaPool = new ArrayList();
        }
        if (this.bbtaPool == null) {
            this.bbtaPool = new ArrayList();
        }
    }

    public static ResourceUtil getInstance(BaseGameActivity gameActivity2) {
        if (self == null) {
            self = new ResourceUtil();
            gameActivity = gameActivity2;
            BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
            if (face == null) {
                face = Typeface.createFromAsset(gameActivity2.getAssets(), "font/HonyaJi-Re.ttf");
            }
        }
        return self;
    }

    public Typeface getTypeface() {
        return face;
    }

    public Sprite getSprite(String fileName) {
        if (this.textureRegionPool.containsKey(fileName)) {
            Sprite s = new Sprite(0.0f, 0.0f, (ITextureRegion) this.textureRegionPool.get(fileName), gameActivity.getVertexBufferObjectManager());
            s.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
            return s;
        }
        InputStream is = null;
        try {
            is = gameActivity.getResources().getAssets().open("gfx/" + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bm = BitmapFactory.decodeStream(is);
        BitmapTextureAtlas bta = new BitmapTextureAtlas(gameActivity.getTextureManager(), getTwoPowerSize((float) bm.getWidth()), getTwoPowerSize((float) bm.getHeight()), TextureOptions.NEAREST);
        gameActivity.getEngine().getTextureManager().loadTexture(bta);
        ITextureRegion btr = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bta, (Context) gameActivity, fileName, 0, 0);
        Sprite s2 = new Sprite(0.0f, 0.0f, btr, gameActivity.getVertexBufferObjectManager());
        s2.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
        this.textureRegionPool.put(fileName, btr);
        try {
            is.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        bm.recycle();
        return s2;
    }

    public AnimatedSprite getAnimatedSprite(String fileName, int column, int row) {
        if (this.tiledTextureRegionPool.containsKey(fileName)) {
            AnimatedSprite s = new AnimatedSprite(0.0f, 0.0f, (ITiledTextureRegion) this.tiledTextureRegionPool.get(fileName), gameActivity.getVertexBufferObjectManager());
            s.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
            return s;
        }
        InputStream is = null;
        try {
            is = gameActivity.getResources().getAssets().open("gfx/" + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Bitmap bm = BitmapFactory.decodeStream(is);
        BitmapTextureAtlas bta = new BitmapTextureAtlas(gameActivity.getTextureManager(), getTwoPowerSize((float) bm.getWidth()), getTwoPowerSize((float) bm.getHeight()), TextureOptions.BILINEAR);
        gameActivity.getTextureManager().loadTexture(bta);
        TiledTextureRegion ttr = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(bta, (Context) gameActivity, fileName, 0, 0, column, row);
        AnimatedSprite s2 = new AnimatedSprite(0.0f, 0.0f, (ITiledTextureRegion) ttr, gameActivity.getVertexBufferObjectManager());
        s2.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
        this.tiledTextureRegionPool.put(fileName, ttr);
        try {
            is.close();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        bm.recycle();
        return s2;
    }

    public ButtonSprite getButtonSprite(String normal, String pressed) {
        if (!this.textureRegionPool.containsKey(normal) || !this.textureRegionPool.containsKey(pressed)) {
            InputStream is = null;
            try {
                is = gameActivity.getResources().getAssets().open("gfx/" + normal);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Bitmap bm = BitmapFactory.decodeStream(is);
            BuildableBitmapTextureAtlas bta = new BuildableBitmapTextureAtlas(gameActivity.getTextureManager(), getTwoPowerSize((float) (bm.getWidth() * 2)), getTwoPowerSize((float) bm.getHeight()));
            ITextureRegion trNormal = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bta, (Context) gameActivity, normal);
            ITextureRegion trPressed = BitmapTextureAtlasTextureRegionFactory.createFromAsset(bta, (Context) gameActivity, pressed);
            try {
                bta.build(new BlackPawnTextureAtlasBuilder(0, 0, 0));
                bta.load();
            } catch (TextureAtlasBuilderException e2) {
                e2.printStackTrace();
            }
            this.textureRegionPool.put(normal, trNormal);
            this.textureRegionPool.put(pressed, trPressed);
            ButtonSprite s = new ButtonSprite(0.0f, 0.0f, trNormal, trPressed, gameActivity.getVertexBufferObjectManager());
            s.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
            try {
                is.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
            bm.recycle();
            return s;
        }
        ButtonSprite s2 = new ButtonSprite(0.0f, 0.0f, (ITextureRegion) this.textureRegionPool.get(normal), (ITextureRegion) this.textureRegionPool.get(pressed), gameActivity.getVertexBufferObjectManager());
        s2.setBlendFunction(IShape.BLENDFUNCTION_SOURCE_DEFAULT, 771);
        return s2;
    }

    public void resetAllTexturePause() {
        self = null;
        this.textureRegionPool.clear();
        this.tiledTextureRegionPool.clear();
        this.textureRegionPool = null;
        this.tiledTextureRegionPool = null;
        this.textureRegionPool = new HashMap<>();
        this.tiledTextureRegionPool = new HashMap<>();
        System.gc();
    }

    public void resetAllTexture() {
        if (this.btaPool.size() > 0) {
            for (int i = 0; i < this.btaPool.size(); i++) {
                ((BitmapTextureAtlas) this.btaPool.get(i)).unload();
                ((BitmapTextureAtlas) this.btaPool.get(i)).clearTextureAtlasSources();
                this.btaPool.set(i, null);
                this.btaPool.remove(i);
            }
        }
        this.btaPool.clear();
        if (this.bbtaPool.size() > 0) {
            for (int i2 = 0; i2 < this.bbtaPool.size(); i2++) {
                ((BuildableBitmapTextureAtlas) this.bbtaPool.get(i2)).unload();
                ((BuildableBitmapTextureAtlas) this.bbtaPool.get(i2)).clearTextureAtlasSources();
                this.bbtaPool.set(i2, null);
                this.bbtaPool.remove(i2);
            }
        }
        this.bbtaPool.clear();
        self = null;
        this.textureRegionPool.clear();
        this.tiledTextureRegionPool.clear();
        this.btaPool = null;
        this.bbtaPool = null;
        this.btaPool = new ArrayList();
        this.bbtaPool = new ArrayList();
        this.textureRegionPool = null;
        this.tiledTextureRegionPool = null;
        this.textureRegionPool = new HashMap<>();
        this.tiledTextureRegionPool = new HashMap<>();
        System.gc();
    }

    public int getTwoPowerSize(float size) {
        int pow2value = 64;
        while (pow2value < ((int) (1.0f + size))) {
            pow2value *= 2;
        }
        return pow2value;
    }
}
