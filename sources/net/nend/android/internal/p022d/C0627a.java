package net.nend.android.internal.p022d;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import java.util.concurrent.Executor;

/* renamed from: net.nend.android.internal.d.a */
/* compiled from: AndroidExecutor */
public class C0627a implements Executor {

    /* renamed from: a */
    private final Handler f873a;

    public C0627a() {
        this(Looper.getMainLooper());
    }

    public C0627a(Looper looper) {
        this.f873a = new Handler(looper);
    }

    public void execute(@Nullable Runnable runnable) {
        this.f873a.post(runnable);
    }
}
