package net.nend.android.internal.p023ui.activities.video;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.support.p000v4.view.ViewCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.RelativeLayout;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import net.nend.android.internal.p008b.p012c.C0544d;
import net.nend.android.internal.p013c.p021f.C0604b;
import net.nend.android.internal.p023ui.p024a.C0654d;
import net.nend.android.internal.p023ui.p024a.C0654d.C0655a;
import net.nend.android.internal.p023ui.views.video.C0728a;
import net.nend.android.internal.p023ui.views.video.C0728a.C0730a;
import net.nend.android.internal.p023ui.views.video.C0731b;
import net.nend.android.internal.p023ui.views.video.C0731b.C0734b;
import net.nend.android.internal.p023ui.views.video.NendAdVideoView;
import net.nend.android.internal.p023ui.views.video.NendAdVideoView.C0727a;
import net.nend.android.internal.utilities.C0740a;
import net.nend.android.internal.utilities.C0740a.C0742a;
import net.nend.android.internal.utilities.C0740a.C0745d;
import net.nend.android.internal.utilities.C0755e;
import net.nend.android.internal.utilities.C0757f;
import net.nend.android.internal.utilities.C0761i;
import net.nend.android.internal.utilities.video.C0762a;
import net.nend.android.internal.utilities.video.C0764b;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: net.nend.android.internal.ui.activities.video.a */
/* compiled from: NendAdVideoActivity */
public abstract class C0687a<Ad extends C0544d> extends Activity implements C0727a {
    public static final int RESULT_CODE_AD_CLICK = 5;
    public static final int RESULT_CODE_AD_CLOSE = 1;
    public static final int RESULT_CODE_AD_FAILED_PLAY = 7;
    public static final int RESULT_CODE_AD_SHOWN = 2;
    public static final int RESULT_CODE_AD_START = 3;
    public static final int RESULT_CODE_AD_STOP = 4;
    public static final int RESULT_CODE_INFO_CLICK = 6;
    public static final String RESULT_DATA_KEY_VIDEO_IS_COMPLETION = "videoIsCompletion";

    /* renamed from: a */
    protected NendAdVideoView f998a;
    @Nullable

    /* renamed from: b */
    protected C0731b f999b;

    /* renamed from: c */
    protected boolean f1000c;

    /* renamed from: d */
    protected Ad f1001d;

    /* renamed from: e */
    protected int f1002e;

    /* renamed from: f */
    protected ResultReceiver f1003f;

    /* renamed from: g */
    protected boolean f1004g;

    /* renamed from: h */
    private FrameLayout f1005h;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public C0731b f1006i;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public C0728a f1007j;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public int f1008k;

    /* renamed from: l */
    private boolean f1009l;

    /* renamed from: m */
    private boolean f1010m;
    /* access modifiers changed from: private */

    /* renamed from: n */
    public boolean f1011n = false;

    /* renamed from: o */
    private boolean f1012o = false;

    /* renamed from: p */
    private float f1013p;
    @Nullable

    /* renamed from: q */
    private Rect f1014q;
    /* access modifiers changed from: private */

    /* renamed from: r */
    public BlockingQueue<C0654d> f1015r = new LinkedBlockingQueue();

    /* renamed from: s */
    private final Runnable f1016s = new Runnable() {
        public void run() {
            while (true) {
                try {
                    final C0654d dVar = (C0654d) C0687a.this.f1015r.take();
                    C0687a.this.runOnUiThread(new Runnable() {
                        public void run() {
                            switch (C06957.f1029a[dVar.mo8018a().ordinal()]) {
                                case 1:
                                    if (dVar.mo8020c().equals("html_on_playing")) {
                                        C0687a.this.m1026f();
                                        return;
                                    } else {
                                        C0687a.this.m1028g();
                                        return;
                                    }
                                case 2:
                                    C0687a.this.m1015a((String) dVar.mo8019b());
                                    return;
                                case 3:
                                    C0687a.this.m1030h();
                                    return;
                                case 4:
                                    C0687a.this.m1031i();
                                    return;
                                case 5:
                                    C0687a.this.m1016a((String) dVar.mo8019b(), dVar.mo8020c());
                                    return;
                                default:
                                    return;
                            }
                        }
                    });
                } catch (InterruptedException e) {
                    if (C0687a.this.isFinishing()) {
                        return;
                    }
                }
            }
        }
    };

