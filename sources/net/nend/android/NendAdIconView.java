package net.nend.android;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.os.Handler;
import android.support.p000v4.view.ViewCompat;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;
import net.nend.android.NendAdView.NendError;
import net.nend.android.internal.C0521a;
import net.nend.android.internal.p023ui.views.C0710b;
import net.nend.android.internal.p023ui.views.C0710b.C0713a;
import net.nend.android.internal.utilities.C0740a;
import net.nend.android.internal.utilities.C0740a.C0742a;
import net.nend.android.internal.utilities.C0740a.C0745d;
import net.nend.android.internal.utilities.C0755e;
import net.nend.android.internal.utilities.C0757f;
import net.nend.android.internal.utilities.C0761i;

public class NendAdIconView extends LinearLayout implements C0713a {

    /* renamed from: a */
    private float f293a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public int f294b;

    /* renamed from: c */
    private boolean f295c;

    /* renamed from: d */
    private boolean f296d;

    /* renamed from: e */
    private int f297e;

    /* renamed from: f */
    private int f298f;

    /* renamed from: g */
    private Bitmap f299g;

    /* renamed from: h */
    private Rect f300h;

    /* renamed from: i */
    private Rect f301i;

    /* renamed from: j */
    private int f302j;

    /* renamed from: k */
    private int f303k;
    /* access modifiers changed from: private */

    /* renamed from: l */
    public int f304l;
    /* access modifiers changed from: private */

    /* renamed from: m */
    public Handler f305m;

    /* renamed from: n */
    private float f306n;

    /* renamed from: o */
    private C0521a f307o;
    /* access modifiers changed from: private */

    /* renamed from: p */
    public String f308p;

    /* renamed from: q */
    private C0466a f309q;

    /* renamed from: r */
    private C0710b f310r;
    /* access modifiers changed from: private */

    /* renamed from: s */
    public TextView f311s;

    /* renamed from: t */
    private Runnable f312t;

    /* renamed from: net.nend.android.NendAdIconView$a */
    interface C0466a {
        void onClick(View view);

        void onClickInformation(View view);

        void onFailedToReceive(NendIconError nendIconError);

        void onReceive(View view);

        void onWindowFocusChanged(boolean z);
    }

    public NendAdIconView(Context context) {
        super(context);
        this.f295c = true;
        this.f296d = true;
        this.f297e = ViewCompat.MEASURED_STATE_MASK;
        this.f299g = null;
        this.f305m = new Handler();
        this.f312t = new Runnable() {
            public void run() {
                NendAdIconView.this.m185a(NendAdIconView.this.f311s);
            }
        };
        m184a(context);
    }

