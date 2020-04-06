package net.nend.android.internal.p013c.p021f;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri.Builder;
import android.support.annotation.VisibleForTesting;
import java.util.ArrayList;
import java.util.Iterator;
import net.nend.android.internal.p008b.C0525a;
import net.nend.android.internal.p013c.C0626g;
import net.nend.android.internal.utilities.C0740a;
import net.nend.android.internal.utilities.C0740a.C0742a;
import net.nend.android.internal.utilities.C0740a.C0743b;
import net.nend.android.internal.utilities.C0740a.C0748f;
import net.nend.android.internal.utilities.C0757f;
import net.nend.android.internal.utilities.video.C0762a;
import net.nend.android.internal.utilities.video.C0764b;
import org.andengine.util.level.constants.LevelConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: net.nend.android.internal.c.f.b */
/* compiled from: EventTransmitter */
public class C0604b {

    /* renamed from: a */
    private static final String f832a = m791a("start");

    /* renamed from: b */
    private static final String f833b = m791a("start/native");

    /* renamed from: c */
    private static final String f834c = m791a("stop");

    /* renamed from: d */
    private static final String f835d = m791a("view");

    /* renamed from: e */
    private static final String f836e = m791a("close_endcard");

    /* renamed from: f */
    private static final String f837f = m791a("click");

    /* renamed from: g */
    private static BroadcastReceiver f838g;

    /* renamed from: net.nend.android.internal.c.f.b$a */
    /* compiled from: EventTransmitter */
    private static class C0607a implements C0743b<String> {

        /* renamed from: a */
        private final String f839a;

        C0607a(String str) {
            this.f839a = str;
        }

        public String getRequestUrl() {
            return this.f839a;
        }

        /* renamed from: a */
        public String makeResponse(byte[] bArr) {
            return C0604b.m807b(bArr);
        }
    }

    /* renamed from: a */
    private static String m791a(String str) {
        return new Builder().scheme("https").authority("vdapp.nend.net").path("v1/video/event/" + str).toString();
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static String m807b(byte[] bArr) {
        if (bArr != null && bArr.length != 0) {
            return new String(bArr);
        }
        throw new NullPointerException("response body is empty.");
    }

    /* renamed from: b */
    private static JSONObject m808b(String str) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", str);
        jSONObject.put("clientTime", System.currentTimeMillis());
        return jSONObject;
    }

    /* renamed from: c */
    private static JSONObject m811c(String str) {
        try {
            return m808b(str);
        } catch (JSONException e) {
            return null;
        }
    }

    @VisibleForTesting
    /* renamed from: a */
    static JSONObject m793a(String str, int i, int i2) {
        try {
            JSONObject b = m808b(str);
            b.put(LevelConstants.TAG_LEVEL_ATTRIBUTE_WIDTH, i);
            b.put(LevelConstants.TAG_LEVEL_ATTRIBUTE_HEIGHT, i2);
            return b;
        } catch (JSONException e) {
            return null;
        }
    }

    @VisibleForTesting
    /* renamed from: a */
    static JSONObject m795a(String str, boolean z) {
        try {
            JSONObject b = m808b(str);
            if (!z) {
                return b;
            }
            b.put("isFullScreen", 1);
            return b;
        } catch (JSONException e) {
            return null;
        }
    }

    /* renamed from: a */
    private static JSONObject m796a(String str, boolean z, boolean z2, long j) {
        try {
            JSONObject b = m808b(str);
            if (z) {
                b.put("type", 1);
            } else if (z2) {
                b.put("type", 2);
            } else {
                b.put("type", 3);
            }
            if (j < 0) {
                return b;
            }
            b.put("timeSpentViewing", j);
            return b;
        } catch (JSONException e) {
            return null;
        }
    }

    /* renamed from: a */
    private static JSONObject m794a(String str, long j) {
        try {
            JSONObject b = m808b(str);
            if (j < 0) {
                return b;
            }
            b.put("timeSpentViewing", j);
            return b;
        } catch (JSONException e) {
            return null;
        }
    }

