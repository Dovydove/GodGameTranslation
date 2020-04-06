package net.nend.android;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import net.nend.android.NendAdView.NendError;
import net.nend.android.internal.C0521a;
import net.nend.android.internal.p013c.p016c.C0568a;
import net.nend.android.internal.p013c.p016c.C0569b;
import net.nend.android.internal.p013c.p016c.C0572c;
import net.nend.android.internal.utilities.C0740a;
import net.nend.android.internal.utilities.C0740a.C0742a;
import net.nend.android.internal.utilities.C0740a.C0743b;
import net.nend.android.internal.utilities.C0740a.C0748f;
import net.nend.android.internal.utilities.C0755e;
import net.nend.android.internal.utilities.C0757f;
import net.nend.android.internal.utilities.C0758g;

public class NendAdIconLoader implements C0466a, C0743b<C0569b> {

    /* renamed from: a */
    static final /* synthetic */ boolean f276a = (!NendAdIconLoader.class.desiredAssertionStatus());

    /* renamed from: b */
    private int f277b = 60;

    /* renamed from: c */
    private Context f278c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public List<NendAdIconView> f279d;

    /* renamed from: e */
    private boolean f280e;

    /* renamed from: f */
    private Handler f281f;

    /* renamed from: g */
    private boolean f282g = false;

    /* renamed from: h */
    private boolean f283h = true;

    /* renamed from: i */
    private int f284i;
    /* access modifiers changed from: private */

    /* renamed from: j */
    public Future<C0569b> f285j;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public C0568a f286k;

    /* renamed from: l */
    private OnClickListener f287l;

    /* renamed from: m */
    private OnInformationClickListener f288m;

    /* renamed from: n */
    private OnFailedListener f289n;

    /* renamed from: o */
    private OnReceiveListener f290o;

    public interface OnClickListener {
        void onClick(NendAdIconView nendAdIconView);
    }

    public interface OnFailedListener {
        void onFailedToReceiveAd(NendIconError nendIconError);
    }

    public interface OnInformationClickListener {
        void onClickInformation(NendAdIconView nendAdIconView);
    }

    public interface OnReceiveListener {
        void onReceiveAd(NendAdIconView nendAdIconView);
    }

