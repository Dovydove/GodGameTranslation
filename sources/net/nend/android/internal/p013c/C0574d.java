package net.nend.android.internal.p013c;

import android.text.TextUtils;
import net.nend.android.NendAdUserFeature;
import net.nend.android.internal.p013c.p018e.p019a.p020a.C0584a;
import net.nend.android.internal.p013c.p018e.p019a.p020a.C0584a.C0586a;
import net.nend.android.internal.p013c.p018e.p019a.p020a.C0587b;
import net.nend.android.internal.p013c.p018e.p019a.p020a.C0587b.C0589a;
import org.json.JSONObject;

/* renamed from: net.nend.android.internal.c.d */
/* compiled from: Nend2AdRequest */
public class C0574d {

    /* renamed from: a */
    private final int f719a;

    /* renamed from: b */
    private final String f720b;

    /* renamed from: c */
    private final C0587b f721c;

    /* renamed from: d */
    private final C0584a f722d;

    /* renamed from: e */
    private final String f723e;

    /* renamed from: f */
    private final String f724f;

    /* renamed from: g */
    private final String f725g;

    /* renamed from: h */
    private final long f726h;

    /* renamed from: i */
    private final NendAdUserFeature f727i;

    /* renamed from: net.nend.android.internal.c.d$a */
    /* compiled from: Nend2AdRequest */
    public static abstract class C0575a<T extends C0575a<T>> {

        /* renamed from: a */
        public final C0589a f728a = new C0589a();

        /* renamed from: b */
        public final C0586a f729b = new C0586a();
        /* access modifiers changed from: private */

        /* renamed from: c */
        public int f730c;
        /* access modifiers changed from: private */

        /* renamed from: d */
        public String f731d;
        /* access modifiers changed from: private */

        /* renamed from: e */
        public C0587b f732e;
        /* access modifiers changed from: private */

        /* renamed from: f */
        public C0584a f733f;
        /* access modifiers changed from: private */

        /* renamed from: g */
        public String f734g;
        /* access modifiers changed from: private */

        /* renamed from: h */
        public String f735h;
        /* access modifiers changed from: private */

        /* renamed from: i */
        public String f736i;
        /* access modifiers changed from: private */

        /* renamed from: j */
        public long f737j;
        /* access modifiers changed from: private */

        /* renamed from: k */
        public NendAdUserFeature f738k;

        /* renamed from: a */
        public abstract C0574d mo7889a();

        /* renamed from: a */
        public T mo7883a(int i) {
            this.f730c = i;
            return this;
        }

        /* renamed from: a */
        public T mo7885a(String str) {
            this.f731d = str;
            return this;
        }

        /* renamed from: a */
        public T mo7888a(C0587b bVar) {
            this.f732e = bVar;
            return this;
        }

        /* renamed from: a */
        public T mo7887a(C0584a aVar) {
            this.f733f = aVar;
            return this;
        }

        /* renamed from: b */
        public T mo7890b(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.f734g = str;
            }
            return this;
        }

        /* renamed from: c */
        public T mo7891c(String str) {
            this.f735h = str;
            return this;
        }

        /* renamed from: d */
        public T mo7892d(String str) {
            this.f736i = str;
            return this;
        }

        /* renamed from: a */
        public T mo7884a(long j) {
            this.f737j = j;
            return this;
        }

        /* renamed from: a */
        public T mo7886a(NendAdUserFeature nendAdUserFeature) {
            this.f738k = nendAdUserFeature;
            return this;
        }
    }

    public C0574d(C0575a<?> aVar) {
        this.f719a = aVar.f730c;
        this.f720b = aVar.f731d;
        this.f721c = aVar.f732e;
        this.f722d = aVar.f733f;
        this.f723e = aVar.f734g;
        this.f724f = aVar.f735h;
        this.f725g = aVar.f736i;
        this.f726h = aVar.f737j;
        this.f727i = aVar.f738k;
    }

    /* renamed from: a */
    public JSONObject mo7882a() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("apiKey", this.f720b);
        jSONObject.put("adspotId", this.f719a);
        jSONObject.put("device", this.f721c.mo7923a());
        jSONObject.put("app", this.f722d.mo7918a());
        jSONObject.putOpt("mediation", this.f723e);
        jSONObject.put("sdk", this.f724f);
        jSONObject.put("sdkVer", this.f725g);
        jSONObject.put("clientTime", this.f726h);
        jSONObject.putOpt("feature", this.f727i != null ? this.f727i.toJson() : null);
        return jSONObject;
    }
}
