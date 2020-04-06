package net.nend.android;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.support.annotation.VisibleForTesting;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import net.nend.android.internal.p008b.p012c.C0540b;
import net.nend.android.internal.p023ui.activities.formats.FullscreenVideoPlayingActivity;
import net.nend.android.internal.p023ui.p024a.C0656e;
import net.nend.android.internal.p023ui.p024a.C0657f;
import net.nend.android.internal.p023ui.views.formats.FontFitTextView;
import net.nend.android.internal.p023ui.views.video.NendAdVideoView;
import net.nend.android.internal.p023ui.views.video.NendAdVideoView.C0727a;
import net.nend.android.internal.utilities.C0757f;
import net.nend.android.internal.utilities.C0761i;
import net.nend.android.internal.utilities.video.C0774f;
import net.nend.android.internal.utilities.video.NendVideoAdClientError;

public class NendAdNativeMediaView extends FrameLayout {
    public static final int FULLSCREEN_MEDIA_PROCESS_COMPLETION = 12;
    public static final int FULLSCREEN_MEDIA_PROCESS_ERROR = 13;
    public static final int FULLSCREEN_MEDIA_PROCESS_PROGRESS = 11;
    public static final int FULLSCREEN_RESULT_CLICK_AD = 2;
    public static final int FULLSCREEN_RESULT_CLICK_INFORMATION = 3;
    public static final int FULLSCREEN_RESULT_FINISH = 1;
    public static final String RESULT_CLICK_URL = "click_url";
    public static final String RESULT_CURRENT_MSEC = "msec";
    public static final String RESULT_ERROR_CODE = "errorCode";
    public static final String RESULT_ERROR_MESSAGE = "errorMessage";
    public static final String RESULT_IS_COMPLETION = "isCompletion";
    public static final String RESULT_REMAIN_MSEC = "remainingMsec";
    public static final String RESULT_TOTAL_MSEC = "totalMsec";
    @VisibleForTesting

    /* renamed from: a */
    boolean f400a = false;
    @VisibleForTesting

    /* renamed from: b */
    C0774f f401b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public Context f402c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public NendAdNativeVideo f403d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public NendAdVideoView f404e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public ImageView f405f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public FrameLayout f406g;

    /* renamed from: h */
    private View f407h;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public boolean f408i = false;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public int f409j = 0;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public int f410k = 0;

    /* renamed from: l */
    private int f411l;

    /* renamed from: m */
    private float f412m;

    /* renamed from: n */
    private float f413n;

    /* renamed from: o */
    private FontFitTextView f414o;

    /* renamed from: p */
    private FontFitTextView f415p;
    /* access modifiers changed from: private */

    /* renamed from: q */
    public NendAdNativeMediaViewListener f416q;

    /* renamed from: r */
    private OnClickListener f417r = new OnClickListener() {
        public void onClick(View view) {
            if (NendAdNativeMediaView.this.mo7598a()) {
                NendAdNativeMediaView.this.m316h();
                NendAdNativeMediaView.this.m319j();
            }
        }
    };

    /* renamed from: s */
    private OnClickListener f418s = new OnClickListener() {
        public void onClick(View view) {
            if (NendAdNativeMediaView.this.mo7598a()) {
                NendAdNativeMediaView.this.f403d.mo7633a(NendAdNativeMediaView.this.f402c, NendAdNativeMediaView.this.f403d.mo7641d(), false);
            }
        }
    };

