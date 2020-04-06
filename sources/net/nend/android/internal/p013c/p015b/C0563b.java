package net.nend.android.internal.p013c.p015b;

import android.content.Context;
import android.net.Uri.Builder;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.google.android.gms.dynamite.ProviderConstants;
import java.util.ArrayList;
import java.util.List;
import net.nend.android.internal.p013c.C0557b;
import org.andengine.util.level.constants.LevelConstants;

/* renamed from: net.nend.android.internal.c.b.b */
/* compiled from: NendAdNativeRequest */
public final class C0563b extends C0557b {

    /* renamed from: g */
    private int f681g;

    /* renamed from: h */
    private List<String> f682h = new ArrayList();

    public C0563b(Context context, int i, String str) {
        super(context, i, str);
        this.f681g = i;
    }

    /* renamed from: a */
    public String mo7833a() {
        return "lons.nend.net";
    }

    /* renamed from: b */
    public String mo7835b() {
        return "nsfeed.php";
    }

    /* renamed from: a */
    public String mo7834a(String str) {
        DisplayMetrics displayMetrics = this.f669f.getResources().getDisplayMetrics();
        Builder appendQueryParameter = new Builder().scheme(this.f664a).authority(this.f665b).path(this.f666c).appendQueryParameter("apikey", this.f668e).appendQueryParameter("spot", String.valueOf(this.f681g)).appendQueryParameter(LevelConstants.TAG_LEVEL_ATTRIBUTE_UID, str).appendQueryParameter("os", mo7850c()).appendQueryParameter(ProviderConstants.API_COLNAME_FEATURE_VERSION, mo7855h()).appendQueryParameter("model", mo7852e()).appendQueryParameter("device", mo7853f()).appendQueryParameter("localize", mo7854g()).appendQueryParameter("sdkver", mo7851d()).appendQueryParameter("gaid", mo7856i()).appendQueryParameter("wdpi", String.valueOf(displayMetrics.xdpi)).appendQueryParameter("hdpi", String.valueOf(displayMetrics.ydpi)).appendQueryParameter("device_width", String.valueOf(displayMetrics.widthPixels)).appendQueryParameter("device_height", String.valueOf(displayMetrics.heightPixels));
        if (this.f682h.size() > 0) {
            appendQueryParameter.appendQueryParameter("acquired_id", String.valueOf(TextUtils.join(",", this.f682h)));
        }
        return appendQueryParameter.toString();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: c */
    public void mo7862c(String str) {
        this.f682h.add(str);
        if (5 == this.f682h.size()) {
            this.f682h.remove(0);
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: j */
    public int mo7863j() {
        return this.f681g;
    }
}
