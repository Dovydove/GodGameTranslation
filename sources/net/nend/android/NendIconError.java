package net.nend.android;

import net.nend.android.NendAdView.NendError;

public class NendIconError {
    public static final int ERROR_ICONVIEW = 1;
    public static final int ERROR_LOADER = 0;

    /* renamed from: a */
    private NendAdIconLoader f534a = null;

    /* renamed from: b */
    private NendAdIconView f535b = null;

    /* renamed from: c */
    private NendError f536c;

    /* renamed from: d */
    private int f537d;

    public NendAdIconLoader getLoader() {
        return this.f534a;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo7742a(NendAdIconLoader nendAdIconLoader) {
        this.f534a = nendAdIconLoader;
    }

    public NendAdIconView getIconView() {
        return this.f535b;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo7743a(NendAdIconView nendAdIconView) {
        this.f535b = nendAdIconView;
    }

    public NendError getNendError() {
        return this.f536c;
    }

    public void setNendError(NendError nendError) {
        this.f536c = nendError;
    }

    public int getErrorType() {
        return this.f537d;
    }

    public void setErrorType(int i) {
        this.f537d = i;
    }
}
