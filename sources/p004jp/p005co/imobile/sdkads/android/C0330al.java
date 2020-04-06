package p004jp.p005co.imobile.sdkads.android;

/* renamed from: jp.co.imobile.sdkads.android.al */
final class C0330al implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C0329ak f74a;

    /* renamed from: b */
    private final /* synthetic */ C0361z f75b;

    C0330al(C0329ak akVar, C0361z zVar) {
        this.f74a = akVar;
        this.f75b = zVar;
    }

    public final void run() {
        if (this.f74a.f71a.f198k > 0) {
            for (int i = 0; this.f74a.f71a.f198k > i; i++) {
                try {
                    new StringBuilder("spot id : ").append(this.f74a.f71a.f190c);
                    C0359x.m125a(null);
                    C0318a aVar = new C0318a(this.f75b, ImobileSdkAd.m32a(), this.f74a.f71a.f207t, true);
                    C0361z zVar = this.f75b;
                    ImobileSdkAd.m32a();
                    aVar.mo7242a(zVar);
                    this.f74a.f71a.f202o.add(aVar);
                } catch (C0360y e) {
                    this.f74a.f71a.f207t.onFailed(e.mo7267a());
                }
            }
        }
        synchronized (this) {
            this.f74a.f71a.mo7273a(C0331am.START);
        }
    }
}
