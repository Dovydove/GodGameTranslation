package p004jp.p005co.imobile.sdkads.android;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: jp.co.imobile.sdkads.android.i */
final class C0344i implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C0341f f124a;

    /* renamed from: b */
    private final /* synthetic */ Future f125b;

    /* renamed from: c */
    private final /* synthetic */ String f126c;

    C0344i(C0341f fVar, Future future, String str) {
        this.f124a = fVar;
        this.f125b = future;
        this.f126c = str;
    }

    public final void run() {
        try {
            JSONObject jSONObject = (JSONObject) this.f125b.get();
            if (jSONObject != null) {
                try {
                    this.f124a.f106b.clear();
                    JSONArray jSONArray = jSONObject.getJSONObject("result").getJSONArray("viewHashes");
                    for (int i = 0; i < jSONArray.length() - 1; i++) {
                        this.f124a.f106b.put(jSONArray.getJSONObject(i).getString("advertisementId"), jSONArray.getJSONObject(i).getString("viewHash"));
                    }
                } catch (JSONException e) {
                    String str = "Ad response error.";
                    new StringBuilder("parse error value:").append(this.f126c);
                    C0359x.m126b(str, "imp");
                }
                String str2 = "Ad View imp send complete.";
                new StringBuilder("viewHash:").append(jSONObject.toString());
                C0359x.m126b(str2, "");
                return;
            }
            C0359x.m126b("Ad View imp send not complete.", "ErroCode(no response).");
        } catch (InterruptedException e2) {
            new StringBuilder("Callable InterruptedException.").append(e2.getMessage());
            C0359x.m126b("Ad View imp send not complete.", "Interrupt");
        } catch (ExecutionException e3) {
            if (e3.getCause().getClass() == C0360y.class) {
                new StringBuilder("Callable NotificationException. reason:").append(((C0360y) e3.getCause()).mo7267a());
                C0359x.m126b("Ad View imp send not complete.", "Notification");
                return;
            }
            new StringBuilder("Callable ExecutionException.").append(e3.getMessage());
            C0359x.m126b("Ad View imp send not complete.", "Execution");
        }
    }
}
