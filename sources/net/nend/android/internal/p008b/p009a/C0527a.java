package net.nend.android.internal.p008b.p009a;

import android.content.Context;
import android.util.DisplayMetrics;
import java.lang.ref.WeakReference;
import java.util.concurrent.Future;
import net.nend.android.NendAdView.NendError;
import net.nend.android.internal.C0521a;
import net.nend.android.internal.C0521a.C0522a;
import net.nend.android.internal.p008b.p009a.p010a.C0530a;
import net.nend.android.internal.p008b.p009a.p010a.C0531b;
import net.nend.android.internal.p013c.p014a.C0551a;
import net.nend.android.internal.p013c.p014a.C0555c;
import net.nend.android.internal.utilities.C0740a;
import net.nend.android.internal.utilities.C0740a.C0742a;
import net.nend.android.internal.utilities.C0740a.C0743b;
import net.nend.android.internal.utilities.C0740a.C0748f;
import net.nend.android.internal.utilities.C0755e;
import net.nend.android.internal.utilities.C0757f;
import net.nend.android.internal.utilities.C0758g;

/* renamed from: net.nend.android.internal.b.a.a */
/* compiled from: NendAd */
public final class C0527a implements C0530a, C0743b<C0521a> {

    /* renamed from: a */
    static final /* synthetic */ boolean f570a = (!C0527a.class.desiredAssertionStatus());

    /* renamed from: b */
    private final String f571b;

    /* renamed from: c */
    private final Context f572c;

    /* renamed from: d */
    private final C0551a f573d;

    /* renamed from: e */
    private C0522a f574e = C0522a.NONE;

    /* renamed from: f */
    private String f575f = null;

    /* renamed from: g */
    private String f576g = null;

    /* renamed from: h */
    private String f577h = null;

    /* renamed from: i */
    private String f578i = null;

    /* renamed from: j */
    private int f579j = 320;

    /* renamed from: k */
    private int f580k = 50;

    /* renamed from: l */
    private int f581l = 60;

    /* renamed from: m */
    private String f582m;

    /* renamed from: n */
    private String f583n;

    /* renamed from: o */
    private boolean f584o;

    /* renamed from: p */
    private WeakReference<C0531b> f585p = null;

    /* renamed from: q */
    private Future<C0521a> f586q;

    /* renamed from: r */
    private DisplayMetrics f587r;

