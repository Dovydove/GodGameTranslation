package net.nend.android.internal.p013c.p015b;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.nend.android.NendAdNative;
import net.nend.android.NendAdNativeClient.Callback;
import net.nend.android.NendAdNativeClient.NendError;
import net.nend.android.internal.utilities.C0740a;
import net.nend.android.internal.utilities.C0740a.C0743b;
import net.nend.android.internal.utilities.C0740a.C0748f;
import net.nend.android.internal.utilities.C0755e;
import net.nend.android.internal.utilities.C0757f;
import net.nend.android.internal.utilities.C0758g;

/* renamed from: net.nend.android.internal.c.b.a */
/* compiled from: NendAdNativeLoader */
public final class C0558a {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public Context f670a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public C0563b f671b;

    /* renamed from: c */
    private ExecutorService f672c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public Handler f673d = new Handler(Looper.getMainLooper());

    public C0558a(Context context, C0563b bVar) {
        this.f670a = context;
        this.f671b = bVar;
        this.f672c = Executors.newSingleThreadExecutor();
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public C0748f<NendAdNative> m586a() {
        return new C0748f<>((C0743b<V>) new C0743b<NendAdNative>() {

            /* renamed from: a */
            static final /* synthetic */ boolean f674a = (!C0558a.class.desiredAssertionStatus());

            public String getRequestUrl() {
                return C0558a.this.f671b.mo7849b(C0755e.m1201b(C0558a.this.f670a));
            }

            /* renamed from: a */
            public NendAdNative makeResponse(byte[] bArr) {
                String str;
                if (bArr != null) {
                    try {
                        str = new String(bArr, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        if (!f674a) {
                            throw new AssertionError();
                        }
                        C0757f.m1215a(C0758g.ERR_HTTP_REQUEST, (Throwable) e);
                        str = null;
                    }
                    if (TextUtils.isEmpty(str)) {
                        return null;
                    }
                    NendAdNative nendAdNative = (NendAdNative) new C0564c(C0558a.this.f670a).mo7867a(str);
                    if (nendAdNative == null) {
                        return null;
                    }
                    nendAdNative.setSpotId(C0558a.this.f671b.mo7863j());
                    return nendAdNative;
                }
                C0757f.m1213a(C0758g.ERR_INVALID_URL);
                return null;
            }
        });
    }

    /* renamed from: a */
    public void mo7857a(final Callback callback) {
        this.f672c.execute(new Runnable() {
            public void run() {
                try {
                    final NendAdNative nendAdNative = (NendAdNative) C0740a.m1170a().mo8182a(C0558a.this.m586a()).get();
                    C0558a.this.f673d.post(new Runnable() {
                        public void run() {
                            if (nendAdNative == null) {
                                callback.onFailure(NendError.FAILED_AD_REQUEST);
                            } else if (nendAdNative.getCampaignId() == null) {
                                callback.onFailure(NendError.FAILED_AD_REQUEST);
                            } else {
                                C0558a.this.f671b.mo7862c(nendAdNative.getCampaignId());
                                callback.onSuccess(nendAdNative);
                            }
                        }
                    });
                } catch (InterruptedException | ExecutionException e) {
                    C0558a.this.f673d.post(new Runnable() {
                        public void run() {
                            callback.onFailure(NendError.FAILED_AD_REQUEST);
                        }
                    });
                }
            }
        });
    }
}
