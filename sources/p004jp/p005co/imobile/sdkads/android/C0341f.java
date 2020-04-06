package p004jp.p005co.imobile.sdkads.android;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Handler;
import android.widget.RelativeLayout;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* renamed from: jp.co.imobile.sdkads.android.f */
abstract class C0341f {

    /* renamed from: a */
    protected Date f105a;

    /* renamed from: b */
    protected Map f106b = new HashMap();

    /* renamed from: c */
    protected C0340e f107c;

    /* renamed from: d */
    protected Boolean f108d = Boolean.valueOf(false);

    /* renamed from: e */
    protected ImobileSdkAdListener f109e;

    /* renamed from: f */
    protected Date f110f;

    /* renamed from: g */
    protected Rect f111g = null;

    /* renamed from: h */
    protected int f112h = 0;

    /* renamed from: i */
    protected int f113i = 0;

    /* renamed from: j */
    protected ImobileIconParams f114j = null;

    /* renamed from: k */
    protected Boolean f115k = Boolean.valueOf(false);

    /* renamed from: l */
    protected ImobileSdkAdListener f116l;

    /* renamed from: m */
    protected ImobileSdkAdListener f117m;

    /* renamed from: n */
    private C0345j f118n = C0345j.NONE;

    /* renamed from: o */
    private final Handler f119o = new Handler();

    C0341f(ImobileSdkAdListener imobileSdkAdListener) {
        this.f117m = imobileSdkAdListener;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public abstract RelativeLayout mo7189a(Activity activity);

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public abstract void mo7190a();

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo7240a(ImobileIconParams imobileIconParams) {
        this.f114j = imobileIconParams;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public abstract void mo7191a(ImobileSdkAdListener imobileSdkAdListener, Rect rect);

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo7241a(C0345j jVar) {
        this.f118n = jVar;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo7242a(C0361z zVar) {
        this.f118n = C0345j.LODING;
        this.f105a = null;
        this.f108d = Boolean.valueOf(false);
        this.f110f = new Date();
        this.f119o.post(new C0342g(this, zVar));
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public abstract Date mo7192b();

    /* access modifiers changed from: 0000 */
    /* renamed from: c */
    public abstract Date mo7193c();

    /* access modifiers changed from: 0000 */
    /* renamed from: d */
    public final C0345j mo7243d() {
        return this.f118n;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public final int mo7244e() {
        return this.f112h;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: f */
    public final int mo7245f() {
        return this.f113i;
    }
}
