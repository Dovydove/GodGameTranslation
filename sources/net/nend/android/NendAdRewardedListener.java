package net.nend.android;

public interface NendAdRewardedListener extends NendAdVideoListener {
    void onRewarded(NendAdVideo nendAdVideo, NendAdRewardItem nendAdRewardItem);
}
