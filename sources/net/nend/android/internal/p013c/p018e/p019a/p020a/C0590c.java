package net.nend.android.internal.p013c.p018e.p019a.p020a;

import org.andengine.util.level.constants.LevelConstants;
import org.json.JSONObject;

/* renamed from: net.nend.android.internal.c.e.a.a.c */
/* compiled from: Screen */
public class C0590c {

    /* renamed from: a */
    private final int f800a;

    /* renamed from: b */
    private final int f801b;

    /* renamed from: c */
    private final int f802c;

    /* renamed from: net.nend.android.internal.c.e.a.a.c$a */
    /* compiled from: Screen */
    public static class C0591a {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public int f803a;
        /* access modifiers changed from: private */

        /* renamed from: b */
        public int f804b;
        /* access modifiers changed from: private */

        /* renamed from: c */
        public int f805c;

        /* renamed from: a */
        public C0591a mo7938a(int i) {
            this.f803a = i;
            return this;
        }

        /* renamed from: b */
        public C0591a mo7940b(int i) {
            this.f804b = i;
            return this;
        }

        /* renamed from: c */
        public C0591a mo7941c(int i) {
            this.f805c = i;
            return this;
        }

        /* renamed from: a */
        public C0590c mo7939a() {
            return new C0590c(this);
        }
    }

    C0590c(C0591a aVar) {
        this.f800a = aVar.f803a;
        this.f801b = aVar.f804b;
        this.f802c = aVar.f805c;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public JSONObject mo7937a() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(LevelConstants.TAG_LEVEL_ATTRIBUTE_WIDTH, this.f800a);
        jSONObject.put(LevelConstants.TAG_LEVEL_ATTRIBUTE_HEIGHT, this.f801b);
        jSONObject.put("dpi", this.f802c);
        return jSONObject;
    }
}
