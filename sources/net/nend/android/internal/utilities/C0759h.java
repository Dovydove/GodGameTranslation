package net.nend.android.internal.utilities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import net.nend.android.internal.utilities.C0740a.C0743b;
import net.nend.android.internal.utilities.C0740a.C0748f;

/* renamed from: net.nend.android.internal.utilities.h */
/* compiled from: NetworkTaskHelper */
public class C0759h {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static final Object[] f1175a = new Object[0];

    /* renamed from: a */
    public static C0748f<Bitmap> m1226a(final String str) {
        return new C0748f<>((C0743b<V>) new C0743b<Bitmap>() {
            public String getRequestUrl() {
                return str;
            }

            /* renamed from: a */
            public Bitmap makeResponse(byte[] bArr) {
                Bitmap decodeByteArray;
                if (bArr != null) {
                    try {
                        synchronized (C0759h.f1175a) {
                            decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
                        }
                        return decodeByteArray;
                    } catch (OutOfMemoryError e) {
                        System.gc();
                        C0757f.m1215a(C0758g.ERR_HTTP_REQUEST, (Throwable) e);
                    } catch (IllegalStateException e2) {
                        C0757f.m1215a(C0758g.ERR_HTTP_REQUEST, (Throwable) e2);
                    }
                }
                return null;
            }
        });
    }
}
