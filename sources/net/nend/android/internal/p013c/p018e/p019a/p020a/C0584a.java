package net.nend.android.internal.p013c.p018e.p019a.p020a;

import android.text.TextUtils;
import org.json.JSONObject;

/* renamed from: net.nend.android.internal.c.e.a.a.a */
/* compiled from: App */
public class C0584a {

    /* renamed from: a */
    private final String f770a;

    /* renamed from: b */
    private final String f771b;

    /* renamed from: c */
    private final String f772c;

    /* renamed from: net.nend.android.internal.c.e.a.a.a$a */
    /* compiled from: App */
    public static class C0586a {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public String f773a;
        /* access modifiers changed from: private */

        /* renamed from: b */
        public String f774b;
        /* access modifiers changed from: private */

        /* renamed from: c */
        public String f775c;

        /* renamed from: a */
        public C0586a mo7919a(String str) {
            this.f773a = str;
            return this;
        }

        /* renamed from: b */
        public C0586a mo7921b(String str) {
            this.f774b = str;
            return this;
        }

        /* renamed from: c */
        public C0586a mo7922c(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.f775c = str;
            }
            return this;
        }

        /* renamed from: a */
        public C0584a mo7920a() {
            return new C0584a(this);
        }
    }

    private C0584a(C0586a aVar) {
        this.f770a = aVar.f773a;
        this.f771b = aVar.f774b;
        this.f772c = aVar.f775c;
    }

    /* renamed from: a */
    public JSONObject mo7918a() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", this.f770a);
        jSONObject.put("ver", this.f771b);
        jSONObject.putOpt("userId", this.f772c);
        return jSONObject;
    }
}