    public NendAdIconLoader(Context context, int i, String str) {
        this.f278c = context;
        this.f284i = i;
        if (i <= 0) {
            throw new IllegalArgumentException(C0758g.ERR_INVALID_SPOT_ID.mo8197a("spot id : " + i));
        } else if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(C0758g.ERR_INVALID_API_KEY.mo8197a("api key : " + str));
        } else {
            C0755e.m1197a(context);
            this.f279d = new ArrayList();
            this.f281f = new Handler() {
                public void handleMessage(Message message) {
                    super.handleMessage(message);
                    if (NendAdIconLoader.this.f279d.size() > 0) {
                        NendAdIconLoader.this.f286k.mo7869a(NendAdIconLoader.this.f279d.size());
                        NendAdIconLoader.this.f285j = C0740a.m1170a().mo8183a(new C0748f((C0743b<V>) NendAdIconLoader.this), new C0742a<C0569b>() {
                            /* renamed from: a */
                            public void mo7491a(C0569b bVar, Exception exc) {
                                NendAdIconLoader.this.m177a(bVar);
                            }
                        });
                        return;
                    }
                    C0757f.m1210a("NendAdIconView is nothing.");
                }
            };
            this.f286k = new C0568a(context, i, str);
        }
    }

    public void addIconView(NendAdIconView nendAdIconView) {
        if (nendAdIconView != null && this.f279d.size() < 8 && !this.f279d.contains(nendAdIconView)) {
            if (this.f282g) {
                loadAd();
            }
            this.f283h = true;
            this.f279d.add(nendAdIconView);
            nendAdIconView.setListner(this);
        }
    }

    public void removeIconView(NendAdIconView nendAdIconView) {
        if (nendAdIconView != null) {
            nendAdIconView.mo7493a();
            this.f279d.remove(nendAdIconView);
            if (this.f279d.size() == 0) {
                pause();
            }
        }
    }

    public int getIconCount() {
        return this.f279d.size();
    }

    public void loadAd() {
        if (this.f281f == null) {
            this.f281f = new Handler();
        }
        this.f281f.removeMessages(719);
        this.f281f.sendEmptyMessage(719);
        this.f282g = true;
    }

    public void resume() {
        this.f283h = true;
        m175a();
    }

    /* renamed from: a */
    private void m175a() {
        if (this.f283h && !this.f281f.hasMessages(719)) {
            this.f281f.sendEmptyMessageDelayed(719, (long) (this.f277b * 1000));
        }
    }

    public void pause() {
        this.f283h = false;
        m179b();
    }

    /* renamed from: b */
    private void m179b() {
        if (this.f285j != null) {
            this.f285j.cancel(true);
        }
        if (this.f281f != null) {
            this.f281f.removeMessages(719);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m177a(C0569b bVar) {
        String str;
        if (bVar != null) {
            this.f277b = C0755e.m1194a(bVar.mo7871a());
            String b = bVar.mo7872b();
            ArrayList c = bVar.mo7873c();
            String str2 = b;
            for (int i = 0; i < this.f279d.size(); i++) {
                if (c.size() > i) {
                    C0521a aVar = (C0521a) c.get(i);
                    if (!TextUtils.isEmpty(str2)) {
                        str = str2 + String.format("&ic[]=%s", new Object[]{aVar.mo7774i()});
                    } else {
                        str = str2;
                    }
                    ((NendAdIconView) this.f279d.get(i)).mo7494a(aVar, this.f284i);
                    str2 = str;
                }
            }
            C0740a.m1170a().mo8182a(new C0748f(str2));
        } else {
            C0757f.m1210a("onFailedToImageDownload!");
            if (this.f289n != null) {
                NendIconError nendIconError = new NendIconError();
                nendIconError.mo7742a(this);
                nendIconError.setErrorType(0);
                nendIconError.setNendError(NendError.FAILED_AD_REQUEST);
                this.f289n.onFailedToReceiveAd(nendIconError);
            }
        }
        if (this.f283h && !this.f281f.hasMessages(719)) {
            this.f281f.sendEmptyMessageDelayed(719, (long) (this.f277b * 1000));
        }
    }

    public C0569b makeResponse(byte[] bArr) {
        if (bArr != null) {
            try {
                return (C0569b) new C0572c(this.f278c, this.f279d.size()).mo7867a(new String(bArr, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                if (!f276a) {
                    throw new AssertionError();
                }
                C0757f.m1215a(C0758g.ERR_HTTP_REQUEST, (Throwable) e);
            }
        }
        return null;
    }

    public String getRequestUrl() {
        return this.f286k.mo7849b(C0755e.m1201b(this.f278c));
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.f287l = onClickListener;
    }

    public void onClick(View view) {
        if (this.f287l != null) {
            this.f287l.onClick((NendAdIconView) view);
        }
    }

    public void setOnInformationClickListener(OnInformationClickListener onInformationClickListener) {
        this.f288m = onInformationClickListener;
    }

    public void onClickInformation(View view) {
        if (this.f288m != null) {
            this.f288m.onClickInformation((NendAdIconView) view);
        }
    }

    public void setOnReceiveListener(OnReceiveListener onReceiveListener) {
        this.f290o = onReceiveListener;
    }

    public void onReceive(View view) {
        if (this.f290o != null) {
            this.f290o.onReceiveAd((NendAdIconView) view);
        }
    }

    public void setOnFailedListener(OnFailedListener onFailedListener) {
        this.f289n = onFailedListener;
    }

    public void onFailedToReceive(NendIconError nendIconError) {
        if (this.f289n != null) {
            this.f289n.onFailedToReceiveAd(nendIconError);
        }
    }

    public void onWindowFocusChanged(boolean z) {
        if (z) {
            if (!this.f280e) {
                this.f280e = true;
                resume();
            }
        } else if (this.f280e) {
            this.f280e = false;
            pause();
        }
    }
}
