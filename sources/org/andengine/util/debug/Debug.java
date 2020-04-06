package org.andengine.util.debug;

import android.util.Log;
import org.andengine.util.Constants;

public class Debug {
    private static DebugLevel sDebugLevel = DebugLevel.VERBOSE;
    private static String sDebugUser = "";
    private static String sTag = Constants.DEBUGTAG;

    public enum DebugLevel {
        NONE,
        ERROR,
        WARNING,
        INFO,
        DEBUG,
        VERBOSE;
        
        public static DebugLevel ALL;

        static {
            ALL = VERBOSE;
        }

        public boolean isSameOrLessThan(DebugLevel pDebugLevel) {
            return compareTo(pDebugLevel) >= 0;
        }
    }

    public static String getTag() {
        return sTag;
    }

    public static void setTag(String pTag) {
        sTag = pTag;
    }

    public static DebugLevel getDebugLevel() {
        return sDebugLevel;
    }

    public static void setDebugLevel(DebugLevel pDebugLevel) {
        if (pDebugLevel == null) {
            throw new IllegalArgumentException("pDebugLevel must not be null!");
        }
        sDebugLevel = pDebugLevel;
    }

    public static void setDebugUser(String pDebugUser) {
        if (pDebugUser == null) {
            throw new IllegalArgumentException("pDebugUser must not be null!");
        }
        sDebugUser = pDebugUser;
    }

    public static void log(DebugLevel pDebugLevel, String pMessage) {
        switch (pDebugLevel) {
            case VERBOSE:
                m1280v(pMessage);
                return;
            case INFO:
                m1276i(pMessage);
                return;
            case DEBUG:
                m1267d(pMessage);
                return;
            case WARNING:
                m1284w(pMessage);
                return;
            case ERROR:
                m1271e(pMessage);
                return;
            default:
                return;
        }
    }

    public static void log(DebugLevel pDebugLevel, String pMessage, Throwable pThrowable) {
        switch (pDebugLevel) {
            case VERBOSE:
                m1283v(pMessage, pThrowable);
                return;
            case INFO:
                m1279i(pMessage, pThrowable);
                return;
            case DEBUG:
                m1270d(pMessage, pThrowable);
                return;
            case WARNING:
                m1287w(pMessage, pThrowable);
                return;
            case ERROR:
                m1274e(pMessage, pThrowable);
                return;
            default:
                return;
        }
    }

    public static void log(DebugLevel pDebugLevel, String pTag, String pMessage) {
        switch (pDebugLevel) {
            case VERBOSE:
                m1281v(pTag, pMessage);
                return;
            case INFO:
                m1277i(pTag, pMessage);
                return;
            case DEBUG:
                m1268d(pTag, pMessage);
                return;
            case WARNING:
                m1285w(pTag, pMessage);
                return;
            case ERROR:
                m1272e(pTag, pMessage);
                return;
            default:
                return;
        }
    }

    public static void log(DebugLevel pDebugLevel, String pTag, String pMessage, Throwable pThrowable) {
        switch (pDebugLevel) {
            case VERBOSE:
                m1282v(pTag, pMessage, pThrowable);
                return;
            case INFO:
                m1278i(pTag, pMessage, pThrowable);
                return;
            case DEBUG:
                m1269d(pTag, pMessage, pThrowable);
                return;
            case WARNING:
                m1286w(pTag, pMessage, pThrowable);
                return;
            case ERROR:
                m1273e(pTag, pMessage, pThrowable);
                return;
            default:
                return;
        }
    }

    /* renamed from: v */
    public static void m1280v(String pMessage) {
        m1282v(sTag, pMessage, null);
    }

    /* renamed from: v */
    public static void m1283v(String pMessage, Throwable pThrowable) {
        m1282v(sTag, pMessage, pThrowable);
    }

    /* renamed from: v */
    public static void m1281v(String pTag, String pMessage) {
        m1282v(pTag, pMessage, null);
    }

