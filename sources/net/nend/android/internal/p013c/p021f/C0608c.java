package net.nend.android.internal.p013c.p021f;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import net.nend.android.NendAdNative;
import net.nend.android.NendAdNativeClient;
import net.nend.android.NendAdNativeClient.Callback;
import net.nend.android.NendAdNativeClient.NendError;
import net.nend.android.NendAdNativeVideo.VideoClickOption;
import net.nend.android.internal.p007a.C0523a;
import net.nend.android.internal.p008b.C0525a;
import net.nend.android.internal.p008b.p012c.C0540b;
import net.nend.android.internal.p013c.C0546a;
import net.nend.android.internal.p013c.p021f.C0617d.C0618a;
import net.nend.android.internal.p022d.C0631e;
import net.nend.android.internal.p022d.C0636g;
import net.nend.android.internal.p022d.C0646k;
import net.nend.android.internal.p022d.C0647l;
import net.nend.android.internal.p022d.C0649m;
import net.nend.android.internal.utilities.C0740a;
import net.nend.android.internal.utilities.C0740a.C0744c;
import net.nend.android.internal.utilities.C0757f;
import net.nend.android.internal.utilities.C0759h;
import net.nend.android.internal.utilities.p027a.C0749a;
import net.nend.android.internal.utilities.video.NendVideoAdClientError;
import org.json.JSONObject;

/* renamed from: net.nend.android.internal.c.f.c */
/* compiled from: NativeAdLoader */
public class C0608c extends C0546a {
    /* access modifiers changed from: private */

    /* renamed from: k */
    public static final String f840k = m511a("/native");

    /* renamed from: c */
    private int f841c;

    /* renamed from: d */
    private String f842d;

    /* renamed from: e */
    private int f843e;

    /* renamed from: f */
    private String f844f;

    /* renamed from: g */
    private String f845g;

    /* renamed from: h */
    private String f846h;

    /* renamed from: i */
    private VideoClickOption f847i;

    /* renamed from: j */
    private ArrayList<Integer> f848j = new ArrayList<>();

    /* renamed from: l */
    private final C0744c<C0540b> f849l = new C0550a<C0540b>() {
        public String getRequestUrl() {
            return C0608c.f840k;
        }

        /* access modifiers changed from: protected */
        /* renamed from: b */
        public C0540b mo7832a(JSONObject jSONObject) {
            return C0540b.m501a(jSONObject);
        }
    };

    public C0608c(Context context, int i, String str, VideoClickOption videoClickOption) {
        super(context);
        this.f843e = i;
        this.f844f = str;
        this.f847i = videoClickOption;
    }

    /* renamed from: b */
    public void mo7970b(String str) {
        this.f845g = str;
    }

    /* renamed from: a */
    public void mo7966a(int i) {
        this.f848j.add(Integer.valueOf(i));
        if (this.f848j.size() > 4) {
            this.f848j.remove(0);
        }
    }

    /* renamed from: a */
    public VideoClickOption mo7965a() {
        return this.f847i;
    }

