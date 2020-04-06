package p004jp.p005co.imobile.sdkads.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* renamed from: jp.co.imobile.sdkads.android.o */
final class C0350o extends BroadcastReceiver {

    /* renamed from: a */
    final /* synthetic */ ImobileSdkAd f142a;

    C0350o(ImobileSdkAd imobileSdkAd) {
        this.f142a = imobileSdkAd;
    }

    public final void onReceive(Context context, Intent intent) {
        C0353r.m99a();
        if (C0353r.m106b().equals("")) {
            C0359x.m125a(null);
            if (this.f142a.f51j != null) {
                C0359x.m125a(null);
                this.f142a.m36a(Boolean.valueOf(false));
                return;
            }
            return;
        }
        C0359x.m125a(null);
        if (this.f142a.f51j == null) {
            C0359x.m125a(null);
            ImobileSdkAd.startAll();
        }
    }
}
