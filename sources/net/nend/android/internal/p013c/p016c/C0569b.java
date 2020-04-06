package net.nend.android.internal.p013c.p016c;

import android.text.TextUtils;
import java.util.ArrayList;
import net.nend.android.internal.C0521a;
import net.nend.android.internal.C0521a.C0522a;

/* renamed from: net.nend.android.internal.c.c.b */
/* compiled from: NendAdIconResponse */
public final class C0569b {

    /* renamed from: a */
    private final C0522a f703a;

    /* renamed from: b */
    private final int f704b;

    /* renamed from: c */
    private int f705c;

    /* renamed from: d */
    private String f706d;

    /* renamed from: e */
    private String f707e;

    /* renamed from: f */
    private ArrayList<C0521a> f708f;

    /* renamed from: net.nend.android.internal.c.c.b$a */
    /* compiled from: NendAdIconResponse */
    static final class C0571a {

        /* renamed from: a */
        static final /* synthetic */ boolean f710a = (!C0569b.class.desiredAssertionStatus());
        /* access modifiers changed from: private */

        /* renamed from: b */
        public C0522a f711b = C0522a.NONE;
        /* access modifiers changed from: private */

        /* renamed from: c */
        public int f712c;
        /* access modifiers changed from: private */

        /* renamed from: d */
        public int f713d;
        /* access modifiers changed from: private */

        /* renamed from: e */
        public String f714e;
        /* access modifiers changed from: private */

        /* renamed from: f */
        public String f715f;
        /* access modifiers changed from: private */

        /* renamed from: g */
        public ArrayList<C0521a> f716g;

        C0571a() {
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public C0571a mo7877a(C0522a aVar) {
            if (f710a || aVar != null) {
                this.f711b = aVar;
                return this;
            }
            throw new AssertionError();
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public C0571a mo7874a(int i) {
            this.f712c = i;
            return this;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: b */
        public C0571a mo7879b(int i) {
            this.f713d = i;
            return this;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public C0571a mo7875a(String str) {
            this.f714e = str;
            return this;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: b */
        public C0571a mo7880b(String str) {
            if (str != null) {
                this.f715f = str.replaceAll(" ", "%20");
            } else {
                this.f715f = null;
            }
            return this;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public C0571a mo7876a(ArrayList<C0521a> arrayList) {
            this.f716g = arrayList;
            return this;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public C0569b mo7878a() {
            return new C0569b(this);
        }
    }

    private C0569b(C0571a aVar) {
        switch (aVar.f711b) {
            case ADVIEW:
                if (TextUtils.isEmpty(aVar.f715f)) {
                    throw new IllegalArgumentException("ImpressionCount Url is invalid.");
                }
                this.f703a = C0522a.ADVIEW;
                this.f704b = aVar.f712c;
                this.f705c = aVar.f713d;
                this.f706d = aVar.f714e;
                this.f707e = aVar.f715f;
                this.f708f = aVar.f716g;
                return;
            default:
                throw new IllegalArgumentException("Unknown view type.");
        }
    }

    /* renamed from: a */
    public int mo7871a() {
        return this.f704b;
    }

    /* renamed from: b */
    public String mo7872b() {
        return this.f707e;
    }

    /* renamed from: c */
    public ArrayList<C0521a> mo7873c() {
        return this.f708f;
    }
}
