package p004jp.p005co.imobile.sdkads.android;

import android.app.Activity;
import android.graphics.Point;
import android.os.Handler;
import android.support.p000v4.app.NotificationCompat;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;
import p004jp.p005co.imobile.sdkads.android.ImobileSdkAd.AdShowType;

/* renamed from: jp.co.imobile.sdkads.android.z */
abstract class C0361z {

    /* renamed from: x */
    private static /* synthetic */ int[] f186x;

    /* renamed from: y */
    private static /* synthetic */ int[] f187y;

    /* renamed from: a */
    protected String f188a = "";

    /* renamed from: b */
    protected String f189b = "";

    /* renamed from: c */
    protected String f190c = "";

    /* renamed from: d */
    protected int f191d = 0;

    /* renamed from: e */
    protected int f192e = 0;

    /* renamed from: f */
    protected int f193f = 0;

    /* renamed from: g */
    protected int f194g = 0;

    /* renamed from: h */
    protected int f195h = 0;

    /* renamed from: i */
    protected int f196i = 0;

    /* renamed from: j */
    protected Date f197j;

    /* renamed from: k */
    protected int f198k = 0;

    /* renamed from: l */
    protected int f199l = 0;

    /* renamed from: m */
    protected String f200m;

    /* renamed from: n */
    protected String f201n;

    /* renamed from: o */
    protected ArrayList f202o = new ArrayList();

    /* renamed from: p */
    protected ImobileSdkAdListener f203p;

    /* renamed from: q */
    protected ImobileSdkAdListener f204q;

    /* renamed from: r */
    protected CopyOnWriteArrayList f205r = new CopyOnWriteArrayList();

    /* renamed from: s */
    protected AtomicBoolean f206s = new AtomicBoolean();

    /* renamed from: t */
    protected ImobileSdkAdListener f207t = new C0319aa(this);

    /* renamed from: u */
    private volatile C0331am f208u = C0331am.NONE;
    /* access modifiers changed from: private */

    /* renamed from: v */
    public final Handler f209v = new Handler();

    /* renamed from: w */
    private AdShowType f210w = null;

    C0361z() {
    }

