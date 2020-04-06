package net.nend.android.internal.utilities;

import java.util.Locale;

/* renamed from: net.nend.android.internal.utilities.g */
/* compiled from: NendStatus */
public enum C0758g {
    SUCCESS(200, "Success!"),
    ERR_EXCESSIVE_AD_CALLS(332, "Excessive ad calls."),
    INITIAL(800, "Initial state"),
    ERR_INVALID_ATTRIBUTE_SET(811, "AttributeSet is invalid."),
    ERR_INVALID_API_KEY(812, "Api key is invalid."),
    ERR_INVALID_SPOT_ID(813, "Spot id is invalid."),
    ERR_INVALID_CONTEXT(814, "Context is invalid."),
    ERR_INVALID_URL(815, "Url is invalid."),
    ERR_INVALID_RESPONSE(814, "Response is invalid."),
    ERR_INVALID_AD_STATUS(815, "Ad status is invalid."),
    ERR_INVALID_RESPONSE_TYPE(816, "Response type is invalid."),
    ERR_INVALID_ICON_COUNT(817, "Icon count is invalid."),
    ERR_NO_AD_IMAGE(818, "This Ad has no Ad image."),
    ERR_NO_LOGO_IMAGE(819, "This Ad has no logo image"),
    ERR_HTTP_REQUEST(820, "Failed to http request."),
    ERR_FAILED_TO_PARSE(821, "Failed to parse response."),
    ERR_OUT_OF_STOCK(830, "Ad is out of stock."),
    ERR_VALIDATION_BINDER_SETTING(849, "There is a problem with the set in NendAdNativeBinder."),
    ERR_UNEXPECTED(899, "Unexpected error.");
    

    /* renamed from: t */
    private final int f1173t;

    /* renamed from: u */
    private final String f1174u;

    private C0758g(int i, String str) {
        this.f1173t = i;
        this.f1174u = str;
    }

    /* renamed from: a */
    public int mo8196a() {
        return this.f1173t;
    }

    /* renamed from: b */
    public String mo8198b() {
        return String.format(Locale.US, "[%s%d] %s", new Object[]{"AE", Integer.valueOf(this.f1173t), this.f1174u});
    }

    /* renamed from: a */
    public String mo8197a(String str) {
        return String.format(Locale.US, "[%s%d] %s %s", new Object[]{"AE", Integer.valueOf(this.f1173t), this.f1174u, str});
    }
}
