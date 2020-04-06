package net.nend.android.internal.p023ui.activities.video;

import android.os.Bundle;
import android.os.ResultReceiver;
import net.nend.android.internal.p008b.p012c.C0542c;

/* renamed from: net.nend.android.internal.ui.activities.video.NendAdRewardedVideoActivity */
public class NendAdRewardedVideoActivity extends C0687a<C0542c> {
    public static Bundle newBundle(C0542c cVar, ResultReceiver resultReceiver, int i) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("videoAd", cVar);
        bundle.putParcelable("resultReceiver", resultReceiver);
        bundle.putInt("spotId", i);
        return bundle;
    }

    public void onBackPressed() {
        if (this.f998a.getVisibility() == 8) {
            mo8068c();
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        this.f1004g = false;
        super.onCreate(bundle);
    }
}
