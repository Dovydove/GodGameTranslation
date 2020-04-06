package net.nend.android.internal.p023ui.p024a;

import android.webkit.JavascriptInterface;
import java.util.concurrent.BlockingQueue;
import net.nend.android.internal.p023ui.p024a.C0654d.C0655a;

/* renamed from: net.nend.android.internal.ui.a.b */
/* compiled from: EndCardJavascriptInterface */
public class C0652b extends C0651a {
    @JavascriptInterface
    public /* bridge */ /* synthetic */ void viewSource(String str) {
        super.viewSource(str);
    }

    public C0652b(BlockingQueue<C0654d> blockingQueue, String str) {
        super(blockingQueue, str);
    }

    @JavascriptInterface
    public void onClickAd() {
        this.f911a.add(new C0654d(C0655a.CLICK_AD, this.f912b));
    }

    @JavascriptInterface
    public void onClickInformation() {
        this.f911a.add(C0654d.f913a);
    }

    @JavascriptInterface
    public void onClickClose() {
        this.f911a.add(C0654d.f914b);
    }

    @JavascriptInterface
    public void isVersion402Later() {
    }
}
