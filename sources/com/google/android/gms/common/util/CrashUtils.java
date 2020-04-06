package com.google.android.gms.common.util;

import android.content.Context;
import android.os.DropBoxManager;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.annotation.concurrent.GuardedBy;

public final class CrashUtils {
    private static final String[] zzzc = {"android.", "com.android.", "dalvik.", "java.", "javax."};
    private static DropBoxManager zzzd = null;
    private static boolean zzze = false;
    private static boolean zzzf;
    private static boolean zzzg;
    private static int zzzh = -1;
    @GuardedBy("CrashUtils.class")
    private static int zzzi = 0;
    @GuardedBy("CrashUtils.class")
    private static int zzzj = 0;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ErrorDialogData {
        public static final int AVG_CRASH_FREQ = 2;
        public static final int BINDER_CRASH = 268435456;
        public static final int DYNAMITE_CRASH = 536870912;
        public static final int FORCED_SHUSHED_BY_WRAPPER = 4;
        public static final int NONE = 0;
        public static final int POPUP_FREQ = 1;
        public static final int SUPPRESSED = 1073741824;
    }

    public static boolean addDynamiteErrorToDropBox(Context context, Throwable th) {
        return addErrorToDropBoxInternal(context, th, ErrorDialogData.DYNAMITE_CRASH);
    }

    @Deprecated
    public static boolean addErrorToDropBox(Context context, Throwable th) {
        return addDynamiteErrorToDropBox(context, th);
    }

    public static boolean addErrorToDropBoxInternal(Context context, String str, String str2, int i) {
        return zza(context, str, str2, i, null);
    }

    public static boolean addErrorToDropBoxInternal(Context context, Throwable th, int i) {
        boolean z;
        try {
            Preconditions.checkNotNull(context);
            Preconditions.checkNotNull(th);
            if (!isPackageSide()) {
                return false;
            }
            if (!zzdb()) {
                th = zza(th);
                if (th == null) {
                    return false;
                }
            }
            return zza(context, Log.getStackTraceString(th), ProcessUtils.getMyProcessName(), i, th);
        } catch (Exception e) {
            try {
                z = zzdb();
            } catch (Exception e2) {
                Log.e("CrashUtils", "Error determining which process we're running in!", e2);
                z = false;
            }
            if (z) {
                throw e;
            }
            Log.e("CrashUtils", "Error adding exception to DropBox!", e);
            return false;
        }
    }

    private static boolean isPackageSide() {
        if (zzze) {
            return zzzf;
        }
        return false;
    }

    public static boolean isSystemClassPrefixInternal(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (String startsWith : zzzc) {
            if (str.startsWith(startsWith)) {
                return true;
            }
        }
        return false;
    }

