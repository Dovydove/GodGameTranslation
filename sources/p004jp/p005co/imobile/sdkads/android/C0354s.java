package p004jp.p005co.imobile.sdkads.android;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

/* renamed from: jp.co.imobile.sdkads.android.s */
final class C0354s implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C0353r f175a;

    /* renamed from: b */
    private final /* synthetic */ Context f176b;

    C0354s(C0353r rVar, Context context) {
        this.f175a = rVar;
        this.f176b = context;
    }

    public final void run() {
        try {
            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.f176b);
            if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                C0359x.m125a(null);
                return;
            }
            this.f175a.f167m = advertisingIdInfo.getId();
            new StringBuilder("AdvertisingId get success. (advertisementId: ").append(this.f175a.f167m).append(")");
            C0359x.m125a(null);
        } catch (IOException e) {
            C0359x.m125a(e);
        } catch (GooglePlayServicesNotAvailableException e2) {
            C0359x.m125a(e2);
        } catch (IllegalStateException e3) {
            C0359x.m125a(e3);
            e3.printStackTrace();
        } catch (GooglePlayServicesRepairableException e4) {
            C0359x.m125a(e4);
        }
    }
}
