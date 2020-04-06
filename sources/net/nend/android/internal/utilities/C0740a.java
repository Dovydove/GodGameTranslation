package net.nend.android.internal.utilities;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadFactory;
import net.nend.android.internal.p013c.C0582e;
import net.nend.android.internal.p013c.C0595f;
import org.json.JSONObject;

/* renamed from: net.nend.android.internal.utilities.a */
/* compiled from: NendAdExecutor */
public class C0740a {

    /* renamed from: a */
    private static C0740a f1126a = null;

    /* renamed from: b */
    private ExecutorService f1127b = Executors.newCachedThreadPool(new ThreadFactory() {
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("net.nend.android.execution.thread");
            thread.setPriority(10);
            return thread;
        }
    });

    /* renamed from: net.nend.android.internal.utilities.a$a */
    /* compiled from: NendAdExecutor */
    public interface C0742a<V> {
        /* renamed from: a */
        void mo7491a(V v, Exception exc);
    }

    /* renamed from: net.nend.android.internal.utilities.a$b */
    /* compiled from: NendAdExecutor */
    public interface C0743b<T> {
        String getRequestUrl();

        T makeResponse(byte[] bArr);
    }

    /* renamed from: net.nend.android.internal.utilities.a$c */
    /* compiled from: NendAdExecutor */
    public interface C0744c<T> extends C0743b<T> {
        /* renamed from: a */
        T mo7831a(C0595f fVar);
    }

    /* renamed from: net.nend.android.internal.utilities.a$d */
    /* compiled from: NendAdExecutor */
    public static class C0745d implements Callable<String> {

        /* renamed from: a */
        private final WeakReference<Context> f1129a;

        public C0745d(Context context) {
            this.f1129a = new WeakReference<>(context);
        }

        /* renamed from: a */
        public String call() {
            Context context = (Context) this.f1129a.get();
            if (context != null) {
                return C0755e.m1204d(context);
            }
            return "";
        }
    }

    /* renamed from: net.nend.android.internal.utilities.a$e */
    /* compiled from: NendAdExecutor */
    private class C0746e<V> extends FutureTask<V> {
        /* access modifiers changed from: private */

        /* renamed from: b */
        public final C0742a<V> f1131b;

        /* renamed from: c */
        private volatile boolean f1132c = false;

        public C0746e(Callable<V> callable, C0742a<V> aVar) {
            super(callable);
            this.f1131b = aVar;
        }

        public boolean cancel(boolean z) {
            this.f1132c = true;
            if (isDone() || super.isCancelled()) {
                return false;
            }
            return super.cancel(z);
        }

        public boolean isCancelled() {
            return super.isCancelled() || this.f1132c;
        }

        /* access modifiers changed from: protected */
        public void done() {
            if (!isCancelled() && this.f1131b != null) {
                try {
                    m1178a(get(), null);
                } catch (InterruptedException | ExecutionException e) {
                    C0757f.m1218b("Failed to execute task.", (Throwable) e);
                    m1178a(null, e);
                }
            }
        }

        /* renamed from: a */
        private void m1178a(final V v, final Exception exc) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    if (!C0746e.this.isCancelled()) {
                        C0746e.this.f1131b.mo7491a(v, exc);
                    }
                }
            });
        }
    }

    /* renamed from: net.nend.android.internal.utilities.a$f */
    /* compiled from: NendAdExecutor */
    public static class C0748f<V> implements Callable<V> {

        /* renamed from: a */
        private final WeakReference<C0743b<V>> f1136a;

        /* renamed from: b */
        private final String f1137b;

        /* renamed from: c */
        private final JSONObject f1138c;

        /* renamed from: d */
        private final String f1139d;

        /* renamed from: e */
        private final boolean f1140e;

        /* renamed from: a */
        public static <V> C0748f<V> m1180a(C0744c<V> cVar, JSONObject jSONObject) {
            return new C0748f<>(cVar, jSONObject, "POST");
        }

        /* renamed from: a */
        public static <V> C0748f<V> m1179a(C0743b<V> bVar, JSONObject jSONObject) {
            return new C0748f<>(bVar, jSONObject, "PUT");
        }

        public C0748f(String str) {
            this.f1136a = null;
            this.f1137b = str;
            this.f1138c = null;
            this.f1139d = "GET";
            this.f1140e = true;
        }

        public C0748f(C0743b<V> bVar) {
            this(bVar, null, "GET");
        }

        private C0748f(C0743b<V> bVar, JSONObject jSONObject, String str) {
            this.f1136a = new WeakReference<>(bVar);
            this.f1137b = null;
            this.f1138c = jSONObject;
            this.f1139d = str;
            this.f1140e = !(bVar instanceof C0744c);
        }

        public V call() {
            String str;
            C0743b bVar = this.f1136a != null ? (C0743b) this.f1136a.get() : null;
            if (bVar != null) {
                str = bVar.getRequestUrl();
            } else {
                str = this.f1137b;
            }
            if (!TextUtils.isEmpty(str)) {
                C0595f a = C0582e.m699a(str, this.f1139d, this.f1138c, this.f1140e);
                if (bVar != null) {
                    if (this.f1140e) {
                        return bVar.makeResponse(a.mo7947a());
                    }
                    return ((C0744c) bVar).mo7831a(a);
                }
            } else {
                C0757f.m1213a(C0758g.ERR_UNEXPECTED);
            }
            return null;
        }
    }

    private C0740a() {
    }

    /* renamed from: a */
    public static synchronized C0740a m1170a() {
        C0740a aVar;
        synchronized (C0740a.class) {
            if (f1126a == null) {
                f1126a = new C0740a();
            }
            aVar = f1126a;
        }
        return aVar;
    }

    /* renamed from: a */
    public synchronized <V> Future<V> mo8182a(Callable<V> callable) {
        return mo8183a(callable, null);
    }

    /* renamed from: a */
    public synchronized <V> Future<V> mo8183a(Callable<V> callable, C0742a<V> aVar) {
        C0746e eVar;
        eVar = new C0746e(callable, aVar);
        this.f1127b.execute(eVar);
        return eVar;
    }

    /* renamed from: b */
    public synchronized ExecutorService mo8184b() {
        return this.f1127b;
    }
}