    /* renamed from: t */
    private C0734b f1017t = new C0734b() {
        /* renamed from: a */
        public void mo8061a() {
            C0687a.this.f1011n = true;
            if (C0687a.this.f998a.getVisibility() == 8) {
                C0687a.this.mo8065a((WebView) C0687a.this.f1006i, "showNendVideoEndcard()");
            }
        }
    };

    /* renamed from: u */
    private ExecutorService f1018u;

    /* renamed from: v */
    private C0730a f1019v = new C0730a() {
        /* renamed from: a */
        public void mo8080a(boolean z) {
            C0687a.this.f998a.setMute(z);
            C0687a.this.f1004g = z;
        }
    };

    /* renamed from: w */
    private C0762a f1020w;

    /* renamed from: net.nend.android.internal.ui.activities.video.a$7 */
    /* compiled from: NendAdVideoActivity */
    static /* synthetic */ class C06957 {

        /* renamed from: a */
        static final /* synthetic */ int[] f1029a = new int[C0655a.values().length];

        static {
            try {
                f1029a[C0655a.CLICK_AD.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1029a[C0655a.VIDEO_RECT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f1029a[C0655a.CLICK_INFORMATION.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f1029a[C0655a.CLICK_CLOSE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f1029a[C0655a.VIEW_SOURCE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        boolean z;
        super.onCreate(bundle);
        this.f1018u = Executors.newSingleThreadExecutor();
        this.f1018u.execute(this.f1016s);
        this.f1012o = false;
        if (bundle == null) {
            Intent intent = getIntent();
            this.f1001d = (C0544d) intent.getParcelableExtra("videoAd");
            this.f1003f = (ResultReceiver) intent.getParcelableExtra("resultReceiver");
            if (this.f1001d == null || this.f1003f == null) {
                C0757f.m1222d("Failed to showAd. It may have failed with loadAd.");
                finish();
                return;
            }
            this.f1008k = intent.getIntExtra("spotId", 0);
            z = false;
        } else {
            this.f1001d = (C0544d) bundle.getParcelable("save_videoAd");
            this.f1003f = (ResultReceiver) bundle.getParcelable("save_result_receiver");
            this.f1002e = bundle.getInt("save_video_played_duration");
            boolean z2 = bundle.getBoolean("save_state_showing_endcard");
            this.f1009l = bundle.getBoolean("save_is_completion_event_start");
            this.f1010m = bundle.getBoolean("save_is_completion_event_view");
            this.f1004g = bundle.getBoolean("save_is_mute");
            this.f1020w = (C0762a) bundle.getParcelable("endcard_display_time");
            z = z2;
        }
        if (!this.f1001d.mo7780a()) {
            C0757f.m1222d("Failed to showAd. Because required ad data is not found or ad is expired.");
            this.f1003f.send(7, null);
            finish();
            return;
        }
        m1021b(z);
        if (!z) {
            this.f1013p = getResources().getDisplayMetrics().density;
            m1014a(this.f1001d.f619l, getResources().getConfiguration().orientation);
            this.f1020w = new C0762a();
        }
        if (bundle == null) {
            this.f1003f.send(2, null);
        }
        this.f1012o = true;
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        if (this.f998a.getVisibility() == 0) {
            this.f998a.mo8155c();
        } else {
            this.f1020w.mo8202a();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (VERSION.SDK_INT >= 24) {
            m1032j();
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        if (this.f998a.getVisibility() == 0) {
            this.f998a.mo8156d();
        } else {
            this.f1020w.mo8203b();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.f1018u.shutdownNow();
        if (isFinishing() && this.f1012o) {
            this.f1003f.send(1, null);
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("save_videoAd", this.f1001d);
        bundle.putParcelable("save_result_receiver", this.f1003f);
        bundle.putInt("save_video_played_duration", this.f1002e);
        bundle.putBoolean("save_state_showing_endcard", this.f998a.getVisibility() == 8);
        bundle.putBoolean("save_is_completion_event_start", this.f1009l);
        bundle.putBoolean("save_is_completion_event_view", this.f1010m);
        bundle.putBoolean("save_is_mute", this.f1004g);
        bundle.putParcelable("endcard_display_time", this.f1020w);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z && VERSION.SDK_INT >= 19) {
            m1033k();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.f998a.getVisibility() != 8) {
            m1014a(this.f1001d.f619l, configuration.orientation);
        }
    }

    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        if (z) {
            m1032j();
        } else {
            m1033k();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m1015a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.f1014q = new Rect((int) (((float) jSONObject.getInt("left")) * this.f1013p), (int) (((float) jSONObject.getInt("top")) * this.f1013p), (int) (((float) jSONObject.getInt("right")) * this.f1013p), (int) (((float) jSONObject.getInt("bottom")) * this.f1013p));
            if (this.f1001d.f564c != getResources().getConfiguration().orientation) {
                m1037o();
            }
        } catch (JSONException e) {
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: f */
    public void m1026f() {
        this.f1000c = true;
        mo8069d();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                C0687a.this.mo8064a(C0687a.this.getApplicationContext());
            }
        }, 1000);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m1016a(String str, String str2) {
        if (str2.equals("html_on_playing") && this.f999b != null && this.f999b.mo8174b(str)) {
            mo8069d();
        } else if (str2.equals("end_card") && this.f1006i.mo8174b(str)) {
            mo8069d();
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: g */
    public void m1028g() {
        mo8064a((Context) this);
    }

    /* access modifiers changed from: private */
    /* renamed from: h */
    public void m1030h() {
        this.f1003f.send(6, null);
        C0740a.m1170a().mo8183a(new C0745d(this), new C0742a<String>() {
            /* renamed from: a */
            public void mo7491a(String str, Exception exc) {
                C0755e.m1198a((Context) C0687a.this, "https://www.nend.net/privacy/optsdkgate?uid=" + C0755e.m1201b(C0687a.this) + "&spot=" + C0687a.this.f1008k + "&gaid=" + str);
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: i */
    public void m1031i() {
        mo8069d();
    }

    /* renamed from: j */
    private void m1032j() {
        getWindow().getDecorView().setSystemUiVisibility(0);
    }

    /* renamed from: k */
    private void m1033k() {
        if (VERSION.SDK_INT < 24 || !isInMultiWindowMode()) {
            View decorView = getWindow().getDecorView();
            if (VERSION.SDK_INT >= 19) {
                decorView.setSystemUiVisibility(5894);
            }
        }
    }

    /* renamed from: a */
    private void m1014a(int i, int i2) {
        switch (i) {
            case 2:
                switch (this.f1001d.f564c) {
                    case 1:
                        setRequestedOrientation(7);
                        break;
                    case 2:
                        setRequestedOrientation(6);
                        break;
                }
            case 3:
            case 4:
                switch (this.f1001d.f564c) {
                    case 1:
                        setRequestedOrientation(6);
                        break;
                    case 2:
                        setRequestedOrientation(7);
                        break;
                }
                if (i2 != this.f1001d.f564c) {
                    mo8054a(true);
                    if (this.f1014q != null) {
                        m1037o();
                        break;
                    }
                } else {
                    mo8054a(false);
                    this.f998a.setLayoutParams(new LayoutParams(-1, -1));
                    break;
                }
                break;
        }
        C0757f.m1210a("displayType: " + i + ", deviceOrientation: " + i2);
    }

    /* renamed from: b */
    private void m1021b(boolean z) {
        this.f1005h = new FrameLayout(this);
        this.f1005h.setLayoutParams(new LayoutParams(-1, -1));
        setContentView(this.f1005h);
        m1035m();
        if ((!z && this.f1001d.f619l == 3) || this.f1001d.f619l == 4) {
            m1036n();
        }
        m1034l();
        mo8053a();
        if (z) {
            this.f1006i.bringToFront();
            this.f998a.setVisibility(8);
            return;
        }
        this.f998a.setVisibility(0);
        this.f998a.setUpVideo(this.f1001d.f568g);
    }

    /* renamed from: l */
    private void m1034l() {
        this.f998a = new NendAdVideoView(this);
        this.f998a.setCallback(this);
        this.f998a.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.f1005h.addView(this.f998a, new LayoutParams(-1, -1));
        this.f998a.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (C0687a.this.f1007j.getVisibility() == 0) {
                    C0687a.this.f1007j.setVisibility(8);
                } else {
                    C0687a.this.f1007j.setVisibility(0);
                }
            }
        });
    }

    /* renamed from: m */
    private void m1035m() {
        this.f1006i = new C0731b(this, this.f1015r, "end_card");
        this.f1006i.setWebViewClientListener(this.f1017t);
        this.f1006i.mo8173a(this.f1001d.f622o);
        this.f1005h.addView(this.f1006i, new LayoutParams(-1, -1));
    }

    /* renamed from: n */
    private void m1036n() {
        if (this.f999b == null) {
            this.f999b = new C0731b(this, this.f1015r, "html_on_playing");
            this.f999b.mo8173a(this.f1001d.f623p);
            this.f1005h.addView(this.f999b, new LayoutParams(-1, -1));
        }
    }

    /* renamed from: o */
    private void m1037o() {
        if (this.f1014q != null) {
            LayoutParams layoutParams = new LayoutParams(this.f1014q.width(), this.f1014q.height());
            layoutParams.setMargins(this.f1014q.left, this.f1014q.top, 0, 0);
            this.f998a.setLayoutParams(layoutParams);
        }
    }

    /* renamed from: p */
    private void m1038p() {
        if (this.f1011n) {
            mo8065a((WebView) this.f1006i, "showNendVideoEndcard()");
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo8065a(WebView webView, String str) {
        if (VERSION.SDK_INT >= 19) {
            webView.evaluateJavascript(str, null);
        } else {
            webView.loadUrl("javascript:" + str);
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo8053a() {
        boolean z = false;
        int dimensionPixelSize = getResources().getDimensionPixelSize(C0761i.m1237f(this, "nend_video_ad_overlay_elements_margin"));
        this.f1007j = new C0728a(this, this.f1019v);
        this.f1007j.setPadding(0, 0, 0, dimensionPixelSize);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(12);
        this.f998a.addView(this.f1007j, layoutParams);
        C0728a aVar = this.f1007j;
        if (!this.f1004g) {
            z = true;
        }
        aVar.setCheckToggleButton(z);
        this.f1007j.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo8054a(boolean z) {
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo8066a(C0731b bVar) {
        bVar.stopLoading();
        bVar.getSettings().setJavaScriptEnabled(false);
        bVar.removeJavascriptInterface("nendSDK");
        bVar.setWebViewClient(null);
        bVar.setWebChromeClient(null);
        bVar.destroy();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public void mo8067b() {
        this.f998a.mo8157e();
        this.f998a.mo8158f();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: c */
    public void mo8068c() {
        this.f1005h.removeAllViews();
        mo8066a(this.f1006i);
        if (this.f999b != null) {
            mo8066a(this.f999b);
            this.f999b = null;
        }
        mo8067b();
        C0604b.m800a((Context) this, this.f1001d.f562a, this.f1020w);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: d */
    public void mo8069d() {
        mo8068c();
        finish();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public void mo8070e() {
        this.f998a.mo8157e();
        this.f998a.setVisibility(8);
        this.f1006i.bringToFront();
        m1038p();
        if (this.f999b != null) {
            this.f1005h.removeView(this.f999b);
            mo8066a(this.f999b);
            this.f999b = null;
        }
        this.f1020w.mo8202a();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo8064a(Context context) {
        this.f1003f.send(5, null);
        C0604b.m812c(context, this.f1001d.f562a);
        C0755e.m1198a(context, this.f1001d.f563b);
    }

    public void onStartPlay() {
        this.f998a.setMute(this.f1004g);
        this.f1003f.send(3, null);
        if (!this.f1009l) {
            this.f1009l = true;
            C0604b.m798a((Context) this, this.f1001d.f562a);
        }
    }

    public void onPrepared() {
        this.f998a.mo8150a(this.f1002e);
        this.f998a.mo8155c();
    }

    public void onCompletion(int i, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(RESULT_DATA_KEY_VIDEO_IS_COMPLETION, z);
        this.f1003f.send(4, bundle);
        C0604b.m803a(this, this.f1001d.f562a, z, this.f1000c, i);
        if (!this.f1010m && z && this.f1001d.f566e == -1) {
            this.f1010m = true;
            C0604b.m810b(this, this.f1001d.f562a);
        }
        if (z) {
            mo8070e();
        }
    }

    public void onProgress(int i, int i2) {
        int i3 = i - i2;
        this.f1002e = i3;
        if (this.f1001d.f566e > -1 && C0764b.m1245a(i3) > this.f1001d.f566e && !this.f1010m) {
            this.f1010m = true;
            C0604b.m810b(this, this.f1001d.f562a);
        }
        this.f1007j.setCounterText(String.valueOf(C0764b.m1245a(i2)));
    }

    public void onError(int i, String str) {
        C0757f.m1222d("NendAd internal error:" + str);
        this.f1003f.send(7, null);
        mo8069d();
    }
}
