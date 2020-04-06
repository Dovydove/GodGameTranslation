package p004jp.p005co.imobile.sdkads.android;

import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: jp.co.imobile.sdkads.android.ImobileIconParams */
public class ImobileIconParams {

    /* renamed from: a */
    private int f32a = 4;

    /* renamed from: b */
    private int f33b = -1;

    /* renamed from: c */
    private boolean f34c = true;

    /* renamed from: d */
    private String f35d = "";

    /* renamed from: e */
    private boolean f36e = true;

    /* renamed from: f */
    private String f37f = "";

    /* renamed from: g */
    private int f38g = -1;

    /* renamed from: h */
    private int f39h = -1;

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final JSONObject mo7173a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("iconNumber", this.f32a);
            jSONObject.put("iconViewLayoutWidth", this.f33b);
            jSONObject.put("iconTitleEnable", Boolean.toString(this.f34c));
            jSONObject.put("iconTitleFontColor", this.f35d);
            jSONObject.put("iconTitleShadowEnable", Boolean.toString(this.f36e));
            jSONObject.put("iconTitleShadowColor", this.f37f);
            jSONObject.put("iconTitleShadowDx", this.f38g);
            jSONObject.put("iconTitleShadowDy", this.f39h);
            return jSONObject;
        } catch (JSONException e) {
            e.getMessage();
            C0359x.m126b("Spot data to ad view data create.", "parse");
            throw new C0360y(FailNotificationReason.RESPONSE);
        }
    }

    public void setIconNumber(int iconNumber) {
        this.f32a = iconNumber;
    }

    public void setIconTitleEnable(boolean iconTitleEnable) {
        this.f34c = iconTitleEnable;
    }

    public void setIconTitleFontColor(String iconTitleFontColor) {
        this.f35d = iconTitleFontColor;
    }

    public void setIconTitleShadowColor(String iconTitleShadowColor) {
        this.f37f = iconTitleShadowColor;
    }

    public void setIconTitleShadowDx(int iconTitleShadowDx) {
        this.f38g = iconTitleShadowDx;
    }

    public void setIconTitleShadowDy(int iconTitleShadowDy) {
        this.f39h = iconTitleShadowDy;
    }

    public void setIconTitleShadowEnable(boolean iconTitleShadowEnable) {
        this.f36e = iconTitleShadowEnable;
    }

    public void setIconViewLayoutWidth(int iconViewLayoutWidth) {
        setIconViewLayoutWidth(iconViewLayoutWidth, true);
    }

    public void setIconViewLayoutWidth(int adIconViewLayoutWidth, boolean convert) {
        if (convert) {
            C0353r.m99a();
            this.f33b = C0353r.m94a(adIconViewLayoutWidth);
            return;
        }
        this.f33b = adIconViewLayoutWidth;
    }
}
