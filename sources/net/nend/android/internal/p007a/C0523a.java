package net.nend.android.internal.p007a;

import android.text.TextUtils;
import net.nend.android.internal.utilities.video.NendVideoAdClientError;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: net.nend.android.internal.a.a */
/* compiled from: FailedRequestException */
public class C0523a extends Exception {

    /* renamed from: a */
    private final int f560a;

    /* renamed from: b */
    private final String f561b;

    public C0523a(NendVideoAdClientError nendVideoAdClientError) {
        this(nendVideoAdClientError.getCode(), nendVideoAdClientError.getMessage());
    }

    public C0523a(int i, String str) {
        super(str);
        this.f560a = i;
        this.f561b = m447a(i, str);
    }

    /* renamed from: a */
    public int mo7777a() {
        return this.f560a;
    }

    public String toString() {
        return "Status = " + this.f560a + ": " + this.f561b;
    }

    /* renamed from: a */
    private String m447a(int i, String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return new JSONObject(str).getString("message");
            } catch (JSONException e) {
                return str;
            }
        } else {
            switch (i) {
                case 204:
                    return "No fill ad.";
                default:
                    return "Unknown error. Response body is empty.";
            }
        }
    }
}
