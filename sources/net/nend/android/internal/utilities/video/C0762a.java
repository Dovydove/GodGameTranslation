package net.nend.android.internal.utilities.video;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;

/* renamed from: net.nend.android.internal.utilities.video.a */
/* compiled from: DisplayTimeManager */
public class C0762a implements Parcelable {
    public static final Creator<C0762a> CREATOR = new Creator<C0762a>() {
        /* renamed from: a */
        public C0762a createFromParcel(Parcel parcel) {
            return new C0762a(parcel);
        }

        /* renamed from: a */
        public C0762a[] newArray(int i) {
            return null;
        }
    };

    /* renamed from: a */
    private long f1180a;

    /* renamed from: b */
    private long f1181b;

    public C0762a() {
        this.f1180a = 0;
        this.f1181b = 0;
    }

    private C0762a(Parcel parcel) {
        this.f1180a = parcel.readLong();
        this.f1181b = parcel.readLong();
    }

    /* renamed from: a */
    public void mo8202a() {
        this.f1180a = SystemClock.elapsedRealtime();
    }

    /* renamed from: b */
    public void mo8203b() {
        if (mo8205d()) {
            this.f1181b += SystemClock.elapsedRealtime() - this.f1180a;
        }
        this.f1180a = 0;
    }

    /* renamed from: c */
    public long mo8204c() {
        return this.f1181b;
    }

    /* renamed from: d */
    public boolean mo8205d() {
        return this.f1180a != 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f1180a);
        parcel.writeLong(this.f1181b);
    }

    public int describeContents() {
        return 0;
    }
}
