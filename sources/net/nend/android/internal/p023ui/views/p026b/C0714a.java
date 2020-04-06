package net.nend.android.internal.p023ui.views.p026b;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import net.nend.android.NendAdInterstitial.NendAdInterstitialClickType;
import net.nend.android.NendAdInterstitial.NendAdInterstitialShowResult;
import net.nend.android.NendAdInterstitial.NendAdInterstitialStatusCode;
import net.nend.android.internal.p013c.p017d.C0577b;
import net.nend.android.internal.utilities.C0755e;
import net.nend.android.internal.utilities.C0758g;

/* renamed from: net.nend.android.internal.ui.views.b.a */
/* compiled from: NendAdInterstitialView */
public class C0714a extends RelativeLayout implements OnClickListener, C0721a {

    /* renamed from: a */
    static final /* synthetic */ boolean f1069a = (!C0714a.class.desiredAssertionStatus());

    /* renamed from: b */
    private C0717c f1070b;

    /* renamed from: c */
    private C0716b f1071c;

    /* renamed from: d */
    private C0715a f1072d;

    /* renamed from: e */
    private C0718b f1073e;

    /* renamed from: f */
    private C0718b f1074f;

    /* renamed from: g */
    private ImageView f1075g;

    /* renamed from: h */
    private RelativeLayout f1076h;

    /* renamed from: i */
    private LayoutParams f1077i;

    /* renamed from: j */
    private LayoutParams f1078j;

    /* renamed from: k */
    private LayoutParams f1079k;

    /* renamed from: l */
    private LayoutParams f1080l;

    /* renamed from: m */
    private boolean f1081m = false;

    /* renamed from: n */
    private NendAdInterstitialClickType f1082n = null;

    /* renamed from: net.nend.android.internal.ui.views.b.a$a */
    /* compiled from: NendAdInterstitialView */
    public interface C0715a {
        /* renamed from: a */
        void mo8052a();
    }

    /* renamed from: net.nend.android.internal.ui.views.b.a$b */
    /* compiled from: NendAdInterstitialView */
    public interface C0716b {
        /* renamed from: a */
        void mo7525a(NendAdInterstitialStatusCode nendAdInterstitialStatusCode);
    }

    /* renamed from: net.nend.android.internal.ui.views.b.a$c */
    /* compiled from: NendAdInterstitialView */
    public interface C0717c {
        /* renamed from: a */
        void mo7524a(NendAdInterstitialClickType nendAdInterstitialClickType);
    }

    public C0714a(Context context, C0577b bVar) {
        super(context);
        float f = getResources().getDisplayMetrics().density;
        int f2 = (int) (((float) bVar.mo7898f()) * f);
        int g = (int) (((float) bVar.mo7899g()) * f);
        int h = (int) (((float) bVar.mo7900h()) * f);
        int i = (int) (f * ((float) bVar.mo7901i()));
        if (f2 == 0 || g == 0 || h == 0 || i == 0) {
            throw new IllegalArgumentException(C0758g.ERR_INVALID_RESPONSE.mo8198b());
        }
        LayoutParams layoutParams = new LayoutParams(f2, g);
        layoutParams.addRule(13);
        this.f1073e = new C0718b(context, layoutParams, this);
        LayoutParams layoutParams2 = new LayoutParams(h, i);
        layoutParams2.addRule(13);
        this.f1074f = new C0718b(context, layoutParams2, this);
        this.f1079k = new LayoutParams(-2, -2);
        this.f1079k.addRule(11);
        this.f1080l = new LayoutParams(-2, -2);
        this.f1080l.addRule(11);
        Bitmap b = C0755e.m1200b(getContext(), "nend_button_cancel.png");
        this.f1075g = new ImageView(getContext());
        this.f1075g.setImageBitmap(b);
        this.f1075g.setOnClickListener(this);
        if (f1069a || b != null) {
            int width = (b.getWidth() * 2) / 3;
            this.f1077i = new LayoutParams(f2 + width, g + width);
            this.f1077i.addRule(13);
            this.f1078j = new LayoutParams((b.getWidth() * 2) + h, i);
            this.f1078j.addRule(13);
            this.f1076h = new RelativeLayout(getContext());
            if (bVar.mo7896d() == 0 || bVar.mo7896d() == 2) {
                setFocusable(true);
                setFocusableInTouchMode(true);
                setOnClickListener(this);
            }
            this.f1076h.addView(this.f1073e, 0);
            this.f1076h.addView(this.f1074f, 1);
            this.f1076h.addView(this.f1075g, 2);
            addView(this.f1076h);
            return;
        }
        throw new AssertionError();
    }

