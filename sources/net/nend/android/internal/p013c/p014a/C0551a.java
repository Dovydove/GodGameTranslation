package net.nend.android.internal.p013c.p014a;

import android.content.Context;
import android.net.Uri.Builder;
import android.util.DisplayMetrics;
import com.google.android.gms.dynamite.ProviderConstants;
import net.nend.android.internal.p013c.C0557b;
import org.andengine.util.level.constants.LevelConstants;

/* renamed from: net.nend.android.internal.c.a.a */
/* compiled from: NendAdRequest */
public class C0551a extends C0557b {
    public C0551a(Context context, int i, String str) {
        super(context, i, str);
    }

    /* renamed from: a */
    public String mo7833a() {
        return "ad1.nend.net";
    }

    /* renamed from: b */
    public String mo7835b() {
        return "na.php";
    }

    /* renamed from: a */
    public String mo7834a(String str) {
        DisplayMetrics displayMetrics = this.f669f.getResources().getDisplayMetrics();
        return new Builder().scheme(this.f664a).authority(this.f665b).path(this.f666c).appendQueryParameter("apikey", this.f668e).appendQueryParameter("spot", String.valueOf(this.f667d)).appendQueryParameter(LevelConstants.TAG_LEVEL_ATTRIBUTE_UID, str).appendQueryParameter("os", mo7850c()).appendQueryParameter(ProviderConstants.API_COLNAME_FEATURE_VERSION, mo7855h()).appendQueryParameter("model", mo7852e()).appendQueryParameter("device", mo7853f()).appendQueryParameter("localize", mo7854g()).appendQueryParameter("sdkver", mo7851d()).appendQueryParameter("gaid", mo7856i()).appendQueryParameter("wdpi", String.valueOf(displayMetrics.xdpi)).appendQueryParameter("hdpi", String.valueOf(displayMetrics.ydpi)).appendQueryParameter("device_width", String.valueOf(displayMetrics.widthPixels)).appendQueryParameter("device_height", String.valueOf(displayMetrics.heightPixels)).toString();
    }
}
