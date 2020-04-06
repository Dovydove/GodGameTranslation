package net.nend.android.internal.p013c.p021f;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import net.nend.android.internal.p008b.p012c.C0538a;
import net.nend.android.internal.p008b.p012c.C0542c;
import net.nend.android.internal.p008b.p012c.C0544d;
import net.nend.android.internal.p013c.C0546a;
import net.nend.android.internal.p022d.C0636g;
import net.nend.android.internal.p022d.C0646k;
import net.nend.android.internal.utilities.C0740a.C0744c;
import org.json.JSONObject;

/* renamed from: net.nend.android.internal.c.f.e */
/* compiled from: VideoAdLoader */
public class C0619e extends C0546a {
    /* access modifiers changed from: private */

    /* renamed from: d */
    public static final String f865d = m511a("/interstitial");
    /* access modifiers changed from: private */

    /* renamed from: e */
    public static final String f866e = m511a("/rewarded");
    @VisibleForTesting

    /* renamed from: c */
    final C0744c<C0538a> f867c = new C0550a<C0538a>() {
        public String getRequestUrl() {
            return C0619e.f865d;
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public C0538a mo7832a(JSONObject jSONObject) {
            return C0538a.m497a(jSONObject);
        }
    };

    /* renamed from: f */
    private final C0744c<C0542c> f868f = new C0550a<C0542c>() {
        public String getRequestUrl() {
            return C0619e.f866e;
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public C0542c mo7832a(JSONObject jSONObject) {
            return C0542c.m504a(jSONObject);
        }
    };

    public C0619e(Context context) {
        super(context);
    }

    /* renamed from: a */
    public C0646k<C0538a> mo7982a(int i, String str, String str2, String str3) {
        return m854b(i, str, str2, str3, this.f867c);
    }

    /* renamed from: b */
    public C0646k<C0542c> mo7985b(int i, String str, String str2, String str3) {
        return m854b(i, str, str2, str3, this.f868f);
    }

    /* renamed from: a */
    public void mo7983a(C0544d dVar) {
        if (!TextUtils.isEmpty(dVar.f567f)) {
            C0596a.m759a(dVar);
            this.f625a.mo7952a(dVar.f567f);
        }
    }

    /* renamed from: b */
    private <V extends C0544d> C0646k<V> m854b(int i, String str, String str2, String str3, C0744c<V> cVar) {
        final Context context = (Context) this.f626b.get();
        return mo7825a(i, str, str2, str3, cVar).mo7999b((C0636g<? super T, ? extends C0646k<? extends R>>) new C0636g<V, C0646k<? extends V>>() {
            /* renamed from: a */
            public C0646k<? extends V> mo7540a(V v) {
                return C0619e.this.f625a.mo7950a(v, context);
            }
        });
    }

    /* renamed from: b */
    public C0625a mo7824a(int i, String str, String str2) {
        return (C0625a) ((C0625a) ((C0625a) new C0625a().mo7883a(i)).mo7885a(str)).mo7890b(str2);
    }
}
