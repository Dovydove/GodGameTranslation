package net.nend.android.internal.p013c.p016c;

import android.content.Context;
import java.util.ArrayList;
import net.nend.android.NendAdNativeMediaView;
import net.nend.android.internal.C0521a.C0522a;
import net.nend.android.internal.p007a.C0524b;
import net.nend.android.internal.p013c.C0566c;
import net.nend.android.internal.p013c.p014a.C0552b.C0554a;
import net.nend.android.internal.utilities.C0758g;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: net.nend.android.internal.c.c.c */
/* compiled from: NendAdIconResponseParser */
public final class C0572c extends C0566c<C0569b> {

    /* renamed from: b */
    private final int f717b;

    public C0572c(Context context, int i) {
        super(context);
        this.f717b = i;
    }

    /* renamed from: b */
    public C0569b mo7847a(C0567a aVar, JSONObject jSONObject) {
        switch (aVar) {
            case ICON_NORMAL:
                return m629a(jSONObject);
            case ICON_APP_TARGETING:
                return m630b(jSONObject);
            default:
                throw new C0524b(C0758g.ERR_INVALID_RESPONSE_TYPE);
        }
    }

    /* renamed from: a */
    private C0569b m629a(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        C0571a aVar = new C0571a();
        JSONArray jSONArray = jSONObject.getJSONArray("default_ads");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            C0554a f = new C0554a().mo7838a(C0522a.ADVIEW).mo7845e(jSONObject2.getString("icon_id")).mo7837a(jSONObject2.getString("image_url")).mo7841b(jSONObject2.getString(NendAdNativeMediaView.RESULT_CLICK_URL)).mo7846f(jSONObject2.getString("animation_gif_flg"));
            if (!jSONObject2.isNull("icon_text")) {
                f.mo7844d(jSONObject2.getString("icon_text"));
            }
            arrayList.add(f.mo7839a());
            if (arrayList.size() >= this.f717b) {
                break;
            }
        }
        if (arrayList.size() == 0) {
            throw new C0524b(C0758g.ERR_OUT_OF_STOCK);
        }
        aVar.mo7876a(arrayList);
        if (!jSONObject.isNull("status_code")) {
            aVar.mo7879b(jSONObject.getInt("status_code"));
        }
        if (!jSONObject.isNull("message")) {
            aVar.mo7875a(jSONObject.getString("message"));
        }
        if (!jSONObject.isNull("reload")) {
            aVar.mo7874a(jSONObject.getInt("reload"));
        }
        if (!jSONObject.isNull("impression_count_url")) {
            aVar.mo7880b(jSONObject.getString("impression_count_url"));
        }
        aVar.mo7877a(C0522a.ADVIEW);
        return aVar.mo7878a();
    }

    /* renamed from: b */
    private C0569b m630b(JSONObject jSONObject) {
        ArrayList arrayList = new ArrayList();
        C0571a aVar = new C0571a();
        JSONArray jSONArray = jSONObject.getJSONArray("targeting_ads");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            JSONArray jSONArray2 = jSONObject2.getJSONArray("conditions");
            int length2 = jSONArray2.length();
            int i2 = 0;
            while (true) {
                if (i2 >= length2) {
                    break;
                } else if (mo7868a(jSONArray2.getJSONArray(i2))) {
                    C0554a f = new C0554a().mo7838a(C0522a.ADVIEW).mo7845e(jSONObject2.getString("icon_id")).mo7837a(jSONObject2.getString("image_url")).mo7841b(jSONObject2.getString(NendAdNativeMediaView.RESULT_CLICK_URL)).mo7846f(jSONObject2.getString("animation_gif_flg"));
                    if (!jSONObject2.isNull("icon_text")) {
                        f.mo7844d(jSONObject2.getString("icon_text"));
                    }
                    arrayList.add(f.mo7839a());
                } else {
                    i2++;
                }
            }
            if (arrayList.size() >= this.f717b) {
                break;
            }
        }
        if (arrayList.size() < this.f717b && !jSONObject.isNull("default_ads")) {
            JSONArray jSONArray3 = jSONObject.getJSONArray("default_ads");
            for (int i3 = 0; i3 < jSONArray3.length(); i3++) {
                JSONObject jSONObject3 = jSONArray3.getJSONObject(i3);
                C0554a f2 = new C0554a().mo7838a(C0522a.ADVIEW).mo7845e(jSONObject3.getString("icon_id")).mo7837a(jSONObject3.getString("image_url")).mo7841b(jSONObject3.getString(NendAdNativeMediaView.RESULT_CLICK_URL)).mo7846f(jSONObject3.getString("animation_gif_flg"));
                if (!jSONObject3.isNull("icon_text")) {
                    f2.mo7844d(jSONObject3.getString("icon_text"));
                }
                arrayList.add(f2.mo7839a());
                if (arrayList.size() >= this.f717b) {
                    break;
                }
            }
        }
        if (arrayList.size() == 0) {
            throw new C0524b(C0758g.ERR_OUT_OF_STOCK);
        }
        aVar.mo7876a(arrayList);
        if (!jSONObject.isNull("status_code")) {
            aVar.mo7879b(jSONObject.getInt("status_code"));
        }
        if (!jSONObject.isNull("message")) {
            aVar.mo7875a(jSONObject.getString("message"));
        }
        if (!jSONObject.isNull("reload")) {
            aVar.mo7874a(jSONObject.getInt("reload"));
        }
        if (!jSONObject.isNull("impression_count_url")) {
            aVar.mo7880b(jSONObject.getString("impression_count_url"));
        }
        aVar.mo7877a(C0522a.ADVIEW);
        return aVar.mo7878a();
    }
}
