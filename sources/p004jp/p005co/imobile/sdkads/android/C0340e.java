package p004jp.p005co.imobile.sdkads.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/* renamed from: jp.co.imobile.sdkads.android.e */
final class C0340e extends WebView {

    /* renamed from: a */
    private Boolean f100a = Boolean.valueOf(true);

    /* renamed from: b */
    private Boolean f101b = Boolean.valueOf(false);

    /* renamed from: c */
    private Boolean f102c = Boolean.valueOf(false);

    /* renamed from: d */
    private Boolean f103d = Boolean.valueOf(false);

    /* renamed from: e */
    private ImobileSdkAdListener f104e = null;

    C0340e(Context context, Boolean bool) {
        super(context);
        this.f101b = bool;
        C0359x.m125a(null);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final Boolean mo7213a() {
        new StringBuilder("AdWebView getHasFocus : ").append(this.f100a);
        C0359x.m125a(null);
        return this.f100a;
    }

    /* renamed from: a */
    public final void mo7214a(WebViewClient webViewClient) {
        super.setWebViewClient(webViewClient);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo7215a(String str) {
        super.loadUrl(str);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo7216a(String str, String str2, String str3, String str4) {
        super.loadDataWithBaseURL(str, str2, str3, str4, null);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo7217a(ImobileSdkAdListener imobileSdkAdListener) {
        C0359x.m125a(null);
        this.f103d = Boolean.valueOf(true);
        this.f104e = imobileSdkAdListener;
    }

    public final boolean canGoBack() {
        return false;
    }

    public final boolean canGoBackOrForward(int i) {
        return false;
    }

    public final boolean canGoForward() {
        return false;
    }

    public final void clearView() {
    }

    public final Bitmap getFavicon() {
        return null;
    }

    public final String getTitle() {
        return null;
    }

    public final String getUrl() {
        return null;
    }

    public final void goBack() {
    }

    public final void goBackOrForward(int i) {
    }

    public final void goForward() {
    }

    public final void loadData(String str, String str2, String str3) {
    }

    public final void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
    }

    public final void loadUrl(String str) {
    }

    public final void onDetachedFromWindow() {
        if (!this.f101b.booleanValue() && getParent() != null && !this.f102c.booleanValue()) {
            this.f102c = Boolean.valueOf(true);
            ((ViewGroup) getParent()).removeView(this);
            removeAllViews();
            destroy();
        }
        super.onDetachedFromWindow();
    }

    public final void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        this.f100a = Boolean.valueOf(hasFocus);
        new StringBuilder("AdWebView Status change onWindowFocusChanged:").append(this.f100a);
        C0359x.m125a(null);
        if (this.f103d.booleanValue() && hasFocus && this.f104e != null) {
            C0359x.m125a(null);
            this.f104e.onDismissAdScreen();
        }
    }

    public final boolean pageDown(boolean z) {
        return false;
    }

    public final boolean pageUp(boolean z) {
        return false;
    }

    public final void reload() {
    }

    public final void setDownloadListener(DownloadListener downloadListener) {
    }

    public final void setWebChromeClient(WebChromeClient webChromeClient) {
    }

    public final void setWebViewClient(WebViewClient webViewClient) {
    }

    public final void stopLoading() {
    }
}
