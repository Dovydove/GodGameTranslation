package net.nend.android.internal.p023ui.views.p025a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.webkit.WebView;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.Future;
import net.nend.android.internal.p023ui.views.C0710b.C0713a;
import net.nend.android.internal.utilities.C0740a;
import net.nend.android.internal.utilities.C0740a.C0742a;
import net.nend.android.internal.utilities.C0740a.C0743b;
import net.nend.android.internal.utilities.C0740a.C0748f;
import net.nend.android.internal.utilities.C0757f;
import net.nend.android.internal.utilities.C0758g;

/* renamed from: net.nend.android.internal.ui.views.a.a */
/* compiled from: NendAdWebView */
public final class C0704a extends WebView implements C0743b<String> {

    /* renamed from: a */
    private String f1047a;

    /* renamed from: b */
    private C0713a f1048b;

    /* renamed from: c */
    private Future<String> f1049c;

    @SuppressLint({"SetJavaScriptEnabled"})
    public C0704a(Context context) {
        super(context);
        getSettings().setJavaScriptEnabled(true);
        setBackgroundColor(0);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        try {
            super.onDetachedFromWindow();
        } catch (IllegalArgumentException e) {
            C0757f.m1211a("AndroidSDK internal error", (Throwable) e);
        }
    }

    /* renamed from: a */
    public void mo8100a(String str, C0713a aVar) {
        this.f1047a = str;
        this.f1048b = aVar;
        this.f1049c = C0740a.m1170a().mo8183a(new C0748f((C0743b<V>) this), new C0742a<String>() {
            /* renamed from: a */
            public void mo7491a(String str, Exception exc) {
                C0704a.this.m1075a(str);
            }
        });
    }

    public String getRequestUrl() {
        return this.f1047a;
    }

    /* renamed from: a */
    public String makeResponse(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            C0757f.m1221c(C0758g.ERR_FAILED_TO_PARSE, e);
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m1075a(String str) {
        if (str != null) {
            loadDataWithBaseURL(this.f1047a, str, "text/html", "UTF-8", null);
            if (this.f1048b != null) {
                this.f1048b.onSuccess();
            }
        } else if (this.f1048b != null) {
            this.f1048b.onFailure();
        }
    }

    public void destroy() {
        if (this.f1049c != null) {
            this.f1049c.cancel(true);
        }
        super.destroy();
    }
}
