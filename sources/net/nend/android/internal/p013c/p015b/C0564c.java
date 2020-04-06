package net.nend.android.internal.p013c.p015b;

import android.content.Context;
import net.nend.android.NendAdNative;
import net.nend.android.NendAdNative.C0482a;
import net.nend.android.NendAdNativeMediaView;
import net.nend.android.internal.p007a.C0524b;
import net.nend.android.internal.p013c.C0566c;
import net.nend.android.internal.utilities.C0758g;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: net.nend.android.internal.c.b.c */
/* compiled from: NendAdNativeResponseParser */
final class C0564c extends C0566c<NendAdNative> {
    public C0564c(Context context) {
        super(context);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public NendAdNative mo7864a(int i) {
        if (C0758g.ERR_EXCESSIVE_AD_CALLS.mo8196a() == i) {
            return new C0482a().mo7580a();
        }
        return null;
    }

    /* renamed from: b */
    public NendAdNative mo7847a(C0567a aVar, JSONObject jSONObject) {
        switch (aVar) {
            case NATIVE_NORMAL:
                return m597a(jSONObject);
            case NATIVE_APP_TARGETING:
                return m598b(jSONObject);
            default:
                throw new C0524b(C0758g.ERR_INVALID_RESPONSE_TYPE);
        }
    }

    /* renamed from: a */
    private NendAdNative m597a(JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject.getJSONObject("default_ad");
        C0482a j = new C0482a().mo7582c(jSONObject2.getString(NendAdNativeMediaView.RESULT_CLICK_URL)).mo7583d(jSONObject2.getString("impression_count_url")).mo7584e(jSONObject2.getString("short_text")).mo7585f(jSONObject2.getString("long_text")).mo7586g(jSONObject2.getString("promotion_url")).mo7587h(jSONObject2.getString("promotion_name")).mo7588i(jSONObject2.getString("action_button_text")).mo7589j(jSONObject2.getString("campaign_id"));
        if (jSONObject2.has("ad_image")) {
            j.mo7579a(jSONObject2.getJSONObject("ad_image").getString("image_url"));
        }
        if (jSONObject2.has("logo_image")) {
            j.mo7581b(jSONObject2.getJSONObject("logo_image").getString("image_url"));
        }
        return j.mo7580a();
    }

    /* renamed from: b */
    private NendAdNative m598b(JSONObject jSONObject) {
        JSONArray jSONArray = jSONObject.getJSONArray("targeting_ads");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            JSONArray jSONArray2 = jSONObject2.getJSONArray("conditions");
            int length2 = jSONArray2.length();
            for (int i2 = 0; i2 < length2; i2++) {
                if (mo7868a(jSONArray2.getJSONArray(i2))) {
                    C0482a j = new C0482a().mo7582c(jSONObject2.getString(NendAdNativeMediaView.RESULT_CLICK_URL)).mo7583d(jSONObject2.getString("impression_count_url")).mo7584e(jSONObject2.getString("short_text")).mo7585f(jSONObject2.getString("long_text")).mo7586g(jSONObject2.getString("promotion_url")).mo7587h(jSONObject2.getString("promotion_name")).mo7588i(jSONObject2.getString("action_button_text")).mo7589j(jSONObject2.getString("campaign_id"));
                    if (jSONObject2.has("ad_image")) {
                        j.mo7579a(jSONObject2.getJSONObject("ad_image").getString("image_url"));
                    }
                    if (jSONObject2.has("logo_image")) {
                        j.mo7581b(jSONObject2.getJSONObject("logo_image").getString("image_url"));
                    }
                    return j.mo7580a();
                }
            }
        }
        if (!jSONObject.isNull("default_ad")) {
            return m597a(jSONObject);
        }
        throw new C0524b(C0758g.ERR_OUT_OF_STOCK);
    }
}