    public C0527a(Context context, int i, String str, DisplayMetrics displayMetrics) {
        if (context == null) {
            throw new NullPointerException("Context is null.");
        } else if (i <= 0) {
            throw new IllegalArgumentException("Spot id is invalid. spot id : " + i);
        } else if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Api key is invalid. api key : " + str);
        } else {
            this.f572c = context;
            this.f587r = displayMetrics;
            this.f573d = new C0551a(context, i, str);
            this.f571b = C0755e.m1201b(context);
        }
    }

    /* renamed from: a */
    public C0522a mo7766a() {
        return this.f574e;
    }

    /* renamed from: b */
    public String mo7767b() {
        return this.f575f;
    }

    /* renamed from: c */
    public String mo7768c() {
        return this.f576g;
    }

    /* renamed from: d */
    public String mo7769d() {
        return this.f577h;
    }

    /* renamed from: e */
    public String mo7770e() {
        return this.f578i;
    }

    /* renamed from: f */
    public int mo7771f() {
        return this.f579j;
    }

    /* renamed from: g */
    public int mo7772g() {
        return this.f580k;
    }

    /* renamed from: h */
    public int mo7773h() {
        return this.f581l;
    }

    /* renamed from: i */
    public String mo7774i() {
        return this.f582m;
    }

    /* renamed from: j */
    public String mo7775j() {
        return this.f583n;
    }

    /* renamed from: k */
    public boolean mo7776k() {
        return this.f584o;
    }

    /* renamed from: l */
    public String mo7791l() {
        return this.f571b;
    }

    /* renamed from: m */
    public boolean mo7792m() {
        return this.f586q == null || this.f586q.isDone();
    }

    /* renamed from: n */
    public boolean mo7793n() {
        if (!mo7792m()) {
            return false;
        }
        this.f586q = C0740a.m1170a().mo8183a(new C0748f((C0743b<V>) this), new C0742a<C0521a>() {
            /* renamed from: a */
            public void mo7491a(C0521a aVar, Exception exc) {
                C0527a.this.m455a(aVar);
            }
        });
        return true;
    }

    /* renamed from: o */
    public void mo7794o() {
        if (this.f586q != null) {
            this.f586q.cancel(true);
        }
    }

    /* renamed from: p */
    public C0531b mo7795p() {
        if (this.f585p != null) {
            return (C0531b) this.f585p.get();
        }
        return null;
    }

    /* renamed from: a */
    public void mo7790a(C0531b bVar) {
        this.f585p = new WeakReference<>(bVar);
    }

    /* renamed from: q */
    public void mo7796q() {
        this.f585p = null;
    }

    public String getRequestUrl() {
        return this.f573d.mo7849b(this.f571b);
    }

    /* renamed from: a */
    public C0521a makeResponse(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            return (C0521a) new C0555c(this.f572c).mo7867a(new String(bArr, "UTF-8"));
        } catch (Exception e) {
            if (!f570a) {
                throw new AssertionError();
            }
            C0757f.m1215a(C0758g.ERR_HTTP_REQUEST, (Throwable) e);
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m455a(C0521a aVar) {
        boolean z = true;
        C0531b p = mo7795p();
        if (aVar != null) {
            float f = this.f587r.density;
            float f2 = ((float) aVar.mo7771f()) * f;
            float g = f * ((float) aVar.mo7772g());
            if (f2 / 2.0f > ((float) this.f587r.widthPixels) || g / 2.0f > ((float) this.f587r.heightPixels) || f2 > ((float) this.f587r.widthPixels) || g > ((float) this.f587r.heightPixels)) {
                z = false;
                if (p != null) {
                    p.onFailedToReceiveAd(NendError.AD_SIZE_TOO_LARGE);
                }
            }
            switch (aVar.mo7766a()) {
                case ADVIEW:
                    m457b(aVar);
                    break;
                case WEBVIEW:
                    m458c(aVar);
                    break;
                case DYNAMICRETARGETING:
                    m459d(aVar);
                    break;
                default:
                    if (!f570a) {
                        throw new AssertionError();
                    } else if (p != null) {
                        p.onFailedToReceiveAd(NendError.INVALID_RESPONSE_TYPE);
                        return;
                    } else {
                        return;
                    }
            }
            if (p != null && z) {
                p.onReceiveAd();
            }
        } else if (p != null) {
            p.onFailedToReceiveAd(NendError.FAILED_AD_REQUEST);
        }
    }

    /* renamed from: b */
    private void m457b(C0521a aVar) {
        if (f570a || aVar != null) {
            this.f574e = C0522a.ADVIEW;
            this.f581l = C0755e.m1194a(aVar.mo7773h());
            this.f575f = aVar.mo7767b();
            this.f576g = aVar.mo7768c();
            this.f578i = aVar.mo7770e();
            this.f580k = aVar.mo7772g();
            this.f579j = aVar.mo7771f();
            this.f582m = aVar.mo7774i();
            this.f583n = aVar.mo7775j();
            this.f584o = aVar.mo7776k();
            this.f577h = null;
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: c */
    private void m458c(C0521a aVar) {
        if (f570a || aVar != null) {
            this.f574e = C0522a.WEBVIEW;
            this.f577h = aVar.mo7769d();
            this.f575f = null;
            this.f576g = null;
            this.f578i = null;
            this.f582m = null;
            this.f583n = null;
            this.f584o = aVar.mo7776k();
            this.f580k = aVar.mo7772g();
            this.f579j = aVar.mo7771f();
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: d */
    private void m459d(C0521a aVar) {
        if (f570a || aVar != null) {
            this.f574e = C0522a.DYNAMICRETARGETING;
            this.f577h = aVar.mo7769d();
            this.f575f = null;
            this.f576g = null;
            this.f578i = null;
            this.f582m = null;
            this.f583n = null;
            this.f584o = aVar.mo7776k();
            this.f580k = aVar.mo7772g();
            this.f579j = aVar.mo7771f();
            this.f581l = C0755e.m1194a(aVar.mo7773h());
            return;
        }
        throw new AssertionError();
    }
}
