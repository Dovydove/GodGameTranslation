package net.nend.android.internal.p023ui.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ViewSwitcher;
import java.util.concurrent.Future;
import net.nend.android.internal.C0521a;
import net.nend.android.internal.utilities.C0740a;
import net.nend.android.internal.utilities.C0740a.C0742a;
import net.nend.android.internal.utilities.C0740a.C0743b;
import net.nend.android.internal.utilities.C0740a.C0748f;
import net.nend.android.internal.utilities.C0755e;
import net.nend.android.internal.utilities.C0757f;
import net.nend.android.internal.utilities.C0758g;

/* renamed from: net.nend.android.internal.ui.views.b */
/* compiled from: NendAdViewSwitcher */
public class C0710b extends ViewSwitcher implements OnClickListener, C0743b<Bitmap> {

    /* renamed from: a */
    private static final Object[] f1059a = new Object[0];

    /* renamed from: b */
    private C0521a f1060b;

    /* renamed from: c */
    private C0713a f1061c;

    /* renamed from: d */
    private Bitmap f1062d;

    /* renamed from: e */
    private ImageView f1063e;

    /* renamed from: f */
    private C0696a f1064f;

    /* renamed from: g */
    private String f1065g = "";

    /* renamed from: h */
    private Future<Bitmap> f1066h;

    /* renamed from: net.nend.android.internal.ui.views.b$a */
    /* compiled from: NendAdViewSwitcher */
    public interface C0713a {
        void onClickAd();

        void onFailure();

        void onSuccess();

        boolean onValidation(int i, int i2);
    }

    public C0710b(Context context) {
        super(context);
        m1091a(context);
    }

    public void onClick(View view) {
        if (!TextUtils.isEmpty(this.f1065g) && mo8114a()) {
            m1104i();
            C0755e.m1198a(getContext(), this.f1065g);
        }
    }

    public String getRequestUrl() {
        return this.f1060b != null ? this.f1060b.mo7767b() : "";
    }

    /* renamed from: a */
    public Bitmap makeResponse(byte[] bArr) {
        Bitmap decodeByteArray;
        if (bArr != null) {
            try {
                synchronized (f1059a) {
                    decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
                }
                return decodeByteArray;
            } catch (OutOfMemoryError e) {
                System.gc();
                C0757f.m1215a(C0758g.ERR_HTTP_REQUEST, (Throwable) e);
            } catch (IllegalStateException e2) {
                C0757f.m1215a(C0758g.ERR_HTTP_REQUEST, (Throwable) e2);
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m1092a(Bitmap bitmap) {
        if (bitmap == null) {
            m1103h();
        } else if (m1095a(bitmap.getWidth(), bitmap.getHeight())) {
            this.f1064f.mo8084a();
            m1099d();
            this.f1062d = bitmap;
            this.f1063e.setImageBitmap(bitmap);
            setDisplayedChild(0);
            m1101f();
        }
    }

    /* renamed from: a */
    public void mo8113a(C0521a aVar, C0713a aVar2) {
        if (aVar != null) {
            m1098c();
            this.f1060b = aVar;
            this.f1061c = aVar2;
            m1091a(getContext());
            if (aVar.mo7776k()) {
                this.f1064f.mo8085a(aVar.mo7767b(), (C0703b) new C0703b() {
                    /* renamed from: a */
                    public void mo8096a() {
                        C0710b.this.setDisplayedChild(1);
                        C0710b.this.m1101f();
                    }

                    /* renamed from: b */
                    public void mo8098b() {
                        C0710b.this.m1103h();
                    }

                    /* renamed from: a */
                    public boolean mo8097a(int i, int i2) {
                        return C0710b.this.m1095a(i, i2);
                    }
                });
                return;
            }
            this.f1066h = C0740a.m1170a().mo8183a(new C0748f((C0743b<V>) this), new C0742a<Bitmap>() {
                /* renamed from: a */
                public void mo7491a(Bitmap bitmap, Exception exc) {
                    C0710b.this.m1092a(bitmap);
                }
            });
        }
    }

    /* renamed from: a */
    public boolean mo8114a() {
        if (m1105j()) {
            return false;
        }
        switch (getDisplayedChild()) {
            case 0:
                if (this.f1063e.getDrawable() == null || !(this.f1063e.getDrawable() instanceof BitmapDrawable)) {
                    return false;
                }
                return true;
            case 1:
                return this.f1064f.mo8086b();
            default:
                return false;
        }
    }

    /* renamed from: b */
    public void mo8115b() {
        this.f1061c = null;
        m1098c();
        m1100e();
    }

    /* renamed from: a */
    private void m1091a(Context context) {
        if (this.f1063e == null) {
            this.f1063e = new ImageView(context);
            this.f1063e.setScaleType(ScaleType.FIT_XY);
            addView(this.f1063e, new LayoutParams(-1, -1));
        }
        if (this.f1064f == null) {
            this.f1064f = new C0696a(context);
            addView(this.f1064f, new LayoutParams(-1, -1));
        }
    }

    /* renamed from: c */
    private void m1098c() {
        if (this.f1066h != null) {
            this.f1066h.cancel(true);
        }
        if (this.f1064f != null) {
            this.f1064f.mo8084a();
        }
    }

    /* renamed from: d */
    private void m1099d() {
        if (this.f1062d != null && !this.f1062d.isRecycled()) {
            this.f1062d.recycle();
        }
        this.f1062d = null;
        if (this.f1063e != null && this.f1063e.getDrawable() != null) {
            this.f1063e.getDrawable().setCallback(null);
            this.f1063e.setImageDrawable(null);
        }
    }

    /* renamed from: e */
    private void m1100e() {
        removeAllViews();
        m1099d();
        this.f1063e = null;
        if (this.f1064f != null) {
            this.f1064f.stopLoading();
            this.f1064f.clearCache(true);
            this.f1064f.setWebViewClient(null);
            this.f1064f.setWebChromeClient(null);
            this.f1064f.destroy();
            this.f1064f = null;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: f */
    public void m1101f() {
        if (!m1105j()) {
            this.f1065g = this.f1060b.mo7768c();
            this.f1063e.setOnClickListener(this);
            this.f1064f.setOnClickListener(this);
            m1102g();
        }
    }

    /* renamed from: g */
    private void m1102g() {
        if (this.f1061c != null) {
            this.f1061c.onSuccess();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: h */
    public void m1103h() {
        if (this.f1061c != null) {
            this.f1061c.onFailure();
        }
    }

    /* renamed from: i */
    private void m1104i() {
        if (this.f1061c != null) {
            this.f1061c.onClickAd();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public boolean m1095a(int i, int i2) {
        if (this.f1061c != null) {
            return this.f1061c.onValidation(i, i2);
        }
        return false;
    }

    /* renamed from: j */
    private boolean m1105j() {
        return this.f1063e == null || this.f1064f == null;
    }
}
