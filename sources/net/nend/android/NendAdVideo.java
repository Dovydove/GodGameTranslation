package net.nend.android;

import android.app.Activity;

public interface NendAdVideo {
    boolean isLoaded();

    void loadAd();

    void releaseAd();

    void setMediationName(String str);

    void setUserFeature(NendAdUserFeature nendAdUserFeature);

    void setUserId(String str);

    void showAd(Activity activity);
}