    /* renamed from: t */
    private C0727a f419t = new C0727a() {
        public void onStartPlay() {
            NendAdNativeMediaView.this.f403d.mo7630a(NendAdNativeMediaView.this.f404e.getWidth(), NendAdNativeMediaView.this.f404e.getHeight());
            if (NendAdNativeMediaView.this.f416q != null) {
                NendAdNativeMediaView.this.f416q.onStartPlay(NendAdNativeMediaView.this);
            }
            NendAdNativeMediaView.this.f401b = C0774f.PLAYING;
            if (NendAdNativeMediaView.this.f405f.getVisibility() != 0) {
                C0656e.m940a(NendAdNativeMediaView.this.getWidth(), NendAdNativeMediaView.this.getHeight(), NendAdNativeMediaView.this.f405f);
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(NendAdNativeMediaView.this.f405f, "alpha", new float[]{0.0f, 1.0f});
                ofFloat.setDuration(1000);
                ofFloat.setStartDelay(0);
                ofFloat.addListener(new AnimatorListenerAdapter() {
                    public void onAnimationStart(Animator animator) {
                        NendAdNativeMediaView.this.f405f.setVisibility(0);
                    }
                });
                ofFloat.start();
            }
            NendAdNativeMediaView.this.m304c();
        }

        public void onPrepared() {
            NendAdNativeMediaView.this.m298b(NendAdNativeMediaView.this.f403d.mo7637b());
            NendAdNativeMediaView.this.m321k();
        }

        public void onCompletion(int i, boolean z) {
            NendAdNativeMediaView.this.m290a(i, z);
            C0757f.m1210a("onCompletion isWindowVisible: " + NendAdNativeMediaView.this.f408i);
            if (NendAdNativeMediaView.this.f408i && z) {
                NendAdNativeMediaView.this.m305c(0);
                NendAdNativeMediaView.this.m326m();
            }
        }

        public void onProgress(int i, int i2) {
            NendAdNativeMediaView.this.f410k = i;
            NendAdNativeMediaView.this.m288a(i, i2);
        }

        public void onError(int i, String str) {
            NendAdNativeMediaView.this.m289a(i, str);
        }
    };

    /* renamed from: u */
    private OnClickListener f420u = new OnClickListener() {
        public void onClick(View view) {
            if (NendAdNativeMediaView.this.mo7598a() && NendAdNativeMediaView.this.f403d != null) {
                switch (NendAdNativeMediaView.this.f403d.mo7640c()) {
                    case FullScreen:
                        NendAdNativeMediaView.this.setProgressDurationTime(NendAdNativeMediaView.this.f404e.getCurrentPosition());
                        Intent intent = new Intent(NendAdNativeMediaView.this.f402c, FullscreenVideoPlayingActivity.class);
                        intent.putExtras(FullscreenVideoPlayingActivity.newBundle(NendAdNativeMediaView.this.f409j, NendAdNativeMediaView.this.f403d.mo7628a(), NendAdNativeMediaView.this.f403d.isMutePlayingFullscreen(), NendAdNativeMediaView.this.f422w));
                        if (NendAdNativeMediaView.this.f402c instanceof Activity) {
                            Activity activity = (Activity) NendAdNativeMediaView.this.f402c;
                            activity.startActivity(intent);
                            activity.overridePendingTransition(17432576, 17432577);
                        } else {
                            intent.setFlags(ErrorDialogData.BINDER_CRASH);
                            NendAdNativeMediaView.this.f402c.startActivity(intent);
                        }
                        if (NendAdNativeMediaView.this.f416q != null) {
                            NendAdNativeMediaView.this.f416q.onOpenFullScreen(NendAdNativeMediaView.this);
                            break;
                        }
                        break;
                    case LP:
                        NendAdNativeMediaView.this.f403d.mo7633a(NendAdNativeMediaView.this.f402c, NendAdNativeMediaView.this.f403d.mo7641d(), false);
                        break;
                }
                NendAdNativeMediaView.this.f404e.setCallback(null);
                NendAdNativeMediaView.this.m318i();
            }
        }
    };

    /* renamed from: v */
    private OnPreDrawListener f421v;
    /* access modifiers changed from: private */

