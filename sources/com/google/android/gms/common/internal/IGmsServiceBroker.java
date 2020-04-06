package com.google.android.gms.common.internal;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IGmsServiceBroker extends IInterface {

    public static abstract class Stub extends Binder implements IGmsServiceBroker {

        private static class zza implements IGmsServiceBroker {
            private final IBinder zza;

            zza(IBinder iBinder) {
                this.zza = iBinder;
            }

            public final IBinder asBinder() {
                return this.zza;
            }

            public final void getService(IGmsCallbacks iGmsCallbacks, GetServiceRequest getServiceRequest) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.common.internal.IGmsServiceBroker");
                    obtain.writeStrongBinder(iGmsCallbacks != null ? iGmsCallbacks.asBinder() : null);
                    if (getServiceRequest != null) {
                        obtain.writeInt(1);
                        getServiceRequest.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.zza.transact(46, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public Stub() {
            attachInterface(this, "com.google.android.gms.common.internal.IGmsServiceBroker");
        }

        public static IGmsServiceBroker asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof IGmsServiceBroker)) ? new zza(iBinder) : (IGmsServiceBroker) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        /* access modifiers changed from: protected */
        public void getLegacyService(int i, IGmsCallbacks iGmsCallbacks, int i2, String str, String str2, String[] strArr, Bundle bundle, IBinder iBinder, String str3, String str4) throws RemoteException {
            throw new UnsupportedOperationException();
        }

        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r12, android.os.Parcel r13, android.os.Parcel r14, int r15) throws android.os.RemoteException {
            /*
                r11 = this;
                r1 = 0
                r0 = 16777215(0xffffff, float:2.3509886E-38)
                if (r12 <= r0) goto L_0x000b
                boolean r0 = super.onTransact(r12, r13, r14, r15)
            L_0x000a:
                return r0
            L_0x000b:
                java.lang.String r0 = "com.google.android.gms.common.internal.IGmsServiceBroker"
                r13.enforceInterface(r0)
                android.os.IBinder r0 = r13.readStrongBinder()
                com.google.android.gms.common.internal.IGmsCallbacks r2 = com.google.android.gms.common.internal.IGmsCallbacks.Stub.asInterface(r0)
                r0 = 46
                if (r12 != r0) goto L_0x0032
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0121
                android.os.Parcelable$Creator<com.google.android.gms.common.internal.GetServiceRequest> r0 = com.google.android.gms.common.internal.GetServiceRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r13)
                com.google.android.gms.common.internal.GetServiceRequest r0 = (com.google.android.gms.common.internal.GetServiceRequest) r0
            L_0x002a:
                r11.getService(r2, r0)
            L_0x002d:
                r14.writeNoException()
                r0 = 1
                goto L_0x000a
            L_0x0032:
                r0 = 47
                if (r12 != r0) goto L_0x0048
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x011e
                android.os.Parcelable$Creator<com.google.android.gms.common.internal.ValidateAccountRequest> r0 = com.google.android.gms.common.internal.ValidateAccountRequest.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r13)
                com.google.android.gms.common.internal.ValidateAccountRequest r0 = (com.google.android.gms.common.internal.ValidateAccountRequest) r0
            L_0x0044:
                r11.validateAccount(r2, r0)
                goto L_0x002d
            L_0x0048:
                int r3 = r13.readInt()
                r0 = 4
                if (r12 == r0) goto L_0x011b
                java.lang.String r4 = r13.readString()
            L_0x0053:
                switch(r12) {
                    case 1: goto L_0x007a;
                    case 2: goto L_0x00f0;
                    case 3: goto L_0x0056;
                    case 4: goto L_0x0056;
                    case 5: goto L_0x00f0;
                    case 6: goto L_0x00f0;
                    case 7: goto L_0x00f0;
                    case 8: goto L_0x00f0;
                    case 9: goto L_0x0098;
                    case 10: goto L_0x00d7;
                    case 11: goto L_0x00f0;
                    case 12: goto L_0x00f0;
                    case 13: goto L_0x00f0;
                    case 14: goto L_0x00f0;
                    case 15: goto L_0x00f0;
                    case 16: goto L_0x00f0;
                    case 17: goto L_0x00f0;
                    case 18: goto L_0x00f0;
                    case 19: goto L_0x0062;
                    case 20: goto L_0x00bc;
                    case 21: goto L_0x0056;
                    case 22: goto L_0x0056;
                    case 23: goto L_0x00f0;
                    case 24: goto L_0x0056;
                    case 25: goto L_0x00f0;
                    case 26: goto L_0x0056;
                    case 27: goto L_0x00f0;
                    case 28: goto L_0x0056;
                    case 29: goto L_0x0056;
                    case 30: goto L_0x00bc;
                    case 31: goto L_0x0056;
                    case 32: goto L_0x0056;
                    case 33: goto L_0x0056;
                    case 34: goto L_0x00e5;
                    case 35: goto L_0x0056;
                    case 36: goto L_0x0056;
                    case 37: goto L_0x00f0;
                    case 38: goto L_0x00f0;
                    case 39: goto L_0x0056;
                    case 40: goto L_0x0056;
                    case 41: goto L_0x00f0;
                    case 42: goto L_0x0056;
                    case 43: goto L_0x00f0;
                    default: goto L_0x0056;
                }
            L_0x0056:
                r10 = r1
                r9 = r1
                r8 = r1
                r7 = r1
                r6 = r1
                r5 = r1
            L_0x005c:
                r0 = r11
                r1 = r12
                r0.getLegacyService(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
                goto L_0x002d
            L_0x0062:
                android.os.IBinder r8 = r13.readStrongBinder()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0114
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r13)
                android.os.Bundle r0 = (android.os.Bundle) r0
                r10 = r1
                r9 = r1
                r7 = r0
                r6 = r1
                r5 = r1
                goto L_0x005c
            L_0x007a:
                java.lang.String r9 = r13.readString()
                java.lang.String[] r6 = r13.createStringArray()
                java.lang.String r5 = r13.readString()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x010f
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r13)
                android.os.Bundle r0 = (android.os.Bundle) r0
                r10 = r1
                r8 = r1
                r7 = r0
                goto L_0x005c
            L_0x0098:
                java.lang.String r5 = r13.readString()
                java.lang.String[] r6 = r13.createStringArray()
                java.lang.String r9 = r13.readString()
                android.os.IBinder r8 = r13.readStrongBinder()
                java.lang.String r10 = r13.readString()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x010c
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r13)
                android.os.Bundle r0 = (android.os.Bundle) r0
                r7 = r0
                goto L_0x005c
            L_0x00bc:
                java.lang.String[] r6 = r13.createStringArray()
                java.lang.String r5 = r13.readString()
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0106
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r13)
                android.os.Bundle r0 = (android.os.Bundle) r0
                r10 = r1
                r9 = r1
                r8 = r1
                r7 = r0
                goto L_0x005c
            L_0x00d7:
                java.lang.String r5 = r13.readString()
                java.lang.String[] r6 = r13.createStringArray()
                r10 = r1
                r9 = r1
                r8 = r1
                r7 = r1
                goto L_0x005c
            L_0x00e5:
                java.lang.String r5 = r13.readString()
                r10 = r1
                r9 = r1
                r8 = r1
                r7 = r1
                r6 = r1
                goto L_0x005c
            L_0x00f0:
                int r0 = r13.readInt()
                if (r0 == 0) goto L_0x0056
                android.os.Parcelable$Creator r0 = android.os.Bundle.CREATOR
                java.lang.Object r0 = r0.createFromParcel(r13)
                android.os.Bundle r0 = (android.os.Bundle) r0
                r10 = r1
                r9 = r1
                r8 = r1
                r7 = r0
                r6 = r1
                r5 = r1
                goto L_0x005c
            L_0x0106:
                r10 = r1
                r9 = r1
                r8 = r1
                r7 = r1
                goto L_0x005c
            L_0x010c:
                r7 = r1
                goto L_0x005c
            L_0x010f:
                r10 = r1
                r8 = r1
                r7 = r1
                goto L_0x005c
            L_0x0114:
                r10 = r1
                r9 = r1
                r7 = r1
                r6 = r1
                r5 = r1
                goto L_0x005c
            L_0x011b:
                r4 = r1
                goto L_0x0053
            L_0x011e:
                r0 = r1
                goto L_0x0044
            L_0x0121:
                r0 = r1
                goto L_0x002a
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.IGmsServiceBroker.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        /* access modifiers changed from: protected */
        public void validateAccount(IGmsCallbacks iGmsCallbacks, ValidateAccountRequest validateAccountRequest) throws RemoteException {
            throw new UnsupportedOperationException();
        }
    }

    void getService(IGmsCallbacks iGmsCallbacks, GetServiceRequest getServiceRequest) throws RemoteException;
}
