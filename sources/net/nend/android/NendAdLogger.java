package net.nend.android;

import android.support.annotation.NonNull;
import android.util.Log;

public class NendAdLogger {

    /* renamed from: a */
    private static NendAdLogger f355a = null;

    /* renamed from: b */
    private static LogLevel f356b = LogLevel.OFF;
    public NendAdLogging logger = new C0479a();

    public enum LogLevel {
        DEBUG(3),
        INFO(4),
        WARN(5),
        ERROR(6),
        OFF(ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        

        /* renamed from: a */
        private final int f358a;

        private LogLevel(int i) {
            this.f358a = i;
        }

        public int getInt() {
            return this.f358a;
        }
    }

    /* renamed from: net.nend.android.NendAdLogger$a */
    private final class C0479a implements NendAdLogging {
        private C0479a() {
        }

        public void logMessage(@NonNull String str, @NonNull LogLevel logLevel) {
            if (Log.isLoggable("nend_SDK", logLevel.getInt())) {
                Log.println(logLevel.getInt(), "nend_SDK", str);
            }
        }
    }

    private NendAdLogger() {
    }

    public static synchronized NendAdLogger sharedInstance() {
        NendAdLogger nendAdLogger;
        synchronized (NendAdLogger.class) {
            if (f355a == null) {
                f355a = new NendAdLogger();
            }
            nendAdLogger = f355a;
        }
        return nendAdLogger;
    }

    public static LogLevel getLogLevel() {
        return f356b;
    }

    public static void setLogLevel(LogLevel logLevel) {
        f356b = logLevel;
    }
}
