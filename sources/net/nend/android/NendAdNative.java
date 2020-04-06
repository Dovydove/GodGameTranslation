package net.nend.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.view.View;
import android.widget.TextView;
import java.util.WeakHashMap;
import net.nend.android.internal.p007a.C0524b;
import net.nend.android.internal.p023ui.p024a.C0657f;
import net.nend.android.internal.utilities.C0740a;
import net.nend.android.internal.utilities.C0740a.C0742a;
import net.nend.android.internal.utilities.C0740a.C0745d;
import net.nend.android.internal.utilities.C0740a.C0748f;
import net.nend.android.internal.utilities.C0755e;
import net.nend.android.internal.utilities.C0757f;
import net.nend.android.internal.utilities.C0758g;

public class NendAdNative implements Parcelable {
    public static final Creator<NendAdNative> CREATOR = new Creator<NendAdNative>() {
        /* renamed from: a */
        public NendAdNative createFromParcel(Parcel parcel) {
            return new NendAdNative(parcel);
        }

        /* renamed from: a */
        public NendAdNative[] newArray(int i) {
            return new NendAdNative[i];
        }
    };

    /* renamed from: a */
    private final String f360a;

    /* renamed from: b */
    private final String f361b;

    /* renamed from: c */
    private final String f362c;

    /* renamed from: d */
    private final String f363d;

    /* renamed from: e */
    private final String f364e;

    /* renamed from: f */
    private final String f365f;

    /* renamed from: g */
    private final String f366g;

    /* renamed from: h */
    private final String f367h;

    /* renamed from: i */
    private final String f368i;

    /* renamed from: j */
    private final String f369j;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public int f370k;

    /* renamed from: l */
    private boolean f371l;

    /* renamed from: m */
    private WeakHashMap<String, Bitmap> f372m;

    /* renamed from: n */
    private OnClickListener f373n;

    /* renamed from: o */
    private C0657f f374o;

    public enum AdvertisingExplicitly {
        PR("PR"),
        SPONSORED("Sponsored"),
        AD("広告"),
        PROMOTION("プロモーション");
        

        /* renamed from: a */
        private String f380a;

        private AdvertisingExplicitly(String str) {
            this.f380a = str;
        }

        public String getText() {
            return this.f380a;
        }
    }

    public interface Callback {
        void onFailure(Exception exc);

        void onSuccess(Bitmap bitmap);
    }

    public interface OnClickListener {
        void onClick(NendAdNative nendAdNative);
    }

    /* renamed from: net.nend.android.NendAdNative$a */
    public static final class C0482a {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public String f381a;
        /* access modifiers changed from: private */

        /* renamed from: b */
        public String f382b;
        /* access modifiers changed from: private */

        /* renamed from: c */
        public String f383c;
        /* access modifiers changed from: private */

        /* renamed from: d */
        public String f384d;
        /* access modifiers changed from: private */

        /* renamed from: e */
        public String f385e;
        /* access modifiers changed from: private */

        /* renamed from: f */
        public String f386f;
        /* access modifiers changed from: private */

        /* renamed from: g */
        public String f387g;
        /* access modifiers changed from: private */

        /* renamed from: h */
        public String f388h;
        /* access modifiers changed from: private */

        /* renamed from: i */
        public String f389i;
        /* access modifiers changed from: private */

        /* renamed from: j */
        public String f390j;

        /* renamed from: a */
        public C0482a mo7579a(String str) {
            if (str != null) {
                this.f381a = str.replaceAll(" ", "%20");
            } else {
                this.f381a = null;
            }
            return this;
        }

        /* renamed from: b */
        public C0482a mo7581b(String str) {
            if (str != null) {
                this.f382b = str.replaceAll(" ", "%20");
            } else {
                this.f382b = null;
            }
            return this;
        }

        /* renamed from: c */
        public C0482a mo7582c(String str) {
            if (str != null) {
                this.f383c = str.replaceAll(" ", "%20");
            } else {
                this.f383c = null;
            }
            return this;
        }

        /* renamed from: d */
        public C0482a mo7583d(String str) {
            if (str != null) {
                this.f384d = str.replaceAll(" ", "%20");
            } else {
                this.f384d = null;
            }
            return this;
        }

        /* renamed from: e */
        public C0482a mo7584e(String str) {
            this.f385e = str;
            return this;
        }

        /* renamed from: f */
        public C0482a mo7585f(String str) {
            this.f386f = str;
            return this;
        }

        /* renamed from: g */
        public C0482a mo7586g(String str) {
            this.f387g = str;
            return this;
        }

        /* renamed from: h */
        public C0482a mo7587h(String str) {
            this.f388h = str;
            return this;
        }

        /* renamed from: i */
        public C0482a mo7588i(String str) {
            this.f389i = str;
            return this;
        }