    public NendAdIconView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NendAdIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.f295c = true;
        this.f296d = true;
        this.f297e = ViewCompat.MEASURED_STATE_MASK;
        this.f299g = null;
        this.f305m = new Handler();
        this.f312t = new Runnable() {
            public void run() {
                NendAdIconView.this.m185a(NendAdIconView.this.f311s);
            }
        };
        TypedArray a = C0761i.m1231a(context, attributeSet, i);
        this.f297e = a.getColor(C0761i.m1229a(context, "NendTitleColor"), ViewCompat.MEASURED_STATE_MASK);
        this.f295c = a.getBoolean(C0761i.m1229a(context, "NendTitleVisible"), true);
        this.f296d = a.getBoolean(C0761i.m1229a(context, "NendIconSpaceEnabled"), true);
        a.recycle();
        m184a(context);
    }

    /* renamed from: a */
    private void m184a(Context context) {
        setOrientation(1);
        this.f306n = context.getResources().getDisplayMetrics().density;
        this.f293a = 3.0f * this.f306n;
        this.f300h = new Rect();
        this.f301i = new Rect();
        this.f299g = C0755e.m1202c(context, "nend_information_icon.png");
        this.f310r = new C0710b(context);
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.gravity = 1;
        addView(this.f310r, layoutParams);
        this.f311s = new TextView(context);
        this.f311s.setTextColor(this.f297e);
        this.f311s.setLines(1);
        this.f311s.setGravity(17);
        LayoutParams layoutParams2 = new LayoutParams(-2, -2);
        layoutParams2.gravity = 1;
        addView(this.f311s, layoutParams2);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo7494a(C0521a aVar, int i) {
        if (aVar == null) {
            m197f();
            return;
        }
        this.f307o = aVar;
        this.f308p = "https://www.nend.net/privacy/optsdkgate?uid=" + C0755e.m1201b(getContext()) + "&spot=" + i;
        this.f310r.mo8113a(aVar, (C0713a) this);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo7493a() {
        this.f309q = null;
        this.f310r.mo8115b();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        super.onMeasure(i, i2);
        int i4 = (int) (57.0f * this.f306n);
        int i5 = (int) (75.0f * this.f306n);
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i2);
        if (1073741824 == mode && 1073741824 == mode2 && size != size2) {
            size2 = Math.min(size, size2);
            size = size2;
        }
        if (1073741824 != mode) {
            size = this.f296d ? i5 : i4;
        }
        if (1073741824 == mode2) {
            if (this.f296d || !this.f295c) {
                i3 = size2;
            } else {
                i3 = (int) (((((float) size2) / ((float) i5)) * ((float) (i5 - i4))) + ((float) size2) + 0.5f);
            }
        } else if (this.f296d || (!this.f296d && this.f295c)) {
            i3 = i5;
        } else {
            i3 = i4;
        }
        setMeasuredDimension(size, i3);
        if (1073741824 == mode || 1073741824 == mode2) {
            if (this.f296d) {
                i4 = (int) (((((float) size) / ((float) i5)) * ((float) i4)) + 0.5f);
            } else {
                i4 = size;
            }
        }
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(i4, 1073741824);
        this.f310r.measure(makeMeasureSpec, makeMeasureSpec);
        if (!this.f296d) {
            size = i4;
        }
        this.f311s.measure(MeasureSpec.makeMeasureSpec(size, 1073741824), MeasureSpec.makeMeasureSpec(i3 - i4, 1073741824));
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        int i5 = i3 - i;
        int width = this.f310r.getWidth();
        this.f298f = (i5 - width) / 2;
        this.f302j = (int) ((((float) width) / (57.0f * this.f306n)) * 12.0f * this.f306n);
        this.f303k = this.f298f + width;
        this.f294b = this.f299g.getWidth() - this.f299g.getHeight();
        this.f301i.top = 0;
        this.f301i.left = 0;
        this.f301i.right = this.f299g.getHeight();
        this.f301i.bottom = this.f299g.getHeight();
        this.f300h.top = 0;
        this.f300h.left = this.f303k - this.f302j;
        this.f300h.right = this.f303k;
        this.f300h.bottom = this.f302j;
        if (z) {
            m185a(this.f311s);
        }
    }

    /* access modifiers changed from: protected */
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.f310r.mo8114a() && this.f299g != null) {
            this.f301i.right = this.f299g.getHeight() + this.f304l;
            this.f300h.left = (int) (((float) (this.f303k - this.f302j)) - ((((float) this.f304l) * this.f306n) / 2.0f));
            canvas.drawBitmap(this.f299g, this.f301i, this.f300h, null);
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        switch (motionEvent.getAction()) {
            case 1:
                if (this.f310r.mo8114a() && this.f299g != null && x > ((float) this.f300h.left) - this.f293a && x < ((float) this.f300h.right) && y > ((float) this.f300h.top) && y < ((float) this.f300h.bottom) + this.f293a) {
                    if (this.f304l >= this.f294b) {
                        C0740a.m1170a().mo8183a(new C0745d(getContext()), new C0742a<String>() {
                            /* renamed from: a */
                            public void mo7491a(String str, Exception exc) {
                                String str2 = NendAdIconView.this.f308p + "&gaid=" + str;
                                NendAdIconView.this.m198g();
                                C0755e.m1198a(NendAdIconView.this.getContext(), str2);
                            }
                        });
                    } else {
                        m189b();
                    }
                    return true;
                }
        }
        return false;
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.f309q != null) {
            this.f309q.onWindowFocusChanged(z);
        }
        if (!z) {
            m193d();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mo7493a();
    }

    public void setTitleVisible(boolean z) {
        if (this.f295c != z) {
            this.f295c = z;
            requestLayout();
            if (z && !TextUtils.isEmpty(this.f311s.getText())) {
                post(this.f312t);
            }
            this.f311s.setVisibility(z ? 0 : 4);
            invalidate();
        }
    }

    public void setTitleColor(int i) {
        if (this.f297e != i) {
            this.f297e = i;
            this.f311s.setTextColor(i);
            invalidate();
        }
    }

    public void setIconSpaceEnabled(boolean z) {
        if (this.f296d != z) {
            this.f296d = z;
            requestLayout();
            invalidate();
        }
    }

    /* access modifiers changed from: 0000 */
    public void setListner(C0466a aVar) {
        this.f309q = aVar;
    }

    /* renamed from: b */
    private void m189b() {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            public void run() {
                NendAdIconView.this.f305m.post(new Runnable() {
                    public void run() {
                        if (NendAdIconView.this.f304l <= NendAdIconView.this.f294b) {
                            NendAdIconView.this.f304l = NendAdIconView.this.f304l + 1;
                            NendAdIconView.this.invalidate();
                            return;
                        }
                        NendAdIconView.this.f304l = NendAdIconView.this.f294b;
                        NendAdIconView.this.invalidate();
                        C04613.this.cancel();
                        NendAdIconView.this.m190c();
                    }
                });
            }
        }, 0, 4);
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public void m190c() {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (NendAdIconView.this.f304l >= 0) {
                    NendAdIconView.this.f305m.post(new Runnable() {
                        public void run() {
                            NendAdIconView.this.f304l = NendAdIconView.this.f304l - 1;
                            NendAdIconView.this.invalidate();
                        }
                    });
                    return;
                }
                NendAdIconView.this.m193d();
                cancel();
            }
        }, 1000, 4);
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public void m193d() {
        this.f305m.post(new Runnable() {
            public void run() {
                NendAdIconView.this.f304l = 0;
                NendAdIconView.this.invalidate();
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m185a(TextView textView) {
        if (getWidth() != 0 && !TextUtils.isEmpty(textView.getText())) {
            float b = m187b(textView);
            if (0.0f != b) {
                textView.setTextSize(0, (float) ((int) b));
                return;
            }
            textView.setTextSize(0, 6.0f);
            textView.setText(TextUtils.ellipsize(textView.getText(), textView.getPaint(), (float) getWidth(), TruncateAt.END).toString());
        }
    }

    /* renamed from: b */
    private float m187b(TextView textView) {
        String charSequence = textView.getText().toString();
        Paint paint = new Paint(textView.getPaint());
        paint.setTextSize(10.0f * this.f306n);
        int width = textView.getWidth();
        int height = textView.getHeight();
        while (true) {
            FontMetrics fontMetrics = paint.getFontMetrics();
            if (((float) width) > paint.measureText(charSequence) && ((float) height) > Math.abs(fontMetrics.ascent - fontMetrics.descent)) {
                return paint.getTextSize();
            }
            float textSize = paint.getTextSize() - 1.0f;
            if (6.0f * this.f306n > textSize) {
                return 0.0f;
            }
            paint.setTextSize(textSize);
        }
    }

    /* renamed from: e */
    private void m195e() {
        if (this.f309q != null) {
            this.f309q.onReceive(this);
        }
    }

    /* renamed from: f */
    private void m197f() {
        C0757f.m1210a("onFailedToImageDownload!");
        if (this.f309q != null) {
            NendIconError nendIconError = new NendIconError();
            nendIconError.mo7743a(this);
            nendIconError.setErrorType(1);
            nendIconError.setNendError(NendError.FAILED_AD_DOWNLOAD);
            this.f309q.onFailedToReceive(nendIconError);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: g */
    public void m198g() {
        if (this.f309q != null) {
            this.f309q.onClickInformation(this);
        }
    }

    public boolean onValidation(int i, int i2) {
        return true;
    }

    public void onClickAd() {
        if (this.f309q != null) {
            this.f309q.onClick(this);
        }
    }

    public void onSuccess() {
        this.f311s.setText(this.f307o.mo7770e());
        m185a(this.f311s);
        this.f311s.setVisibility(this.f295c ? 0 : 4);
        postInvalidate();
        m195e();
    }

    public void onFailure() {
        m197f();
    }
}
