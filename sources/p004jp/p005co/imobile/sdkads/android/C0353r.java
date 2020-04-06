package p004jp.p005co.imobile.sdkads.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Point;
import android.graphics.Rect;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.p000v4.app.NotificationCompat;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import org.andengine.util.level.constants.LevelConstants;
import org.json.JSONException;
import org.json.JSONObject;
import p004jp.p005co.imobile.sdkads.android.ImobileSdkAd.AdOrientation;

/* renamed from: jp.co.imobile.sdkads.android.r */
final class C0353r implements LocationListener {

    /* renamed from: c */
    private static C0353r f154c = new C0353r();

    /* renamed from: u */
    private static /* synthetic */ int[] f155u;

    /* renamed from: a */
    LocationManager f156a = null;

    /* renamed from: b */
    List f157b;

    /* renamed from: d */
    private String f158d = "android";

    /* renamed from: e */
    private String f159e = "";

    /* renamed from: f */
    private String f160f = "";

    /* renamed from: g */
    private String f161g = "";

    /* renamed from: h */
    private String f162h = "";

    /* renamed from: i */
    private String f163i = "wifi";

    /* renamed from: j */
    private String f164j = "";

    /* renamed from: k */
    private String f165k = "";

    /* renamed from: l */
    private String f166l = "";
    /* access modifiers changed from: private */

    /* renamed from: m */
    public String f167m = "";

    /* renamed from: n */
    private Boolean f168n = Boolean.valueOf(true);

    /* renamed from: o */
    private float f169o = 0.0f;

    /* renamed from: p */
    private int f170p = 0;

    /* renamed from: q */
    private int f171q = 0;

    /* renamed from: r */
    private double f172r = 0.0d;

    /* renamed from: s */
    private double f173s = 0.0d;

    /* renamed from: t */
    private boolean f174t = false;

    private C0353r() {
    }

    /* renamed from: a */
    static int m94a(int i) {
        return (int) ((((float) i) * f154c.f169o) + 0.5f);
    }

