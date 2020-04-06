package net.nend.android.internal.utilities.video;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;
import net.nend.android.internal.p022d.C0631e;
import net.nend.android.internal.p022d.C0646k;
import net.nend.android.internal.p022d.C0647l;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: net.nend.android.internal.utilities.video.c */
/* compiled from: NendLocationSensorUtility */
class C0765c {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public GoogleApiClient f1182a;

    /* renamed from: b */
    private OnConnectionFailedListener f1183b;

    /* renamed from: c */
    private ConnectionCallbacks f1184c;

    C0765c() {
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public C0646k<JSONObject> mo8212a(Context context) {
        final C0631e a = C0647l.m920a();
        if (C0773e.m1264a(context, "android.permission.ACCESS_FINE_LOCATION") || C0773e.m1264a(context, "android.permission.ACCESS_COARSE_LOCATION")) {
            this.f1184c = new ConnectionCallbacks() {
                public void onConnected(@Nullable Bundle bundle) {
                    Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(C0765c.this.f1182a);
                    C0765c.this.m1250a();
                    if (lastLocation != null) {
                        try {
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("lat", lastLocation.getLatitude());
                            jSONObject.put("lng", lastLocation.getLongitude());
                            a.mo7991a(jSONObject);
                        } catch (JSONException e) {
                            a.mo7992a(e.getCause());
                        }
                    } else {
                        a.mo7992a((Throwable) new Exception("Failed to get location."));
                    }
                }

                public void onConnectionSuspended(int i) {
                    C0765c.this.m1250a();
                    a.mo7992a((Throwable) new Exception("Failed to get location."));
                }
            };
            this.f1183b = new OnConnectionFailedListener() {
                public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                    C0765c.this.m1250a();
                    a.mo7992a((Throwable) new Exception("Failed to get location."));
                }
            };
            this.f1182a = new Builder(context).addApi(LocationServices.API).addConnectionCallbacks(this.f1184c).addOnConnectionFailedListener(this.f1183b).build();
            this.f1182a.connect();
        } else {
            a.mo7992a((Throwable) new Exception("Permission denied."));
        }
        return a.mo7990a();
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m1250a() {
        this.f1182a.unregisterConnectionCallbacks(this.f1184c);
        this.f1182a.unregisterConnectionFailedListener(this.f1183b);
        this.f1182a.disconnect();
    }
}
