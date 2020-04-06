package net.nend.android.internal.utilities.video;

public enum NendVideoAdClientError {
    FAILED_INTERNAL(600, "Internal error at nendSDK Video process."),
    FAILED_AD_DOWNLOAD(601, "Failed to Ad download."),
    FAILED_AD_FALLBACK(602, "Failed to fallback fullboard ad."),
    NETWORK_IS_NOT_ACTIVE(603, "Network is not active."),
    INVALID_AD_DATA(604, "ad data is not found or ad is expired.");
    

    /* renamed from: a */
    private final int f1178a;

    /* renamed from: b */
    private final String f1179b;

    private NendVideoAdClientError(int i, String str) {
        this.f1178a = i;
        this.f1179b = str;
    }

    public String getMessage() {
        return this.f1179b;
    }

    public int getCode() {
        return this.f1178a;
    }
}
