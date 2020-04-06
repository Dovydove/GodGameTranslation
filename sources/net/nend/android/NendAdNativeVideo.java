package net.nend.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.view.View.OnClickListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;
import net.nend.android.NendAdNative.Callback;
import net.nend.android.internal.p007a.C0524b;
import net.nend.android.internal.p008b.p012c.C0540b;
import net.nend.android.internal.p013c.p021f.C0604b;
import net.nend.android.internal.p013c.p021f.C0608c;
import net.nend.android.internal.p022d.C0627a;
import net.nend.android.internal.p022d.C0630d;
import net.nend.android.internal.utilities.C0740a;
import net.nend.android.internal.utilities.C0740a.C0742a;
import net.nend.android.internal.utilities.C0740a.C0745d;
import net.nend.android.internal.utilities.C0755e;
import net.nend.android.internal.utilities.C0758g;
import net.nend.android.internal.utilities.p027a.C0749a;

public class NendAdNativeVideo implements Parcelable {
    public static final Creator<NendAdNativeVideo> CREATOR = new Creator<NendAdNativeVideo>() {
        /* renamed from: a */
        public NendAdNativeVideo createFromParcel(Parcel parcel) {
            return new NendAdNativeVideo(parcel);
        }

        /* renamed from: a */
        public NendAdNativeVideo[] newArray(int i) {
            return new NendAdNativeVideo[i];
        }
    };

    /* renamed from: m */
    private static final ArrayList<VideoClickOption> f436m = new ArrayList<VideoClickOption>() {
        {
            add(VideoClickOption.FullScreen);
            add(VideoClickOption.LP);
        }
    };

    /* renamed from: n */
    private static final ArrayList<C0505c> f437n = new ArrayList<C0505c>() {
        {
            add(C0505c.STANDBY);
            add(C0505c.ACTIVATED);
            add(C0505c.VIEWED);
            add(C0505c.COMPLETED);
        }
    };
    @VisibleForTesting

    /* renamed from: a */
    Set<C0504b> f438a;

    /* renamed from: b */
    private final VideoClickOption f439b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public final int f440c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public WeakReference<Context> f441d;

    /* renamed from: e */
    private WeakReference<C0608c> f442e;

    /* renamed from: f */
    private C0540b f443f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public Bitmap f444g;

    /* renamed from: h */
    private NendAdNative f445h;

    /* renamed from: i */
    private NendAdNativeVideoListener f446i;

    /* renamed from: j */
    private boolean f447j;

    /* renamed from: k */
    private int f448k;

    /* renamed from: l */
    private C0505c f449l;

    public enum VideoClickOption {
        FullScreen(1),
        LP(2);
        

        /* renamed from: a */
        private final int f459a;

        private VideoClickOption(int i) {
            this.f459a = i;
        }

        public int intValue() {
            return this.f459a;
        }
    }

    /* renamed from: net.nend.android.NendAdNativeVideo$a */
    public static final class C0503a {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public C0540b f460a;
        /* access modifiers changed from: private */

        /* renamed from: b */
        public VideoClickOption f461b;
        /* access modifiers changed from: private */

        /* renamed from: c */
        public NendAdNative f462c;
        /* access modifiers changed from: private */

        /* renamed from: d */
        public int f463d;
        /* access modifiers changed from: private */

