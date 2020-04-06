package net.nend.android.internal.p022d;

import java.util.concurrent.Executor;

/* renamed from: net.nend.android.internal.d.k */
/* compiled from: Promise */
public interface C0646k<T> {
    /* renamed from: a */
    C0646k<T> mo7993a(Executor executor);

    /* renamed from: a */
    C0646k<T> mo7994a(C0628b<? super T, Throwable> bVar);

    /* renamed from: a */
    <R> C0646k<R> mo7995a(C0629c<? super T, Throwable, ? extends C0646k<? extends R>> cVar);

    /* renamed from: a */
    C0646k<T> mo7996a(C0630d<? super T> dVar);

    /* renamed from: a */
    C0646k<T> mo7997a(C0636g<Throwable, ? extends T> gVar);

    /* renamed from: b */
    C0646k<T> mo7998b(C0630d<Throwable> dVar);

    /* renamed from: b */
    <R> C0646k<R> mo7999b(C0636g<? super T, ? extends C0646k<? extends R>> gVar);

    /* renamed from: b */
    boolean mo8000b();

    /* renamed from: c */
    boolean mo8001c();
}
