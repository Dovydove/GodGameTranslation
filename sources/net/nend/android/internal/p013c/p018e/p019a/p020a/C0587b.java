package net.nend.android.internal.p013c.p018e.p019a.p020a;

import android.text.TextUtils;
import org.json.JSONObject;

/* renamed from: net.nend.android.internal.c.e.a.a.b */
/* compiled from: Device */
public class C0587b {

    /* renamed from: a */
    private final int f776a;

    /* renamed from: b */
    private final String f777b;

    /* renamed from: c */
    private final String f778c;

    /* renamed from: d */
    private final String f779d;

    /* renamed from: e */
    private final String f780e;

    /* renamed from: f */
    private final String f781f;

    /* renamed from: g */
    private final int f782g;

    /* renamed from: h */
    private final C0590c f783h;

    /* renamed from: i */
    private final C0592d f784i;

    /* renamed from: j */
    private final int f785j;

    /* renamed from: k */
    private final String f786k;

    /* renamed from: l */
    private final boolean f787l;

    /* renamed from: net.nend.android.internal.c.e.a.a.b$a */
    /* compiled from: Device */
    public static class C0589a {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public int f788a;
        /* access modifiers changed from: private */

        /* renamed from: b */
        public String f789b;
        /* access modifiers changed from: private */

        /* renamed from: c */
        public String f790c;
        /* access modifiers changed from: private */

        /* renamed from: d */
        public String f791d;
        /* access modifiers changed from: private */

        /* renamed from: e */
        public String f792e;
        /* access modifiers changed from: private */

        /* renamed from: f */
        public String f793f;
        /* access modifiers changed from: private */

        /* renamed from: g */
        public int f794g;
        /* access modifiers changed from: private */

        /* renamed from: h */
        public C0590c f795h;
        /* access modifiers changed from: private */

        /* renamed from: i */
        public C0592d f796i;
        /* access modifiers changed from: private */

        /* renamed from: j */
        public int f797j;
        /* access modifiers changed from: private */

        /* renamed from: k */
        public String f798k;
        /* access modifiers changed from: private */

        /* renamed from: l */
        public boolean f799l;

        /* renamed from: a */
        public C0589a mo7924a(int i) {
            this.f788a = i;
            return this;
        }

        /* renamed from: a */
        public C0589a mo7925a(String str) {
            this.f789b = str;
            return this;
        }

        /* renamed from: b */
        public C0589a mo7931b(String str) {
            if (str == null) {
                str = "";
            }
            this.f790c = str;
            return this;
        }

        /* renamed from: c */
        public C0589a mo7933c(String str) {
            this.f791d = str;
            return this;
        }

        /* renamed from: d */
        public C0589a mo7934d(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.f792e = str;
            }
            return this;
        }

        /* renamed from: e */
        public C0589a mo7935e(String str) {
            this.f793f = str;
            return this;
        }

        /* renamed from: b */
        public C0589a mo7930b(int i) {
            this.f794g = i;
            return this;
        }

        /* renamed from: a */
        public C0589a mo7926a(C0590c cVar) {
            this.f795h = cVar;
            return this;
        }

        /* renamed from: a */
        public C0589a mo7927a(C0592d dVar) {
            this.f796i = dVar;
            return this;
        }

        /* renamed from: c */
        public C0589a mo7932c(int i) {
            this.f797j = i;
            return this;
        }

        /* renamed from: f */
        public C0589a mo7936f(String str) {
            this.f798k = str;
            return this;
        }

        /* renamed from: a */
        public C0589a mo7928a(boolean z) {
            this.f799l = z;
            return this;
        }

        /* renamed from: a */
        public C0587b mo7929a() {
            return new C0587b(this);
        }
    }

    private C0587b(C0589a aVar) {
        this.f776a = aVar.f788a;
        this.f777b = aVar.f789b;
        this.f778c = aVar.f790c;
        this.f779d = aVar.f791d;
        this.f780e = aVar.f792e;
        this.f781f = aVar.f793f;
        this.f782g = aVar.f794g;
        this.f783h = aVar.f795h;
        this.f784i = aVar.f796i;
        this.f785j = aVar.f797j;
        this.f786k = aVar.f798k;
        this.f787l = aVar.f799l;
    }

    /* renamed from: a */
    public JSONObject mo7923a() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("os", this.f776a);
        jSONObject.put("osVer", this.f777b);
        jSONObject.put("model", this.f778c);
        jSONObject.put("userAgent", this.f779d);
        jSONObject.putOpt("gaid", this.f780e);
        jSONObject.put("language", this.f781f);
        jSONObject.put("orientation", this.f782g);
        jSONObject.putOpt("screen", this.f783h.mo7937a());
        jSONObject.putOpt("sensor", this.f784i.mo7942a());
        jSONObject.put("mediaVol", this.f785j);
        jSONObject.putOpt("carrier", this.f786k);
        jSONObject.putOpt("isWifi", Boolean.valueOf(this.f787l));
        return jSONObject;
    }
}
