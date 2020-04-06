package net.nend.android.internal.utilities.p027a;

import android.graphics.Bitmap;
import android.support.p000v4.media.session.PlaybackStateCompat;
import android.util.LruCache;

/* renamed from: net.nend.android.internal.utilities.a.a */
/* compiled from: BitmapLruCacheUtils */
public class C0749a {

    /* renamed from: a */
    private static final int f1141a = ((int) (Runtime.getRuntime().maxMemory() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID));

    /* renamed from: b */
    private static final int f1142b = (f1141a / 8);

    /* renamed from: c */
    private static LruCache<String, Bitmap> f1143c = new LruCache<String, Bitmap>(f1142b) {
        /* access modifiers changed from: protected */
        /* renamed from: a */
        public int sizeOf(String str, Bitmap bitmap) {
            return bitmap.getByteCount() / 1024;
        }
    };

    /* renamed from: a */
    public static void m1182a(String str, Bitmap bitmap) {
        Bitmap a = m1181a(str);
        if (a == null || a.isRecycled()) {
            f1143c.put(str, bitmap);
        }
    }

    /* renamed from: a */
    public static Bitmap m1181a(String str) {
        Bitmap bitmap = (Bitmap) f1143c.get(str);
        if (bitmap == null || !bitmap.isRecycled()) {
            return bitmap;
        }
        f1143c.remove(str);
        return null;
    }
}
