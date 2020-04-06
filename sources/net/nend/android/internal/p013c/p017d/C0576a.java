package net.nend.android.internal.p013c.p017d;

import android.content.Context;
import android.net.Uri.Builder;
import com.google.android.gms.dynamite.ProviderConstants;
import net.nend.android.internal.p013c.C0557b;
import org.andengine.util.level.constants.LevelConstants;

/* renamed from: net.nend.android.internal.c.d.a */
/* compiled from: NendAdInterstitialRequest */
public class C0576a extends C0557b {
    public C0576a(Context context, int i, String str) {
        super(context, i, str);
    }

    /* renamed from: a */
    public String mo7833a() {
        return "lois.nend.net";
    }

    /* renamed from: b */
    public String mo7835b() {
        return "nsa.php";
    }

    /* renamed from: a */
    public String mo7834a(String str) {
        return new Builder().scheme(this.f664a).authority(this.f665b).path(this.f666c).appendQueryParameter("apikey", this.f668e).appendQueryParameter("spot", String.valueOf(this.f667d)).appendQueryParameter(LevelConstants.TAG_LEVEL_ATTRIBUTE_UID, str).appendQueryParameter("os", mo7850c()).appendQueryParameter(ProviderConstants.API_COLNAME_FEATURE_VERSION, mo7855h()).appendQueryParameter("model", mo7852e()).appendQueryParameter("device", mo7853f()).appendQueryParameter("localize", mo7854g()).appendQueryParameter("sdkver", mo7851d()).appendQueryParameter("gaid", mo7856i()).toString();
    }
}
