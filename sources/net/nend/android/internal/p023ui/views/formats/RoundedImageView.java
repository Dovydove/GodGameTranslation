package net.nend.android.internal.p023ui.views.formats;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.widget.ImageView;

/* renamed from: net.nend.android.internal.ui.views.formats.RoundedImageView */
public class RoundedImageView extends ImageView {

    /* renamed from: a */
    Bitmap f1095a;

    /* renamed from: b */
    float f1096b;

    public RoundedImageView(Context context) {
        this(context, null);
    }

    public RoundedImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1096b = 10.0f;
    }

    public void setImageDrawable(Drawable drawable) {
        setRoundBitmap(((BitmapDrawable) drawable).getBitmap());
    }

    public void setImageResource(int i) {
        setRoundBitmap(BitmapFactory.decodeResource(getResources(), i));
    }

    public void setImageBitmap(Bitmap bitmap) {
        setRoundBitmap(bitmap);
    }

    private void setRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, (float) width, (float) height), this.f1096b, this.f1096b, new Paint(1));
        if (this.f1095a != null) {
            this.f1095a.recycle();
            this.f1095a = null;
        }
        this.f1095a = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas2 = new Canvas(this.f1095a);
        Paint paint = new Paint();
        canvas2.drawBitmap(createBitmap, 0.0f, 0.0f, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas2.drawBitmap(bitmap, new Rect(0, 0, width, height), new Rect(0, 0, width, height), paint);
        createBitmap.recycle();
        super.setImageDrawable(new BitmapDrawable(getContext().getResources(), this.f1095a));
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        super.setImageDrawable(null);
        if (VERSION.SDK_INT >= 16) {
            setBackground(null);
        } else {
            setBackgroundDrawable(null);
        }
        destroyDrawingCache();
        if (this.f1095a != null) {
            this.f1095a.recycle();
            this.f1095a = null;
        }
    }
}
