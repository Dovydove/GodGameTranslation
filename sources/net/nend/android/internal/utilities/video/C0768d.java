package net.nend.android.internal.utilities.video;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import net.nend.android.internal.p022d.C0630d;
import net.nend.android.internal.p022d.C0631e;
import net.nend.android.internal.p022d.C0636g;
import net.nend.android.internal.p022d.C0646k;
import net.nend.android.internal.p022d.C0647l;
import net.nend.android.internal.p022d.C0649m;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: net.nend.android.internal.utilities.video.d */
/* compiled from: NendSensorUtility */
public class C0768d {

    /* renamed from: a */
    private final Context f1189a;

    public C0768d(Context context) {
        this.f1189a = context;
    }

    /* renamed from: a */
    public C0646k<JSONObject> mo8216a() {
        final C0631e a = C0647l.m920a();
        C0647l.m925a(m1253a(this.f1189a, 13), m1253a(this.f1189a, 12)).mo7996a((C0630d<? super T>) new C0630d<C0649m<Integer, Integer>>() {
            /* renamed from: a */
            public void mo7666a(C0649m<Integer, Integer> mVar) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    if (mVar.f906a != null) {
                        jSONObject.put("temperature", ((Integer) mVar.f906a).intValue());
                    }
                    if (mVar.f907b != null) {
                        jSONObject.put("humidity", ((Integer) mVar.f907b).intValue());
                    }
                    if (jSONObject.length() == 0) {
                        a.mo7992a((Throwable) new Exception("Failed to get the sensor values."));
                    } else {
                        a.mo7991a(jSONObject);
                    }
                } catch (JSONException e) {
                    a.mo7992a(e.getCause());
                }
            }
        });
        return a.mo7990a();
    }

    /* renamed from: b */
    public C0646k<JSONObject> mo8217b() {
        final C0631e a = C0647l.m920a();
        C0647l.m925a(m1254b(this.f1189a, 1), m1254b(this.f1189a, 2)).mo7996a((C0630d<? super T>) new C0630d<C0649m<float[], float[]>>() {
            /* renamed from: a */
            public void mo7666a(C0649m<float[], float[]> mVar) {
                if (mVar.f906a == null || mVar.f907b == null) {
                    a.mo7992a((Throwable) new Exception("Failed to get the sensor values."));
                    return;
                }
                float[] fArr = new float[9];
                float[] fArr2 = new float[3];
                float[] fArr3 = new float[3];
                float[] fArr4 = new float[3];
                System.arraycopy(mVar.f906a, 0, fArr2, 0, fArr2.length);
                System.arraycopy(mVar.f907b, 0, fArr3, 0, fArr3.length);
                if (SensorManager.getRotationMatrix(fArr, null, fArr2, fArr3)) {
                    SensorManager.getOrientation(fArr, fArr4);
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("yaw", (double) fArr4[0]);
                        jSONObject.put("pitch", (double) fArr4[1]);
                        jSONObject.put("roll", (double) fArr4[2]);
                        a.mo7991a(jSONObject);
                    } catch (JSONException e) {
                        a.mo7992a(e.getCause());
                    }
                } else {
                    a.mo7992a((Throwable) new Exception("Failed to getRotationMatrix."));
                }
            }
        });
        return a.mo7990a();
    }

    /* renamed from: c */
    public C0646k<JSONObject> mo8218c() {
        try {
            Class.forName("com.google.android.gms.location.LocationServices");
            return new C0765c().mo8212a(this.f1189a);
        } catch (ClassNotFoundException e) {
            return C0647l.m922a((Throwable) e);
        }
    }

    /* renamed from: a */
    private C0646k<Integer> m1253a(Context context, int i) {
        return m1254b(context, i).mo7999b((C0636g<? super T, ? extends C0646k<? extends R>>) new C0636g<float[], C0646k<? extends Integer>>() {
            /* renamed from: a */
            public C0646k<Integer> mo7540a(float[] fArr) {
                return C0647l.m921a(fArr == null ? null : Integer.valueOf((int) fArr[0]));
            }
        });
    }

    /* renamed from: b */
    private C0646k<float[]> m1254b(Context context, int i) {
        final C0631e a = C0647l.m920a();
        final SensorManager sensorManager = (SensorManager) context.getSystemService("sensor");
        final Sensor defaultSensor = sensorManager.getDefaultSensor(i);
        if (defaultSensor != null) {
            sensorManager.registerListener(new SensorEventListener() {
                public void onSensorChanged(SensorEvent sensorEvent) {
                    sensorManager.unregisterListener(this, defaultSensor);
                    float[] fArr = new float[3];
                    if (sensorEvent.values.length <= fArr.length) {
                        System.arraycopy(sensorEvent.values, 0, fArr, 0, sensorEvent.values.length);
                        a.mo7991a(fArr);
                        return;
                    }
                    a.mo7991a(null);
                }

                public void onAccuracyChanged(Sensor sensor, int i) {
                }
            }, defaultSensor, 2);
        } else {
            a.mo7991a(null);
        }
        return a.mo7990a();
    }
}
