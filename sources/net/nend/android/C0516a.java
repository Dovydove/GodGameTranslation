package net.nend.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import net.nend.android.NendAdVideoListener;
import net.nend.android.internal.p007a.C0523a;
import net.nend.android.internal.p008b.p012c.C0544d;
import net.nend.android.internal.p013c.p021f.C0619e;
import net.nend.android.internal.p022d.C0627a;
import net.nend.android.internal.p022d.C0628b;
import net.nend.android.internal.p022d.C0630d;
import net.nend.android.internal.p022d.C0646k;
import net.nend.android.internal.p022d.C0647l;
import net.nend.android.internal.p023ui.activities.video.C0687a;
import net.nend.android.internal.utilities.C0740a;
import net.nend.android.internal.utilities.C0740a.C0745d;
import net.nend.android.internal.utilities.C0755e;
import net.nend.android.internal.utilities.C0757f;
import net.nend.android.internal.utilities.C0758g;
import net.nend.android.internal.utilities.video.NendVideoAdClientError;

/* renamed from: net.nend.android.a */
/* compiled from: NendAdVideoImpl */
abstract class C0516a<Ad extends C0544d, Listener extends NendAdVideoListener> implements NendAdVideo {

    /* renamed from: a */
    protected final int f539a;

    /* renamed from: b */
    protected final String f540b;

    /* renamed from: c */
    Context f541c;

    /* renamed from: d */
    String f542d;

    /* renamed from: e */
    String f543e;

    /* renamed from: f */
    Ad f544f;

    /* renamed from: g */
    boolean f545g;
    @Nullable

    /* renamed from: h */
    Listener f546h;
    @Nullable

    /* renamed from: i */
    C0646k<Ad> f547i;

