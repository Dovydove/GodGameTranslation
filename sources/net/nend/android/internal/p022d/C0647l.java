package net.nend.android.internal.p022d;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: net.nend.android.internal.d.l */
/* compiled from: PromiseLite */
public class C0647l {
    /* renamed from: a */
    public static <T> C0631e<T> m920a() {
        return new C0632f();
    }

    /* renamed from: a */
    public static <T> C0646k<T> m921a(T t) {
        C0631e a = m920a();
        a.mo7991a(t);
        return a.mo7990a();
    }

    /* renamed from: a */
    public static <T> C0646k<T> m922a(Throwable th) {
        C0631e a = m920a();
        a.mo7992a(th);
        return a.mo7990a();
    }

    /* renamed from: a */
    public static <T> C0646k<T> m923a(ExecutorService executorService, Callable<T> callable) {
        return new C0637h(executorService, callable);
    }

    /* renamed from: a */
    public static <T1, T2> C0646k<C0649m<T1, T2>> m925a(C0646k<? extends T1> kVar, C0646k<? extends T2> kVar2) {
        return m924a((T) new C0649m(), (C0646k<?>[]) new C0646k[]{kVar, kVar2});
    }

    /* renamed from: a */
    public static <T1, T2, T3> C0646k<C0650n<T1, T2, T3>> m926a(C0646k<? extends T1> kVar, C0646k<? extends T2> kVar2, C0646k<? extends T3> kVar3) {
        return m924a((T) new C0650n(), (C0646k<?>[]) new C0646k[]{kVar, kVar2, kVar3});
    }

    /* renamed from: a */
    private static <T extends C0639i> C0646k<T> m924a(T t, C0646k<?>... kVarArr) {
        if (t == null || kVarArr.length == 0) {
            return m922a((Throwable) new IllegalArgumentException("An empty parameters was passed."));
        }
        int length = kVarArr.length;
        final Object[] objArr = new Object[length];
        final AtomicInteger atomicInteger = new AtomicInteger(length);
        final C0631e a = m920a();
        for (final int i = 0; i < length; i++) {
            final T t2 = t;
            kVarArr[i].mo7994a((C0628b<? super T, Throwable>) new C0628b<Object, Throwable>() {
                /* renamed from: a */
                public void mo7687a(Object obj, Throwable th) {
                    if (obj != null) {
                        objArr[i] = obj;
                    }
                    if (atomicInteger.decrementAndGet() == 0) {
                        t2.mo8006a(objArr);
                        a.mo7991a(t2);
                    }
                }
            });
        }
        return a.mo7990a();
    }
}
