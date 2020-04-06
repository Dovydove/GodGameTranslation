package net.nend.android.internal.p013c.p021f;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.VisibleForTesting;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Pair;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import net.nend.android.internal.p007a.C0523a;
import net.nend.android.internal.p008b.C0525a;
import net.nend.android.internal.p008b.p012c.C0544d;
import net.nend.android.internal.p013c.C0626g;
import net.nend.android.internal.p022d.C0636g;
import net.nend.android.internal.p022d.C0646k;
import net.nend.android.internal.p022d.C0647l;
import net.nend.android.internal.utilities.C0753c;
import net.nend.android.internal.utilities.C0755e;
import net.nend.android.internal.utilities.C0757f;
import net.nend.android.internal.utilities.video.NendVideoAdClientError;

/* renamed from: net.nend.android.internal.c.f.a */
/* compiled from: CacheManager */
public class C0596a {

    /* renamed from: c */
    private static C0596a f814c = null;

    /* renamed from: d */
    private static long f815d;
    @VisibleForTesting

    /* renamed from: a */
    public final Map<String, AtomicInteger> f816a = new HashMap();
    @VisibleForTesting

    /* renamed from: b */
    public File f817b;

    /* renamed from: e */
    private final ExecutorService f818e = Executors.newSingleThreadExecutor();

    @VisibleForTesting
    /* renamed from: net.nend.android.internal.c.f.a$a */
    /* compiled from: CacheManager */
    static class C0603a extends ArrayList<String> {
        C0603a() {
        }

        @VisibleForTesting
        /* renamed from: a */
        static C0603a m787a(File file) {
            C0603a aVar = new C0603a();
            aVar.m790c(file);
            return aVar;
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Removed duplicated region for block: B:11:0x001b A[SYNTHETIC, Splitter:B:11:0x001b] */
        /* renamed from: b */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void m789b(java.io.File r4) {
            /*
                r3 = this;
                r2 = 0
                java.io.ObjectOutputStream r1 = new java.io.ObjectOutputStream     // Catch:{ all -> 0x0017 }
                java.io.FileOutputStream r0 = new java.io.FileOutputStream     // Catch:{ all -> 0x0017 }
                r0.<init>(r4)     // Catch:{ all -> 0x0017 }
                r1.<init>(r0)     // Catch:{ all -> 0x0017 }
                r1.writeObject(r3)     // Catch:{ all -> 0x0023 }
                r1.flush()     // Catch:{ all -> 0x0023 }
                if (r1 == 0) goto L_0x0016
                r1.close()     // Catch:{ IOException -> 0x001f }
            L_0x0016:
                return
            L_0x0017:
                r0 = move-exception
                r1 = r2
            L_0x0019:
                if (r1 == 0) goto L_0x001e
                r1.close()     // Catch:{ IOException -> 0x0021 }
            L_0x001e:
                throw r0
            L_0x001f:
                r0 = move-exception
                goto L_0x0016
            L_0x0021:
                r1 = move-exception
                goto L_0x001e
            L_0x0023:
                r0 = move-exception
                goto L_0x0019
            */
            throw new UnsupportedOperationException("Method not decompiled: net.nend.android.internal.p013c.p021f.C0596a.C0603a.m789b(java.io.File):void");
        }

        /* JADX WARNING: Removed duplicated region for block: B:11:0x001e A[SYNTHETIC, Splitter:B:11:0x001e] */
        /* renamed from: c */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void m790c(java.io.File r4) {
            /*
                r3 = this;
                r2 = 0
                java.io.ObjectInputStream r1 = new java.io.ObjectInputStream     // Catch:{ all -> 0x001a }
                java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ all -> 0x001a }
                r0.<init>(r4)     // Catch:{ all -> 0x001a }
                r1.<init>(r0)     // Catch:{ all -> 0x001a }
                java.lang.Object r0 = r1.readObject()     // Catch:{ all -> 0x0026 }
                java.util.List r0 = (java.util.List) r0     // Catch:{ all -> 0x0026 }
                r3.addAll(r0)     // Catch:{ all -> 0x0026 }
                if (r1 == 0) goto L_0x0019
                r1.close()     // Catch:{ IOException -> 0x0022 }
            L_0x0019:
                return
            L_0x001a:
                r0 = move-exception
                r1 = r2
            L_0x001c:
                if (r1 == 0) goto L_0x0021
                r1.close()     // Catch:{ IOException -> 0x0024 }
            L_0x0021:
                throw r0
            L_0x0022:
                r0 = move-exception
                goto L_0x0019
            L_0x0024:
                r1 = move-exception
                goto L_0x0021
            L_0x0026:
                r0 = move-exception
                goto L_0x001c
            */
            throw new UnsupportedOperationException("Method not decompiled: net.nend.android.internal.p013c.p021f.C0596a.C0603a.m790c(java.io.File):void");
        }
    }

