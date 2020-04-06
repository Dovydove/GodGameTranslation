package p004jp.p005co.imobile.sdkads.android;

import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: jp.co.imobile.sdkads.android.k */
final class C0346k extends C0347l {

    /* renamed from: d */
    private ArrayList f136d = new ArrayList();

    C0346k() {
    }

    /* renamed from: a */
    static C0346k m86a(JSONObject jSONObject, C0346k kVar) {
        Boolean valueOf;
        C0346k kVar2 = null;
        try {
            if (jSONObject.has("conditions")) {
                kVar2 = new C0346k();
                kVar2.f137a = jSONObject.getString("operator");
                kVar2.f138b = Boolean.valueOf(jSONObject.getBoolean("not"));
                JSONArray jSONArray = jSONObject.getJSONArray("conditions");
                for (int i = 0; i < jSONArray.length(); i++) {
                    C0346k a = m86a(jSONArray.getJSONObject(i), kVar2);
                    if (a != null) {
                        kVar2.m87a(a);
                    }
                }
            } else if (jSONObject.has("conditionDetails")) {
                C0349n nVar = new C0349n();
                nVar.f137a = jSONObject.getString("operator");
                nVar.f138b = Boolean.valueOf(jSONObject.getBoolean("not"));
                JSONArray jSONArray2 = jSONObject.getJSONArray("conditionDetails");
                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                    C0348m mVar = new C0348m();
                    JSONObject jSONObject2 = jSONArray2.getJSONObject(i2);
                    mVar.f140a = Boolean.valueOf(false);
                    Boolean.valueOf(false);
                    switch (jSONObject2.getInt("ct")) {
                        case 1:
                            valueOf = Boolean.valueOf(C0353r.m99a().mo7256b(jSONObject2.getString("nm")));
                            break;
                        case 2:
                            valueOf = Boolean.valueOf(C0353r.m103a(jSONObject2.getString("nm")));
                            break;
                        default:
                            valueOf = Boolean.valueOf(false);
                            break;
                    }
                    Boolean valueOf2 = Boolean.valueOf(jSONObject2.getBoolean("contain"));
                    mVar.f140a = Boolean.valueOf((valueOf.booleanValue() && valueOf2.booleanValue()) || valueOf == valueOf2);
                    if (!jSONObject2.getBoolean("action")) {
                        mVar.f140a = Boolean.valueOf(!mVar.f140a.booleanValue());
                    }
                    nVar.mo7251a(mVar);
                }
                kVar.m87a(nVar);
            }
            return kVar2;
        } catch (JSONException e) {
            e.getMessage();
            C0359x.m126b("Ad targeting condition format error.", "parse");
            throw new C0360y(FailNotificationReason.RESPONSE);
        }
    }

    /* renamed from: a */
    private void m87a(C0347l lVar) {
        this.f136d.add(lVar);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final Boolean mo7249a() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f136d.iterator();
        while (it.hasNext()) {
            arrayList.add(((C0347l) it.next()).mo7249a());
        }
        return mo7250a(arrayList);
    }
}
