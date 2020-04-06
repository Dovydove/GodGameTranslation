package p004jp.p005co.imobile.sdkads.android;

import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;

/* renamed from: jp.co.imobile.sdkads.android.aq */
final class C0335aq implements OnKeyListener {

    /* renamed from: a */
    final /* synthetic */ C0334ap f91a;

    C0335aq(C0334ap apVar) {
        this.f91a = apVar;
    }

    public final boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent keyEvent) {
        switch (keyCode) {
            case 4:
            case 84:
                return true;
            default:
                return false;
        }
    }
}
