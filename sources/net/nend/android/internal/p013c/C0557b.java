package net.nend.android.internal.p013c;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.util.Locale;
import net.nend.android.BuildConfig;
import net.nend.android.internal.utilities.C0751b.C0752a;
import net.nend.android.internal.utilities.C0755e;
import net.nend.android.internal.utilities.C0755e.C0756a;

/* renamed from: net.nend.android.internal.c.b */
/* compiled from: AbsNendAdRequest */
public abstract class C0557b {

    /* renamed from: a */
    protected final String f664a;

    /* renamed from: b */
    protected final String f665b;

    /* renamed from: c */
    protected final String f666c;

    /* renamed from: d */
    protected final int f667d;

    /* renamed from: e */
    protected final String f668e;

    /* renamed from: f */
    protected final Context f669f;

    /* renamed from: a */
    public abstract String mo7833a();

    /* renamed from: a */
    public abstract String mo7834a(String str);

    /* renamed from: b */
    public abstract String mo7835b();

    public C0557b(Context context, int i, String str) {
        if (context == null) {
            throw new NullPointerException("Context is null.");
        } else if (i <= 0) {
            throw new IllegalArgumentException("Spot id is invalid. spot id : " + i);
        } else if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Api key is invalid. api key : " + str);
        } else {
            this.f669f = context;
            this.f667d = i;
            this.f668e = str;
            this.f664a = C0756a.m1206a(context, C0752a.ADSCHEME.mo8195a(), "https");
            this.f665b = C0756a.m1206a(context, C0752a.ADAUTHORITY.mo8195a(), mo7833a());
            this.f666c = C0756a.m1206a(context, C0752a.ADPATH.mo8195a(), mo7835b());
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public String mo7850c() {
        return "android";
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public String mo7851d() {
        return BuildConfig.VERSION_NAME;
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public String mo7852e() {
        return Build.MODEL;
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public String mo7853f() {
        return Build.DEVICE;
    }

    /* access modifiers changed from: protected */
    /* renamed from: g */
    public String mo7854g() {
        return Locale.getDefault().toString();
    }

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public String mo7855h() {
        return VERSION.RELEASE;
    }

    /* renamed from: b */
    public String mo7849b(String str) {
        if (!TextUtils.isEmpty(str)) {
            return mo7834a(str);
        }
        throw new IllegalArgumentException("UID is invalid. uid : " + str);
    }

    /* access modifiers changed from: protected */
    /* renamed from: i */
    public String mo7856i() {
        return C0755e.m1203c(this.f669f);
    }
}
