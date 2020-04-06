package net.nend.android.internal.p023ui.views.video;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.util.concurrent.BlockingQueue;
import net.nend.android.internal.p023ui.p024a.C0652b;
import net.nend.android.internal.p023ui.p024a.C0653c;
import net.nend.android.internal.p023ui.p024a.C0654d;
import net.nend.android.internal.utilities.C0757f;

/* renamed from: net.nend.android.internal.ui.views.video.b */
/* compiled from: CacheHtmlWebView */
public class C0731b extends WebView {

    /* renamed from: a */
    private static final String[] f1117a = {"android.webkit.JniUtil", "java.lang.Runtime"};
    /* access modifiers changed from: private */

    /* renamed from: b */
    public C0734b f1118b;

    /* renamed from: net.nend.android.internal.ui.views.video.b$a */
    /* compiled from: CacheHtmlWebView */
    private class C0733a extends WebViewClient {
        private C0733a() {
        }

        public void onPageFinished(WebView webView, String str) {
            if (VERSION.SDK_INT < 17) {
                webView.loadUrl("javascript:window.nendSDK.viewSource(document.documentElement.outerHTML);");
            }
            if (C0731b.this.f1118b != null) {
                C0731b.this.f1118b.mo8061a();
                C0731b.this.f1118b = null;
            }
            super.onPageFinished(webView, str);
        }
    }

    /* renamed from: net.nend.android.internal.ui.views.video.b$b */
    /* compiled from: CacheHtmlWebView */
    public interface C0734b {
        /* renamed from: a */
        void mo8061a();
    }

    @SuppressLint({"SetJavaScriptEnabled, AddJavascriptInterface"})
    public C0731b(Context context, BlockingQueue<C0654d> blockingQueue, String str) {
        super(context);
        getSettings().setJavaScriptEnabled(true);
        setWebViewClient(new C0733a());
        if (str.equals("html_on_playing")) {
            addJavascriptInterface(new C0653c(blockingQueue, str), "nendSDK");
        } else {
            addJavascriptInterface(new C0652b(blockingQueue, str), "nendSDK");
        }
        if (VERSION.SDK_INT >= 19) {
            setWebContentsDebuggingEnabled(false);
        }
    }

    /* renamed from: a */
    public void mo8173a(String str) {
        loadUrl("file://" + str);
    }

    /* renamed from: b */
    public boolean mo8174b(String str) {
        for (String contains : f1117a) {
            if (str.contains(contains)) {
                C0757f.m1222d("This html content is maybe dangerous. Disable Javascript option");
                return true;
            }
        }
        return false;
    }

    public void setWebViewClientListener(C0734b bVar) {
        this.f1118b = bVar;
    }
}
