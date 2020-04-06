package net.nend.android.internal.p013c.p016c;

import android.content.Context;
import android.net.Uri.Builder;
import com.google.android.gms.dynamite.ProviderConstants;
import net.nend.android.internal.p013c.C0557b;
import org.andengine.util.level.constants.LevelConstants;

/* renamed from: net.nend.android.internal.c.c.a */
/* compiled from: NendAdIconRequest */
public class C0568a extends C0557b {

    /* renamed from: g */
    private int f702g;

    public C0568a(Context context, int i, String str) {
        super(context, i, str);
    }

    /* renamed from: a */
    public String mo7833a() {
        return "ad3.nend.net";
    }

    /* renamed from: b */
    public String mo7835b() {
        return "nia.php";
    }

    /* renamed from: a */
    public String mo7834a(String str) {
        return new Builder().scheme(this.f664a).authority(this.f665b).path(this.f666c).appendQueryParameter("apikey", this.f668e).appendQueryParameter("spot", String.valueOf(this.f667d)).appendQueryParameter(LevelConstants.TAG_LEVEL_ATTRIBUTE_UID, str).appendQueryParameter("os", mo7850c()).appendQueryParameter(ProviderConstants.API_COLNAME_FEATURE_VERSION, mo7855h()).appendQueryParameter("model", mo7852e()).appendQueryParameter("device", mo7853f()).appendQueryParameter("localize", mo7854g()).appendQueryParameter("sdkver", mo7851d()).appendQueryParameter("ad_num", String.valueOf(mo7870j())).appendQueryParameter("gaid", mo7856i()).toString();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: j */
    public int mo7870j() {
        return this.f702g;
    }

    /* renamed from: a */
    public void mo7869a(int i) {
        this.f702g = i;
    }
}
