package net.nend.android.internal.p023ui.p024a;

import android.webkit.JavascriptInterface;
import java.util.concurrent.BlockingQueue;
import net.nend.android.internal.p023ui.p024a.C0654d.C0655a;

/* renamed from: net.nend.android.internal.ui.a.a */
/* compiled from: CheckHtmlBridgeInterface */
class C0651a {

    /* renamed from: a */
    final BlockingQueue<C0654d> f911a;

    /* renamed from: b */
    final String f912b;

    C0651a(BlockingQueue<C0654d> blockingQueue, String str) {
        this.f911a = blockingQueue;
        this.f912b = str;
    }

    @JavascriptInterface
    public void viewSource(String str) {
        this.f911a.add(new C0654d(C0655a.VIEW_SOURCE, this.f912b, str));
    }
}