    /* renamed from: a */
    static int m95a(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getInt(str);
        } catch (JSONException e) {
            new StringBuilder("getJsonIntValueFromKey JSON parce error. value:").append(str);
            C0359x.m126b("SDK API Message", "PARSE INT");
            throw new C0360y(FailNotificationReason.RESPONSE);
        }
    }

    /* renamed from: a */
    static String m96a(Activity activity, AdOrientation adOrientation, Rect rect) {
        String str;
        JSONObject e = m110e();
        try {
            JSONObject jSONObject = e.getJSONObject("result");
            switch (m111g()[adOrientation.ordinal()]) {
                case 2:
                    str = "p";
                    break;
                case 3:
                    str = "l";
                    break;
                default:
                    str = m97a((Context) activity);
                    break;
            }
            jSONObject.put("rotation", str);
            jSONObject.put("top", rect.top);
            jSONObject.put("left", rect.left);
            jSONObject.put(LevelConstants.TAG_LEVEL_ATTRIBUTE_WIDTH, rect.right);
            jSONObject.put(LevelConstants.TAG_LEVEL_ATTRIBUTE_HEIGHT, rect.bottom);
            e.put(NotificationCompat.CATEGORY_STATUS, "succeed");
            return e.toString();
        } catch (JSONException e2) {
            new StringBuilder("getDisplayOrientationInfoToJSonString:").append(e2.getMessage());
            C0359x.m126b("SDK API Message", "DM");
            throw new C0360y(FailNotificationReason.RESPONSE);
        }
    }

    /* renamed from: a */
    private static String m97a(Context context) {
        return context.getResources().getConfiguration().orientation == 1 ? "p" : context.getResources().getConfiguration().orientation == 2 ? "l" : "";
    }

    /* renamed from: a */
    static C0353r m99a() {
        return f154c;
    }

    /* renamed from: a */
    static final void m100a(Activity activity, String str) {
        if (m103a(str)) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            if (activity == null) {
                intent.setFlags(ErrorDialogData.BINDER_CRASH);
                ImobileSdkAd.m32a().startActivity(intent);
                return;
            }
            activity.startActivity(intent);
        }
    }

    /* renamed from: a */
    static final void m101a(String str, String str2, C0340e eVar) {
        Executors.newCachedThreadPool().submit(new C0356u(Executors.newCachedThreadPool().submit(new C0355t(str)), str2, eVar));
    }

    /* renamed from: a */
    static final boolean m103a(String str) {
        return ImobileSdkAd.m32a().getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(new StringBuilder(String.valueOf(str)).append("://").toString())), 65536).size() > 0;
    }

    /* renamed from: a */
    static final boolean m104a(JSONObject jSONObject) {
        try {
            return C0346k.m86a(jSONObject, null).mo7249a().booleanValue();
        } catch (C0360y e) {
            C0359x.m125a(e);
            return false;
        }
    }

    /* renamed from: b */
    static final int m105b(Activity activity) {
        boolean z;
        int i;
        int i2 = 9;
        int i3 = 8;
        int i4 = 1;
        if (m97a((Context) activity).equals("p")) {
            z = true;
            i = 1;
        } else if (!m97a((Context) activity).equals("l")) {
            return 0;
        } else {
            z = false;
            i = 0;
        }
        int rotation = ((WindowManager) ImobileSdkAd.m32a().getSystemService("window")).getDefaultDisplay().getRotation();
        if (VERSION.SDK_INT <= 8) {
            return i;
        }
        switch (rotation) {
            case 0:
                if (!z) {
                    i4 = 0;
                }
                return i4;
            case 1:
                return z ? 9 : 0;
            case 2:
                if (!z) {
                    i2 = 8;
                }
                return i2;
            case 3:
                if (z) {
                    i3 = 1;
                }
                return i3;
            default:
                return 0;
        }
    }

    /* renamed from: b */
    static String m106b() {
        if (ImobileSdkAd.m32a().checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == -1) {
            return "mobile";
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) ImobileSdkAd.m32a().getSystemService("connectivity");
        if (connectivityManager == null) {
            return "mobile";
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) ? "" : activeNetworkInfo.getTypeName().toLowerCase(Locale.getDefault());
    }

    /* renamed from: b */
    static String m107b(JSONObject jSONObject, String str) {
        try {
            return jSONObject.getString(str);
        } catch (JSONException e) {
            new StringBuilder("getJsonStringValueFromKey JSON parce error. value:").append(str);
            C0359x.m126b("SDK API Message", "PARSE STRING");
            throw new C0360y(FailNotificationReason.RESPONSE);
        }
    }

    /* renamed from: b */
    static final void m108b(Activity activity, String str) {
        try {
            activity.startActivity(ImobileSdkAd.m32a().getPackageManager().getLaunchIntentForPackage(str));
        } catch (Exception e) {
            new StringBuilder("Applicaiotn Start faild. applicaiotnID:").append(str).append(" message:").append(e.getMessage());
            C0359x.m126b("SDK API Message", "Application not installed.");
        }
    }

    /* renamed from: c */
    static JSONObject m109c(String str) {
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            new StringBuilder("getJsonObjectFromJsonString JSON parce error. value:").append(str);
            C0359x.m126b("SDK API Message", "PARSE");
            throw new C0360y(FailNotificationReason.RESPONSE);
        }
    }

    /* renamed from: e */
    static JSONObject m110e() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(NotificationCompat.CATEGORY_STATUS, "faild");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("code", "");
            jSONObject2.put("message", "");
            jSONObject.put("error", jSONObject2);
            jSONObject.put("result", new JSONObject());
            return jSONObject;
        } catch (JSONException e) {
            new StringBuilder("getJsonBaseForHtml JSON parce error. value:").append(e.getMessage());
            C0359x.m126b("SDK API Message", "BASE PARSE");
            throw new C0360y(FailNotificationReason.RESPONSE);
        }
    }

    /* renamed from: g */
    private static /* synthetic */ int[] m111g() {
        int[] iArr = f155u;
        if (iArr == null) {
            iArr = new int[AdOrientation.values().length];
            try {
                iArr[AdOrientation.AUTO.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[AdOrientation.LANDSCAPE.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[AdOrientation.PORTRAIT.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            f155u = iArr;
        }
        return iArr;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo7254a(Activity activity) {
        if (!this.f174t) {
            Context a = ImobileSdkAd.m32a();
            this.f159e = a.getPackageName();
            this.f160f = "V1.3";
            this.f161g = Locale.getDefault().getLanguage();
            this.f162h = VERSION.RELEASE;
            this.f163i = m106b();
            this.f164j = Build.BRAND;
            this.f165k = Build.DEVICE;
            new Thread(new C0354s(this, a)).start();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) activity.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            this.f169o = displayMetrics.density;
            Point point = new Point(0, 0);
            DisplayMetrics displayMetrics2 = new DisplayMetrics();
            ((WindowManager) activity.getApplicationContext().getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics2);
            if (m97a((Context) activity).equals("l")) {
                point.x = displayMetrics2.heightPixels;
                point.y = displayMetrics2.widthPixels;
            } else {
                point.x = displayMetrics2.widthPixels;
                point.y = displayMetrics2.heightPixels;
            }
            this.f170p = point.x;
            this.f171q = point.y;
            this.f168n = Boolean.valueOf((activity.getWindow().getAttributes().flags & 1024) == 0);
            mo7257c();
            this.f174t = true;
            C0359x.m126b("SDK API Message", "Sdk api init complete.");
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo7255a(Builder builder) {
        builder.appendQueryParameter("spt", this.f158d);
        builder.appendQueryParameter("appid", this.f159e);
        builder.appendQueryParameter("sdkv", this.f160f);
        builder.appendQueryParameter("lang", this.f161g);
        builder.appendQueryParameter("os", this.f162h);
        builder.appendQueryParameter("nk", this.f163i);
        builder.appendQueryParameter("dvbrand", this.f164j);
        builder.appendQueryParameter("dvname", this.f165k);
        if (this.f167m != null && !this.f167m.equals("")) {
            builder.appendQueryParameter("gaid", this.f167m);
        }
        builder.appendQueryParameter("dpr", Float.toString(this.f169o));
        builder.appendQueryParameter("dpw", Integer.toString(this.f170p));
        builder.appendQueryParameter("dph", Integer.toString(this.f171q));
        if (this.f172r > 0.0d && this.f173s > 0.0d) {
            builder.appendQueryParameter("lat", Double.toString(this.f172r));
            builder.appendQueryParameter("lng", Double.toString(this.f173s));
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public final boolean mo7256b(String str) {
        if (this.f157b == null) {
            this.f157b = new ArrayList();
            for (ApplicationInfo applicationInfo : ImobileSdkAd.m32a().getPackageManager().getInstalledApplications(0)) {
                this.f157b.add(applicationInfo.packageName);
            }
        }
        return this.f157b.indexOf(str) >= 0;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: c */
    public final void mo7257c() {
        String str;
        String str2;
        if (this.f156a == null) {
            String str3 = "";
            this.f156a = (LocationManager) ImobileSdkAd.m32a().getSystemService("location");
            if (ImobileSdkAd.m32a().checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == -1) {
                C0359x.m125a(null);
                str = str3;
            } else {
                str = this.f156a.isProviderEnabled("gps") ? "gps" : str3;
            }
            if (ImobileSdkAd.m32a().checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == -1) {
                C0359x.m125a(null);
                str2 = str;
            } else {
                str2 = (!this.f156a.isProviderEnabled("network") || !str.equals("")) ? str : "network";
            }
            if (str2.equals("")) {
                C0359x.m125a(null);
            } else {
                this.f156a.requestLocationUpdates(str2, 0, 0.0f, this);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: d */
    public final void mo7258d() {
        if (this.f156a != null) {
            this.f156a.removeUpdates(this);
            this.f172r = 0.0d;
            this.f173s = 0.0d;
            this.f156a = null;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: f */
    public final String mo7259f() {
        JSONObject e = m110e();
        try {
            JSONObject jSONObject = e.getJSONObject("result");
            jSONObject.put("spt", this.f158d);
            jSONObject.put("appid", this.f159e);
            jSONObject.put("sdkv", this.f160f);
            jSONObject.put("lang", this.f161g);
            jSONObject.put("os", this.f162h);
            jSONObject.put("nk", this.f163i);
            jSONObject.put("dvbrand", this.f164j);
            jSONObject.put("dvname", this.f165k);
            jSONObject.put("rotation", m97a(ImobileSdkAd.m32a()));
            jSONObject.put("deviceid", this.f166l);
            jSONObject.put("advertisingid", this.f167m);
            jSONObject.put("statusbar", this.f168n);
            jSONObject.put("dpr", Float.toString(this.f169o));
            jSONObject.put("dpw", Integer.toString(this.f170p));
            jSONObject.put("dph", Integer.toString(this.f171q));
            jSONObject.put("lat", Double.toString(this.f172r));
            jSONObject.put("lng", Double.toString(this.f173s));
            e.put(NotificationCompat.CATEGORY_STATUS, "succeed");
            return e.toString();
        } catch (JSONException e2) {
            new StringBuilder("getDeviceInfoJsonForHtml JSON parce error. value:").append(e2.getMessage());
            C0359x.m126b("SDK API Message", "DEVICE PARSE");
            throw new C0360y(FailNotificationReason.RESPONSE);
        }
    }

    public final void onLocationChanged(Location location) {
        this.f172r = location.getLatitude();
        this.f173s = location.getLongitude();
        new StringBuilder("Location get lat:").append(location.getLatitude()).append(" lng:").append(location.getLongitude());
        C0359x.m125a(null);
    }

    public final void onProviderDisabled(String str) {
    }

    public final void onProviderEnabled(String str) {
    }

    public final void onStatusChanged(String str, int i, Bundle bundle) {
    }
}
