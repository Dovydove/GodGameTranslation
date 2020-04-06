package p004jp.p005co.imobile.sdkads.android;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: jp.co.imobile.sdkads.android.ak */
final class C0329ak implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C0361z f71a;

    /* renamed from: b */
    private final /* synthetic */ Future f72b;

    /* renamed from: c */
    private final /* synthetic */ C0361z f73c;

    C0329ak(C0361z zVar, Future future, C0361z zVar2) {
        this.f71a = zVar;
        this.f72b = future;
        this.f73c = zVar2;
    }

    public final void run() {
        try {
            JSONObject jSONObject = (JSONObject) this.f72b.get();
            if (jSONObject == null) {
                C0359x.m126b("Spot response data Error from c.", "empty");
                this.f71a.f207t.onFailed(FailNotificationReason.RESPONSE);
            }
            try {
                if (!jSONObject.getString("error").equals("null")) {
                    if (jSONObject.getJSONObject("error").getString("code").equals("0002")) {
                        C0359x.m126b("Ad response data Error from c.", "authority");
                        this.f71a.f207t.onFailed(FailNotificationReason.AUTHORITY);
                    } else {
                        C0359x.m126b("Ad response data Error from c.", "response");
                        this.f71a.f207t.onFailed(FailNotificationReason.RESPONSE);
                    }
                }
                JSONObject jSONObject2 = jSONObject.getJSONObject("result");
                this.f71a.f193f = jSONObject2.getInt("intervalTime");
                new StringBuilder("from spot info value:").append(jSONObject2.getInt("intervalTime"));
                C0359x.m125a(null);
                this.f71a.f191d = jSONObject2.getInt("skipCount");
                new StringBuilder("from spot info value:").append(jSONObject2.getInt("skipCount"));
                C0359x.m125a(null);
                this.f71a.f194g = jSONObject2.getInt("refreshTime");
                new StringBuilder("from spot info value:").append(jSONObject2.getInt("refreshTime"));
                C0359x.m125a(null);
                C0361z zVar = this.f71a;
                C0353r.m99a();
                zVar.f195h = C0353r.m94a(jSONObject2.getInt("displayWidth"));
                C0361z zVar2 = this.f71a;
                C0353r.m99a();
                zVar2.f196i = C0353r.m94a(jSONObject2.getInt("displayHeight"));
                new StringBuilder("from spot info value: width : ").append(jSONObject2.getInt("displayWidth")).append("height : ").append(jSONObject2.getInt("displayHeight"));
                C0359x.m125a(null);
                this.f71a.f198k = jSONObject2.getInt("stockCount");
                new StringBuilder("from spot info value:").append(jSONObject2.getInt("stockCount"));
                C0359x.m125a(null);
                this.f71a.f199l = jSONObject2.getInt("adReadDelayTime");
                new StringBuilder("from spot info value:").append(jSONObject2.getInt("adReadDelayTime"));
                C0359x.m125a(null);
                this.f71a.f200m = jSONObject2.getString("templateRequestUrl");
                new StringBuilder("from spot info value:").append(jSONObject2.getString("templateRequestUrl"));
                C0359x.m125a(null);
                this.f71a.f201n = C0357v.m122c(this.f73c.mo7279f());
            } catch (JSONException e) {
                String str = "Ad response data Error from j.";
                e.getMessage();
                C0359x.m126b(str, "parse");
                this.f71a.f207t.onFailed(FailNotificationReason.RESPONSE);
            } catch (C0360y e2) {
                String str2 = "Ad response data Error from h.";
                e2.getMessage();
                C0359x.m126b(str2, "data");
                this.f71a.f207t.onFailed(e2.mo7267a());
            }
            if (this.f71a.mo7269a() == C0331am.LODING) {
                this.f71a.f209v.post(new C0330al(this, this.f73c));
            }
        } catch (InterruptedException e3) {
            this.f71a.mo7273a(C0331am.ERROR);
            new StringBuilder("Callable InterruptedException.").append(e3.getMessage());
            C0359x.m126b("Ad request get ad data.", "Interrupt");
            this.f71a.f207t.onFailed(FailNotificationReason.UNKNOWN);
        } catch (ExecutionException e4) {
            this.f71a.mo7273a(C0331am.ERROR);
            if (e4.getCause().getClass() == C0360y.class) {
                C0360y yVar = (C0360y) e4.getCause();
                new StringBuilder("Callable NotificationException. reason:").append(yVar.mo7267a());
                C0359x.m126b("Ad request get ad data.", "Notification");
                this.f71a.f207t.onFailed(yVar.mo7267a());
                return;
            }
            new StringBuilder("Callable ExecutionException.").append(e4.getMessage());
            C0359x.m126b("Ad request get ad data.", "Execution");
            this.f71a.f207t.onFailed(FailNotificationReason.UNKNOWN);
        }
    }
}
