package net.nend.android.internal.p008b.p011b;

import android.app.Activity;
import android.content.Context;
import net.nend.android.NendAdNative;
import net.nend.android.NendAdNative.AdvertisingExplicitly;
import net.nend.android.NendNativeAdConnector;
import net.nend.android.internal.utilities.C0740a;
import net.nend.android.internal.utilities.C0740a.C0742a;
import net.nend.android.internal.utilities.C0740a.C0745d;
import net.nend.android.internal.utilities.C0755e;

/* renamed from: net.nend.android.internal.b.b.a */
/* compiled from: NendNativeAdConnectorImpl */
public class C0534a implements NendNativeAdConnector {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public final NendAdNative f595a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public String f596b;

    public C0534a(NendAdNative nendAdNative) {
        this.f595a = nendAdNative;
    }

    public void setSpotId(String str) {
        this.f596b = str;
    }

    public String getShortText() {
        return this.f595a.getTitleText();
    }

    public String getLongText() {
        return this.f595a.getContentText();
    }

    public String getPromotionUrl() {
        return this.f595a.getPromotionUrl();
    }

    public String getPromotionName() {
        return this.f595a.getPromotionName();
    }

    public String getActionButtonText() {
        return this.f595a.getActionText();
    }

    public String getAdImageUrl() {
        return this.f595a.getAdImageUrl();
    }

    public String getLogoImageUrl() {
        return this.f595a.getLogoImageUrl();
    }

    public void sendImpression() {
        this.f595a.onImpression();
    }

    public void performAdClick(final Activity activity) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                C0755e.m1198a((Context) activity, C0534a.this.f595a.getClickUrl());
            }
        });
    }

    public void performInformationClick(final Activity activity) {
        final String b = C0755e.m1201b(activity.getApplicationContext());
        C0740a.m1170a().mo8183a(new C0745d(activity.getApplicationContext()), new C0742a<String>() {
            /* renamed from: a */
            public void mo7491a(String str, Exception exc) {
                final String str2 = "https://www.nend.net/privacy/optsdkgate?uid=" + b + "&spot=" + C0534a.this.f596b + "&gaid=" + str;
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        C0755e.m1198a((Context) activity, str2);
                    }
                });
            }
        });
    }

    public String getAdvertisingExplicitlyText(int i) {
        switch (i) {
            case 0:
                return AdvertisingExplicitly.PR.getText();
            case 1:
                return AdvertisingExplicitly.SPONSORED.getText();
            case 2:
                return AdvertisingExplicitly.AD.getText();
            case 3:
                return AdvertisingExplicitly.PROMOTION.getText();
            default:
                return "";
        }
    }
}
