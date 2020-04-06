package net.nend.android.internal.p023ui.views.p026b;

import android.content.Context;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout.LayoutParams;
import java.io.UnsupportedEncodingException;
import net.nend.android.NendAdInterstitial.NendAdInterstitialClickType;
import net.nend.android.internal.utilities.C0740a;
import net.nend.android.internal.utilities.C0740a.C0742a;
import net.nend.android.internal.utilities.C0740a.C0743b;
import net.nend.android.internal.utilities.C0740a.C0748f;
import net.nend.android.internal.utilities.C0757f;
import net.nend.android.internal.utilities.C0758g;

/* renamed from: net.nend.android.internal.ui.views.b.b */
/* compiled from: NendAdInterstitialWebView */
public class C0718b extends WebView implements C0743b<String> {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public final C0721a f1083a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public C0722b f1084b = C0722b.INCOMPLETE;

    /* renamed from: c */
    private String f1085c = "";

    /* renamed from: net.nend.android.internal.ui.views.b.b$a */
    /* compiled from: NendAdInterstitialWebView */
    interface C0721a {
        /* renamed from: a */
        void mo8119a(NendAdInterstitialClickType nendAdInterstitialClickType, String str);

        /* renamed from: e */
        void mo8124e();
    }

    /* renamed from: net.nend.android.internal.ui.views.b.b$b */
    /* compiled from: NendAdInterstitialWebView */
    enum C0722b {
        SUCCESS,
        FAILED,
        INCOMPLETE
    }

    public C0718b(Context context, LayoutParams layoutParams, C0721a aVar) {
        super(context);
        this.f1083a = aVar;
        clearCache(false);
        setLayoutParams(layoutParams);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        setLayerType(1, null);
        getSettings().setLoadWithOverviewMode(true);
        getSettings().setUseWideViewPort(true);
        getSettings().setRenderPriority(RenderPriority.HIGH);
        getSettings().setCacheMode(2);
        setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView webView, String str) {
                super.onPageFinished(webView, str);
                C0718b.this.f1084b = C0722b.SUCCESS;
                C0718b.this.f1083a.mo8124e();
            }

            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str.startsWith("https://www.nend.net/privacy/optsdkgate")) {
                    C0718b.this.f1083a.mo8119a(NendAdInterstitialClickType.INFORMATION, str);
                } else {
                    C0718b.this.f1083a.mo8119a(NendAdInterstitialClickType.DOWNLOAD, str);
                }
                return true;
            }
        });
        setBackgroundColor(0);
    }

    /* renamed from: a */
    public void mo8136a(String str) {
        this.f1084b = C0722b.INCOMPLETE;
        this.f1085c = str;
        C0740a.m1170a().mo8183a(new C0748f((C0743b<V>) this), new C0742a<String>() {
            /* renamed from: a */
            public void mo7491a(String str, Exception exc) {
                C0718b.this.m1128b(str);
            }
        });
    }

    public C0722b getStatusCode() {
        return this.f1084b;
    }

    public String getRequestUrl() {
        return this.f1085c;
    }

    /* renamed from: a */
    public String makeResponse(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            C0757f.m1221c(C0758g.ERR_HTTP_REQUEST, e);
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m1128b(String str) {
        if (str != null) {
            loadDataWithBaseURL("http://output.nend.net", str, "text/html", "UTF-8", null);
            return;
        }
        this.f1084b = C0722b.FAILED;
        this.f1083a.mo8124e();
    }
}
