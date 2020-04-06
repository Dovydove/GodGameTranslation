package net.nend.android.internal.p023ui.views.video;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView.SurfaceTextureListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import java.io.IOException;
import net.nend.android.internal.utilities.C0757f;

/* renamed from: net.nend.android.internal.ui.views.video.NendAdVideoView */
public class NendAdVideoView extends FrameLayout implements SurfaceTextureListener {
    @VisibleForTesting

    /* renamed from: a */
    MediaPlayer f1097a;
    @VisibleForTesting

    /* renamed from: b */
    SurfaceTexture f1098b;
    @VisibleForTesting

    /* renamed from: c */
    boolean f1099c;
    @VisibleForTesting

    /* renamed from: d */
    boolean f1100d;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public C0735c f1101e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public C0727a f1102f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public boolean f1103g;

    /* renamed from: h */
    private boolean f1104h;
    /* access modifiers changed from: private */

    /* renamed from: i */
    public int f1105i;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public int f1106j;

    /* renamed from: k */
    private String f1107k;

    /* renamed from: l */
    private Surface f1108l;

    /* renamed from: m */
    private long f1109m;

    /* renamed from: net.nend.android.internal.ui.views.video.NendAdVideoView$a */
    public interface C0727a {
        void onCompletion(int i, boolean z);

        void onError(int i, String str);

        void onPrepared();

        void onProgress(int i, int i2);

        void onStartPlay();
    }

    public NendAdVideoView(Context context) {
        this(context, null);
    }

