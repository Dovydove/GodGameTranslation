package p004jp.p005co.imobile.sdkads.android;

/* renamed from: jp.co.imobile.sdkads.android.an */
final class C0332an extends C0361z {

    /* renamed from: u */
    ImobileSdkAdListener f83u = null;

    C0332an() {
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024 A[SYNTHETIC, Splitter:B:8:0x0024] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo7205a(android.app.Activity r8, p004jp.p005co.imobile.sdkads.android.ImobileSdkAdListener r9, android.graphics.Point r10, java.lang.Boolean r11, android.view.ViewGroup r12, p004jp.p005co.imobile.sdkads.android.ImobileIconParams r13, java.lang.Boolean r14) {
        /*
            r7 = this;
            r2 = 0
            if (r8 == 0) goto L_0x0009
            android.view.Window r0 = r8.getWindow()
            if (r0 != 0) goto L_0x000a
        L_0x0009:
            return
        L_0x000a:
            r7.f204q = r9
            android.graphics.Rect r6 = new android.graphics.Rect
            int r0 = r10.x
            int r1 = r10.y
            int r3 = r7.f195h
            int r4 = r7.f196i
            r6.<init>(r0, r1, r3, r4)
            int r0 = r7.f198k
            if (r0 == 0) goto L_0x004c
            jp.co.imobile.sdkads.android.f r1 = r7.mo7283j()
            r4 = r1
        L_0x0022:
            if (r4 == 0) goto L_0x006d
            r4.mo7240a(r13)     // Catch:{ y -> 0x0041 }
            r4.f115k = r14     // Catch:{ y -> 0x0041 }
            r0 = 0
            p004jp.p005co.imobile.sdkads.android.C0359x.m125a(r0)     // Catch:{ y -> 0x0041 }
            android.widget.RelativeLayout r3 = r4.mo7189a(r8)     // Catch:{ y -> 0x0041 }
            jp.co.imobile.sdkads.android.ao r0 = new jp.co.imobile.sdkads.android.ao     // Catch:{ y -> 0x0041 }
            r1 = r7
            r2 = r12
            r5 = r8
            r0.<init>(r1, r2, r3, r4, r5)     // Catch:{ y -> 0x0041 }
            r7.f83u = r0     // Catch:{ y -> 0x0041 }
            jp.co.imobile.sdkads.android.ImobileSdkAdListener r0 = r7.f83u     // Catch:{ y -> 0x0041 }
            r4.mo7191a(r0, r6)     // Catch:{ y -> 0x0041 }
            goto L_0x0009
        L_0x0041:
            r0 = move-exception
            jp.co.imobile.sdkads.android.ImobileSdkAdListener r1 = r7.f207t
            jp.co.imobile.sdkads.android.FailNotificationReason r0 = r0.mo7267a()
            r1.onFailed(r0)
            goto L_0x0009
        L_0x004c:
            jp.co.imobile.sdkads.android.a r1 = new jp.co.imobile.sdkads.android.a     // Catch:{ y -> 0x0060 }
            android.content.Context r0 = p004jp.p005co.imobile.sdkads.android.ImobileSdkAd.m32a()     // Catch:{ y -> 0x0060 }
            jp.co.imobile.sdkads.android.ImobileSdkAdListener r3 = r7.f207t     // Catch:{ y -> 0x0060 }
            r4 = 0
            r1.<init>(r7, r0, r3, r4)     // Catch:{ y -> 0x0060 }
            p004jp.p005co.imobile.sdkads.android.ImobileSdkAd.m32a()     // Catch:{ y -> 0x0075 }
            r1.mo7242a(r7)     // Catch:{ y -> 0x0075 }
            r4 = r1
            goto L_0x0022
        L_0x0060:
            r0 = move-exception
            r1 = r2
        L_0x0062:
            jp.co.imobile.sdkads.android.ImobileSdkAdListener r2 = r7.f207t
            jp.co.imobile.sdkads.android.FailNotificationReason r0 = r0.mo7267a()
            r2.onFailed(r0)
            r4 = r1
            goto L_0x0022
        L_0x006d:
            jp.co.imobile.sdkads.android.ImobileSdkAdListener r0 = r7.f207t
            jp.co.imobile.sdkads.android.FailNotificationReason r1 = p004jp.p005co.imobile.sdkads.android.FailNotificationReason.AD_NOT_READY
            r0.onFailed(r1)
            goto L_0x0009
        L_0x0075:
            r0 = move-exception
            goto L_0x0062
        */
        throw new UnsupportedOperationException("Method not decompiled: p004jp.p005co.imobile.sdkads.android.C0332an.mo7205a(android.app.Activity, jp.co.imobile.sdkads.android.ImobileSdkAdListener, android.graphics.Point, java.lang.Boolean, android.view.ViewGroup, jp.co.imobile.sdkads.android.ImobileIconParams, java.lang.Boolean):void");
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: k */
    public final boolean mo7206k() {
        return mo7269a() == C0331am.START;
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
        if (this.f83u != null) {
            this.f83u.onAdCloseCompleted();
        }
    }
}
