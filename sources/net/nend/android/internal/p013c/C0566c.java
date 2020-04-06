package net.nend.android.internal.p013c;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.SparseArray;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import net.nend.android.internal.p007a.C0524b;
import net.nend.android.internal.utilities.C0757f;
import net.nend.android.internal.utilities.C0758g;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: net.nend.android.internal.c.c */
/* compiled from: AbsNendAdResponseParser */
public abstract class C0566c<T> {

    /* renamed from: a */
    static final /* synthetic */ boolean f684a = (!C0566c.class.desiredAssertionStatus());

    /* renamed from: b */
    private final PackageManager f685b;

    /* renamed from: net.nend.android.internal.c.c$a */
    /* compiled from: AbsNendAdResponseParser */
    protected enum C0567a {
        UNSUPPORTED(0),
        BANNER_NORMAL(1),
        BANNER_WEB_VIEW(2),
        BANNER_APP_TARGETING(3),
        BANNER_DYNAMIC_RETARGETING(4),
        ICON_NORMAL(11),
        ICON_APP_TARGETING(13),
        INTERSTITIAL_NORMAL(21),
        INTERSTITIAL_APP_TARGETING(23),
        INTERSTITIAL_APP_TARGETING_ICON(24),
        INTERSTITIAL_APP_TARGETING_RECT(25),
        NATIVE_NORMAL(31),
        NATIVE_APP_TARGETING(32);
        

        /* renamed from: n */
        private static final SparseArray<C0567a> f699n = null;

        /* renamed from: o */
        private int f701o;

        static {
            int i;
            C0567a[] values;
            f699n = new SparseArray<>();
            for (C0567a aVar : values()) {
                f699n.put(aVar.f701o, aVar);
            }
        }

        private C0567a(int i) {
            this.f701o = i;
        }

        /* renamed from: a */
        protected static C0567a m607a(int i) {
            return (C0567a) f699n.get(i, UNSUPPORTED);
        }
    }

    /* renamed from: a */
    public abstract T mo7847a(C0567a aVar, JSONObject jSONObject);

    public C0566c(Context context) {
        if (context == null) {
            throw new NullPointerException(C0758g.ERR_INVALID_CONTEXT.mo8198b());
        }
        this.f685b = context.getPackageManager();
    }

    /* renamed from: a */
    public T mo7867a(String str) {
        if (str != null) {
            try {
                if (str.length() != 0) {
                    JSONObject jSONObject = new JSONObject(URLDecoder.decode(str, "UTF-8"));
                    int i = jSONObject.getInt("status_code");
                    if (i == C0758g.SUCCESS.mo8196a()) {
                        return mo7847a(C0567a.m607a(jSONObject.getInt("response_type")), jSONObject);
                    }
                    T a = mo7864a(i);
                    if (a != null) {
                        return a;
                    }
                    throw new C0524b(C0758g.ERR_INVALID_AD_STATUS, "Ad status : " + jSONObject.getInt("status_code") + ", Message : " + jSONObject.getString("message"));
                }
            } catch (UnsupportedEncodingException e) {
                if (!f684a) {
                    throw new AssertionError();
                }
            } catch (JSONException e2) {
                C0757f.m1219b(C0758g.ERR_FAILED_TO_PARSE, (Throwable) e2);
            } catch (C0524b e3) {
                C0757f.m1219b(C0758g.ERR_FAILED_TO_PARSE, (Throwable) e3);
            } catch (IllegalArgumentException e4) {
                C0757f.m1219b(C0758g.ERR_FAILED_TO_PARSE, (Throwable) e4);
            }
        }
        throw new IllegalArgumentException(C0758g.ERR_INVALID_RESPONSE.mo8198b());
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public T mo7864a(int i) {
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo7868a(JSONArray jSONArray) {
        if (f684a || jSONArray != null) {
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                int i2 = jSONObject.getInt("logical_operator");
                if (i2 == 1) {
                    try {
                        this.f685b.getPackageInfo(jSONObject.getString("url_scheme"), 1);
                    } catch (NameNotFoundException | RuntimeException e) {
                        return false;
                    }
                } else if (i2 != 2) {
                    return false;
                } else {
                    try {
                        this.f685b.getPackageInfo(jSONObject.getString("url_scheme"), 1);
                        return false;
                    } catch (NameNotFoundException e2) {
                    } catch (RuntimeException e3) {
                        return false;
                    }
                }
            }
            return true;
        }
        throw new AssertionError();
    }
}
