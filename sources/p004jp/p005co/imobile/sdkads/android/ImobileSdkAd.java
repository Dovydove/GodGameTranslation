package p004jp.p005co.imobile.sdkads.android;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Point;
import android.view.ViewGroup;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: jp.co.imobile.sdkads.android.ImobileSdkAd */
public class ImobileSdkAd {

    /* renamed from: a */
    private static ImobileSdkAd f40a = new ImobileSdkAd();

    /* renamed from: m */
    private static /* synthetic */ int[] f41m;

    /* renamed from: n */
    private static /* synthetic */ int[] f42n;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public ConcurrentHashMap f43b = new ConcurrentHashMap();

    /* renamed from: c */
    private Boolean f44c = Boolean.valueOf(false);

    /* renamed from: d */
    private Boolean f45d = Boolean.valueOf(false);

    /* renamed from: e */
    private Boolean f46e = Boolean.valueOf(true);

    /* renamed from: f */
    private AdOrientation f47f = AdOrientation.AUTO;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public Context f48g = null;

    /* renamed from: h */
    private C0353r f49h;

    /* renamed from: i */
    private Timer f50i = null;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public TimerTask f51j = null;

    /* renamed from: k */
    private Boolean f52k = Boolean.valueOf(false);

    /* renamed from: l */
    private final BroadcastReceiver f53l = new C0350o(this);

    /* renamed from: jp.co.imobile.sdkads.android.ImobileSdkAd$AdOrientation */
    public enum AdOrientation {
        AUTO,
        PORTRAIT,
        LANDSCAPE
    }

    /* renamed from: jp.co.imobile.sdkads.android.ImobileSdkAd$AdShowType */
    public enum AdShowType {
        DIALOG,
        INLINE
    }

    /* renamed from: jp.co.imobile.sdkads.android.ImobileSdkAd$AdType */
    public enum AdType {
        NORMAL_AD,
        HOUSE_AD
    }

    private ImobileSdkAd() {
    }

    /* renamed from: a */
    static Context m32a() {
        return f40a.f48g;
    }

    /* renamed from: a */
    private void m34a(Activity activity, String str, String str2, String str3, AdShowType adShowType) {
        if (this.f48g == null) {
            this.f48g = activity.getApplicationContext();
            try {
                ApplicationInfo applicationInfo = activity.getApplicationContext().getPackageManager().getApplicationInfo(activity.getApplicationContext().getPackageName(), 128);
                if (applicationInfo.metaData != null) {
                    this.f44c = Boolean.valueOf(applicationInfo.metaData.getBoolean("i-mobile_Testing", C0358w.f182b.booleanValue()));
                    this.f45d = Boolean.valueOf(applicationInfo.metaData.getBoolean("i-mobile_DebugLogging", C0358w.f181a.booleanValue()));
                    this.f46e = Boolean.valueOf(applicationInfo.metaData.getBoolean("i-mobile_SendID", C0358w.f183c.booleanValue()));
                    String string = applicationInfo.metaData.getString("i-mobile_AdOrientation");
                    if (string != null) {
                        try {
                            this.f47f = AdOrientation.valueOf(string.toUpperCase(Locale.getDefault()));
                        } catch (RuntimeException e) {
                            C0359x.m124a("ImobileSdkAd parameter error.", "i-mobile_ShowLayout value Illegal, use value default(AUTO).");
                            this.f47f = C0358w.f184d;
                        }
                    } else {
                        this.f47f = C0358w.f184d;
                    }
                }
            } catch (NameNotFoundException e2) {
            }
            this.f49h = C0353r.m99a();
            this.f49h.mo7254a(activity);
        }
        C0361z zVar = (C0361z) this.f43b.get(str3);
        if (zVar == null) {
            switch (m44e()[adShowType.ordinal()]) {
                case 1:
                    zVar = new C0334ap();
                    break;
                case 2:
                    zVar = new C0332an();
                    break;
                default:
                    C0359x.m124a("ImobileSdkAd spot create error.", "adShowType not found.");
                    break;
            }
            if (zVar != null) {
                zVar.mo7271a(adShowType);
                activity.getApplicationContext();
                zVar.mo7270a(str, str2, str3);
                this.f43b.put(str3, zVar);
            }
        }
    }