    /* renamed from: w */
    public ResultReceiver f422w = new ResultReceiver(new Handler(Looper.getMainLooper())) {
        /* access modifiers changed from: protected */
        public void onReceiveResult(int i, Bundle bundle) {
            super.onReceiveResult(i, bundle);
            if (NendAdNativeMediaView.this.f403d != null || i == 1 || i == 13) {
                switch (i) {
                    case 1:
                        if (NendAdNativeMediaView.this.f404e == null && NendAdNativeMediaView.this.f403d != null) {
                            NendAdNativeMediaView.this.m311f();
                        }
                        if (NendAdNativeMediaView.this.f416q != null) {
                            NendAdNativeMediaView.this.f416q.onCloseFullScreen(NendAdNativeMediaView.this);
                            return;
                        }
                        return;
                    case 2:
                        NendAdNativeMediaView.this.f403d.mo7633a(NendAdNativeMediaView.this.f402c, bundle.getString(NendAdNativeMediaView.RESULT_CLICK_URL), true);
                        NendAdNativeMediaView.this.f400a = true;
                        return;
                    case 3:
                        NendAdNativeMediaView.this.f403d.mo7631a(NendAdNativeMediaView.this.f402c);
                        NendAdNativeMediaView.this.f400a = true;
                        return;
                    case 11:
                        NendAdNativeMediaView.this.m288a(NendAdNativeMediaView.this.f410k, bundle.getInt(NendAdNativeMediaView.RESULT_REMAIN_MSEC));
                        return;
                    case 12:
                        boolean z = bundle.getBoolean(NendAdNativeMediaView.RESULT_IS_COMPLETION);
                        if (z || NendAdNativeMediaView.this.f400a) {
                            NendAdNativeMediaView.this.m290a(NendAdNativeMediaView.this.f409j, z);
                            NendAdNativeMediaView.this.f400a = false;
                            NendAdNativeMediaView.this.m316h();
                            return;
                        }
                        NendAdNativeMediaView.this.m298b(NendAdNativeMediaView.this.f409j);
                        return;
                    case 13:
                        if (NendAdNativeMediaView.this.f404e != null) {
                            NendAdNativeMediaView.this.m307d();
                            NendAdNativeMediaView.this.m326m();
                            return;
                        } else if (NendAdNativeMediaView.this.f403d != null) {
                            NendAdNativeMediaView.this.m311f();
                            return;
                        } else {
                            NendAdNativeMediaView.this.m289a(bundle.getInt(NendAdNativeMediaView.RESULT_ERROR_CODE), bundle.getString(NendAdNativeMediaView.RESULT_ERROR_MESSAGE));
                            return;
                        }
                    default:
                        return;
                }
            }
        }
    };

