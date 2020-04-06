package com.google.android.gms.common.util;

import android.os.Binder;
import android.os.Process;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import javax.annotation.Nullable;

public class ProcessUtils {
    private static String zzaai = null;
    private static int zzaaj = 0;

    public static class SystemGroupsNotAvailableException extends Exception {
        SystemGroupsNotAvailableException(String str) {
            super(str);
        }

        SystemGroupsNotAvailableException(String str, Throwable th) {
            super(str, th);
        }
    }

    private ProcessUtils() {
    }

    @Nullable
    public static String getCallingProcessName() {
        int callingPid = Binder.getCallingPid();
        return callingPid == zzde() ? getMyProcessName() : zzl(callingPid);
    }

    @Nullable
    public static String getMyProcessName() {
        if (zzaai == null) {
            zzaai = zzl(zzde());
        }
        return zzaai;
    }

    public static boolean hasSystemGroups() throws SystemGroupsNotAvailableException {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = zzm("/proc/" + zzde() + "/status");
            boolean zzk = zzk(bufferedReader);
            IOUtils.closeQuietly((Closeable) bufferedReader);
            return zzk;
        } catch (IOException e) {
            throw new SystemGroupsNotAvailableException("Unable to access /proc/pid/status.", e);
        } catch (Throwable th) {
            IOUtils.closeQuietly((Closeable) bufferedReader);
            throw th;
        }
    }

    private static int zzde() {
        if (zzaaj == 0) {
            zzaaj = Process.myPid();
        }
        return zzaaj;
    }

    private static boolean zzk(BufferedReader bufferedReader) throws IOException, SystemGroupsNotAvailableException {
        String readLine = bufferedReader.readLine();
        while (readLine != null) {
            String trim = readLine.trim();
            if (trim.startsWith("Groups:")) {
                for (String parseLong : trim.substring(7).trim().split("\\s", -1)) {
                    try {
                        long parseLong2 = Long.parseLong(parseLong);
                        if (parseLong2 >= 1000 && parseLong2 < 2000) {
                            return true;
                        }
                    } catch (NumberFormatException e) {
                    }
                }
                return false;
            }
            readLine = bufferedReader.readLine();
        }
        throw new SystemGroupsNotAvailableException("Missing Groups entry from proc/pid/status.");
    }

    /* JADX WARNING: type inference failed for: r1v1, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: type inference failed for: r1v4, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v13 */
    /* JADX WARNING: type inference failed for: r1v14 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String zzl(int r4) {
        /*
            r0 = 0
            if (r4 > 0) goto L_0x0004
        L_0x0003:
            return r0
        L_0x0004:
            r1 = 25
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x002f, all -> 0x0035 }
            r2.<init>(r1)     // Catch:{ IOException -> 0x002f, all -> 0x0035 }
            java.lang.String r1 = "/proc/"
            java.lang.StringBuilder r1 = r2.append(r1)     // Catch:{ IOException -> 0x002f, all -> 0x0035 }
            java.lang.StringBuilder r1 = r1.append(r4)     // Catch:{ IOException -> 0x002f, all -> 0x0035 }
            java.lang.String r2 = "/cmdline"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ IOException -> 0x002f, all -> 0x0035 }
            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x002f, all -> 0x0035 }
            java.io.BufferedReader r1 = zzm(r1)     // Catch:{ IOException -> 0x002f, all -> 0x0035 }
            java.lang.String r2 = r1.readLine()     // Catch:{ IOException -> 0x003f, all -> 0x003d }
            java.lang.String r0 = r2.trim()     // Catch:{ IOException -> 0x003f, all -> 0x003d }
            com.google.android.gms.common.util.IOUtils.closeQuietly(r1)
            goto L_0x0003
        L_0x002f:
            r1 = move-exception
            r1 = r0
        L_0x0031:
            com.google.android.gms.common.util.IOUtils.closeQuietly(r1)
            goto L_0x0003
        L_0x0035:
            r1 = move-exception
            r3 = r1
            r1 = r0
            r0 = r3
        L_0x0039:
            com.google.android.gms.common.util.IOUtils.closeQuietly(r1)
            throw r0
        L_0x003d:
            r0 = move-exception
            goto L_0x0039
        L_0x003f:
            r2 = move-exception
            goto L_0x0031
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.util.ProcessUtils.zzl(int):java.lang.String");
    }

    private static BufferedReader zzm(String str) throws IOException {
        ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            return new BufferedReader(new FileReader(str));
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }
}