    /* renamed from: v */
    public static void m1282v(String pTag, String pMessage, Throwable pThrowable) {
        if (!sDebugLevel.isSameOrLessThan(DebugLevel.VERBOSE)) {
            return;
        }
        if (pThrowable == null) {
            Log.v(pTag, pMessage);
        } else {
            Log.v(pTag, pMessage, pThrowable);
        }
    }

    public static void vUser(String pMessage, String pDebugUser) {
        if (sDebugUser.equals(pDebugUser)) {
            m1280v(pMessage);
        }
    }

    public static void vUser(String pMessage, Throwable pThrowable, String pDebugUser) {
        if (sDebugUser.equals(pDebugUser)) {
            m1283v(pMessage, pThrowable);
        }
    }

    public static void vUser(String pTag, String pMessage, String pDebugUser) {
        if (sDebugUser.equals(pDebugUser)) {
            m1281v(pTag, pMessage);
        }
    }

    public static void vUser(String pTag, String pMessage, Throwable pThrowable, String pDebugUser) {
        if (sDebugUser.equals(pDebugUser)) {
            m1282v(pTag, pMessage, pThrowable);
        }
    }

    /* renamed from: d */
    public static void m1267d(String pMessage) {
        m1269d(sTag, pMessage, null);
    }

    /* renamed from: d */
    public static void m1270d(String pMessage, Throwable pThrowable) {
        m1269d(sTag, pMessage, pThrowable);
    }

    /* renamed from: d */
    public static void m1268d(String pTag, String pMessage) {
        m1269d(pTag, pMessage, null);
    }

    /* renamed from: d */
    public static void m1269d(String pTag, String pMessage, Throwable pThrowable) {
        if (!sDebugLevel.isSameOrLessThan(DebugLevel.DEBUG)) {
            return;
        }
        if (pThrowable == null) {
            Log.d(pTag, pMessage);
        } else {
            Log.d(pTag, pMessage, pThrowable);
        }
    }

    public static void dUser(String pMessage, String pDebugUser) {
        if (sDebugUser.equals(pDebugUser)) {
            m1267d(pMessage);
        }
    }

    public static void dUser(String pMessage, Throwable pThrowable, String pDebugUser) {
        if (sDebugUser.equals(pDebugUser)) {
            m1270d(pMessage, pThrowable);
        }
    }

    public static void dUser(String pTag, String pMessage, String pDebugUser) {
        if (sDebugUser.equals(pDebugUser)) {
            m1268d(pTag, pMessage);
        }
    }

    public static void dUser(String pTag, String pMessage, Throwable pThrowable, String pDebugUser) {
        if (sDebugUser.equals(pDebugUser)) {
            m1269d(pTag, pMessage, pThrowable);
        }
    }

    /* renamed from: i */
    public static void m1276i(String pMessage) {
        m1278i(sTag, pMessage, null);
    }

    /* renamed from: i */
    public static void m1279i(String pMessage, Throwable pThrowable) {
        m1278i(sTag, pMessage, pThrowable);
    }

    /* renamed from: i */
    public static void m1277i(String pTag, String pMessage) {
        m1278i(pTag, pMessage, null);
    }

    /* renamed from: i */
    public static void m1278i(String pTag, String pMessage, Throwable pThrowable) {
        if (!sDebugLevel.isSameOrLessThan(DebugLevel.INFO)) {
            return;
        }
        if (pThrowable == null) {
            Log.i(pTag, pMessage);
        } else {
            Log.i(pTag, pMessage, pThrowable);
        }
    }

    public static void iUser(String pMessage, String pDebugUser) {
        if (sDebugUser.equals(pDebugUser)) {
            m1276i(pMessage);
        }
    }

    public static void iUser(String pMessage, Throwable pThrowable, String pDebugUser) {
        if (sDebugUser.equals(pDebugUser)) {
            m1279i(pMessage, pThrowable);
        }
    }

    public static void iUser(String pTag, String pMessage, String pDebugUser) {
        if (sDebugUser.equals(pDebugUser)) {
            m1277i(pTag, pMessage);
        }
    }