    /* renamed from: a */
    private static void m804a(Context context, C0743b<String> bVar, JSONObject jSONObject) {
        try {
            C0764b.m1247a(context, new JSONObject().put("requestUrl", bVar.getRequestUrl()).put("postJsonObj", jSONObject.toString()));
        } catch (JSONException e) {
        }
        if (f838g == null) {
            f838g = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if (C0626g.m868a(context)) {
                        C0604b.m809b(context);
                    }
                }
            };
            C0626g.m867a(context, f838g);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static void m809b(Context context) {
        ArrayList a = C0764b.m1246a(context);
        if (!a.isEmpty()) {
            Iterator it = a.iterator();
            while (it.hasNext()) {
                JSONObject jSONObject = (JSONObject) it.next();
                try {
                    m805a((C0743b<String>) new C0607a<String>(jSONObject.getString("requestUrl")), new JSONObject(jSONObject.getString("postJsonObj")));
                } catch (JSONException e) {
                }
            }
            C0764b.m1248b(context);
        }
        if (f838g != null) {
            try {
                C0626g.m869b(context, f838g);
            } catch (IllegalArgumentException e2) {
                C0757f.m1210a("NetworkChecker receiver is already unregistered");
            } finally {
                f838g = null;
            }
        }
    }

    /* renamed from: a */
    private static void m805a(C0743b<String> bVar, JSONObject jSONObject) {
        C0740a.m1170a().mo8183a(C0748f.m1179a(bVar, jSONObject), new C0742a<String>() {
            /* renamed from: a */
            public void mo7491a(String str, Exception exc) {
                if (str != null) {
                    C0757f.m1210a(str);
                } else if (exc != null) {
                    C0757f.m1218b("Failed to send video event. ", (Throwable) exc);
                }
            }
        });
    }

    /* renamed from: a */
    private static void m801a(Context context, String str, JSONObject jSONObject) {
        C0607a aVar = new C0607a(str);
        if (C0626g.m868a(context)) {
            m809b(context);
            m805a((C0743b<String>) aVar, jSONObject);
            return;
        }
        m804a(context, (C0743b<String>) aVar, jSONObject);
    }

    /* renamed from: a */
    public static void m798a(Context context, String str) {
        m801a(context, f832a, m811c(str));
    }

    /* renamed from: a */
    public static void m799a(Context context, String str, int i, int i2) {
        m801a(context, f833b, m793a(str, i, i2));
        C0757f.m1210a("width: " + i + ", height: " + i2);
    }

    /* renamed from: a */
    public static void m803a(Context context, String str, boolean z, boolean z2, int i) {
        m801a(context, f834c, m796a(str, z, z2, (long) i));
        C0757f.m1210a("isCompletion: " + z + ", isSkipped: " + z2 + ", timeSpent: " + i);
    }

    /* renamed from: b */
    public static void m810b(Context context, String str) {
        m801a(context, f835d, m811c(str));
    }

    /* renamed from: c */
    public static void m812c(Context context, String str) {
        m801a(context, f837f, m811c(str));
    }

    /* renamed from: a */
    public static void m802a(Context context, String str, boolean z) {
        m801a(context, f837f, m795a(str, z));
        C0757f.m1210a("isFullScreen: " + z);
    }

    /* renamed from: a */
    public static void m800a(Context context, String str, C0762a aVar) {
        if (aVar.mo8205d()) {
            aVar.mo8203b();
            C0757f.m1210a("End card display time = " + aVar.mo8204c());
            m801a(context, f836e, m794a(str, aVar.mo8204c()));
        }
    }

    /* renamed from: a */
    public static <AD extends C0525a> boolean m806a(AD ad, boolean z, int i, boolean z2) {
        return !z && ((z2 && ad.f566e == -1) || (ad.f566e > -1 && C0764b.m1245a(i) > ad.f566e));
    }
}
