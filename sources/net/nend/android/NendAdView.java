package net.nend.android;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import net.nend.android.internal.C0521a;
import net.nend.android.internal.C0521a.C0522a;
import net.nend.android.internal.p008b.p009a.C0527a;
import net.nend.android.internal.p008b.p009a.C0532b;
import net.nend.android.internal.p008b.p009a.p010a.C0530a;
import net.nend.android.internal.p008b.p009a.p010a.C0531b;
import net.nend.android.internal.p023ui.views.C0710b;
import net.nend.android.internal.p023ui.views.C0710b.C0713a;
import net.nend.android.internal.p023ui.views.p025a.C0704a;
import net.nend.android.internal.p023ui.views.p025a.C0706b;
import net.nend.android.internal.p023ui.views.p025a.C0706b.C0709b;
import net.nend.android.internal.utilities.C0755e;
import net.nend.android.internal.utilities.C0757f;
import net.nend.android.internal.utilities.C0758g;
import net.nend.android.internal.utilities.C0761i;

public final class NendAdView extends RelativeLayout implements C0531b, C0709b, C0713a {

    /* renamed from: a */
    static final /* synthetic */ boolean f512a = (!NendAdView.class.desiredAssertionStatus());

    /* renamed from: b */
    private int f513b;

    /* renamed from: c */
    private String f514c;

    /* renamed from: d */
    private float f515d;

    /* renamed from: e */
    private C0530a f516e;

    /* renamed from: f */
    private C0532b f517f;

    /* renamed from: g */
    private NendAdListener f518g;

    /* renamed from: h */
    private RelativeLayout f519h;

    /* renamed from: i */
    private C0706b f520i;

    /* renamed from: j */
    private C0704a f521j;

    /* renamed from: k */
    private boolean f522k;

    /* renamed from: l */
    private DisplayMetrics f523l;

    /* renamed from: m */
    private NendError f524m;

    /* renamed from: n */
    private C0710b f525n;

    /* renamed from: o */
    private boolean f526o;

    /* renamed from: p */
    private boolean f527p;

    /* renamed from: q */
    private int f528q;

    /* renamed from: r */
    private int f529r;

    public enum NendError {
        AD_SIZE_TOO_LARGE(840, "Ad size will not fit on screen."),
        INVALID_RESPONSE_TYPE(841, "Response type is invalid."),
        FAILED_AD_REQUEST(842, "Failed to Ad request."),
        FAILED_AD_DOWNLOAD(843, "Failed to Ad download."),
        AD_SIZE_DIFFERENCES(844, "Ad size differences.");
        

        /* renamed from: a */
        private final int f532a;

        /* renamed from: b */
        private final String f533b;

        private NendError(int i, String str) {
            this.f532a = i;
            this.f533b = str;
        }

        public String getMessage() {
            return this.f533b;
        }

        public int getCode() {
            return this.f532a;
        }
    }

    public NendAdView(Context context, int i, String str) {
        this(context, i, str, false);
    }

    public NendAdView(Context context, int i, String str, boolean z) {
        super(context, null, 0);
        this.f515d = 1.0f;
        this.f516e = null;
        this.f517f = null;
        this.f518g = null;
        this.f519h = null;
        this.f520i = null;
        this.f521j = null;
        this.f522k = false;
        this.f528q = -1;
        this.f529r = -1;
        m405a(context, i, str, z);
    }

