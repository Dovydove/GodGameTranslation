package com.google.android.gms.internal.stable;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;
import java.util.HashMap;

public final class zze {

    public static class zza implements BaseColumns {
        private static HashMap<Uri, zzh> zzagq = new HashMap<>();

        private static zzh zza(ContentResolver contentResolver, Uri uri) {
            zzh zzh = (zzh) zzagq.get(uri);
            if (zzh == null) {
                zzh zzh2 = new zzh();
                zzagq.put(uri, zzh2);
                contentResolver.registerContentObserver(uri, true, new zzf(null, zzh2));
                return zzh2;
            } else if (!zzh.zzagu.getAndSet(false)) {
                return zzh;
            } else {
                synchronized (zzh) {
                    zzh.zzags.clear();
                    zzh.zzagt = new Object();
                }
                return zzh;
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            r0 = r9;
            r1 = r10;
            r2 = r0.query(r1, new java.lang.String[]{"value"}, "name=?", new java.lang.String[]{r11}, null);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0039, code lost:
            if (r2 == null) goto L_0x0041;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x003f, code lost:
            if (r2.moveToFirst() != false) goto L_0x004f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:24:0x0041, code lost:
            zza(r7, r8, r11, null);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:25:0x0045, code lost:
            if (r2 == null) goto L_0x004a;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:0x0047, code lost:
            r2.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
            r0 = r2.getString(0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
            zza(r7, r8, r11, r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:37:0x0057, code lost:
            if (r2 == null) goto L_?;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:38:0x0059, code lost:
            r2.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:39:0x005d, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:0x005e, code lost:
            r1 = r0;
            r0 = null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:50:0x008b, code lost:
            r0 = th;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:51:0x008c, code lost:
            r6 = r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:52:0x008e, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:53:0x008f, code lost:
            r1 = r0;
            r0 = null;
            r6 = r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:54:0x0093, code lost:
            r1 = e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:55:0x0094, code lost:
            r6 = r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
            return null;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:65:?, code lost:
            return r0;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:66:?, code lost:
            return r0;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Removed duplicated region for block: B:48:0x0087  */
        /* JADX WARNING: Removed duplicated region for block: B:50:0x008b A[ExcHandler: all (th java.lang.Throwable), Splitter:B:21:0x003b] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected static java.lang.String zza(android.content.ContentResolver r9, android.net.Uri r10, java.lang.String r11) {
            /*
                r6 = 0
                java.lang.Class<com.google.android.gms.internal.stable.zze$zza> r1 = com.google.android.gms.internal.stable.zze.zza.class
                monitor-enter(r1)
                com.google.android.gms.internal.stable.zzh r7 = zza(r9, r10)     // Catch:{ all -> 0x001e }
                monitor-exit(r1)     // Catch:{ all -> 0x001e }
                monitor-enter(r7)
                java.lang.Object r8 = r7.zzagt     // Catch:{ all -> 0x004c }
                java.util.HashMap<java.lang.String, java.lang.String> r0 = r7.zzags     // Catch:{ all -> 0x004c }
                boolean r0 = r0.containsKey(r11)     // Catch:{ all -> 0x004c }
                if (r0 == 0) goto L_0x0021
                java.util.HashMap<java.lang.String, java.lang.String> r0 = r7.zzags     // Catch:{ all -> 0x004c }
                java.lang.Object r0 = r0.get(r11)     // Catch:{ all -> 0x004c }
                java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x004c }
                monitor-exit(r7)     // Catch:{ all -> 0x004c }
            L_0x001d:
                return r0
            L_0x001e:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x001e }
                throw r0
            L_0x0021:
                monitor-exit(r7)     // Catch:{ all -> 0x004c }
                r0 = 1
                java.lang.String[] r2 = new java.lang.String[r0]     // Catch:{ SQLException -> 0x005d }
                r0 = 0
                java.lang.String r1 = "value"
                r2[r0] = r1     // Catch:{ SQLException -> 0x005d }
                java.lang.String r3 = "name=?"
                r0 = 1
                java.lang.String[] r4 = new java.lang.String[r0]     // Catch:{ SQLException -> 0x005d }
                r0 = 0
                r4[r0] = r11     // Catch:{ SQLException -> 0x005d }
                r5 = 0
                r0 = r9
                r1 = r10
                android.database.Cursor r2 = r0.query(r1, r2, r3, r4, r5)     // Catch:{ SQLException -> 0x005d }
                if (r2 == 0) goto L_0x0041
                boolean r0 = r2.moveToFirst()     // Catch:{ SQLException -> 0x008e, all -> 0x008b }
                if (r0 != 0) goto L_0x004f
            L_0x0041:
                r0 = 0
                zza(r7, r8, r11, r0)     // Catch:{ SQLException -> 0x008e, all -> 0x008b }
                if (r2 == 0) goto L_0x004a
                r2.close()
            L_0x004a:
                r0 = r6
                goto L_0x001d
            L_0x004c:
                r0 = move-exception
                monitor-exit(r7)     // Catch:{ all -> 0x004c }
                throw r0
            L_0x004f:
                r0 = 0
                java.lang.String r0 = r2.getString(r0)     // Catch:{ SQLException -> 0x008e, all -> 0x008b }
                zza(r7, r8, r11, r0)     // Catch:{ SQLException -> 0x0093, all -> 0x008b }
                if (r2 == 0) goto L_0x001d
                r2.close()
                goto L_0x001d
            L_0x005d:
                r0 = move-exception
                r1 = r0
                r0 = r6
            L_0x0060:
                java.lang.String r2 = "GoogleSettings"
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0084 }
                java.lang.String r4 = "Can't get key "
                r3.<init>(r4)     // Catch:{ all -> 0x0084 }
                java.lang.StringBuilder r3 = r3.append(r11)     // Catch:{ all -> 0x0084 }
                java.lang.String r4 = " from "
                java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x0084 }
                java.lang.StringBuilder r3 = r3.append(r10)     // Catch:{ all -> 0x0084 }
                java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0084 }
                android.util.Log.e(r2, r3, r1)     // Catch:{ all -> 0x0084 }
                if (r6 == 0) goto L_0x001d
                r6.close()
                goto L_0x001d
            L_0x0084:
                r0 = move-exception
            L_0x0085:
                if (r6 == 0) goto L_0x008a
                r6.close()
            L_0x008a:
                throw r0
            L_0x008b:
                r0 = move-exception
                r6 = r2
                goto L_0x0085
            L_0x008e:
                r0 = move-exception
                r1 = r0
                r0 = r6
                r6 = r2
                goto L_0x0060
            L_0x0093:
                r1 = move-exception
                r6 = r2
                goto L_0x0060
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.stable.zze.zza.zza(android.content.ContentResolver, android.net.Uri, java.lang.String):java.lang.String");
        }

        private static void zza(zzh zzh, Object obj, String str, String str2) {
            synchronized (zzh) {
                if (obj == zzh.zzagt) {
                    zzh.zzags.put(str, str2);
                }
            }
        }
    }
}
