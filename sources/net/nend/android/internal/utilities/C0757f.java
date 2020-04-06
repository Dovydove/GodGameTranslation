package net.nend.android.internal.utilities;

import android.util.Log;
import net.nend.android.NendAdLogger;
import net.nend.android.NendAdLogger.LogLevel;

/* renamed from: net.nend.android.internal.utilities.f */
/* compiled from: NendLog */
public final class C0757f {
    /* renamed from: a */
    private static boolean m1216a(LogLevel logLevel) {
        return logLevel.getInt() >= NendAdLogger.getLogLevel().getInt();
    }

    /* renamed from: a */
    private static void m1209a(int i, String str, Throwable th) {
        switch (i) {
            case 2:
            case 3:
                m1212a(str, th, LogLevel.DEBUG);
                return;
            case 4:
                m1212a(str, th, LogLevel.INFO);
                return;
            case 5:
                m1212a(str, th, LogLevel.WARN);
                return;
            case 6:
                m1212a(str, th, LogLevel.ERROR);
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private static void m1212a(String str, Throwable th, LogLevel logLevel) {
        if (m1216a(logLevel)) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[5];
            String str2 = stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + ":\n" + str;
            if (th != null) {
                str2 = str2 + 10 + Log.getStackTraceString(th);
            }
            if (NendAdLogger.sharedInstance().logger != null) {
                NendAdLogger.sharedInstance().logger.logMessage(str2, logLevel);
            }
        }
    }

    /* renamed from: a */
    public static void m1210a(String str) {
        m1209a(3, str, (Throwable) null);
    }

    /* renamed from: a */
    public static void m1215a(C0758g gVar, Throwable th) {
        m1209a(3, gVar.mo8198b(), th);
    }

    /* renamed from: b */
    public static void m1217b(String str) {
        m1209a(4, str, (Throwable) null);
    }

    /* renamed from: c */
    public static void m1220c(String str) {
        m1209a(5, str, (Throwable) null);
    }

    /* renamed from: a */
    public static void m1211a(String str, Throwable th) {
        m1209a(5, str, th);
    }

    /* renamed from: a */
    public static void m1213a(C0758g gVar) {
        m1209a(5, gVar.mo8198b(), (Throwable) null);
    }

    /* renamed from: b */
    public static void m1219b(C0758g gVar, Throwable th) {
        m1209a(5, gVar.mo8198b(), th);
    }

    /* renamed from: d */
    public static void m1222d(String str) {
        m1209a(6, str, (Throwable) null);
    }

    /* renamed from: b */
    public static void m1218b(String str, Throwable th) {
        m1209a(6, str, th);
    }

    /* renamed from: a */
    public static void m1214a(C0758g gVar, String str) {
        m1209a(6, gVar.mo8197a(str), (Throwable) null);
    }

    /* renamed from: c */
    public static void m1221c(C0758g gVar, Throwable th) {
        m1209a(6, gVar.mo8198b(), th);
    }
}
