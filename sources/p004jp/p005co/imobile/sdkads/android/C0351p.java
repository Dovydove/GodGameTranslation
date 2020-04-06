package p004jp.p005co.imobile.sdkads.android;

import java.util.Map.Entry;
import java.util.TimerTask;

/* renamed from: jp.co.imobile.sdkads.android.p */
final class C0351p extends TimerTask {

    /* renamed from: b */
    private static /* synthetic */ int[] f143b;

    /* renamed from: a */
    final /* synthetic */ ImobileSdkAd f144a;

    C0351p(ImobileSdkAd imobileSdkAd) {
        this.f144a = imobileSdkAd;
    }

    /* renamed from: a */
    private static /* synthetic */ int[] m93a() {
        int[] iArr = f143b;
        if (iArr == null) {
            iArr = new int[C0331am.m57a().length];
            try {
                iArr[C0331am.ERROR.ordinal()] = 6;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[C0331am.LODING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[C0331am.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[C0331am.PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[C0331am.START.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[C0331am.STOP.ordinal()] = 5;
            } catch (NoSuchFieldError e6) {
            }
            f143b = iArr;
        }
        return iArr;
    }

    public final void run() {
        C0359x.m125a(null);
        for (Entry entry : this.f144a.f43b.entrySet()) {
            new StringBuilder("Spot status:").append(((C0361z) entry.getValue()).mo7269a()).append(" on spot:").append(((C0361z) entry.getValue()).f190c);
            C0359x.m125a(null);
            switch (m93a()[((C0361z) entry.getValue()).mo7269a().ordinal()]) {
                case 3:
                    C0361z zVar = (C0361z) entry.getValue();
                    this.f144a.f48g;
                    zVar.mo7282i();
                    break;
                case 6:
                    ((C0361z) entry.getValue()).mo7281h();
                    break;
            }
        }
    }
}
