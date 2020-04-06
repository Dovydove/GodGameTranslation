package net.nend.android.internal.p013c.p018e.p019a.p020a;

import org.json.JSONObject;

/* renamed from: net.nend.android.internal.c.e.a.a.d */
/* compiled from: Sensor */
public class C0592d {

    /* renamed from: a */
    private final JSONObject f806a;

    /* renamed from: b */
    private final JSONObject f807b;

    /* renamed from: c */
    private final JSONObject f808c;

    /* renamed from: net.nend.android.internal.c.e.a.a.d$a */
    /* compiled from: Sensor */
    public static class C0594a {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public JSONObject f809a;
        /* access modifiers changed from: private */

        /* renamed from: b */
        public JSONObject f810b;
        /* access modifiers changed from: private */

        /* renamed from: c */
        public JSONObject f811c;

        /* renamed from: a */
        public C0594a mo7943a(JSONObject jSONObject) {
            this.f809a = jSONObject;
            return this;
        }

        /* renamed from: b */
        public C0594a mo7945b(JSONObject jSONObject) {
            this.f810b = jSONObject;
            return this;
        }

        /* renamed from: c */
        public C0594a mo7946c(JSONObject jSONObject) {
            this.f811c = jSONObject;
            return this;
        }

        /* renamed from: a */
        public C0592d mo7944a() {
            return new C0592d(this);
        }
    }

    private C0592d(C0594a aVar) {
        this.f806a = aVar.f809a;
        this.f807b = aVar.f810b;
        this.f808c = aVar.f811c;
    }

    /* renamed from: a */
    public JSONObject mo7942a() {
        JSONObject jSONObject = new JSONObject();
        if (this.f806a != null) {
            jSONObject.putOpt("attitude", this.f806a);
        }
        if (this.f807b != null) {
            jSONObject.putOpt("air", this.f807b);
        }
        if (this.f808c != null) {
            jSONObject.putOpt("geo", this.f808c);
        }
        if (jSONObject.length() > 0) {
            return jSONObject;
        }
        return null;
    }
}
