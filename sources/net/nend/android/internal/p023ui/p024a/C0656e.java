package net.nend.android.internal.p023ui.p024a;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import java.text.NumberFormat;
import net.nend.android.internal.p008b.p012c.C0540b;
import net.nend.android.internal.utilities.C0761i;

/* renamed from: net.nend.android.internal.ui.a.e */
/* compiled from: NativeVideoUtils */
public class C0656e {
    /* renamed from: a */
    private static LayoutParams m938a(int i, int i2, float f) {
        int b = m943b(i, i2, f);
        return new LayoutParams((int) (((float) b) * f), (int) (((float) b) * f));
    }

    /* renamed from: b */
    private static int m943b(int i, int i2, float f) {
        return (int) (((float) Math.min(i, i2)) * f);
    }

    /* renamed from: b */
    private static boolean m944b(View view, int i) {
        return view != null && (i < view.getHeight() || view.getHeight() == 0);
    }

    /* renamed from: a */
    public static void m940a(int i, int i2, ImageView imageView) {
        if (i > 0 && i2 > 0) {
            LayoutParams a = m938a(i, i2, 0.3f);
            a.gravity = 83;
            if (m944b(imageView, a.height)) {
                imageView.setLayoutParams(a);
            }
        }
    }

    /* renamed from: a */
    public static View m937a(FrameLayout frameLayout, Context context, C0540b bVar) {
        String str;
        String str2;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        if (bVar.f564c == 1) {
            str = "media_replay_cta_port";
            str2 = "media_view_replay_cta_port";
        } else {
            str = "media_replay_cta_land";
            str2 = "media_view_replay_cta_land";
        }
        int c = C0761i.m1234c(context, str);
        int b = C0761i.m1233b(context, str2);
        if (layoutInflater == null) {
            return null;
        }
        ConstraintLayout constraintLayout = (ConstraintLayout) layoutInflater.inflate(c, (ViewGroup) frameLayout.findViewById(b));
        frameLayout.addView(constraintLayout);
        return constraintLayout;
    }

    /* renamed from: a */
    public static float m934a(View view, int i) {
        float f;
        float width;
        int a = m936a(view.getContext(), i);
        if (i == 1) {
            f = 0.5f;
            width = ((float) view.getHeight()) / 1.7777778f;
        } else {
            f = 0.7f;
            width = ((float) view.getWidth()) / 1.7777778f;
        }
        return f * Math.min(width, (float) a);
    }

    /* renamed from: a */
    private static int m936a(Context context, int i) {
        String str;
        if (i == 1) {
            str = "max_width_rectangle_media_view_replay_cta_port";
        } else {
            str = "max_height_rectangle_media_view_replay_cta_land";
        }
        return context.getResources().getDimensionPixelSize(C0761i.m1237f(context, str));
    }

    /* renamed from: a */
    public static void m942a(Context context, View view, float f, float f2, int i) {
        String str;
        String str2;
        if (i == 1) {
            String str3 = "rectangle_media_view_replay_cta_port";
            if (f2 / f <= 1.7777778f) {
                str = "w,9:16";
                str2 = str3;
            } else {
                str = "h,9:16";
                str2 = str3;
            }
        } else {
            String str4 = "rectangle_media_view_replay_cta_land";
            if (f / f2 <= 1.7777778f) {
                str = "h,16:9";
                str2 = str4;
            } else {
                str = "w,16:9";
                str2 = str4;
            }
        }
        ConstraintLayout constraintLayout = (ConstraintLayout) view.findViewById(C0761i.m1233b(context, str2));
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) constraintLayout.getLayoutParams();
        layoutParams.dimensionRatio = str;
        constraintLayout.setLayoutParams(layoutParams);
    }

    @VisibleForTesting
    /* renamed from: a */
    static int m935a(Context context, float f, float f2) {
        if (f >= f2) {
            return C0761i.m1236e(context, "nend_ad_star_none");
        }
        if (f2 - f <= 0.5f) {
            return C0761i.m1236e(context, "nend_ad_star_half");
        }
        return C0761i.m1236e(context, "nend_ad_star_filled");
    }

    /* renamed from: a */
    public static void m941a(Activity activity, C0540b bVar, int[] iArr) {
        float f = 0.0f;
        for (int i : iArr) {
            int a = m935a((Context) activity, f, bVar.f612m);
            ImageView imageView = (ImageView) activity.findViewById(i);
            imageView.setImageResource(a);
            imageView.setTag(Integer.valueOf(a));
            f += 1.0f;
        }
    }

    /* renamed from: a */
    public static String m939a(long j) {
        return "(" + NumberFormat.getNumberInstance().format(j) + ")";
    }
}
