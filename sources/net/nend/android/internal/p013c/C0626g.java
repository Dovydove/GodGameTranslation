package net.nend.android.internal.p013c;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

/* renamed from: net.nend.android.internal.c.g */
/* compiled from: NetworkChecker */
public class C0626g {
    /* renamed from: a */
    public static boolean m868a(Context context) {
        NetworkInfo c = m871c(context);
        return c != null && c.isConnectedOrConnecting();
    }

    /* renamed from: b */
    public static boolean m870b(Context context) {
        NetworkInfo c = m871c(context);
        if (c == null || c.getType() != 1) {
            return false;
        }
        return true;
    }

    /* renamed from: c */
    private static NetworkInfo m871c(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager != null) {
            return connectivityManager.getActiveNetworkInfo();
        }
        return null;
    }

    /* renamed from: a */
    public static void m867a(Context context, @NonNull BroadcastReceiver broadcastReceiver) {
        context.registerReceiver(broadcastReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    /* renamed from: b */
    public static void m869b(Context context, @NonNull BroadcastReceiver broadcastReceiver) {
        context.unregisterReceiver(broadcastReceiver);
    }
}
