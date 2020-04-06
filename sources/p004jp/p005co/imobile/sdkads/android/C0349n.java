package p004jp.p005co.imobile.sdkads.android;

import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: jp.co.imobile.sdkads.android.n */
final class C0349n extends C0347l {

    /* renamed from: d */
    private ArrayList f141d = new ArrayList();

    C0349n() {
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final Boolean mo7249a() {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f141d.iterator();
        while (it.hasNext()) {
            arrayList.add(((C0348m) it.next()).f140a);
        }
        return mo7250a(arrayList);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo7251a(C0348m mVar) {
        this.f141d.add(mVar);
    }
}
