package net.nend.android;

import android.app.Activity;

public interface NendNativeAdConnector {
    String getActionButtonText();

    String getAdImageUrl();

    String getAdvertisingExplicitlyText(int i);

    String getLogoImageUrl();

    String getLongText();

    String getPromotionName();

    String getPromotionUrl();

    String getShortText();

    void performAdClick(Activity activity);

    void performInformationClick(Activity activity);

    void sendImpression();

    void setSpotId(String str);
}
