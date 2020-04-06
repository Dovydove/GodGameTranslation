package net.nend.android;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import net.nend.android.internal.p013c.p015b.C0558a;
import net.nend.android.internal.p013c.p015b.C0563b;
import net.nend.android.internal.utilities.C0755e;
import net.nend.android.internal.utilities.C0757f;
import net.nend.android.internal.utilities.C0758g;

public class NendAdNativeClient {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public C0558a f391a;

    /* renamed from: b */
    private C0483a f392b;

    public interface Callback {
        void onFailure(NendError nendError);

        void onSuccess(NendAdNative nendAdNative);
    }

    public enum NendError {
        FAILED_AD_REQUEST(340, "Failed to Ad request."),
        INVALID_RESPONSE_TYPE(341, "Response type is invalid."),
        INVALID_INTERVAL_MILLIS(342, "Interval millis set 30000 or more.");
        

        /* renamed from: a */
        private final int f394a;

        /* renamed from: b */
        private final String f395b;

        private NendError(int i, String str) {
            this.f394a = i;
            this.f395b = str;
        }

        public String getMessage() {
            return this.f395b;
        }

        public int getCode() {
            return this.f394a;
        }
    }

    /* renamed from: net.nend.android.NendAdNativeClient$a */
    private class C0483a extends Handler {

        /* renamed from: b */
        private boolean f397b = false;

        /* renamed from: c */
        private Callback f398c;

        /* renamed from: d */
        private long f399d;

        C0483a(Looper looper, Callback callback) {
            super(looper);
            this.f398c = callback;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            NendAdNativeClient.this.f391a.mo7857a(this.f398c);
            if (this.f397b) {
                sendEmptyMessageDelayed(113, this.f399d);
            }
        }

        /* renamed from: a */
        public void mo7595a() {
            this.f397b = false;
            removeMessages(113);
        }

        /* access modifiers changed from: private */
        /* renamed from: b */
        public boolean m283b() {
            return this.f397b;
        }

        /* renamed from: a */
        public void mo7596a(Long l) {
            this.f399d = l.longValue();
            this.f397b = true;
            sendEmptyMessageDelayed(113, l.longValue());
        }
    }

    public NendAdNativeClient(Context context, int i, String str) {
        if (i <= 0) {
            throw new IllegalArgumentException(C0758g.ERR_INVALID_SPOT_ID.mo8197a("spot id : " + i));
        } else if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(C0758g.ERR_INVALID_API_KEY.mo8197a("api key : " + str));
        } else {
            C0755e.m1197a(context);
            this.f391a = new C0558a(context, new C0563b(context, i, str));
        }
    }

    public void loadAd(Callback callback) {
        this.f391a.mo7857a(callback);
    }

    public void enableAutoReload(long j, Callback callback) {
        if (this.f392b != null && this.f392b.m283b()) {
            this.f392b.mo7595a();
        }
        if (j < 30000) {
            callback.onFailure(NendError.INVALID_INTERVAL_MILLIS);
            return;
        }
        this.f392b = new C0483a(Looper.getMainLooper(), callback);
        this.f392b.mo7596a(Long.valueOf(j));
    }

    public void disableAutoReload() {
        if (this.f392b == null) {
            C0757f.m1220c("First, you should call 'enableAutoReload'.");
        } else {
            this.f392b.mo7595a();
        }
    }
}