    private C0596a(Context context) {
        m767b(context);
        m755a(86400000);
    }

    /* renamed from: b */
    private static boolean m770b() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public static boolean m773c(File file) {
        return System.currentTimeMillis() - file.lastModified() > f815d;
    }

    @VisibleForTesting
    /* renamed from: a */
    public static void m755a(long j) {
        f815d = j;
    }

    /* renamed from: a */
    public static synchronized C0596a m753a(Context context) {
        C0596a aVar;
        synchronized (C0596a.class) {
            if (f814c == null) {
                f814c = new C0596a(context);
            }
            aVar = f814c;
        }
        return aVar;
    }

    /* renamed from: a */
    private static void m757a(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                outputStream.flush();
                return;
            }
        }
    }

    /* renamed from: c */
    private static Callable<Boolean> m772c(final String str, final File file) {
        return new Callable<Boolean>() {
            /* renamed from: a */
            public Boolean call() {
                try {
                    C0596a.m777e(str, file);
                    C0757f.m1210a("Cache a file from " + str + " to " + file.getAbsolutePath());
                    return Boolean.valueOf(true);
                } catch (Exception e) {
                    C0757f.m1218b("Failed to cache file at " + str, e.getCause());
                    return Boolean.valueOf(false);
                }
            }
        };
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0023 A[SYNTHETIC, Splitter:B:15:0x0023] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0028 A[Catch:{ IOException -> 0x002c }] */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m776d(java.lang.String r4, java.io.File r5) {
        /*
            r2 = 0
            java.io.ByteArrayInputStream r3 = new java.io.ByteArrayInputStream     // Catch:{ all -> 0x001f }
            java.lang.String r0 = "utf-8"
            byte[] r0 = r4.getBytes(r0)     // Catch:{ all -> 0x001f }
            r3.<init>(r0)     // Catch:{ all -> 0x001f }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ all -> 0x002e }
            r1.<init>(r5)     // Catch:{ all -> 0x002e }
            m757a(r3, r1)     // Catch:{ all -> 0x0032 }
            if (r1 == 0) goto L_0x0019
            r1.close()     // Catch:{ IOException -> 0x0035 }
        L_0x0019:
            if (r3 == 0) goto L_0x001e
            r3.close()     // Catch:{ IOException -> 0x0035 }
        L_0x001e:
            return
        L_0x001f:
            r0 = move-exception
            r1 = r2
        L_0x0021:
            if (r1 == 0) goto L_0x0026
            r1.close()     // Catch:{ IOException -> 0x002c }
        L_0x0026:
            if (r2 == 0) goto L_0x002b
            r2.close()     // Catch:{ IOException -> 0x002c }
        L_0x002b:
            throw r0
        L_0x002c:
            r1 = move-exception
            goto L_0x002b
        L_0x002e:
            r0 = move-exception
            r1 = r2
            r2 = r3
            goto L_0x0021
        L_0x0032:
            r0 = move-exception
            r2 = r3
            goto L_0x0021
        L_0x0035:
            r0 = move-exception
            goto L_0x001e
        */
        throw new UnsupportedOperationException("Method not decompiled: net.nend.android.internal.p013c.p021f.C0596a.m776d(java.lang.String, java.io.File):void");
    }

    /* renamed from: a */
    static void m759a(C0544d dVar) {
        C0753c.m1191b(new File(dVar.f622o));
        if (!TextUtils.isEmpty(dVar.f623p)) {
            C0753c.m1191b(new File(dVar.f623p));
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0042 A[SYNTHETIC, Splitter:B:20:0x0042] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0047 A[Catch:{ IOException -> 0x0050 }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004c  */
    /* renamed from: e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m777e(java.lang.String r6, java.io.File r7) {
        /*
            r2 = 0
            java.net.URL r0 = new java.net.URL     // Catch:{ all -> 0x003d }
            r0.<init>(r6)     // Catch:{ all -> 0x003d }
            java.net.URLConnection r0 = r0.openConnection()     // Catch:{ all -> 0x003d }
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ all -> 0x003d }
            r1 = 30000(0x7530, float:4.2039E-41)
            r0.setConnectTimeout(r1)     // Catch:{ all -> 0x0052 }
            r1 = 30000(0x7530, float:4.2039E-41)
            r0.setReadTimeout(r1)     // Catch:{ all -> 0x0052 }
            r1 = 1
            r0.setDoInput(r1)     // Catch:{ all -> 0x0052 }
            r1 = 0
            r0.setUseCaches(r1)     // Catch:{ all -> 0x0052 }
            r0.connect()     // Catch:{ all -> 0x0052 }
            java.io.InputStream r4 = r0.getInputStream()     // Catch:{ all -> 0x0052 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ all -> 0x0058 }
            r3.<init>(r7)     // Catch:{ all -> 0x0058 }
            m757a(r4, r3)     // Catch:{ all -> 0x005e }
            if (r3 == 0) goto L_0x0032
            r3.close()     // Catch:{ IOException -> 0x0065 }
        L_0x0032:
            if (r4 == 0) goto L_0x0037
            r4.close()     // Catch:{ IOException -> 0x0065 }
        L_0x0037:
            if (r0 == 0) goto L_0x003c
            r0.disconnect()
        L_0x003c:
            return
        L_0x003d:
            r0 = move-exception
            r1 = r2
            r3 = r2
        L_0x0040:
            if (r2 == 0) goto L_0x0045
            r2.close()     // Catch:{ IOException -> 0x0050 }
        L_0x0045:
            if (r3 == 0) goto L_0x004a
            r3.close()     // Catch:{ IOException -> 0x0050 }
        L_0x004a:
            if (r1 == 0) goto L_0x004f
            r1.disconnect()
        L_0x004f:
            throw r0
        L_0x0050:
            r2 = move-exception
            goto L_0x004a
        L_0x0052:
            r1 = move-exception
            r3 = r2
            r5 = r0
            r0 = r1
            r1 = r5
            goto L_0x0040
        L_0x0058:
            r1 = move-exception
            r3 = r4
            r5 = r0
            r0 = r1
            r1 = r5
            goto L_0x0040
        L_0x005e:
            r1 = move-exception
            r2 = r3
            r3 = r4
            r5 = r1
            r1 = r0
            r0 = r5
            goto L_0x0040
        L_0x0065:
            r1 = move-exception
            goto L_0x0037
        */
        throw new UnsupportedOperationException("Method not decompiled: net.nend.android.internal.p013c.p021f.C0596a.m777e(java.lang.String, java.io.File):void");
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public static void m774d(File file) {
        File[] listFiles;
        for (File file2 : file.listFiles()) {
            if (file2.delete()) {
                C0757f.m1210a("Delete file at " + file2.getAbsolutePath());
            }
        }
        if (file.delete()) {
            C0757f.m1210a("Delete directory at " + file.getAbsolutePath());
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public static String m771c(String str) {
        return C0755e.m1195a(str).toUpperCase();
    }

    @WorkerThread
    /* renamed from: a */
    private static boolean m764a(List<Pair<String, File>> list) {
        boolean z;
        int size = list.size();
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(size);
        ExecutorCompletionService executorCompletionService = new ExecutorCompletionService(newFixedThreadPool);
        for (Pair pair : list) {
            executorCompletionService.submit(m772c((String) pair.first, (File) pair.second));
        }
        int i = 0;
        while (true) {
            if (i >= size) {
                z = true;
                break;
            }
            try {
                if (!((Boolean) executorCompletionService.take().get()).booleanValue()) {
                    newFixedThreadPool.shutdownNow();
                    z = false;
                    break;
                }
                i++;
            } catch (InterruptedException | ExecutionException e) {
                newFixedThreadPool.shutdownNow();
                z = false;
            }
        }
        if (z) {
            newFixedThreadPool.shutdown();
        }
        return z;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void m767b(android.content.Context r7) {
        /*
            r6 = this;
            r1 = 0
            r0 = 1
            r2 = 0
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 19
            if (r3 < r4) goto L_0x0051
        L_0x0009:
            if (r0 == 0) goto L_0x0080
            boolean r0 = m770b()
            if (r0 == 0) goto L_0x0080
            java.io.File r0 = r7.getExternalCacheDir()
            if (r0 == 0) goto L_0x0080
            boolean r2 = r0.canWrite()
            if (r2 == 0) goto L_0x0080
            boolean r2 = r0.canRead()
            if (r2 == 0) goto L_0x0080
            java.lang.String r0 = r0.getAbsolutePath()
        L_0x0027:
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 == 0) goto L_0x0035
            java.io.File r0 = r7.getFilesDir()
            java.lang.String r0 = r0.getAbsolutePath()
        L_0x0035:
            java.io.File r2 = new java.io.File
            java.lang.String r3 = ".nend"
            r2.<init>(r0, r3)
            r6.f817b = r2
            java.io.File r0 = r6.f817b
            boolean r0 = r0.exists()
            if (r0 != 0) goto L_0x0050
            java.io.File r0 = r6.f817b
            boolean r0 = r0.mkdir()
            if (r0 != 0) goto L_0x0063
            r6.f817b = r1
        L_0x0050:
            return
        L_0x0051:
            android.content.pm.PackageManager r3 = r7.getPackageManager()
            java.lang.String r4 = "android.permission.WRITE_EXTERNAL_STORAGE"
            java.lang.String r5 = r7.getPackageName()
            int r3 = r3.checkPermission(r4, r5)
            if (r3 == 0) goto L_0x0009
            r0 = r2
            goto L_0x0009
        L_0x0063:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Create cache directory at "
            java.lang.StringBuilder r0 = r0.append(r1)
            java.io.File r1 = r6.f817b
            java.lang.String r1 = r1.getAbsolutePath()
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            net.nend.android.internal.utilities.C0757f.m1210a(r0)
            goto L_0x0050
        L_0x0080:
            r0 = r1
            goto L_0x0027
        */
        throw new UnsupportedOperationException("Method not decompiled: net.nend.android.internal.p013c.p021f.C0596a.m767b(android.content.Context):void");
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public <V extends C0544d> C0646k<V> mo7950a(V v, final Context context) {
        return mo7949a(v, context).mo7999b((C0636g<? super T, ? extends C0646k<? extends R>>) new C0636g<V, C0646k<? extends V>>() {
            /* renamed from: a */
            public C0646k<? extends V> mo7540a(V v) {
                return C0596a.this.m766b(v, context);
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public <V extends C0544d> C0646k<V> m766b(final V v, final Context context) {
        if (this.f817b == null) {
            return C0647l.m922a((Throwable) new IOException("Cache directory is not exist."));
        }
        return C0647l.m923a(this.f818e, (Callable<T>) new Callable<V>() {
            /* renamed from: a */
            public V call() {
                C0603a a;
                String[] strArr;
                File file;
                String str = null;
                ArrayList arrayList = new ArrayList();
                File file2 = new File(C0596a.this.f817b.getAbsolutePath(), C0596a.m771c(v.f565d));
                String absolutePath = file2.getAbsolutePath();
                File file3 = new File(absolutePath, "mapping.dat");
                if (!file3.exists()) {
                    a = new C0603a();
                } else {
                    a = C0603a.m787a(file3);
                }
                for (String str2 : v.f618k) {
                    String a2 = C0753c.m1187a(str2);
                    File file4 = new File(absolutePath, a2);
                    v.f620m = v.f620m.replace(str2, a2);
                    if (!TextUtils.isEmpty(v.f621n)) {
                        v.f621n = v.f621n.replace(str2, a2);
                    }
                    if (a.contains(str2)) {
                        if (file4.exists()) {
                        } else {
                            a.remove(str2);
                        }
                    }
                    arrayList.add(Pair.create(str2, file4));
                    a.add(str2);
                }
                String valueOf = String.valueOf(System.currentTimeMillis());
                File file5 = new File(absolutePath, valueOf + "_endcard.html");
                C0596a.m776d(v.f620m, file5);
                if (!TextUtils.isEmpty(v.f621n)) {
                    file = new File(absolutePath, valueOf + "_htmlOnPlaying.html");
                    C0596a.m776d(v.f621n, file);
                } else {
                    file = null;
                }
                C0596a.this.m756a(context, arrayList, file2, a);
                C0544d dVar = v;
                String absolutePath2 = file5.getAbsolutePath();
                if (file != null) {
                    str = file.getAbsolutePath();
                }
                dVar.mo7819b(absolutePath2, str);
                return v;
            }
        });
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public <V extends C0525a> C0646k<V> mo7949a(final V v, final Context context) {
        if (this.f817b == null) {
            return C0647l.m922a((Throwable) new IOException("Cache directory is not exist."));
        }
        return C0647l.m923a(this.f818e, (Callable<T>) new Callable<V>() {
            /* renamed from: a */
            public V call() {
                if (C0596a.this.f817b.exists()) {
                    C0596a.this.mo7951a();
                } else {
                    C0596a.this.m767b(context);
                }
                String b = C0596a.m771c(v.f565d);
                File file = new File(C0596a.this.f817b.getAbsolutePath(), b);
                String absolutePath = file.getAbsolutePath();
                ArrayList arrayList = new ArrayList();
                File file2 = new File(absolutePath, b);
                if (!file2.exists()) {
                    arrayList.add(Pair.create(v.f565d, file2));
                }
                if (file.exists() || file.mkdir()) {
                    C0596a.this.m756a(context, arrayList, file, null);
                    C0596a.this.m775d(absolutePath);
                    v.mo7779a(absolutePath, file2.getAbsolutePath());
                    return v;
                }
                throw new IOException("Failed to create cache directory.");
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m756a(Context context, List<Pair<String, File>> list, File file, C0603a aVar) {
        if (list.isEmpty()) {
            return;
        }
        if (!C0626g.m868a(context) || !m764a(list)) {
            m774d(file);
            throw new C0523a(NendVideoAdClientError.FAILED_AD_DOWNLOAD);
        }
        if (aVar != null) {
            aVar.m789b(new File(file, "mapping.dat"));
        }
        C0757f.m1210a("Success to create a cache directory at " + file.getAbsolutePath());
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    /* renamed from: a */
    public void mo7951a() {
        if (this.f817b != null) {
            File[] listFiles = this.f817b.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    return file.isDirectory();
                }
            });
            if (listFiles != null && listFiles.length > 0) {
                for (File file : listFiles) {
                    String absolutePath = file.getAbsolutePath();
                    AtomicInteger atomicInteger = (AtomicInteger) this.f816a.get(absolutePath);
                    if (atomicInteger == null || atomicInteger.get() <= 0) {
                        if (m773c(file)) {
                            m774d(file);
                        } else if (!new File(absolutePath, "mapping.dat").exists()) {
                            m774d(file);
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public void m775d(String str) {
        if (this.f816a.containsKey(str)) {
            ((AtomicInteger) this.f816a.get(str)).incrementAndGet();
        } else {
            this.f816a.put(str, new AtomicInteger(1));
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo7952a(final String str) {
        this.f818e.execute(new Runnable() {
            public void run() {
                if (C0596a.this.f816a.containsKey(str) && ((AtomicInteger) C0596a.this.f816a.get(str)).decrementAndGet() == 0) {
                    File file = new File(str);
                    if (file.exists() && C0596a.m773c(file)) {
                        C0596a.m774d(file);
                        C0596a.this.f816a.remove(str);
                    }
                }
            }
        });
    }
}
