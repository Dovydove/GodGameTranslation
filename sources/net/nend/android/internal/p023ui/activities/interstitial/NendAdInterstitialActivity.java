package net.nend.android.internal.p023ui.activities.interstitial;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup.LayoutParams;
import net.nend.android.NendAdInterstitial;
import net.nend.android.internal.p023ui.views.p026b.C0714a;
import net.nend.android.internal.p023ui.views.p026b.C0714a.C0715a;

/* renamed from: net.nend.android.internal.ui.activities.interstitial.NendAdInterstitialActivity */
public class NendAdInterstitialActivity extends Activity {

    /* renamed from: a */
    private C0714a f985a;

    /* renamed from: b */
    private C0715a f986b = new C0715a() {
        /* renamed from: a */
        public void mo8052a() {
            NendAdInterstitialActivity.this.finish();
        }
    };

    public static Bundle newBundle(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("ARGS_SPOT_ID", i);
        return bundle;
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.f985a = NendAdInterstitial.getInterstitialAdView(getIntent().getIntExtra("ARGS_SPOT_ID", -1));
        if (this.f985a == null || this.f985a.getParent() != null) {
            finish();
            return;
        }
        this.f985a.setDismissDelegate(this.f986b);
        this.f985a.setOrientation(getResources().getConfiguration().orientation);
        setContentView(this.f985a, new LayoutParams(-1, -1));
    }

    public void onBackPressed() {
        this.f985a.mo8123d();
        super.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.f985a != null) {
            this.f985a.setDismissDelegate(null);
        }
    }
}
