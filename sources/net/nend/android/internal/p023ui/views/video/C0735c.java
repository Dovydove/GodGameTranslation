package net.nend.android.internal.p023ui.views.video;

import android.content.Context;
import android.view.TextureView;
import android.view.View.MeasureSpec;

/* renamed from: net.nend.android.internal.ui.views.video.c */
/* compiled from: NendAdTextureView */
public class C0735c extends TextureView {

    /* renamed from: a */
    private int f1120a = 0;

    /* renamed from: b */
    private int f1121b = 0;

    public C0735c(Context context) {
        super(context);
    }

    /* renamed from: a */
    public void mo8177a(int i, int i2) {
        this.f1120a = i;
        this.f1121b = i2;
        requestLayout();
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int ceil;
        int ceil2;
        if (this.f1120a <= 0 || this.f1121b <= 0) {
            super.onMeasure(i, i2);
            return;
        }
        int size = MeasureSpec.getSize(i);
        float f = ((float) this.f1120a) / ((float) size);
        float size2 = ((float) this.f1121b) / ((float) MeasureSpec.getSize(i2));
        if (size2 > f) {
            ceil = (int) Math.ceil((double) (((float) this.f1120a) / size2));
            ceil2 = (int) Math.ceil((double) (((float) this.f1121b) / size2));
        } else {
            ceil = (int) Math.ceil((double) (((float) this.f1120a) / f));
            ceil2 = (int) Math.ceil((double) (((float) this.f1121b) / f));
        }
        setMeasuredDimension(ceil, ceil2);
    }
}
