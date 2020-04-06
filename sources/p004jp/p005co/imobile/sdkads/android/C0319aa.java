package p004jp.p005co.imobile.sdkads.android;

import java.util.Iterator;

/* renamed from: jp.co.imobile.sdkads.android.aa */
final class C0319aa extends ImobileSdkAdListener {
    /* access modifiers changed from: 0000 */

    /* renamed from: a */
    public final /* synthetic */ C0361z f59a;

    C0319aa(C0361z zVar) {
        this.f59a = zVar;
    }

    public final void onAdCliclkCompleted() {
        this.f59a.f206s.set(false);
        if (this.f59a.f203p != null) {
            this.f59a.f209v.post(new C0322ad(this));
        }
    }

    public final void onAdCloseCompleted() {
        this.f59a.f206s.set(false);
        if (this.f59a.f203p != null) {
            this.f59a.f209v.post(new C0323ae(this));
        }
        if (this.f59a.f204q != null) {
            this.f59a.f209v.post(new C0324af(this));
        }
    }

    public final void onAdReadyCompleted() {
        if (this.f59a.f203p != null) {
            this.f59a.f209v.post(new C0320ab(this));
        }
    }

    public final void onAdShowCompleted() {
        if (this.f59a.f203p != null) {
            this.f59a.f209v.post(new C0321ac(this));
        }
    }

    public final void onDismissAdScreen() {
        if (this.f59a.f203p != null) {
            this.f59a.f209v.post(new C0327ai(this));
        }
    }

    public final void onFailed(FailNotificationReason reason) {
        this.f59a.f206s.set(false);
        if (reason == FailNotificationReason.AUTHORITY) {
            this.f59a.mo7273a(C0331am.ERROR);
        }
        Iterator it = this.f59a.f202o.iterator();
        boolean z = true;
        while (it.hasNext()) {
            C0341f fVar = (C0341f) it.next();
            if (fVar.mo7243d() == C0345j.DISPLAYABLE || fVar.mo7243d() == C0345j.LODING) {
                z = false;
            }
        }
        if (z && this.f59a.f203p != null) {
            this.f59a.f209v.post(new C0325ag(this, reason));
        }
        if (this.f59a.f204q != null && reason == FailNotificationReason.AD_NOT_READY) {
            this.f59a.f209v.post(new C0326ah(this));
        }
    }
}
