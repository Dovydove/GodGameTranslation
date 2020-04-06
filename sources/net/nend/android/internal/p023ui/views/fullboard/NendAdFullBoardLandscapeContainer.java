package net.nend.android.internal.p023ui.views.fullboard;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import android.widget.ImageView;
import net.nend.android.internal.utilities.C0761i;

/* renamed from: net.nend.android.internal.ui.views.fullboard.NendAdFullBoardLandscapeContainer */
public class NendAdFullBoardLandscapeContainer extends FrameLayout {
    public NendAdFullBoardLandscapeContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        ImageView imageView = (ImageView) getChildAt(0);
        Drawable drawable = imageView.getDrawable();
        if (drawable == null) {
            super.onMeasure(i, i2);
            return;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        float min = Math.min(((float) size) / ((float) intrinsicWidth), ((float) size2) / ((float) intrinsicHeight));
        int i3 = (int) ((((float) intrinsicWidth) * min) + 0.5f);
        int i4 = (int) ((min * ((float) intrinsicHeight)) + 0.5f);
        setMeasuredDimension(i3, i4);
        imageView.measure(MeasureSpec.makeMeasureSpec(i3, 1073741824), MeasureSpec.makeMeasureSpec(i4, 1073741824));
        View childAt = getChildAt(1);
        childAt.measure(MeasureSpec.makeMeasureSpec(i3, 1073741824), MeasureSpec.makeMeasureSpec(getResources().getDimensionPixelSize(C0761i.m1237f(getContext(), "nend_full_board_ad_logo_size")) + childAt.getPaddingTop() + childAt.getPaddingBottom(), 1073741824));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        View childAt = getChildAt(0);
        childAt.layout(0, 0, childAt.getMeasuredWidth(), childAt.getMeasuredHeight());
        View childAt2 = getChildAt(1);
        childAt2.layout(0, (i4 - i2) - childAt2.getMeasuredHeight(), childAt2.getMeasuredWidth(), i4);
    }
}
