package net.nend.android.internal.utilities;

import android.net.Uri.Builder;

/* renamed from: net.nend.android.internal.utilities.d */
/* compiled from: NendFlavors */
public final class C0754d {
    /* renamed from: a */
    public static String m1193a(String str) {
        return new Builder().scheme("https").authority("vdapp.nend.net").path("v1/video/ad" + str).toString();
    }
}
