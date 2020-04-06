package net.nend.android.internal.p013c;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.media.AudioManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.annotation.VisibleForTesting;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.webkit.WebView;
import java.lang.ref.WeakReference;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import net.nend.android.BuildConfig;
import net.nend.android.NendAdUserFeature;
import net.nend.android.internal.p007a.C0523a;
import net.nend.android.internal.p008b.C0525a;
import net.nend.android.internal.p013c.C0574d.C0575a;
import net.nend.android.internal.p013c.p018e.p019a.p020a.C0587b;
import net.nend.android.internal.p013c.p018e.p019a.p020a.C0590c;
import net.nend.android.internal.p013c.p018e.p019a.p020a.C0590c.C0591a;
import net.nend.android.internal.p013c.p018e.p019a.p020a.C0592d;
import net.nend.android.internal.p013c.p018e.p019a.p020a.C0592d.C0594a;
import net.nend.android.internal.p013c.p021f.C0596a;
import net.nend.android.internal.p022d.C0627a;
import net.nend.android.internal.p022d.C0636g;
import net.nend.android.internal.p022d.C0646k;
import net.nend.android.internal.p022d.C0647l;
import net.nend.android.internal.p022d.C0650n;
import net.nend.android.internal.utilities.C0740a;
import net.nend.android.internal.utilities.C0740a.C0744c;
import net.nend.android.internal.utilities.C0740a.C0745d;
import net.nend.android.internal.utilities.C0740a.C0748f;
import net.nend.android.internal.utilities.C0754d;
import net.nend.android.internal.utilities.C0758g;
import net.nend.android.internal.utilities.video.C0768d;
import net.nend.android.internal.utilities.video.C0773e;
import net.nend.android.internal.utilities.video.NendVideoAdClientError;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: net.nend.android.internal.c.a */
/* compiled from: AbsNend2AdLoader */
public abstract class C0546a {

    /* renamed from: c */
    private static String f624c;
    /* access modifiers changed from: protected */

    /* renamed from: a */
    public C0596a f625a;

    /* renamed from: b */
    protected WeakReference<Context> f626b;

    /* renamed from: d */
    private NendAdUserFeature f627d;

    /* renamed from: net.nend.android.internal.c.a$a */
    /* compiled from: AbsNend2AdLoader */
    protected static abstract class C0550a<V> implements C0744c<V> {
        /* access modifiers changed from: protected */
        /* renamed from: a */
        public abstract V mo7832a(JSONObject jSONObject);

        protected C0550a() {
        }

        public V makeResponse(byte[] bArr) {
            return null;
        }

        /* renamed from: a */
        public V mo7831a(C0595f fVar) {
            int b = fVar.mo7948b();
            String a = m529a(fVar.mo7947a());
            if (C0758g.SUCCESS.mo8196a() != b) {
                throw new C0523a(b, a);
            }
            try {
                return mo7832a(new JSONObject(a));
            } catch (JSONException e) {
                throw new IllegalArgumentException(e.getCause());
            }
        }

        /* renamed from: a */
        private String m529a(byte[] bArr) {
            if (bArr == null || bArr.length == 0) {
                return "";
            }
            return new String(bArr);
        }
    }

    /* renamed from: a */
    public abstract C0575a mo7824a(int i, String str, String str2);

    public C0546a(Context context) {
        this.f626b = new WeakReference<>(context);
        this.f625a = C0596a.m753a(context);
    }

    /* renamed from: a */
    protected static String m511a(String str) {
        return C0754d.m1193a(str);
    }

    /* renamed from: c */
    private static String m514c(Context context) {
        if (TextUtils.isEmpty(f624c)) {
            WebView webView = new WebView(context);
            f624c = webView.getSettings().getUserAgentString();
            webView.destroy();
        }
        return f624c;
    }

    /* renamed from: d */
    private static String m515d(Context context) {
        if (VERSION.SDK_INT >= 24) {
            return context.getResources().getConfiguration().getLocales().get(0).getLanguage();
        }
        return context.getResources().getConfiguration().locale.getLanguage();
    }

    /* renamed from: e */
    private static int m516e(Context context) {
        return context.getResources().getConfiguration().orientation == 1 ? 1 : 2;
    }

    /* renamed from: f */
    private static C0590c m517f(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return new C0591a().mo7938a(displayMetrics.widthPixels).mo7940b(displayMetrics.heightPixels).mo7941c(displayMetrics.densityDpi).mo7939a();
    }

