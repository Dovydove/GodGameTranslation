package net.nend.android.internal.p023ui.views.video;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.p000v4.view.ViewCompat;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.ToggleButton;
import net.nend.android.internal.utilities.C0761i;

@SuppressLint({"ViewConstructor"})
/* renamed from: net.nend.android.internal.ui.views.video.a */
/* compiled from: BottomOverlayView */
public class C0728a extends RelativeLayout {

    /* renamed from: a */
    private final TextView f1113a;

    /* renamed from: b */
    private final ToggleButton f1114b;

    /* renamed from: c */
    private final C0730a f1115c;

    /* renamed from: net.nend.android.internal.ui.views.video.a$a */
    /* compiled from: BottomOverlayView */
    public interface C0730a {
        /* renamed from: a */
        void mo8080a(boolean z);
    }

    public C0728a(Context context, C0730a aVar) {
        super(context);
        this.f1114b = new ToggleButton(context);
        this.f1113a = new TextView(context);
        this.f1115c = aVar;
        m1157a();
    }

    /* renamed from: a */
    private void m1157a() {
        this.f1114b.setBackgroundResource(C0761i.m1236e(getContext(), "nend_ad_video_toggle_volume"));
        this.f1114b.setTextOff("");
        this.f1114b.setTextOn("");
        this.f1114b.setChecked(false);
        int f = C0761i.m1237f(getContext(), "nend_video_ad_overlay_elements_margin");
        int f2 = C0761i.m1237f(getContext(), "nend_video_ad_overlay_content_size");
        int dimensionPixelSize = getResources().getDimensionPixelSize(f);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(f2);
        LayoutParams layoutParams = new LayoutParams(dimensionPixelSize2, dimensionPixelSize2);
        layoutParams.addRule(9);
        layoutParams.addRule(12);
        layoutParams.setMargins(dimensionPixelSize, 0, 0, dimensionPixelSize);
        this.f1114b.setLayoutParams(layoutParams);
        this.f1113a.setTextSize(1, 16.0f);
        this.f1113a.setBackgroundResource(C0761i.m1236e(getContext(), "nend_ad_video_mask"));
        this.f1113a.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.f1113a.setGravity(17);
        LayoutParams layoutParams2 = new LayoutParams(dimensionPixelSize2, dimensionPixelSize2);
        layoutParams2.addRule(11);
        layoutParams2.addRule(12);
        layoutParams2.setMargins(0, 0, dimensionPixelSize, dimensionPixelSize);
        this.f1113a.setLayoutParams(layoutParams2);
        addView(this.f1114b);
        addView(this.f1113a);
        this.f1114b.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                C0728a.this.setMute(!z);
            }
        });
    }

    public void setCounterText(String str) {
        this.f1113a.setText(str);
    }

    public void setCheckToggleButton(boolean z) {
        this.f1114b.setChecked(z);
    }

    /* access modifiers changed from: private */
    public void setMute(boolean z) {
        this.f1115c.mo8080a(z);
    }
}