    /* renamed from: n */
    private static /* synthetic */ int[] m129n() {
        int[] iArr = f186x;
        if (iArr == null) {
            iArr = new int[C0331am.m57a().length];
            try {
                iArr[C0331am.ERROR.ordinal()] = 6;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[C0331am.LODING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[C0331am.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[C0331am.PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[C0331am.START.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[C0331am.STOP.ordinal()] = 5;
            } catch (NoSuchFieldError e6) {
            }
            f186x = iArr;
        }
        return iArr;
    }

    /* renamed from: o */
    private static /* synthetic */ int[] m130o() {
        int[] iArr = f187y;
        if (iArr == null) {
            iArr = new int[C0345j.m85a().length];
            try {
                iArr[C0345j.DISPLAING.ordinal()] = 7;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[C0345j.DISPLAYABLE.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[C0345j.DISPLAYED.ordinal()] = 6;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[C0345j.ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[C0345j.EXPIRED.ordinal()] = 8;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[C0345j.LODING.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[C0345j.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[C0345j.SCRIPT_ERROR.ordinal()] = 5;
            } catch (NoSuchFieldError e8) {
            }
            f187y = iArr;
        }
        return iArr;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final String mo7268a(ImobileIconParams imobileIconParams, Boolean bool) {
        JSONObject e = C0353r.m110e();
        try {
            JSONObject jSONObject = e.getJSONObject("result");
            jSONObject.put("pid", this.f188a);
            jSONObject.put("mid", this.f189b);
            jSONObject.put("sid", this.f190c);
            jSONObject.put("test", ImobileSdkAd.m39b().toString());
            if (imobileIconParams != null) {
                jSONObject.put("iconParams", imobileIconParams.mo7173a());
            }
            if (bool.booleanValue()) {
                jSONObject.put("refreshTime", 0);
            } else {
                jSONObject.put("refreshTime", this.f194g);
            }
            e.put(NotificationCompat.CATEGORY_STATUS, "succeed");
            return e.toString();
        } catch (JSONException e2) {
            e2.getMessage();
            C0359x.m126b("Spot data to ad view data create.", "parse");
            throw new C0360y(FailNotificationReason.RESPONSE);
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final C0331am mo7269a() {
        return this.f208u;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public abstract void mo7205a(Activity activity, ImobileSdkAdListener imobileSdkAdListener, Point point, Boolean bool, ViewGroup viewGroup, ImobileIconParams imobileIconParams, Boolean bool2);

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo7270a(String str, String str2, String str3) {
        this.f188a = str;
        this.f189b = str2;
        this.f190c = str3;
    }

    /* renamed from: a */
    public final void mo7271a(AdShowType adShowType) {
        this.f210w = adShowType;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo7272a(ImobileSdkAdListener imobileSdkAdListener) {
        this.f203p = imobileSdkAdListener;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo7273a(C0331am amVar) {
        new StringBuilder("status : ").append(amVar);
        C0359x.m125a(null);
        this.f208u = amVar;
        if (C0331am.START == amVar && this.f205r.size() > 0) {
            Iterator it = this.f205r.iterator();
            while (it.hasNext()) {
                ((ImobileSdkAdListener) it.next()).onAdReadyCompleted();
            }
            this.f205r.clear();
        }
        new StringBuilder("Wait show ad execute.").append(amVar);
        C0359x.m125a(null);
    }

    /* renamed from: b */
    public final AdShowType mo7274b() {
        return this.f210w;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public final void mo7275b(ImobileSdkAdListener imobileSdkAdListener) {
        if (imobileSdkAdListener != null) {
            this.f205r.add(imobileSdkAdListener);
        } else {
            this.f205r.clear();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: c */
    public final String mo7276c() {
        return this.f188a;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: d */
    public final String mo7277d() {
        return this.f189b;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public final String mo7278e() {
        return this.f190c;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: f */
    public final String mo7279f() {
        return this.f200m;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: g */
    public final String mo7280g() {
        return this.f201n;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: h */
    public final void mo7281h() {
        switch (m129n()[this.f208u.ordinal()]) {
            case 1:
            case 5:
            case 6:
                mo7273a(C0331am.LODING);
                Executors.newCachedThreadPool().submit(new C0329ak(this, Executors.newCachedThreadPool().submit(new C0328aj(this, this)), this));
                return;
            case 4:
                mo7273a(C0331am.START);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: i */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo7282i() {
        /*
            r11 = this;
            r10 = 60
            r9 = 13
            r2 = 0
            r8 = 0
            r4 = 1
            int r0 = r11.f198k
            if (r0 == 0) goto L_0x0017
            jp.co.imobile.sdkads.android.am r0 = r11.f208u
            jp.co.imobile.sdkads.android.am r1 = p004jp.p005co.imobile.sdkads.android.C0331am.STOP
            if (r0 == r1) goto L_0x0017
            jp.co.imobile.sdkads.android.am r0 = r11.f208u
            jp.co.imobile.sdkads.android.am r1 = p004jp.p005co.imobile.sdkads.android.C0331am.ERROR
            if (r0 != r1) goto L_0x0018
        L_0x0017:
            return
        L_0x0018:
            java.util.Calendar r5 = java.util.Calendar.getInstance()
            java.util.ArrayList r0 = r11.f202o
            java.util.Iterator r6 = r0.iterator()
            r1 = r2
        L_0x0023:
            boolean r0 = r6.hasNext()
            if (r0 != 0) goto L_0x004f
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "available ad:"
            r0.<init>(r2)
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = "/"
            java.lang.StringBuilder r0 = r0.append(r1)
            int r1 = r11.f198k
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = " on spot:"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = r11.f190c
            r0.append(r1)
            p004jp.p005co.imobile.sdkads.android.C0359x.m125a(r8)
            goto L_0x0017
        L_0x004f:
            java.lang.Object r0 = r6.next()
            jp.co.imobile.sdkads.android.f r0 = (p004jp.p005co.imobile.sdkads.android.C0341f) r0
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r7 = "ad status:"
            r3.<init>(r7)
            jp.co.imobile.sdkads.android.j r7 = r0.mo7243d()
            java.lang.StringBuilder r3 = r3.append(r7)
            java.lang.String r7 = " on spot:"
            java.lang.StringBuilder r3 = r3.append(r7)
            java.lang.String r7 = r11.f190c
            r3.append(r7)
            p004jp.p005co.imobile.sdkads.android.C0359x.m125a(r8)
            int[] r3 = m130o()
            jp.co.imobile.sdkads.android.j r7 = r0.mo7243d()
            int r7 = r7.ordinal()
            r3 = r3[r7]
            switch(r3) {
                case 1: goto L_0x008a;
                case 2: goto L_0x00cd;
                case 3: goto L_0x00b8;
                case 4: goto L_0x008c;
                case 5: goto L_0x008c;
                case 6: goto L_0x008a;
                case 7: goto L_0x0083;
                case 8: goto L_0x008a;
                default: goto L_0x0083;
            }
        L_0x0083:
            r3 = r2
        L_0x0084:
            if (r3 == 0) goto L_0x0023
            r0.mo7242a(r11)
            goto L_0x0023
        L_0x008a:
            r3 = r4
            goto L_0x0084
        L_0x008c:
            java.util.Date r3 = r0.mo7193c()
            r5.setTime(r3)
            r5.add(r9, r10)
            java.util.Date r3 = new java.util.Date
            r3.<init>()
            java.util.Date r7 = r5.getTime()
            int r3 = r3.compareTo(r7)
            if (r3 <= 0) goto L_0x00a7
            r3 = r4
            goto L_0x0084
        L_0x00a7:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r7 = "Error retry not reach time. on spot:"
            r3.<init>(r7)
            java.lang.String r7 = r11.f190c
            r3.append(r7)
            p004jp.p005co.imobile.sdkads.android.C0359x.m125a(r8)
            r3 = r2
            goto L_0x0084
        L_0x00b8:
            java.util.Date r3 = new java.util.Date
            r3.<init>()
            java.util.Date r7 = r0.mo7192b()
            int r3 = r3.compareTo(r7)
            if (r3 <= 0) goto L_0x00c9
            r3 = r4
            goto L_0x0084
        L_0x00c9:
            int r1 = r1 + 1
            r3 = r2
            goto L_0x0084
        L_0x00cd:
            java.util.Date r3 = r0.mo7193c()
            r5.setTime(r3)
            r5.add(r9, r10)
            java.util.Date r3 = new java.util.Date
            r3.<init>()
            java.util.Date r7 = r5.getTime()
            int r3 = r3.compareTo(r7)
            if (r3 <= 0) goto L_0x0083
            r3 = r4
            goto L_0x0084
        */
        throw new UnsupportedOperationException("Method not decompiled: p004jp.p005co.imobile.sdkads.android.C0361z.mo7282i():void");
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: j */
    public final C0341f mo7283j() {
        Iterator it = this.f202o.iterator();
        C0341f fVar = null;
        while (it.hasNext()) {
            C0341f fVar2 = (C0341f) it.next();
            if (fVar2.mo7243d() == C0345j.DISPLAYABLE) {
                if (new Date().compareTo(fVar2.mo7192b()) > 0) {
                    fVar2.mo7241a(C0345j.EXPIRED);
                } else if (fVar == null) {
                    fVar = fVar2;
                } else if (fVar.mo7192b().after(fVar2.mo7192b())) {
                    fVar = fVar2;
                }
            }
        }
        return fVar;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: k */
    public abstract boolean mo7206k();

    /* access modifiers changed from: 0000 */
    /* renamed from: l */
    public abstract void mo7207l();

    /* access modifiers changed from: 0000 */
    /* renamed from: m */
    public abstract void mo7208m();
}
