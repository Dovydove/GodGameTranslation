package net.nend.android.internal.p013c.p014a;

import android.text.TextUtils;
import net.nend.android.internal.C0521a;
import net.nend.android.internal.C0521a.C0522a;

/* renamed from: net.nend.android.internal.c.a.b */
/* compiled from: NendAdResponse */
public final class C0552b implements C0521a {

    /* renamed from: a */
    private final C0522a f639a;

    /* renamed from: b */
    private final String f640b;

    /* renamed from: c */
    private final String f641c;

    /* renamed from: d */
    private final String f642d;

    /* renamed from: e */
    private final String f643e;

    /* renamed from: f */
    private final String f644f;

    /* renamed from: g */
    private final String f645g;

    /* renamed from: h */
    private final int f646h;

    /* renamed from: i */
    private final int f647i;

    /* renamed from: j */
    private final int f648j;

    /* renamed from: k */
    private final boolean f649k;

    /* renamed from: net.nend.android.internal.c.a.b$a */
    /* compiled from: NendAdResponse */
    public static final class C0554a {

        /* renamed from: a */
        static final /* synthetic */ boolean f651a = (!C0552b.class.desiredAssertionStatus());
        /* access modifiers changed from: private */

        /* renamed from: b */
        public C0522a f652b = C0522a.NONE;
        /* access modifiers changed from: private */

        /* renamed from: c */
        public String f653c;
        /* access modifiers changed from: private */

        /* renamed from: d */
        public String f654d;
        /* access modifiers changed from: private */

        /* renamed from: e */
        public String f655e;
        /* access modifiers changed from: private */

        /* renamed from: f */
        public String f656f;
        /* access modifiers changed from: private */

        /* renamed from: g */
        public String f657g;
        /* access modifiers changed from: private */

        /* renamed from: h */
        public String f658h;
        /* access modifiers changed from: private */

        /* renamed from: i */
        public int f659i;
        /* access modifiers changed from: private */

        /* renamed from: j */
        public int f660j;
        /* access modifiers changed from: private */

        /* renamed from: k */
        public int f661k;
        /* access modifiers changed from: private */

        /* renamed from: l */
        public boolean f662l = false;

        /* renamed from: a */
        public C0554a mo7838a(C0522a aVar) {
            if (f651a || aVar != null) {
                this.f652b = aVar;
                return this;
            }
            throw new AssertionError();
        }

        /* renamed from: a */
        public C0554a mo7837a(String str) {
            if (str != null) {
                this.f653c = str.replaceAll(" ", "%20");
            } else {
                this.f653c = null;
            }
            return this;
        }

        /* renamed from: b */
        public C0554a mo7841b(String str) {
            if (str != null) {
                this.f654d = str.replaceAll(" ", "%20");
            } else {
                this.f654d = null;
            }
            return this;
        }

        /* renamed from: c */
        public C0554a mo7843c(String str) {
            if (str != null) {
                this.f655e = str.replaceAll(" ", "%20");
            } else {
                this.f655e = null;
            }
            return this;
        }

        /* renamed from: d */
        public C0554a mo7844d(String str) {
            this.f656f = str;
            return this;
        }

        /* renamed from: a */
        public C0554a mo7836a(int i) {
            this.f659i = i;
            return this;
        }

        /* renamed from: b */
        public C0554a mo7840b(int i) {
            this.f661k = i;
            return this;
        }

        /* renamed from: c */
        public C0554a mo7842c(int i) {
            this.f660j = i;
            return this;
        }

        /* renamed from: e */
        public C0554a mo7845e(String str) {
            this.f658h = str;
            return this;
        }

        /* renamed from: f */
        public C0554a mo7846f(String str) {
            this.f662l = "1".equals(str);
            return this;
        }

        /* renamed from: a */
        public C0552b mo7839a() {
            return new C0552b(this);
        }
    }

    private C0552b(C0554a aVar) {
        switch (aVar.f652b) {
            case ADVIEW:
                if (TextUtils.isEmpty(aVar.f653c)) {
                    throw new IllegalArgumentException("Image url is invalid.");
                } else if (TextUtils.isEmpty(aVar.f654d)) {
                    throw new IllegalArgumentException("Click url is invalid");
                } else {
                    this.f639a = C0522a.ADVIEW;
                    this.f640b = aVar.f653c;
                    this.f641c = aVar.f654d;
                    this.f642d = null;
                    this.f643e = aVar.f656f;
                    this.f646h = aVar.f659i;
                    this.f647i = aVar.f661k;
                    this.f648j = aVar.f660j;
                    this.f644f = aVar.f658h;
                    this.f645g = aVar.f657g;
                    this.f649k = aVar.f662l;
                    return;
                }
            case WEBVIEW:
                if (TextUtils.isEmpty(aVar.f655e)) {
                    throw new IllegalArgumentException("Web view url is invalid");
                }
                this.f639a = C0522a.WEBVIEW;
                this.f640b = null;
                this.f641c = null;
                this.f642d = aVar.f655e;
                this.f643e = null;
                this.f646h = 0;
                this.f647i = aVar.f661k;
                this.f648j = aVar.f660j;
                this.f644f = null;
                this.f645g = null;
                this.f649k = false;
                return;
            case DYNAMICRETARGETING:
                if (TextUtils.isEmpty(aVar.f655e)) {
                    throw new IllegalArgumentException("Web view url is invalid");
                }
                this.f639a = C0522a.DYNAMICRETARGETING;
                this.f640b = null;
                this.f641c = null;
                this.f642d = aVar.f655e;
                this.f643e = null;
                this.f646h = aVar.f659i;
                this.f647i = aVar.f661k;
                this.f648j = aVar.f660j;
                this.f644f = null;
                this.f645g = null;
                this.f649k = false;
                return;
            default:
                throw new IllegalArgumentException("Uknown view type.");
        }
    }

    /* renamed from: a */
    public C0522a mo7766a() {
        return this.f639a;
    }

    /* renamed from: b */
    public String mo7767b() {
        return this.f640b;
    }

    /* renamed from: c */
    public String mo7768c() {
        return this.f641c;
    }

    /* renamed from: d */
    public String mo7769d() {
        return this.f642d;
    }

    /* renamed from: e */
    public String mo7770e() {
        return this.f643e;
    }

    /* renamed from: f */
    public int mo7771f() {
        return this.f648j;
    }

    /* renamed from: g */
    public int mo7772g() {
        return this.f647i;
    }

    /* renamed from: h */
    public int mo7773h() {
        return this.f646h;
    }

    /* renamed from: i */
    public String mo7774i() {
        return this.f644f;
    }

    /* renamed from: j */
    public String mo7775j() {
        return this.f645g;
    }

    /* renamed from: k */
    public boolean mo7776k() {
        return this.f649k;
    }
}
