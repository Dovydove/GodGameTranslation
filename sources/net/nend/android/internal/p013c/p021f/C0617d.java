package net.nend.android.internal.p013c.p021f;

import java.util.ArrayList;
import net.nend.android.internal.p013c.C0574d;
import net.nend.android.internal.p013c.C0574d.C0575a;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: net.nend.android.internal.c.f.d */
/* compiled from: NativeAdRequest */
public class C0617d extends C0574d {

    /* renamed from: a */
    private final ArrayList<Integer> f861a;

    /* renamed from: b */
    private final int f862b;

    /* renamed from: net.nend.android.internal.c.f.d$a */
    /* compiled from: NativeAdRequest */
    public static class C0618a extends C0575a<C0618a> {
        /* access modifiers changed from: private */

        /* renamed from: c */
        public ArrayList<Integer> f863c;
        /* access modifiers changed from: private */

        /* renamed from: d */
        public int f864d;

        /* renamed from: a */
        public C0618a mo7980a(ArrayList<Integer> arrayList) {
            this.f863c = arrayList;
            return this;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: b */
        public C0618a mo7981b(int i) {
            this.f864d = i;
            return this;
        }

        /* renamed from: a */
        public C0574d mo7889a() {
            return new C0617d(this);
        }
    }

    C0617d(C0618a aVar) {
        super(aVar);
        this.f861a = aVar.f863c;
        this.f862b = aVar.f864d;
    }

    /* renamed from: a */
    public JSONObject mo7882a() {
        JSONObject a = super.mo7882a();
        a.put("acquiredIds", new JSONArray(this.f861a));
        a.put("videoClickAction", this.f862b);
        return a;
    }
}
