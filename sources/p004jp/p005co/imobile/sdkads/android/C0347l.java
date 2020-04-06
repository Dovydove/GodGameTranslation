package p004jp.p005co.imobile.sdkads.android;

import java.util.List;

/* renamed from: jp.co.imobile.sdkads.android.l */
abstract class C0347l {

    /* renamed from: a */
    String f137a = null;

    /* renamed from: b */
    Boolean f138b = null;

    /* renamed from: c */
    Boolean f139c = null;

    C0347l() {
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public abstract Boolean mo7249a();

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final Boolean mo7250a(List list) {
        boolean z = false;
        if (list.size() > 1) {
            if (this.f137a.equals("AND")) {
                this.f139c = Boolean.valueOf(!list.contains(Boolean.valueOf(false)));
            } else {
                this.f139c = Boolean.valueOf(list.contains(Boolean.valueOf(true)));
            }
        } else if (list.size() == 1) {
            this.f139c = (Boolean) list.get(0);
        } else {
            this.f139c = Boolean.valueOf(false);
            return this.f139c;
        }
        if (this.f138b.booleanValue()) {
            if (!this.f139c.booleanValue()) {
                z = true;
            }
            this.f139c = Boolean.valueOf(z);
        }
        return this.f139c;
    }
}