    /* renamed from: b */
    public C0646k<C0540b> mo7969b() {
        C0646k b = m819b(this.f843e, this.f844f, this.f845g, this.f846h, this.f849l).mo7999b((C0636g<? super T, ? extends C0646k<? extends R>>) new C0636g<C0540b, C0646k<? extends C0649m<C0540b, Bitmap>>>() {
            /* renamed from: a */
            public C0646k<? extends C0649m<C0540b, Bitmap>> mo7540a(C0540b bVar) {
                return C0647l.m925a(C0647l.m921a(bVar), C0608c.m816a(bVar));
            }
        });
        if (this.f841c > 0 && !TextUtils.isEmpty(this.f842d)) {
            return b.mo7997a((C0636g<Throwable, ? extends T>) new C0636g<Throwable, C0649m<C0540b, Bitmap>>() {
                /* renamed from: a */
                public C0649m<C0540b, Bitmap> mo7540a(Throwable th) {
                    C0757f.m1217b("Failed to load Native Video Ad. Fallback normal Native ad.");
                    return null;
                }
            }).mo7999b((C0636g<? super T, ? extends C0646k<? extends R>>) new C0636g<C0649m<C0540b, Bitmap>, C0646k<? extends C0540b>>() {
                /* renamed from: a */
                public C0646k<? extends C0540b> mo7540a(C0649m<C0540b, Bitmap> mVar) {
                    if (mVar != null) {
                        return C0647l.m921a(mVar.f906a);
                    }
                    return C0608c.this.m821d();
                }
            });
        }
        C0757f.m1217b("You can use fallback option at Native Video Ad. Let's check the wiki.");
        return b.mo7999b((C0636g<? super T, ? extends C0646k<? extends R>>) new C0636g<C0649m<C0540b, Bitmap>, C0646k<? extends C0540b>>() {
            /* renamed from: a */
            public C0646k<? extends C0540b> mo7540a(C0649m<C0540b, Bitmap> mVar) {
                return C0647l.m921a(mVar.f906a);
            }
        });
    }

    /* renamed from: b */
    private <V extends C0525a> C0646k<V> m819b(int i, String str, String str2, String str3, C0744c<V> cVar) {
        final Context context = (Context) this.f626b.get();
        return mo7825a(i, str, str2, str3, cVar).mo7999b((C0636g<? super T, ? extends C0646k<? extends R>>) new C0636g<V, C0646k<? extends V>>() {
            /* renamed from: a */
            public C0646k<? extends V> mo7540a(V v) {
                return C0608c.this.f625a.mo7949a(v, context);
            }
        });
    }

    /* renamed from: a */
    public static C0646k<Bitmap> m816a(C0540b bVar) {
        final String str = bVar.f608i;
        final Bitmap a = C0749a.m1181a(str);
        if (a == null || a.isRecycled()) {
            return C0647l.m923a(C0740a.m1170a().mo8184b(), (Callable<T>) C0759h.m1226a(str)).mo7999b((C0636g<? super T, ? extends C0646k<? extends R>>) new C0636g<Bitmap, C0646k<? extends Bitmap>>() {
                /* renamed from: a */
                public C0646k<? extends Bitmap> mo7540a(Bitmap bitmap) {
                    if (bitmap == null) {
                        return C0647l.m922a((Throwable) new C0523a(NendVideoAdClientError.FAILED_AD_DOWNLOAD));
                    }
                    C0749a.m1182a(str, bitmap);
                    return C0647l.m921a(a);
                }
            });
        }
        return C0647l.m921a(a);
    }

    /* renamed from: b */
    public void mo7971b(C0540b bVar) {
        if (!TextUtils.isEmpty(bVar.f567f)) {
            this.f625a.mo7952a(bVar.f567f);
        }
    }

    /* renamed from: c */
    public void mo7972c(String str) {
        this.f846h = str;
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public C0646k<C0540b> m821d() {
        Context context = (Context) this.f626b.get();
        final C0631e a = C0647l.m920a();
        new NendAdNativeClient(context, this.f841c, this.f842d).loadAd(new Callback() {
            public void onSuccess(NendAdNative nendAdNative) {
                a.mo7991a(C0540b.m500a(nendAdNative));
            }

            public void onFailure(NendError nendError) {
                a.mo7992a((Throwable) new C0523a(NendVideoAdClientError.FAILED_AD_FALLBACK));
            }
        });
        return a.mo7990a();
    }

    /* renamed from: a */
    public void mo7967a(int i, String str) {
        this.f841c = i;
        this.f842d = str;
    }

    /* renamed from: b */
    public C0618a mo7824a(int i, String str, String str2) {
        return ((C0618a) ((C0618a) ((C0618a) new C0618a().mo7883a(i)).mo7885a(str)).mo7890b(str2)).mo7980a(this.f848j).mo7981b(this.f847i.intValue());
    }
}
