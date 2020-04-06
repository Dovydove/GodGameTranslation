package net.nend.android.internal.utilities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import net.nend.android.NendAdLogger;
import net.nend.android.NendAdLogger.LogLevel;
import net.nend.android.internal.utilities.C0740a.C0745d;

/* renamed from: net.nend.android.internal.utilities.e */
/* compiled from: NendHelper */
public final class C0755e {

    /* renamed from: a */
    static final /* synthetic */ boolean f1152a = (!C0755e.class.desiredAssertionStatus());

    /* renamed from: net.nend.android.internal.utilities.e$a */
    /* compiled from: NendHelper */
    public static class C0756a {
        /* renamed from: a */
        public static boolean m1208a(Context context, String str, boolean z) {
            Bundle a = m1205a(context);
            if (a != null) {
                return a.getBoolean(str, z);
            }
            return z;
        }

        /* renamed from: a */
        public static String m1206a(Context context, String str, String str2) {
            Bundle a = m1205a(context);
            if (a == null || a.getString(str) == null) {
                return str2;
            }
            return a.getString(str);
        }

        /* renamed from: a */
        public static boolean m1207a(Context context, String str) {
            Bundle a = m1205a(context);
            return a != null && a.containsKey(str);
        }

        /* renamed from: a */
        private static Bundle m1205a(Context context) {
            try {
                return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            } catch (NameNotFoundException e) {
                C0757f.m1215a(C0758g.ERR_UNEXPECTED, (Throwable) e);
                return null;
            }
        }
    }

    private C0755e() {
    }

    /* renamed from: a */
    public static void m1197a(Context context) {
        if (NendAdLogger.getLogLevel().getInt() == LogLevel.OFF.getInt()) {
            String str = "NendDebuggable";
            NendAdLogger.setLogLevel(C0756a.m1208a(context, "NendDebuggable", false) ? LogLevel.DEBUG : LogLevel.OFF);
            if (C0756a.m1207a(context, "NendDebuggable")) {
                Log.i("nend_SDK", "NendDebuggable is deprecated. Use NendAdLogger#setLogLevel(NendAdLogger.LogLevel) instead.");
            }
        }
    }

    /* renamed from: b */
    public static String m1201b(Context context) {
        if (f1152a || context != null) {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            String string = defaultSharedPreferences.getString("NENDUUID", "");
            if (!TextUtils.isEmpty(string)) {
                return string;
            }
            String a = m1195a(UUID.randomUUID().toString());
            Editor edit = defaultSharedPreferences.edit();
            edit.putString("NENDUUID", a);
            edit.commit();
            return a;
        }
        throw new AssertionError();
    }

    /* renamed from: c */
    public static String m1203c(Context context) {
        try {
            return (String) C0740a.m1170a().mo8182a(new C0745d(context)).get();
        } catch (InterruptedException e) {
            C0757f.m1211a("Failed to get the Advertising ID", (Throwable) e);
            return "";
        } catch (ExecutionException e2) {
            C0757f.m1211a("Failed to get the Advertising ID", (Throwable) e2);
            return "";
        }
    }

    /* renamed from: a */
    public static String m1195a(String str) {
        byte[] bArr = new byte[16];
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            bArr = instance.digest();
        } catch (NoSuchAlgorithmException e) {
            C0757f.m1218b(e.getMessage(), (Throwable) e);
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(Integer.toString((b & 255) + 256, 16).substring(1));
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static void m1198a(Context context, String str) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        if (m1199a(context, intent)) {
            intent.setFlags(ErrorDialogData.BINDER_CRASH);
            context.startActivity(intent);
        }
    }

    /* renamed from: a */
    private static boolean m1199a(Context context, Intent intent) {
        if (context.getPackageManager().queryIntentActivities(intent, 65536).size() > 0) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public static int m1194a(int i) {
        if (i > 99999) {
            return 99999;
        }
        if (i <= 30) {
            return 30;
        }
        return i;
    }

    /* renamed from: d */
    public static String m1204d(Context context) {
        try {
            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
            if (advertisingIdInfo != null && !advertisingIdInfo.isLimitAdTrackingEnabled()) {
                return advertisingIdInfo.getId();
            }
            C0757f.m1210a("LimitAdTrackingEnabled");
            return "";
        } catch (IOException e) {
            C0757f.m1211a("Failed to get the Advertising ID", (Throwable) e);
        } catch (GooglePlayServicesRepairableException e2) {
            C0757f.m1211a("Failed to get the Advertising ID", (Throwable) e2);
        } catch (GooglePlayServicesNotAvailableException e3) {
            C0757f.m1211a("Failed to get the Advertising ID", (Throwable) e3);
        }
    }

    /* renamed from: b */
    public static Bitmap m1200b(Context context, String str) {
        Bitmap c = m1202c(context, str);
        if (c == null) {
            return null;
        }
        float f = context.getResources().getDisplayMetrics().density;
        Matrix matrix = new Matrix();
        matrix.setScale(f / 2.0f, f / 2.0f);
        Bitmap createBitmap = Bitmap.createBitmap(c, 0, 0, c.getWidth(), c.getHeight(), matrix, true);
        if (c != createBitmap) {
            c.recycle();
        }
        return createBitmap;
    }

    /* renamed from: c */
    public static Bitmap m1202c(Context context, String str) {
        try {
            return BitmapFactory.decodeStream(context.getResources().getAssets().open(str));
        } catch (IOException e) {
            return null;
        }
    }

    /* renamed from: a */
    public static String m1196a(String str, HashMap<String, String> hashMap) {
        Builder buildUpon = Uri.parse(str).buildUpon();
        for (Entry entry : hashMap.entrySet()) {
            buildUpon.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
        }
        return buildUpon.build().toString();
    }
}
