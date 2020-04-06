package com.google.android.gms.common;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.ICertData;
import com.google.android.gms.common.internal.ICertData.Stub;
import com.google.android.gms.common.internal.IGoogleCertificatesApi;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.LoadingException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.CheckReturnValue;
import javax.annotation.concurrent.GuardedBy;

@CheckReturnValue
final class GoogleCertificates {
    private static volatile IGoogleCertificatesApi zzax;
    private static final Object zzay = new Object();
    private static Context zzaz;
    @GuardedBy("GoogleCertificates.class")
    private static Set<ICertData> zzba;
    @GuardedBy("GoogleCertificates.class")
    private static Set<ICertData> zzbb;

    static abstract class CertData extends Stub {
        private int zzbc;

        protected CertData(byte[] bArr) {
            Preconditions.checkArgument(bArr.length == 25);
            this.zzbc = Arrays.hashCode(bArr);
        }

        protected static byte[] zzd(String str) {
            try {
                return str.getBytes("ISO-8859-1");
            } catch (UnsupportedEncodingException e) {
                throw new AssertionError(e);
            }
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof ICertData)) {
                return false;
            }
            try {
                ICertData iCertData = (ICertData) obj;
                if (iCertData.getHashCode() != hashCode()) {
                    return false;
                }
                IObjectWrapper bytesWrapped = iCertData.getBytesWrapped();
                if (bytesWrapped == null) {
                    return false;
                }
                return Arrays.equals(getBytes(), (byte[]) ObjectWrapper.unwrap(bytesWrapped));
            } catch (RemoteException e) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
                return false;
            }
        }

        /* access modifiers changed from: 0000 */
        public abstract byte[] getBytes();

        public IObjectWrapper getBytesWrapped() {
            return ObjectWrapper.wrap(getBytes());
        }

        public int getHashCode() {
            return hashCode();
        }

        public int hashCode() {
            return this.zzbc;
        }
    }

    static synchronized void init(Context context) {
        synchronized (GoogleCertificates.class) {
            if (zzaz != null) {
                Log.w("GoogleCertificates", "GoogleCertificates has been initialized already");
            } else if (context != null) {
                zzaz = context.getApplicationContext();
            }
        }
    }

    static zzg zza(String str, CertData certData, boolean z) {
        boolean z2 = true;
        try {
            zzc();
            Preconditions.checkNotNull(zzaz);
            try {
                if (zzax.isGoogleOrPlatformSigned(new GoogleCertificatesQuery(str, certData, z), ObjectWrapper.wrap(zzaz.getPackageManager()))) {
                    return zzg.zzg();
                }
                if (z || !zza(str, certData, true).zzbl) {
                    z2 = false;
                }
                return zzg.zza(str, certData, z, z2);
            } catch (RemoteException e) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
                return zzg.zza("module call", e);
            }
        } catch (LoadingException e2) {
            return zzg.zza("module init", e2);
        }
    }

    private static Set<ICertData> zza(IBinder[] iBinderArr) throws RemoteException {
        HashSet hashSet = new HashSet(r1);
        for (IBinder asInterface : iBinderArr) {
            ICertData asInterface2 = Stub.asInterface(asInterface);
            if (asInterface2 != null) {
                hashSet.add(asInterface2);
            }
        }
        return hashSet;
    }

    private static void zzc() throws LoadingException {
        if (zzax == null) {
            Preconditions.checkNotNull(zzaz);
            synchronized (zzay) {
                if (zzax == null) {
                    zzax = IGoogleCertificatesApi.Stub.asInterface(DynamiteModule.load(zzaz, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING, "com.google.android.gms.googlecertificates").instantiate("com.google.android.gms.common.GoogleCertificatesImpl"));
                }
            }
        }
    }

    static synchronized Set<ICertData> zzd() {
        Set<ICertData> emptySet;
        synchronized (GoogleCertificates.class) {
            if (zzba != null) {
                emptySet = zzba;
            } else {
                try {
                    zzc();
                    try {
                        IObjectWrapper googleCertificates = zzax.getGoogleCertificates();
                        if (googleCertificates == null) {
                            Log.e("GoogleCertificates", "Failed to get Google certificates from remote");
                            emptySet = Collections.emptySet();
                        } else {
                            zzba = zza((IBinder[]) ObjectWrapper.unwrap(googleCertificates));
                            emptySet = zzba;
                        }
                    } catch (RemoteException e) {
                        Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
                        emptySet = Collections.emptySet();
                    }
                } catch (LoadingException e2) {
                    Log.e("GoogleCertificates", "Failed to load com.google.android.gms.googlecertificates", e2);
                    emptySet = Collections.emptySet();
                }
            }
        }
        return emptySet;
    }

    static synchronized Set<ICertData> zze() {
        Set<ICertData> emptySet;
        synchronized (GoogleCertificates.class) {
            if (zzbb != null) {
                emptySet = zzbb;
            } else {
                try {
                    zzc();
                    try {
                        IObjectWrapper googleReleaseCertificates = zzax.getGoogleReleaseCertificates();
                        if (googleReleaseCertificates == null) {
                            Log.e("GoogleCertificates", "Failed to get Google certificates from remote");
                            emptySet = Collections.emptySet();
                        } else {
                            zzbb = zza((IBinder[]) ObjectWrapper.unwrap(googleReleaseCertificates));
                            emptySet = zzbb;
                        }
                    } catch (RemoteException e) {
                        Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
                        emptySet = Collections.emptySet();
                    }
                } catch (LoadingException e2) {
                    Log.e("GoogleCertificates", "Failed to load com.google.android.gms.googlecertificates", e2);
                    emptySet = Collections.emptySet();
                }
            }
        }
        return emptySet;
    }
}
