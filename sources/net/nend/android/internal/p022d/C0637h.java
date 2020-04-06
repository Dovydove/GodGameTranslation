package net.nend.android.internal.p022d;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/* renamed from: net.nend.android.internal.d.h */
/* compiled from: FuturePromise */
class C0637h<T> extends C0632f<T> {

    /* renamed from: a */
    private Future<?> f889a;

    C0637h(ExecutorService executorService, Callable<T> callable) {
        if (executorService == null || callable == null) {
            mo7992a((Throwable) new NullPointerException());
            return;
        }
        C06381 r0 = new FutureTask<T>(callable) {
            /* access modifiers changed from: protected */
            public void done() {
                if (!isCancelled()) {
                    try {
                        C0637h.this.mo7991a(get());
                    } catch (InterruptedException | ExecutionException e) {
                        C0637h.this.mo7992a(e.getCause());
                    }
                } else {
                    C0637h.this.mo7992a((Throwable) new CancellationException());
                }
            }
        };
        executorService.submit(r0);
        this.f889a = r0;
    }

    /* renamed from: c */
    public boolean mo8001c() {
        return this.f889a != null && this.f889a.cancel(true);
    }
}
