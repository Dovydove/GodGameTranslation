package net.nend.android.internal.utilities;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.VisibleForTesting;
import android.util.AttributeSet;
import java.util.Arrays;

/* renamed from: net.nend.android.internal.utilities.i */
/* compiled from: ResourcesUtil */
public final class C0761i {
    /* renamed from: a */
    public static int m1229a(Context context, String str) {
        return Arrays.binarySearch(m1232a(context), m1238g(context, str));
    }

    @VisibleForTesting
    /* renamed from: a */
    static int[] m1232a(Context context) {
        int[] iArr = {m1238g(context, "NendSpotId"), m1238g(context, "NendApiKey"), m1238g(context, "NendReloadable"), m1238g(context, "NendAdjustSize"), m1238g(context, "NendTitleColor"), m1238g(context, "NendTitleVisible"), m1238g(context, "NendIconCount"), m1238g(context, "NendIconSpaceEnabled"), m1238g(context, "NendOrientation")};
        Arrays.sort(iArr);
        return iArr;
    }

    /* renamed from: a */
    public static TypedArray m1231a(Context context, AttributeSet attributeSet, int i) {
        return context.getTheme().obtainStyledAttributes(attributeSet, m1232a(context), i, 0);
    }

    /* renamed from: a */
    private static int m1230a(Context context, String str, String str2) {
        return context.getResources().getIdentifier(str, str2, context.getPackageName());
    }

    /* renamed from: b */
    public static int m1233b(Context context, String str) {
        return m1230a(context, str, "id");
    }

    /* renamed from: c */
    public static int m1234c(Context context, String str) {
        return m1230a(context, str, "layout");
    }

    /* renamed from: d */
    public static int m1235d(Context context, String str) {
        return m1230a(context, str, "string");
    }

    /* renamed from: e */
    public static int m1236e(Context context, String str) {
        return m1230a(context, str, "drawable");
    }

    /* renamed from: f */
    public static int m1237f(Context context, String str) {
        return m1230a(context, str, "dimen");
    }

    /* renamed from: g */
    private static int m1238g(Context context, String str) {
        return m1230a(context, str, "attr");
    }
}
