package net.nend.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import net.nend.android.NendAdFullBoard.FullBoardAdListener;
import net.nend.android.NendAdFullBoardLoader.Callback;
import net.nend.android.NendAdFullBoardLoader.FullBoardAdError;
import net.nend.android.internal.p007a.C0523a;
import net.nend.android.internal.p008b.p012c.C0538a;
import net.nend.android.internal.p022d.C0629c;
import net.nend.android.internal.p022d.C0631e;
import net.nend.android.internal.p022d.C0636g;
import net.nend.android.internal.p022d.C0646k;
import net.nend.android.internal.p022d.C0647l;
import net.nend.android.internal.p023ui.activities.video.NendAdInterstitialVideoActivity;
import net.nend.android.internal.utilities.C0757f;
import net.nend.android.internal.utilities.C0758g;
import net.nend.android.internal.utilities.video.NendVideoAdClientError;

public class NendAdInterstitialVideo extends C0516a<C0538a, NendAdVideoListener> {
    @VisibleForTesting

    /* renamed from: k */
    private int f347k;

    /* renamed from: l */
    private String f348l;
    /* access modifiers changed from: private */

    /* renamed from: m */
    public FullBoardAdListener f349m = new FullBoardAdListener() {
        public void onDismissAd(NendAdFullBoard nendAdFullBoard) {
            NendAdInterstitialVideo.this.f548j.send(1, null);
        }

        public void onShowAd(NendAdFullBoard nendAdFullBoard) {
            NendAdInterstitialVideo.this.f548j.send(2, null);
        }

        public void onClickAd(NendAdFullBoard nendAdFullBoard) {
            NendAdInterstitialVideo.this.f548j.send(5, null);
        }
    };

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

    public NendAdInterstitialVideo(Context context, int i, String str) {
        super(context, i, str);
    }

    public void setAdListener(NendAdVideoListener nendAdVideoListener) {
        this.f546h = nendAdVideoListener;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public C0646k<C0538a> mo7527a() {
        C0646k<C0538a> a = this.mVideoAdLoader.mo7982a(this.f539a, this.f540b, this.f542d, this.f543e);
        if (this.f347k > 0 && !TextUtils.isEmpty(this.f348l)) {
            return a.mo7997a((C0636g<Throwable, ? extends T>) new C0636g<Throwable, C0538a>() {
                /* renamed from: a */
                public C0538a mo7540a(Throwable th) {
                    C0757f.m1217b("Failed to load Interstitial Ad. Fallback full board ad.");
                    return null;
                }
            }).mo7995a((C0629c<? super T, Throwable, ? extends C0646k<? extends R>>) new C0629c<C0538a, Throwable, C0646k<C0538a>>() {
                /* renamed from: a */
                public C0646k<C0538a> mo7538a(C0538a aVar, Throwable th) {
                    if (aVar != null) {
                        return C0647l.m921a(aVar);
                    }
                    return NendAdInterstitialVideo.this.m246b();
                }
            });
        }
        C0757f.m1217b("You can use fallback option at Interstitial Ad. Let's check the wiki.");
        return a;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public Intent mo7526a(Activity activity) {
        Intent intent = new Intent(activity, NendAdInterstitialVideoActivity.class);
        intent.putExtras(NendAdInterstitialVideoActivity.newBundle((C0538a) this.f544f, this.f548j, this.f539a));
        return intent;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo7529b(Activity activity) {
        if (((C0538a) this.f544f).f606j != null) {
            ((C0538a) this.f544f).f606j.show(activity);
        } else {
            super.mo7529b(activity);
        }
    }

    public void addFallbackFullboard(int i, String str) {
        if (i <= 0) {
            throw new IllegalArgumentException(C0758g.ERR_INVALID_SPOT_ID.mo8197a("spot id : " + i));
        } else if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(C0758g.ERR_INVALID_API_KEY.mo8197a("api key : " + str));
        } else {
            this.f347k = i;
            this.f348l = str;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public C0646k<C0538a> m246b() {
        final C0631e a = C0647l.m920a();
        new NendAdFullBoardLoader(this.f541c, this.f347k, this.f348l).loadAd(new Callback() {
            public void onSuccess(NendAdFullBoard nendAdFullBoard) {
                nendAdFullBoard.setAdListener(NendAdInterstitialVideo.this.f349m);
                a.mo7991a(C0538a.m496a(nendAdFullBoard));
            }

            public void onFailure(FullBoardAdError fullBoardAdError) {
                a.mo7992a((Throwable) new C0523a(NendVideoAdClientError.FAILED_AD_FALLBACK));
            }
        });
        return a.mo7990a();
    }
}
