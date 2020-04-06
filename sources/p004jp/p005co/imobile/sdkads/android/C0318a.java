package p004jp.p005co.imobile.sdkads.android;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import java.util.Date;

/* renamed from: jp.co.imobile.sdkads.android.a */
final class C0318a extends C0341f {
    /* access modifiers changed from: private */

    /* renamed from: n */
    public RelativeLayout f57n;
    /* access modifiers changed from: private */

    /* renamed from: o */
    public Activity f58o = null;

    C0318a(C0361z zVar, Context context, ImobileSdkAdListener imobileSdkAdListener, boolean z) {
        super(imobileSdkAdListener);
        Boolean valueOf = Boolean.valueOf(z);
        this.f57n = new RelativeLayout(context);
        this.f57n.setLayoutParams(new LayoutParams(-2, -2));
        this.f57n.setId(985478646);
        this.f57n.setBackgroundColor(Color.argb(0, 0, 0, 0));
        this.f57n.setClickable(true);
        this.f57n.setOnClickListener(new C0338c(this));
        this.f107c = new C0340e(context, valueOf);
        this.f107c.setBackgroundColor(0);
        this.f107c.setVerticalScrollbarOverlay(true);
        this.f107c.getSettings().setAppCacheEnabled(true);
        if (VERSION.SDK_INT > 10 && this.f107c.isHardwareAccelerated()) {
            this.f107c.setLayerType(1, null);
        }
        this.f107c.getSettings().setJavaScriptEnabled(true);
        this.f107c.mo7214a((WebViewClient) new C0339d(this, zVar));
        this.f57n.addView(this.f107c, -1, -1);
    }

    /* renamed from: a */
    static /* synthetic */ void m47a(C0318a aVar, int i, int i2, int i3, int i4) {
        aVar.f113i = i;
        aVar.f112h = i2;
        aVar.f57n.getLayoutParams().width = i3;
        aVar.f57n.getLayoutParams().height = i4;
        aVar.f107c.getLayoutParams().width = i3;
        aVar.f107c.getLayoutParams().height = i4;
        aVar.f57n.requestLayout();
    }

    /* renamed from: d */
    static /* synthetic */ void m50d(C0318a aVar) {
        if (aVar.f116l != null) {
            aVar.f116l.onAdCloseCompleted();
            aVar.f116l = null;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final RelativeLayout mo7189a(Activity activity) {
        C0359x.m125a(null);
        if (activity != null) {
            this.f58o = activity;
        }
        return this.f57n;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo7190a() {
        C0359x.m125a(null);
        this.f107c.mo7215a("javascript:ShowComplete();");
        mo7241a(C0345j.DISPLAING);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo7191a(ImobileSdkAdListener imobileSdkAdListener, Rect rect) {
        C0359x.m125a(null);
        if (this.f58o == null) {
            C0359x.m125a(null);
            throw new C0360y(FailNotificationReason.UNKNOWN);
        }
        this.f116l = imobileSdkAdListener;
        if (this.f108d.booleanValue()) {
            C0359x.m125a(null);
            StringBuilder sb = new StringBuilder("javascript:ShowAdBefore('");
            C0353r.m99a();
            sb.append(C0353r.m96a(this.f58o, ImobileSdkAd.m43d(), rect)).append("');");
            C0359x.m125a(null);
            C0340e eVar = this.f107c;
            StringBuilder sb2 = new StringBuilder("javascript:ShowAdBefore('");
            C0353r.m99a();
            eVar.mo7215a(sb2.append(C0353r.m96a(this.f58o, ImobileSdkAd.m43d(), rect)).append("');").toString());
        } else {
            C0359x.m125a(null);
            this.f109e = new C0337b(this, rect);
        }
        C0359x.m125a(null);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public final Date mo7192b() {
        return this.f105a;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: c */
    public final Date mo7193c() {
        return this.f110f;
    }
}