    public void onClick(View view) {
        this.f1082n = NendAdInterstitialClickType.CLOSE;
        mo8120a();
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setOrientation(configuration.orientation);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (!z) {
            if (this.f1082n == null) {
                this.f1082n = NendAdInterstitialClickType.CLOSE;
                mo8120a();
            } else if (this.f1082n == NendAdInterstitialClickType.DOWNLOAD || this.f1082n == NendAdInterstitialClickType.INFORMATION) {
                mo8120a();
            }
            if (this.f1070b != null) {
                this.f1070b.mo7524a(this.f1082n);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f1081m = true;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.f1081m = false;
    }

    public void setOrientation(int i) {
        LayoutParams layoutParams;
        LayoutParams layoutParams2;
        if (i == 1) {
            layoutParams = this.f1077i;
            layoutParams2 = this.f1079k;
            this.f1074f.setVisibility(8);
            this.f1073e.setVisibility(0);
        } else {
            layoutParams = this.f1078j;
            layoutParams2 = this.f1080l;
            this.f1073e.setVisibility(8);
            this.f1074f.setVisibility(0);
        }
        this.f1075g.setLayoutParams(layoutParams2);
        this.f1076h.setLayoutParams(layoutParams);
        this.f1076h.invalidate();
    }

    /* renamed from: a */
    public boolean mo8120a() {
        if (this.f1072d == null) {
            return false;
        }
        this.f1072d.mo8052a();
        return true;
    }

    /* renamed from: b */
    public boolean mo8121b() {
        return this.f1081m;
    }

    /* renamed from: a */
    public void mo8118a(String str) {
        this.f1073e.mo8136a(str + 1);
        this.f1074f.mo8136a(str + 2);
    }

    /* renamed from: c */
    public boolean mo8122c() {
        int i = getResources().getConfiguration().orientation;
        if (i == 1 && this.f1073e.getStatusCode() == C0722b.SUCCESS) {
            return true;
        }
        if (i == 2 && this.f1074f.getStatusCode() == C0722b.SUCCESS) {
            return true;
        }
        return false;
    }

    public NendAdInterstitialShowResult getStatus() {
        if (this.f1073e.getStatusCode() == C0722b.FAILED || this.f1074f.getStatusCode() == C0722b.FAILED) {
            return NendAdInterstitialShowResult.AD_DOWNLOAD_INCOMPLETE;
        }
        return NendAdInterstitialShowResult.AD_LOAD_INCOMPLETE;
    }

    public void setOnClickListener(C0717c cVar) {
        this.f1070b = cVar;
    }

    public void setOnCompletionListener(C0716b bVar) {
        this.f1071c = bVar;
    }

    public void setDismissDelegate(C0715a aVar) {
        this.f1072d = aVar;
    }

    /* renamed from: d */
    public void mo8123d() {
        this.f1082n = NendAdInterstitialClickType.CLOSE;
    }

    /* renamed from: e */
    public void mo8124e() {
        if (this.f1073e.getStatusCode() != C0722b.INCOMPLETE && this.f1074f.getStatusCode() != C0722b.INCOMPLETE && this.f1071c != null) {
            if (this.f1073e.getStatusCode() == C0722b.SUCCESS && this.f1074f.getStatusCode() == C0722b.SUCCESS) {
                this.f1071c.mo7525a(NendAdInterstitialStatusCode.SUCCESS);
            } else {
                this.f1071c.mo7525a(NendAdInterstitialStatusCode.FAILED_AD_DOWNLOAD);
            }
        }
    }

    /* renamed from: a */
    public void mo8119a(NendAdInterstitialClickType nendAdInterstitialClickType, String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f1082n = nendAdInterstitialClickType;
            C0755e.m1198a(getContext(), str);
        }
    }
}
