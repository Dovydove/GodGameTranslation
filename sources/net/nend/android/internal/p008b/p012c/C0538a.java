package net.nend.android.internal.p008b.p012c;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import net.nend.android.NendAdFullBoard;
import org.json.JSONObject;

/* renamed from: net.nend.android.internal.b.c.a */
/* compiled from: InterstitialVideoAd */
public class C0538a extends C0544d {
    public static final Creator<C0538a> CREATOR = new Creator<C0538a>() {
        /* renamed from: a */
        public C0538a createFromParcel(Parcel parcel) {
            return new C0538a(parcel);
        }

        /* renamed from: a */
        public C0538a[] newArray(int i) {
            return new C0538a[i];
        }
    };

    /* renamed from: h */
    public final int f604h;

    /* renamed from: i */
    public final String f605i;

    /* renamed from: j */
    public NendAdFullBoard f606j;

    private C0538a(JSONObject jSONObject) {
        super(jSONObject);
        this.f604h = jSONObject.getInt("skipOffset");
        this.f605i = jSONObject.getString("ctaButtonText");
    }

    private C0538a() {
        this.f604h = 0;
        this.f605i = "";
    }

    private C0538a(Parcel parcel) {
        super(parcel);
        this.f604h = parcel.readInt();
        this.f605i = parcel.readString();
    }

    /* renamed from: a */
    public static C0538a m497a(JSONObject jSONObject) {
        return new C0538a(jSONObject);
    }

    /* renamed from: a */
    public static C0538a m496a(NendAdFullBoard nendAdFullBoard) {
        C0538a aVar = new C0538a();
        aVar.mo7779a("", "");
        aVar.mo7819b("", "");
        aVar.f606j = nendAdFullBoard;
        return aVar;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.f604h);
        parcel.writeString(this.f605i);
    }
}
