package net.nend.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import net.nend.android.internal.p008b.p012c.C0542c;
import net.nend.android.internal.p022d.C0646k;
import net.nend.android.internal.p023ui.activities.video.NendAdRewardedVideoActivity;

public class NendAdRewardedVideo extends C0516a<C0542c, NendAdRewardedListener> {
    public /* bridge */ /* synthetic */ boolean isLoaded() {
        return super.isLoaded();
    }

    public /* bridge */ /* synthetic */ void loadAd() {
        super.loadAd();
    }

    public /* bridge */ /* synthetic */ void releaseAd() {
        super.releaseAd();
    }

    public /* bridge */ /* synthetic */ void setMediationName(String str) {
        super.setMediationName(str);
    }

    public /* bridge */ /* synthetic */ void setUserFeature(NendAdUserFeature nendAdUserFeature) {
        super.setUserFeature(nendAdUserFeature);
    }

    public /* bridge */ /* synthetic */ void setUserId(String str) {
        super.setUserId(str);
    }

    public /* bridge */ /* synthetic */ void showAd(Activity activity) {
        super.showAd(activity);
    }

    public NendAdRewardedVideo(Context context, int i, String str) {
        super(context, i, str);
    }

    public void setAdListener(NendAdRewardedListener nendAdRewardedListener) {
        this.f546h = nendAdRewardedListener;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo7714a(boolean z) {
        if (z && this.f546h != null) {
            ((NendAdRewardedListener) this.f546h).onRewarded(this, new NendAdRewardItem(((C0542c) this.f544f).f616h, ((C0542c) this.f544f).f617i));
        }
        super.mo7714a(z);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public C0646k<C0542c> mo7527a() {
        return this.mVideoAdLoader.mo7985b(this.f539a, this.f540b, this.f542d, this.f543e);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public Intent mo7526a(Activity activity) {
        Intent intent = new Intent(activity, NendAdRewardedVideoActivity.class);
        intent.putExtras(NendAdRewardedVideoActivity.newBundle((C0542c) this.f544f, this.f548j, this.f539a));
        return intent;
    }
}