    /* renamed from: j */
    ResultReceiver f548j = new ResultReceiver(new Handler(Looper.getMainLooper())) {
        /* access modifiers changed from: protected */
        public void onReceiveResult(int i, Bundle bundle) {
            super.onReceiveResult(i, bundle);
            C0757f.m1210a("resultCode: " + i);
            switch (i) {
                case 1:
                    C0516a.this.f545g = false;
                    C0516a.this.m424a(null);
                    if (C0516a.this.f546h != null) {
                        C0516a.this.f546h.onClosed(C0516a.this);
                        return;
                    }
                    return;
                case 2:
                    if (C0516a.this.f546h != null) {
                        C0516a.this.f546h.onShown(C0516a.this);
                        return;
                    }
                    return;
                case 3:
                    if (C0516a.this.f546h != null) {
                        C0516a.this.f546h.onStarted(C0516a.this);
                        return;
                    }
                    return;
                case 4:
                    C0516a.this.mo7714a(bundle.getBoolean(C0687a.RESULT_DATA_KEY_VIDEO_IS_COMPLETION));
                    return;
                case 5:
                    if (C0516a.this.f546h != null) {
                        C0516a.this.f546h.onAdClicked(C0516a.this);
                        return;
                    }
                    return;
                case 6:
                    if (C0516a.this.f546h != null) {
                        C0516a.this.f546h.onInformationClicked(C0516a.this);
                        return;
                    }
                    return;
                case 7:
                    C0516a.this.f545g = false;
                    if (C0516a.this.f546h != null) {
                        C0516a.this.f546h.onFailedToPlay(C0516a.this);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };

    /* renamed from: k */
    private C0627a f549k;
    @VisibleForTesting
    public C0619e mVideoAdLoader;

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public abstract Intent mo7526a(Activity activity);

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public abstract C0646k<Ad> mo7527a();

    C0516a(Context context, int i, String str) {
        m422a(context, i, str);
        this.f541c = context;
        this.f539a = i;
        this.f540b = str;
        this.mVideoAdLoader = new C0619e(context);
        this.f549k = new C0627a(context.getMainLooper());
        C0755e.m1197a(this.f541c);
        C0647l.m923a(C0740a.m1170a().mo8184b(), (Callable<T>) new C0745d<T>(this.f541c)).mo7994a((C0628b<? super T, Throwable>) new C0628b<String, Throwable>() {
            /* renamed from: a */
            public void mo7687a(String str, Throwable th) {
                if (TextUtils.isEmpty(str)) {
                    C0757f.m1211a("Cannot get Google Advertising ID...", th);
                } else {
                    C0757f.m1217b("Google Advertising ID = " + str);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo7714a(boolean z) {
        if (this.f546h == null) {
            return;
        }
        if (z) {
            this.f546h.onCompleted(this);
        } else {
            this.f546h.onStopped(this);
        }
    }

    /* renamed from: a */
    private void m422a(Context context, int i, String str) {
        if (context == null) {
            throw new NullPointerException("Context is null.");
        } else if (i <= 0) {
            throw new IllegalArgumentException(C0758g.ERR_INVALID_SPOT_ID.mo8197a("spot id : " + i));
        } else if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(C0758g.ERR_INVALID_API_KEY.mo8197a("api key : " + str));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m424a(Ad ad) {
        if (this.f544f != null) {
            this.mVideoAdLoader.mo7983a((C0544d) this.f544f);
        }
        this.f544f = ad;
    }

    /* renamed from: b */
    private boolean m425b() {
        if (this.f547i != null && this.f547i.mo8000b()) {
            C0757f.m1220c("NendAdVideo is loading.");
            return true;
        } else if (!this.f545g) {
            return false;
        } else {
            C0757f.m1220c("NendAdVideo is playing.");
            return true;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo7529b(Activity activity) {
        activity.startActivity(mo7526a(activity));
    }

    public void setUserFeature(NendAdUserFeature nendAdUserFeature) {
        this.mVideoAdLoader.mo7827a(nendAdUserFeature);
    }

    public void loadAd() {
        if (!m425b()) {
            this.f547i = mo7527a();
            this.f547i.mo7993a((Executor) this.f549k).mo7996a((C0630d<? super T>) new C0630d<Ad>() {
                /* renamed from: a */
                public void mo7666a(Ad ad) {
                    C0516a.this.m424a(ad);
                    if (C0516a.this.f546h != null) {
                        C0516a.this.f546h.onLoaded(C0516a.this);
                    }
                }
            }).mo7998b((C0630d<Throwable>) new C0630d<Throwable>() {
                /* renamed from: a */
                public void mo7666a(Throwable th) {
                    C0516a.this.m424a(null);
                    C0757f.m1218b("Failed to load ad.", th);
                    if (C0516a.this.f546h == null) {
                        return;
                    }
                    if (th instanceof C0523a) {
                        C0516a.this.f546h.onFailedToLoad(C0516a.this, ((C0523a) th).mo7777a());
                        return;
                    }
                    C0516a.this.f546h.onFailedToLoad(C0516a.this, NendVideoAdClientError.FAILED_INTERNAL.getCode());
                }
            });
        }
    }

    public void showAd(Activity activity) {
        if (!isLoaded()) {
            C0757f.m1222d("Failed to showAd. loadAd is not complete.");
        } else if (!m425b()) {
            this.f545g = true;
            mo7529b(activity);
        }
    }

    public void releaseAd() {
        this.f546h = null;
        this.f541c = null;
        if (!this.f545g) {
            m424a((Ad) null);
            if (this.f547i != null && this.f547i.mo8000b()) {
                this.f547i.mo8001c();
            }
            this.f547i = null;
            this.f545g = false;
        }
    }

    public boolean isLoaded() {
        boolean z = this.f544f != null && !this.f544f.mo7782c();
        if (!z) {
            m424a((Ad) null);
        }
        return z;
    }

    public void setMediationName(String str) {
        this.f542d = str;
    }

    public void setUserId(String str) {
        this.f543e = str;
    }
}
