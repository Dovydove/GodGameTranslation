package net.nend.android.internal.p008b.p012c;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import net.nend.android.internal.p008b.C0525a;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: net.nend.android.internal.b.c.d */
/* compiled from: VideoAd */
public abstract class C0544d extends C0525a {
    public static final Creator<C0544d> CREATOR = new Creator<C0544d>() {
        /* renamed from: a */
        public C0544d createFromParcel(Parcel parcel) {
            return null;
        }

        /* renamed from: a */
        public C0544d[] newArray(int i) {
            return null;
        }
    };

    /* renamed from: k */
    public final String[] f618k;

    /* renamed from: l */
    public final int f619l;

    /* renamed from: m */
    public String f620m;

    /* renamed from: n */
    public String f621n;

    /* renamed from: o */
    public String f622o;

    /* renamed from: p */
    public String f623p;

    public C0544d() {
        this.f618k = new String[0];
        this.f619l = 0;
    }

    public C0544d(JSONObject jSONObject) {
        super(jSONObject);
        this.f620m = jSONObject.getString("endcardHtml");
        this.f619l = jSONObject.getInt("displayType");
        if (!jSONObject.isNull("htmlOnPlaying")) {
            this.f621n = jSONObject.getString("htmlOnPlaying");
        } else {
            this.f621n = "";
        }
        JSONArray jSONArray = jSONObject.getJSONArray("assets");
        this.f618k = new String[jSONArray.length()];
        for (int i = 0; i < this.f618k.length; i++) {
            this.f618k[i] = jSONArray.getString(i);
        }
    }

    public C0544d(Parcel parcel) {
        super(parcel);
        this.f618k = parcel.createStringArray();
        this.f619l = parcel.readInt();
        this.f622o = parcel.readString();
        this.f623p = parcel.readString();
    }

    /* renamed from: b */
    public void mo7819b(String str, String str2) {
        this.f622o = str;
        this.f623p = str2;
        this.f620m = null;
        this.f621n = null;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeStringArray(this.f618k);
        parcel.writeInt(this.f619l);
        parcel.writeString(this.f622o);
        parcel.writeString(this.f623p);
    }
}