    @VisibleForTesting
    public static synchronized void setTestVariables(DropBoxManager dropBoxManager, boolean z, boolean z2, int i) {
        synchronized (CrashUtils.class) {
            zzze = true;
            zzzd = dropBoxManager;
            zzzg = z;
            zzzf = z2;
            zzzh = i;
            zzzi = 0;
            zzzj = 0;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0194, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x0194 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:42:0x0144] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0197 A[SYNTHETIC, Splitter:B:77:0x0197] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:59:0x0170=Splitter:B:59:0x0170, B:79:0x019a=Splitter:B:79:0x019a} */
    @com.google.android.gms.common.util.VisibleForTesting
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized java.lang.String zza(android.content.Context r8, java.lang.String r9, java.lang.String r10, int r11) {
        /*
            r3 = 0
            java.lang.Class<com.google.android.gms.common.util.CrashUtils> r4 = com.google.android.gms.common.util.CrashUtils.class
            monitor-enter(r4)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0180 }
            r0 = 1024(0x400, float:1.435E-42)
            r5.<init>(r0)     // Catch:{ all -> 0x0180 }
            java.lang.String r0 = "Process: "
            java.lang.StringBuilder r0 = r5.append(r0)     // Catch:{ all -> 0x0180 }
            java.lang.String r1 = com.google.android.gms.common.util.Strings.nullToEmpty(r10)     // Catch:{ all -> 0x0180 }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x0180 }
            java.lang.String r1 = "\n"
            r0.append(r1)     // Catch:{ all -> 0x0180 }
            java.lang.String r0 = "Package: com.google.android.gms"
            r5.append(r0)     // Catch:{ all -> 0x0180 }
            r2 = 12451009(0xbdfcc1, float:1.744758E-38)
            java.lang.String r0 = "12.4.51 (020308-{{cl}})"
            boolean r1 = zzdb()     // Catch:{ all -> 0x0180 }
            if (r1 == 0) goto L_0x0043
            com.google.android.gms.common.wrappers.PackageManagerWrapper r1 = com.google.android.gms.common.wrappers.Wrappers.packageManager(r8)     // Catch:{ Exception -> 0x0176 }
            java.lang.String r6 = r8.getPackageName()     // Catch:{ Exception -> 0x0176 }
            r7 = 0
            android.content.pm.PackageInfo r1 = r1.getPackageInfo(r6, r7)     // Catch:{ Exception -> 0x0176 }
            int r2 = r1.versionCode     // Catch:{ Exception -> 0x0176 }
            java.lang.String r6 = r1.versionName     // Catch:{ Exception -> 0x0176 }
            if (r6 == 0) goto L_0x0043
            java.lang.String r0 = r1.versionName     // Catch:{ Exception -> 0x0176 }
        L_0x0043:
            java.lang.String r1 = " v"
            java.lang.StringBuilder r1 = r5.append(r1)     // Catch:{ all -> 0x0180 }
            r1.append(r2)     // Catch:{ all -> 0x0180 }
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch:{ all -> 0x0180 }
            if (r1 != 0) goto L_0x008d
            java.lang.String r1 = "("
            boolean r1 = r0.contains(r1)     // Catch:{ all -> 0x0180 }
            if (r1 == 0) goto L_0x007e
            java.lang.String r1 = ")"
            boolean r1 = r0.contains(r1)     // Catch:{ all -> 0x0180 }
            if (r1 != 0) goto L_0x007e
            java.lang.String r1 = "-"
            boolean r1 = r0.endsWith(r1)     // Catch:{ all -> 0x0180 }
            if (r1 == 0) goto L_0x0074
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0180 }
            java.lang.String r1 = "111111111"
            java.lang.String r0 = r0.concat(r1)     // Catch:{ all -> 0x0180 }
        L_0x0074:
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0180 }
            java.lang.String r1 = ")"
            java.lang.String r0 = r0.concat(r1)     // Catch:{ all -> 0x0180 }
        L_0x007e:
            java.lang.String r1 = " ("
            java.lang.StringBuilder r1 = r5.append(r1)     // Catch:{ all -> 0x0180 }
            java.lang.StringBuilder r0 = r1.append(r0)     // Catch:{ all -> 0x0180 }
            java.lang.String r1 = ")"
            r0.append(r1)     // Catch:{ all -> 0x0180 }
        L_0x008d:
            java.lang.String r0 = "\n"
            r5.append(r0)     // Catch:{ all -> 0x0180 }
            java.lang.String r0 = "Build: "
            java.lang.StringBuilder r0 = r5.append(r0)     // Catch:{ all -> 0x0180 }
            java.lang.String r1 = android.os.Build.FINGERPRINT     // Catch:{ all -> 0x0180 }
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch:{ all -> 0x0180 }
            java.lang.String r1 = "\n"
            r0.append(r1)     // Catch:{ all -> 0x0180 }
            boolean r0 = android.os.Debug.isDebuggerConnected()     // Catch:{ all -> 0x0180 }
            if (r0 == 0) goto L_0x00ae
            java.lang.String r0 = "Debugger: Connected\n"
            r5.append(r0)     // Catch:{ all -> 0x0180 }
        L_0x00ae:
            if (r11 == 0) goto L_0x00bf
            java.lang.String r0 = "DD-EDD: "
            java.lang.StringBuilder r0 = r5.append(r0)     // Catch:{ all -> 0x0180 }
            java.lang.StringBuilder r0 = r0.append(r11)     // Catch:{ all -> 0x0180 }
            java.lang.String r1 = "\n"
            r0.append(r1)     // Catch:{ all -> 0x0180 }
        L_0x00bf:
            java.lang.String r0 = "\n"
            r5.append(r0)     // Catch:{ all -> 0x0180 }
            boolean r0 = android.text.TextUtils.isEmpty(r9)     // Catch:{ all -> 0x0180 }
            if (r0 != 0) goto L_0x00cd
            r5.append(r9)     // Catch:{ all -> 0x0180 }
        L_0x00cd:
            boolean r0 = zzdb()     // Catch:{ all -> 0x0180 }
            if (r0 == 0) goto L_0x01a9
            java.lang.String r0 = "logcat_for_system_app_crash"
            int r1 = zzzh     // Catch:{ all -> 0x0180 }
            if (r1 < 0) goto L_0x0183
            int r0 = zzzh     // Catch:{ all -> 0x0180 }
        L_0x00db:
            if (r0 <= 0) goto L_0x0170
            java.lang.String r1 = "\n"
            r5.append(r1)     // Catch:{ all -> 0x0180 }
            r2 = 0
            java.lang.ProcessBuilder r1 = new java.lang.ProcessBuilder     // Catch:{ IOException -> 0x01a2, all -> 0x0194 }
            r3 = 13
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ IOException -> 0x01a2, all -> 0x0194 }
            r6 = 0
            java.lang.String r7 = "/system/bin/logcat"
            r3[r6] = r7     // Catch:{ IOException -> 0x01a2, all -> 0x0194 }
            r6 = 1
            java.lang.String r7 = "-v"
            r3[r6] = r7     // Catch:{ IOException -> 0x01a2, all -> 0x0194 }
            r6 = 2
            java.lang.String r7 = "time"
            r3[r6] = r7     // Catch:{ IOException -> 0x01a2, all -> 0x0194 }
            r6 = 3
            java.lang.String r7 = "-b"
            r3[r6] = r7     // Catch:{ IOException -> 0x01a2, all -> 0x0194 }
            r6 = 4
            java.lang.String r7 = "events"
            r3[r6] = r7     // Catch:{ IOException -> 0x01a2, all -> 0x0194 }
            r6 = 5
            java.lang.String r7 = "-b"
            r3[r6] = r7     // Catch:{ IOException -> 0x01a2, all -> 0x0194 }
            r6 = 6
            java.lang.String r7 = "system"
            r3[r6] = r7     // Catch:{ IOException -> 0x01a2, all -> 0x0194 }
            r6 = 7
            java.lang.String r7 = "-b"
            r3[r6] = r7     // Catch:{ IOException -> 0x01a2, all -> 0x0194 }
            r6 = 8
            java.lang.String r7 = "main"
            r3[r6] = r7     // Catch:{ IOException -> 0x01a2, all -> 0x0194 }
            r6 = 9
            java.lang.String r7 = "-b"
            r3[r6] = r7     // Catch:{ IOException -> 0x01a2, all -> 0x0194 }
            r6 = 10
            java.lang.String r7 = "crash"
            r3[r6] = r7     // Catch:{ IOException -> 0x01a2, all -> 0x0194 }
            r6 = 11
            java.lang.String r7 = "-t"
            r3[r6] = r7     // Catch:{ IOException -> 0x01a2, all -> 0x0194 }
            r6 = 12
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ IOException -> 0x01a2, all -> 0x0194 }
            r3[r6] = r0     // Catch:{ IOException -> 0x01a2, all -> 0x0194 }
            r1.<init>(r3)     // Catch:{ IOException -> 0x01a2, all -> 0x0194 }
            r0 = 1
            java.lang.ProcessBuilder r0 = r1.redirectErrorStream(r0)     // Catch:{ IOException -> 0x01a2, all -> 0x0194 }
            java.lang.Process r0 = r0.start()     // Catch:{ IOException -> 0x01a2, all -> 0x0194 }
            java.io.OutputStream r1 = r0.getOutputStream()     // Catch:{ IOException -> 0x01a7, all -> 0x0194 }
            r1.close()     // Catch:{ IOException -> 0x01a7, all -> 0x0194 }
        L_0x0144:
            java.io.InputStream r1 = r0.getErrorStream()     // Catch:{ IOException -> 0x01a5, all -> 0x0194 }
            r1.close()     // Catch:{ IOException -> 0x01a5, all -> 0x0194 }
        L_0x014b:
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x01a2, all -> 0x0194 }
            java.io.InputStream r0 = r0.getInputStream()     // Catch:{ IOException -> 0x01a2, all -> 0x0194 }
            r1.<init>(r0)     // Catch:{ IOException -> 0x01a2, all -> 0x0194 }
            r0 = 8192(0x2000, float:1.14794E-41)
            char[] r0 = new char[r0]     // Catch:{ IOException -> 0x0163 }
        L_0x0158:
            int r2 = r1.read(r0)     // Catch:{ IOException -> 0x0163 }
            if (r2 <= 0) goto L_0x018e
            r3 = 0
            r5.append(r0, r3, r2)     // Catch:{ IOException -> 0x0163 }
            goto L_0x0158
        L_0x0163:
            r0 = move-exception
        L_0x0164:
            java.lang.String r2 = "CrashUtils"
            java.lang.String r3 = "Error running logcat"
            android.util.Log.e(r2, r3, r0)     // Catch:{ all -> 0x019f }
            if (r1 == 0) goto L_0x0170
            r1.close()     // Catch:{ IOException -> 0x019b }
        L_0x0170:
            java.lang.String r0 = r5.toString()     // Catch:{ all -> 0x0180 }
            monitor-exit(r4)
            return r0
        L_0x0176:
            r1 = move-exception
            java.lang.String r6 = "CrashUtils"
            java.lang.String r7 = "Error while trying to get the package information! Using static version."
            android.util.Log.w(r6, r7, r1)     // Catch:{ all -> 0x0180 }
            goto L_0x0043
        L_0x0180:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        L_0x0183:
            android.content.ContentResolver r1 = r8.getContentResolver()     // Catch:{ all -> 0x0180 }
            r2 = 0
            int r0 = android.provider.Settings.Secure.getInt(r1, r0, r2)     // Catch:{ all -> 0x0180 }
            goto L_0x00db
        L_0x018e:
            r1.close()     // Catch:{ IOException -> 0x0192 }
            goto L_0x0170
        L_0x0192:
            r0 = move-exception
            goto L_0x0170
        L_0x0194:
            r0 = move-exception
        L_0x0195:
            if (r2 == 0) goto L_0x019a
            r2.close()     // Catch:{ IOException -> 0x019d }
        L_0x019a:
            throw r0     // Catch:{ all -> 0x0180 }
        L_0x019b:
            r0 = move-exception
            goto L_0x0170
        L_0x019d:
            r1 = move-exception
            goto L_0x019a
        L_0x019f:
            r0 = move-exception
            r2 = r1
            goto L_0x0195
        L_0x01a2:
            r0 = move-exception
            r1 = r2
            goto L_0x0164
        L_0x01a5:
            r1 = move-exception
            goto L_0x014b
        L_0x01a7:
            r1 = move-exception
            goto L_0x0144
        L_0x01a9:
            r0 = r3
            goto L_0x00db
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.util.CrashUtils.zza(android.content.Context, java.lang.String, java.lang.String, int):java.lang.String");
    }

    @VisibleForTesting
    private static synchronized Throwable zza(Throwable th) {
        Throwable th2;
        synchronized (CrashUtils.class) {
            LinkedList linkedList = new LinkedList();
            while (th != null) {
                linkedList.push(th);
                th = th.getCause();
            }
            Throwable th3 = null;
            boolean z = false;
            while (!linkedList.isEmpty()) {
                Throwable th4 = (Throwable) linkedList.pop();
                StackTraceElement[] stackTrace = th4.getStackTrace();
                ArrayList arrayList = new ArrayList();
                arrayList.add(new StackTraceElement(th4.getClass().getName(), "<filtered>", "<filtered>", 1));
                boolean z2 = z;
                for (StackTraceElement stackTraceElement : stackTrace) {
                    String className = stackTraceElement.getClassName();
                    String fileName = stackTraceElement.getFileName();
                    boolean z3 = !TextUtils.isEmpty(fileName) && fileName.startsWith(":com.google.android.gms");
                    z2 |= z3;
                    if (!z3 && !isSystemClassPrefixInternal(className)) {
                        stackTraceElement = new StackTraceElement("<filtered>", "<filtered>", "<filtered>", 1);
                    }
                    arrayList.add(stackTraceElement);
                }
                th3 = th3 == null ? new Throwable("<filtered>") : new Throwable("<filtered>", th3);
                th3.setStackTrace((StackTraceElement[]) arrayList.toArray(new StackTraceElement[0]));
                z = z2;
            }
            th2 = !z ? null : th3;
        }
        return th2;
    }

    private static synchronized boolean zza(Context context, String str, String str2, int i, Throwable th) {
        boolean z;
        synchronized (CrashUtils.class) {
            Preconditions.checkNotNull(context);
            if (!isPackageSide() || Strings.isEmptyOrWhitespace(str)) {
                z = false;
            } else {
                int hashCode = str.hashCode();
                int hashCode2 = th == null ? zzzj : th.hashCode();
                if (zzzi == hashCode && zzzj == hashCode2) {
                    z = false;
                } else {
                    zzzi = hashCode;
                    zzzj = hashCode2;
                    DropBoxManager dropBoxManager = zzzd != null ? zzzd : (DropBoxManager) context.getSystemService("dropbox");
                    if (dropBoxManager == null || !dropBoxManager.isTagEnabled("system_app_crash")) {
                        z = false;
                    } else {
                        dropBoxManager.addText("system_app_crash", zza(context, str, str2, i));
                        z = true;
                    }
                }
            }
        }
        return z;
    }

    private static boolean zzdb() {
        if (zzze) {
            return zzzg;
        }
        return false;
    }
}
