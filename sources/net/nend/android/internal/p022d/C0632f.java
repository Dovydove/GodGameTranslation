package net.nend.android.internal.p022d;

import java.util.Timer;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;

/* renamed from: net.nend.android.internal.d.f */
/* compiled from: DeferredPromise */
class C0632f<T> implements C0631e<T>, C0646k<T> {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public T f874a = null;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public Throwable f875b = null;

    /* renamed from: c */
    private boolean f876c = false;

    /* renamed from: d */
    private C0630d<? super T> f877d = null;

    /* renamed from: e */
    private C0630d<Throwable> f878e = null;

    /* renamed from: f */
    private C0628b<? super T, Throwable> f879f = null;

    /* renamed from: g */
    private C0636g<Throwable, ? extends T> f880g = null;

    /* renamed from: h */
    private Executor f881h = null;

    /* renamed from: i */
    private Timer f882i;

    C0632f() {
    }

    /* renamed from: a */
    public void mo7991a(T t) {
        synchronized (this) {
            if (!this.f876c) {
                m882d();
                m880b(t);
            }
        }
    }

    /* renamed from: a */
    public void mo7992a(Throwable th) {
        synchronized (this) {
            if (!this.f876c) {
                m882d();
                if (this.f880g != null) {
                    m880b((T) this.f880g.mo7540a(th));
                } else {
                    m881b(th);
                }
            }
        }
    }

    /* renamed from: a */
    public C0646k<T> mo7990a() {
        return this;
    }

    /* renamed from: a */
    public C0646k<T> mo7993a(Executor executor) {
        synchronized (this) {
            this.f881h = executor;
        }
        return this;
    }

    /* renamed from: a */
    public C0646k<T> mo7996a(final C0630d<? super T> dVar) {
        if (dVar != null) {
            synchronized (this) {
                if (!this.f876c) {
                    this.f877d = dVar;
                } else if (this.f875b == null) {
                    if (this.f881h != null) {
                        this.f881h.execute(new Runnable() {
                            public void run() {
                                dVar.mo7666a(C0632f.this.f874a);
                            }
                        });
                    } else {
                        dVar.mo7666a(this.f874a);
                    }
                }
            }
        }
        return this;
    }

    /* renamed from: b */
    public C0646k<T> mo7998b(final C0630d<Throwable> dVar) {
        if (dVar != null) {
            synchronized (this) {
                if (!this.f876c) {
                    this.f878e = dVar;
                } else if (this.f875b != null) {
                    if (this.f881h != null) {
                        this.f881h.execute(new Runnable() {
                            public void run() {
                                dVar.mo7666a(C0632f.this.f875b);
                            }
                        });
                    } else {
                        dVar.mo7666a(this.f875b);
                    }
                }
            }
        }
        return this;
    }

    /* renamed from: a */
    public C0646k<T> mo7994a(final C0628b<? super T, Throwable> bVar) {
        if (bVar != null) {
            synchronized (this) {
                if (!this.f876c) {
                    this.f879f = bVar;
                } else if (this.f881h != null) {
                    this.f881h.execute(new Runnable() {
                        public void run() {
                            bVar.mo7687a(C0632f.this.f874a, C0632f.this.f875b);
                        }
                    });
                } else {
                    bVar.mo7687a(this.f874a, this.f875b);
                }
            }
        }
        return this;
    }

    /* renamed from: a */
    public C0646k<T> mo7997a(C0636g<Throwable, ? extends T> gVar) {
        synchronized (this) {
            if (!this.f876c) {
                this.f880g = gVar;
            } else if (this.f875b != null) {
                this.f874a = gVar.mo7540a(this.f875b);
                this.f875b = null;
            }
        }
        return this;
    }

    /* renamed from: b */
    public <R> C0646k<R> mo7999b(C0636g<? super T, ? extends C0646k<? extends R>> gVar) {
        return new C0640j((C0646k<T>) this, gVar).mo7993a(this.f881h);
    }

    /* renamed from: a */
    public <R> C0646k<R> mo7995a(C0629c<? super T, Throwable, ? extends C0646k<? extends R>> cVar) {
        return new C0640j((C0646k<T>) this, cVar).mo7993a(this.f881h);
    }

    /* renamed from: b */
    public boolean mo8000b() {
        boolean z;
        synchronized (this) {
            z = !this.f876c;
        }
        return z;
    }

    /* renamed from: c */
    public boolean mo8001c() {
        boolean z;
        synchronized (this) {
            if (!this.f876c) {
                mo7992a((Throwable) new CancellationException());
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    /* renamed from: b */
    private void m880b(T t) {
        this.f874a = t;
        this.f876c = true;
        try {
            mo7996a(this.f877d);
        } finally {
            mo7994a(this.f879f);
        }
    }

    /* renamed from: b */
    private void m881b(Throwable th) {
        this.f875b = th;
        this.f876c = true;
        try {
            mo7998b(this.f878e);
        } finally {
            mo7994a(this.f879f);
        }
    }

    /* renamed from: d */
    private void m882d() {
        if (this.f882i != null) {
            this.f882i.cancel();
            this.f882i = null;
        }
    }
}
