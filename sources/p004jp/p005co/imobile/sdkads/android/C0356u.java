package p004jp.p005co.imobile.sdkads.android;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* renamed from: jp.co.imobile.sdkads.android.u */
final class C0356u implements Runnable {

    /* renamed from: a */
    private final /* synthetic */ Future f178a;

    /* renamed from: b */
    private final /* synthetic */ String f179b;

    /* renamed from: c */
    private final /* synthetic */ C0340e f180c;

    C0356u(Future future, String str, C0340e eVar) {
        this.f178a = future;
        this.f179b = str;
        this.f180c = eVar;
    }

    public final void run() {
        try {
            String str = (String) this.f178a.get();
            if (this.f179b.equals("")) {
                new StringBuilder("response:").append(str);
                C0359x.m126b("Send request from html complete.", "");
                return;
            }
            this.f180c.mo7215a("javascript:" + this.f179b + "('" + str + "');");
            new StringBuilder("callbackFunctionName:").append(this.f179b).append("response:").append(str);
            C0359x.m126b("Send request from html complete.", "");
        } catch (InterruptedException e) {
            new StringBuilder("Callable InterruptedException.").append(e.getMessage());
            C0359x.m126b("Send request from html not complete.", "Interrupt");
        } catch (ExecutionException e2) {
            if (e2.getCause().getClass() == C0360y.class) {
                new StringBuilder("Callable NotificationException. reason:").append(((C0360y) e2.getCause()).mo7267a());
                C0359x.m126b("Send request from html not complete.", "Notification");
                return;
            }
            new StringBuilder("Callable ExecutionException.").append(e2.getMessage());
            C0359x.m126b("Send request from html not complete.", "Execution");
        }
    }
}
