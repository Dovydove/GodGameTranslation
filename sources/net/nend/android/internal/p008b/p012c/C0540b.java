package net.nend.android.internal.p008b.p012c;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import net.nend.android.NendAdNative;
import net.nend.android.internal.p008b.C0525a;
import org.json.JSONObject;

/* renamed from: net.nend.android.internal.b.c.b */
/* compiled from: NativeAd */
public class C0540b extends C0525a {
    public static final Creator<C0540b> CREATOR = new Creator<C0540b>() {
        /* renamed from: a */
        public C0540b createFromParcel(Parcel parcel) {
            return new C0540b(parcel);
        }

        /* renamed from: a */
        public C0540b[] newArray(int i) {
            return new C0540b[i];
        }
    };

    /* renamed from: h */
    public final int f607h;

    /* renamed from: i */
    public final String f608i;

    /* renamed from: j */
    public final String f609j;

    /* renamed from: k */
    public final String f610k;

    /* renamed from: l */
    public final String f611l;

    /* renamed from: m */
    public final float f612m;

    /* renamed from: n */
    public final int f613n;

    /* renamed from: o */
    public final String f614o;

    /* renamed from: p */
    public NendAdNative f615p;

    private C0540b(JSONObject jSONObject) {
        super(jSONObject);
        this.f607h = jSONObject.getInt("acquiredId");
        this.f608i = jSONObject.getString("logoImageUrl");
        this.f609j = jSONObject.getString("title");
        this.f610k = jSONObject.getString("advertiserName");
        this.f611l = jSONObject.getString("description");
        if (!jSONObject.isNull("userRating")) {
            this.f612m = (float) jSONObject.getDouble("userRating");
        } else {
            this.f612m = -1.0f;
        }
        if (!jSONObject.isNull("userRatingCount")) {
            this.f613n = jSONObject.getInt("userRatingCount");
        } else {
            this.f613n = -1;
        }
        this.f614o = jSONObject.getString("ctaButtonText");
    }

    private C0540b() {
        this.f607h = 0;
        this.f608i = null;
        this.f609j = null;
        this.f610k = null;
        this.f611l = null;
        this.f612m = 0.0f;
        this.f613n = 0;
        this.f614o = null;
    }

    private C0540b(Parcel parcel) {
        super(parcel);
        this.f607h = parcel.readInt();
        this.f608i = parcel.readString();
        this.f609j = parcel.readString();
        this.f610k = parcel.readString();
        this.f611l = parcel.readString();
        this.f612m = parcel.readFloat();
        this.f613n = parcel.readInt();
        this.f614o = parcel.readString();
    }

    /* renamed from: a */
    public static C0540b m501a(JSONObject jSONObject) {
        return new C0540b(jSONObject);
    }

    /* renamed from: a */
    public static C0540b m500a(NendAdNative nendAdNative) {
        C0540b bVar = new C0540b();
        bVar.mo7779a("", "");
        bVar.f615p = nendAdNative;
        return bVar;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f607h);
        parcel.writeString(this.f608i);
        parcel.writeString(this.f609j);
        parcel.writeString(this.f610k);
        parcel.writeString(this.f611l);
        parcel.writeFloat(this.f612m);
        parcel.writeInt(this.f613n);
        parcel.writeString(this.f614o);
    }
}
