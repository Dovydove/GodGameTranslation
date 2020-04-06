package net.nend.android.internal.p023ui.activities.formats;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.support.annotation.VisibleForTesting;
import android.support.constraint.ConstraintLayout;
import android.support.p000v4.view.ViewCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executor;
import net.nend.android.NendAdNativeMediaView;
import net.nend.android.internal.p008b.p012c.C0540b;
import net.nend.android.internal.p013c.p021f.C0608c;
import net.nend.android.internal.p022d.C0627a;
import net.nend.android.internal.p022d.C0630d;
import net.nend.android.internal.p023ui.p024a.C0656e;
import net.nend.android.internal.p023ui.views.formats.FontFitTextView;
import net.nend.android.internal.p023ui.views.formats.RoundedImageView;
import net.nend.android.internal.p023ui.views.video.NendAdVideoView;
import net.nend.android.internal.p023ui.views.video.NendAdVideoView.C0727a;
import net.nend.android.internal.utilities.C0755e;
import net.nend.android.internal.utilities.C0757f;
import net.nend.android.internal.utilities.C0761i;
import net.nend.android.internal.utilities.p027a.C0749a;
import net.nend.android.internal.utilities.video.C0774f;
import net.nend.android.internal.utilities.video.NendVideoAdClientError;

/* renamed from: net.nend.android.internal.ui.activities.formats.FullscreenVideoPlayingActivity */
public class FullscreenVideoPlayingActivity extends Activity {

    /* renamed from: p */
    private static final ArrayList<C0774f> f942p = new ArrayList<C0774f>() {
        {
            add(C0774f.PREPARING);
            add(C0774f.PLAYING);
            add(C0774f.PAUSING);
            add(C0774f.COMPLETED);
        }
    };
    @VisibleForTesting

    /* renamed from: a */
    C0774f f943a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public ResultReceiver f944b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public NendAdVideoView f945c;

    /* renamed from: d */
    private C0540b f946d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public FrameLayout f947e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public ToggleButton f948f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public boolean f949g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public int f950h = 0;

    /* renamed from: i */
    private int f951i;

    /* renamed from: j */
    private int f952j;

    /* renamed from: k */
    private int f953k;
    /* access modifiers changed from: private */

    /* renamed from: l */
    public boolean f954l;

    /* renamed from: m */
    private OnClickListener f955m = new OnClickListener() {
        public void onClick(View view) {
            FullscreenVideoPlayingActivity.this.m974d();
            FullscreenVideoPlayingActivity.this.m977f();
        }
    };

    /* renamed from: n */
    private OnClickListener f956n = new OnClickListener() {
        public void onClick(View view) {
            FullscreenVideoPlayingActivity.this.m975e();
        }
    };

