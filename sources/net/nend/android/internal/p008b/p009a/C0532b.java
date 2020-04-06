package net.nend.android.internal.p008b.p009a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;
import net.nend.android.internal.p008b.p009a.p010a.C0530a;

/* renamed from: net.nend.android.internal.b.a.b */
/* compiled from: NendAdController */
public final class C0532b {

    /* renamed from: a */
    private final C0530a f590a;

    /* renamed from: b */
    private final Handler f591b;

    /* renamed from: c */
    private boolean f592c = false;

    /* renamed from: d */
    private boolean f593d = true;

    /* renamed from: net.nend.android.internal.b.a.b$a */
    /* compiled from: NendAdController */
    private static class C0533a extends Handler {

        /* renamed from: a */
        private WeakReference<C0530a> f594a;

        C0533a(Looper looper, C0530a aVar) {
            super(looper);
            this.f594a = new WeakReference<>(aVar);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            C0530a aVar = (C0530a) this.f594a.get();
            if (aVar != null) {
                aVar.mo7793n();
            }
        }
    }

    public C0532b(C0530a aVar) {
        if (aVar == null) {
            throw new NullPointerException("Ad object is null.");
        }
        this.f590a = aVar;
        this.f591b = new C0533a(Looper.getMainLooper(), aVar);
    }

    /* renamed from: a */
    public void mo7799a(boolean z) {
        this.f593d = z;
        if (z) {
            mo7801b();
        } else {
            mo7802c();
        }
    }

    /* renamed from: a */
    public void mo7798a() {
        mo7802c();
        this.f591b.sendEmptyMessage(718);
    }

    /* renamed from: b */
    public boolean mo7801b() {
        if (!this.f593d || !this.f592c || this.f591b.hasMessages(718)) {
            return false;
        }
        this.f591b.sendEmptyMessageDelayed(718, (long) (this.f590a.mo7773h() * 1000));
        return true;
    }

    /* renamed from: c */
    public void mo7802c() {
        this.f591b.removeMessages(718);
        this.f590a.mo7794o();
    }

    /* renamed from: b */
    public void mo7800b(boolean z) {
        this.f592c = z;
        if (z && this.f590a.mo7792m()) {
            mo7801b();
        }
    }
}
