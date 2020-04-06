package net.nend.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.SparseArray;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import net.nend.android.internal.p013c.p017d.C0576a;
import net.nend.android.internal.p013c.p017d.C0577b;
import net.nend.android.internal.p013c.p017d.C0580c;
import net.nend.android.internal.p023ui.activities.interstitial.NendAdInterstitialActivity;
import net.nend.android.internal.p023ui.views.p026b.C0714a;
import net.nend.android.internal.p023ui.views.p026b.C0714a.C0716b;
import net.nend.android.internal.p023ui.views.p026b.C0714a.C0717c;
import net.nend.android.internal.utilities.C0740a;
import net.nend.android.internal.utilities.C0740a.C0742a;
import net.nend.android.internal.utilities.C0740a.C0743b;
import net.nend.android.internal.utilities.C0740a.C0748f;
import net.nend.android.internal.utilities.C0755e;
import net.nend.android.internal.utilities.C0757f;
import net.nend.android.internal.utilities.C0758g;

public class NendAdInterstitial {

    /* renamed from: a */
    private static int f320a = Integer.MIN_VALUE;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public static OnCompletionListener f321b;

    /* renamed from: c */
    private static C0473b f322c = new C0473b() {
        /* renamed from: a */
        public void mo7517a(int i, NendAdInterstitialStatusCode nendAdInterstitialStatusCode) {
            if (NendAdInterstitial.f321b != null) {
                if (NendAdInterstitial.f321b instanceof OnCompletionListenerSpot) {
                    ((OnCompletionListenerSpot) NendAdInterstitial.f321b).onCompletion(nendAdInterstitialStatusCode, i);
                } else {
                    NendAdInterstitial.f321b.onCompletion(nendAdInterstitialStatusCode);
                }
            }
        }
    };
    /* access modifiers changed from: private */

    /* renamed from: d */
    public static SparseArray<C0468a> f323d = new SparseArray<>();
    public static boolean isAutoReloadEnabled = true;

    public enum NendAdInterstitialClickType {
        DOWNLOAD,
        CLOSE,
        INFORMATION
    }

    public enum NendAdInterstitialShowResult {
        AD_SHOW_SUCCESS,
        AD_LOAD_INCOMPLETE,
        AD_REQUEST_INCOMPLETE,
        AD_DOWNLOAD_INCOMPLETE,
        AD_FREQUENCY_NOT_RECHABLE,
        AD_SHOW_ALREADY
    }

    public enum NendAdInterstitialStatusCode {
        SUCCESS,
        INVALID_RESPONSE_TYPE,
        FAILED_AD_REQUEST,
        FAILED_AD_INCOMPLETE,
        FAILED_AD_DOWNLOAD
    }

    public interface OnClickListener {
        void onClick(NendAdInterstitialClickType nendAdInterstitialClickType);
    }

    public interface OnClickListenerSpot extends OnClickListener {
        void onClick(NendAdInterstitialClickType nendAdInterstitialClickType, int i);
    }

    public interface OnCompletionListener {
        void onCompletion(NendAdInterstitialStatusCode nendAdInterstitialStatusCode);
    }

    public interface OnCompletionListenerSpot extends OnCompletionListener {
        void onCompletion(NendAdInterstitialStatusCode nendAdInterstitialStatusCode, int i);
    }

    /* renamed from: net.nend.android.NendAdInterstitial$a */
    private static class C0468a implements C0743b<C0577b> {

        /* renamed from: a */
        private Context f327a;

        /* renamed from: b */
        private String f328b;
        /* access modifiers changed from: private */

        /* renamed from: c */
        public int f329c;
        /* access modifiers changed from: private */

        /* renamed from: d */
        public C0473b f330d;

        /* renamed from: e */
        private C0714a f331e;

        /* renamed from: f */
        private C0576a f332f;

        /* renamed from: g */
        private int f333g;

        /* renamed from: h */
        private String f334h;

        /* renamed from: i */
        private String f335i;

        /* renamed from: j */
        private String f336j;
        /* access modifiers changed from: private */

        /* renamed from: k */
        public C0472a f337k;

        /* renamed from: net.nend.android.NendAdInterstitial$a$a */
        private enum C0472a {
            LOADING,
            LOADED,
            NOT_LOADED
        }

        /* renamed from: net.nend.android.NendAdInterstitial$a$b */
        private interface C0473b {
            /* renamed from: a */
            void mo7517a(int i, NendAdInterstitialStatusCode nendAdInterstitialStatusCode);
        }

