package net.nend.android.internal.utilities.video;

import android.content.Context;
import java.io.File;
import java.util.ArrayList;
import net.nend.android.internal.utilities.C0753c;
import net.nend.android.internal.utilities.C0757f;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: net.nend.android.internal.utilities.video.b */
/* compiled from: EventTransmitUtils */
public class C0764b extends C0753c {
    /* renamed from: a */
    public static void m1247a(Context context, JSONObject jSONObject) {
        m1188a(context, String.valueOf(System.currentTimeMillis()) + ".json", ".nend_sdk_queue_video_event", jSONObject.toString());
    }

    /* renamed from: a */
    public static ArrayList<JSONObject> m1246a(Context context) {
        ArrayList<JSONObject> arrayList = new ArrayList<>();
        File a = m1185a(context, ".nend_sdk_queue_video_event");
        if (a.isDirectory()) {
            for (File a2 : a.listFiles()) {
                try {
                    arrayList.add(new JSONObject(m1186a(a2)));
                } catch (JSONException e) {
                    C0757f.m1218b("Failed to query queued video event.", (Throwable) e);
                }
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    public static void m1248b(Context context) {
        try {
            m1190b(context, ".nend_sdk_queue_video_event");
        } catch (Exception e) {
            C0757f.m1218b("Failed to delete file.", (Throwable) e);
        }
    }

    /* renamed from: a */
    public static int m1245a(int i) {
        return Math.round(((float) i) / 1000.0f);
    }
}