    public static void iUser(String pTag, String pMessage, Throwable pThrowable, String pDebugUser) {
        if (sDebugUser.equals(pDebugUser)) {
            m1278i(pTag, pMessage, pThrowable);
        }
    }

    /* renamed from: w */
    public static void m1284w(String pMessage) {
        m1286w(sTag, pMessage, null);
    }

    /* renamed from: w */
    public static void m1288w(Throwable pThrowable) {
        m1287w("", pThrowable);
    }

    /* renamed from: w */
    public static void m1287w(String pMessage, Throwable pThrowable) {
        m1286w(sTag, pMessage, pThrowable);
    }

    /* renamed from: w */
    public static void m1285w(String pTag, String pMessage) {
        m1286w(pTag, pMessage, null);
    }

    /* renamed from: w */
    public static void m1286w(String pTag, String pMessage, Throwable pThrowable) {
        if (!sDebugLevel.isSameOrLessThan(DebugLevel.WARNING)) {
            return;
        }
        if (pThrowable == null) {
            Log.w(pTag, pMessage);
        } else {
            Log.w(pTag, pMessage, pThrowable);
        }
    }

    public static void wUser(String pMessage, String pDebugUser) {
        if (sDebugUser.equals(pDebugUser)) {
            m1284w(pMessage);
        }
    }

    public static void wUser(Throwable pThrowable, String pDebugUser) {
        if (sDebugUser.equals(pDebugUser)) {
            m1288w(pThrowable);
        }
    }

    public static void wUser(String pMessage, Throwable pThrowable, String pDebugUser) {
        if (sDebugUser.equals(pDebugUser)) {
            m1287w(pMessage, pThrowable);
        }
    }

    public static void wUser(String pTag, String pMessage, String pDebugUser) {
        if (sDebugUser.equals(pDebugUser)) {
            m1285w(pTag, pMessage);
        }
    }

    public static void wUser(String pTag, String pMessage, Throwable pThrowable, String pDebugUser) {
        if (sDebugUser.equals(pDebugUser)) {
            m1286w(pTag, pMessage, pThrowable);
        }
    }

    /* renamed from: e */
    public static void m1271e(String pMessage) {
        m1273e(sTag, pMessage, null);
    }

    /* renamed from: e */
    public static void m1275e(Throwable pThrowable) {
        m1274e(sTag, pThrowable);
    }

    /* renamed from: e */
    public static void m1274e(String pMessage, Throwable pThrowable) {
        m1273e(sTag, pMessage, pThrowable);
    }

    /* renamed from: e */
    public static void m1272e(String pTag, String pMessage) {
        m1273e(pTag, pMessage, null);
    }

    /* renamed from: e */
    public static void m1273e(String pTag, String pMessage, Throwable pThrowable) {
        if (!sDebugLevel.isSameOrLessThan(DebugLevel.ERROR)) {
            return;
        }
        if (pThrowable == null) {
            Log.e(pTag, pMessage);
        } else {
            Log.e(pTag, pMessage, pThrowable);
        }
    }

    public static void eUser(String pMessage, String pDebugUser) {
        if (sDebugUser.equals(pDebugUser)) {
            m1271e(pMessage);
        }
    }

    public static void eUser(Throwable pThrowable, String pDebugUser) {
        if (sDebugUser.equals(pDebugUser)) {
            m1275e(pThrowable);
        }
    }

    public static void eUser(String pMessage, Throwable pThrowable, String pDebugUser) {
        if (sDebugUser.equals(pDebugUser)) {
            m1274e(pMessage, pThrowable);
        }
    }

    public static void eUser(String pTag, String pMessage, String pDebugUser) {
        if (sDebugUser.equals(pDebugUser)) {
            m1272e(pTag, pMessage);
        }
    }

    public static void eUser(String pTag, String pMessage, Throwable pThrowable, String pDebugUser) {
        if (sDebugUser.equals(pDebugUser)) {
            m1273e(pTag, pMessage, pThrowable);
        }
    }
}
