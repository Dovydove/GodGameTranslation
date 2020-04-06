package net.nend.android.internal.p023ui.views.video;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.p000v4.view.ViewCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import net.nend.android.internal.utilities.C0761i;

@SuppressLint({"ViewConstructor"})
/* renamed from: net.nend.android.internal.ui.views.video.d */
/* compiled from: TopOverlayView */
public class C0736d extends RelativeLayout {

    /* renamed from: a */
    private final Button f1122a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public final C0739a f1123b;

    /* renamed from: net.nend.android.internal.ui.views.video.d$a */
    /* compiled from: TopOverlayView */
    public interface C0739a {
        /* renamed from: a */
        void mo8058a();

        /* renamed from: b */
        void mo8059b();
    }

    public C0736d(Context context, String str, C0739a aVar) {
        super(context);
        this.f1122a = new Button(context);
        this.f1123b = aVar;
        m1167a(context, str);
    }

    /* renamed from: a */
    private void m1167a(Context context, String str) {
        this.f1122a.setText(str);
        this.f1122a.setTextColor(-1);
        this.f1122a.setBackgroundResource(C0761i.m1236e(context, "nend_ad_video_cta"));
        this.f1122a.setTypeface(Typeface.DEFAULT_BOLD);
        this.f1122a.setTextSize(1, 20.0f);
        this.f1122a.setShadowLayer(8.0f, 1.0f, 1.0f, ViewCompat.MEASURED_STATE_MASK);
        int dimensionPixelSize = getResources().getDimensionPixelSize(C0761i.m1237f(context, "nend_video_ad_overlay_elements_margin"));
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.setMargins(0, dimensionPixelSize, 0, 0);
        layoutParams.addRule(9);
        layoutParams.addRule(15);
        this.f1122a.setLayoutParams(layoutParams);
        Button button = new Button(context);
        button.setBackgroundResource(C0761i.m1236e(context, "nend_ad_video_close"));
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(C0761i.m1237f(context, "nend_video_ad_overlay_content_size"));
        LayoutParams layoutParams2 = new LayoutParams(dimensionPixelSize2, dimensionPixelSize2);
        layoutParams2.setMargins(0, dimensionPixelSize, dimensionPixelSize, 0);
        layoutParams2.addRule(11);
        layoutParams2.addRule(15);
        button.setLayoutParams(layoutParams2);
        addView(this.f1122a);
        addView(button);
        this.f1122a.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                C0736d.this.f1123b.mo8058a();
            }
        });
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                C0736d.this.f1123b.mo8059b();
            }
        });
    }

    public void setHideCallToAction(boolean z) {
        if (z) {
            this.f1122a.setVisibility(8);
        } else {
            this.f1122a.setVisibility(0);
        }
    }
}