    /* renamed from: o */
    private C0727a f957o = new C0727a() {
        public void onStartPlay() {
        }

        public void onPrepared() {
            FullscreenVideoPlayingActivity.this.m977f();
        }

        public void onCompletion(int i, boolean z) {
            Bundle bundle = new Bundle();
            bundle.putInt(NendAdNativeMediaView.RESULT_CURRENT_MSEC, i);
            bundle.putBoolean(NendAdNativeMediaView.RESULT_IS_COMPLETION, z);
            FullscreenVideoPlayingActivity.this.f944b.send(12, bundle);
            FullscreenVideoPlayingActivity.this.f950h = i;
            if (z) {
                FullscreenVideoPlayingActivity.this.f943a = C0774f.COMPLETED;
                FullscreenVideoPlayingActivity.this.f947e.setVisibility(0);
                FullscreenVideoPlayingActivity.this.f948f.setVisibility(4);
                return;
            }
            FullscreenVideoPlayingActivity.this.f943a = C0774f.PAUSING;
        }

        public void onProgress(int i, int i2) {
            FullscreenVideoPlayingActivity.this.f950h = i - i2;
            Bundle bundle = new Bundle();
            bundle.putInt(NendAdNativeMediaView.RESULT_TOTAL_MSEC, i);
            bundle.putInt(NendAdNativeMediaView.RESULT_REMAIN_MSEC, i2);
            FullscreenVideoPlayingActivity.this.f944b.send(11, bundle);
        }

        public void onError(int i, String str) {
            Bundle bundle = new Bundle();
            bundle.putInt(NendAdNativeMediaView.RESULT_ERROR_CODE, i);
            bundle.putString(NendAdNativeMediaView.RESULT_ERROR_MESSAGE, str);
            FullscreenVideoPlayingActivity.this.f944b.send(13, bundle);
            if (FullscreenVideoPlayingActivity.this.f954l) {
                FullscreenVideoPlayingActivity.this.finish();
                return;
            }
            FullscreenVideoPlayingActivity.this.f954l = true;
            FullscreenVideoPlayingActivity.this.f945c.mo8157e();
            FullscreenVideoPlayingActivity.this.f945c.mo8158f();
            FullscreenVideoPlayingActivity.this.f945c = null;
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    FullscreenVideoPlayingActivity.this.m963a();
                }
            });
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m965a(bundle);
        if (this.f946d.f564c == 1) {
            setContentView(C0761i.m1234c(this, "activity_native_port_video_fullscreen_playing"));
        } else {
            setContentView(C0761i.m1234c(this, "activity_native_land_video_fullscreen_playing"));
        }
    }

    /* renamed from: a */
    private void m965a(Bundle bundle) {
        if (bundle == null) {
            Intent intent = getIntent();
            this.f946d = (C0540b) intent.getParcelableExtra("key_ad_unit");
            this.f944b = (ResultReceiver) intent.getParcelableExtra("key_receiver");
            this.f950h = intent.getIntExtra("key_video_playing_time", 0);
            this.f949g = intent.getBooleanExtra("key_mute", false);
            this.f943a = C0774f.PREPARING;
        } else {
            this.f946d = (C0540b) bundle.getParcelable("key_ad_unit");
            this.f944b = (ResultReceiver) bundle.getParcelable("key_receiver");
            this.f950h = bundle.getInt("key_video_playing_time");
            this.f949g = bundle.getBoolean("key_mute");
            this.f943a = (C0774f) f942p.get(bundle.getInt("key_playing_status"));
            if (this.f943a == C0774f.COMPLETED) {
                m974d();
            }
        }
        if (this.f946d == null || !this.f946d.mo7780a()) {
            C0757f.m1222d("Failed to play video. Because required loader data is not found or loader is expired.");
            Bundle bundle2 = new Bundle();
            bundle2.putInt(NendAdNativeMediaView.RESULT_ERROR_CODE, NendVideoAdClientError.INVALID_AD_DATA.getCode());
            bundle2.putString(NendAdNativeMediaView.RESULT_ERROR_MESSAGE, NendVideoAdClientError.INVALID_AD_DATA.getMessage());
            this.f944b.send(13, bundle2);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        if (this.f947e == null) {
            m963a();
            m969b();
            m971c();
        } else if (this.f947e.getVisibility() != 0) {
            this.f945c.mo8155c();
            this.f943a = C0774f.PLAYING;
        }
        m964a(getResources().getConfiguration().orientation);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        if (this.f947e.getVisibility() == 0) {
            m974d();
            return;
        }
        this.f945c.mo8156d();
        this.f943a = C0774f.PAUSING;
    }

    public static Bundle newBundle(int i, C0540b bVar, boolean z, ResultReceiver resultReceiver) {
        Bundle bundle = new Bundle();
        bundle.putInt("key_video_playing_time", i);
        bundle.putParcelable("key_ad_unit", bVar);
        bundle.putParcelable("key_receiver", resultReceiver);
        bundle.putBoolean("key_mute", z);
        return bundle;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        m964a(configuration.orientation);
    }

    /* renamed from: a */
    private void m964a(int i) {
        if (this.f951i == 0) {
            this.f951i = C0761i.m1233b(this, "native_video_fullscreen_card");
        }
        if (i == this.f946d.f564c) {
            findViewById(this.f951i).setVisibility(8);
            findViewById(this.f952j).setVisibility(0);
            return;
        }
        findViewById(this.f951i).setVisibility(0);
        findViewById(this.f952j).setVisibility(8);
    }

    public void finish() {
        super.finish();
        if (this.f945c != null) {
            this.f945c.mo8157e();
            this.f945c.mo8158f();
        }
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(this.f951i);
        if (constraintLayout != null) {
            constraintLayout.setVisibility(4);
        }
        View findViewById = findViewById(this.f952j);
        if (findViewById != null) {
            findViewById.setVisibility(8);
        }
        Button button = (Button) findViewById(this.f953k);
        if (button != null) {
            button.setVisibility(4);
        }
        this.f944b.send(1, null);
        overridePendingTransition(17432576, 17432577);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("key_ad_unit", this.f946d);
        bundle.putParcelable("key_receiver", this.f944b);
        bundle.putBoolean("key_mute", this.f949g);
        bundle.putInt("key_playing_status", this.f943a.ordinal());
        bundle.putInt("key_video_playing_time", this.f950h);
    }

    /* renamed from: a */
    private String m961a(String str) {
        return C0755e.m1196a(str, (HashMap<String, String>) new HashMap<String, String>() {
            {
                put("ifs", "1");
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m963a() {
        this.f945c = (NendAdVideoView) findViewById(C0761i.m1233b(this, "native_video_fullscreen_videoview"));
        this.f945c.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.f945c.mo8152a(this.f946d.f568g, true);
        this.f945c.setCallback(this.f957o);
        this.f945c.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (FullscreenVideoPlayingActivity.this.f943a == C0774f.COMPLETED) {
                    return;
                }
                if (FullscreenVideoPlayingActivity.this.f948f.getVisibility() == 0) {
                    FullscreenVideoPlayingActivity.this.f948f.setVisibility(4);
                } else {
                    FullscreenVideoPlayingActivity.this.f948f.setVisibility(0);
                }
            }
        });
        ImageView imageView = (ImageView) findViewById(C0761i.m1233b(this, "native_video_fullscreen_action_optout"));
        imageView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                FullscreenVideoPlayingActivity.this.f944b.send(3, null);
            }
        });
        C0656e.m940a(this.f945c.getWidth(), this.f945c.getHeight(), imageView);
    }

    /* renamed from: b */
    private void m969b() {
        final RoundedImageView roundedImageView = (RoundedImageView) findViewById(C0761i.m1233b(this, "native_video_fullscreen_card_image"));
        Bitmap a = C0749a.m1181a(this.f946d.f608i);
        if (a == null) {
            C0608c.m816a(this.f946d).mo7993a((Executor) new C0627a()).mo7996a((C0630d<? super T>) new C0630d<Bitmap>() {
                /* renamed from: a */
                public void mo7666a(Bitmap bitmap) {
                    roundedImageView.setImageBitmap(bitmap);
                    roundedImageView.setBackgroundColor(0);
                }
            }).mo7998b((C0630d<Throwable>) new C0630d<Throwable>() {
                /* renamed from: a */
                public void mo7666a(Throwable th) {
                    roundedImageView.setBackgroundColor(-3355444);
                }
            });
        } else {
            roundedImageView.setImageBitmap(a);
            roundedImageView.setBackgroundColor(0);
        }
        Button button = (Button) findViewById(C0761i.m1233b(this, "native_video_fullscreen_card_cta"));
        m966a(button);
        button.setShadowLayer(8.0f, 1.0f, 1.0f, ViewCompat.MEASURED_STATE_MASK);
        ((TextView) findViewById(C0761i.m1233b(this, "native_video_fullscreen_card_title"))).setText(this.f946d.f609j);
        ((TextView) findViewById(C0761i.m1233b(this, "native_video_fullscreen_card_advertiser"))).setText(this.f946d.f610k);
        TextView textView = (TextView) findViewById(C0761i.m1233b(this, "native_video_fullscreen_card_description"));
        if (this.f946d.f611l == null || this.f946d.f611l.length() <= 45) {
            textView.setText(this.f946d.f611l);
        } else {
            textView.setText(this.f946d.f611l.substring(0, 45) + "...");
        }
        int[] iArr = {C0761i.m1233b(this, "native_video_fullscreen_card_star001"), C0761i.m1233b(this, "native_video_fullscreen_card_star002"), C0761i.m1233b(this, "native_video_fullscreen_card_star003"), C0761i.m1233b(this, "native_video_fullscreen_card_star004"), C0761i.m1233b(this, "native_video_fullscreen_card_star005")};
        int b = C0761i.m1233b(this, "native_video_fullscreen_card_rating_count");
        if (this.f946d.f612m == -1.0f || this.f946d.f613n == -1) {
            for (int findViewById : iArr) {
                findViewById(findViewById).setVisibility(8);
            }
            findViewById(b).setVisibility(8);
            return;
        }
        C0656e.m941a((Activity) this, this.f946d, iArr);
        ((TextView) findViewById(b)).setText(C0656e.m939a((long) this.f946d.f613n));
    }

    /* renamed from: c */
    private void m971c() {
        this.f947e = (FrameLayout) findViewById(C0761i.m1233b(this, "native_video_fullscreen_replay_area"));
        this.f947e.setVisibility(8);
        C0656e.m937a(this.f947e, (Context) this, this.f946d);
        ((ImageButton) findViewById(C0761i.m1233b(this, "media_view_button_replay"))).setOnClickListener(this.f955m);
        ((FontFitTextView) findViewById(C0761i.m1233b(this, "description_media_view_button_replay"))).setOnClickListener(this.f955m);
        ((ImageButton) findViewById(C0761i.m1233b(this, "media_view_button_cta"))).setOnClickListener(this.f956n);
        FontFitTextView fontFitTextView = (FontFitTextView) findViewById(C0761i.m1233b(this, "description_media_view_button_cta"));
        fontFitTextView.setOnClickListener(this.f956n);
        fontFitTextView.setText(this.f946d.f614o);
        if (this.f953k == 0) {
            this.f953k = C0761i.m1233b(this, "native_video_fullscreen_close");
        }
        ((Button) findViewById(this.f953k)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                FullscreenVideoPlayingActivity.this.finish();
            }
        });
        if (this.f952j == 0) {
            this.f952j = C0761i.m1233b(this, "native_video_fullscreen_action_cta");
        }
        Button button = (Button) findViewById(this.f952j);
        m966a(button);
        button.setShadowLayer(8.0f, 1.0f, 1.0f, ViewCompat.MEASURED_STATE_MASK);
        this.f948f = (ToggleButton) findViewById(C0761i.m1233b(this, "native_video_fullscreen_action_toggle_volume"));
        this.f948f.setTextOff("");
        this.f948f.setTextOn("");
        this.f948f.setChecked(!this.f949g);
        this.f948f.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                FullscreenVideoPlayingActivity.this.f949g = !z;
                FullscreenVideoPlayingActivity.this.f945c.setMute(FullscreenVideoPlayingActivity.this.f949g);
            }
        });
        this.f948f.setVisibility(4);
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public void m974d() {
        this.f943a = C0774f.PREPARING;
        this.f950h = 0;
        if (this.f945c != null) {
            this.f945c.mo8150a(this.f950h);
        }
        if (this.f947e != null) {
            this.f947e.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: e */
    public void m975e() {
        Bundle bundle = new Bundle();
        bundle.putString(NendAdNativeMediaView.RESULT_CLICK_URL, m961a(this.f946d.f563b));
        this.f944b.send(2, bundle);
        finish();
    }

    /* renamed from: a */
    private void m966a(Button button) {
        button.setText(this.f946d.f614o);
        button.setOnClickListener(this.f956n);
    }

    /* access modifiers changed from: private */
    /* renamed from: f */
    public void m977f() {
        this.f945c.mo8150a(this.f950h);
        this.f945c.setMute(this.f949g);
        this.f945c.mo8155c();
        this.f943a = C0774f.PLAYING;
    }
}