    public NendAdVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1105i = 0;
        this.f1106j = 0;
        this.f1107k = null;
    }

    public void setUpVideo(String str) {
        mo8152a(str, false);
    }

    /* renamed from: a */
    public void mo8152a(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            C0757f.m1222d("Video setup failed. Because the file path of setUpVideo method is empty or null.");
        } else if (this.f1101e == null) {
            this.f1107k = str;
            this.f1099c = false;
            this.f1103g = false;
            this.f1104h = z;
            this.f1101e = new C0735c(getContext());
            LayoutParams layoutParams = new LayoutParams(-1, -1);
            layoutParams.gravity = 17;
            this.f1101e.setLayoutParams(layoutParams);
            this.f1101e.setSurfaceTextureListener(this);
            addView(this.f1101e, 0);
            invalidate();
            requestLayout();
        } else {
            C0757f.m1220c("setUpVideo method call has already been completed.");
        }
    }

    public void setCallback(C0727a aVar) {
        this.f1102f = aVar;
    }

    /* renamed from: a */
    public boolean mo8153a() {
        C0757f.m1210a("mIsMediaPlayerPrepared: " + this.f1099c);
        C0757f.m1210a(" MediaPlayer object is " + (this.f1097a != null ? "allocated." : "released."));
        return this.f1099c && this.f1097a != null && !this.f1097a.isPlaying();
    }

    public int getCurrentPosition() {
        if (this.f1097a != null) {
            return this.f1097a.getCurrentPosition();
        }
        return 0;
    }

    /* renamed from: b */
    public void mo8154b() {
        if (!this.f1103g || this.f1104h) {
            m1147i();
        } else {
            C0757f.m1222d("This video can play only once.");
        }
    }

    /* renamed from: c */
    public void mo8155c() {
        if (this.f1103g && !this.f1104h) {
            C0757f.m1222d("This video can play only once.");
        } else if (!this.f1099c) {
            mo8154b();
        } else if (this.f1097a != null && !this.f1097a.isPlaying()) {
            this.f1097a.seekTo(this.f1105i);
            this.f1103g = false;
            this.f1097a.start();
            this.f1109m = System.currentTimeMillis();
            if (this.f1102f != null) {
                this.f1102f.onStartPlay();
            }
        }
    }

    /* renamed from: d */
    public void mo8156d() {
        if (this.f1097a != null && this.f1097a.isPlaying()) {
            this.f1105i = this.f1097a.getCurrentPosition();
            this.f1097a.pause();
            if (this.f1102f != null) {
                this.f1102f.onCompletion(this.f1097a.getCurrentPosition(), false);
            }
        }
    }

    /* renamed from: e */
    public void mo8157e() {
        if (this.f1097a != null && this.f1097a.isPlaying()) {
            m1145g();
            if (this.f1102f != null) {
                this.f1102f.onCompletion(this.f1097a.getCurrentPosition(), false);
            }
        }
    }

    /* renamed from: g */
    private void m1145g() {
        if (this.f1097a != null) {
            this.f1105i = 0;
            this.f1099c = false;
            this.f1097a.stop();
            this.f1100d = false;
            this.f1097a.reset();
        }
    }

    /* renamed from: a */
    public void mo8150a(int i) {
        if (this.f1097a != null) {
            this.f1097a.seekTo(i);
            this.f1105i = this.f1097a.getCurrentPosition();
        }
    }

    public void setMute(boolean z) {
        if (this.f1097a != null) {
            float f = z ? 0.0f : 1.0f;
            this.f1097a.setVolume(f, f);
        }
    }

    /* renamed from: f */
    public void mo8158f() {
        this.f1103g = false;
        m1146h();
        if (this.f1102f != null) {
            this.f1102f = null;
        }
        if (this.f1101e != null) {
            removeView(this.f1101e);
            this.f1101e = null;
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.f1098b == null) {
            this.f1098b = surfaceTexture;
            m1147i();
        }
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        C0757f.m1210a(" MediaPlayer object is " + (this.f1097a != null ? "still allocated." : "released."));
        if (this.f1097a != null) {
            this.f1105i = this.f1103g ? 0 : this.f1097a.getCurrentPosition();
            if (this.f1097a.isPlaying() && this.f1102f != null) {
                this.f1102f.onCompletion(this.f1097a.getCurrentPosition(), false);
            }
            m1146h();
        }
        if (this.f1098b != null) {
            this.f1098b.release();
            this.f1098b = null;
        }
        return true;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        if (this.f1097a != null && this.f1102f != null && this.f1097a.isPlaying() && System.currentTimeMillis() - this.f1109m >= 1000) {
            this.f1109m = System.currentTimeMillis();
            this.f1102f.onProgress(this.f1106j, this.f1106j - this.f1097a.getCurrentPosition());
        }
    }

    /* renamed from: h */
    private void m1146h() {
        this.f1099c = false;
        this.f1100d = false;
        if (this.f1097a != null) {
            if (this.f1108l != null) {
                this.f1108l.release();
                this.f1108l = null;
            }
            try {
                this.f1097a.stop();
                this.f1097a.reset();
                this.f1097a.release();
                this.f1097a = null;
            } catch (IllegalStateException e) {
                C0757f.m1218b("Failed to release MediaPlayer.", (Throwable) e);
            }
        }
    }

    /* renamed from: i */
    private void m1147i() {
        if (this.f1098b != null) {
            if (this.f1097a == null) {
                this.f1097a = new MediaPlayer();
                this.f1097a.setOnPreparedListener(new OnPreparedListener() {
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        NendAdVideoView.this.f1106j = mediaPlayer.getDuration();
                        NendAdVideoView.this.f1099c = true;
                        if (NendAdVideoView.this.f1101e != null) {
                            NendAdVideoView.this.f1101e.mo8177a(mediaPlayer.getVideoWidth(), mediaPlayer.getVideoHeight());
                        }
                        if (NendAdVideoView.this.f1102f != null) {
                            NendAdVideoView.this.f1102f.onPrepared();
                        }
                    }
                });
                this.f1097a.setOnCompletionListener(new OnCompletionListener() {
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        NendAdVideoView.this.f1105i = 0;
                        NendAdVideoView.this.f1103g = true;
                        if (NendAdVideoView.this.f1102f != null) {
                            NendAdVideoView.this.f1102f.onProgress(NendAdVideoView.this.f1106j, 0);
                            NendAdVideoView.this.f1102f.onCompletion(mediaPlayer.getCurrentPosition(), true);
                        }
                    }
                });
                this.f1097a.setOnErrorListener(new OnErrorListener() {
                    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                        NendAdVideoView.this.mo8151a(i, i2);
                        return true;
                    }
                });
            }
            try {
                if (!this.f1100d) {
                    this.f1097a.setDataSource(this.f1107k);
                    this.f1100d = true;
                }
                if (this.f1108l == null) {
                    this.f1108l = new Surface(this.f1098b);
                    this.f1097a.setSurface(this.f1108l);
                }
                this.f1097a.prepareAsync();
            } catch (IllegalStateException e) {
                C0757f.m1218b("Failed to prepare media player.", (Throwable) e);
                if (this.f1102f != null) {
                    this.f1102f.onError(1, "Failed to prepare media player.");
                }
            } catch (IOException e2) {
                C0757f.m1218b("Failed to create media player.", (Throwable) e2);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    /* renamed from: a */
    public void mo8151a(int i, int i2) {
        C0757f.m1210a("what: " + i);
        C0757f.m1210a("extra: " + i2);
        switch (i) {
            case 1:
                m1145g();
                if (this.f1102f != null) {
                    this.f1102f.onError(i, "Media unknown error.");
                    return;
                }
                return;
            case 100:
                m1145g();
                if (this.f1102f != null) {
                    this.f1102f.onError(i, "Media server died.");
                    return;
                }
                return;
            default:
                return;
        }
    }
}
