package p004jp.p005co.imobile.sdkads.android;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import android.widget.RelativeLayout;

/* renamed from: jp.co.imobile.sdkads.android.ao */
final class C0333ao extends ImobileSdkAdListener {

    /* renamed from: a */
    final /* synthetic */ C0332an f84a;

    /* renamed from: b */
    private final /* synthetic */ ViewGroup f85b;

    /* renamed from: c */
    private final /* synthetic */ RelativeLayout f86c;

    /* renamed from: d */
    private final /* synthetic */ C0341f f87d;

    /* renamed from: e */
    private final /* synthetic */ Activity f88e;

    C0333ao(C0332an anVar, ViewGroup viewGroup, RelativeLayout relativeLayout, C0341f fVar, Activity activity) {
        this.f84a = anVar;
        this.f85b = viewGroup;
        this.f86c = relativeLayout;
        this.f87d = fVar;
        this.f88e = activity;
    }

    public final void onAdCloseCompleted() {
        this.f87d.mo7241a(C0345j.DISPLAYED);
        this.f84a.f207t.onAdCloseCompleted();
        this.f84a.f83u = null;
    }

    public final void onAdReadyCompleted() {
        C0359x.m125a(null);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        if (this.f85b != null) {
            this.f85b.addView(this.f86c);
        } else {
            layoutParams.topMargin = this.f87d.mo7244e();
            layoutParams.leftMargin = this.f87d.mo7245f();
            layoutParams.gravity = 48;
            this.f88e.addContentView(this.f86c, layoutParams);
        }
        this.f87d.mo7190a();
        this.f84a.f207t.onAdShowCompleted();
    }

    public final void onDismissAdScreen() {
        this.f84a.f207t.onDismissAdScreen();
    }
}
