package net.nend.android.internal.p023ui.activities.video;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.widget.RelativeLayout.LayoutParams;
import net.nend.android.internal.p008b.p012c.C0538a;
import net.nend.android.internal.p023ui.views.video.C0731b.C0734b;
import net.nend.android.internal.p023ui.views.video.C0736d;
import net.nend.android.internal.p023ui.views.video.C0736d.C0739a;
import net.nend.android.internal.utilities.video.C0764b;

/* renamed from: net.nend.android.internal.ui.activities.video.NendAdInterstitialVideoActivity */
public class NendAdInterstitialVideoActivity extends C0687a<C0538a> {
    @Nullable

    /* renamed from: h */
    private ObjectAnimator f988h;

    /* renamed from: i */
    private C0736d f989i;

    /* renamed from: j */
    private C0739a f990j = new C0739a() {
        /* renamed from: a */
        public void mo8058a() {
            NendAdInterstitialVideoActivity.this.f1000c = true;
            NendAdInterstitialVideoActivity.this.mo8069d();
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    NendAdInterstitialVideoActivity.this.mo8064a(NendAdInterstitialVideoActivity.this.getApplicationContext());
                }
            }, 1000);
        }

        /* renamed from: b */
        public void mo8059b() {
            NendAdInterstitialVideoActivity.this.f1000c = true;
            NendAdInterstitialVideoActivity.this.mo8070e();
        }
    };
    /* access modifiers changed from: private */

    /* renamed from: k */
    public boolean f991k = false;
    /* access modifiers changed from: private */

    /* renamed from: l */
    public boolean f992l = false;

    /* renamed from: m */
    private C0734b f993m = new C0734b() {
        /* renamed from: a */
        public void mo8061a() {
            NendAdInterstitialVideoActivity.this.f992l = true;
            NendAdInterstitialVideoActivity.this.m1007f();
        }
    };

    public static Bundle newBundle(C0538a aVar, ResultReceiver resultReceiver, int i) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("videoAd", aVar);
        bundle.putParcelable("resultReceiver", resultReceiver);
        bundle.putInt("spotId", i);
        return bundle;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        this.f1004g = true;
        super.onCreate(bundle);
        if (!isFinishing() && this.f998a.getVisibility() == 0) {
            if (this.f999b != null) {
                this.f999b.setWebViewClientListener(this.f993m);
            }
            if (bundle == null) {
                m1003a(((C0538a) this.f1001d).f604h);
            } else {
                m1003a(Math.max(((C0538a) this.f1001d).f604h - C0764b.m1245a(this.f1002e), 0));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.f988h != null) {
            this.f988h.removeAllListeners();
            if (this.f988h.isStarted() || this.f988h.isRunning()) {
                this.f988h.cancel();
            }
        }
    }

    public void onBackPressed() {
        mo8068c();
        super.onBackPressed();
    }

    /* renamed from: a */
    private void m1003a(int i) {
        this.f988h = ObjectAnimator.ofFloat(this.f989i, "alpha", new float[]{0.0f, 1.0f});
        this.f988h.setDuration(1000);
        this.f988h.setStartDelay((long) (i * 1000));
        this.f988h.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                NendAdInterstitialVideoActivity.this.f991k = true;
                NendAdInterstitialVideoActivity.this.m1007f();
            }
        });
        this.f988h.start();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo8053a() {
        this.f989i = new C0736d(this, ((C0538a) this.f1001d).f605i, this.f990j);
        this.f989i.setAlpha(0.0f);
        this.f998a.addView(this.f989i, new LayoutParams(-1, -2));
        super.mo8053a();
    }

    /* access modifiers changed from: private */
    /* renamed from: f */
    public void m1007f() {
        if (this.f999b != null && this.f992l && this.f991k) {
            mo8065a((WebView) this.f999b, "showActionButton()");
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo8054a(boolean z) {
        this.f989i.setHideCallToAction(z);
    }
}