        /* renamed from: j */
        public C0482a mo7589j(String str) {
            this.f390j = str;
            return this;
        }

        /* renamed from: a */
        public NendAdNative mo7580a() {
            return new NendAdNative(this);
        }
    }

    private NendAdNative(C0482a aVar) {
        this.f371l = false;
        this.f372m = new WeakHashMap<>();
        this.f360a = aVar.f381a;
        this.f361b = aVar.f382b;
        this.f362c = aVar.f383c;
        this.f363d = aVar.f384d;
        this.f364e = aVar.f385e;
        this.f365f = aVar.f386f;
        this.f366g = aVar.f387g;
        this.f367h = aVar.f388h;
        this.f368i = aVar.f389i;
        this.f369j = aVar.f390j;
        this.f374o = new C0657f();
    }

    public void intoView(View view, NendAdNativeViewBinder nendAdNativeViewBinder) {
        intoView(new NendAdNativeViewHolder(view, nendAdNativeViewBinder));
    }

    public void intoView(NendAdNativeViewHolder nendAdNativeViewHolder) {
        this.f374o.mo8023a(nendAdNativeViewHolder, this);
    }

    public void onClick() {
        if (this.f373n != null) {
            this.f373n.onClick(this);
        }
    }

    public void onClickInformation(final Context context) {
        C0740a.m1170a().mo8183a(new C0745d(context), new C0742a<String>() {
            /* renamed from: a */
            public void mo7491a(String str, Exception exc) {
                C0755e.m1198a(context, "https://www.nend.net/privacy/optsdkgate?uid=" + C0755e.m1201b(context) + "&spot=" + NendAdNative.this.f370k + "&gaid=" + str);
            }
        });
    }

    public void onImpression() {
        if (!this.f371l) {
            this.f371l = true;
            C0740a.m1170a().mo8182a(new C0748f(m255a()));
            C0757f.m1217b("send impression");
        }
    }

    public Bitmap getCache(String str) {
        return (Bitmap) this.f372m.get(str);
    }

    public void setCache(String str, Bitmap bitmap) {
        this.f372m.put(str, bitmap);
    }

    public String getCampaignId() {
        return this.f369j;
    }

    public String getAdImageUrl() {
        return this.f360a;
    }

    public String getLogoImageUrl() {
        return this.f361b;
    }

    public String getClickUrl() {
        return this.f362c;
    }

    /* renamed from: a */
    private String m255a() {
        return this.f363d;
    }

    public String getTitleText() {
        return this.f364e;
    }

    public String getContentText() {
        return this.f365f;
    }

    public String getPromotionUrl() {
        return this.f366g;
    }

    public String getPromotionName() {
        return this.f367h;
    }

    public String getActionText() {
        return this.f368i;
    }

    public void setSpotId(int i) {
        this.f370k = i;
    }

    public boolean isSentImpression() {
        return this.f371l;
    }

    public void downloadAdImage(Callback callback) {
        if (getAdImageUrl() != null) {
            this.f374o.mo8022a(getAdImageUrl(), this, callback);
        } else {
            callback.onFailure(new C0524b(C0758g.ERR_NO_AD_IMAGE));
        }
    }

    public void downloadLogoImage(Callback callback) {
        if (getLogoImageUrl() != null) {
            this.f374o.mo8022a(getLogoImageUrl(), this, callback);
        } else {
            callback.onFailure(new C0524b(C0758g.ERR_NO_LOGO_IMAGE));
        }
    }

    public void activate(View view, TextView textView) {
        if (view == null || textView == null) {
            throw new NullPointerException("adContainer and prTextView is required.");
        }
        this.f374o.mo8021a(view, textView, this);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.f373n = onClickListener;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f360a);
        parcel.writeString(this.f361b);
        parcel.writeString(this.f362c);
        parcel.writeString(this.f363d);
        parcel.writeString(this.f364e);
        parcel.writeString(this.f365f);
        parcel.writeString(this.f366g);
        parcel.writeString(this.f367h);
        parcel.writeString(this.f368i);
        parcel.writeString(this.f369j);
        parcel.writeInt(this.f370k);
        parcel.writeByte(this.f371l ? (byte) 1 : 0);
    }

    protected NendAdNative(Parcel parcel) {
        boolean z = false;
        this.f371l = false;
        this.f372m = new WeakHashMap<>();
        this.f360a = parcel.readString();
        this.f361b = parcel.readString();
        this.f362c = parcel.readString();
        this.f363d = parcel.readString();
        this.f364e = parcel.readString();
        this.f365f = parcel.readString();
        this.f366g = parcel.readString();
        this.f367h = parcel.readString();
        this.f368i = parcel.readString();
        this.f369j = parcel.readString();
        this.f370k = parcel.readInt();
        if (parcel.readByte() != 0) {
            z = true;
        }
        this.f371l = z;
    }
}
