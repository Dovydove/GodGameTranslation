package net.nend.android;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.nend.android.internal.p008b.p011b.C0534a;

public class NendNativeAdConnectorFactory {

    /* renamed from: a */
    private static final List<String> f538a = Arrays.asList(new String[]{"net.nend.unity.plugin.NendUnityNativeAdClient", "net.nend.NendModule.NendNativeAdClient"});

    public static NendNativeAdConnector createNativeAdConnector(NendAdNative nendAdNative) {
        boolean z;
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int length = stackTrace.length;
        int i = 0;
        boolean z2 = false;
        while (true) {
            if (i >= length) {
                z = z2;
                break;
            }
            StackTraceElement stackTraceElement = stackTrace[i];
            Iterator it = f538a.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = z2;
                    break;
                }
                if (stackTraceElement.getClassName().contains((String) it.next())) {
                    z = true;
                    break;
                }
            }
            if (z) {
                break;
            }
            i++;
            z2 = z;
        }
        if (z) {
            return new C0534a(nendAdNative);
        }
        throw new UnsupportedOperationException("Not allow to use this feature.");
    }
}
