package com.google.android.gms.common.logging;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.internal.GmsLogger;
import java.util.Locale;

public class Logger {
    private final String mTag;
    private final String zzud;
    private final GmsLogger zzvd;
    private final int zzve;

    private Logger(String str, String str2) {
        this.zzud = str2;
        this.mTag = str;
        this.zzvd = new GmsLogger(str);
        int i = 2;
        while (7 >= i && !Log.isLoggable(this.mTag, i)) {
            i++;
        }
        this.zzve = i;
    }

    public Logger(String str, String... strArr) {
        String sb;
        if (strArr.length == 0) {
            sb = "";
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append('[');
            for (String str2 : strArr) {
                if (sb2.length() > 1) {
                    sb2.append(",");
                }
                sb2.append(str2);
            }
            sb2.append(']').append(' ');
            sb = sb2.toString();
        }
        this(str, sb);
    }

    /* renamed from: d */
    public void mo6935d(String str, Throwable th, @Nullable Object... objArr) {
        if (isLoggable(3)) {
            Log.d(this.mTag, format(str, objArr), th);
        }
    }

    /* renamed from: d */
    public void mo6936d(String str, @Nullable Object... objArr) {
        if (isLoggable(3)) {
            Log.d(this.mTag, format(str, objArr));
        }
    }

    /* renamed from: e */
    public void mo6937e(String str, Throwable th, @Nullable Object... objArr) {
        Log.e(this.mTag, format(str, objArr), th);
    }

    /* renamed from: e */
    public void mo6938e(String str, @Nullable Object... objArr) {
        Log.e(this.mTag, format(str, objArr));
    }

    public String elidePii(Object obj) {
        boolean canLogPii = this.zzvd.canLogPii();
        if (obj == null) {
            return "<NULL>";
        }
        String trim = obj.toString().trim();
        if (trim.isEmpty()) {
            return "<EMPTY>";
        }
        if (canLogPii) {
            return trim;
        }
        return String.format("<ELLIDED:%s>", new Object[]{Integer.valueOf(trim.hashCode())});
    }

    /* access modifiers changed from: protected */
    public String format(String str, @Nullable Object... objArr) {
        if (objArr != null && objArr.length > 0) {
            str = String.format(Locale.US, str, objArr);
        }
        return this.zzud.concat(str);
    }

    public String getTag() {
        return this.mTag;
    }

    /* renamed from: i */
    public void mo6942i(String str, Throwable th, @Nullable Object... objArr) {
        Log.i(this.mTag, format(str, objArr), th);
    }

    /* renamed from: i */
    public void mo6943i(String str, @Nullable Object... objArr) {
        Log.i(this.mTag, format(str, objArr));
    }

    public boolean isLoggable(int i) {
        return this.zzve <= i;
    }

    public boolean isPiiLoggable() {
        return this.zzvd.canLogPii();
    }

    /* renamed from: v */
    public void mo6946v(String str, Throwable th, @Nullable Object... objArr) {
        if (isLoggable(2)) {
            Log.v(this.mTag, format(str, objArr), th);
        }
    }

    /* renamed from: v */
    public void mo6947v(String str, @Nullable Object... objArr) {
        if (isLoggable(2)) {
            Log.v(this.mTag, format(str, objArr));
        }
    }

    /* renamed from: w */
    public void mo6948w(String str, Throwable th, @Nullable Object... objArr) {
        Log.w(this.mTag, format(str, objArr), th);
    }

    /* renamed from: w */
    public void mo6949w(String str, @Nullable Object... objArr) {
        Log.w(this.mTag, format(str, objArr));
    }

    /* renamed from: w */
    public void mo6950w(Throwable th) {
        Log.w(this.mTag, th);
    }

    public void wtf(String str, Throwable th, @Nullable Object... objArr) {
        Log.wtf(this.mTag, format(str, objArr), th);
    }

    @SuppressLint({"WtfWithoutException"})
    public void wtf(String str, @Nullable Object... objArr) {
        Log.wtf(this.mTag, format(str, objArr));
    }

    public void wtf(Throwable th) {
        Log.wtf(this.mTag, th);
    }
}
