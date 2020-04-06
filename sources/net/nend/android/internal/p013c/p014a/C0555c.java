package net.nend.android.internal.p013c.p014a;

import android.content.Context;
import net.nend.android.NendAdNativeMediaView;
import net.nend.android.internal.C0521a;
import net.nend.android.internal.C0521a.C0522a;
import net.nend.android.internal.p007a.C0524b;
import net.nend.android.internal.p013c.C0566c;
import net.nend.android.internal.p013c.p014a.C0552b.C0554a;
import net.nend.android.internal.utilities.C0758g;
import org.andengine.util.level.constants.LevelConstants;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: net.nend.android.internal.c.a.c */
/* compiled from: NendAdResponseParser */
public final class C0555c extends C0566c<C0521a> {
    public C0555c(Context context) {
        super(context);
    }

    /* renamed from: b */
    public C0521a mo7847a(C0567a aVar, JSONObject jSONObject) {
        switch (aVar) {
            case BANNER_NORMAL:
                return m568a(jSONObject);
            case BANNER_WEB_VIEW:
                return m569b(jSONObject);
            case BANNER_APP_TARGETING:
                return m570c(jSONObject);
            case BANNER_DYNAMIC_RETARGETING:
                return m571d(jSONObject);
            default:
                throw new C0524b(C0758g.ERR_INVALID_RESPONSE_TYPE);
        }
    }

    /* renamed from: a */
    private C0521a m568a(JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject.getJSONObject("default_ad");
        C0554a c = new C0554a().mo7838a(C0522a.ADVIEW).mo7837a(jSONObject2.getString("image_url")).mo7841b(jSONObject2.getString(NendAdNativeMediaView.RESULT_CLICK_URL)).mo7840b(jSONObject.getInt(LevelConstants.TAG_LEVEL_ATTRIBUTE_HEIGHT)).mo7842c(jSONObject.getInt(LevelConstants.TAG_LEVEL_ATTRIBUTE_WIDTH));
        if (!jSONObject2.isNull("animation_gif_flg")) {
            c.mo7846f(jSONObject2.getString("animation_gif_flg"));
        }
        if (!jSONObject.isNull("reload")) {
            c.mo7836a(jSONObject.getInt("reload"));
        }
        return c.mo7839a();
    }

    /* renamed from: b */
    private C0521a m569b(JSONObject jSONObject) {
        return new C0554a().mo7838a(C0522a.WEBVIEW).mo7843c(jSONObject.getString("web_view_url")).mo7840b(jSONObject.getInt(LevelConstants.TAG_LEVEL_ATTRIBUTE_HEIGHT)).mo7842c(jSONObject.getInt(LevelConstants.TAG_LEVEL_ATTRIBUTE_WIDTH)).mo7839a();
    }

    /* renamed from: c */
    private C0521a m570c(JSONObject jSONObject) {
        JSONArray jSONArray = jSONObject.getJSONArray("targeting_ads");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            JSONArray jSONArray2 = jSONObject2.getJSONArray("conditions");
            int length2 = jSONArray2.length();
            for (int i2 = 0; i2 < length2; i2++) {
                if (mo7868a(jSONArray2.getJSONArray(i2))) {
                    C0554a f = new C0554a().mo7838a(C0522a.ADVIEW).mo7837a(jSONObject2.getString("image_url")).mo7841b(jSONObject2.getString(NendAdNativeMediaView.RESULT_CLICK_URL)).mo7840b(jSONObject.getInt(LevelConstants.TAG_LEVEL_ATTRIBUTE_HEIGHT)).mo7842c(jSONObject.getInt(LevelConstants.TAG_LEVEL_ATTRIBUTE_WIDTH)).mo7846f(jSONObject2.getString("animation_gif_flg"));
                    if (!jSONObject.isNull("reload")) {
                        f.mo7836a(jSONObject.getInt("reload"));
                    }
                    return f.mo7839a();
                }
            }
        }
        if (!jSONObject.isNull("default_ad")) {
            return m568a(jSONObject);
        }
        throw new C0524b(C0758g.ERR_OUT_OF_STOCK);
    }

    /* renamed from: d */
    private C0521a m571d(JSONObject jSONObject) {
        C0554a c = new C0554a().mo7838a(C0522a.DYNAMICRETARGETING).mo7843c(jSONObject.getString("web_view_url")).mo7840b(jSONObject.getInt(LevelConstants.TAG_LEVEL_ATTRIBUTE_HEIGHT)).mo7842c(jSONObject.getInt(LevelConstants.TAG_LEVEL_ATTRIBUTE_WIDTH));
        if (!jSONObject.isNull("reload")) {
            c.mo7836a(jSONObject.getInt("reload"));
        }
        return c.mo7839a();
    }
}