    /* renamed from: x */
    private BroadcastReceiver f423x = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("android.intent.action.SCREEN_ON".equals(action)) {
                NendAdNativeMediaView.this.m321k();
                if (NendAdNativeMediaView.this.f408i) {
                    NendAdNativeMediaView.this.invalidate();
                }
            } else if ("android.intent.action.SCREEN_OFF".equals(action)) {
                NendAdNativeMediaView.this.m326m();
                if (NendAdNativeMediaView.this.f406g.getVisibility() == 0) {
                    NendAdNativeMediaView.this.m316h();
                }
            }
        }
    };

    public NendAdNativeMediaView(Context context) {
        super(context);
        m291a(context);
    }

    public NendAdNativeMediaView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        m291a(context);
    }

    @TargetApi(21)
    public NendAdNativeMediaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, 0);
        m291a(context);
    }

    @TargetApi(21)
    public NendAdNativeMediaView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        m291a(context);
    }

    /* renamed from: a */
    private void m291a(Context context) {
        this.f401b = C0774f.PREPARING;
        this.f402c = context;
        inflate(context, C0761i.m1234c(getContext(), "view_native_media"), this);
        this.f411l = C0761i.m1233b(getContext(), "native_media_row_videoview");
        this.f404e = (NendAdVideoView) findViewById(this.f411l);
        this.f406g = (FrameLayout) findViewById(C0761i.m1233b(getContext(), "native_media_row_replay_area"));
        FrameLayout frameLayout = (FrameLayout) findViewById(C0761i.m1233b(getContext(), "native_media_row_action_area"));
        frameLayout.findViewById(C0761i.m1233b(getContext(), "native_video_fullscreen_action_cta")).setVisibility(8);
        frameLayout.findViewById(C0761i.m1233b(getContext(), "native_video_fullscreen_action_toggle_volume")).setVisibility(8);
        this.f405f = (ImageView) frameLayout.findViewById(C0761i.m1233b(getContext(), "native_video_fullscreen_action_optout"));
        this.f405f.setVisibility(4);
    }

    /* renamed from: a */
    private void m297a(boolean z) {
        if (z) {
            m321k();
            return;
        }
        m326m();
        if (this.f406g.getVisibility() == 0) {
            m316h();
        }
    }

    /* renamed from: b */
    private boolean m301b() {
        if (VERSION.SDK_INT < 24 || !(this.f402c instanceof Activity)) {
            return true;
        }
        return !((Activity) this.f402c).isInMultiWindowMode();
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        C0757f.m1210a("visibility: " + i);
        this.f408i = i == 0;
        m297a(this.f408i);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        C0757f.m1210a("hasWindowFocus: " + z);
        if (m301b()) {
            m297a(z);
            if (z) {
                invalidate();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f402c.registerReceiver(this.f423x, new IntentFilter("android.intent.action.SCREEN_ON"));
        this.f402c.registerReceiver(this.f423x, new IntentFilter("android.intent.action.SCREEN_OFF"));
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        C0757f.m1210a(" videoView object is " + (this.f404e != null ? "still allocated." : "destroyed."));
        if (this.f404e != null) {
            m326m();
            if (this.f406g.getVisibility() == 0) {
                m316h();
            }
            m307d();
        }
        try {
            this.f402c.unregisterReceiver(this.f423x);
        } catch (IllegalArgumentException e) {
            C0757f.m1210a("Screen action receiver is already unregistered");
        }
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        m304c();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z) {
            m304c();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public void m304c() {
        if (this.f404e != null && this.f403d != null) {
            float width = (float) getWidth();
            float height = (float) getHeight();
            if (width > 0.0f && height > 0.0f) {
                if (width != this.f412m || height != this.f413n) {
                    this.f413n = height;
                    this.f412m = width;
                    int videoOrientation = this.f403d.getVideoOrientation();
                    C0656e.m942a(this.f402c, this, width, height, videoOrientation);
                    m287a(videoOrientation);
                }
            }
        }
    }

    /* renamed from: a */
    private void m287a(int i) {
        int a = (int) C0656e.m934a((View) this.f404e, i);
        this.f414o.mo8141a(a);
        this.f415p.mo8141a(a);
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public void m307d() {
        this.f404e.setCallback(null);
        this.f404e.mo8157e();
        this.f404e.mo8158f();
        this.f404e = null;
    }

    public void setMediaViewListener(NendAdNativeMediaViewListener nendAdNativeMediaViewListener) {
        this.f416q = nendAdNativeMediaViewListener;
    }

    public void setMedia(NendAdNativeVideo nendAdNativeVideo) {
        if (this.f403d != nendAdNativeVideo) {
            m309e();
        }
        this.f403d = nendAdNativeVideo;
        this.f403d.mo7635a((C0504b) new C0504b() {
            /* renamed from: a */
            public void mo7607a() {
                NendAdNativeMediaView.this.m326m();
                NendAdNativeMediaView.this.m309e();
                NendAdNativeMediaView.this.f403d = null;
            }
        });
        m311f();
    }

    /* access modifiers changed from: private */
    /* renamed from: e */
    public void m309e() {
        if (this.f404e != null) {
            m307d();
            this.f401b = C0774f.PREPARING;
        }
        if (this.f407h != null) {
            this.f406g.removeView(this.f407h);
            this.f407h = null;
        }
        this.f413n = 0.0f;
        this.f412m = 0.0f;
    }

    /* access modifiers changed from: private */
    /* renamed from: f */
    public void m311f() {
        C0540b a = this.f403d.mo7628a();
        if (a == null || !a.mo7780a()) {
            m289a(NendVideoAdClientError.INVALID_AD_DATA.getCode(), NendVideoAdClientError.INVALID_AD_DATA.getMessage());
            return;
        }
        if (this.f404e == null) {
            this.f404e = (NendAdVideoView) findViewById(this.f411l);
        }
        this.f404e.setCallback(this.f419t);
        this.f404e.mo8152a(a.f568g, true);
        this.f404e.setOnClickListener(this.f420u);
        this.f405f.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                NendAdNativeMediaView.this.f403d.mo7631a(NendAdNativeMediaView.this.f402c);
            }
        });
        m314g();
    }

    /* renamed from: g */
    private void m314g() {
        this.f406g.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
            }
        });
        if (this.f407h == null) {
            this.f407h = C0656e.m937a(this.f406g, this.f402c, this.f403d.mo7628a());
            ((ImageButton) findViewById(C0761i.m1233b(getContext(), "media_view_button_replay"))).setOnClickListener(this.f417r);
            this.f414o = (FontFitTextView) findViewById(C0761i.m1233b(getContext(), "description_media_view_button_replay"));
            this.f414o.setOnClickListener(this.f417r);
            ((ImageButton) findViewById(C0761i.m1233b(getContext(), "media_view_button_cta"))).setOnClickListener(this.f418s);
            this.f415p = (FontFitTextView) findViewById(C0761i.m1233b(getContext(), "description_media_view_button_cta"));
            this.f415p.setOnClickListener(this.f418s);
        }
        this.f415p.setText(this.f403d.getCallToActionText());
        if (this.f401b != C0774f.COMPLETED) {
            m305c(8);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: h */
    public void m316h() {
        setProgressDurationTime(0);
        if (this.f403d != null) {
            m298b(this.f403d.mo7637b());
        }
        m305c(8);
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m298b(int i) {
        if (this.f404e != null) {
            this.f404e.mo8150a(i);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: i */
    public void m318i() {
        this.f404e.mo8156d();
        if (this.f401b != C0774f.COMPLETED) {
            this.f401b = C0774f.PAUSING;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: j */
    public void m319j() {
        if (this.f404e.mo8153a()) {
            m321k();
            return;
        }
        this.f404e.setCallback(this.f419t);
        this.f404e.mo8154b();
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public void m305c(int i) {
        if (i == 0) {
            m287a(this.f403d.getVideoOrientation());
        } else {
            this.f413n = 0.0f;
            this.f412m = 0.0f;
        }
        this.f406g.setVisibility(i);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m288a(int i, int i2) {
        setProgressDurationTime(i - i2);
        if (this.f403d.mo7636a(this.f409j, false)) {
            this.f403d.mo7638b(this.f402c);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m290a(int i, boolean z) {
        if (this.f403d.mo7636a(i, z)) {
            this.f403d.mo7638b(this.f402c);
        }
        setProgressDurationTime(i);
        if (z) {
            this.f401b = C0774f.COMPLETED;
        } else if (this.f401b != C0774f.COMPLETED) {
            this.f401b = C0774f.PAUSING;
        }
        this.f403d.mo7632a(this.f402c, i, z);
        if (this.f416q == null) {
            return;
        }
        if (z) {
            this.f416q.onCompletePlay(this);
        } else {
            this.f416q.onStopPlay(this);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m289a(int i, String str) {
        C0757f.m1222d("NendAdNativeMediaView: " + i + " :" + str);
        if (this.f416q != null) {
            this.f416q.onError(i, str);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: k */
    public void m321k() {
        if (this.f421v == null) {
            ViewTreeObserver viewTreeObserver = getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                this.f421v = new OnPreDrawListener() {
                    public boolean onPreDraw() {
                        NendAdNativeMediaView.this.m323l();
                        return true;
                    }
                };
                viewTreeObserver.addOnPreDrawListener(this.f421v);
            }
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: l */
    public void m323l() {
        if (this.f404e != null && this.f406g.getVisibility() == 8) {
            if (!mo7598a()) {
                m318i();
            } else if (this.f404e.mo8153a() && this.f401b != C0774f.PLAYING) {
                this.f404e.setCallback(this.f419t);
                this.f404e.setMute(true);
                this.f404e.mo8155c();
                this.f401b = C0774f.PLAYING;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    /* renamed from: a */
    public boolean mo7598a() {
        return this.f408i && C0657f.m950a(getRootView(), (View) this, 50);
    }

    /* access modifiers changed from: private */
    /* renamed from: m */
    public void m326m() {
        if (this.f421v != null) {
            ViewTreeObserver viewTreeObserver = getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(this.f421v);
                this.f421v = null;
                if (this.f404e != null) {
                    setProgressDurationTime(this.f404e.getCurrentPosition());
                    m318i();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void setProgressDurationTime(int i) {
        this.f409j = i;
        C0757f.m1210a("progressDuration: " + this.f409j);
        if (this.f403d != null) {
            this.f403d.mo7629a(this.f409j);
        }
    }
}
