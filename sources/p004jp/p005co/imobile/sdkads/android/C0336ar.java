package p004jp.p005co.imobile.sdkads.android;

import android.view.View;
import android.view.ViewGroup;

/* renamed from: jp.co.imobile.sdkads.android.ar */
final class C0336ar extends ImobileSdkAdListener {

    /* renamed from: a */
    final /* synthetic */ C0334ap f92a;

    /* renamed from: b */
    private final /* synthetic */ C0341f f93b;

    /* renamed from: c */
    private final /* synthetic */ int f94c;

    C0336ar(C0334ap apVar, C0341f fVar, int i) {
        this.f92a = apVar;
        this.f93b = fVar;
        this.f94c = i;
    }

    public final void onAdCloseCompleted() {
        this.f93b.mo7241a(C0345j.DISPLAYED);
        this.f92a.f90v.getOwnerActivity().setRequestedOrientation(this.f94c);
        this.f92a.f90v.dismiss();
        View findViewById = this.f92a.f90v.findViewById(985478646);
        if (findViewById != null) {
            ((ViewGroup) this.f92a.f90v.findViewById(16908290)).removeView(findViewById);
            C0359x.m125a(null);
        } else {
            C0359x.m126b("Ad Dialog close failed.", "");
        }
        this.f92a.f90v = null;
        this.f92a.f89u = null;
    }

    public final void onAdReadyCompleted() {
        C0359x.m125a(null);
        this.f92a.f90v.show();
        this.f93b.mo7190a();
        this.f92a.f207t.onAdShowCompleted();
    }
}
