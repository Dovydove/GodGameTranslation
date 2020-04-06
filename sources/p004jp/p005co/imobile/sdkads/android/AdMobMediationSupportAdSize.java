package p004jp.p005co.imobile.sdkads.android;

/* renamed from: jp.co.imobile.sdkads.android.AdMobMediationSupportAdSize */
public enum AdMobMediationSupportAdSize {
    BUNNER(320, 50),
    LARGE_BANNER(320, 100),
    MEDIUM_RECTANGLE(300, 250);
    

    /* renamed from: a */
    private final int f29a;

    /* renamed from: b */
    private final int f30b;

    private AdMobMediationSupportAdSize(int width, int height) {
        this.f30b = width;
        this.f29a = height;
    }

    public final int getHeight() {
        return this.f29a;
    }

    public final int getWidth() {
        return this.f30b;
    }
}
