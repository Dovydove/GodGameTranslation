package net.nend.android;

public interface NendAdVideoListener {
    void onAdClicked(NendAdVideo nendAdVideo);

    void onClosed(NendAdVideo nendAdVideo);

    void onCompleted(NendAdVideo nendAdVideo);

    void onFailedToLoad(NendAdVideo nendAdVideo, int i);

    void onFailedToPlay(NendAdVideo nendAdVideo);

    void onInformationClicked(NendAdVideo nendAdVideo);

    void onLoaded(NendAdVideo nendAdVideo);

    void onShown(NendAdVideo nendAdVideo);

    void onStarted(NendAdVideo nendAdVideo);

    void onStopped(NendAdVideo nendAdVideo);
}
