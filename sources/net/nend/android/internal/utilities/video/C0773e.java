package net.nend.android.internal.utilities.video;

import android.content.Context;
import android.os.Build.VERSION;

/* renamed from: net.nend.android.internal.utilities.video.e */
/* compiled from: PermissionUtils */
public class C0773e {
    /* renamed from: a */
    public static boolean m1264a(Context context, String str) {
        if (VERSION.SDK_INT >= 23) {
            if (context.checkSelfPermission(str) == 0) {
                return true;
            }
            return false;
        } else if (context.getPackageManager().checkPermission(str, context.getPackageName()) != 0) {
            return false;
        } else {
            return true;
        }
    }
}
