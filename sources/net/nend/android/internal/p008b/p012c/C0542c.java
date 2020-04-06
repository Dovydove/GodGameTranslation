package net.nend.android.internal.p008b.p012c;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import org.json.JSONObject;

/* renamed from: net.nend.android.internal.b.c.c */
/* compiled from: RewardedVideoAd */
public class C0542c extends C0544d {
    public static final Creator<C0542c> CREATOR = new Creator<C0542c>() {
        /* renamed from: a */
        public C0542c createFromParcel(Parcel parcel) {
            return new C0542c(parcel);
        }

        /* renamed from: a */
        public C0542c[] newArray(int i) {
            return new C0542c[i];
        }
    };

    /* renamed from: h */
    public final String f616h;

    /* renamed from: i */
    public final int f617i;

    private C0542c(JSONObject jSONObject) {
        super(jSONObject);
        JSONObject jSONObject2 = jSONObject.getJSONObject("reward");
        this.f616h = jSONObject2.getString("currName");
        this.f617i = jSONObject2.getInt("currAmount");
    }

    private C0542c(Parcel parcel) {
        super(parcel);
        this.f616h = parcel.readString();
        this.f617i = parcel.readInt();
    }

    /* renamed from: a */
    public static C0542c m504a(JSONObject jSONObject) {
        return new C0542c(jSONObject);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f616h);
        parcel.writeInt(this.f617i);
    }
}