    public NendAdView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NendAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f515d = 1.0f;
        this.f516e = null;
        this.f517f = null;
        this.f518g = null;
        this.f519h = null;
        this.f520i = null;
        this.f521j = null;
        this.f522k = false;
        this.f528q = -1;
        this.f529r = -1;
        if (attributeSet == null) {
            throw new NullPointerException(C0758g.ERR_INVALID_ATTRIBUTE_SET.mo8198b());
        }
        TypedArray a = C0761i.m1231a(context, attributeSet, i);
        int i2 = a.getInt(C0761i.m1229a(context, "NendSpotId"), 0);
        String string = a.getString(C0761i.m1229a(context, "NendApiKey"));
        boolean z = a.getBoolean(C0761i.m1229a(context, "NendAdjustSize"), false);
        boolean z2 = a.getBoolean(C0761i.m1229a(context, "NendReloadable"), true);
        a.recycle();
        m405a(context, i2, string, z);
        if (!z2) {
            pause();
        }
        loadAd();
    }

    /* renamed from: a */
    private void m405a(Context context, int i, String str, boolean z) {
        if (i <= 0) {
            throw new IllegalArgumentException(C0758g.ERR_INVALID_SPOT_ID.mo8197a("spot id : " + i));
        } else if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(C0758g.ERR_INVALID_API_KEY.mo8197a("api key : " + str));
        } else {
            C0755e.m1197a(context);
            this.f523l = new DisplayMetrics();
            ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(this.f523l);
            this.f515d = this.f523l.density;
            this.f513b = i;
            this.f514c = str;
            this.f526o = z;
            this.f516e = new C0527a(context, i, str, this.f523l);
            this.f516e.mo7790a(this);
            this.f517f = new C0532b(this.f516e);
            this.f525n = new C0710b(getContext());
            this.f527p = true;
        }
    }

    public void loadAd() {
        m419m();
        this.f517f.mo7798a();
    }

    public void setListener(NendAdListener nendAdListener) {
        this.f518g = nendAdListener;
    }

    public void removeListener() {
        this.f518g = null;
    }

    public void resume() {
        C0757f.m1210a("resume!");
        m419m();
        this.f517f.mo7799a(true);
        if (this.f516e.mo7766a() == C0522a.WEBVIEW) {
            m410d();
        } else if (this.f516e.mo7766a() == C0522a.DYNAMICRETARGETING) {
            m411e();
        }
    }

    public void pause() {
        C0757f.m1210a("pause!");
        m419m();
        this.f517f.mo7799a(false);
        if (this.f516e.mo7766a() == C0522a.WEBVIEW || this.f516e.mo7766a() == C0522a.DYNAMICRETARGETING) {
            m417k();
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f516e == null) {
            this.f516e = new C0527a(getContext(), this.f513b, this.f514c, this.f523l);
            this.f516e.mo7790a(this);
            this.f517f = new C0532b(this.f516e);
            loadAd();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        C0757f.m1210a("onDetachedFromWindow!");
        this.f527p = true;
        m412f();
        super.onDetachedFromWindow();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            this.f517f.mo7800b(true);
        }
    }

    public void onWindowFocusChanged(boolean z) {
        C0757f.m1210a("onWindowFocusChanged!" + String.valueOf(z));
        super.onWindowFocusChanged(z);
        if (this.f517f != null) {
            this.f517f.mo7800b(z);
            if (z && this.f522k) {
                this.f522k = false;
                if (this.f518g != null) {
                    this.f518g.onDismissScreen(this);
                }
            }
        }
    }

    public void onReceiveAd() {
        C0757f.m1210a("onReceive!");
        if (!f512a && this.f516e == null) {
            throw new AssertionError();
        } else if (!m418l()) {
            this.f524m = null;
            if (this.f527p) {
                m409c();
                this.f527p = false;
            }
            switch (this.f516e.mo7766a()) {
                case ADVIEW:
                    this.f525n.mo8113a((C0521a) this.f516e, (C0713a) this);
                    return;
                case DYNAMICRETARGETING:
                    this.f517f.mo7801b();
                    m411e();
                    return;
                case WEBVIEW:
                    m410d();
                    if (this.f518g != null) {
                        this.f518g.onReceiveAd(this);
                        return;
                    }
                    return;
                default:
                    onFailedToReceiveAd(NendError.INVALID_RESPONSE_TYPE);
                    return;
            }
        }
    }

    public void onFailedToReceiveAd(NendError nendError) {
        C0757f.m1210a("onFailedToReceive!");
        if (!f512a && this.f517f == null) {
            throw new AssertionError();
        } else if (!m418l() && this.f517f != null) {
            if (!this.f517f.mo7801b()) {
                C0757f.m1210a("Failed to reload.");
            }
            if (this.f518g != null) {
                this.f524m = nendError;
                this.f518g.onFailedToReceiveAd(this);
            }
        }
    }

    /* renamed from: a */
    private boolean m406a(int i, int i2) {
        int g = this.f516e.mo7772g();
        int f = this.f516e.mo7771f();
        if (i == 320 && i2 == 48) {
            i2 = 50;
        }
        return (g == i2 && f == i) || (g * 2 == i2 && f * 2 == i);
    }

    /* renamed from: a */
    private void m404a() {
        removeAllViews();
        m417k();
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        if (this.f519h == null || this.f520i == null || !this.f520i.mo8104a()) {
            this.f519h = new RelativeLayout(getContext());
            this.f519h.addView(this.f525n, layoutParams);
            this.f520i = new C0706b(getContext(), this.f516e.mo7791l(), this.f513b, this);
            LayoutParams layoutParams2 = new LayoutParams(-2, -2);
            layoutParams2.addRule(11);
            this.f519h.addView(this.f520i, layoutParams2);
        }
        this.f520i.bringToFront();
        addView(this.f519h, layoutParams);
    }

    /* renamed from: b */
    private void m407b() {
        removeAllViews();
        m416j();
        if (this.f521j == null) {
            this.f521j = new C0704a(getContext());
        }
        LayoutParams layoutParams = new LayoutParams((int) (((float) this.f516e.mo7771f()) * this.f515d), (int) (((float) this.f516e.mo7772g()) * this.f515d));
        layoutParams.addRule(13);
        addView(this.f521j, layoutParams);
    }

    /* renamed from: c */
    private void m409c() {
        int f = this.f516e.mo7771f();
        int g = this.f516e.mo7772g();
        if (m408b(f, g)) {
            float min = Math.min(((float) Math.min(this.f523l.widthPixels, this.f523l.heightPixels)) / (320.0f * this.f515d), 1.5f);
            this.f528q = (int) ((((float) f) * this.f515d * min) + 0.5f);
            this.f529r = (int) ((((float) g) * this.f515d * min) + 0.5f);
        } else {
            this.f528q = (int) ((((float) f) * this.f515d) + 0.5f);
            this.f529r = (int) ((((float) g) * this.f515d) + 0.5f);
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams != null) {
            if (layoutParams.width != this.f528q || layoutParams.height != this.f529r) {
                layoutParams.width = this.f528q;
                layoutParams.height = this.f529r;
                super.setLayoutParams(layoutParams);
            }
        }
    }

    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams != null && this.f528q > 0 && this.f529r > 0) {
            layoutParams.width = this.f528q;
            layoutParams.height = this.f529r;
        }
        super.setLayoutParams(layoutParams);
    }

    /* renamed from: b */
    private boolean m408b(int i, int i2) {
        return this.f526o && ((320 == i && 50 == i2) || ((320 == i && 100 == i2) || ((300 == i && 100 == i2) || (300 == i && 250 == i2))));
    }

    /* renamed from: d */
    private void m410d() {
        if (f512a || this.f516e != null) {
            m407b();
            this.f521j.loadUrl(this.f516e.mo7769d());
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: e */
    private void m411e() {
        if (f512a || this.f516e != null) {
            if (this.f521j == null) {
                this.f521j = new C0704a(getContext());
            }
            this.f521j.mo8100a(this.f516e.mo7769d(), (C0713a) this);
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: f */
    private void m412f() {
        m413g();
        m414h();
        removeListener();
        m415i();
    }

    /* renamed from: g */
    private void m413g() {
        if (this.f517f != null) {
            this.f517f.mo7802c();
            this.f517f = null;
        }
    }

    /* renamed from: h */
    private void m414h() {
        if (this.f516e != null) {
            this.f516e.mo7796q();
            this.f516e = null;
        }
    }

    /* renamed from: i */
    private void m415i() {
        removeAllViews();
        m416j();
        m417k();
    }

    /* renamed from: j */
    private void m416j() {
        if (this.f519h != null) {
            this.f519h.removeAllViews();
            this.f519h = null;
        }
        if (this.f520i != null) {
            this.f520i.setImageDrawable(null);
            this.f520i.mo8105b();
            this.f520i = null;
        }
        if (this.f525n != null) {
            this.f525n.mo8115b();
        }
    }

    /* renamed from: k */
    private void m417k() {
        if (this.f521j != null) {
            this.f521j.stopLoading();
            this.f521j.getSettings().setJavaScriptEnabled(false);
            this.f521j.setWebChromeClient(null);
            this.f521j.setWebViewClient(null);
            removeView(this.f521j);
            this.f521j.removeAllViews();
            this.f521j.destroy();
            this.f521j = null;
        }
    }

    /* renamed from: l */
    private boolean m418l() {
        return this.f516e == null;
    }

    /* renamed from: m */
    private void m419m() {
        if (this.f517f == null) {
            if (this.f516e == null) {
                this.f516e = new C0527a(getContext(), this.f513b, this.f514c, this.f523l);
                this.f516e.mo7790a(this);
            }
            this.f517f = new C0532b(this.f516e);
        }
    }

    public NendError getNendError() {
        return this.f524m;
    }

    public boolean onValidation(int i, int i2) {
        if (m418l()) {
            return false;
        }
        if (m406a(i, i2)) {
            return true;
        }
        onFailedToReceiveAd(NendError.AD_SIZE_DIFFERENCES);
        return false;
    }

    public void onClickAd() {
        this.f522k = true;
        if (this.f518g != null) {
            this.f518g.onClick(this);
        }
    }

    public void onSuccess() {
        if (this.f517f != null && this.f516e != null) {
            if (this.f516e.mo7766a() == C0522a.DYNAMICRETARGETING) {
                m407b();
            } else {
                m404a();
            }
            this.f517f.mo7801b();
            if (this.f518g != null) {
                this.f518g.onReceiveAd(this);
            }
        }
    }

    public void onFailure() {
        onFailedToReceiveAd(NendError.FAILED_AD_DOWNLOAD);
    }

    public void onInformationButtonClick() {
        this.f522k = true;
        if (this.f518g != null && (this.f518g instanceof NendAdInformationListener)) {
            ((NendAdInformationListener) this.f518g).onInformationButtonClick(this);
        }
    }
}
