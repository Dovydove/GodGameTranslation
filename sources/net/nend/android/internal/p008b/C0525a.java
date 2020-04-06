package net.nend.android.internal.p008b;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import java.io.File;
import java.util.Arrays;
import org.json.JSONObject;

/* renamed from: net.nend.android.internal.b.a */
/* compiled from: Nend2Ad */
public class C0525a implements Parcelable {
    public static final Creator<C0525a> CREATOR = new Creator<C0525a>() {
        /* renamed from: a */
        public C0525a createFromParcel(Parcel parcel) {
            return new C0525a(parcel);
        }

        /* renamed from: a */
        public C0525a[] newArray(int i) {
            return new C0525a[i];
        }
    };

    /* renamed from: a */
    public final String f562a;

    /* renamed from: b */
    public final String f563b;

    /* renamed from: c */
    public final int f564c;

    /* renamed from: d */
    public final String f565d;

    /* renamed from: e */
    public final int f566e;

    /* renamed from: f */
    public String f567f;

    /* renamed from: g */
    public String f568g;

    /* renamed from: h */
    private final long f569h;

    public C0525a() {
        this.f562a = "";
        this.f563b = "";
        this.f564c = 0;
        this.f565d = "";
        this.f566e = 0;
        this.f569h = Long.MAX_VALUE;
    }

    public C0525a(JSONObject jSONObject) {
        this.f562a = jSONObject.getString("id");
        this.f565d = jSONObject.getString("videoUrl");
        this.f563b = jSONObject.getString("clickUrl");
        this.f564c = jSONObject.getInt("orientation");
        if (!jSONObject.isNull("viewEventTime")) {
            this.f566e = jSONObject.getInt("viewEventTime");
        } else {
            this.f566e = -1;
        }
        this.f569h = System.currentTimeMillis();
    }

    public C0525a(Parcel parcel) {
        this.f562a = parcel.readString();
        this.f563b = parcel.readString();
        this.f564c = parcel.readInt();
        this.f565d = parcel.readString();
        this.f566e = parcel.readInt();
        this.f567f = parcel.readString();
        this.f568g = parcel.readString();
        this.f569h = parcel.readLong();
    }

    /* renamed from: a */
    public boolean mo7780a() {
        return mo7781b() && !mo7782c();
    }

    /* renamed from: a */
    public void mo7779a(String str, String str2) {
        this.f567f = str;
        this.f568g = str2;
    }

    @VisibleForTesting
    /* renamed from: b */
    public boolean mo7781b() {
        for (String str : Arrays.asList(new String[]{this.f567f, this.f568g})) {
            if (!TextUtils.isEmpty(str) && !new File(str).exists()) {
                return false;
            }
        }
        return true;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f562a);
        parcel.writeString(this.f563b);
        parcel.writeInt(this.f564c);
        parcel.writeString(this.f565d);
        parcel.writeInt(this.f566e);
        parcel.writeString(this.f567f);
        parcel.writeString(this.f568g);
        parcel.writeLong(this.f569h);
    }

    /* renamed from: c */
    public boolean mo7782c() {
        return System.currentTimeMillis() - this.f569h >= 259200000;
    }
}