    /* renamed from: a */
    private void m35a(Activity activity, String str, ImobileSdkAdListener imobileSdkAdListener, Point point, Boolean bool, ViewGroup viewGroup, ImobileIconParams imobileIconParams, Boolean bool2) {
        C0361z zVar = (C0361z) this.f43b.get(str);
        if (zVar == null) {
            C0359x.m124a("ImobileSdkAd start error.", "Spot is not registered.");
            if (imobileSdkAdListener != null) {
                imobileSdkAdListener.onAdCloseCompleted();
            }
        }
        Point point2 = point == null ? new Point(0, 0) : point;
        synchronized (this) {
            switch (m45f()[zVar.mo7269a().ordinal()]) {
                case 2:
                case 4:
                case 6:
                    switch (m44e()[zVar.mo7274b().ordinal()]) {
                        case 1:
                            if (imobileSdkAdListener != null) {
                                imobileSdkAdListener.onAdCloseCompleted();
                            }
                            C0359x.m124a("ImobileSdkAd start error.", "Spot is not registered.");
                            break;
                        case 2:
                            zVar.mo7275b(new C0352q(this, zVar, activity, imobileSdkAdListener, point2, bool, viewGroup, imobileIconParams, bool2));
                            break;
                    }
                case 3:
                    zVar.mo7205a(activity, imobileSdkAdListener, point2, bool, viewGroup, imobileIconParams, bool2);
                    break;
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m36a(Boolean bool) {
        for (Entry value : this.f43b.entrySet()) {
            ((C0361z) value.getValue()).mo7207l();
        }
        if (this.f50i != null) {
            this.f50i.cancel();
        }
        this.f51j = null;
        C0359x.m125a(null);
        if (bool.booleanValue() && this.f52k.booleanValue()) {
            this.f48g.unregisterReceiver(this.f53l);
            this.f52k = Boolean.valueOf(false);
        }
        C0353r.m99a().mo7258d();
    }

    /* renamed from: a */
    private void m37a(String str) {
        C0361z zVar = (C0361z) this.f43b.get(str);
        if (zVar == null) {
            C0359x.m124a("ImobileSdkAd start error.", "Spot is not registered.");
            return;
        }
        new StringBuilder("spot id : ").append(str);
        C0359x.m125a(null);
        zVar.mo7281h();
        C0353r.m99a();
        if (!C0353r.m106b().equals("") && this.f51j == null) {
            this.f50i = new Timer(true);
            this.f51j = new C0351p(this);
            this.f50i.schedule(this.f51j, 0, 5000);
            C0359x.m125a(null);
        }
        if (!this.f52k.booleanValue()) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            this.f48g.registerReceiver(this.f53l, intentFilter);
            this.f52k = Boolean.valueOf(true);
        }
    }

    public static void activityDestory() {
        for (Entry value : f40a.f43b.entrySet()) {
            ((C0361z) value.getValue()).mo7208m();
        }
    }

    /* renamed from: b */
    static Boolean m39b() {
        return f40a.f44c;
    }

    /* renamed from: c */
    static Boolean m42c() {
        return f40a.f45d;
    }

    /* renamed from: d */
    static AdOrientation m43d() {
        return f40a.f47f;
    }

    /* renamed from: e */
    private static /* synthetic */ int[] m44e() {
        int[] iArr = f41m;
        if (iArr == null) {
            iArr = new int[AdShowType.values().length];
            try {
                iArr[AdShowType.DIALOG.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[AdShowType.INLINE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            f41m = iArr;
        }
        return iArr;
    }

    /* renamed from: f */
    private static /* synthetic */ int[] m45f() {
        int[] iArr = f42n;
        if (iArr == null) {
            iArr = new int[C0331am.m57a().length];
            try {
                iArr[C0331am.ERROR.ordinal()] = 6;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[C0331am.LODING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[C0331am.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[C0331am.PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[C0331am.START.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[C0331am.STOP.ordinal()] = 5;
            } catch (NoSuchFieldError e6) {
            }
            f42n = iArr;
        }
        return iArr;
    }

    public static boolean isShowAd(String spotId) {
        C0361z zVar = (C0361z) f40a.f43b.get(spotId);
        if (zVar != null) {
            return zVar.mo7206k();
        }
        return false;
    }

    public static void registerSpot(Activity activity, String publisherId, String mediaId, String spotId) {
        f40a.m34a(activity, publisherId, mediaId, spotId, AdShowType.DIALOG);
    }

    public static void registerSpotFullScreen(Activity activity, String publisherId, String mediaId, String spotId) {
        f40a.m34a(activity, publisherId, mediaId, spotId, AdShowType.DIALOG);
    }

    public static void registerSpotInline(Activity activity, String publisherId, String mediaId, String spotId) {
        f40a.m34a(activity, publisherId, mediaId, spotId, AdShowType.INLINE);
    }

    public static void setImobileSdkAdListener(String spotId, ImobileSdkAdListener listener) {
        C0361z zVar = (C0361z) f40a.f43b.get(spotId);
        if (zVar != null) {
            zVar.mo7272a(listener);
        } else {
            C0359x.m124a("ImobileSdkAd start error.", "Spot is not registered.");
        }
    }

    public static void setShowAdIntervalTime(String str, int i) {
    }

    public static void setShowAdSkipCount(String str, int i) {
    }

    public static void showAd(Activity activity, String spotId) {
        f40a.m35a(activity, spotId, null, null, Boolean.valueOf(false), null, null, Boolean.valueOf(false));
    }

    public static void showAd(Activity activity, String spotId, int left, int top, boolean isDpiConvert) {
        if (isDpiConvert) {
            C0353r.m99a();
            left = C0353r.m94a(left);
            C0353r.m99a();
            top = C0353r.m94a(top);
        }
        Activity activity2 = activity;
        String str = spotId;
        f40a.m35a(activity2, str, null, new Point(left, top), Boolean.valueOf(false), null, new ImobileIconParams(), Boolean.valueOf(false));
    }

    public static void showAd(Activity activity, String spotId, int left, int top, boolean isDpiConvert, ImobileIconParams iconParams) {
        if (isDpiConvert) {
            C0353r.m99a();
            left = C0353r.m94a(left);
            C0353r.m99a();
            top = C0353r.m94a(top);
        }
        Activity activity2 = activity;
        String str = spotId;
        f40a.m35a(activity2, str, null, new Point(left, top), Boolean.valueOf(false), null, iconParams, Boolean.valueOf(false));
    }

    public static void showAd(Activity activity, String spotId, ViewGroup targetViewGroup) {
        f40a.m35a(activity, spotId, null, null, Boolean.valueOf(false), targetViewGroup, new ImobileIconParams(), Boolean.valueOf(false));
    }

    public static void showAd(Activity activity, String spotId, ViewGroup targetViewGroup, ImobileIconParams iconParams) {
        f40a.m35a(activity, spotId, null, null, Boolean.valueOf(false), targetViewGroup, iconParams, Boolean.valueOf(false));
    }

    public static void showAd(Activity activity, String spotId, ImobileSdkAdListener listener) {
        f40a.m35a(activity, spotId, listener, null, Boolean.valueOf(false), null, null, Boolean.valueOf(false));
    }

    public static void showAdForAdMobMediation(Activity activity, String spotId, ViewGroup targetViewGroup) {
        f40a.m35a(activity, spotId, null, null, Boolean.valueOf(false), targetViewGroup, new ImobileIconParams(), Boolean.valueOf(true));
    }

    public static void showAdForce(Activity activity, String spotId, ImobileSdkAdListener listener) {
        f40a.m35a(activity, spotId, listener, null, Boolean.valueOf(true), null, null, Boolean.valueOf(false));
    }

    public static void showAdforce(Activity activity, String spotId) {
        f40a.m35a(activity, spotId, null, null, Boolean.valueOf(true), null, null, Boolean.valueOf(false));
    }

    public static void start(String spotId) {
        f40a.m37a(spotId);
    }

    public static void startAll() {
        ImobileSdkAd imobileSdkAd = f40a;
        for (Entry entry : imobileSdkAd.f43b.entrySet()) {
            if (((C0361z) entry.getValue()).mo7269a() == C0331am.PAUSE) {
                ((C0361z) entry.getValue()).mo7273a(C0331am.START);
            }
            imobileSdkAd.m37a(((C0361z) entry.getValue()).f190c);
        }
        if (imobileSdkAd.f48g != null) {
            C0353r.m99a().mo7257c();
        }
    }

    public static void stop(String spotId) {
        ((C0361z) f40a.f43b.get(spotId)).mo7207l();
    }

    public static void stopAll() {
        f40a.m36a(Boolean.valueOf(true));
    }
}
