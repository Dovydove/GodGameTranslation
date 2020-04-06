package net.nend.android.internal.p023ui.p024a;

import android.webkit.JavascriptInterface;
import java.util.concurrent.BlockingQueue;
import net.nend.android.internal.p023ui.p024a.C0654d.C0655a;

/* renamed from: net.nend.android.internal.ui.a.c */
/* compiled from: HtmlOnPlayingJavascriptInterface */
public class C0653c extends C0651a {
    @JavascriptInterface
    public /* bridge */ /* synthetic */ void viewSource(String str) {
        super.viewSource(str);
    }

    public C0653c(BlockingQueue<C0654d> blockingQueue, String str) {
        super(blockingQueue, str);
    }

    @JavascriptInterface
    public void setVideoRect(String str) {
        this.f911a.add(new C0654d(C0655a.VIDEO_RECT, this.f912b, str));
    }

    @JavascriptInterface
    public void onClickAd() {
        this.f911a.add(new C0654d(C0655a.CLICK_AD, this.f912b));
    }
}
