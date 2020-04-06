package net.nend.android.internal.p013c.p017d;

import android.text.TextUtils;
import java.util.ArrayList;
import net.nend.android.NendAdInterstitial.NendAdInterstitialStatusCode;
import net.nend.android.internal.C0521a;
import net.nend.android.internal.C0521a.C0522a;

/* renamed from: net.nend.android.internal.c.d.b */
/* compiled from: NendAdInterstitialResponse */
public final class C0577b {

    /* renamed from: a */
    private final C0522a f739a;

    /* renamed from: b */
    private int f740b;

    /* renamed from: c */
    private String f741c;

    /* renamed from: d */
    private String f742d;

    /* renamed from: e */
    private String f743e;

    /* renamed from: f */
    private int f744f;

    /* renamed from: g */
    private int f745g;

    /* renamed from: h */
    private String f746h;

    /* renamed from: i */
    private int f747i;

    /* renamed from: j */
    private int f748j;

    /* renamed from: k */
    private int f749k;

    /* renamed from: l */
    private int f750l;

    /* renamed from: m */
    private ArrayList<C0521a> f751m;

    /* renamed from: n */
    private NendAdInterstitialStatusCode f752n;

    /* renamed from: net.nend.android.internal.c.d.b$a */
    /* compiled from: NendAdInterstitialResponse */
    static final class C0579a {

        /* renamed from: a */
        static final /* synthetic */ boolean f754a = (!C0577b.class.desiredAssertionStatus());
        /* access modifiers changed from: private */

        /* renamed from: b */
        public C0522a f755b = C0522a.NONE;
        /* access modifiers changed from: private */

        /* renamed from: c */
        public int f756c;
        /* access modifiers changed from: private */

        /* renamed from: d */
        public String f757d;
        /* access modifiers changed from: private */

        /* renamed from: e */
        public String f758e;
        /* access modifiers changed from: private */

        /* renamed from: f */
        public String f759f;
        /* access modifiers changed from: private */

        /* renamed from: g */
        public int f760g;
        /* access modifiers changed from: private */

        /* renamed from: h */
        public int f761h;
        /* access modifiers changed from: private */

        /* renamed from: i */
        public String f762i;
        /* access modifiers changed from: private */

        /* renamed from: j */
        public ArrayList<C0521a> f763j;
        /* access modifiers changed from: private */

        /* renamed from: k */
        public int f764k;
        /* access modifiers changed from: private */

        /* renamed from: l */
        public int f765l;
        /* access modifiers changed from: private */

        /* renamed from: m */
        public int f766m;
        /* access modifiers changed from: private */

        /* renamed from: n */
        public int f767n;
        /* access modifiers changed from: private */

        /* renamed from: o */
        public NendAdInterstitialStatusCode f768o;

        C0579a() {
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public C0579a mo7906a(C0522a aVar) {
            if (f754a || aVar != null) {
                this.f755b = aVar;
                return this;
            }
            throw new AssertionError();
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public C0579a mo7903a(int i) {
            this.f756c = i;
            return this;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public C0579a mo7904a(String str) {
            this.f762i = str;
            return this;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: b */
        public C0579a mo7909b(String str) {
            this.f757d = str;
            return this;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: c */
        public C0579a mo7911c(String str) {
            if (str != null) {
                this.f758e = str.replaceAll(" ", "%20");
            } else {
                this.f758e = null;
            }
            return this;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: d */
        public C0579a mo7913d(String str) {
            if (str != null) {
                this.f759f = str.replaceAll(" ", "%20");
            } else {
                this.f759f = null;
            }
            return this;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: b */
        public C0579a mo7908b(int i) {
            this.f760g = i;
            return this;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: c */
        public C0579a mo7910c(int i) {
            this.f761h = i;
            return this;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public C0579a mo7905a(NendAdInterstitialStatusCode nendAdInterstitialStatusCode) {
            this.f768o = nendAdInterstitialStatusCode;
            return this;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: d */
        public C0579a mo7912d(int i) {
            this.f764k = i;
            return this;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: e */
        public C0579a mo7914e(int i) {
            this.f765l = i;
            return this;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: f */
        public C0579a mo7915f(int i) {
            this.f766m = i;
            return this;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: g */
        public C0579a mo7916g(int i) {
            this.f767n = i;
            return this;
        }

        /* renamed from: a */
        public C0577b mo7907a() {
            return new C0577b(this);
        }
    }

    private C0577b(C0579a aVar) {
        switch (aVar.f755b) {
            case ADVIEW:
                if (aVar.f768o == null) {
                    if (TextUtils.isEmpty(aVar.f758e)) {
                        throw new IllegalArgumentException("ImpressionCount url is invalid.");
                    } else if (TextUtils.isEmpty(aVar.f759f)) {
                        throw new IllegalArgumentException("Request url is invalid.");
                    }
                }
                this.f739a = C0522a.ADVIEW;
                this.f740b = aVar.f756c;
                this.f741c = aVar.f757d;
                this.f742d = aVar.f758e;
                this.f743e = aVar.f759f;
                this.f744f = aVar.f760g;
                this.f745g = aVar.f761h;
                this.f746h = aVar.f762i;
                this.f751m = aVar.f763j;
                this.f752n = aVar.f768o;
                this.f747i = aVar.f764k;
                this.f748j = aVar.f765l;
                this.f749k = aVar.f766m;
                this.f750l = aVar.f767n;
                return;
            default:
                throw new IllegalArgumentException("Unknown view type.");
        }
    }

    /* renamed from: a */
    public String mo7893a() {
        return this.f742d;
    }

    /* renamed from: b */
    public String mo7894b() {
        return this.f743e;
    }

    /* renamed from: c */
    public int mo7895c() {
        return this.f744f;
    }

    /* renamed from: d */
    public int mo7896d() {
        return this.f745g;
    }

    /* renamed from: e */
    public String mo7897e() {
        return this.f746h;
    }

    /* renamed from: f */
    public int mo7898f() {
        return this.f747i;
    }

    /* renamed from: g */
    public int mo7899g() {
        return this.f748j;
    }

    /* renamed from: h */
    public int mo7900h() {
        return this.f749k;
    }

    /* renamed from: i */
    public int mo7901i() {
        return this.f750l;
    }

    /* renamed from: j */
    public NendAdInterstitialStatusCode mo7902j() {
        return this.f752n;
    }
}
