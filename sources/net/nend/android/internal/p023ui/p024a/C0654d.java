package net.nend.android.internal.p023ui.p024a;

/* renamed from: net.nend.android.internal.ui.a.d */
/* compiled from: JSCallback */
public class C0654d {

    /* renamed from: a */
    static final C0654d f913a = new C0654d(C0655a.CLICK_INFORMATION);

    /* renamed from: b */
    static final C0654d f914b = new C0654d(C0655a.CLICK_CLOSE);

    /* renamed from: c */
    private final C0655a f915c;

    /* renamed from: d */
    private final Object f916d;

    /* renamed from: e */
    private final String f917e;

    /* renamed from: net.nend.android.internal.ui.a.d$a */
    /* compiled from: JSCallback */
    public enum C0655a {
        VIEW_SOURCE,
        CLICK_AD,
        CLICK_INFORMATION,
        CLICK_CLOSE,
        VIDEO_RECT
    }

    C0654d(C0655a aVar, String str) {
        this(aVar, str, null);
    }

    C0654d(C0655a aVar, String str, Object obj) {
        this.f915c = aVar;
        this.f916d = obj;
        this.f917e = str;
    }

    private C0654d(C0655a aVar) {
        this(aVar, null, null);
    }

    /* renamed from: a */
    public C0655a mo8018a() {
        return this.f915c;
    }

    /* renamed from: b */
    public Object mo8019b() {
        return this.f916d;
    }

    /* renamed from: c */
    public String mo8020c() {
        return this.f917e;
    }
}