    /* renamed from: a */
    private static C0592d m512a(JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        return new C0594a().mo7943a(jSONObject).mo7945b(jSONObject2).mo7946c(jSONObject3).mo7944a();
    }

    @VisibleForTesting
    /* renamed from: a */
    protected static int m510a(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        if (audioManager != null) {
            return audioManager.getStreamVolume(3);
        }
        return 0;
    }

    @VisibleForTesting
    /* renamed from: b */
    protected static String m513b(Context context) {
        if (C0773e.m1264a(context, "android.permission.READ_PHONE_STATE")) {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null) {
                return telephonyManager.getNetworkOperatorName();
            }
        }
        return null;
    }

    /* renamed from: g */
    private static String m518g(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            return "";
        }
    }

    /* renamed from: a */
    public void mo7827a(NendAdUserFeature nendAdUserFeature) {
        this.f627d = nendAdUserFeature;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public <V extends C0525a> C0646k<V> mo7825a(int i, String str, String str2, final String str3, final C0744c<V> cVar) {
        final Context context = (Context) this.f626b.get();
        if (context == null) {
            return C0647l.m922a((Throwable) new IllegalStateException("Context is null"));
        }
        final ExecutorService b = C0740a.m1170a().mo8184b();
        C0627a aVar = new C0627a(context.getMainLooper());
        final C0575a a = mo7824a(i, str, str2);
        final C0768d dVar = new C0768d(context);
        return C0647l.m923a(b, (Callable<T>) new C0745d<T>(context)).mo7999b((C0636g<? super T, ? extends C0646k<? extends R>>) new C0636g<String, C0646k<? extends C0650n<JSONObject, JSONObject, JSONObject>>>() {
            /* renamed from: a */
            public C0646k<? extends C0650n<JSONObject, JSONObject, JSONObject>> mo7540a(String str) {
                a.f728a.mo7934d(str);
                return C0647l.m926a(dVar.mo8217b(), dVar.mo8216a(), dVar.mo8218c());
            }
        }).mo7993a((Executor) aVar).mo7999b((C0636g<? super T, ? extends C0646k<? extends R>>) new C0636g<C0650n<JSONObject, JSONObject, JSONObject>, C0646k<? extends JSONObject>>() {
            /* renamed from: a */
            public C0646k<? extends JSONObject> mo7540a(C0650n<JSONObject, JSONObject, JSONObject> nVar) {
                return C0546a.this.mo7826a(context, a, nVar, str3);
            }
        }).mo7999b((C0636g<? super T, ? extends C0646k<? extends R>>) new C0636g<JSONObject, C0646k<? extends V>>() {
            /* renamed from: a */
            public C0646k<? extends V> mo7540a(JSONObject jSONObject) {
                if (C0626g.m868a(context)) {
                    return C0647l.m923a(b, (Callable<T>) C0748f.m1180a(cVar, jSONObject));
                }
                return C0647l.m922a((Throwable) new C0523a(NendVideoAdClientError.NETWORK_IS_NOT_ACTIVE));
            }
        });
    }

    /* renamed from: a */
    public C0646k<JSONObject> mo7826a(Context context, C0575a aVar, C0650n<JSONObject, JSONObject, JSONObject> nVar, String str) {
        C0587b a = aVar.f728a.mo7924a(2).mo7925a(VERSION.RELEASE).mo7931b(Build.MODEL).mo7933c(m514c(context)).mo7935e(m515d(context)).mo7930b(m516e(context)).mo7926a(m517f(context)).mo7927a(m512a((JSONObject) nVar.f908a, (JSONObject) nVar.f909b, (JSONObject) nVar.f910c)).mo7932c(m510a(context)).mo7936f(m513b(context)).mo7928a(C0626g.m870b(context)).mo7929a();
        aVar.mo7888a(a).mo7887a(aVar.f729b.mo7919a(context.getPackageName()).mo7921b(m518g(context)).mo7922c(str).mo7920a()).mo7891c("Nend SDK").mo7892d(BuildConfig.VERSION_NAME).mo7884a(System.currentTimeMillis()).mo7886a(this.f627d);
        try {
            return C0647l.m921a(aVar.mo7889a().mo7882a());
        } catch (JSONException e) {
            return C0647l.m922a(e.getCause());
        }
    }
}
