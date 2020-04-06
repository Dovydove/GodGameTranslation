package p004jp.p005co.imobile.sdkads.android;

import android.graphics.Rect;

/* renamed from: jp.co.imobile.sdkads.android.b */
final class C0337b extends ImobileSdkAdListener {

    /* renamed from: a */
    final /* synthetic */ C0318a f95a;

    /* renamed from: b */
    private final /* synthetic */ Rect f96b;

    C0337b(C0318a aVar, Rect rect) {
        this.f95a = aVar;
        this.f96b = rect;
    }

    public final void onAdReadyCompleted() {
        try {
            StringBuilder sb = new StringBuilder("javascript:ShowAdBefore('");
            C0353r.m99a();
            sb.append(C0353r.m96a(this.f95a.f58o, ImobileSdkAd.m43d(), this.f96b)).append("');");
            C0359x.m125a(null);
            C0340e eVar = this.f95a.f107c;
            StringBuilder sb2 = new StringBuilder("javascript:ShowAdBefore('");
            C0353r.m99a();
            eVar.mo7215a(sb2.append(C0353r.m96a(this.f95a.f58o, ImobileSdkAd.m43d(), this.f96b)).append("');").toString());
            this.f95a.f109e = null;
        } catch (C0360y e) {
            this.f95a.f117m.onFailed(FailNotificationReason.UNKNOWN);
        }
    }
}
