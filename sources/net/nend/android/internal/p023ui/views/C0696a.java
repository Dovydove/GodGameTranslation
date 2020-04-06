package net.nend.android.internal.p023ui.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.concurrent.Future;
import net.nend.android.internal.utilities.C0740a;
import net.nend.android.internal.utilities.C0740a.C0742a;
import net.nend.android.internal.utilities.C0740a.C0743b;
import net.nend.android.internal.utilities.C0740a.C0748f;
import net.nend.android.internal.utilities.C0757f;

/* renamed from: net.nend.android.internal.ui.views.a */
/* compiled from: NendAdAnimationWebView */
public class C0696a extends WebView {

    /* renamed from: a */
    private Future<C0702a> f1030a;

    /* renamed from: b */
    private boolean f1031b;

    /* renamed from: c */
    private boolean f1032c;

    /* renamed from: d */
    private boolean f1033d;

    /* renamed from: e */
    private OnClickListener f1034e;

    /* renamed from: f */
    private C0703b f1035f;

    /* renamed from: g */
    private String f1036g;

    /* renamed from: h */
    private OnPreDrawListener f1037h;

    /* renamed from: net.nend.android.internal.ui.views.a$a */
    /* compiled from: NendAdAnimationWebView */
    private static class C0702a {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public final byte[] f1044a;
        /* access modifiers changed from: private */

        /* renamed from: b */
        public final int f1045b;
        /* access modifiers changed from: private */

        /* renamed from: c */
        public final int f1046c;

        private C0702a(byte[] bArr, int i, int i2) {
            this.f1044a = bArr;
            this.f1045b = i;
            this.f1046c = i2;
        }

        /* access modifiers changed from: private */
        /* renamed from: a */
        public boolean m1067a() {
            return this.f1044a != null && this.f1044a.length > 0;
        }
    }

    /* renamed from: net.nend.android.internal.ui.views.a$b */
    /* compiled from: NendAdAnimationWebView */
    interface C0703b {
        /* renamed from: a */
        void mo8096a();

        /* renamed from: a */
        boolean mo8097a(int i, int i2);

        /* renamed from: b */
        void mo8098b();
    }

    C0696a(Context context) {
        super(context);
        this.f1031b = false;
        this.f1032c = false;
        this.f1033d = false;
        this.f1034e = null;
        this.f1035f = null;
        this.f1036g = "";
        this.f1037h = new OnPreDrawListener() {
            public boolean onPreDraw() {
                if (C0696a.this.isShown()) {
                    C0696a.this.m1060e();
                    ViewTreeObserver viewTreeObserver = C0696a.this.getViewTreeObserver();
                    if (viewTreeObserver.isAlive()) {
                        viewTreeObserver.removeOnPreDrawListener(this);
                    }
                }
                return true;
            }
        };
        this.f1032c = false;
        this.f1033d = false;
        setBackgroundColor(0);
        setLayerType(1, null);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView webView, int i, String str, String str2) {
                C0696a.this.m1059d();
            }
        });
        getSettings().setSupportZoom(false);
        getSettings().setBuiltInZoomControls(false);
        getSettings().setUseWideViewPort(false);
        getSettings().setLoadWithOverviewMode(false);
    }

    /* renamed from: a */
    private static String m1052a(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<style type='text/css'>");
        sb.append("body{margin: auto auto} img{max-width: 100%; max-height: 100%;}");
        sb.append("</style>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<img src=\"" + str + "\" width=\"100%\" height=\"100%\" />");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        try {
            super.onDetachedFromWindow();
        } catch (IllegalArgumentException e) {
            C0757f.m1211a("AndroidSDK internal error", (Throwable) e);
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.f1031b = true;
                return true;
            case 1:
                if (this.f1031b) {
                    if (this.f1034e != null) {
                        this.f1034e.onClick(this);
                    }
                    this.f1031b = false;
                    return true;
                }
                break;
            case 3:
                this.f1031b = false;
                break;
        }
        return false;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.f1034e = onClickListener;
    }

    public void destroy() {
        super.destroy();
        this.f1033d = true;
    }

    /* renamed from: a */
    public void mo8085a(final String str, C0703b bVar) {
        this.f1035f = bVar;
        this.f1030a = C0740a.m1170a().mo8183a(new C0748f((C0743b<V>) new C0743b<C0702a>() {
            public String getRequestUrl() {
                return str;
            }

            /* renamed from: a */
            public C0702a makeResponse(byte[] bArr) {
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
                return new C0702a(bArr, options.outWidth, options.outHeight);
            }
        }), new C0742a<C0702a>() {
            /* renamed from: a */
            public void mo7491a(C0702a aVar, Exception exc) {
                C0696a.this.m1053a(aVar);
            }
        });
    }

    /* renamed from: a */
    public void mo8084a() {
        this.f1035f = null;
        if (this.f1030a != null) {
            this.f1030a.cancel(true);
        }
    }

    /* renamed from: b */
    public boolean mo8086b() {
        return this.f1032c;
    }

    /* renamed from: c */
    private void m1058c() {
        if (this.f1035f != null) {
            this.f1035f.mo8096a();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public void m1059d() {
        if (this.f1035f != null) {
            this.f1035f.mo8098b();
        }
    }

    /* renamed from: a */
    private boolean m1056a(int i, int i2) {
        if (this.f1035f != null) {
            return this.f1035f.mo8097a(i, i2);
        }
        return false;
    }

    /* access modifiers changed from: private */
    /* renamed from: e */
    public void m1060e() {
        if (!this.f1033d) {
            loadDataWithBaseURL(null, this.f1036g, "text/html", "utf-8", null);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m1053a(C0702a aVar) {
        if (aVar == null || !aVar.m1067a()) {
            m1059d();
        } else if (m1056a(aVar.f1045b, aVar.f1046c)) {
            if (this.f1033d) {
                m1059d();
                return;
            }
            this.f1032c = true;
            m1058c();
            this.f1036g = m1052a(String.format("data:image/gif;base64,%s", new Object[]{Base64.encodeToString(aVar.f1044a, 2)}));
            if (isShown()) {
                post(new Runnable() {
                    public void run() {
                        C0696a.this.m1060e();
                    }
                });
                return;
            }
            ViewTreeObserver viewTreeObserver = getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnPreDrawListener(this.f1037h);
            }
        }
    }
}
