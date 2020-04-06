package p004jp.p005co.imobile.sdkads.android;

import android.app.Activity;
import android.content.Context;
import android.support.p000v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

/* renamed from: jp.co.imobile.sdkads.android.ImobileInlineView */
public class ImobileInlineView extends RelativeLayout {
    public ImobileInlineView(Context context) {
        this(context, null, 0);
    }

    public ImobileInlineView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ImobileInlineView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (isInEditMode()) {
            TextView textView = new TextView(context);
            textView.setText(" ImobileInlineView V1.3");
            textView.setPadding(5, 5, 5, 5);
            textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            textView.setTextSize(26.0f);
            addView(textView, new LayoutParams(-2, -2));
            setBackgroundColor(-3355444);
            return;
        }
        ImobileIconParams imobileIconParams = new ImobileIconParams();
        if (attrs != null) {
            String attributeValue = attrs.getAttributeValue(null, "pid");
            if (attributeValue == null || attributeValue.trim().equals("")) {
                C0359x.m124a("ImobileInlineView XML Parameter Error", "Please set publisherId.");
                return;
            }
            String attributeValue2 = attrs.getAttributeValue(null, "mid");
            if (attributeValue2 == null || attributeValue2.trim().equals("")) {
                C0359x.m124a("ImobileInlineView XML Parameter Error", "Please set mediaId.");
                return;
            }
            String attributeValue3 = attrs.getAttributeValue(null, "sid");
            if (attributeValue3 == null || attributeValue3.trim().equals("")) {
                C0359x.m124a("ImobileInlineView XML Parameter Error", "Please set mediaId.");
                return;
            }
            Activity activity = (Activity) context;
            ImobileSdkAd.registerSpotInline(activity, attributeValue, attributeValue2, attributeValue3);
            ImobileSdkAd.start(attributeValue3);
            imobileIconParams.setIconNumber(attrs.getAttributeIntValue(null, "iconNumber", 4));
            if (attrs.getAttributeIntValue(null, "iconViewLayoutWidth", 0) != 0) {
                imobileIconParams.setIconViewLayoutWidth(attrs.getAttributeIntValue(null, "iconViewLayoutWidth", -1));
            }
            imobileIconParams.setIconTitleEnable(attrs.getAttributeBooleanValue(null, "iconTitleEnable", true));
            if (attrs.getAttributeValue(null, "iconTitleFontColor") != null) {
                imobileIconParams.setIconTitleFontColor(attrs.getAttributeValue(null, "iconTitleFontColor"));
            }
            imobileIconParams.setIconTitleShadowEnable(attrs.getAttributeBooleanValue(null, "iconTitleShadowEnable", true));
            if (attrs.getAttributeValue(null, "iconTitleShadowColor") != null) {
                imobileIconParams.setIconTitleShadowColor(attrs.getAttributeValue(null, "iconTitleShadowColor"));
            }
            imobileIconParams.setIconTitleShadowDx(attrs.getAttributeIntValue(null, "iconTitleShadowDx", -1));
            imobileIconParams.setIconTitleShadowDy(attrs.getAttributeIntValue(null, "iconTitleShadowDy", -1));
            ImobileSdkAd.showAd(activity, attributeValue3, this, imobileIconParams);
            return;
        }
        C0359x.m124a("ImobileInlineView XML Parameter Error", "Please ser spotId.");
    }
}
