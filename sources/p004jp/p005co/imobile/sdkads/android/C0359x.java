package p004jp.p005co.imobile.sdkads.android;

import android.util.Log;

/* renamed from: jp.co.imobile.sdkads.android.x */
final class C0359x {
    C0359x() {
    }

    /* renamed from: a */
    static void m124a(String str, String str2) {
        Log.d("i-mobile SDK ADS ERROR", "[ErrorLog]" + str + " Message:" + str2);
    }

    /* renamed from: a */
    static void m125a(Throwable th) {
        if (ImobileSdkAd.m42c().booleanValue() && th != null) {
            th.printStackTrace();
        }
    }

    /* renamed from: b */
    static void m126b(String str, String str2) {
        if (ImobileSdkAd.m42c().booleanValue()) {
            Log.d("i-mobile SDK ADS DEBUG", "[ForceLog]" + str + " Message:" + str2);
        }
    }
}
