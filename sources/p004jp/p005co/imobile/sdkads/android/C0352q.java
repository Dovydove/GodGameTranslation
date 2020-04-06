package p004jp.p005co.imobile.sdkads.android;

import android.app.Activity;
import android.graphics.Point;
import android.view.ViewGroup;

/* renamed from: jp.co.imobile.sdkads.android.q */
final class C0352q extends ImobileSdkAdListener {

    /* renamed from: a */
    final /* synthetic */ ImobileSdkAd f145a;

    /* renamed from: b */
    private final /* synthetic */ C0361z f146b;

    /* renamed from: c */
    private final /* synthetic */ Activity f147c;

    /* renamed from: d */
    private final /* synthetic */ ImobileSdkAdListener f148d;

    /* renamed from: e */
    private final /* synthetic */ Point f149e;

    /* renamed from: f */
    private final /* synthetic */ Boolean f150f;

    /* renamed from: g */
    private final /* synthetic */ ViewGroup f151g;

    /* renamed from: h */
    private final /* synthetic */ ImobileIconParams f152h;

    /* renamed from: i */
    private final /* synthetic */ Boolean f153i;

    C0352q(ImobileSdkAd imobileSdkAd, C0361z zVar, Activity activity, ImobileSdkAdListener imobileSdkAdListener, Point point, Boolean bool, ViewGroup viewGroup, ImobileIconParams imobileIconParams, Boolean bool2) {
        this.f145a = imobileSdkAd;
        this.f146b = zVar;
        this.f147c = activity;
        this.f148d = imobileSdkAdListener;
        this.f149e = point;
        this.f150f = bool;
        this.f151g = viewGroup;
        this.f152h = imobileIconParams;
        this.f153i = bool2;
    }

    public final void onAdReadyCompleted() {
        this.f146b.mo7205a(this.f147c, this.f148d, this.f149e, this.f150f, this.f151g, this.f152h, this.f153i);
    }
}
