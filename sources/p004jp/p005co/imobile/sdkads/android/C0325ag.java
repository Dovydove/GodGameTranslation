package p004jp.p005co.imobile.sdkads.android;

/* renamed from: jp.co.imobile.sdkads.android.ag */
final class C0325ag implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C0319aa f65a;

    /* renamed from: b */
    private final /* synthetic */ FailNotificationReason f66b;

    C0325ag(C0319aa aaVar, FailNotificationReason failNotificationReason) {
        this.f65a = aaVar;
        this.f66b = failNotificationReason;
    }

    public final void run() {
        this.f65a.f59a.f203p.onFailed(this.f66b);
    }
}