        private C0468a(int i, String str, C0473b bVar) {
            this.f328b = null;
            this.f329c = Integer.MIN_VALUE;
            this.f337k = C0472a.NOT_LOADED;
            this.f329c = i;
            this.f328b = str;
            this.f330d = bVar;
        }

        /* renamed from: a */
        private static String m214a(int i) {
            return String.format(Locale.US, "%s_%d", new Object[]{"count", Integer.valueOf(i)});
        }

        /* access modifiers changed from: private */
        /* renamed from: a */
        public void m218a(Context context) {
            if (Integer.MIN_VALUE != this.f329c && !TextUtils.isEmpty(this.f328b)) {
                if (this.f337k == C0472a.LOADING) {
                    C0757f.m1220c("Ad is loading.");
                    return;
                }
                this.f337k = C0472a.LOADING;
                this.f327a = context;
                this.f332f = new C0576a(context, this.f329c, this.f328b);
                C0740a.m1170a().mo8183a(new C0748f((C0743b<V>) this), new C0742a<C0577b>() {
                    /* renamed from: a */
                    public void mo7491a(C0577b bVar, Exception exc) {
                        C0468a.this.m221a(bVar);
                    }
                });
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: a */
        public NendAdInterstitialShowResult m215a(Activity activity, OnClickListener onClickListener) {
            if (this.f337k != C0472a.LOADED) {
                m218a(activity.getApplicationContext());
                return m234e();
            } else if (!m233d()) {
                m224b();
                return NendAdInterstitialShowResult.AD_DOWNLOAD_INCOMPLETE;
            } else if (!m231c(activity.getApplicationContext())) {
                return NendAdInterstitialShowResult.AD_FREQUENCY_NOT_RECHABLE;
            } else {
                if (m227b(activity, onClickListener)) {
                    m236f();
                    return NendAdInterstitialShowResult.AD_SHOW_SUCCESS;
                }
                m218a(activity.getApplicationContext());
                return NendAdInterstitialShowResult.AD_REQUEST_INCOMPLETE;
            }
        }

        /* renamed from: b */
        private boolean m227b(final Activity activity, final OnClickListener onClickListener) {
            if (this.f331e.mo8121b()) {
                return false;
            }
            this.f331e.setOnClickListener(new C0717c() {
                /* renamed from: a */
                public void mo7524a(NendAdInterstitialClickType nendAdInterstitialClickType) {
                    NendAdInterstitial.m211b(nendAdInterstitialClickType, onClickListener, C0468a.this.f329c);
                    C0468a.this.m225b(activity.getApplicationContext());
                }
            });
            activity.startActivity(new Intent(activity, NendAdInterstitialActivity.class).putExtras(NendAdInterstitialActivity.newBundle(this.f329c)));
            return true;
        }

        /* access modifiers changed from: private */
        /* renamed from: a */
        public boolean m222a() {
            if (this.f331e == null || !this.f331e.mo8120a()) {
                return false;
            }
            m225b(this.f331e.getContext());
            return true;
        }

        /* access modifiers changed from: private */
        /* renamed from: b */
        public void m225b(Context context) {
            if (NendAdInterstitial.isAutoReloadEnabled) {
                m218a(context);
            } else {
                NendAdInterstitial.f323d.delete(this.f329c);
            }
        }

        /* renamed from: b */
        private void m224b() {
            if (this.f331e != null) {
                this.f331e.mo8118a(this.f335i + "&ad=" + this.f336j + "&dn=");
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: c */
        public boolean m230c() {
            return this.f331e != null && this.f331e.mo8121b();
        }

        /* renamed from: d */
        private boolean m233d() {
            return this.f331e != null && this.f331e.mo8122c();
        }

        /* renamed from: e */
        private NendAdInterstitialShowResult m234e() {
            return this.f331e != null ? this.f331e.getStatus() : NendAdInterstitialShowResult.AD_LOAD_INCOMPLETE;
        }

        /* renamed from: c */
        private boolean m231c(Context context) {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            Editor edit = defaultSharedPreferences.edit();
            String a = m214a(this.f329c);
            int i = defaultSharedPreferences.getInt(a, 0);
            if (i >= this.f333g) {
                edit.putInt(a, 0);
                edit.apply();
                return true;
            }
            edit.putInt(a, i + 1);
            edit.apply();
            return false;
        }

        /* renamed from: f */
        private void m236f() {
            C0740a.m1170a().mo8182a(new C0748f(this.f334h + "&ad=" + this.f336j));
        }

        public String getRequestUrl() {
            return this.f332f != null ? this.f332f.mo7849b(C0755e.m1201b(this.f327a)) : "";
        }

        /* renamed from: a */
        public C0577b makeResponse(byte[] bArr) {
            if (bArr != null) {
                try {
                    return (C0577b) new C0580c(this.f327a).mo7867a(new String(bArr, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    C0757f.m1221c(C0758g.ERR_HTTP_REQUEST, e);
                }
            }
            return null;
        }

        /* access modifiers changed from: private */
        /* renamed from: a */
        public void m221a(C0577b bVar) {
            if (bVar == null) {
                this.f330d.mo7517a(this.f329c, NendAdInterstitialStatusCode.FAILED_AD_REQUEST);
                this.f337k = C0472a.NOT_LOADED;
            } else if (bVar.mo7902j() != null) {
                this.f330d.mo7517a(this.f329c, bVar.mo7902j());
                this.f337k = C0472a.NOT_LOADED;
            } else {
                this.f334h = bVar.mo7893a();
                this.f333g = bVar.mo7895c();
                this.f335i = bVar.mo7894b();
                this.f336j = bVar.mo7897e();
                this.f331e = new C0714a(this.f327a, bVar);
                this.f331e.setOnCompletionListener(new C0716b() {
                    /* renamed from: a */
                    public void mo7525a(NendAdInterstitialStatusCode nendAdInterstitialStatusCode) {
                        C0468a.this.f337k = C0472a.LOADED;
                        C0468a.this.f330d.mo7517a(C0468a.this.f329c, nendAdInterstitialStatusCode);
                    }
                });
                m224b();
            }
        }

        /* access modifiers changed from: private */
        /* renamed from: g */
        public C0714a m237g() {
            return this.f331e;
        }
    }

    public static void loadAd(Context context, String str, int i) {
        C0755e.m1197a(context);
        C0468a aVar = (C0468a) f323d.get(i);
        if (aVar == null) {
            aVar = new C0468a(i, str, f322c);
            f323d.put(i, aVar);
        }
        aVar.m218a(context);
        f320a = i;
    }

    public static NendAdInterstitialShowResult showAd(Activity activity) {
        return m207a(activity, f320a, (OnClickListener) null);
    }

    public static NendAdInterstitialShowResult showAd(Activity activity, OnClickListener onClickListener) {
        return m207a(activity, f320a, onClickListener);
    }

    public static NendAdInterstitialShowResult showAd(Activity activity, int i) {
        return m207a(activity, i, (OnClickListener) null);
    }

    public static NendAdInterstitialShowResult showAd(Activity activity, int i, OnClickListener onClickListener) {
        return m207a(activity, i, onClickListener);
    }

    public static boolean dismissAd() {
        boolean z = false;
        for (int i = 0; i < f323d.size(); i++) {
            if (((C0468a) f323d.get(f323d.keyAt(i))).m222a()) {
                z = true;
            }
        }
        return z;
    }

    public static void setListener(OnCompletionListener onCompletionListener) {
        f321b = onCompletionListener;
    }

    /* renamed from: a */
    private static NendAdInterstitialShowResult m207a(Activity activity, int i, OnClickListener onClickListener) {
        if (m212c()) {
            return NendAdInterstitialShowResult.AD_SHOW_ALREADY;
        }
        C0468a aVar = (C0468a) f323d.get(i);
        if (aVar != null) {
            return aVar.m215a(activity, onClickListener);
        }
        return NendAdInterstitialShowResult.AD_LOAD_INCOMPLETE;
    }

    /* renamed from: c */
    private static boolean m212c() {
        for (int i = 0; i < f323d.size(); i++) {
            if (((C0468a) f323d.get(f323d.keyAt(i))).m230c()) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static void m211b(NendAdInterstitialClickType nendAdInterstitialClickType, OnClickListener onClickListener, int i) {
        if (onClickListener == null) {
            return;
        }
        if (onClickListener instanceof OnClickListenerSpot) {
            ((OnClickListenerSpot) onClickListener).onClick(nendAdInterstitialClickType, i);
        } else {
            onClickListener.onClick(nendAdInterstitialClickType);
        }
    }

    public static C0714a getInterstitialAdView(int i) {
        C0468a aVar = (C0468a) f323d.get(i);
        if (aVar != null) {
            return aVar.m237g();
        }
        return null;
    }
}
