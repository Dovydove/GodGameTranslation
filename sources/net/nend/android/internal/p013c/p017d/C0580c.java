package net.nend.android.internal.p013c.p017d;

import android.content.Context;
import net.nend.android.NendAdInterstitial.NendAdInterstitialStatusCode;
import net.nend.android.internal.C0521a.C0522a;
import net.nend.android.internal.p007a.C0524b;
import net.nend.android.internal.p013c.C0566c;
import net.nend.android.internal.utilities.C0758g;
import org.andengine.util.level.constants.LevelConstants;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: net.nend.android.internal.c.d.c */
/* compiled from: NendAdInterstitialResponseParser */
public final class C0580c extends C0566c<C0577b> {
    public C0580c(Context context) {
        super(context);
    }

    /* renamed from: b */
    public C0577b mo7847a(C0567a aVar, JSONObject jSONObject) {
        switch (aVar) {
            case INTERSTITIAL_NORMAL:
                return m694a(jSONObject);
            case INTERSTITIAL_APP_TARGETING:
            case INTERSTITIAL_APP_TARGETING_ICON:
            case INTERSTITIAL_APP_TARGETING_RECT:
                return m695b(jSONObject);
            default:
                C0579a aVar2 = new C0579a();
                aVar2.mo7906a(C0522a.ADVIEW);
                aVar2.mo7905a(NendAdInterstitialStatusCode.INVALID_RESPONSE_TYPE);
                return aVar2.mo7907a();
        }
    }

    /* renamed from: a */
    private C0577b m694a(JSONObject jSONObject) {
        C0579a aVar = new C0579a();
        aVar.mo7904a(jSONObject.getJSONObject("ad").getJSONObject("default_ad").getString("ad_id"));
        if (!jSONObject.isNull("status_code")) {
            aVar.mo7903a(jSONObject.getInt("status_code"));
        }
        if (!jSONObject.isNull("message")) {
            aVar.mo7909b(jSONObject.getString("message"));
        }
        if (!jSONObject.isNull("impression_count_url")) {
            aVar.mo7911c(jSONObject.getString("impression_count_url"));
        }
        if (!jSONObject.isNull("request_url")) {
            aVar.mo7913d(jSONObject.getString("request_url"));
        }
        if (!jSONObject.isNull("size")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("size");
            if (!jSONObject2.isNull("portrait") && !jSONObject2.isNull("landscape")) {
                JSONObject jSONObject3 = jSONObject2.getJSONObject("portrait");
                JSONObject jSONObject4 = jSONObject2.getJSONObject("landscape");
                if (jSONObject3 != null && !jSONObject3.isNull(LevelConstants.TAG_LEVEL_ATTRIBUTE_WIDTH) && !jSONObject3.isNull(LevelConstants.TAG_LEVEL_ATTRIBUTE_HEIGHT)) {
                    aVar.mo7914e(jSONObject3.getInt(LevelConstants.TAG_LEVEL_ATTRIBUTE_HEIGHT));
                    aVar.mo7912d(jSONObject3.getInt(LevelConstants.TAG_LEVEL_ATTRIBUTE_WIDTH));
                }
                if (jSONObject4 != null && !jSONObject4.isNull(LevelConstants.TAG_LEVEL_ATTRIBUTE_WIDTH) && !jSONObject4.isNull(LevelConstants.TAG_LEVEL_ATTRIBUTE_HEIGHT)) {
                    aVar.mo7916g(jSONObject4.getInt(LevelConstants.TAG_LEVEL_ATTRIBUTE_HEIGHT));
                    aVar.mo7915f(jSONObject4.getInt(LevelConstants.TAG_LEVEL_ATTRIBUTE_WIDTH));
                }
            }
        }
        if (!jSONObject.isNull("frequency")) {
            aVar.mo7908b(jSONObject.getInt("frequency"));
        }
        if (!jSONObject.isNull("ad_close_area")) {
            aVar.mo7910c(jSONObject.getInt("ad_close_area"));
        }
        aVar.mo7906a(C0522a.ADVIEW);
        return aVar.mo7907a();
    }

    /* renamed from: b */
    private C0577b m695b(JSONObject jSONObject) {
        C0579a aVar = new C0579a();
        JSONObject jSONObject2 = jSONObject.getJSONObject("ad");
        JSONArray jSONArray = jSONObject2.getJSONArray("targeting_ads");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject3 = jSONArray.getJSONObject(i);
            JSONArray jSONArray2 = jSONObject3.getJSONArray("conditions");
            int length2 = jSONArray2.length();
            for (int i2 = 0; i2 < length2; i2++) {
                if (mo7868a(jSONArray2.getJSONArray(i2))) {
                    aVar.mo7904a(jSONObject3.getString("ad_id"));
                    if (!jSONObject.isNull("status_code")) {
                        aVar.mo7903a(jSONObject.getInt("status_code"));
                    }
                    if (!jSONObject.isNull("message")) {
                        aVar.mo7909b(jSONObject.getString("message"));
                    }
                    if (!jSONObject.isNull("impression_count_url")) {
                        aVar.mo7911c(jSONObject.getString("impression_count_url"));
                    }
                    if (!jSONObject.isNull("request_url")) {
                        aVar.mo7913d(jSONObject.getString("request_url"));
                    }
                    if (!jSONObject.isNull("size")) {
                        JSONObject jSONObject4 = jSONObject.getJSONObject("size");
                        if (!jSONObject4.isNull("portrait") && !jSONObject4.isNull("landscape")) {
                            JSONObject jSONObject5 = jSONObject4.getJSONObject("portrait");
                            JSONObject jSONObject6 = jSONObject4.getJSONObject("landscape");
                            if (jSONObject5 != null && !jSONObject5.isNull(LevelConstants.TAG_LEVEL_ATTRIBUTE_WIDTH) && !jSONObject5.isNull(LevelConstants.TAG_LEVEL_ATTRIBUTE_HEIGHT)) {
                                aVar.mo7914e(jSONObject5.getInt(LevelConstants.TAG_LEVEL_ATTRIBUTE_HEIGHT));
                                aVar.mo7912d(jSONObject5.getInt(LevelConstants.TAG_LEVEL_ATTRIBUTE_WIDTH));
                            }
                            if (jSONObject6 != null && !jSONObject6.isNull(LevelConstants.TAG_LEVEL_ATTRIBUTE_WIDTH) && !jSONObject6.isNull(LevelConstants.TAG_LEVEL_ATTRIBUTE_HEIGHT)) {
                                aVar.mo7916g(jSONObject6.getInt(LevelConstants.TAG_LEVEL_ATTRIBUTE_HEIGHT));
                                aVar.mo7915f(jSONObject6.getInt(LevelConstants.TAG_LEVEL_ATTRIBUTE_WIDTH));
                            }
                        }
                    }
                    if (!jSONObject.isNull("frequency")) {
                        aVar.mo7908b(jSONObject.getInt("frequency"));
                    }
                    if (!jSONObject.isNull("ad_close_area")) {
                        aVar.mo7910c(jSONObject.getInt("ad_close_area"));
                    }
                    aVar.mo7906a(C0522a.ADVIEW);
                    return aVar.mo7907a();
                }
            }
        }
        if (!jSONObject2.isNull("default_ad")) {
            return m694a(jSONObject);
        }
        throw new C0524b(C0758g.ERR_OUT_OF_STOCK);
    }
}
