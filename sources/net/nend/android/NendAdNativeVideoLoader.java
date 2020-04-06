package net.nend.android;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import java.lang.ref.WeakReference;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import net.nend.android.NendAdNativeVideo.C0503a;
import net.nend.android.NendAdNativeVideo.VideoClickOption;
import net.nend.android.internal.p007a.C0523a;
import net.nend.android.internal.p008b.p012c.C0540b;
import net.nend.android.internal.p013c.p021f.C0608c;
import net.nend.android.internal.p022d.C0627a;
import net.nend.android.internal.p022d.C0628b;
import net.nend.android.internal.p022d.C0630d;
import net.nend.android.internal.p022d.C0646k;
import net.nend.android.internal.p022d.C0647l;
import net.nend.android.internal.utilities.C0740a;
import net.nend.android.internal.utilities.C0740a.C0745d;
import net.nend.android.internal.utilities.C0755e;
import net.nend.android.internal.utilities.C0757f;
import net.nend.android.internal.utilities.C0758g;
import net.nend.android.internal.utilities.p027a.C0749a;
import net.nend.android.internal.utilities.video.NendVideoAdClientError;

public class NendAdNativeVideoLoader {
    @VisibleForTesting

    /* renamed from: a */
    C0646k<C0540b> f470a;
    @VisibleForTesting

    /* renamed from: b */
    BlockingQueue<Callback> f471b;

    /* renamed from: c */
    private final Context f472c;

    /* renamed from: d */
    private final int f473d;

    /* renamed from: e */
    private final C0608c f474e;

    /* renamed from: f */
    private final C0627a f475f;

    public interface Callback {
        void onFailure(int i);

        void onSuccess(NendAdNativeVideo nendAdNativeVideo);
    }

    public NendAdNativeVideoLoader(Context context, int i, String str) {
        this(context, i, str, VideoClickOption.FullScreen);
    }

    public NendAdNativeVideoLoader(Context context, int i, String str, VideoClickOption videoClickOption) {
        this.f471b = new LinkedBlockingQueue();
        if (i <= 0) {
            throw new IllegalArgumentException(C0758g.ERR_INVALID_SPOT_ID.mo8197a("spot id : " + i));
        } else if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(C0758g.ERR_INVALID_API_KEY.mo8197a("api key : " + str));
        } else {
            this.f472c = context;
            this.f473d = i;
            this.f474e = new C0608c(context, i, str, videoClickOption);
            this.f475f = new C0627a(context.getMainLooper());
            C0755e.m1197a(context);
            C0647l.m923a(C0740a.m1170a().mo8184b(), (Callable<T>) new C0745d<T>(context)).mo7994a((C0628b<? super T, Throwable>) new C0628b<String, Throwable>() {
                /* renamed from: a */
                public void mo7687a(String str, Throwable th) {
                    if (TextUtils.isEmpty(str)) {
                        C0757f.m1211a("Cannot get Google Advertising ID...", th);
                    } else {
                        C0757f.m1217b("Google Advertising ID = " + str);
                    }
                }
            });
        }
    }

    /* renamed from: a */
    private boolean m379a() {
        if (this.f470a == null || !this.f470a.mo8000b()) {
            return false;
        }
        C0757f.m1220c("Ex loading of NendAdNativeVideo is not completed yet.");
        return true;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m380b() {
        Callback callback = (Callback) this.f471b.poll();
        if (callback != null) {
            loadAd(callback);
        }
    }

    public void loadAd(final Callback callback) {
        if (m379a()) {
            C0757f.m1217b("Added your loading request to queue...");
            this.f471b.add(callback);
            return;
        }
        this.f470a = this.f474e.mo7969b();
        this.f470a.mo7993a((Executor) this.f475f).mo7996a((C0630d<? super T>) new C0630d<C0540b>() {
            /* renamed from: a */
            public void mo7666a(C0540b bVar) {
                callback.onSuccess(NendAdNativeVideoLoader.this.m377a(bVar));
                NendAdNativeVideoLoader.this.m380b();
            }
        }).mo7998b((C0630d<Throwable>) new C0630d<Throwable>() {
            /* renamed from: a */
            public void mo7666a(Throwable th) {
                if (!(th instanceof CancellationException)) {
                    C0757f.m1218b("Failed to load ad.", th);
                    if (th instanceof C0523a) {
                        callback.onFailure(((C0523a) th).mo7777a());
                    } else {
                        callback.onFailure(NendVideoAdClientError.FAILED_INTERNAL.getCode());
                    }
                    NendAdNativeVideoLoader.this.m380b();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public NendAdNativeVideo m377a(C0540b bVar) {
        NendAdNativeVideo nendAdNativeVideo = null;
        if (bVar != null) {
            if (bVar.f615p != null) {
                nendAdNativeVideo = new C0503a().mo7674a(bVar.f615p).mo7677a();
            } else {
                nendAdNativeVideo = new C0503a().mo7676a(bVar).mo7675a(this.f474e.mo7965a()).mo7672a(this.f473d).mo7673a(C0749a.m1181a(bVar.f608i)).mo7677a();
                this.f474e.mo7966a(bVar.f607h);
            }
            nendAdNativeVideo.mo7634a(new WeakReference<>(this.f472c));
            nendAdNativeVideo.mo7639b(new WeakReference<>(this.f474e));
        }
        return nendAdNativeVideo;
    }

    public void setFillerImageNativeAd(int i, String str) {
        this.f474e.mo7967a(i, str);
    }

    public void setMediationName(String str) {
        this.f474e.mo7970b(str);
    }

    public void setUserId(String str) {
        this.f474e.mo7972c(str);
    }

    public void setUserFeature(NendAdUserFeature nendAdUserFeature) {
        this.f474e.mo7827a(nendAdUserFeature);
    }

    public void releaseLoader() {
        if (this.f470a != null && this.f470a.mo8000b()) {
            this.f470a.mo8001c();
        }
        this.f470a = null;
        this.f471b.clear();
    }
}
