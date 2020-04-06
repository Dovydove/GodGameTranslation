package net.nend.android;

public interface NendAdNativeMediaViewListener {
    void onCloseFullScreen(NendAdNativeMediaView nendAdNativeMediaView);

    void onCompletePlay(NendAdNativeMediaView nendAdNativeMediaView);

    void onError(int i, String str);

    void onOpenFullScreen(NendAdNativeMediaView nendAdNativeMediaView);

    void onStartPlay(NendAdNativeMediaView nendAdNativeMediaView);

    void onStopPlay(NendAdNativeMediaView nendAdNativeMediaView);
}
