package net.nend.android.internal.p023ui.views.formats;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.support.p000v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.TextView;

/* renamed from: net.nend.android.internal.ui.views.formats.FontFitTextView */
public class FontFitTextView extends TextView {

    /* renamed from: a */
    private FontMetrics f1092a;

    /* renamed from: b */
    private int f1093b;

    public FontFitTextView(Context context) {
        super(context);
        m1135a();
    }

    public FontFitTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m1135a();
    }

    /* renamed from: a */
    private void m1135a() {
        setShadowLayer(8.0f, 1.0f, 1.0f, ViewCompat.MEASURED_STATE_MASK);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int i5 = i3 - i;
        if (this.f1093b != i5) {
            m1136a(m1137b(getWidth()), this.f1093b != 0);
            this.f1093b = i5;
        }
    }

    /* renamed from: b */
    private float m1137b(int i) {
        float f = 2.0f;
        String str = "１２３４５６７８";
        Paint paint = new Paint();
        float textSize = getTextSize();
        paint.setTextSize(textSize);
        float measureText = paint.measureText(str);
        if (((float) i) <= measureText) {
            while (true) {
                float f2 = measureText;
                float f3 = textSize;
                if (((float) i) >= f2) {
                    f = f3;
                    break;
                } else if (2.0f >= f3) {
                    break;
                } else {
                    textSize = f3 - 1.0f;
                    paint.setTextSize(textSize);
                    measureText = paint.measureText(str);
                }
            }
        } else {
            f = textSize;
            while (((float) i) > measureText) {
                f += 1.0f;
                paint.setTextSize(f);
                measureText = paint.measureText(str);
            }
        }
        this.f1092a = paint.getFontMetrics();
        return f;
    }

    /* renamed from: a */
    public void mo8141a(int i) {
        m1136a(m1137b(i), false);
    }

    /* renamed from: a */
    private void m1136a(float f, boolean z) {
        super.setTextSize(0, f);
        float f2 = (this.f1092a.top * -1.0f) + this.f1092a.bottom;
        if (z || ((float) getHeight()) < f2) {
            setHeight((int) f2);
            postDelayed(new Runnable() {
                public void run() {
                    FontFitTextView.this.requestLayout();
                    FontFitTextView.this.invalidate();
                }
            }, 10);
        }
    }
}