        /* renamed from: e */
        public Bitmap f464e;

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public C0503a mo7676a(C0540b bVar) {
            this.f460a = bVar;
            return this;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public C0503a mo7675a(VideoClickOption videoClickOption) {
            this.f461b = videoClickOption;
            return this;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public C0503a mo7674a(NendAdNative nendAdNative) {
            this.f462c = nendAdNative;
            return this;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public C0503a mo7672a(int i) {
            this.f463d = i;
            return this;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public C0503a mo7673a(Bitmap bitmap) {
            this.f464e = bitmap;
            return this;
        }

        /* renamed from: a */
        public NendAdNativeVideo mo7677a() {
            return new NendAdNativeVideo(this);
        }
    }

    /* renamed from: net.nend.android.NendAdNativeVideo$b */
    interface C0504b {
        /* renamed from: a */
        void mo7607a();
    }

    /* renamed from: net.nend.android.NendAdNativeVideo$c */
    enum C0505c {
        STANDBY,
        ACTIVATED,
        VIEWED,
        COMPLETED
    }

    protected NendAdNativeVideo(C0503a aVar) {
        this.f441d = new WeakReference<>(null);
        this.f442e = new WeakReference<>(null);
        this.f438a = new HashSet();
        this.f443f = aVar.f460a;
        this.f439b = aVar.f461b;
        this.f445h = aVar.f462c;
        this.f440c = aVar.f463d;
        this.f444g = aVar.f464e;
        this.f449l = C0505c.STANDBY;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo7630a(int i, int i2) {
        if (this.f449l.ordinal() < C0505c.ACTIVATED.ordinal()) {
            this.f449l = C0505c.ACTIVATED;
            m338a((Context) this.f441d.get(), i, i2);
            if (this.f446i != null) {
                this.f446i.onImpression(this);
            }
        }
    }

    public void deactivate() {
        for (C0504b a : this.f438a) {
            a.mo7607a();
        }
        this.f438a.clear();
        if (hasVideo()) {
            C0608c cVar = (C0608c) this.f442e.get();
            if (cVar != null) {
                cVar.mo7971b(this.f443f);
            }
            this.f443f = null;
            this.f444g = null;
        } else {
            this.f445h = null;
        }
        this.f446i = null;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.f443f, 0);
        parcel.writeInt(this.f439b.ordinal());
        parcel.writeInt(this.f448k);
        parcel.writeInt(this.f449l.ordinal());
        parcel.writeInt(this.f440c);
    }

    private NendAdNativeVideo(Parcel parcel) {
        this.f441d = new WeakReference<>(null);
        this.f442e = new WeakReference<>(null);
        this.f438a = new HashSet();
        this.f443f = (C0540b) parcel.readParcelable(C0540b.class.getClassLoader());
        this.f439b = (VideoClickOption) f436m.get(parcel.readInt());
        this.f448k = parcel.readInt();
        this.f449l = (C0505c) f437n.get(parcel.readInt());
        this.f440c = parcel.readInt();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo7634a(WeakReference<Context> weakReference) {
        this.f441d = weakReference;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public void mo7639b(WeakReference<C0608c> weakReference) {
        this.f442e = weakReference;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public C0540b mo7628a() {
        return this.f443f;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public int mo7637b() {
        return this.f448k;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo7629a(int i) {
        this.f448k = i;
    }

    public boolean hasVideo() {
        return this.f443f != null;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: c */
    public VideoClickOption mo7640c() {
        return this.f439b;
    }

    @Nullable
    public Bitmap getLogoImageBitmap() {
        if (this.f444g == null) {
            this.f444g = C0749a.m1181a(this.f443f.f608i);
        }
        return this.f444g;
    }

    public void downloadLogoImageBitmap(final Callback callback) {
        Bitmap logoImageBitmap = getLogoImageBitmap();
        if (logoImageBitmap != null) {
            callback.onSuccess(logoImageBitmap);
        } else {
            C0608c.m816a(this.f443f).mo7993a((Executor) new C0627a()).mo7996a((C0630d<? super T>) new C0630d<Bitmap>() {
                /* renamed from: a */
                public void mo7666a(Bitmap bitmap) {
                    NendAdNativeVideo.this.f444g = bitmap;
                    callback.onSuccess(bitmap);
                }
            }).mo7998b((C0630d<Throwable>) new C0630d<Throwable>() {
                /* renamed from: a */
                public void mo7666a(Throwable th) {
                    callback.onFailure(new C0524b(C0758g.ERR_UNEXPECTED));
                }
            });
        }
    }

    public boolean isMutePlayingFullscreen() {
        return this.f447j;
    }

    public void setMutePlayingFullscreen(boolean z) {
        this.f447j = z;
    }

    public String getLogoImageUrl() {
        return this.f443f.f608i;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: d */
    public String mo7641d() {
        return this.f443f.f563b;
    }

    public String getTitleText() {
        return this.f443f.f609j;
    }

    public String getDescriptionText() {
        return this.f443f.f611l;
    }

    public String getCallToActionText() {
        return this.f443f.f614o;
    }

    public String getAdvertiserName() {
        return this.f443f.f610k;
    }

    public int getVideoOrientation() {
        return this.f443f.f564c;
    }

    public float getUserRating() {
        return this.f443f.f612m;
    }

    public int getUserRatingCount() {
        return this.f443f.f613n;
    }

    public NendAdNative getFallbackAd() {
        return this.f445h;
    }

    public void registerInteractionViews(ArrayList<View> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((View) it.next()).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    NendAdNativeVideo.this.mo7633a((Context) NendAdNativeVideo.this.f441d.get(), NendAdNativeVideo.this.mo7641d(), false);
                }
            });
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo7633a(Context context, String str, boolean z) {
        m339a(context, z);
        C0755e.m1198a(context, str);
        if (this.f446i != null) {
            this.f446i.onClickAd(this);
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo7631a(final Context context) {
        C0740a.m1170a().mo8183a(new C0745d(context), new C0742a<String>() {
            /* renamed from: a */
            public void mo7491a(String str, Exception exc) {
                C0755e.m1198a(context, "https://www.nend.net/privacy/optsdkgate?uid=" + C0755e.m1201b(context) + "&spot=" + NendAdNativeVideo.this.f440c + "&gaid=" + str);
            }
        });
        if (this.f446i != null) {
            this.f446i.onClickInformation(this);
        }
    }

    public void setListener(NendAdNativeVideoListener nendAdNativeVideoListener) {
        this.f446i = nendAdNativeVideoListener;
    }

    public NendAdNativeVideoListener getListener() {
        return this.f446i;
    }

    /* renamed from: a */
    private void m338a(Context context, int i, int i2) {
        C0604b.m799a(context, this.f443f.f562a, i, i2);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public void mo7638b(Context context) {
        this.f449l = C0505c.VIEWED;
        C0604b.m810b(context, this.f443f.f562a);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public boolean mo7636a(int i, boolean z) {
        return C0604b.m806a(this.f443f, m341e(), i, z);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo7632a(Context context, int i, boolean z) {
        if (z && this.f449l != C0505c.COMPLETED) {
            this.f449l = C0505c.COMPLETED;
            C0604b.m803a(context, this.f443f.f562a, true, false, i);
        }
    }

    /* renamed from: a */
    private void m339a(Context context, boolean z) {
        C0604b.m802a(context, this.f443f.f562a, z);
    }

    /* renamed from: e */
    private boolean m341e() {
        return this.f449l.ordinal() >= C0505c.VIEWED.ordinal();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo7635a(C0504b bVar) {
        this.f438a.add(bVar);
    }
}
