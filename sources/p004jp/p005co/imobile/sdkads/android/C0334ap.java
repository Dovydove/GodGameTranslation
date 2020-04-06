package p004jp.p005co.imobile.sdkads.android;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.widget.RelativeLayout.LayoutParams;

/* renamed from: jp.co.imobile.sdkads.android.ap */
final class C0334ap extends C0361z {

    /* renamed from: u */
    ImobileSdkAdListener f89u = null;
    /* access modifiers changed from: private */

    /* renamed from: v */
    public Dialog f90v = null;

    C0334ap() {
    }

    /* renamed from: a */
    private void m63a(Activity activity, C0341f fVar, Rect rect) {
        C0359x.m125a(null);
        this.f90v = new Dialog(activity);
        this.f90v.setOwnerActivity(activity);
        this.f90v.setOnKeyListener(new C0335aq(this));
        this.f90v.getWindow().setLayout(-1, -1);
        this.f90v.requestWindowFeature(1);
        this.f90v.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(0, 0, 0, 0)));
        int requestedOrientation = this.f90v.getOwnerActivity().getRequestedOrientation();
        activity.setRequestedOrientation(C0353r.m105b(activity));
        this.f89u = new C0336ar(this, fVar, requestedOrientation);
        this.f90v.setContentView(fVar.mo7189a(activity), new LayoutParams(-1, -2));
        fVar.mo7191a(this.f89u, rect);
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ed  */
    /* JADX WARNING: Removed duplicated region for block: B:48:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo7205a(android.app.Activity r8, p004jp.p005co.imobile.sdkads.android.ImobileSdkAdListener r9, android.graphics.Point r10, java.lang.Boolean r11, android.view.ViewGroup r12, p004jp.p005co.imobile.sdkads.android.ImobileIconParams r13, java.lang.Boolean r14) {
        /*
            r7 = this;
            r6 = 0
            r7.f204q = r9
            java.util.concurrent.atomic.AtomicBoolean r0 = r7.f206s
            boolean r0 = r0.get()
            if (r0 == 0) goto L_0x0013
            java.lang.String r0 = "Show ad."
            java.lang.String r1 = "Already show ad."
            p004jp.p005co.imobile.sdkads.android.C0359x.m126b(r0, r1)
        L_0x0012:
            return
        L_0x0013:
            java.util.concurrent.atomic.AtomicBoolean r0 = r7.f206s
            r1 = 1
            r0.set(r1)
            boolean r0 = r11.booleanValue()
            if (r0 != 0) goto L_0x0084
            int r0 = r7.f192e
            int r0 = r0 + 1
            r7.f192e = r0
            java.util.Date r0 = r7.f197j
            if (r0 == 0) goto L_0x0061
            java.util.Calendar r0 = java.util.Calendar.getInstance()
            java.util.Date r1 = r7.f197j
            r0.setTime(r1)
            r1 = 13
            int r2 = r7.f193f
            r0.add(r1, r2)
            java.util.Date r1 = new java.util.Date
            r1.<init>()
            java.util.Date r0 = r0.getTime()
            int r0 = r1.compareTo(r0)
            if (r0 >= 0) goto L_0x0061
            jp.co.imobile.sdkads.android.ImobileSdkAdListener r0 = r7.f207t
            r0.onAdCloseCompleted()
            java.lang.String r0 = "ShowAd IntervalTime skip."
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Last ShowAd DateTime:"
            r1.<init>(r2)
            java.util.Date r2 = r7.f197j
            r1.append(r2)
            java.lang.String r1 = ""
            p004jp.p005co.imobile.sdkads.android.C0359x.m126b(r0, r1)
            goto L_0x0012
        L_0x0061:
            int r0 = r7.f193f
            if (r0 != 0) goto L_0x0084
            int r0 = r7.f191d
            int r1 = r7.f192e
            if (r0 < r1) goto L_0x0084
            jp.co.imobile.sdkads.android.ImobileSdkAdListener r0 = r7.f207t
            r0.onAdCloseCompleted()
            java.lang.String r0 = "ShowAd skip count skip."
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "call count:"
            r1.<init>(r2)
            int r2 = r7.f192e
            r1.append(r2)
            java.lang.String r1 = ""
            p004jp.p005co.imobile.sdkads.android.C0359x.m126b(r0, r1)
            goto L_0x0012
        L_0x0084:
            android.graphics.Rect r3 = new android.graphics.Rect
            int r0 = r10.x
            int r1 = r10.y
            int r2 = r7.f195h
            int r4 = r7.f196i
            r3.<init>(r0, r1, r2, r4)
            r2 = 0
            int r0 = r7.f198k
            if (r0 <= 0) goto L_0x00cb
            jp.co.imobile.sdkads.android.f r0 = r7.mo7283j()
            if (r0 == 0) goto L_0x00c2
            r7.m63a(r8, r0, r3)     // Catch:{ y -> 0x00b0 }
        L_0x009f:
            java.util.Date r0 = new java.util.Date
            r0.<init>()
            r7.f197j = r0
            boolean r0 = r11.booleanValue()
            if (r0 != 0) goto L_0x0012
            r7.f192e = r6
            goto L_0x0012
        L_0x00b0:
            r0 = move-exception
            java.lang.String r1 = "showAdDialog"
            java.lang.String r2 = ""
            p004jp.p005co.imobile.sdkads.android.C0359x.m126b(r1, r2)
            jp.co.imobile.sdkads.android.ImobileSdkAdListener r1 = r7.f207t
            jp.co.imobile.sdkads.android.FailNotificationReason r0 = r0.mo7267a()
            r1.onFailed(r0)
            goto L_0x009f
        L_0x00c2:
            jp.co.imobile.sdkads.android.ImobileSdkAdListener r0 = r7.f207t
            jp.co.imobile.sdkads.android.FailNotificationReason r1 = p004jp.p005co.imobile.sdkads.android.FailNotificationReason.AD_NOT_READY
            r0.onFailed(r1)
            goto L_0x0012
        L_0x00cb:
            jp.co.imobile.sdkads.android.a r1 = new jp.co.imobile.sdkads.android.a     // Catch:{ y -> 0x00f1 }
            android.content.Context r0 = p004jp.p005co.imobile.sdkads.android.ImobileSdkAd.m32a()     // Catch:{ y -> 0x00f1 }
            jp.co.imobile.sdkads.android.ImobileSdkAdListener r4 = r7.f207t     // Catch:{ y -> 0x00f1 }
            r5 = 0
            r1.<init>(r7, r0, r4, r5)     // Catch:{ y -> 0x00f1 }
            p004jp.p005co.imobile.sdkads.android.ImobileSdkAd.m32a()     // Catch:{ y -> 0x010f }
            r1.mo7242a(r7)     // Catch:{ y -> 0x010f }
        L_0x00dd:
            r7.m63a(r8, r1, r3)     // Catch:{ y -> 0x00fd }
        L_0x00e0:
            java.util.Date r0 = new java.util.Date
            r0.<init>()
            r7.f197j = r0
            boolean r0 = r11.booleanValue()
            if (r0 != 0) goto L_0x0012
            r7.f192e = r6
            goto L_0x0012
        L_0x00f1:
            r0 = move-exception
            r1 = r2
        L_0x00f3:
            jp.co.imobile.sdkads.android.ImobileSdkAdListener r2 = r7.f207t
            jp.co.imobile.sdkads.android.FailNotificationReason r0 = r0.mo7267a()
            r2.onFailed(r0)
            goto L_0x00dd
        L_0x00fd:
            r0 = move-exception
            java.lang.String r1 = "showAdDialog"
            java.lang.String r2 = ""
            p004jp.p005co.imobile.sdkads.android.C0359x.m126b(r1, r2)
            jp.co.imobile.sdkads.android.ImobileSdkAdListener r1 = r7.f207t
            jp.co.imobile.sdkads.android.FailNotificationReason r0 = r0.mo7267a()
            r1.onFailed(r0)
            goto L_0x00e0
        L_0x010f:
            r0 = move-exception
            goto L_0x00f3
        */
        throw new UnsupportedOperationException("Method not decompiled: p004jp.p005co.imobile.sdkads.android.C0334ap.mo7205a(android.app.Activity, jp.co.imobile.sdkads.android.ImobileSdkAdListener, android.graphics.Point, java.lang.Boolean, android.view.ViewGroup, jp.co.imobile.sdkads.android.ImobileIconParams, java.lang.Boolean):void");
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: k */
    public final boolean mo7206k() {
        return this.f198k != 0 ? mo7283j() != null : mo7269a() == C0331am.START;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: l */
    public final void mo7207l() {
        if (mo7269a() == C0331am.START) {
            mo7273a(C0331am.PAUSE);
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: m */
    public final void mo7208m() {
        if (this.f90v != null && this.f90v.isShowing() && this.f89u != null) {
            this.f89u.onAdCloseCompleted();
        }
    }
}
