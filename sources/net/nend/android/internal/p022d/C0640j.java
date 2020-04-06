package net.nend.android.internal.p022d;

/* renamed from: net.nend.android.internal.d.j */
/* compiled from: PipedPromise */
class C0640j<T, R> extends C0632f<R> {

    /* renamed from: a */
    private final C0646k<T> f891a;

    /* renamed from: b */
    private C0646k<? extends R> f892b;

    private C0640j(C0646k<T> kVar) {
        this.f891a = kVar;
    }

    C0640j(C0646k<T> kVar, final C0636g<? super T, ? extends C0646k<? extends R>> gVar) {
        this(kVar);
        m901c(new C0630d<T>() {
            /* renamed from: a */
            public void mo7666a(T t) {
                try {
                    C0640j.this.m899a((C0646k) gVar.mo7540a(t));
                } catch (Exception e) {
                    C0640j.this.mo7992a(e.getCause());
                }
            }
        });
    }

    C0640j(C0646k<T> kVar, final C0629c<? super T, Throwable, ? extends C0646k<? extends R>> cVar) {
        this(kVar);
        m900b(new C0628b<T, Throwable>() {
            /* renamed from: a */
            public void mo7687a(T t, Throwable th) {
                try {
                    C0640j.this.m899a((C0646k) cVar.mo7538a(t, th));
                } catch (Exception e) {
                    C0640j.this.mo7992a(e.getCause());
                }
            }
        });
    }

    /* renamed from: c */
    private void m901c(final C0630d<? super T> dVar) {
        m900b(new C0628b<T, Throwable>() {
            /* renamed from: a */
            public void mo7687a(T t, Throwable th) {
                if (th != null) {
                    C0640j.this.mo7992a(th);
                } else {
                    dVar.mo7666a(t);
                }
            }
        });
    }

    /* renamed from: b */
    private void m900b(C0628b<? super T, Throwable> bVar) {
        this.f891a.mo7994a(bVar);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m899a(C0646k<? extends R> kVar) {
        this.f892b = kVar;
        this.f892b.mo7996a((C0630d<? super T>) new C0630d<R>() {
            /* renamed from: a */
            public void mo7666a(R r) {
                C0640j.this.mo7991a(r);
            }
        });
        this.f892b.mo7998b((C0630d<Throwable>) new C0630d<Throwable>() {
            /* renamed from: a */
            public void mo7666a(Throwable th) {
                C0640j.this.mo7992a(th);
            }
        });
    }

    /* renamed from: c */
    public boolean mo8001c() {
        return this.f891a.mo8001c() || (this.f892b != null && this.f892b.mo8001c()) || super.mo8001c();
    }
}
