package net.nend.android.internal.p013c;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

/* renamed from: net.nend.android.internal.c.e */
/* compiled from: NendURLConnectionHelper */
public class C0582e {

    /* renamed from: net.nend.android.internal.c.e$a */
    /* compiled from: NendURLConnectionHelper */
    private static class C0583a {
        /* renamed from: a */
        static byte[] m700a(InputStream inputStream) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                try {
                    int read = inputStream.read(bArr);
                    if (read < 0) {
                        return byteArrayOutputStream.toByteArray();
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                } catch (IOException e) {
                    return null;
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x0091 A[SYNTHETIC, Splitter:B:34:0x0091] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0096 A[SYNTHETIC, Splitter:B:37:0x0096] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00b7 A[SYNTHETIC, Splitter:B:49:0x00b7] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00bc A[SYNTHETIC, Splitter:B:52:0x00bc] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00c1  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static net.nend.android.internal.p013c.C0595f m699a(java.lang.String r8, java.lang.String r9, org.json.JSONObject r10, boolean r11) {
        /*
            r3 = 0
            r2 = 500(0x1f4, float:7.0E-43)
            java.net.URL r0 = new java.net.URL     // Catch:{ IOException -> 0x0085, all -> 0x00b2 }
            r0.<init>(r8)     // Catch:{ IOException -> 0x0085, all -> 0x00b2 }
            java.net.URLConnection r0 = r0.openConnection()     // Catch:{ IOException -> 0x0085, all -> 0x00b2 }
            java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch:{ IOException -> 0x0085, all -> 0x00b2 }
            r0.setRequestMethod(r9)     // Catch:{ IOException -> 0x00e5, all -> 0x00d3 }
            r1 = 10000(0x2710, float:1.4013E-41)
            r0.setReadTimeout(r1)     // Catch:{ IOException -> 0x00e5, all -> 0x00d3 }
            r1 = 10000(0x2710, float:1.4013E-41)
            r0.setConnectTimeout(r1)     // Catch:{ IOException -> 0x00e5, all -> 0x00d3 }
            r1 = 1
            r0.setDoInput(r1)     // Catch:{ IOException -> 0x00e5, all -> 0x00d3 }
            r1 = 0
            r0.setUseCaches(r1)     // Catch:{ IOException -> 0x00e5, all -> 0x00d3 }
            if (r10 == 0) goto L_0x00fa
            java.lang.String r1 = "Content-Type"
            java.lang.String r4 = "application/json"
            r0.setRequestProperty(r1, r4)     // Catch:{ IOException -> 0x00e5, all -> 0x00d3 }
            java.lang.String r1 = "Accept"
            java.lang.String r4 = "application/json"
            r0.setRequestProperty(r1, r4)     // Catch:{ IOException -> 0x00e5, all -> 0x00d3 }
            r1 = 1
            r0.setDoOutput(r1)     // Catch:{ IOException -> 0x00e5, all -> 0x00d3 }
            java.io.OutputStream r4 = r0.getOutputStream()     // Catch:{ IOException -> 0x00e5, all -> 0x00d3 }
            java.lang.String r1 = r10.toString()     // Catch:{ IOException -> 0x00ed, all -> 0x00d9 }
            java.lang.String r5 = "UTF-8"
            byte[] r1 = r1.getBytes(r5)     // Catch:{ IOException -> 0x00ed, all -> 0x00d9 }
            r4.write(r1)     // Catch:{ IOException -> 0x00ed, all -> 0x00d9 }
            r4.flush()     // Catch:{ IOException -> 0x00ed, all -> 0x00d9 }
        L_0x004b:
            r0.connect()     // Catch:{ IOException -> 0x00ed, all -> 0x00d9 }
            int r2 = r0.getResponseCode()     // Catch:{ IOException -> 0x00ed, all -> 0x00d9 }
            if (r11 == 0) goto L_0x0072
            java.io.InputStream r5 = r0.getInputStream()     // Catch:{ IOException -> 0x00ed, all -> 0x00d9 }
        L_0x0058:
            net.nend.android.internal.c.f r1 = new net.nend.android.internal.c.f     // Catch:{ IOException -> 0x00f4, all -> 0x00de }
            byte[] r6 = net.nend.android.internal.p013c.C0582e.C0583a.m700a(r5)     // Catch:{ IOException -> 0x00f4, all -> 0x00de }
            r1.<init>(r2, r6)     // Catch:{ IOException -> 0x00f4, all -> 0x00de }
            if (r4 == 0) goto L_0x0066
            r4.close()     // Catch:{ IOException -> 0x0077 }
        L_0x0066:
            if (r5 == 0) goto L_0x006b
            r5.close()     // Catch:{ IOException -> 0x007e }
        L_0x006b:
            if (r0 == 0) goto L_0x0070
            r0.disconnect()
        L_0x0070:
            r0 = r1
        L_0x0071:
            return r0
        L_0x0072:
            java.io.InputStream r5 = m698a(r0)     // Catch:{ IOException -> 0x00ed, all -> 0x00d9 }
            goto L_0x0058
        L_0x0077:
            r2 = move-exception
            net.nend.android.internal.utilities.g r3 = net.nend.android.internal.utilities.C0758g.ERR_HTTP_REQUEST
            net.nend.android.internal.utilities.C0757f.m1219b(r3, r2)
            goto L_0x0066
        L_0x007e:
            r2 = move-exception
            net.nend.android.internal.utilities.g r3 = net.nend.android.internal.utilities.C0758g.ERR_HTTP_REQUEST
            net.nend.android.internal.utilities.C0757f.m1219b(r3, r2)
            goto L_0x006b
        L_0x0085:
            r0 = move-exception
            r1 = r2
            r4 = r3
            r5 = r3
            r2 = r3
        L_0x008a:
            net.nend.android.internal.utilities.g r6 = net.nend.android.internal.utilities.C0758g.ERR_HTTP_REQUEST     // Catch:{ all -> 0x00e2 }
            net.nend.android.internal.utilities.C0757f.m1219b(r6, r0)     // Catch:{ all -> 0x00e2 }
            if (r4 == 0) goto L_0x0094
            r4.close()     // Catch:{ IOException -> 0x00a4 }
        L_0x0094:
            if (r5 == 0) goto L_0x0099
            r5.close()     // Catch:{ IOException -> 0x00ab }
        L_0x0099:
            if (r2 == 0) goto L_0x009e
            r2.disconnect()
        L_0x009e:
            net.nend.android.internal.c.f r0 = new net.nend.android.internal.c.f
            r0.<init>(r1, r3)
            goto L_0x0071
        L_0x00a4:
            r0 = move-exception
            net.nend.android.internal.utilities.g r4 = net.nend.android.internal.utilities.C0758g.ERR_HTTP_REQUEST
            net.nend.android.internal.utilities.C0757f.m1219b(r4, r0)
            goto L_0x0094
        L_0x00ab:
            r0 = move-exception
            net.nend.android.internal.utilities.g r4 = net.nend.android.internal.utilities.C0758g.ERR_HTTP_REQUEST
            net.nend.android.internal.utilities.C0757f.m1219b(r4, r0)
            goto L_0x0099
        L_0x00b2:
            r0 = move-exception
            r4 = r3
            r5 = r3
        L_0x00b5:
            if (r4 == 0) goto L_0x00ba
            r4.close()     // Catch:{ IOException -> 0x00c5 }
        L_0x00ba:
            if (r5 == 0) goto L_0x00bf
            r5.close()     // Catch:{ IOException -> 0x00cc }
        L_0x00bf:
            if (r3 == 0) goto L_0x00c4
            r3.disconnect()
        L_0x00c4:
            throw r0
        L_0x00c5:
            r1 = move-exception
            net.nend.android.internal.utilities.g r2 = net.nend.android.internal.utilities.C0758g.ERR_HTTP_REQUEST
            net.nend.android.internal.utilities.C0757f.m1219b(r2, r1)
            goto L_0x00ba
        L_0x00cc:
            r1 = move-exception
            net.nend.android.internal.utilities.g r2 = net.nend.android.internal.utilities.C0758g.ERR_HTTP_REQUEST
            net.nend.android.internal.utilities.C0757f.m1219b(r2, r1)
            goto L_0x00bf
        L_0x00d3:
            r1 = move-exception
            r4 = r3
            r5 = r3
            r3 = r0
            r0 = r1
            goto L_0x00b5
        L_0x00d9:
            r1 = move-exception
            r5 = r3
            r3 = r0
            r0 = r1
            goto L_0x00b5
        L_0x00de:
            r1 = move-exception
            r3 = r0
            r0 = r1
            goto L_0x00b5
        L_0x00e2:
            r0 = move-exception
            r3 = r2
            goto L_0x00b5
        L_0x00e5:
            r1 = move-exception
            r4 = r3
            r5 = r3
            r7 = r0
            r0 = r1
            r1 = r2
            r2 = r7
            goto L_0x008a
        L_0x00ed:
            r1 = move-exception
            r5 = r3
            r7 = r2
            r2 = r0
            r0 = r1
            r1 = r7
            goto L_0x008a
        L_0x00f4:
            r1 = move-exception
            r7 = r1
            r1 = r2
            r2 = r0
            r0 = r7
            goto L_0x008a
        L_0x00fa:
            r4 = r3
            goto L_0x004b
        */
        throw new UnsupportedOperationException("Method not decompiled: net.nend.android.internal.p013c.C0582e.m699a(java.lang.String, java.lang.String, org.json.JSONObject, boolean):net.nend.android.internal.c.f");
    }

    /* renamed from: a */
    private static InputStream m698a(HttpURLConnection httpURLConnection) {
        try {
            return httpURLConnection.getInputStream();
        } catch (IOException e) {
            return httpURLConnection.getErrorStream();
        }
    }
}
